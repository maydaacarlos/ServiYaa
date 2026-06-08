# ServiYaa

Proyecto academico desarrollado por el grupo como propuesta de plataforma de servicios basada en arquitectura de microservicios con Spring Boot, Spring Cloud y Docker.

## Integrantes

- Mamani Quispe Mary Margoth
- Carlos Arotaype Mayda Rocio
- Tito Larico Juana
- Quispe Valencia Erick Maycol

## Descripcion General

ServiYaa busca organizar una solucion distribuida orientada a servicios para escenarios de autenticacion, gestion de usuarios, solicitudes, pagos, notificaciones y monitoreo. La propuesta se apoya en una base de infraestructura comun con configuracion centralizada, descubrimiento de servicios, API Gateway y un stack de observabilidad.

Este repositorio corresponde a una version importada y ordenada del trabajo del grupo. En el estado actual incluye:

- `infra/`: Config Server, Registry Server y API Gateway
- `observability/`: Prometheus, Loki, Promtail y Grafana
- `services/MS1/`: microservicio de autenticacion

Ademas, el `config-repo` dentro de `infra/` conserva configuraciones para otros microservicios del ecosistema ServiYaa, lo que deja preparada la base para continuar la integracion completa.

## Objetivos del Proyecto

- Implementar una arquitectura de microservicios con Spring Boot.
- Centralizar configuracion con Spring Cloud Config Server.
- Registrar y descubrir servicios con Eureka.
- Exponer el acceso unificado mediante API Gateway.
- Gestionar autenticacion con JWT.
- Preparar el sistema para observabilidad con Prometheus, Loki y Grafana.
- Facilitar despliegues en entornos `dev` y `prod` con Docker Compose.

## Arquitectura Resumida

```text
Clientes -> API Gateway -> Microservicios
                    |-> Config Server
                    |-> Eureka / Registry Server
                    |-> Observability Stack
```

Componentes principales:

- `Config Server`: distribuye configuracion por entorno.
- `Registry Server`: permite descubrimiento dinamico.
- `API Gateway`: concentra rutas de entrada, CORS y validacion JWT.
- `MS1 Auth`: autenticacion y emision de tokens.
- `Observability`: recoleccion de metricas y logs.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Cloud Config
- Spring Cloud Netflix Eureka
- Spring Cloud Gateway
- Spring Security con JWT
- Maven
- Docker y Docker Compose
- Prometheus
- Loki
- Promtail
- Grafana
- MySQL

## Estructura del Repositorio

```text
ServiYaa/
  infra/
    config-repo/
    config-server/
    gateway/
    registry-server/
  observability/
    grafana/
    loki/
    prometheus/
    promtail/
  services/
    MS1/
```

## Requisitos Previos

Antes de ejecutar el proyecto se recomienda contar con:

- Java 17
- Maven 3.9 o superior
- Docker Desktop
- Docker Compose
- Git

Comandos utiles de validacion:

```bash
java -version
mvn -version
docker version
docker compose version
git --version
```

## Ejecucion Rapida

### 1. Levantar infraestructura base

```bash
cd infra
docker compose up -d
```

Esto levanta:

- Config Server DEV y PROD
- Registry Server DEV y PROD
- Gateway DEV y PROD
- Bases MySQL asociadas al ecosistema

### 2. Verificar infraestructura

```bash
curl http://localhost:7071/ms-payment/dev
curl http://localhost:7072/ms-payment/prod
curl http://localhost:7091/actuator/health
curl http://localhost:7092/actuator/health
```

### 3. Levantar observabilidad

Entorno DEV:

```bash
cd observability
docker compose -f docker-compose-dev.yml up -d
```

Entorno PROD:

```bash
cd observability
docker compose up -d
```

### 4. Servicios de observacion

- Prometheus DEV: `http://localhost:19090`
- Prometheus PROD: `http://localhost:29090`
- Grafana DEV: `http://localhost:13000`
- Grafana PROD: `http://localhost:23000`

## Alcance del Estado Actual

Este snapshot del repositorio no representa aun el sistema completo en su version final. Actualmente el contenido funcional visible esta concentrado en:

- infraestructura compartida
- stack de observabilidad
- microservicio `MS1`

Sin embargo, el proyecto ya deja definida la base arquitectonica del ecosistema y evidencia la direccion tecnica del trabajo del grupo.

## Aporte del Grupo

Como equipo, este proyecto nos permitio consolidar aprendizaje en:

- diseno de soluciones distribuidas
- configuracion centralizada por entornos
- descubrimiento de servicios
- seguridad con JWT
- despliegue con contenedores
- monitoreo de metricas y logs

La propuesta de ServiYaa refleja el esfuerzo colaborativo del grupo por construir una solucion modular, escalable y alineada con los temas desarrollados durante el curso.

## Referencias Internas

- [Infraestructura](./infra/README.md)
- [Observabilidad](./observability/README.md)
- [MS1 Auth](./services/MS1/README.md)
