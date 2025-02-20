import { createSlice } from "@reduxjs/toolkit";

const storedUser = localStorage.getItem("user");
const initialState = {
  user: storedUser ? JSON.parse(storedUser) : null, // Si hay usuario en localStorage, lo carga
};

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    setUser: (state, action) => {
      state.user = action.payload;
      localStorage.setItem("user", JSON.stringify(action.payload)); // 🔹 Guardar en localStorage
    },
    logout: (state) => {
      state.user = null;
      localStorage.removeItem("user"); // 🔹 Borrar del localStorage al cerrar sesión
    },
  },
});

export const { setUser, logout } = authSlice.actions;
export default authSlice.reducer;
