-- TODO: Ajustar el readme para indicar como cargar el archivo (MAPA 2016-05-06)
begin;
set client_encoding = 'utf-8';

--Esta extensión es muy importante para el caso de uso Buscar Tutor
--Si por x o z razón les manda un error favor de buscar como
--instalarla en su computadora.
CREATE EXTENSION IF NOT EXISTS pg_trgm;

CREATE TABLE usuario (
	id_usuario serial PRIMARY KEY, -- Cambio el tipo por uno serial, el tipo se encarga de generar la secuencia de forma automática
	correo text UNIQUE NOT NULL check(correo SIMILAR TO '[0-9A-Za-z -_.áéíóúñü]+@%.%'),
	nombre_usuario text NOT NULL check(nombre_usuario SIMILAR TO '[A-Za-z]+'),
	app text NOT NULL CHECK(app SIMILAR TO '[A-Za-záéíóúñü]+'),
	apm text NOT NULL CHECK(apm SIMILAR TO '[A-Za-záéíóúñü]+'),
	contrasenia varchar(15) NOT NULL,
	telefono bigint CHECK(telefono <= 9999999999),
	acerca_de varchar(255),
	calificacion integer
);

comment on table usuario
is
'El usuario con nombre NOMBRE_USUARIO, apellido paterno
APELLIDO_PATERNO_USUARIO, apellido materno APELLIDO MATERNO tiene una contraseña
CONTRASENIA_USUARIO y telefono TELEFONO_USUARIO teniendo como información
adicional ACERCA_DE_USUARIO.';

CREATE TABLE alumno (
	id_usuario integer PRIMARY KEY REFERENCES usuario(id_usuario),
	fec_nac date NOT NULL CHECK( date_part('year',age(fec_nac)) >= 15 )
);

comment on table alumno
is
'El usuario ID_USUARIO es un alumno que con fecha de nacimiento
FECHA_NACIMIENTO_ALUMNO.';

--Con respecto al problema de los tutores, bastaba con quitarle
--la nularidad a la columna nivel_estudios_tutor
CREATE TABLE tutor (
	id_usuario integer PRIMARY KEY REFERENCES usuario(id_usuario),
	nivel_estudio text 
);

comment on table tutor
is
'El usuario ID_USUARIO es un tutor con nivel de estudios NIVEL_ESTUDIOS.';

CREATE TABLE materia (
	id_materia integer PRIMARY KEY,
	nombre_materia text NOT NULL,
	area_materia integer NOT NULL CHECK(area_materia >= 1 and area_materia <= 4)
);

comment on table materia
is
'La materia ID_MATERIA con nombre NOMBRE_MATERIA se encuentra en el área
AREA_MATERIA';

INSERT INTO materia(id_materia, nombre_materia, area_materia)
VALUES (1, 'Matemáticas', 1),
	   (2, 'Física', 1),
	   (3, 'Química', 2),
	   (4, 'Biología', 2),
	   (5, 'Informática', 1),
	   (6, 'Historia', 4),
	   (7, 'Geografía', 3),
	   (8, 'Literatura', 4),
	   (9, 'Derecho', 3);

CREATE TABLE tutor_materia (
	id_usuario integer NOT NULL REFERENCES tutor(id_usuario),
	id_materia integer NOT NULL REFERENCES materia(id_materia)
);

comment on table tutor_materia
is
'El tutor ID_USUARIO imparte la materia ID_MATERIA';

CREATE TABLE asesoria(
	id_asesoria serial PRIMARY KEY,
	costo integer NOT NULL,
	fec_asesoria date NOT NULL,
	direccion varchar NOT NULL,

	comentario varchar,
	calificacion_alumno integer,
	calificacion_tutor integer
);

CREATE TABLE solicitud(
	id_solicitud serial PRIMARY KEY,
	id_materia integer NOT NULL REFERENCES materia(id_materia),
	estado char NOT NULL,
	id_alumno integer NOT NULL REFERENCES alumno(id_usuario),
	id_tutor integer NOT NULL REFERENCES tutor(id_usuario),
	id_asesoria integer NOT NULL REFERENCES asesoria(id_asesoria)
);

--Función que regresa todos los tutores dado un nombre de una materia.
create or replace function buscatutor(v_nombre_materia text)
returns setof usuario as $$
  select usuario.*
  from usuario
    inner join tutor on (usuario.id_usuario = tutor.id_usuario)
    inner join tutor_materia on (usuario.id_usuario = tutor_materia.id_usuario)
    inner join materia on (tutor_materia.id_materia = materia.id_materia)
  where similarity(nombre_materia,v_nombre_materia) > 0.3;
$$ language sql stable;

comment on function buscatutor(v_nombre_materia text)
is
'Busca a todos los tutores que se encuentran impartiendo la materia
V_NOMBRE_MATERIA.';

commit;
