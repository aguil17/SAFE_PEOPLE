import { useState, useEffect } from "react";
import { MapContainer, TileLayer, Marker, Popup, useMap } from "react-leaflet";
import { Icon } from "leaflet";
import ReportButton from "./ReportButton";
import IncidentForm from "../Report/IncidentForm";
import "leaflet/dist/leaflet.css";
import "./MapComponent.scss";

// 🔹 Icono personalizado para los marcadores
const customIcon = new Icon({
  iconUrl: "/marker-icon.png", // Asegúrate de tener este ícono en assets
  iconSize: [32, 32]
});

const MapComponent = () => {
  const [selectedIncident, setSelectedIncident] = useState(null);
  const [formOpen, setFormOpen] = useState(false);
  const [incidents, setIncidents] = useState([]);
  const [markerPosition, setMarkerPosition] = useState([4.711, -74.0721]); // 🔹 Ubicación inicial (Bogotá)
  const [userLocation, setUserLocation] = useState(null); // 📌 Ubicación del usuario

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
    setIncidents([...incidents, { ...incident, location: markerPosition }]);
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
          <Marker key={index} position={incident.location} icon={customIcon}>
            <Popup>{incident.type}: {incident.description}</Popup>
          </Marker>
        ))}

        {/* 📌 Marcador arrastrable */}
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
        >
          <Popup>{userLocation ? "Ubicación seleccionada" : "Mueve este marcador"}</Popup>
        </Marker>
      </MapContainer>

      {/* 📌 Botón de reporte */}
      <ReportButton
        onSelectIncidentType={(type) => {
          setSelectedIncident(type);
          setFormOpen(true);
        }}
      />

      {/* 📌 Formulario flotante */}
      <IncidentForm
        open={formOpen}
        onClose={() => setFormOpen(false)}
        onSubmit={handleReport}
        incidentType={selectedIncident}
      />
    </div>
  );
};

export default MapComponent;
