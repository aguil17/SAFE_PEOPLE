import { useState, useEffect } from "react";
import { MapContainer, TileLayer, Marker, Popup, useMap } from "react-leaflet";
import { Icon } from "leaflet";
import ReportButton from "./ReportButton";
import IncidentStepper from "../Report/IncidentStepper"; // 🔹 Nuevo Stepper
import "leaflet/dist/leaflet.css";
import "./MapComponent.scss";
import fireIcon from "../../assets/icons/point_fire.png";
import crashIcon from "../../assets/icons/point_crash.png";
import thiefIcon from "../../assets/icons/point_thief.png";
import aloneIcon from "../../assets/icons/point_alone.png";

// 🔹 Íconos personalizados para los marcadores
const icons = {
  accident: new Icon({
    iconUrl: crashIcon,
    iconSize: [40]
  }),
  fire: new Icon({
    iconUrl: fireIcon,
    iconSize: [40]
  }),
  robbery: new Icon({
    iconUrl: thiefIcon,
    iconSize: [40]
  }),
  default: new Icon({
    iconUrl: aloneIcon,
    iconSize: [50]
  })
};

const MapComponent = () => {
  const [selectedIncident, setSelectedIncident] = useState(null);
  const [formOpen, setFormOpen] = useState(false);
  const [incidents, setIncidents] = useState([]);
  const [markerPosition, setMarkerPosition] = useState([4.711, -74.0721]); // 🔹 Ubicación inicial (Bogotá)
  const [userLocation, setUserLocation] = useState(null);

  const typeMapping = {
    Incendio: "fire",
    Robo: "robbery",
    Accidente: "accident"
  };

  // 📌 Hook para centrar el mapa en la ubicación del usuario
  const MapCenter = ({ position }) => {
    const map = useMap();
    useEffect(() => {
      if (position) {
        map.setView(position, 15);
      }
    }, [position, map]);
    return null;
  };

  // 📌 Obtener la ubicación del usuario al cargar el componente
  useEffect(() => {
    if ("geolocation" in navigator) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const { latitude, longitude } = position.coords;
          const newPosition = [latitude, longitude];
          setMarkerPosition(newPosition);
          setUserLocation(newPosition);
        },
        (error) => {
          console.error("Error obteniendo la ubicación:", error);
        }
      );
    }
  }, []);

  // 📌 Manejar el reporte de incidentes
  const handleReport = (incident) => {
    const validType = typeMapping[incident.type] || "default";

    setIncidents((prevIncidents) => {
      const updatedIncidents = [
        ...prevIncidents,
        {
          ...incident,
          location: markerPosition,
          type: validType, // 🔹 Asegura que solo use los tipos permitidos
        }
      ];

      return updatedIncidents;
    });

    setMarkerPosition(userLocation || [4.711, -74.0721]); // 🔹 Reinicia la posición
    setFormOpen(false); // 🔹 Cierra el formulario después de reportar
  };

  return (
    <div className="map-container">
      <MapContainer center={markerPosition} zoom={13} className="map">
        <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />

        {/* 📌 Centra el mapa en la ubicación del usuario */}
        {userLocation && <MapCenter position={userLocation} />}

        {/* 📌 Muestra todos los incidentes reportados */}
        {incidents.map((incident, index) => (
          <Marker
            key={index}
            position={incident.location}
            icon={icons[incident.type] || icons.default}
          >
            <Popup>{incident.type}: {incident.description}</Popup>
          </Marker>
        ))}

        {/* 📌 Marcador arrastrable antes de reportar */}
        <Marker
          position={markerPosition}
          draggable={true}
          eventHandlers={{
            dragend: (event) => {
              setMarkerPosition([
                event.target.getLatLng().lat,
                event.target.getLatLng().lng
              ]);
            }
          }}
          icon={icons.default} // 🔹 Ícono de selección antes de reportar
        >
          <Popup>{userLocation ? "Ubicación seleccionada" : "Mueve este marcador"}</Popup>
        </Marker>
      </MapContainer>

      {/* 📌 Botón de reporte */}
      <ReportButton
        onSelectIncidentType={(type) => {
          console.log("Botón de reporte presionado, tipo de incidente:", type);
          setSelectedIncident(type);
          setFormOpen(true);
        }}
      />

      {/* 📌 Stepper para reportar incidentes */}
      <IncidentStepper
        open={formOpen}
        onClose={() => setFormOpen(false)}
        onSubmit={handleReport}
        incidentType={selectedIncident}
        markerPosition={markerPosition}
      />
    </div>
  );
};

export default MapComponent;
