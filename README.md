# 🧠 NeuroKids

O **NeuroKids** é uma plataforma educativa e interativa desenvolvida especialmente para crianças neurodivergentes, oferecendo um ambiente acolhedor, acessível e divertido para o aprendizado.

A plataforma combina educação e entretenimento por meio de jogos, atividades lúdicas, desafios cognitivos e conteúdos adaptados, estimulando o desenvolvimento das habilidades cognitivas, emocionais e sociais de cada criança de forma personalizada.

---

# 🎯 Objetivo do Projeto

O NeuroKids foi criado com o objetivo de auxiliar crianças neurodivergentes no processo de aprendizagem através da tecnologia, promovendo inclusão, acessibilidade e desenvolvimento educacional de forma interativa e dinâmica.

---

# 🚀 Tecnologias Utilizadas

- Java 21 LTS
- Spring Boot 3.3.5
- Spring Data JPA 3.3.5
- Spring Web 6.1
- MySQL
- Swagger / OpenAPI
- Apache Maven
- Git & GitHub
- Postman
- IntelliJ IDEA
- Trello

---

# ⚙️ Funcionalidades

✅ API REST com Spring Boot  
✅ Integração com banco de dados MySQL  
✅ Operações CRUD  
✅ Documentação Swagger/OpenAPI  
✅ Organização em camadas (Controller, Service e Repository)  
✅ Versionamento com Git/GitHub  
✅ Testes de API com Postman  

---

# 📦 Como Executar o Projeto

## 1️⃣ Clone o repositório

```bash
git clone https://github.com/JoaoHenrique2109/NeuroKids.git
```

## 2️⃣ Entre na pasta do projeto

```bash
cd NeuroKids
```

## 3️⃣ Configure o banco de dados

Crie o banco no MySQL:

```sql
CREATE DATABASE neurokids;
```

---

# 🔧 Configuração do application.properties

Localização:

```bash
src/main/resources/application.properties
```

Configure:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/neurokids
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8080
```

---

# ▶️ Executar o Projeto

Execute no terminal:

```bash
mvn clean install
mvn spring-boot:run
```

Ou execute diretamente pela IDE.

---

# 🌐 Acessos

## API

```bash
http://localhost:8080
```

## Swagger

```bash
http://localhost:8080/swagger-ui/index.html
```

---

# 🧪 Testes da API

Os testes podem ser realizados utilizando o Postman.

---

# 👨‍💻 Equipe de Desenvolvimento

- Victor Gabriel
- Maria Karolayne
- José Rafael
- Gleysson Robert
- Victor Kauã
- João Bezerra
- Ruan Henry

---

# 📚 Motivação

Muitas crianças neurodivergentes enfrentam dificuldades no aprendizado tradicional devido à falta de recursos adaptados às suas necessidades cognitivas, sensoriais e comportamentais.

O NeuroKids surge como uma solução inclusiva, utilizando tecnologia para transformar o aprendizado em uma experiência divertida, acessível e acolhedora.
