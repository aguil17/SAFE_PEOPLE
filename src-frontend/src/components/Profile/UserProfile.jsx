import PropTypes from "prop-types";
import { useDispatch } from "react-redux";
import { logout } from "../../redux/authSlice";
import { useNavigate } from "react-router-dom";
import { Typography, Paper, Button } from "@mui/material";
import "./UserProfile.scss";

const UserProfile = ({ user }) => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleLogout = () => {
    dispatch(logout()); // Eliminar datos en Redux
    localStorage.removeItem("user"); // Eliminar usuario de localStorage
    navigate("/"); // Redirigir a Home
  };

  if (!user) {
    return <Typography variant="h5">No hay usuario autenticado</Typography>;
  }

  return (
    <Paper className="user-profile">
      <Typography variant="h4">Perfil de Usuario</Typography>
      <Typography variant="h6">
        <strong>Nombre:</strong> {user.persona.name} {user.persona.lastName}
      </Typography>
      <Typography variant="h6">
        <strong>Correo:</strong> {user.persona.email}
      </Typography>
      <Typography variant="h6">
        <strong>Celular:</strong> {user.persona.cellphone}
      </Typography>
      <Typography variant="h6">
        <strong>Fecha de Nacimiento:</strong> {user.persona.birthdate}
      </Typography>
      <Typography variant="h6">
        <strong>Género:</strong> {user.persona.gender}
      </Typography>
      <Typography variant="h6">
        <strong>DNI:</strong> {user.persona.dni}
      </Typography>
      <Typography variant="h6">
        <strong>Rol:</strong> {user.usuario.role}
      </Typography>

      <Button
        variant="contained"
        color="secondary"
        className="user-profile__logout"
        onClick={handleLogout}
      >
        Cerrar Sesión
      </Button>
    </Paper>
  );
};

UserProfile.propTypes = {
  user: PropTypes.shape({
    persona: PropTypes.shape({
      birthdate: PropTypes.any,
      cellphone: PropTypes.any,
      dni: PropTypes.any,
      email: PropTypes.any,
      gender: PropTypes.any,
      lastName: PropTypes.any,
      name: PropTypes.any,
    }),
    usuario: PropTypes.shape({
      role: PropTypes.any,
    }),
  }),
};

export default UserProfile;
