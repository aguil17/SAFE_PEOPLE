import { useState } from "react";
import { Fab, Box, Button } from "@mui/material";
import AddIcon from "@mui/icons-material/Add";
import "./ReportButton.scss";

const ReportButton = ({ onSelectIncidentType }) => {
  const [isOpen, setIsOpen] = useState(false);

  const toggleMenu = () => setIsOpen(!isOpen);

  return (
    <Box className="report-button">
      {/* 🔹 Botón flotante "+" */}
      <Fab color="primary" onClick={toggleMenu} className="report-button__fab">
        <AddIcon />
      </Fab>

      {/* 🔹 Opciones de reporte (Incendio, Robo, Accidente) */}
      {isOpen && (
        <Box className="report-button__options">
          {["Incendio", "Robo", "Accidente"].map((type) => (
            <Button
              key={type}
              className="report-button__option"
              onClick={() => {
                onSelectIncidentType(type);
                setIsOpen(false); // Cierra el menú después de seleccionar
              }}
            >
              {type}
            </Button>
          ))}
        </Box>
      )}
    </Box>
  );
};

export default ReportButton;
