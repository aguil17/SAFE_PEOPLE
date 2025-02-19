import { useState, useEffect } from "react";
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
  const [markerPosition, setMarkerPosition] = useState([4.711, -74.0721]);
  const [userLocation, setUserLocation] = useState(null);
  
  const typeMapping = {
    fire: "fire",
    robbery: "robbery",
    accident: "accident",
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

    const validType = typeMapping[incident.tipoIncidente] || "default";

    const newIncident = {
      ...incident,
      location: [incident.ubicacion.latitud, incident.ubicacion.longitud], // 🔹 Asegurar coordenadas
      type: validType,
    };

    setMarkerPosition(userLocation || [4.711, -74.0721]); // 🔹 Resetear marcador a la ubicación inicial
    setFormOpen(false); // 🔹 Cerrar formulario después de reportar
  };

  return (
    <div className="map-container">
      <MapContainer center={markerPosition} zoom={13} className="map">
        <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />

        {/* 📌 Centra el mapa en la ubicación del usuario */}
        {userLocation && <MapCenter position={userLocation} />}

        {/* 📌 Pintar incidentes desde Redux */}
        {incidents.map((incident) => (
          <Marker
            key={incident.id}
            position={[incident.latitude, incident.longitude]}
            icon={icons[incident.incidentType] || icons.default}
          >
            <Popup>
              <strong>{incident.incidentType}</strong><br />
              {incident.descriptionIncident}<br />
              <small>{incident.cityName}, {incident.districtName}</small>
            </Popup>
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
                event.target.getLatLng().lng,
              ]);
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
