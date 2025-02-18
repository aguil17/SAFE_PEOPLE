import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { Stepper, Step, StepLabel, StepContent, Button, Box, Modal, CircularProgress } from "@mui/material";
import StepIncidentDetails from "./StepIncidentDetails";
import StepAdditionalInfo from "./StepAdditionalInfo";
import "./IncidentStepper.scss";
import { reportIncident } from "../../services/incidentService";

const steps = [
  { label: "Detalles del Incidente", component: StepIncidentDetails },
  { label: "Información Adicional", component: StepAdditionalInfo },
];

const IncidentStepper = ({ open, onClose, onSubmit, incidentType, markerPosition }) => {
  const [activeStep, setActiveStep] = useState(0);
  const [description, setDescription] = useState("");
  const [photo, setPhoto] = useState(null);
  const [woundedList, setWoundedList] = useState([]);
  const [materialsList, setMaterialsList] = useState([]);
  const [nombreCiudad, setNombreCiudad] = useState("No especificado");
  const [nombreDistrito, setNombreDistrito] = useState("No especificado");
  const [loading, setLoading] = useState(false);

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
  
    console.log("photo", photo);
    
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
        estadoSalud: "stable",
        estadoVital: "alive",
        tipoHerida: "Desconocido",
        tipoHerido: "Desconocido",
        descripcionHerida: "No especificado",
        edad: w.edad ? w.edad.toString() : "0",
        genero: w.genero === "masculino" ? "male" : w.genero === "femenino" ? "female" : "male",
      })),
  
      informantes: [informant],
  
      materiales: materialsList.map(m => ({
        tipoMaterial: m.tipoMaterial,
        cantidad: m.cantidad,
        condicionMaterial: "new",
        descripcion: m.descripcion || "No especificado",
      })),
    };
  
    try {
      setLoading(true);
      const response = await reportIncident(incidentData);
  
      if (response.success) {
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
      setDescription("");
      setPhoto(null);
      setWoundedList([]);
      setMaterialsList([]);

      if (latitude && longitude) {
        fetchLocationDetails(latitude, longitude);
      }
    }
  }, [open]);

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
                    photo={photo}
                    setPhoto={setPhoto}
                    wounded={woundedList}
                    setWounded={setWoundedList}
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
                      disabled={loading}
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
