import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "../pages/Home";
import Reports from "../pages/Reports";
import Statistics from "../pages/Statistics";
import EmergencyContacts from "../pages/EmergencyContacts";
import Profile from "../pages/Profile";
import Navbar from "../components/Navbar/Navbar";

const AppRouter = () => {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/reports" element={<Reports />} />
        <Route path="/statistics" element={<Statistics />} />
        <Route path="/contacts" element={<EmergencyContacts />} />
        <Route path="/profile" element={<Profile />} />
      </Routes>
    </Router>
  );
};

export default AppRouter;
