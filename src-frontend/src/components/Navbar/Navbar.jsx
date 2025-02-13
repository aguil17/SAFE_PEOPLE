import {
  AppBar,
  Toolbar,
  Typography,
  Button,
  Box,
  IconButton,
  Drawer,
  List,
  ListItem,
  ListItemText,
} from "@mui/material";
import { Link } from "react-router-dom";
import MenuIcon from "@mui/icons-material/Menu";
import { useState } from "react";
import logo from "../../assets/LogoSafe.png";
import "./Navbar.scss";

const menuItems = [
  { id: "home", text: "🏠 Inicio", path: "/" },
  { id: "reports", text: "📜 Reportes", path: "/reports" },
  { id: "statistics", text: "📊 Estadísticas", path: "/statistics" },
  { id: "contacts", text: "🚨 Emergencia", path: "/contacts" },
  { id: "profile", text: "👤 Perfil", path: "/profile" },
];

const Navbar = () => {
  const [mobileOpen, setMobileOpen] = useState(false);

  const handleDrawerToggle = () => {
    setMobileOpen(!mobileOpen);
  };

  return (
    <AppBar position="static" className="navbar">
      <Toolbar className="navbar__toolbar">
        {/* Logo y título */}
        <Box className="navbar__logo-container">
          <img src={logo} alt="SafePeople Logo" className="navbar__logo" />
          <Typography id="navbar__logo-text" variant="h6">
            SafePeople
          </Typography>
        </Box>

        {/* Menú para pantallas grandes */}
        <Box className="navbar__buttons">
          {menuItems.map(({ id, text, path }) => (
            <Button
              key={id}
              id={`navbar__button--${id}`}
              component={Link}
              to={path}
              className="navbar__button"
            >
              {text}
            </Button>
          ))}
        </Box>

        {/* Botón de menú hamburguesa en móviles */}
        <IconButton
          className="navbar__menu-icon"
          edge="end"
          color="inherit"
          onClick={handleDrawerToggle}
        >
          <MenuIcon />
        </IconButton>

        {/* Menú lateral en móviles */}
        <Drawer anchor="right" open={mobileOpen} onClose={handleDrawerToggle}>
          <List className="navbar__drawer">
            {menuItems.map(({ id, text, path }) => (
              <ListItem button key={id} component={Link} to={path} onClick={handleDrawerToggle}>
                <ListItemText primary={text} sx={{ color: "white" }} />{" "}
                {/* 🔹 Aplica el color blanco */}
              </ListItem>
            ))}
          </List>
        </Drawer>
      </Toolbar>
    </AppBar>
  );
};

export default Navbar;
