import React from 'react';
import { Link, Outlet } from 'react-router-dom';
import '../css/Dashboard.css';
import { useNavigate } from 'react-router-dom';


const UserDashboard = () => {

    const navigate = useNavigate();
    
    const handleLogout = () => {
        localStorage.removeItem('token');
        navigate('/');
    };


  return (
    <div>
      <nav className="navbar">
        <ul>
          {/* <li><Link to="/user-dashboard/drugs">Drugs</Link></li> */}
          <li><Link to="/user-dashboard/sections">Sections</Link></li>
          <li><button onClick={handleLogout} id='logout-btn'>Logout</button></li>

        </ul>
      </nav>
      <div className="container">
        <Outlet />
      </div>
    </div>
  );
};

export default UserDashboard;