export default {
  extends: ["stylelint-config-standard-scss"],
  rules: {
    "color-hex-length": "short",
    "max-nesting-depth": 3, // 🔹 Máximo de 3 niveles de anidación en SCSS
    "selector-max-id": 0, // 🔹 No permitir IDs en los selectores
    "scss/dollar-variable-pattern": "^([a-z][a-z0-9]*)(-[a-z0-9]+)*$", // 🔹 Variables en kebab-case
    "scss/load-no-partial-leading-underscore": true, // 🔹 Importaciones limpias en SCSS
    "selector-class-pattern": "^[a-z0-9\\-_]+$", // Permite BEM
  },
};
