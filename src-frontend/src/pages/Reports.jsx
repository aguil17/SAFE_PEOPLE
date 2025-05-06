import { useSelector, useDispatch } from "react-redux";
import { Box, Typography, CircularProgress } from "@mui/material";
import { loadIncidents } from "../redux/incidentsSlice";
import ReportList from "../components/Report/ReportList";
import ReportsHeader from "../components/Reports/ReportsHeader";
import { useIncidentDeletion } from "../hooks/useIncidentDeletion";
import { useEffect } from "react";
import "./Reports.scss";

const Reports = () => {
  const dispatch = useDispatch();
  const user = useSelector((state) => state.auth.user);
  const incidents = useSelector((state) => state.incidents.list);
  const loading = useSelector((state) => state.incidents.loading);
  const { deleting, handleDelete } = useIncidentDeletion();

  console.log("✅ Incidentes en Redux:", incidents);

  useEffect(() => {
    if (incidents.length === 0) {
      dispatch(loadIncidents());
    }
  }, [dispatch, incidents.length]);

  const userIncidents = incidents.filter(
    (incident) => incident.incidenteInformantes[0].email === user?.usuario?.username
  );

  const renderContent = () => {
    if (loading) {
      return (
        <Box className="reports__loading">
          <CircularProgress />
        </Box>
      );
    }

    if (userIncidents.length === 0) {
      return (
        <Typography variant="h6" className="reports__empty-message">
          No tienes reportes registrados.
        </Typography>
      );
    }

    return (
      <ReportList 
        incidents={userIncidents} 
        onDelete={handleDelete} 
        deleting={deleting} 
      />
    );
  };

  return (
    <Box className="reports">
      <ReportsHeader 
        title="Mis Reportes"
        subtitle="Gestiona y monitorea todos tus reportes de incidentes"
      />
      {renderContent()}
    </Box>
  );
};

export default Reports;
