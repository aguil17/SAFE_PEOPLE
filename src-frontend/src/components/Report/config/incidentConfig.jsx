import LocalFireDepartmentIcon from '@mui/icons-material/LocalFireDepartment';
import SportsKabaddiIcon from '@mui/icons-material/SportsKabaddi';
import DirectionsCarFilledIcon from '@mui/icons-material/DirectionsCarFilled';
import fireIcon from "../../../assets/icons/point_fire.png";
import crashIcon from "../../../assets/icons/point_crash.png";
import thiefIcon from "../../../assets/icons/point_thief.png";
import aloneIcon from "../../../assets/icons/point_alone.png";

export const incidentImages = {
  fire: fireIcon,
  robbery: thiefIcon,
  accident: crashIcon,
  default: aloneIcon,
};

export const incidentIcons = {
  fire: <LocalFireDepartmentIcon />,
  robbery: <SportsKabaddiIcon />,
  accident: <DirectionsCarFilledIcon />,
};

export const filterConfig = [
  { label: "🔥 Incendio", value: "fire", color: "#FF4B4B" },
  { label: "🦹‍♂️ Robo", value: "robbery", color: "#041122" },
  { label: "🚗 Accidente", value: "accident", color: "#00C97E" },
];
