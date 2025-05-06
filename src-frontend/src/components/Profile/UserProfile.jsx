import PropTypes from "prop-types";
import { useDispatch } from "react-redux";
import { logout } from "../../redux/authSlice";
import { useNavigate } from "react-router-dom";
import { 
  Typography, 
  Paper, 
  Button, 
  Avatar, 
  Box, 
  Divider,
  Grid,
  IconButton,
} from "@mui/material";
import LogoutIcon from '@mui/icons-material/Logout';
import PersonIcon from '@mui/icons-material/Person';
import EmailIcon from '@mui/icons-material/Email';
import PhoneIcon from '@mui/icons-material/Phone';
import CakeIcon from '@mui/icons-material/Cake';
import BadgeIcon from '@mui/icons-material/Badge';
import AdminPanelSettingsIcon from '@mui/icons-material/AdminPanelSettings';
import "./UserProfile.scss";

const UserProfile = ({ user }) => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleLogout = () => {
    dispatch(logout());
    localStorage.removeItem("user");
    navigate("/");
  };

  if (!user) {
    return <Typography variant="h5">No hay usuario autenticado</Typography>;
  }

  const getInitials = (name, lastName) => {
    return `${name?.charAt(0) || ''}${lastName?.charAt(0) || ''}`.toUpperCase();
  };

  return (
    <Paper className="user-profile" elevation={3}>
      <Box className="user-profile__header">
        <Typography variant="h4" className="user-profile__title">
          Perfil de Usuario
        </Typography>
        <IconButton 
          onClick={handleLogout}
          className="user-profile__logout-icon"
          title="Cerrar Sesión"
        >
          <LogoutIcon />
        </IconButton>
      </Box>

      <Box className="user-profile__avatar-section">
        <Avatar className="user-profile__avatar">
          {getInitials(user.persona.name, user.persona.lastName)}
        </Avatar>
        <Typography variant="h5" className="user-profile__name">
          {user.persona.name} {user.persona.lastName}
        </Typography>
        <Typography variant="subtitle1" className="user-profile__role">
          {user.usuario.role === 'user' ? 'Usuario' : 'Administrador'}
        </Typography>
      </Box>

      <Divider className="user-profile__divider" />

      <Grid container spacing={3} className="user-profile__info">
        <Grid item xs={12} sm={6}>
          <Box className="user-profile__info-item">
            <EmailIcon className="user-profile__info-icon" />
            <Box>
              <Typography variant="caption" color="textSecondary">
                Correo Electrónico
              </Typography>
              <Typography variant="body1">
                {user.persona.email}
              </Typography>
            </Box>
          </Box>
        </Grid>

        <Grid item xs={12} sm={6}>
          <Box className="user-profile__info-item">
            <PhoneIcon className="user-profile__info-icon" />
            <Box>
              <Typography variant="caption" color="textSecondary">
                Teléfono
              </Typography>
              <Typography variant="body1">
                {user.persona.cellphone}
              </Typography>
            </Box>
          </Box>
        </Grid>

        <Grid item xs={12} sm={6}>
          <Box className="user-profile__info-item">
            <CakeIcon className="user-profile__info-icon" />
            <Box>
              <Typography variant="caption" color="textSecondary">
                Fecha de Nacimiento
              </Typography>
              <Typography variant="body1">
                {new Date(user.persona.birthdate).toLocaleDateString()}
              </Typography>
            </Box>
          </Box>
        </Grid>

        <Grid item xs={12} sm={6}>
          <Box className="user-profile__info-item">
            <PersonIcon className="user-profile__info-icon" />
            <Box>
              <Typography variant="caption" color="textSecondary">
                Género
              </Typography>
              <Typography variant="body1">
                {user.persona.gender === 'male' ? 'Masculino' : 'Femenino'}
              </Typography>
            </Box>
          </Box>
        </Grid>

        <Grid item xs={12} sm={6}>
          <Box className="user-profile__info-item">
            <BadgeIcon className="user-profile__info-icon" />
            <Box>
              <Typography variant="caption" color="textSecondary">
                DNI
              </Typography>
              <Typography variant="body1">
                {user.persona.dni}
              </Typography>
            </Box>
          </Box>
        </Grid>

        <Grid item xs={12} sm={6}>
          <Box className="user-profile__info-item">
            <AdminPanelSettingsIcon className="user-profile__info-icon" />
            <Box>
              <Typography variant="caption" color="textSecondary">
                Rol
              </Typography>
              <Typography variant="body1">
                {user.usuario.role === 'user' ? 'Usuario' : 'Administrador'}
              </Typography>
            </Box>
          </Box>
        </Grid>
      </Grid>

      <Box className="user-profile__actions">
        <Button
          variant="contained"
          color="error"
          startIcon={<LogoutIcon />}
          onClick={handleLogout}
          className="user-profile__logout-button"
        >
          Cerrar Sesión
        </Button>
      </Box>
    </Paper>
  );
};

UserProfile.propTypes = {
  user: PropTypes.shape({
    persona: PropTypes.shape({
      birthdate: PropTypes.string,
      cellphone: PropTypes.string,
      dni: PropTypes.string,
      email: PropTypes.string,
      gender: PropTypes.string,
      lastName: PropTypes.string,
      name: PropTypes.string,
    }),
    usuario: PropTypes.shape({
      role: PropTypes.string,
    }),
  }),
};

export default UserProfile;
