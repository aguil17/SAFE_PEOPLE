import { useSelector } from "react-redux";
import { Box } from "@mui/material";
import UserProfile from "../components/Profile/UserProfile";
import "./Profile.scss";

const Profile = () => {
  const user = useSelector((state) => state.auth.user);

  return (
    <Box className="profile">
      <UserProfile user={user} />
    </Box>
  );
};

export default Profile;
