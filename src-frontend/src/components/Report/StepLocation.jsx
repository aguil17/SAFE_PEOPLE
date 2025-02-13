import { Box, TextField } from "@mui/material";

const StepLocation = ({ latitude, longitude, city, district }) => {
  return (
    <Box>
      {/* <TextField label="Latitud" fullWidth value={latitude || ""} disabled sx={{ mb: 2 }} />
      <TextField label="Longitud" fullWidth value={longitude || ""} disabled sx={{ mb: 2 }} /> */}
      <TextField
        label="Ciudad (Obtenida automáticamente)"
        fullWidth
        value={city || ""}
        disabled // 🔹 Ahora es solo de lectura
        sx={{ mb: 2 }}
      />
      <TextField
        label="Distrito (Obtenido automáticamente)"
        fullWidth
        value={district || ""}
        disabled // 🔹 Ahora es solo de lectura
      />
    </Box>
  );
};

export default StepLocation;
