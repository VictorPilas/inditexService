# Inditex Prices API

API REST para la gestión y consulta de precios de productos, desarrollado en Java (Spring Boot) y desplegable con Docker.

---

## 💡 Características

- Java 17 + Spring Boot
- Exposición de endpoint `/prices` con filtros por fecha, producto y marca.
- Docker y Docker Compose configurados.
- Tests End-to-End (E2E) disponibles vía Postman.
- Documentación interactiva disponible en Swagger UI.

---

## 📦 Construcción y despliegue con Docker

### 1. **Construir la imagen Docker**

```bash
docker build -t victor220686/inditex-service:latest .
```

### 2. **Subir la imagen a DockerHub**

```bash
docker push victor220686/inditex-service:latest
```

---

## 🛠️ Levantar servicio con Docker Compose

### 1. **Variables configurables en `.env`**

```env
DOCKERHUB_USER=victor220686
IMAGE_TAG=latest
APP_PORT=8080
JAVA_OPTS=-Xms512m -Xmx1024m
```

### 2. **Ejecutar Docker Compose**

```bash
docker-compose --env-file .env up -d
```

La aplicación estará disponible en:
```
http://localhost:8080/prices
```

---

## 📈 Acceso a Swagger UI

Una vez el servicio esté levantado, puedes acceder a la documentación interactiva de la API en:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 📚 Compilar y ejecutar el proyecto manualmente

### 1. **Clonar el repositorio y moverse al proyecto**

```bash
git clone <URL_REPOSITORIO>
cd <directorio-proyecto>
```

### 2. **Compilar el proyecto**

```bash
./mvnw clean install
```

### 3. **Ejecutar la aplicación**

```bash
./mvnw spring-boot:run
```

Por defecto, el servicio quedará expuesto en:
```
http://localhost:8080
```

---

## 💡 Uso del Endpoint

### **GET `/prices`**

**Parámetros opcionales:**

| Parámetro  | Tipo    | Descripción                      |
|-----------|--------|----------------------------------|
| `date`    | String | Fecha en formato `yyyy-MM-dd-HH.mm.ss` |
| `productId` | Integer | ID del producto                 |
| `brandId` | Integer | ID de la marca                  |

**Ejemplo:**

```
GET http://localhost:8080/prices?date=2020-06-16-21.00.00&productId=35455&brandId=1
```

---

## 🔧 Test End-to-End con Postman

### 1. **Importar colección Postman**

Descarga la colección actualizada:

[Descargar Inditex Postman Collection](sandbox:/mnt/data/inditex_postman_collection_updated.json)

Importa en Postman para ejecutar dos escenarios:

- **Get Prices - Success Case**
- **Get Prices - No Results**

### 2. **Ejecutar con Newman (CLI)**

```bash
newman run inditex_postman_collection_updated.json
```

---

## 🛠️ Tecnologías usadas

- Java 17
- Spring Boot
- Docker & Docker Compose
- Postman & Newman
- Swagger UI

---

## 🌟 Autor

**Victor (victor220686)**

DockerHub: [https://hub.docker.com/u/victor220686](https://hub.docker.com/u/victor220686)

