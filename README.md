# ServiYaa

ServiYaa es una propuesta academica de sistema distribuido construida por nuestro grupo para aplicar los conceptos de microservicios, configuracion centralizada, descubrimiento de servicios, gateway, seguridad y observabilidad.

## Equipo de Trabajo

- Mamani Quispe Mary Margoth
- Carlos Arotaype Mayda Rocio
- Tito Larico Juana
- Quispe Valencia Erick Maycol

## Vision del Proyecto

El objetivo de ServiYaa es organizar una arquitectura modular orientada a servicios que permita separar responsabilidades del sistema y facilitar el despliegue por componentes. La solucion se apoya en Spring Boot, Spring Cloud y Docker para construir una base distribuida preparada para crecer por modulos.

## Contenido Actual del Repositorio

En esta version del repositorio se conserva la base tecnica del proyecto en los siguientes modulos:

- `infra/`
  Infraestructura comun con Config Server, Registry Server y API Gateway.
- `observability/`
  Stack de monitoreo y trazabilidad con Prometheus, Loki, Promtail y Grafana.
- `services/MS1/`
  Microservicio de autenticacion `ms-auth`.

Adicionalmente, el `config-repo` de infraestructura mantiene configuraciones para otros microservicios del ecosistema, lo que deja preparada la continuidad del proyecto.

## Objetivos Tecnicos

- Implementar una arquitectura basada en microservicios.
- Centralizar configuraciones por entorno.
- Registrar y descubrir servicios de forma dinamica.
- Exponer los accesos mediante API Gateway.
- Gestionar autenticacion con JWT.
- Habilitar observabilidad mediante metricas y logs.
- Facilitar despliegues en entornos `dev` y `prod`.

## Arquitectura General

```text
Clientes -> API Gateway -> Microservicios
                    |-> Config Server
                    |-> Eureka / Registry Server
                    |-> Observability Stack
```

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Cloud Config
- Spring Cloud Netflix Eureka
- Spring Cloud Gateway
- Spring Security con JWT
- Maven
- Docker
- Docker Compose
- MySQL
- Prometheus
- Loki
- Promtail
- Grafana

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

- Java 17
- Maven 3.9+
- Docker Desktop
- Docker Compose
- Git

Validacion sugerida:

```bash
java -version
mvn -version
docker version
docker compose version
git --version
```

## Ejecucion Base

### Infraestructura

```bash
cd infra
docker compose up -d
```

### Validaciones minimas

```bash
curl http://localhost:7071/ms-payment/dev
curl http://localhost:7072/ms-payment/prod
curl http://localhost:7091/actuator/health
curl http://localhost:7092/actuator/health
```

### Observabilidad

DEV:

```bash
cd observability
docker compose -f docker-compose-dev.yml up -d
```

PROD:

```bash
cd observability
docker compose up -d
```

## Aprendizaje del Grupo

Este proyecto nos permitio reforzar conocimientos sobre:

- diseno de sistemas distribuidos
- configuracion centralizada por entornos
- descubrimiento de servicios
- seguridad con JWT
- despliegue con contenedores
- monitoreo de metricas y logs

## Referencias Internas

- [Infraestructura](./infra/README.md)
- [Observabilidad](./observability/README.md)
- [MS1 Auth](./services/MS1/README.md)
