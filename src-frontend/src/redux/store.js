import { configureStore } from "@reduxjs/toolkit";
import authReducer from "./authSlice";
import incidentsReducer from "./incidentsSlice";

export const store = configureStore({
  reducer: {
    auth: authReducer,
    incidents: incidentsReducer,
  },
  devTools: process.env.NODE_ENV !== "production", // Habilita Redux DevTools solo en desarrollo
});

export default store;
