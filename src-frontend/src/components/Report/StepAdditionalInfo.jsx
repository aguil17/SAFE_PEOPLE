import { TextField, Box, Typography, Grid } from "@mui/material";


const StepAdditionalInfo = ({ informant, setInformant, wounded, setWounded, materials, setMaterials }) => {
  return (
    <Box>
      {/* 🔹 INFORMANTE */}
      <Typography variant="h6" sx={{ mb: 2, fontWeight: "bold" }}>
        Informante (Opcional)
      </Typography>
      <Grid container spacing={2}>
        <Grid item xs={6}>
          <TextField
            label="Nombre"
            fullWidth
            value={informant.name}
            onChange={(e) => setInformant({ ...informant, name: e.target.value })}
          />
        </Grid>
        <Grid item xs={6}>
          <TextField
            label="Apellidos"
            fullWidth
            value={informant.lastName}
            onChange={(e) => setInformant({ ...informant, lastName: e.target.value })}
          />
        </Grid>
        <Grid item xs={6}>
          <TextField
            label="Celular"
            fullWidth
            value={informant.cellphone}
            onChange={(e) => setInformant({ ...informant, cellphone: e.target.value })}
          />
        </Grid>
        <Grid item xs={6}>
          <TextField
            label="Correo Electrónico"
            fullWidth
            value={informant.email}
            onChange={(e) => setInformant({ ...informant, email: e.target.value })}
          />
        </Grid>
      </Grid>

      {/* 🔹 HERIDOS */}
      <Typography variant="h6" sx={{ mt: 4, mb: 2, fontWeight: "bold" }}>
        Heridos (Opcional)
      </Typography>
      <Grid container spacing={2}>
        <Grid item xs={4}>
          <TextField
            label="Cantidad"
            fullWidth
            value={wounded.quantity}
            onChange={(e) => setWounded({ ...wounded, quantity: e.target.value })}
          />
        </Grid>
        <Grid item xs={4}>
          <TextField
            label="Nombre"
            fullWidth
            value={wounded.name}
            onChange={(e) => setWounded({ ...wounded, name: e.target.value })}
          />
        </Grid>
        <Grid item xs={4}>
          <TextField
            label="Apellidos"
            fullWidth
            value={wounded.lastName}
            onChange={(e) => setWounded({ ...wounded, lastName: e.target.value })}
          />
        </Grid>
      </Grid>

      {/* 🔹 MATERIALES */}
      <Typography variant="h6" sx={{ mt: 4, mb: 2, fontWeight: "bold" }}>
        Materiales (Opcional)
      </Typography>
      <Grid container spacing={2}>
        <Grid item xs={6}>
          <TextField
            label="Tipo"
            fullWidth
            value={materials.type}
            onChange={(e) => setMaterials({ ...materials, type: e.target.value })}
          />
        </Grid>
        <Grid item xs={6}>
          <TextField
            label="Cantidad"
            fullWidth
            value={materials.quantity}
            onChange={(e) => setMaterials({ ...materials, quantity: e.target.value })}
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            label="Descripción"
            fullWidth
            multiline
            rows={2}
            value={materials.description}
            onChange={(e) => setMaterials({ ...materials, description: e.target.value })}
          />
        </Grid>
      </Grid>
    </Box>
  );
};

export default StepAdditionalInfo;
