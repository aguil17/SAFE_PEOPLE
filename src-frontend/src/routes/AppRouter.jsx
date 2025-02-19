import { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { useSelector } from "react-redux";
import Home from "../pages/Home";
import Reports from "../pages/Reports";
import Profile from "../pages/Profile";
import Navbar from "../components/Navbar/Navbar";
import AuthModal from "../components/Auth/AuthModal";

const AppRouter = () => {
  const user = useSelector((state) => state.auth.user);
  const [openAuthModal, setOpenAuthModal] = useState(false);

  useEffect(() => {
    if (!user) {
      setOpenAuthModal(true);
    }
  }, [user]);

  return (
    <Router>
      <Navbar />
      <AuthModal open={openAuthModal} onClose={() => setOpenAuthModal(false)} />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/reports" element={<Reports />} />
        <Route path="/profile" element={<Profile />} />
      </Routes>
    </Router>
  );
};

export default AppRouter;
