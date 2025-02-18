import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  incidentImage: "",
};

const reportSlice = createSlice({
  name: "report",
  initialState,
  reducers: {
    setIncidentImage: (state, action) => {
      state.incidentImage = action.payload;
    },
  },
});

export const { setIncidentImage } = reportSlice.actions;
export default reportSlice.reducer;