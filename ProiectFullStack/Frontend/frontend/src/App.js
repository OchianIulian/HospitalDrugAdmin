import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './components/Login';
import Dashboard from './components/Dashboard';
import Users from './components/UsersTable';
import Drugs from './components/DrugsTable';
import Sections from './components/SectionsTable';
import UserDashboard from './components/UserDashboard';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/admin-dashboard" element={<Dashboard />} >
          <Route path="users" element={<Users />} />
          <Route path="drugs" element={<Drugs />} />
          <Route path="sections" element={<Sections />} />
        </Route>
        <Route path="/user-dashboard" element={<UserDashboard />} >
          <Route path="drugs" element={<Drugs />} />
          <Route path="sections" element={<Sections />} />
        </Route>
      </Routes>
    </Router>
  );
}

export default App;
