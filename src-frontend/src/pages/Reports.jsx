import React from "react";
import { useSelector, useDispatch } from "react-redux";
import { Box, Typography } from "@mui/material";
import { deleteIncident } from "../services/incidentService";
import { removeIncident } from "../redux/incidentsSlice";
import ReportList from "../components/Report/ReportList";

const Reports = () => {
  const dispatch = useDispatch();
  // Obtener usuario autenticado
  const user = useSelector((state) => state.auth.user);
  // Obtener todos los reportes desde Redux
  const incidents = useSelector((state) => state.incidents.list);

  // Filtrar solo los reportes del usuario autenticado
  const userIncidents = incidents.filter((incident) => 
    incident.incidenteInformantes[0].email === user?.usuario?.username
  );

  // 📌 Función para eliminar un reporte
  const handleDelete = async (incidentId) => {
    const confirmDelete = window.confirm("¿Estás seguro de eliminar este reporte?");
    if (!confirmDelete) return;

    const response = await deleteIncident(incidentId);
    if (response.success) {
      alert("Reporte eliminado exitosamente.");
      dispatch(removeIncident(incidentId)); // 🔹 Actualizar Redux
    } else {
      alert(`Error eliminando el reporte: ${response.message}`);
    }
  };

  return (
    <Box className="reports">
      <Typography variant="h4" sx={{ mb: 2 }}>
        📋 Mis Reportes
      </Typography>

      {/* Si el usuario no tiene reportes, mostramos un mensaje */}
      {userIncidents.length === 0 ? (
        <Typography variant="h6" color="textSecondary">
          No tienes reportes registrados.
        </Typography>
      ) : (
        <ReportList incidents={userIncidents} onDelete={handleDelete} />
      )}
    </Box>
  );
};

export default Reports;
