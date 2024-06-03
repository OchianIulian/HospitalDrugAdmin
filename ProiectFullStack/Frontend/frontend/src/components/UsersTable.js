import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';
import { jwtDecode } from 'jwt-decode';
import '../css/Dashboard.css';


const UsersTable = () => {
    const token = Cookies.get('token');
  const [users, setUsers] = useState([]);
  const [newUser, setNewUser] = useState({ fullName: '', email: '', cnp: '', role: ''});



  useEffect(() => {
    // Fetch users
    axios.get('http://localhost:8080/getAllUsers' ,  { headers: { Authorization: `Bearer ${token}` } })
      .then(response => setUsers(response.data))
      .catch(error => console.error(error));
  }, []);

  const handleAddUser = () => {
    axios.post('http://localhost:8080/addUser', newUser, { headers: { Authorization: `Bearer ${token}` } })
      .then(response => setUsers([...users, response.data]))
      .catch(error => console.error(error));
  };

  const handleDeleteUser = (id) => {
    axios.delete(`http://localhost:8080/deleteUser/${id}`, { headers: { Authorization: `Bearer ${token}` } })
      .then(() => setUsers(users.filter(user => user.id !== id)))
      .catch(error => console.error(error));
  };

  if(token == null) {
    return <h2>You need to login to see this page</h2>;
  } else {
    const decodedToken = jwtDecode(token);
    if (decodedToken.role != 'ADMIN') {
      return <h2>Only admins can see this page</h2>;
    } else if(decodedToken.exp < Date.now() / 1000) {
      return <h2>Your session has expired. Please login again.</h2>;
    }

  }

  return (
    <div className="table-container">
      <h2>Users</h2>
      <input
        type="text"
        placeholder="Name"
        value={newUser.fullName}
        onChange={(e) => setNewUser({ ...newUser, fullName: e.target.value })}
      />
      <input
        type="email"
        placeholder="Email"
        value={newUser.email}
        onChange={(e) => setNewUser({ ...newUser, email: e.target.value })}
      />
      <input
        type="text"
        placeholder="CNP"
        value={newUser.cnp}
        onChange={(e) => setNewUser({ ...newUser, cnp: e.target.value })}
      />
      <input
        type="text"
        placeholder="Role"
        value={newUser.role}
        onChange={(e) => setNewUser({ ...newUser, role: e.target.value })}
      />
      <button className="btn add" onClick={handleAddUser}>Add User</button>

      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {users.map(user => (
            <tr key={user.id}>
              <td>{user.fullName}</td>
              <td>{user.email}</td>
              <td>
                <button className="btn delete" onClick={() => handleDeleteUser(user.fullName)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default UsersTable;
