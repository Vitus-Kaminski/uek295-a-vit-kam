# README

## Projektbeschreibung

Dies ist ein Server-Projekt mit einer REST-API, die Bestellungen (`Orders`) und deren Sendungsverfolgung (`Tracking`) verwaltet. Die Datenbank basiert auf PostgreSQL und wird über Docker bereitgestellt.

---

## Voraussetzungen

Bevor du das Projekt starten kannst, stelle sicher, dass du folgende Software installiert hast:

- [Docker](https://www.docker.com/)
    
- [Postman](https://www.postman.com/) (optional für API-Tests)
    
- Java 17 oder höher

Ich persöhnlich benutze InteliJ für den auf JavaSpringboot basierendes Projekt.

---
## Installation

### 1. Repository klonen

```bash
git clone <REPOSITORY_URL>
cd <REPOSITORY_NAME>
```

### 2. Docker-Container für PostgreSQL starten

```bash
docker-compose up -d
```

Dies startet die PostgreSQL-Datenbank im Hintergrund.

Die Anwendung läuft nun unter `http://localhost:8080`.

## API-Endpunkte

### Order CRUD

- **GET /api/v1/orders/{id}** – Bestellung per ID abrufen
    
- **POST /api/v1/orders** – Neue Bestellung erstellen
    
- **PUT /api/v1/orders/{id}** – Bestellung aktualisieren
    
- **DELETE /api/v1/orders/{id}** – Bestellung löschen
    

### Tracking CRUD

- **GET /api/v1/orders/trackings/{id}** – Tracking per ID abrufen
    
- **POST /api/v1/orders/trackings** – Neues Tracking erstellen
    
- **PUT /api/v1/orders/trackings/{id}** – Tracking aktualisieren
    

### Weitere Endpunkte

- **GET /api/v1/orders** – Alle Bestellungen abrufen
    
- **GET /api/v1/orders/trackings** – Alle Trackings abrufen
    
- **GET /api/v1/orders/date?before=YYYY-MM-DD&after=YYYY-MM-DD** – Bestellungen nach Datum filtern
    
- **GET /api/v1/orders/comment/{comment}** – Bestellungen nach Kommentar filtern
    
- **GET /api/v1/orders/status?status=Pending** – Bestellungen nach Status filtern
    
- **GET /api/v1/orders/trackings/code/{code}** – Tracking anhand des Codes abrufen
    

## API-Tests mit Postman

Die Datei Postman-Collection `uek295_testing.postman_collection.json` kann in Postman importiert werden, um die API-Endpunkte zu testen.

## Fehlerbehandlung

Sollte die Anwendung nicht starten:

- Prüfe, ob Docker läuft (`docker ps`)
    
- Stelle sicher, dass der Port `8080` nicht belegt ist
    
- Überprüfe die Log-Ausgabe mit `docker logs postgres_container`
