# Superheroes API Project

## Prueba Técnica: Perfil DevOps

This project demonstrates a DevOps technical test involving cloning, dockerizing, deploying, synchronizing, and extending an API with a Python script. Below are the detailed steps and instructions to reproduce the setup.

## Cloning the Repository

1. Clone the repository:

   ```bash
   git clone https://github.com/friveradev/superheros
   cd superheros

2. Dockerizado de la app

   Este proyecto utiliza un contenedor Docker para ejecutar una aplicación Java basada en el archivo `superheros.jar`. La configuración de Docker se realiza mediante un archivo Dockerfile, el cual está descrito a continuación.
   
   ## Dockerfile
   
   El Dockerfile se utiliza para crear una imagen de Docker que contiene la aplicación Java. A continuación se explica cada línea del Dockerfile:
   
   ```dockerfile
   # Utiliza una imagen oficial de OpenJDK como imagen base
   FROM openjdk:17-jdk-slim
   
   # Establece el directorio de trabajo en el contenedor
   WORKDIR /app
   
   # Copia el archivo JAR en el contenedor
   COPY target/superheros.jar app.jar
   
   # Expone el puerto 8080 al exterior del contenedor
   EXPOSE 8080
   
   # Ejecuta el archivo JAR
   ENTRYPOINT ["java", "-jar", "app.jar"]
   ```

   El archivo yml incluye las relaciones entre la base de datos creada (superheroes) usando mysql8 al igual que las relaciones con el contenedor que se encargara de ejecutar el script de python usado en el 4 punto de la presente prueba
   
      ```yaml
      version: '3.8'
      
      services:
        db:
          image: mysql:8
          environment:
            MYSQL_DATABASE: superheroes
            MYSQL_USER: username
            MYSQL_PASSWORD: password
            MYSQL_ROOT_PASSWORD: password
          ports:
            - "3306:3306"
      
        app:
          build: .
          ports:
            - "8080:8080"
          environment:
            SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/superheroes
            SPRING_DATASOURCE_USERNAME: username
            SPRING_DATASOURCE_PASSWORD: password
          depends_on:
            - db
      
        fetch_superheroes:
          build:
            context: .
            dockerfile: Dockerfile.python
          depends_on:
            - app
   ```
       
3. Sincronización aws bucket
   La sincronización entre el bucket de AWS S3 y el directorio local se realiza utilizando el comando `aws s3 sync`. Este comando asegura que el contenido del directorio local se    
   sincronice con el bucket de S3.
   
   ### Código de Sincronización
   
   El siguiente comando se utiliza para sincronizar el directorio local con el bucket de S3:
   
   ```sh
   aws s3 sync /home/difegopa/cloudlabs_learning_project/superheros s3://superheros-bucket --delete
   ```

# Ejecución Proyecto Superhéroes

Este proyecto tiene como objetivo desplegar un conjunto de servicios en contenedores Docker para gestionar y obtener información sobre superhéroes. Incluye un servicio de base de datos MySQL, una aplicación principal y un script Python para obtener y mostrar los superhéroes desde la API.

## Estructura del Proyecto

El proyecto se compone de los siguientes servicios:

1. **db**: Servicio de base de datos MySQL.
2. **app**: Aplicación principal que sirve la API de superhéroes.
3. **fetch_superheroes**: Script Python que consume la API de superhéroes y muestra la información.

## Requisitos Previos

- Docker instalado en tu máquina.
- Docker Compose instalado.

## Archivos del Proyecto

- **Dockerfile.python**: Dockerfile para construir la imagen del script Python.
- **docker-compose.yml**: Archivo de configuración de Docker Compose.
- **fetch_superheroes.py**: Script Python para obtener y mostrar la información de los superhéroes.
- **requirements.txt**: Archivo de dependencias para el script Python.

## Instrucciones

### Paso 1: Clonar el Repositorio

1. **Clonar el repositorio**:
    ```sh
    git clone https://github.com/diefgonzalezpac/proyecto-superheroes.git
    cd proyecto-superheroes
    ```

### Paso 2: Crear la Red de Docker

2. **Crear la red de Docker**:
    ```sh
    docker network create superheroes-network
    ```

### Paso 3: Construir y Levantar los Servicios

3. **Construir y levantar los servicios**:
    ```sh
    docker-compose up -d
    ```

    Esto construirá las imágenes y levantará los contenedores definidos en el archivo `docker-compose.yml`.

### Paso 4: Ejecutar el Contenedor de `fetch_superheroes`

4. **Ejecutar el contenedor `fetch_superheroes`**:
    ```sh
    docker-compose run fetch_superheroes
    ```

### Paso 5: Verificar los Logs

5. **Verificar los logs**:
    ```sh
    docker logs nombre_del_contenedor_fetch_superheroes
    ```

    Esto mostrará la información de los superhéroes en los logs del contenedor `fetch_superheroes`.

### Opción 2: Descargar desde DockerHub

Si prefieres descargar las imágenes desde DockerHub, sigue estos pasos:

#### Paso 1: Crear la Red de Docker

1. **Crear la red de Docker**:
    ```sh
    docker network create superheroes-network
    ```

#### Paso 2: Descargar y Levantar los Servicios

2. **Descargar y levantar los servicios**:
    ```sh
    docker-compose -f docker-compose.yml up -d
    ```

    Asegúrate de que tu `docker-compose.yml` esté configurado para usar las imágenes de DockerHub:


#### Paso 3: Ejecutar el Contenedor de `fetch_superheroes`

3. **Ejecutar el contenedor `fetch_superheroes`**:
    ```sh
    docker-compose run fetch_superheroes
    ```

#### Paso 4: Verificar los Logs

4. **Verificar los logs**:
    ```sh
    docker logs nombre_del_contenedor_fetch_superheroes
    ```

    Esto mostrará la información de los superhéroes en los logs del contenedor `fetch_superheroes`.

### Notas Adicionales

- Asegúrate de que los puertos `3306` y `8080` no estén siendo utilizados por otros servicios en tu máquina.
- Puedes modificar las variables de entorno en el archivo `docker-compose.yml` según tus necesidades.
- Si deseas detener y eliminar los contenedores, usa el comando:
    ```sh
    docker-compose down
    ```

