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
