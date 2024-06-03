import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';
import { jwtDecode } from 'jwt-decode';

const SectionsTable = () => {
    const token = Cookies.get('token');
  const [sections, setSections] = useState([]);
  const [newSection, setNewSection] = useState({ name: '' });

  useEffect(() => {
    // Fetch sections
    axios.get('http://localhost:8080/ward/getAllWards', { headers: { Authorization: `Bearer ${token}` } })
      .then(response => setSections(response.data))
      .catch(error => console.error(error));
  }, []);

  const handleAddSection = () => {
    console.log(newSection.name);
    console.log(token);
    axios.post(`http://localhost:8080/ward/add/${newSection.name}`,{},  { headers: { Authorization: `Bearer ${token}` } })
      .then(response => setSections([...sections, response.data]))
      .catch(error => console.error(error));
  };

  const handleDeleteSection = (name) => {
    axios.delete(`http://localhost:8080/ward/delete/${name}`, { headers: { Authorization: `Bearer ${token}` } })
      .then(() => setSections(sections.filter(section => section.name !== name)))
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
    <div>
      <h2>Sections</h2>
      <input
        type="text"
        placeholder="Name"
        value={newSection.name}
        onChange={(e) => setNewSection({ ...newSection, name: e.target.value })}
      />
      <button onClick={handleAddSection}>Add Section</button>

      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {sections.map(section => (
            <tr key={section.id}>
              <td>{section.name}</td>
              <td>
                <button onClick={() => handleDeleteSection(section.name)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default SectionsTable;
