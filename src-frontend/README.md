# 🛡 SafePeople - Reportes de Incidentes

## 📌 Introducción
**SafePeople** es una aplicación web diseñada para facilitar la **notificación rápida de incidentes** como robos, incendios y accidentes en la ciudad. Permite a los ciudadanos reportar incidentes con geolocalización en tiempo real, adjuntar imágenes y notificar a las entidades correspondientes.

## 🚀 Características Principales
- **Mapa interactivo** 📍 con reportes en tiempo real.
- **Registro y autenticación de usuarios** 🔐.
- **Creación de reportes de incidentes** con detalles como fecha, hora, tipo de incidente y ubicación.
- **Carga de imágenes** para evidenciar los incidentes.
- **Filtrado de reportes por usuario** en la sección de **Mis Reportes**.
- **Eliminación de reportes** por parte del usuario.
- **Diseño responsivo** y **accesible**.

## 🛠 Tecnologías Utilizadas

### 📌 Frontend
- **React.js** ⚛️ - Biblioteca para la interfaz de usuario.
- **React Router** - Para la navegación entre páginas.
- **Redux Toolkit** 🗂 - Manejo del estado global de la aplicación.
- **Material UI** 🎨 - Estilos y componentes modernos.
- **Leaflet** 🗺 - Mapas interactivos.
- **SCSS** 💅 - Estilos modulares y organizados.

### 📌 Backend
- **Node.js + Express.js** 🚀 - API REST para la gestión de incidentes.
- **PostgreSQL** 🛢 - Base de datos relacional.
- **AWS** ☁ - Infraestructura en la nube.

## 🔧 Instalación y Configuración

1. **Clonar el repositorio:**
```sh
 git clone https://github.com/tu_usuario/safepeople.git
 cd safepeople
```

2. **Instalar dependencias:**
```sh
 npm install
```

3. **Configurar las variables de entorno:**
   - Crear un archivo `.env` en la raíz con las siguientes variables:
```env
REACT_APP_API_BASE_URL=http://1.1.1.11.1:8762/ms-operator
REACT_APP_MAP_TILE_URL=https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png
```

4. **Ejecutar el proyecto en modo desarrollo:**
```sh
 npm run dev
```

5. **Acceder a la aplicación:**
   - Abrir en el navegador: `http://localhost:5173`

## 🖥 Estructura del Proyecto

```
📂 SafePeople
│── 📂 src
│   │── 📂 assets           # Imágenes y recursos
│   │── 📂 components       # Componentes reutilizables
│   │── 📂 pages            # Páginas principales (Home, Reports, etc.)
│   │── 📂 redux            # Estado global con Redux Toolkit
│   │── 📂 services         # Llamadas a la API
│   │── 📂 utils            # Funciones auxiliares
│   │── 📄 App.jsx          # Componente principal
│   │── 📄 main.jsx         # Punto de entrada de la app
│── 📄 README.md            # Documentación
│── 📄 package.json         # Dependencias y scripts
```

## 🗺 Funcionamiento de la Aplicación

1. **Autenticación de Usuarios**
   - Los usuarios deben iniciar sesión o registrarse para reportar incidentes.

2. **Mapa Interactivo**
   - Los incidentes se visualizan en el mapa con íconos representativos.
   - Se puede mover el marcador antes de reportar un incidente.

3. **Reporte de Incidentes**
   - Se completa un formulario con detalles del incidente.
   - Se pueden agregar imágenes y detalles adicionales.

4. **Mis Reportes**
   - Se listan los reportes creados por el usuario autenticado.
   - Se pueden eliminar reportes si es necesario.

## 📤 Contribución
¡Las contribuciones son bienvenidas! 🛠️ Si deseas mejorar SafePeople:
1. **Forkea** el repositorio 🍴.
2. Crea una **rama nueva** (`feature/nueva-funcionalidad`) 🔄.
3. **Haz commit** de tus cambios (`git commit -m 'Agrega nueva funcionalidad'`) ✅.
4. **Haz push** y abre un **Pull Request** 🚀.

## 📜 Licencia
Este proyecto está bajo la licencia **MIT**. Puedes utilizarlo y modificarlo libremente. 😊

---

🚀 ¡Gracias por ser parte de SafePeople! 🌍
