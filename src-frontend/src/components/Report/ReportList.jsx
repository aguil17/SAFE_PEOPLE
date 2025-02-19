import PropTypes from "prop-types";
import { Box, Card, CardContent, CardMedia, Typography, Button, CircularProgress } from "@mui/material";
import fireIcon from "../../assets/icons/point_fire.png";
import crashIcon from "../../assets/icons/point_crash.png";
import thiefIcon from "../../assets/icons/point_thief.png";
import aloneIcon from "../../assets/icons/point_alone.png";
import "./ReportList.scss";

// 📌 Mapeo de imágenes de incidentes
const incidentImages = {
  fire: fireIcon,
  robbery: thiefIcon,
  accident: crashIcon,
  default: aloneIcon,
};

const ReportList = ({ incidents, onDelete, deleting }) => {
  return (
    <Box className="report-list">
      {incidents.map((incident) => {
        // 📌 Verificamos si `photo` es una imagen en Base64 válida
        const isBase64 = incident.photo && incident.photo.startsWith("data:image");
        const imageUrl = isBase64
          ? incident.photo
          : incidentImages[incident.incidentType] || incidentImages.default;

        return (
          <Card key={incident.id} className="report-list__card">
            <CardMedia component="img" height="140" image={imageUrl} alt={incident.incidentType} className="report-list__image" />
            <CardContent>
              <Typography variant="h6" className="report-list__title">
                {incident.incidentType === "fire"
                  ? "🔥 Incendio"
                  : incident.incidentType === "robbery"
                  ? "🦹‍♂️ Robo"
                  : incident.incidentType === "accident"
                  ? "🚗 Accidente"
                  : "📍 Otro"}
              </Typography>
              <Typography variant="body2">
                <strong>📅 Fecha:</strong> {new Date(incident.date).toLocaleDateString()}
              </Typography>
              <Typography variant="body2">
                <strong>⏰ Hora:</strong> {incident.time}
              </Typography>
              <Typography variant="body2">
                <strong>📍 Ubicación:</strong> {incident.cityName}, {incident.districtName}
              </Typography>
              <Typography variant="body2">
                <strong>📝 Descripción:</strong> {incident.descriptionIncident || "Sin descripción"}
              </Typography>
              <Button
                variant="contained"
                color="error"
                className="report-list__delete-button"
                onClick={() => onDelete(incident.id)}
                disabled={deleting === incident.id}
              >
                {deleting === incident.id ? <CircularProgress size={24} color="inherit" /> : "🗑 Eliminar"}
              </Button>
            </CardContent>
          </Card>
        );
      })}
    </Box>
  );
};

ReportList.propTypes = {
  incidents: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.number.isRequired,
      incidentType: PropTypes.string.isRequired,
      date: PropTypes.string.isRequired,
      time: PropTypes.string.isRequired,
      cityName: PropTypes.string.isRequired,
      districtName: PropTypes.string.isRequired,
      descriptionIncident: PropTypes.string,
      photo: PropTypes.string,
    })
  ).isRequired,
  onDelete: PropTypes.func.isRequired,
  deleting: PropTypes.number, // Nuevo prop para mostrar el loader
};

export default ReportList;
