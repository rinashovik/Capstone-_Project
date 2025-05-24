import React, { useEffect, useState, useRef } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { FaLock, FaUser } from 'react-icons/fa';
import { GrMailOption } from 'react-icons/gr';
import axios from 'axios';

const RegisterForm = () => {
  const navigate = useNavigate();

  useEffect(() => {
    document.title = 'Register';
  }, []);

  const [user, setUser] = useState({
    username: '',
    email: '',
    password: '',
    verifyPassword: '',
  });

  const [errors, setErrors] = useState({});

  const { username, email, password, verifyPassword } = user;
  const errorRef = useRef(null);

  useEffect(() => {
    if (errorRef.current) {
      const errorMessageHeight = errorRef.current.clientHeight;
      const newMargin = 30 + errorMessageHeight;
      document.documentElement.style.setProperty('--error-margin-register', `${newMargin}px`);
    }
  }, [errors]);

  const onInputChange = (e) => {
    setErrors((prevErrors) => ({ ...prevErrors, [e.target.name]: undefined }));
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    try {
      await axios.post('http://localhost:8080/register', user, {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        withCredentials: true,
      });
  
      setErrors({});
      navigate('/login');

    } catch (error) {
      console.error('Registration error:', error);
    
      if (error.response && error.response.data) {
        const serverErrors = error.response.data;
    
        if (serverErrors.error) {
          setErrors({ ...errors, verifyPassword: serverErrors.error });
        } else if (serverErrors.errors) {
          setErrors({
            username: serverErrors.errors.username,
            email: serverErrors.errors.email,
            password: serverErrors.errors.password,
            verifyPassword: serverErrors.errors.verifyPassword,
          });
        }
      } else {
        console.error('Unexpected error:', error);
        console.log('Response:', error.response);
      }
    }
  };

  return (
    <div className="wrapper">
      <form onSubmit={handleSubmit}>
        <h1>Register</h1>
        <div className="input-box" style={{ marginBottom: 'var(--error-margin-register)' }}>
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

        <div className="input-box" style={{ marginBottom: 'var(--error-margin-register)' }}>
          <input
            type="text"
            name="email"
            placeholder="Email"
            required
            value={email}
            onChange={(e) => onInputChange(e)}
          />
          <GrMailOption className="icon" />
          {errors.email && (
            <div className="error-message" ref={errorRef}>
              {errors.email}
            </div>
          )}
        </div>

        <div className="input-box" style={{ marginBottom: 'var(--error-margin-register)' }}>
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

        <div className="input-box" style={{ marginBottom: 'var(--error-margin-register)' }}>
          <input
            type="password"
            name="verifyPassword"
            placeholder="Verify Password"
            required
            value={verifyPassword}
            onChange={(e) => onInputChange(e)}
          />
          <FaLock className="icon" />
          {errors.verifyPassword && (
            <div className="error-message" ref={errorRef}>
              {errors.verifyPassword}
            </div>
          )}
        </div>

        <button type="submit">Register</button>

        <div className="register-link">
          <p>
            Already have an account? <Link to="/login">Login!</Link>
          </p>
        </div>
      </form>
    </div>
  );
};

export default RegisterForm;