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
  ListItemIcon,
  Container,
} from "@mui/material";
import { Link, useLocation } from "react-router-dom";
import MenuIcon from "@mui/icons-material/Menu";
import HomeIcon from "@mui/icons-material/Home";
import DescriptionIcon from "@mui/icons-material/Description";
import PersonIcon from "@mui/icons-material/Person";
import { useState } from "react";
import logo from "../../assets/LogoSafe.png";
import "./Navbar.scss";

const menuItems = [
  { id: "home", text: "Inicio", path: "/", icon: <HomeIcon /> },
  { id: "reports", text: "Reportes", path: "/reports", icon: <DescriptionIcon /> },
  { id: "profile", text: "Perfil", path: "/profile", icon: <PersonIcon /> },
];

const Navbar = () => {
  const [mobileOpen, setMobileOpen] = useState(false);
  const location = useLocation();

  const handleDrawerToggle = () => {
    setMobileOpen(!mobileOpen);
  };

  const isActive = (path) => {
    return location.pathname === path;
  };

  return (
    <AppBar position="fixed" className="navbar">
      <Container maxWidth="xl">
        <Toolbar className="navbar__toolbar" disableGutters>
          <Box className="navbar__logo-container" component={Link} to="/">
            <img src={logo} alt="SafePeople Logo" className="navbar__logo" />
            <Typography variant="h6" className="navbar__logo-text">
              SafePeople
            </Typography>
          </Box>

          <Box className="navbar__buttons">
            {menuItems.map(({ id, text, path, icon }) => (
              <Button
                key={id}
                component={Link}
                to={path}
                className={`navbar__button ${isActive(path) ? 'navbar__button--active' : ''}`}
                startIcon={icon}
              >
                {text}
              </Button>
            ))}
          </Box>

          <Box className="navbar__mobile-menu">
            <IconButton
              className="navbar__menu-icon"
              edge="end"
              color="inherit"
              onClick={handleDrawerToggle}
              aria-label="menu"
            >
              <MenuIcon />
            </IconButton>
          </Box>
        </Toolbar>
      </Container>

      <Drawer
        anchor="right"
        open={mobileOpen}
        onClose={handleDrawerToggle}
        classes={{
          paper: 'navbar__drawer'
        }}
      >
        <List>
          {menuItems.map(({ id, text, path, icon }) => (
            <ListItem
              button
              key={id}
              component={Link}
              to={path}
              onClick={handleDrawerToggle}
              className={isActive(path) ? 'navbar__drawer-item--active' : ''}
            >
              <ListItemIcon className="navbar__drawer-icon">
                {icon}
              </ListItemIcon>
              <ListItemText primary={text} />
            </ListItem>
          ))}
        </List>
      </Drawer>
    </AppBar>
  );
};

export default Navbar;
