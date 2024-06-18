import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../css/Login.css';
import Cookies from 'js-cookie';
import { jwtDecode } from 'jwt-decode';


function Login() {
  const [email, setEmail] = useState('');
  const [cnp, setCnp] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/auth/login', { email, cnp });
       const token = response.data;
      // Save the token in a cookie
      Cookies.set('token', token);
      // Decode the token
      const decodedToken = jwtDecode(token);

      if (response.status === 200) {
        console.log(decodedToken);
        if (decodedToken.role === 'ADMIN') {
            // Navigate to the admin dashboard
            navigate('/admin-dashboard');
          } else if(decodedToken.role == 'USER') {
            // Navigate to the regular user dashboard
            navigate('/user-dashboard');
          } else {
            alert('Invalid role');
          }
      }
    } catch (error) {
      alert('Login failed. Please check your credentials.');
    }
  };



  return (
    <div className="login-container">
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Email:</label>
          <input 
            type="email" 
            value={email} 
            onChange={(e) => setEmail(e.target.value)} 
            required 
          />
        </div>
        <div>
          <label>CNP:</label>
          <input 
            type="password" 
            value={cnp} 
            onChange={(e) => setCnp(e.target.value)} 
            required 
          />
        </div>
        <button type="submit">Login</button>
      </form>
    </div>
  );
}

export default Login;
