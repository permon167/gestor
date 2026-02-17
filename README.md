# 📦 Gestor Backend  

Backend REST desarrollado en **Java 21** utilizando **Spring Boot**, **Hibernate (JPA)** y **PostgreSQL**.

El proyecto implementa la gestión de **clientes** y **pedidos**, aplicando buenas prácticas como separación por capas, uso de DTOs y control de validaciones.

---

# 🚀 Tecnologías utilizadas

- **Java 21**
- **Maven**
- **Spring Boot 3.4**
- **Spring Data JPA (Hibernate)**
- **PostgreSQL**
- **HikariCP (Connection Pool)**
- **Tomcat embebido**
- **Docker** (para base de datos)

---


Separación de responsabilidades:

- **Entity** → Modelo de persistencia
- **DTO** → Modelo de exposición en API
- **Repository** → Acceso a datos
- **Controller** → Capa REST
- **Main** → Configuración y arranque de aplicación

Esta estructura permite mantener desacopladas las capas y facilita el mantenimiento y la escalabilidad.

---

# 🗄 Modelo de datos

## Cliente

| Campo  | Tipo | Restricciones |
|---------|------|---------------|
| id      | Long | Primary Key, autogenerado |
| nombre  | String | NOT NULL |
| email   | String | NOT NULL, UNIQUE |

---

## Pedido

| Campo       | Tipo | Restricciones |
|------------|------|---------------|
| id         | Long | Primary Key, autogenerado |
| cliente_id | FK   | Relación ManyToOne con Cliente |
| total      | Decimal | >= 0 |

Relación: Cliente(1) --> (N) Pedido


# ⚙️ Configuración de Base de Datos

## Levantar PostgreSQL con Docker

```bash
docker run --name postgres-dev \
-e POSTGRES_PASSWORD=postgres \
-e POSTGRES_DB=empresa \
-p 5432:5432 \
-d postgres
```
---

# Ejecucion del proyecto
mvn clean spring-boot:run

# API REST
GET /api/health
POST /api/clientes
GET /api/clientes
POST /api/clientes/{clienteId}/pedidos
GET /api/clientes/{clienteId}/pedidos

# Decisiones Técnicas

## Uso de Hibernate (JPA)

Mapeo objeto-relacional mediante anotaciones.

Gestión automática de relaciones.

Generación y sincronización de esquema con ddl-auto: update.

## Uso de DTOs

No se devuelven entidades JPA directamente.

Motivos:

Evitar problemas derivados de fetch LAZY.

Prevenir ciclos infinitos en serialización JSON.

Desacoplar el modelo de base de datos del contrato de la API.

## HikariCP

Spring Boot integra HikariCP como pool de conexiones por defecto, lo que permite:

Reutilización eficiente de conexiones.

Mejor rendimiento.

Gestión automática del ciclo de vida de conexiones.

## Tomcat embebido

La aplicación se ejecuta sobre Tomcat embebido incluido en Spring Boot, permitiendo ejecutar el backend como aplicación standalone sin necesidad de despliegue manual en servidor externo.




