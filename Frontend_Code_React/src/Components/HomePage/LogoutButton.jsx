import React from 'react';
import { useNavigate } from 'react-router-dom';
import './LogoutButton.css';
import axios from 'axios';

const LogoutButton = () => {
  const navigate = useNavigate();

  const handleLogout = async () => {
    await axios.get("http://localhost:8080/logout",{withCredentials:true})
    navigate('/login');
  };

  return (
    <button className="logout-button" onClick={handleLogout}>Logout</button>
  );
};

export default LogoutButton;

