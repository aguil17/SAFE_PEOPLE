import { TextField, Box, Typography, Button, Grid } from "@mui/material";
import AddIcon from "@mui/icons-material/Add";
import RemoveIcon from "@mui/icons-material/Remove";

const StepAdditionalInfo = ({ wounded, setWounded, materials, setMaterials }) => {
  // 📌 Agregar un nuevo herido
  const addWounded = () => {
    setWounded([...wounded, { nombre: "", apellidos: "", cantidad: "1" }]);
  };

  // 📌 Eliminar un herido
  const removeWounded = (index) => {
    setWounded(wounded.filter((_, i) => i !== index));
  };

  // 📌 Agregar un nuevo material
  const addMaterial = () => {
    setMaterials([...materials, { tipoMaterial: "", cantidad: "1", descripcion: "" }]);
  };

  // 📌 Eliminar un material
  const removeMaterial = (index) => {
    setMaterials(materials.filter((_, i) => i !== index));
  };

  return (
    <Box>
      {/* 🔹 Sección de Heridos */}
      <Typography variant="h6" sx={{ mt: 2, fontWeight: "bold" }}>
        Heridos
      </Typography>
      <Grid container spacing={2}>
        {wounded.map((w, index) => (
          <Grid container item xs={12} spacing={2} key={index} alignItems="center">
            <Grid item xs={12} md={4}>
              <TextField
                label="Nombre"
                fullWidth
                value={w.nombre}
                onChange={(e) => {
                  const updated = [...wounded];
                  updated[index].nombre = e.target.value;
                  setWounded(updated);
                }}
              />
            </Grid>
            <Grid item xs={12} md={4}>
              <TextField
                label="Apellidos"
                fullWidth
                value={w.apellidos}
                onChange={(e) => {
                  const updated = [...wounded];
                  updated[index].apellidos = e.target.value;
                  setWounded(updated);
                }}
              />
            </Grid>
            <Grid item xs={6} md={2}>
              <TextField
                label="Cantidad"
                type="number"
                fullWidth
                value={w.cantidad}
                onChange={(e) => {
                  const updated = [...wounded];
                  updated[index].cantidad = e.target.value;
                  setWounded(updated);
                }}
              />
            </Grid>
            <Grid item xs={6} md={2} display="flex" justifyContent="center">
              <Button onClick={() => removeWounded(index)} color="error">
                <RemoveIcon />
              </Button>
            </Grid>
          </Grid>
        ))}
      </Grid>
      <Button onClick={addWounded} sx={{ mt: 2 }}>
        <AddIcon /> Agregar Herido
      </Button>

      {/* 🔹 Sección de Materiales */}
      <Typography variant="h6" sx={{ mt: 3, fontWeight: "bold" }}>
        Materiales
      </Typography>
      <Grid container spacing={2}>
        {materials.map((m, index) => (
          <Grid container item xs={12} spacing={2} key={index} alignItems="center">
            <Grid item xs={12} md={4}>
              <TextField
                label="Tipo de Material"
                fullWidth
                value={m.tipoMaterial}
                onChange={(e) => {
                  const updated = [...materials];
                  updated[index].tipoMaterial = e.target.value;
                  setMaterials(updated);
                }}
              />
            </Grid>
            <Grid item xs={6} md={2}>
              <TextField
                label="Cantidad"
                type="number"
                fullWidth
                value={m.cantidad}
                onChange={(e) => {
                  const updated = [...materials];
                  updated[index].cantidad = e.target.value;
                  setMaterials(updated);
                }}
              />
            </Grid>
            <Grid item xs={12} md={4}>
              <TextField
                label="Descripción"
                fullWidth
                value={m.descripcion}
                onChange={(e) => {
                  const updated = [...materials];
                  updated[index].descripcion = e.target.value;
                  setMaterials(updated);
                }}
              />
            </Grid>
            <Grid item xs={6} md={2} display="flex" justifyContent="center">
              <Button onClick={() => removeMaterial(index)} color="error">
                <RemoveIcon />
              </Button>
            </Grid>
          </Grid>
        ))}
      </Grid>
      <Button onClick={addMaterial} sx={{ mt: 2 }}>
        <AddIcon /> Agregar Material
      </Button>
    </Box>
  );
};

export default StepAdditionalInfo;
