import React from 'react';
import { Link, Outlet } from 'react-router-dom';
import '../css/Dashboard.css';
import { useNavigate } from 'react-router-dom';


const Dashboard = () => {

    const navigate = useNavigate();
    
    const handleLogout = () => {
        localStorage.removeItem('token');
        navigate('/');
    };


  return (
    <div>
      <nav className="navbar">
        <ul>
          <li><Link to="/admin-dashboard/users">Users</Link></li>
          <li><Link to="/admin-dashboard/drugs">Drugs</Link></li>
          <li><Link to="/admin-dashboard/sections">Sections</Link></li>
          <li><button onClick={handleLogout} id='logout-btn'>Logout</button></li>
        </ul>
      </nav>
      <div className="container">
        <Outlet />
      </div>
    </div>
  );
};

export default Dashboard;
