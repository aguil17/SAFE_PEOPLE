import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Stepper, Step, StepLabel, StepContent, Button, Box, Modal, CircularProgress } from "@mui/material";
import StepIncidentDetails from "./StepIncidentDetails";
import StepAdditionalInfo from "./StepAdditionalInfo";
import "./IncidentStepper.scss";
import { reportIncident } from "../../services/incidentService";
import { addIncident } from "../../redux/incidentsSlice";

const steps = [
  { label: "Detalles del Incidente", component: StepIncidentDetails },
  { label: "Información Adicional", component: StepAdditionalInfo },
];

const IncidentStepper = ({ open, onClose, onSubmit, incidentType, markerPosition }) => {
  const dispatch = useDispatch();
  const [activeStep, setActiveStep] = useState(0);
  const [description, setDescription] = useState("");
  const [photo, setPhoto] = useState(null);
  const [woundedList, setWoundedList] = useState([]);
  const [materialsList, setMaterialsList] = useState([]);
  const [nombreCiudad, setNombreCiudad] = useState("No especificado");
  const [nombreDistrito, setNombreDistrito] = useState("No especificado");
  const [loading, setLoading] = useState(false);
  const [isDescriptionValid, setIsDescriptionValid] = useState(false);
  const [isWoundedValid, setIsWoundedValid] = useState(null);
  const [woundedErrors, setWoundedErrors] = useState([]);

  const user = useSelector((state) => state.auth.user);

  // 🔹 Datos del informante desde Redux
  const informant = {
    nombre: user?.persona?.name || "",
    apellidos: user?.persona?.lastName || "",
    correoElectronico: user?.persona?.email || "",
    celular: user?.persona?.cellphone || "",
  };

  const latitude = markerPosition?.[0] || "";
  const longitude = markerPosition?.[1] || "";

  // 📌 Función para obtener la ciudad y distrito por coordenadas
  const fetchLocationDetails = async (lat, lng) => {
    try {
      const response = await fetch(
        `https://nominatim.openstreetmap.org/reverse?format=json&lat=${lat}&lon=${lng}`
      );
      const data = await response.json();

      if (data.address) {
        setNombreCiudad(
          data.address.city || data.address.town || data.address.village || "No especificado"
        );
        setNombreDistrito(data.address.suburb || "No especificado");
      }
    } catch (error) {
      console.error("Error obteniendo la ubicación:", error);
    }
  };

  // 📌 Obtener ciudad automáticamente cuando cambia la ubicación
  useEffect(() => {
    if (latitude && longitude) {
      fetchLocationDetails(latitude, longitude);
    }
  }, [latitude, longitude]);

  // 📌 Enviar datos al backend al finalizar
  const handleFinish = async () => {
    const typeMapping = {
      Incendio: "fire",
      Robo: "robbery",
      Accidente: "accident",
    };
    const incidentData = {
      descripcion: description,
      fecha: new Date().toISOString().split("T")[0],
      hora: new Date().toLocaleTimeString(),
      idUsuario: user?.usuario?.id || 0,
      foto: photo || "No disponible",
      tipoIncidente: typeMapping[incidentType] || "unknown",

      ubicacion: {
        nombreCiudad,
        nombreDistrito,
        longitud: markerPosition[1].toString(),
        referencia: "Ubicación seleccionada en el mapa",
        descripcion: "Ubicación ingresada automáticamente",
        latitud: markerPosition[0].toString(),
      },
      heridos: woundedList.map(w => ({
        nombre: w.nombre,
        apellidos: w.apellidos,
        cantidad: w.cantidad,
        estadoSalud: w.estadoSalud || "stable",
        estadoVital: w.estadoVital || "alive",
        tipoHerida: w.tipoHerida || "Desconocido",
        tipoHerido: w.tipoHerido || "Desconocido",
        descripcionHerida: w.descripcionHerida || "No especificado",
        edad: w.edad ? w.edad.toString() : "0",
        genero: w.genero === "masculino" ? "male" : w.genero === "femenino" ? "female" : "undefined",
      })),

      informantes: [informant],

      materiales: materialsList.length > 0 ? materialsList.map(m => ({
        tipoMaterial: m.tipoMaterial || "N/A",
        cantidad: m.cantidad || "0",
        condicionMaterial: m.condicionMaterial || "damaged",
        descripcion: m.descripcion || "N/A",
      })) : [{
        tipoMaterial: "N/A",
        cantidad: "0",
        condicionMaterial: "damaged",
        descripcion: "N/A",
      }],
    };

    try {
      setLoading(true);
      const response = await dispatch(addIncident(incidentData));
      if (response?.payload?.success) {
        alert("¡Incidente reportado con éxito! 🎉");

        // 🔹 Pasamos el incidente reportado al mapa
        onSubmit({
          ...incidentData,
          ubicacion: {
            latitud: markerPosition[0],
            longitud: markerPosition[1],
          },
        });

        setActiveStep(0);
        setDescription("");
        setPhoto(null);
        setWoundedList([]);
        setMaterialsList([]);
        onClose();
      } else {
        alert(`Error al reportar el incidente: ${response.message || "Intenta de nuevo"}`);
      }
    } catch (error) {
      console.error("Error al enviar el incidente:", error);
      alert("Hubo un problema al conectar con el servidor.");
    } finally {
      setLoading(false);
    }
  };


  useEffect(() => {
    if (open) {
      setActiveStep(0);
      setIsDescriptionValid(false);
      setDescription("");
      setPhoto(null);
      setWoundedList([]);
      setMaterialsList([]);

      if (latitude && longitude) {
        fetchLocationDetails(latitude, longitude);
      }
    }
  }, [open]);

  const validateWoundedList = () => {
    if (woundedList.length === 0) {
      setWoundedErrors([]);
      return true;
    }

    let hasErrors = false;
    const errors = woundedList.map((w) => {
      const error = {
        estadoSalud: !w.estadoSalud || w.estadoSalud === "",
        estadoVital: !w.estadoVital || w.estadoVital === "",
      };
      if (error.estadoSalud || error.estadoVital) hasErrors = true;
      return error;
    });

    setWoundedErrors(errors);
    return !hasErrors;
  };

  useEffect(() => {
    setIsWoundedValid(validateWoundedList());
  }, [woundedList]);

  return (
    <Modal open={open} onClose={onClose} className="incident-stepper__modal">
      <Box
        className="incident-stepper"
        sx={{
          maxHeight: "90vh",
          overflow: "auto",
          p: 3,
        }}
      >
        <Stepper activeStep={activeStep} orientation="vertical">
          {steps.map((step, index) => {
            const StepComponent = step.component;
            return (
              <Step key={index}>
                <StepLabel>{step.label}</StepLabel>
                <StepContent sx={{ maxHeight: "60vh", overflowY: "auto" }}>
                  <StepComponent
                    description={description}
                    setDescription={setDescription}
                    setIsDescriptionValid={setIsDescriptionValid}
                    photo={photo}
                    setPhoto={setPhoto}
                    wounded={woundedList}
                    setWounded={setWoundedList}
                    woundedErrors={woundedErrors}
                    setWoundedErrors={setWoundedErrors}
                    materials={materialsList}
                    setMaterials={setMaterialsList}
                    incidentType={incidentType}
                    latitude={latitude}
                    longitude={longitude}
                  />
                  <Box className="incident-stepper__buttons">
                    {index > 0 && (
                      <Button
                        onClick={() => setActiveStep(index - 1)}
                        className="incident-stepper__button"
                        disabled={loading}
                      >
                        Atrás
                      </Button>
                    )}
                    <Button
                      onClick={() => (index === steps.length - 1 ? handleFinish() : setActiveStep(index + 1))}
                      className="incident-stepper__button"
                      disabled={loading || (index === 0 && !isDescriptionValid) || (index === steps.length - 1 && isWoundedValid === false)}
                    >
                      {loading ? <CircularProgress size={24} color="inherit" /> : index === steps.length - 1 ? "Finalizar" : "Siguiente"}
                    </Button>
                  </Box>
                </StepContent>
              </Step>
            );
          })}
        </Stepper>
      </Box>
    </Modal>
  );
};

export default IncidentStepper;
