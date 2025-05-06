import PropTypes from "prop-types";
import {
  Box,
  Card,
  CardContent,
  CardMedia,
  Typography,
  Button,
  CircularProgress,
  Collapse,
} from "@mui/material";
import CalendarTodayIcon from '@mui/icons-material/CalendarToday';
import AccessTimeIcon from '@mui/icons-material/AccessTime';
import LocationOnIcon from '@mui/icons-material/LocationOn';
import DescriptionIcon from '@mui/icons-material/Description';
import LocalHospitalIcon from '@mui/icons-material/LocalHospital';
import HandymanIcon from '@mui/icons-material/Handyman';
import { incidentImages, incidentIcons } from "../../config/incidentConfig";
import "./styles.scss";

const getEstadoSaludLabel = (estado) => {
  const estados = {
    stable: "Estable",
    serious_stable: "Grave Estable",
    serious_unstable: "Grave Inestable",
    extremely_serious: "Extremadamente Grave",
  };
  return estados[estado] || estado;
};

const IncidentCard = ({ incident, expanded, onExpandClick, onDelete, deleting }) => {
  const isBase64 = incident.photo && incident.photo.startsWith("data:image");
  const imageUrl = isBase64
    ? incident.photo
    : incidentImages[incident.incidentType] || incidentImages.default;

  return (
    <Card className="report-list__card">
      <CardMedia
        component="img"
        image={imageUrl}
        alt={incident.incidentType}
        className={`report-list__image ${expanded ? 'report-list__image--expanded' : ''}`}
      />
      <CardContent className="report-list__content">
        <Typography variant="h6" className="report-list__title">
          <Box className={`icon icon--${incident.incidentType}`}>
            {incidentIcons[incident.incidentType] || <LocationOnIcon />}
          </Box>
          {incident.incidentType === "fire"
            ? "Incendio"
            : incident.incidentType === "robbery"
              ? "Robo"
              : incident.incidentType === "accident"
                ? "Accidente"
                : "Otro"}
        </Typography>

        <Box className="report-list__info">
          <Box className="report-list__info-item">
            <CalendarTodayIcon className="report-list__info-icon" />
            <Box>
              <Typography variant="caption" color="textSecondary">Fecha</Typography>
              <Typography variant="body2">{new Date(incident.date).toLocaleDateString()}</Typography>
            </Box>
          </Box>
          <Box className="report-list__info-item">
            <AccessTimeIcon className="report-list__info-icon" />
            <Box>
              <Typography variant="caption" color="textSecondary">Hora</Typography>
              <Typography variant="body2">{incident.time}</Typography>
            </Box>
          </Box>
          <Box 
            className={`report-list__info-item report-list__location ${!expanded ? 'report-list__location--truncate' : ''}`}
          >
            <LocationOnIcon className="report-list__info-icon" />
            <Box>
              <Typography variant="caption" color="textSecondary">Ubicación</Typography>
              <Typography variant="body2">{incident.cityName}, {incident.districtName}</Typography>
            </Box>
          </Box>
          <Box 
            className={`report-list__info-item report-list__description ${!expanded ? 'report-list__description--truncate' : ''}`}
          >
            <DescriptionIcon className="report-list__info-icon" />
            <Box>
              <Typography variant="caption" color="textSecondary">Descripción</Typography>
              <Typography variant="body2">{incident.descriptionIncident || "Sin descripción"}</Typography>
            </Box>
          </Box>
        </Box>

        <Box className="report-list__buttons">
          <Button
            variant="contained"
            size="small"
            onClick={() => onExpandClick(incident.id)}
            className="report-list__expand-button"
          >
            {expanded ? "Ver menos" : "Ver más"}
          </Button>
          <Button
            variant="contained"
            className="report-list__delete-button"
            onClick={() => onDelete(incident.id)}
            disabled={deleting === incident.id}
          >
            {deleting === incident.id ? (
              <CircularProgress size={24} color="inherit" />
            ) : (
              "Eliminar"
            )}
          </Button>
        </Box>

        <Collapse in={expanded} timeout="auto">
          <Box className="report-list__details">
            <Box className="report-list__details-section">
              <Typography variant="h6" className="report-list__details-title">
                <LocalHospitalIcon className="report-list__details-icon" />
                Heridos
              </Typography>
              {incident.heridos?.length > 0 ? (
                <Box className="report-list__details-content">
                  {incident.heridos.map((herido, index) => {
                    const nombreCompleto = [herido.name, herido.lastName].filter(Boolean).join(" ");
                    const detalles = [
                      nombreCompleto || "Sin nombre",
                      getEstadoSaludLabel(herido.healthStatus),
                      herido.typeEnjury !== "Desconocido" && herido.typeEnjury,
                      herido.age && herido.age !== 0 && `${herido.age} años`,
                      herido.descriptionEnjury !== "No especificado" && herido.descriptionEnjury
                    ].filter(Boolean).join(" - ");

                    return (
                      <Typography key={index} variant="body2">
                        {detalles}
                      </Typography>
                    );
                  })}
                </Box>
              ) : (
                <Typography variant="body2">No se registraron heridos.</Typography>
              )}
            </Box>

            <Box className="report-list__details-section">
              <Typography variant="h6" className="report-list__details-title">
                <HandymanIcon className="report-list__details-icon" />
                Materiales Dañados
              </Typography>
              {incident.materiales?.length > 0 ? (
                <Box className="report-list__details-content">
                  {incident.materiales.map((material, index) => {
                    if (material.quantity === 0) return null;
                    return (
                      <Typography key={index} variant="body2">
                        {material.materialType || "Material desconocido"} - {material.materialCondition || "Condición desconocida"}
                        {material.quantity > 0 && ` (Cantidad: ${material.quantity})`}
                      </Typography>
                    );
                  })}
                </Box>
              ) : (
                <Typography variant="body2">No se registraron materiales dañados.</Typography>
              )}
              {incident.materiales?.length > 0 && 
               incident.materiales.every(material => material.quantity === 0) && (
                <Typography variant="body2" sx={{ mt: 1 }}>
                  No se registraron daños materiales.
                </Typography>
              )}
            </Box>
          </Box>
        </Collapse>
      </CardContent>
    </Card>
  );
};

IncidentCard.propTypes = {
  incident: PropTypes.shape({
    id: PropTypes.number.isRequired,
    incidentType: PropTypes.string.isRequired,
    date: PropTypes.string.isRequired,
    time: PropTypes.string.isRequired,
    cityName: PropTypes.string.isRequired,
    districtName: PropTypes.string.isRequired,
    descriptionIncident: PropTypes.string,
    photo: PropTypes.string,
    heridos: PropTypes.array,
    materiales: PropTypes.array,
  }).isRequired,
  expanded: PropTypes.bool.isRequired,
  onExpandClick: PropTypes.func.isRequired,
  onDelete: PropTypes.func.isRequired,
  deleting: PropTypes.number,
};

export default IncidentCard;
