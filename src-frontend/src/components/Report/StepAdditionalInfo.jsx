import PropTypes from "prop-types";
import {
  TextField,
  Box,
  Typography,
  Button,
  Grid,
  MenuItem,
  Select,
  FormControl,
  InputLabel,
} from "@mui/material";
import AddIcon from "@mui/icons-material/Add";
import RemoveIcon from "@mui/icons-material/Remove";

const StepAdditionalInfo = ({
  wounded,
  setWounded,
  materials,
  setMaterials,
  woundedErrors,
  setWoundedErrors,
}) => {
  // 📌 Agregar un nuevo herido con valores por defecto
  const addWounded = () => {
    setWounded([
      ...wounded,
      {
        nombre: "",
        apellidos: "",
        cantidad: "1",
        estadoSalud: "",
        estadoVital: "",
        tipoHerida: "Desconocido",
        descripcionHerida: "No especificado",
        edad: "0",
        genero: "undefined",
      },
    ]);
  };

  // 📌 Eliminar un herido
  const removeWounded = (index) => {
    setWounded(wounded.filter((_, i) => i !== index));
  };

  // 📌 Agregar un nuevo material con valores por defecto
  const addMaterial = () => {
    setMaterials([
      ...materials,
      {
        tipoMaterial: "",
        cantidad: "1",
        condicionMaterial: "new",
        descripcion: "",
      },
    ]);
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
            <Grid item xs={12} md={12}>
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
            <Grid item xs={12} md={12}>
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
            <Grid item xs={12} md={6}>
              <TextField
                label="Edad"
                type="number"
                fullWidth
                value={w.edad}
                onChange={(e) => {
                  let value = parseInt(e.target.value, 10);
                  if (isNaN(value) || value < 0) {
                    value = 0;
                  }
                  const updated = [...wounded];
                  updated[index].edad = value.toString();
                  setWounded(updated);
                }}
              />
            </Grid>
            <Grid item xs={12} md={6}>
              <FormControl fullWidth>
                <InputLabel id="wounded__select-label--genero">Género</InputLabel>
                <Select
                  labelId="wounded__select-label--genero"
                  label="Género"
                  value={w.genero}
                  onChange={(e) => {
                    const updated = [...wounded];
                    updated[index].genero = e.target.value;
                    setWounded(updated);
                  }}
                >
                  <MenuItem value="male">Masculino</MenuItem>
                  <MenuItem value="female">Femenino</MenuItem>
                  <MenuItem value="undefined">Otro</MenuItem>
                </Select>
              </FormControl>
            </Grid>
            <Grid item xs={12} md={12}>
              <FormControl fullWidth error={woundedErrors[index]?.estadoSalud}>
                <InputLabel id={`wounded__select-label--estadoSalud-${index}`}>
                  Estado Salud
                </InputLabel>
                <Select
                  labelId={`wounded__select-label--estadoSalud-${index}`}
                  label="Estado Salud"
                  value={w.estadoSalud}
                  onChange={(e) => {
                    const updated = [...wounded];
                    updated[index].estadoSalud = e.target.value;
                    setWounded(updated);
                    const errorsUpdated = [...woundedErrors];
                    errorsUpdated[index].estadoSalud = !e.target.value;
                    setWoundedErrors(errorsUpdated);
                  }}
                >
                  <MenuItem value="stable">Estable</MenuItem>
                  <MenuItem value="serious_stable">Grave Estable</MenuItem>
                  <MenuItem value="serious_unstable">Grave Inestable</MenuItem>
                  <MenuItem value="extremely_serious">Extremadamente Grave</MenuItem>
                </Select>
              </FormControl>
              {woundedErrors[index]?.estadoSalud && (
                <Typography color="error" variant="caption">
                  Estado de salud es obligatorio.
                </Typography>
              )}
            </Grid>

            <Grid item xs={12} md={12}>
              <FormControl fullWidth error={woundedErrors[index]?.estadoVital}>
                <InputLabel id={`wounded__select-label--estadoVital-${index}`}>
                  Estado Vital
                </InputLabel>
                <Select
                  labelId={`wounded__select-label--estadoVital-${index}`}
                  label="Estado Vital"
                  value={w.estadoVital}
                  onChange={(e) => {
                    const updated = [...wounded];
                    updated[index].estadoVital = e.target.value;
                    setWounded(updated);
                    const errorsUpdated = [...woundedErrors];
                    errorsUpdated[index].estadoVital = !e.target.value;
                    setWoundedErrors(errorsUpdated);
                  }}
                >
                  <MenuItem value="alive">Vivo</MenuItem>
                  <MenuItem value="deceased">Fallecido</MenuItem>
                </Select>
              </FormControl>
              {woundedErrors[index]?.estadoVital && (
                <Typography color="error" variant="caption">
                  Estado vital es obligatorio.
                </Typography>
              )}
            </Grid>
            <Grid item xs={12}>
              <FormControl fullWidth>
                <InputLabel id="wounded__select-label--tipoHerida">Tipo de Herida</InputLabel>
                <Select
                  labelId="wounded__select-label--tipoHerida"
                  label="Tipo de Herida"
                  value={w.tipoHerida}
                  onChange={(e) => {
                    const updated = [...wounded];
                    updated[index].tipoHerida = e.target.value;
                    setWounded(updated);
                  }}
                >
                  <MenuItem value="fracture">Fractura</MenuItem>
                  <MenuItem value="burn">Quemadura</MenuItem>
                  <MenuItem value="cut">Corte</MenuItem>
                  <MenuItem value="contusion">Contusión</MenuItem>
                  <MenuItem value="hemorrhage">Hemorragia</MenuItem>
                  <MenuItem value="unknown">Desconocido</MenuItem>
                </Select>
              </FormControl>
            </Grid>
            <Grid item xs={12}>
              <TextField
                label="Descripción de la herida"
                fullWidth
                multiline
                rows={2}
                value={w.descripcionHerida}
                onChange={(e) => {
                  const updated = [...wounded];
                  updated[index].descripcionHerida = e.target.value;
                  setWounded(updated);
                }}
              />
            </Grid>
            <Grid item xs={12} display="flex" justifyContent="center">
              <Button onClick={() => removeWounded(index)} color="error">
                <RemoveIcon /> Eliminar
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
            <Grid item xs={12} md={12}>
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
            <Grid item xs={12} md={6}>
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
            <Grid item xs={12} md={6}>
              <FormControl fullWidth>
                <InputLabel id="materials__select-label--material_condition">Condición</InputLabel>
                <Select
                  labelId="materials__select-label--material_condition"
                  label="Condición"
                  value={m.condicionMaterial}
                  onChange={(e) => {
                    const updated = [...materials];
                    updated[index].condicionMaterial = e.target.value;
                    setMaterials(updated);
                  }}
                >
                  <MenuItem value="new">Nuevo</MenuItem>
                  <MenuItem value="used">Usado</MenuItem>
                  <MenuItem value="damaged">Dañado</MenuItem>
                </Select>
              </FormControl>
            </Grid>
            <Grid item xs={12} md={12}>
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
            <Grid item xs={12} display="flex" justifyContent="center">
              <Button onClick={() => removeMaterial(index)} color="error">
                <RemoveIcon /> Eliminar
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

StepAdditionalInfo.propTypes = {
  materials: PropTypes.shape({
    filter: PropTypes.func,
    map: PropTypes.func,
  }),
  setMaterials: PropTypes.func,
  setWounded: PropTypes.func,
  setWoundedErrors: PropTypes.func,
  wounded: PropTypes.shape({
    filter: PropTypes.func,
    map: PropTypes.func,
  }),
  woundedErrors: PropTypes.any,
};

export default StepAdditionalInfo;
