# 🦁 Granja / Zoo — CRUD con Spring Boot

Proyecto CRUD para la gestión de **Especies** y **Animales** con relación `OneToMany`.  
Incluye API REST (`@RestController`) e interfaz web con Thymeleaf (`@Controller`).

---

## 📋 Modelos

### Especie (padre)
| Campo | Tipo | Descripción |
|-------|------|-------------|
| id | Long | PK autoincremental |
| nombre | String | Nombre de la especie |
| reino | String | Reino (Animalia, Vegetal...) |
| habitat | String | Hábitat natural |
| peligro | Boolean | ¿En peligro de extinción? |

### Animal (hijo)
| Campo | Tipo | Descripción |
|-------|------|-------------|
| id | Long | PK autoincremental |
| nombre | String | Nombre del animal |
| sexo | String | M (Macho) o H (Hembra) |
| fechaNacimiento | LocalDate | Fecha de nacimiento |
| peso | Double | Peso en kg |
| recinto | String | Recinto asignado |
| especie | Especie | FK → especie_id |

---

## ✅ Requisitos

- Java 17+
- Maven 3.9+
- MySQL 8.0

---

## 🗄️ Configurar base de datos

Abrir MySQL y ejecutar:

```sql
CREATE DATABASE granjazoo;
```

Editar `src/main/resources/application.properties` si tu contraseña es diferente:

```properties
spring.datasource.username=root
spring.datasource.password=TU_CONTRASEÑA
```

---

## 🚀 Ejecutar el proyecto

```bash
cd granja-zoo
mvn spring-boot:run
```

Abrir en el navegador: **http://localhost:8080/especies**

---

## 🌐 Interfaz Web (Thymeleaf)

| Método | URL | Descripción |
|--------|-----|-------------|
| GET | `/especies` | Listado de especies |
| GET | `/especies/nuevo` | Formulario nueva especie |
| POST | `/especies/guardar` | Guardar especie |
| GET | `/especies/editar/{id}` | Formulario editar especie |
| GET | `/especies/eliminar/{id}` | Eliminar especie |
| GET | `/animales` | Listado de animales (con filtros) |
| GET | `/animales?sexo=M` | Filtrar por sexo |
| GET | `/animales?recinto=ZonaNorte` | Filtrar por recinto |
| GET | `/animales/nuevo` | Formulario nuevo animal |
| POST | `/animales/guardar` | Guardar animal |
| GET | `/animales/editar/{id}` | Formulario editar animal |
| GET | `/animales/eliminar/{id}` | Eliminar animal |

---

## 📡 API REST

### Especies — `/api/especies`

| Método | URL | Descripción |
|--------|-----|-------------|
| GET | `/api/especies` | Listar todas |
| GET | `/api/especies?nombre=Leon` | Filtrar por nombre |
| GET | `/api/especies/{id}` | Obtener por ID |
| POST | `/api/especies` | Crear especie |
| PUT | `/api/especies/{id}` | Actualizar especie |
| PATCH | `/api/especies/{id}` | Actualizar parcial |
| DELETE | `/api/especies/{id}` | Eliminar especie |

### Animales — `/api/animales`

| Método | URL | Descripción |
|--------|-----|-------------|
| GET | `/api/animales` | Listar todos |
| GET | `/api/animales?sexo=M` | Filtrar por sexo |
| GET | `/api/animales?recinto=ZonaNorte` | Filtrar por recinto |
| GET | `/api/animales?especieId=1` | Filtrar por especie |
| GET | `/api/animales/{id}` | Obtener por ID |
| POST | `/api/animales` | Crear animal |
| PUT | `/api/animales/{id}` | Actualizar animal |
| PATCH | `/api/animales/{id}` | Actualizar parcial |
| DELETE | `/api/animales/{id}` | Eliminar animal |

### Ejemplo POST crear especie

```json
POST /api/especies
Content-Type: application/json

{
  "nombre": "León",
  "reino": "Animalia",
  "habitat": "Sabana africana",
  "peligro": false
}
```

### Ejemplo POST crear animal

```json
POST /api/animales
Content-Type: application/json

{
  "nombre": "Simba",
  "sexo": "M",
  "fechaNacimiento": "2020-03-15",
  "peso": 190.5,
  "recinto": "Zona Norte",
  "especie": { "id": 1 }
}
```

---

## 🏗️ Estructura del proyecto

```
src/main/java/com/zoo/granjazoo/
├── model/
│   ├── Especie.java
│   └── Animal.java
├── repository/
│   ├── EspecieRepository.java
│   └── AnimalRepository.java
├── service/
│   ├── EspecieService.java
│   └── AnimalService.java
├── controller/
│   ├── EspecieController.java      (web)
│   ├── AnimalController.java       (web)
│   ├── EspecieApiController.java   (REST API)
│   └── AnimalApiController.java    (REST API)
└── GranjaZooApplication.java

src/main/resources/
├── templates/
│   ├── especies/
│   │   ├── lista.html
│   │   └── formulario.html
│   └── animales/
│       ├── lista.html
│       └── formulario.html
└── application.properties
```

---

## Puerto: 8080
