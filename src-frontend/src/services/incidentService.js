const API_URL = "http://34.198.223.16:8762/ms-operator/incidente";

/**
 * Envía un reporte de incidente al backend.
 * @param {Object} incidentData - Datos del incidente a enviar.
 * @returns {Promise<Object>} Respuesta del backend.
 */
export const reportIncident = async (incidentData) => {
  try {
    const payload = {
      targetMethod: "POST",
      body: incidentData, // El body ya viene listo desde IncidentStepper
    };

    const response = await fetch(API_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(payload),
    });

    const result = await response.json();

    if (!response.ok) {
      throw new Error(result.message || "Error en la solicitud");
    }

    return { success: true, data: result };
  } catch (error) {
    console.error("Error reportando el incidente:", error);
    return { success: false, message: error.message };
  }
};

/**
 * Adapta la nueva estructura de incidente a la estructura anterior
 * @param {Object} incident - Incidente con la nueva estructura
 * @returns {Object} Incidente con la estructura anterior
 */
const adaptIncident = (incident) => {
  if (!incident) return null;

  // Extraer datos del usuario/informante
  const informante = incident.usuario?.persona || {};
  
  return {
    id: incident.id,
    incidentType: incident.incidentType,
    descriptionIncident: incident.description, // Adaptación del nombre del campo
    date: incident.date,
    time: incident.time,
    photo: incident.photo,
    deleteAt: incident.deleteAt,
    creationDate: incident.creationDate,
    
    // Datos de ubicación
    idLocation: incident.ubicacion?.id,
    cityName: incident.ubicacion?.cityName,
    districtName: incident.ubicacion?.districtName,
    descriptionLocation: incident.ubicacion?.description,
    reference: incident.ubicacion?.reference,
    latitude: parseFloat(incident.ubicacion?.latitude),
    longitude: parseFloat(incident.ubicacion?.longitude),
    
    // Datos de usuario
    idUser: incident.usuario?.id || 0,
    username: incident.usuario?.username,
    passwordUser: incident.usuario?.password,
    role: incident.usuario?.role,
    
    // Datos de persona
    idPerson: informante.id || 0,
    personName: informante.name,
    personLastName: informante.lastName,
    dni: informante.dni,
    email: informante.email,
    cellphone: informante.cellphone,
    passwordPerson: null,
    birthdate: informante.birthdate,
    gender: informante.gender,
    
    // Arrays
    heridos: incident.heridos || [],
    materiales: incident.materiales || [],
    
    // Convertir los datos del informante al formato anterior
    incidenteInformantes: [{
      id: incident.id,
      name: informante.name,
      lastName: informante.lastName,
      cellphone: informante.cellphone,
      email: informante.email,
      assignmentDate: incident.creationDate,
      creationDate: incident.creationDate
    }]
  };
};

/**
 * Obtiene la lista de incidentes en un rango de fechas.
 * @param {string} startDate - Fecha inicial en formato YYYY-MM-DD.
 * @param {string} endDate - Fecha final en formato YYYY-MM-DD.
 * @returns {Promise<Object[]>} Lista de incidentes.
 */
export const fetchIncidents = async (startDate, endDate) => {
  try {
    const payload = {
      targetMethod: "GET",
    };

    const response = await fetch(
      `${API_URL}/fechaCreacionInicial/${startDate}/fechaCreacionFinal/${endDate}`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(payload),
      }
    );

    const result = await response.json();

    if (!response.ok || result.error) {
      throw new Error(result.message || "Error obteniendo los incidentes");
    }

    // Adaptar cada incidente a la estructura anterior
    const adaptedIncidents = result.incidentes.map(adaptIncident);
    return adaptedIncidents;
  } catch (error) {
    console.error("Error obteniendo los incidentes:", error);
    return [];
  }
};

/**
 * Elimina un incidente por ID.
 * @param {number} incidentId - ID del incidente a eliminar.
 * @returns {Promise<Object>} Respuesta del backend.
 */
export const deleteIncident = async (incidentId) => {
  try {
    const payload = {
      targetMethod: "DELETE",
    };

    const response = await fetch(`${API_URL}/${incidentId}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(payload),
    });

    const result = await response.json();

    if (!response.ok || result.error) {
      throw new Error(result.message || "Error eliminando el incidente");
    }

    return { success: true, data: result };
  } catch (error) {
    console.error("Error eliminando el incidente:", error);
    return { success: false, message: error.message };
  }
};
