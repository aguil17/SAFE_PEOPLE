import { useEffect, useState } from "react";
import { Stepper, Step, StepLabel, StepContent, Button, Box, Modal } from "@mui/material";
import StepIncidentDetails from "./StepIncidentDetails";
import StepLocation from "./StepLocation";
import StepAdditionalInfo from "./StepAdditionalInfo";
import "./IncidentStepper.scss";

const steps = [
  { label: "Detalles del Incidente", component: StepIncidentDetails },
  { label: "Ubicación", component: StepLocation },
  { label: "Información Adicional", component: StepAdditionalInfo }
];

const IncidentStepper = ({ open, onClose, onSubmit, incidentType, markerPosition }) => {
  const [activeStep, setActiveStep] = useState(0);
  const [description, setDescription] = useState("");
  const [photo, setPhoto] = useState(null);
  const [city, setCity] = useState("");
  const [district, setDistrict] = useState("");
  const [informant, setInformant] = useState({ name: "", lastName: "", cellphone: "", email: "" });
  const [wounded, setWounded] = useState({ quantity: "", name: "", lastName: "" });
  const [materials, setMaterials] = useState({ type: "", description: "", quantity: "" });

  const latitude = markerPosition?.[0] || ""; // Extraer latitud
  const longitude = markerPosition?.[1] || ""; // Extraer longitud

  // 🔹 Función para obtener la ciudad y distrito por coordenadas
  const fetchLocationDetails = async (lat, lng) => {
    try {
      const response = await fetch(
        `https://nominatim.openstreetmap.org/reverse?format=json&lat=${lat}&lon=${lng}`
      );
      const data = await response.json();

      if (data.address) {
        setCity(data.address.city || data.address.town || data.address.village || "No especificado");
        setDistrict(data.address.suburb || "No especificado");
      }
    } catch (error) {
      console.error("Error obteniendo la ubicación:", error);
    }
  };

  // 🔹 Obtener ciudad automáticamente cuando cambia la ubicación
  useEffect(() => {
    if (latitude && longitude) {
      fetchLocationDetails(latitude, longitude);
    }
  }, [latitude, longitude]);

  const handleFinish = () => {
    const incidentData = {
      type: incidentType, // Asegurar que se envíe el tipo de incidente
      description,
      photo,
      city,
      district,
      latitude,
      longitude,
      informant,
      wounded,
      materials
    };

    console.log("Datos enviados al finalizar:", incidentData); // 🔹 Para verificar en consola
    onSubmit(incidentData); // Enviar los datos al MapComponent
    onClose(); // Cerrar el modal

  };

  useEffect(() => {
    if (open) {
      setActiveStep(0);
      setDescription("");
      setPhoto(null);
      setInformant({ name: "", lastName: "", cellphone: "", email: "" });
      setWounded({ quantity: "", name: "", lastName: "" });
      setMaterials({ type: "", description: "", quantity: "" });
  
      // 🔹 Volver a obtener la ciudad y distrito
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
          maxHeight: "90vh",  // 🔹 Altura máxima del modal
          overflow: "auto",   // 🔹 Habilita el scroll si hay demasiados elementos
          p: 3
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
                    city={city}
                    setCity={setCity}
                    district={district}
                    setDistrict={setDistrict}
                    informant={informant}
                    setInformant={setInformant}
                    wounded={wounded}
                    setWounded={setWounded}
                    materials={materials}
                    setMaterials={setMaterials}
                    incidentType={incidentType}
                    latitude={latitude}
                    longitude={longitude}
                  />
                  <Box className="incident-stepper__buttons">
                    {index > 0 && <Button onClick={() => setActiveStep(index - 1)}>Atrás</Button>}
                    <Button onClick={() => (index === steps.length - 1 ? handleFinish() : setActiveStep(index + 1))}>
                      {index === steps.length - 1 ? "Finalizar" : "Siguiente"}
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
