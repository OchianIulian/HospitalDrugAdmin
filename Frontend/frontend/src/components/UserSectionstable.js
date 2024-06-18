import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';
import { jwtDecode } from 'jwt-decode';

const SectionsTable = () => {
  const token = Cookies.get('token');
  const [sections, setSections] = useState([]);
  const [newSection, setNewSection] = useState({ name: '' });
  const [selectedSection, setSelectedSection] = useState(null);

  useEffect(() => {
    // Fetch sections
    axios.get('http://localhost:8080/ward/getAllWards', { headers: { Authorization: `Bearer ${token}` } })
      .then(response => setSections(response.data))
      .catch(error => console.error(error));
  }, []);

  const handleAddSection = () => {
    axios.post(`http://localhost:8080/ward/add/${newSection.name}`,{},  { headers: { Authorization: `Bearer ${token}` } })
      .then(response => setSections([...sections, response.data]))
      .catch(error => console.error(error));
  };

  if(token == null) {
    return <h2>You need to login to see this page</h2>;
  } else {
    const decodedToken = jwtDecode(token);
    if(decodedToken.exp < Date.now() / 1000) {
      return <h2>Your session has expired. Please login again.</h2>;
    }
  }

  return (
    <div>
      <h2 className='navbarElemTitle'>Sections</h2>
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
          </tr>
        </thead>
        <tbody>
          {sections.map(section => (
            <tr key={section.id} onClick={() => setSelectedSection(section)}>
              <td>{section.name}</td>
            </tr>
          ))}
        </tbody>
      </table>

      {selectedSection && (
        <table>
          <thead>
            <tr>
              <th>Property</th>
              <th>Value</th>
            </tr>
          </thead>
          <tbody>
            {Object.entries(selectedSection).map(([key, value]) => (
              <tr key={key}>
                <td>{key}</td>
                <td>{value}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default SectionsTable;