import { useState } from "react";
import { TextField, Box, Typography, Button } from "@mui/material";
import UploadFileIcon from "@mui/icons-material/UploadFile";

const StepIncidentDetails = ({ description, setDescription, photo, setPhoto, incidentType }) => {
  const [error, setError] = useState(false);

  const handlePhotoChange = (event) => {
    setPhoto(event.target.files[0]);
  };

  const handleDescriptionChange = (e) => {
    const value = e.target.value;
    setDescription(value);
    setError(value.trim() === ""); // 🔹 Si está vacío, muestra error
  };

  return (
    <Box>
      {/* 🔹 Mostrar tipo de incidente */}
      <Typography variant="h6" sx={{ marginBottom: 2, fontWeight: "bold", color: "#041122" }}>
        Tipo de incidente: {incidentType || "No especificado"}
      </Typography>

      {/* 🔹 Campo de descripción */}
      <TextField
        label="Descripción del incidente *"
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
        {photo ? photo.name : "Subir una imagen"}
        <input type="file" accept="image/*" hidden onChange={handlePhotoChange} />
      </Button>
    </Box>
  );
};

export default StepIncidentDetails;
