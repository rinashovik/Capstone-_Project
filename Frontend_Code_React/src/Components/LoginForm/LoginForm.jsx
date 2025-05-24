import React, { useEffect, useState, useRef } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { FaLock, FaUser } from 'react-icons/fa';
import axios from 'axios';
import './LoginForm.css';

const LoginForm = () => {
  const navigate = useNavigate();

  useEffect(() => {
    document.title = 'Login';
  }, []);

  const [user, setUser] = useState({
    username: '',
    password: '',
  });

  const [errors, setErrors] = useState({});

  const { username, password } = user;
  const errorRef = useRef(null);

  useEffect(() => {
    if (errorRef.current) {
      const errorMessageHeight = errorRef.current.clientHeight;
      const newMargin = 30 + errorMessageHeight;
      document.documentElement.style.setProperty('--error-margin-login', `${newMargin}px`);
    }
  }, [errors]);

  const onInputChange = (e) => {
    setErrors((prevErrors) => ({ ...prevErrors, [e.target.name]: undefined }));
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    try {
      await axios.post('http://localhost:8080/login', user, {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        withCredentials: true,
      });
  
      setErrors({});
      navigate('/');
    } catch (error) {
      if (error.response && error.response.data && error.response.data.errors) {
        const serverErrors = error.response.data.errors;
  
        if (Array.isArray(serverErrors) && serverErrors.length > 0) {
          // Handle specific field errors
          const fieldErrors = {};
          serverErrors.forEach((error) => {
            fieldErrors[error.field] = error.defaultMessage;
          });
  
          // Check for specific error cases
          if (fieldErrors.password) {
            // Incorrect password
            fieldErrors.password = 'Incorrect password';
          } else if (fieldErrors.username) {
            // Username does not exist
            fieldErrors.general = ''; // Clear general error if username-specific error is present
          }
  
          setErrors(fieldErrors);
        } else if (typeof serverErrors === 'object') {
          // Handle different response structure
          const fieldErrors = {};
          for (const field in serverErrors) {
            fieldErrors[field] = serverErrors[field];
          }
  
          // Check for specific error cases
          if (fieldErrors.password === 'Invalid password') {
            // Incorrect password
            fieldErrors.password = 'Incorrect password';
          } else if (fieldErrors.username === 'The given username does not exist') {
            // Username does not exist
            fieldErrors.general = ''; // Clear general error if username-specific error is present
          }
  
          setErrors(fieldErrors);
        } else if (typeof serverErrors === 'string') {
          // Handle general errors
          setErrors({ general: serverErrors });
        } else {
        }
      }
    }
  };

  return (
    <div className="wrapper">
      <form onSubmit={handleSubmit}>
        <h1>Login</h1>

        <div className="input-box" style={{ marginBottom: 'var(--error-margin-login)' }}>
          <input
            type="text"
            name="username"
            placeholder="Username"
            required
            value={username}
            onChange={(e) => onInputChange(e)}
          />
          <FaUser className="icon" />
          {errors.username && (
            <div className="error-message" ref={errorRef}>
              {errors.username}
            </div>
          )}
        </div>

        <div className="input-box" style={{ marginBottom: 'var(--error-margin-login)' }}>
          <input
            type="password"
            name="password"
            placeholder="Password"
            required
            value={password}
            onChange={(e) => onInputChange(e)}
          />
          <FaLock className="icon" />
          {errors.password && (
            <div className="error-message" ref={errorRef}>
              {errors.password}
            </div>
          )}
        </div>

        {errors.general && (
          <div className="error-message" ref={errorRef}>
            {errors.general}
          </div>
        )}

        <button type="submit">Login</button>

        <div className="register-link">
          <p>
            Don't have an account? <Link to="/register">Register!</Link>
          </p>
        </div>
      </form>
    </div>
  );
};

export default LoginForm;