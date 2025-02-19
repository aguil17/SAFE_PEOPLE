import { useState, useEffect, useRef } from "react";
import { useDispatch, useSelector } from "react-redux";
import { MapContainer, TileLayer, Marker, Popup, useMap } from "react-leaflet";
import { Icon } from "leaflet";
import ReportButton from "./ReportButton";
import IncidentStepper from "../Report/IncidentStepper";
import { loadIncidents } from "../../redux/incidentsSlice";
import "leaflet/dist/leaflet.css";
import "./MapComponent.scss";
import fireIcon from "../../assets/icons/point_fire.png";
import crashIcon from "../../assets/icons/point_crash.png";
import thiefIcon from "../../assets/icons/point_thief.png";
import aloneIcon from "../../assets/icons/point_alone.png";

// 🔹 Íconos personalizados
const icons = {
  accident: new Icon({
    iconUrl: crashIcon,
    iconSize: [40],
  }),
  fire: new Icon({
    iconUrl: fireIcon,
    iconSize: [40],
  }),
  robbery: new Icon({
    iconUrl: thiefIcon,
    iconSize: [40],
  }),
  default: new Icon({
    iconUrl: aloneIcon,
    iconSize: [55],
  }),
};

const MapComponent = () => {
  const dispatch = useDispatch();
  const incidents = useSelector((state) => state.incidents?.list || []);
  const [selectedIncident, setSelectedIncident] = useState(null);
  const [formOpen, setFormOpen] = useState(false);
  const [markerPosition, setMarkerPosition] = useState([4.711, -74.0721]); // Posición inicial
  const [userLocation, setUserLocation] = useState(null);
  const mapRef = useRef(null); // Referencia al mapa para centrarlo solo cuando sea necesario

  // eslint-disable-next-line react/prop-types
  const MapCenter = ({ position }) => {
    const map = useMap();
    useEffect(() => {
      if (position && !mapRef.current) {
        map.setView(position, 15);
        mapRef.current = map; // Guardar referencia al mapa para evitar re-centrado innecesario
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

  // 📌 Cargar incidentes al montar el componente
  useEffect(() => {
    dispatch(loadIncidents());
  }, [dispatch]);

  // 📌 Manejar el reporte de incidentes
  const handleReport = (incident) => {
    if (!incident || !incident.ubicacion) {
      console.error("❌ Error: El incidente no tiene ubicación definida.");
      return;
    }
    setMarkerPosition(userLocation || [4.711, -74.0721]); // 🔹 Resetear marcador a la ubicación inicial
    setFormOpen(false); // 🔹 Cerrar formulario después de reportar
  };

  return (
    <div className="map-container">
      <MapContainer center={markerPosition} zoom={13} className="map">
        <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />

        {/* 📌 Centrar el mapa en la ubicación del usuario SOLO la primera vez */}
        {userLocation && <MapCenter position={userLocation} />}

        {/* 📌 Pintar incidentes desde Redux */}
        {incidents.map((incident) => (
          <Marker
            key={incident.id}
            position={[incident.latitude, incident.longitude]}
            icon={icons[incident.incidentType] || icons.default}
          >
            <Popup>
              <strong>
                {incident.incidentType === "fire"
                  ? "Incendio"
                  : incident.incidentType === "robbery"
                    ? "Robo"
                    : incident.incidentType === "accident"
                      ? "Accidente"
                      : "Otro"}
              </strong>
              <br />
              {new Date(incident.creationDate).toLocaleDateString("es-ES", {
                weekday: "long",
                year: "numeric",
                month: "long",
                day: "numeric",
              })}{" "}
              -
              {new Date(incident.creationDate).toLocaleTimeString("es-ES", {
                hour: "2-digit",
                minute: "2-digit",
                second: "2-digit",
                hour12: true,
              })}
              <br />
              <small>
                {incident.cityName}, {incident.districtName}
              </small>
            </Popup>
          </Marker>
        ))}

        {/* 📌 Marcador arrastrable */}
        <Marker
          position={markerPosition}
          draggable={true}
          eventHandlers={{
            dragend: (event) => {
              const newPos = [event.target.getLatLng().lat, event.target.getLatLng().lng];
              setMarkerPosition(newPos);

              // 📌 Centrar el mapa en la nueva posición del marcador
              if (mapRef.current) {
                mapRef.current.setView(newPos, 15);
              }
            },
          }}
          icon={icons.default}
        >
          <Popup>Ubicación seleccionada</Popup>
        </Marker>
      </MapContainer>

      {/* 📌 Botón de reporte */}
      <ReportButton
        onSelectIncidentType={(type) => {
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
