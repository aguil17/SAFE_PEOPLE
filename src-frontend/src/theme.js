import { createTheme } from "@mui/material/styles";

const theme = createTheme({
  palette: {
    primary: {
      main: "#041122", // Azul oscuro (Navbar, fondo principal)
      contrastText: "#ffffff", // Texto en botones y Navbar
    },
    secondary: {
      main: "#259073", // Verde oscuro (Botones principales)
    },
    success: {
      main: "#7FDA89", // Verde medio (Hover de botones, detalles)
    },
    warning: {
      main: "#E6F99D", // Amarillo claro (Notificaciones, alertas)
    },
    background: {
      default: "#F9FAFB", // Verde claro (Fondo de la app)
      paper: "#ffffff", // Fondo de tarjetas y modales
    },
  },
  typography: {
    fontFamily: "'Roboto', sans-serif",
  },
});

export default theme;
