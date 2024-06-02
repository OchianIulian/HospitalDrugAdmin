# HospitalDrugAdmin

## Descriere

Acest proiect are scopul de a gestiona stocurile de medicamente dintr-un spital. Sistemul permite secțiilor spitalului să solicite medicamente dintr-un depozit central. Dacă medicamentele nu sunt disponibile în depozit, acestea trebuie adăugate înainte de a putea fi comandate.

## Tehnologii Utilizate

- Frontend: React.js
- Backend: Java Spring Boot
- Bază de Date: MySQL
- Autentificare: JWT (JSON Web Tokens)
- Stilizare: CSS
- Documentatie: Swagger

## Funcționalități

- Autentificare și autorizare: Administratori de depozit și personal medical.
- Gestionarea medicamentelor în depozit: Adăugare, modificare, ștergere medicamente.
- Gestionarea stocurilor și comenzilor: Vizualizare stocuri, comandă medicamente, verificare stoc.
- Dashboard și rapoarte: Statistici și rapoarte detaliate despre stocuri, consum si personal medical.

## Instalare și Configurare
### Backend (Spring Boot)

#### Clonare Repository:

```
git clone https://github.com/username/medications-management.git
cd medications-management/server
```

#### Configurare Bază de Date:
Modifică application.properties pentru a include detaliile bazei de date MySQL

#### Build și Rulare:
```
mvn clean install
mvn spring-boot:run
```

### Frontend (React.js)
#### Clonare Repository:

```
cd medications-management/client
 ```

#### Instalare Dependențe:
```
npm install
```
#### Rulare Aplicație:
````
npm start
````
## Contact
Pentru întrebări sau sugestii, te rugăm să ne contactezi la ochianandrei15@gmail.com.




