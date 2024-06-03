import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';
import { jwtDecode } from 'jwt-decode';

const DrugsTable = () => {
    const token = Cookies.get('token');
    const [drugs, setDrugs] = useState([]);
    const [newDrug, setNewDrug] = useState({ name: '', quantity: '' });

    useEffect(() => {
        // Fetch drugs
        axios.get('http://localhost:8080/drugStorage/getAllDrugs', { headers: { Authorization: `Bearer ${token}` } })
        .then(response => setDrugs(response.data))
        .catch(error => console.error(error));
    }, []);

    const handleAddDrug = () => {
        console.log(newDrug.name + " " + newDrug.quantity);
        axios.put(`http://localhost:8080/addDrugQuantity/${newDrug.name}/${newDrug.quantity}`, { headers: { Authorization: `Bearer ${token}` } })
        .then(response => setDrugs([...drugs, response.data]))
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
      <h2>Drugs</h2>
      <input
        type="text"
        placeholder="Name"
        value={newDrug.name}
        onChange={(e) => setNewDrug({ ...newDrug, name: e.target.value })}
      />
      <input
        type="text"
        placeholder="Quantity"
        value={newDrug.quantity}
        onChange={(e) => setNewDrug({ ...newDrug, quantity: e.target.value })}
      />
      <button onClick={handleAddDrug}>Add Drug</button>
      <br/>
      <input
        type="text"
        placeholder="Name"
        //value={newDrug.name}
        //onChange={(e) => setNewDrug({ ...newDrug, name: e.target.value })}
      />
      <input
        type="text"
        placeholder="Quantity"
        //value={newDrug.quantity}
        //onChange={(e) => setNewDrug({ ...newDrug, quantity: e.target.value })}
      />
      <button>Remove drug</button>

      <table>
            <thead>
            <tr>
                <th>Name</th>
                <th>Manufacturer</th>
                <th>Description</th>
                <th>Side effects</th>
                <th>Dosage</th>
                <th>Quantity</th>
            </tr>
            </thead>
            <tbody>
            {drugs.map(drug => (
                <tr key={drug.id}>
                    <td>{drug.name}</td>
                    <td>{drug.manufacturer}</td>
                    <td>{drug.description}</td>
                    <td>{drug.sideEffects}</td>
                    <td>{drug.dosage}</td>
                    <td>{drug.quantity}</td>
                </tr>
            ))}
            </tbody>
      </table>
    </div>
  );
};

export default DrugsTable;
