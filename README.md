# IOteayudo2.0
Repositorio para la aplicación IOteayudo, desarrollado con el grupo el
equipo Nextia en el curso de **Ingeniería de Software 2016-2**
impartido por **Hanna Oktaba, Isael Durán y Miguel Piña**.

Esta aplicación tiene como objetivo implementar economía compartida
poniendo en contacto a estudiantes de bachillerato con profesores
interesados en brindar asesorías del nivel.

## Configuración

Para cargar la base de datos hay que ejecutar el siguiente comando en
la terminal.

```sh
psql -U usuario -f createdb.sql
```

Una vez creada la base de datos hay que ejecutar el siguiente comando
que se encarga de crear las relaciones y funciones que van a ser
usadas dentro de la aplicación.

```sh
psql IOteayudo -U usuario -f ioteayudo.sql
```

## Integrantes del equipo

####Yoshua Ian Alfaro Mendoza
	Responsable de calidad / Desarrollador
####Luis Daniel López Monroy
	Responsable técnico / Desarrollador
####David Alejandro Nieto Andrade
	Responsable técnico / Desarrollador
####Manuel Soto Romero
    Responsable del equipo / Desarrollador
####Ricardo Taboada Magallanes
	Responsable de colaboración / Desarrollador
