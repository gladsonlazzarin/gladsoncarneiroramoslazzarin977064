### Seletivo SEPLAG MT 2026

# Seletivo - API de Artistas e Álbuns

**Inscrição:** 16501  
**Candidato:** Gladson Carneiro Ramos Lazzarin

---

## Descrição do Projeto

API RESTful em **Spring Boot 3 / Java 21** que gerencia o relacionamento **Artista ↔ Álbum (N:N)**, utilizando:

- **PostgreSQL** para dados
- **MinIO** para armazenamento de arquivos
- Docker & Docker Compose para execução e orquestração

---

## Arquivo de Variáveis de Ambiente (`seletivo.env`)

```env
# PostgreSQL
POSTGRES_USER=postgres
POSTGRES_PASSWORD=123456
POSTGRES_DB=seletivo
POSTGRES_PORT=5432

# Spring Boot
SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/seletivo
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres

# MinIO
MINIO_ENDPOINT=http://minio:9000
MINIO_ACCESS_KEY=minioadmin
MINIO_SECRET_KEY=minioadmin
```

### Com Docker Compose

```
git clone https://github.com/gladsonlazzarin/gladsoncarneiroramoslazzarin977064.git

docker-compose up --build

```

### Swagger

> http://localhost:8080/swagger-ui/index.htm

Usuario administrador

nome: admin
senha: 123456

Usuario comum

nome: user
senha: 123456
