import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { fetchIncidents, reportIncident } from "../services/incidentService";

// 🕐 Obtener la fecha actual y hace 24 horas en formato YYYY-MM-DD
const getDateRange = () => {
  const now = new Date();
  const past = new Date();
  past.setDate(now.getDate() - 1);
  const formatDate = (date) => date.toISOString().split("T")[0];
  return { startDate: formatDate(past), endDate: formatDate(now) };
};

// 🛠 AsyncThunk para obtener incidentes desde el backend
export const loadIncidents = createAsyncThunk(
  "incidents/loadIncidents",
  async () => {
    const { startDate, endDate } = getDateRange();
    return await fetchIncidents(startDate, endDate);
  }
);

// 🆕 AsyncThunk para reportar un incidente y actualizar la lista
export const addIncident = createAsyncThunk(
  "incidents/addIncident",
  async (incidentData, { dispatch }) => {
    const response = await reportIncident(incidentData);
    if (response.success) {
      await dispatch(loadIncidents()); // 🔹 Recargar la lista de incidentes
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
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(loadIncidents.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(loadIncidents.fulfilled, (state, action) => {
        state.loading = false;
        state.list = action.payload;
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
      });
  },
});

export default incidentsSlice.reducer;
