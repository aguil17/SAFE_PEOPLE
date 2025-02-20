import PropTypes from "prop-types";
import { useState } from "react";
import { useDispatch } from "react-redux";
import { setUser } from "../../redux/authSlice";
import { Modal, Box, TextField, Button, Tabs, Tab } from "@mui/material";
import authService from "../../services/authService";
import "./AuthModal.scss";

const AuthModal = ({ open, onClose }) => {
  const [tab, setTab] = useState(0); // 0 = Registro, 1 = Iniciar Sesión
  const [form, setForm] = useState({
    nombre: "",
    apellidos: "",
    genero: "male",
    celular: "",
    correo: "",
    dni: "",
    cumpleanios: "",
    nombreUsuario: "",
    password: "",
    role: "user",
  });
  const [errors, setErrors] = useState({ correo: false });

  const dispatch = useDispatch();

  const validateEmail = (email) => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });

    // Validar el correo en tiempo real
    if (name === "correo") {
      setErrors({ ...errors, correo: !validateEmail(value) });
    }
  };

  const handleSubmit = async () => {
    // Validar que los campos obligatorios no estén vacíos
    if (
      (!form.nombre || !form.apellidos || !form.correo || !form.password || !form.celular) &&
      tab === 0
    ) {
      alert("Todos los campos son obligatorios.");
      return;
    }

    // Validar el formato del correo antes de enviar
    if (!validateEmail(form.correo)) {
      alert("Por favor, ingresa un correo válido.");
      return;
    }

    try {
      let userData = form;
      if (tab === 1) {
        // Si está en "Iniciar sesión", solo enviamos el correo con datos dummy
        userData = {
          nombre: "N/A",
          apellidos: "N/A",
          genero: "male",
          celular: "0000000000",
          correo: form.correo,
          dni: "00000000",
          cumpleanios: "2000-01-01",
          nombreUsuario: form.correo,
          password: "dummy",
          role: "user",
        };
      }
      if (tab === 0) {
        userData.nombreUsuario = form.correo;
        userData.dni = "00000000";
        userData.cumpleanios = "2000-01-01";
      }

      const response = await authService.registerOrLogin(userData);

      if (!response.error) {
        dispatch(setUser(response));
        localStorage.setItem("user", JSON.stringify(response));
        onClose();
      } else {
        alert(response.message);
      }
    } catch (error) {
      console.error("Error en autenticación:", error);
    }
  };

  return (
    <Modal open={open} onClose={onClose} className="auth-modal">
      <Box className="auth-modal__container">
        <Tabs value={tab} onChange={(_, value) => setTab(value)}>
          <Tab label="Registro" />
          <Tab label="Iniciar Sesión" />
        </Tabs>
        {tab === 0 ? (
          <>
            <TextField
              label="Nombre"
              name="nombre"
              onChange={handleInputChange}
              fullWidth
              required
              error={!form.nombre}
            />
            <TextField
              label="Apellidos"
              name="apellidos"
              onChange={handleInputChange}
              fullWidth
              required
              error={!form.apellidos}
            />
            <TextField
              label="Correo"
              name="correo"
              type="email"
              onChange={handleInputChange}
              fullWidth
              required
              error={errors.correo}
              helperText={errors.correo ? "Formato de correo inválido" : ""}
            />
            <TextField
              label="Celular"
              name="celular"
              onChange={handleInputChange}
              fullWidth
              required
              error={!form.celular}
            />
            <TextField
              label="Contraseña"
              name="password"
              type="password"
              onChange={handleInputChange}
              fullWidth
              required
              error={!form.password}
            />
          </>
        ) : (
          <>
            <TextField
              label="Correo"
              name="correo"
              type="email"
              onChange={handleInputChange}
              fullWidth
              required
              error={errors.correo}
              helperText={errors.correo ? "Formato de correo inválido" : ""}
            />
          </>
        )}
        <Button variant="contained" className="auth-modal__button" onClick={handleSubmit}>
          {tab === 0 ? "Registrarse" : "Iniciar Sesión"}
        </Button>
      </Box>
    </Modal>
  );
};

AuthModal.propTypes = {
  onClose: PropTypes.func,
  open: PropTypes.any,
};

export default AuthModal;
