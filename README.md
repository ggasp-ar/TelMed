# TelMed

Repositorio de la plataforma de teleconsultas medicas

Creado en la cursada de las materias Análisis y Diseño de Sistemas, Practica profesional y Legislación, y Programación Orientada a Objetos.

## Configuracion

Reemplazar en el archivo TelMed/src/main/resources/application.properties la configuracion hacia una base de datos valida.

```bash
spring.datasource.url=jdbc:postgresql://192.168.0.121:5432/TelMed
spring.datasource.username=root
spring.datasource.password=root
```

## Ejecucion
Ejecutar desde la clase DemoApplication

La url "{hostname}/usertest" genera los tres tipos de usuarios disponibles y las muestra en la consola de la aplicacion

La url "{hostname}/fjh" generea franjas horarias al medico disponible y asigna turnos de testeo para ver en el historial de turnos (paciente) y consultas (medico)
