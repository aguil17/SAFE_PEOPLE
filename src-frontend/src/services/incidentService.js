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

const INCIDENT_API_URL = "http://34.198.223.16:8762/ms-buscador/incidente";

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
      `${INCIDENT_API_URL}/fechaCreacionInicial/${startDate}/fechaCreacionFinal/${endDate}`,
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

    return result.incidentes; // Retorna la lista de incidentes
  } catch (error) {
    console.error("Error obteniendo los incidentes:", error);
    return [];
  }
};

const DELETE_API_URL = "http://34.198.223.16:8762/ms-operator/incidente";

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

    const response = await fetch(`${DELETE_API_URL}/${incidentId}`, {
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

