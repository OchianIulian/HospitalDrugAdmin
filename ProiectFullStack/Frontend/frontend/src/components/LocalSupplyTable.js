import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';
import { jwtDecode } from 'jwt-decode';

const LocalSupplyTable = () => {
    const token = Cookies.get('token');
  const [drugs, setDrugs] = useState([]);
  const [newDrug, setNewDrug] = useState({ name: '', dosage: '' });

  useEffect(() => {
    // Fetch drugs
    axios.get('/api/drugs')
      .then(response => setDrugs(response.data))
      .catch(error => console.error(error));
  }, []);

  const handleAddDrug = () => {
    axios.post('/api/drugs', newDrug)
      .then(response => setDrugs([...drugs, response.data]))
      .catch(error => console.error(error));
  };

  const handleDeleteDrug = (id) => {
    axios.delete(`/api/drugs/${id}`)
      .then(() => setDrugs(drugs.filter(drug => drug.id !== id)))
      .catch(error => console.error(error));
  };

    if(token == null) {
        return <h2>You need to login to see this page</h2>;
    }

  return (
    <div>
      <h2>Drugs</h2>
      <input
        type="text"
        placeholder="Name"
        value={newDrug.name}
        onChange={(e) => setNewDrug({ ...newDrug, name: e.target.value })}
      />
      <input
        type="text"
        placeholder="Dosage"
        value={newDrug.dosage}
        onChange={(e) => setNewDrug({ ...newDrug, dosage: e.target.value })}
      />
      <button onClick={handleAddDrug}>Add Drug</button>

      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Dosage</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {drugs.map(drug => (
            <tr key={drug.id}>
              <td>{drug.name}</td>
              <td>{drug.dosage}</td>
              <td>
                <button onClick={() => handleDeleteDrug(drug.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default LocalSupplyTable;
