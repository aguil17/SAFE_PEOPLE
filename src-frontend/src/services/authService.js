const API_URL = "http://34.198.223.16:8762/ms-operator/user";

const registerOrLogin = async (userData) => {
  try {
    const response = await fetch(API_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        targetMethod: "POST",
        body: userData,
      }),
    });

    const result = await response.json();

    if (!result.error) {
      // Registro exitoso
      return result;
    } else if (result.code === "409" && result.persona) {
      // Usuario ya existe, armamos un objeto de usuario con la información de "persona"
      return {
        error: false,
        code: "201",
        usuario: {
          id: result.persona.id,
          username: userData.nombreUsuario || result.persona.email,
          password: null,
          role: "user",
          idPerson: result.persona.id,
          creationDate: result.persona.creationDate,
          updateDate: result.persona.updateDate,
        },
        persona: result.persona,
        message: "Inicio de sesión exitoso (usuario ya registrado)",
      };
    }

    return result;
  } catch (error) {
    console.error("Error en autenticación:", error);
    return { error: true, message: "Error en la conexión" };
  }
};

export default { registerOrLogin };
