import React, { useEffect } from 'react';
import './HomePage.css';
import LogoutButton from './LogoutButton';

const HomePage = () => {
    useEffect(() => {
      document.title = 'ProcrastiNOT';
    }, []);

  const handleLogout = () => {
    console.log('Logout logic goes here');
  };

  return (
    <div className="logout-btn">
      <LogoutButton onLogout={handleLogout} />
    </div>
  );
};

sdfsasd
export default HomePage;
