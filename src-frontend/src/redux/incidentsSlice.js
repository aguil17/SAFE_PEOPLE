import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { fetchIncidents, reportIncident, deleteIncident } from "../services/incidentService";

// 🕐 Obtener la fecha actual y hace 24 horas en formato YYYY-MM-DD
const getDateRange = () => {
  const now = new Date();
  const past = new Date();
  past.setDate(now.getDate() - 1);
  const formatDate = (date) => date.toISOString().split("T")[0];
  return { startDate: formatDate(past), endDate: formatDate(now) };
};

// 🛠 AsyncThunk para obtener incidentes desde el backend
export const loadIncidents = createAsyncThunk("incidents/loadIncidents", async () => {
  const { startDate, endDate } = getDateRange();
  return await fetchIncidents(startDate, endDate);
});

// 🆕 AsyncThunk para reportar un incidente y agregarlo a la lista sin recargar todo
export const addIncident = createAsyncThunk(
  "incidents/addIncident",
  async (incidentData, { dispatch }) => {
    const response = await reportIncident(incidentData);
    if (response.success) {
      dispatch(addIncidentToState(response.data)); // 🆕 Agregar directamente a Redux sin recargar todo
    }
    return response;
  }
);

// 🗑 AsyncThunk para eliminar un incidente y actualizar Redux
export const removeIncidentAsync = createAsyncThunk(
  "incidents/removeIncidentAsync",
  async (incidentId, { dispatch }) => {
    const response = await deleteIncident(incidentId);
    if (response.success) {
      dispatch(removeIncident(incidentId)); // ✅ Remover del Redux state si el backend confirma
    }
    return response;
  }
);

const incidentsSlice = createSlice({
  name: "incidents",
  initialState: {
    list: [],
    loading: false,
    error: null,
  },
  reducers: {
    // 🆕 Acción síncrona para agregar un nuevo incidente al inicio
    addIncidentToState: (state, action) => {
      state.list.unshift(action.payload); // ✅ Agregar al inicio para mantener el orden descendente
    },
    // 🗑 Remover un incidente del Redux state
    removeIncident: (state, action) => {
      state.list = state.list.filter((incident) => incident.id !== action.payload);
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(loadIncidents.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(loadIncidents.fulfilled, (state, action) => {
        state.loading = false;
        state.list = action.payload.sort((a, b) => {
          // ✅ Ordenamos por fecha y hora en orden descendente (más recientes primero)
          return new Date(b.date + " " + b.time) - new Date(a.date + " " + a.time);
        });
      })
      .addCase(loadIncidents.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error.message;
      })
      .addCase(addIncident.pending, (state) => {
        state.loading = true;
      })
      .addCase(addIncident.fulfilled, (state) => {
        state.loading = false;
      })
      .addCase(addIncident.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error.message;
      })
      .addCase(removeIncidentAsync.pending, (state) => {
        state.loading = true;
      })
      .addCase(removeIncidentAsync.fulfilled, (state) => {
        state.loading = false;
      })
      .addCase(removeIncidentAsync.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error.message;
      });
  },
});

export const { removeIncident, addIncidentToState } = incidentsSlice.actions;
export default incidentsSlice.reducer;
