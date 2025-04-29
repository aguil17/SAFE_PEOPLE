import { useState } from 'react';
import { useDispatch } from 'react-redux';
import { removeIncidentAsync } from '../redux/incidentsSlice';

export const useIncidentDeletion = () => {
  const dispatch = useDispatch();
  const [deleting, setDeleting] = useState(null);

  const handleDelete = async (incidentId) => {
    try {
      const confirmDelete = window.confirm("¿Estás seguro de eliminar este reporte?");
      if (!confirmDelete) return;

      setDeleting(incidentId);
      const response = await dispatch(removeIncidentAsync(incidentId));
      
      if (!response.payload.success) {
        throw new Error(response.payload.message);
      }
    } catch (error) {
      alert(`Error al eliminar el reporte: ${error.message}`);
    } finally {
      setDeleting(null);
    }
  };

  return {
    deleting,
    handleDelete
  };
};
