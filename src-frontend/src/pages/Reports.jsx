import { useSelector, useDispatch } from "react-redux";
import { Box, Typography, CircularProgress } from "@mui/material";
import { loadIncidents, removeIncidentAsync } from "../redux/incidentsSlice";
import ReportList from "../components/Report/ReportList";
import { useEffect, useState } from "react";
import "./Reports.scss"; // 👈 Importamos el nuevo SCSS

const Reports = () => {
  const dispatch = useDispatch();
  const user = useSelector((state) => state.auth.user);
  const incidents = useSelector((state) => state.incidents.list);
  const loading = useSelector((state) => state.incidents.loading);

  console.log("✅ Incidentes en Redux:", incidents);

  // Estado para manejar la eliminación individual de reportes
  const [deleting, setDeleting] = useState(null);

  useEffect(() => {
    if (incidents.length === 0) {
      dispatch(loadIncidents());
    }
  }, [dispatch, incidents.length]);

  // Filtrar reportes del usuario autenticado
  const userIncidents = incidents.filter(
    (incident) => incident.incidenteInformantes[0].email === user?.usuario?.username
  );

  // 📌 Manejar la eliminación de incidentes
  const handleDelete = async (incidentId) => {
    const confirmDelete = window.confirm("¿Estás seguro de eliminar este reporte?");
    if (!confirmDelete) return;

    setDeleting(incidentId); // Mostrar loader en el botón específico
    const response = await dispatch(removeIncidentAsync(incidentId));
    setDeleting(null); // Ocultar loader tras la eliminación

    if (!response.payload.success) {
      alert(`Error eliminando el reporte: ${response.payload.message}`);
    }
  };

  return (
    <Box className="reports">
      <Box className="reports__header">
        <Typography variant="h4" className="reports__title">
          📋 Mis Reportes
        </Typography>
      </Box>

      {loading ? (
        <Box display="flex" justifyContent="center" mt={3}>
          <CircularProgress />
        </Box>
      ) : userIncidents.length === 0 ? (
        <Typography variant="h6" color="textSecondary">
          No tienes reportes registrados.
        </Typography>
      ) : (
        <ReportList incidents={userIncidents} onDelete={handleDelete} deleting={deleting} />
      )}
    </Box>
  );
};

export default Reports;
