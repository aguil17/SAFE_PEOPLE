import { useState } from "react";
import { TextField, Button, Box, Modal } from "@mui/material";
import "./IncidentForm.scss";

const IncidentForm = ({ open, onClose, onSubmit, incidentType }) => {
  const [description, setDescription] = useState("");
  const [photo, setPhoto] = useState(null);

  const handlePhotoChange = (event) => {
    const file = event.target.files[0];
    setPhoto(file);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    onSubmit({ type: incidentType, description, photo });
    onClose(); // 🔹 Cierra el formulario después de enviar
  };

  return (
    <Modal open={open} onClose={onClose} className="incident-form__modal">
      <Box className="incident-form">
        <h2>Reportar {incidentType}</h2>
        
        {/* 🔹 Campo de descripción */}
        <TextField
          label="Descripción del incidente"
          multiline
          rows={3}
          fullWidth
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          required
        />

        {/* 🔹 Subir una foto (opcional) */}
        <input type="file" accept="image/*" onChange={handlePhotoChange} />

        {/* 🔹 Botones */}
        <Box className="incident-form__buttons">
          <Button onClick={onClose} className="incident-form__cancel">
            Cancelar
          </Button>
          <Button type="submit" variant="contained" onClick={handleSubmit}>
            Reportar
          </Button>
        </Box>
      </Box>
    </Modal>
  );
};

export default IncidentForm;
