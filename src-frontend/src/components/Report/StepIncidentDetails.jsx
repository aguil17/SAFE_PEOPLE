import PropTypes from "prop-types";
import { useState, useEffect } from "react";
import { TextField, Box, Typography, Button } from "@mui/material";
import UploadFileIcon from "@mui/icons-material/UploadFile";
import imageCompression from "browser-image-compression";

const StepIncidentDetails = ({
  description,
  setDescription,
  setIsDescriptionValid,
  photo,
  setPhoto,
  incidentType,
}) => {
  const [error, setError] = useState(false);
  const [compressedPhoto, setCompressedPhoto] = useState(photo || null); // 🔹 Inicializar con photo si ya existe

  useEffect(() => {
    if (photo) {
      setCompressedPhoto(photo); // 🔹 Restaurar la imagen cuando regresas al paso anterior
    }
  }, [photo]);

  const handlePhotoChange = async (event) => {
    const file = event.target.files[0];
    if (!file) return;

    try {
      const options = {
        maxSizeMB: 0.2,
        maxWidthOrHeight: 400,
        useWebWorker: true,
      };

      const compressedFile = await imageCompression(file, options);
      setPhoto(compressedFile);
      // Convertir a Base64
      const reader = new FileReader();
      reader.readAsDataURL(compressedFile);
      reader.onloadend = () => {
        setPhoto(reader.result); // 🔹 Ahora photo contiene la imagen en Base64
        setCompressedPhoto(reader.result);
      };
    } catch (error) {
      console.error("Error comprimiendo la imagen:", error);
    }
  };

  const handleDescriptionChange = (e) => {
    const value = e.target.value;
    setDescription(value);
    setIsDescriptionValid(value.trim() !== "");
    setError(value.trim() === "");
  };

  return (
    <Box>
      {/* 🔹 Mostrar tipo de incidente */}
      <Typography variant="h6" sx={{ marginBottom: 2, fontWeight: "bold", color: "#041122" }}>
        Tipo de incidente: {incidentType || "No especificado"}
      </Typography>

      {/* 🔹 Campo de descripción */}
      <TextField
        label="Descripción del incidente"
        multiline
        rows={3}
        fullWidth
        value={description}
        onChange={handleDescriptionChange}
        error={error}
        helperText={error ? "La descripción es obligatoria" : ""}
        required
      />

      {/* 🔹 Botón de carga de imagen */}
      <Button
        variant="outlined"
        component="label"
        sx={{ marginTop: 2, display: "flex", alignItems: "center", gap: 1 }}
      >
        <UploadFileIcon />
        {photo ? "Imagen seleccionada" : "Subir una imagen"}
        <input type="file" accept="image/*" hidden onChange={handlePhotoChange} />
      </Button>

      {/* 🔹 Vista previa de la imagen comprimida */}
      {compressedPhoto && (
        <Box sx={{ marginTop: 2 }}>
          <Typography variant="body2">Vista previa:</Typography>
          <img src={compressedPhoto} alt="Vista previa" style={{ maxWidth: "50%" }} />
        </Box>
      )}
    </Box>
  );
};

StepIncidentDetails.propTypes = {
  description: PropTypes.any,
  incidentType: PropTypes.string,
  photo: PropTypes.any,
  setDescription: PropTypes.func,
  setIsDescriptionValid: PropTypes.func,
  setPhoto: PropTypes.func,
};

export default StepIncidentDetails;
