# Creo de la Base de Datos
CREATE DATABASE parquimetros;

# selecciono la base de datos sobre la cual voy a hacer modificaciones
USE parquimetros;

#-------------------------------------------------------------------------
# Creación Tablas para las entidades

CREATE TABLE conductores(
	dni INT NOT NULL,
	nombre VARCHAR(45) NOT NULL,
	apellido VARCHAR(45) NOT NULL,
	direccion VARCHAR(45) NOT NULL,
	telefono VARCHAR(45) NOT NULL,
	registro INT NOT NULL,

	CONSTRAINT pk_conductores PRIMARY KEY (dni),

)ENGINE=InnoDB;

CREATE TABLE automoviles(
	patente VARCHAR(6) NOT NULL,
	marca VARCHAR(45) NOT NULL,
	modelo VARCHAR(45) NOT NULL,
	color VARCHAR(45) NOT NULL,
	dni INT NOT NULL,

	CONSTRAINT pk_automoviles PRIMARY KEY (patente),

	CONSTRAINT FK_automovil_conductores FOREIGN KEY (dni) REFERENCES conductores (dni),

)ENGINE=InnoDB;

CREATE TABLE tipos_tarjeta(
	tipo VARCHAR(45) NOT NULL,
	descuento FLOAT(3,2) NOT NULL, #float sin color

	CONSTRAINT pk_tipos_tarjeta PRIMARY KEY (tipo),

	CONSTRAINT chk_descuento CHECK (descuento<=1 AND descuento>=0),
	
)ENGINE=InnoDB;

CREATE TABLE tarjeta(
	id_tarjeta INT NOT NULL,
	saldo FLOAT(5,2) NOT NULL,
	tipo VARCHAR(45) NOT NULL,
	patente VARCHAR(6) NOT NULL,

	CONSTRAINT pk_tipos_tarjeta PRIMARY KEY(id_tarjeta),

	CONSTRAINT FK_tarjeta_tipos_tarjeta FOREIGN KEY (tipo) REFERENCES tipos_tarjeta(tipo),

	CONSTRAINT FK_tarjeta_automoviles FOREIGN KEY (patente) REFERENCES automoviles(patente),

)ENGINE=InnoDB;

CREATE TABLE inspectores(
	legajo INT NOT NULL,
	dni INT NOT NULL,
	nombre VARCHAR(45) NOT NULL,
	apellido VARCHAR(45) NOT NULL,
	password VARCHAR(32) NOT NULL,

	CONSTRAINT pk_inspectores PRIMARY KEY (legajo),

)ENGINE=InnoDB;

CREATE TABLE ubicaciones(
	calle VARCHAR(45) NOT NULL, 
	altura INT NOT NULL,
	tarifa FLOAT(5,2) NOT NULL,

	CONSTRAINT pk_ubicaciones PRIMARY KEY (calle,altura),

	CONSTRAINT chk_tarifa CHECK (tarifa>0),

)ENGINE=InnoDB;

CREATE TABLE parquimetros(
	id_parq INT NOT NULL, #es un numero natural ??? hago un chek >0?
	numero INT NOT NULL, #es un numero natural ???
	calle VARCHAR(45) NOT NULL,
	altura INT NOT NULL,

	CONSTRAINT pk_parquimetros PRIMARY KEY (id_parq),

	CONSTRAINT FK_parquimetros_ubicaciones FOREIGN KEY (calle,altura) REFERENCES ubicaciones(calle,altura),
	#calle y altura corresponden a una ubicacion DE ESTA MANERA ME ASEGURA QUE EXISTA EL PAR calle altura???

)ENGINE=InnoDB;

CREATE TABLE estacionamientos( #PREGUNTAR
	id_tarjeta INT NOT NULL,
	id_parq INT NOT NULL,
	fecha-ent DATE NOT NULL,
	hora-ent DATETIME NOT NULL,
	fecha-sal DATE NOT NULL,
	hora-sal DATETIME NOT NULL,

	CONSTRAINT pk_estacionamientos PRIMARY KEY(id_parq,fecha-ent,hora-ent),

	CONSTRAINT FK_estacionamientos_tarjeta FOREIGN KEY (id_tarjeta) REFERENCES tarjeta(id_tarjeta),

	CONSTRAINT FK_estacionamientos_parquimetros FOREIGN KEY (id_parq) REFERENCES parquimetros(id_parq),

)ENGINE=InnoDB;

CREATE TABLE accede(
	legajo INT NOT NULL,
	id_parq INT NOT NULL, #es un numero natural ??? hago un chek >0?
	fecha DATE NOT NULL,
	hora DATETIME NOT NULL,

	CONSTRAINT pk_accede PRIMARY KEY(id_parq,fecha,hora),

	CONSTRAINT FK_accede_inspectores FOREIGN KEY (legajo) REFERENCES inspectores(legajo),

	CONSTRAINT FK_accede_parquimetros FOREIGN KEY (id_parq) REFERENCES parquimetros(id_parq),

)ENGINE=InnoDB;

CREATE TABLE asociado_con(
	id_asociado_con INT NOT NULL,
	legajo INT NOT NULL,
	calle VARCHAR(45) NOT NULL,
	altura INT NOT NULL,
	dia ENUM('Lu','Ma','Mi', 'Ju','Vi' , 'Sa' , 'Do'), #esta bien???
	turno ENUM('M' , 'T'), #esta bien???

	CONSTRAINT pk_asociado_con PRIMARY KEY (id_asociado_con),

	CONSTRAINT FK_asociado_con_inspectores FOREIGN KEY (legajo) REFERENCES inspectores(legajo),

	CONSTRAINT FK_asociado_con_ubicaiones FOREIGN KEY (calle,altura) REFERENCES ubicaciones(calle,altura),

)ENGINE=InnoDB;

CREATE TABLE multa(
	numero INT NOT NULL,
	fecha DATE NOT NULL,
	hora DATETIME NOT NULL,
	patente VARCHAR(6) NOT NULL,
	id_asociado_con INT NOT NULL,

	CONSTRAINT pk_multa PRIMARY KEY (numero),

	CONSTRAINT FK_multa_automoviles FOREIGN KEY (patente) REFERENCES automoviles(patente),

	CONSTRAINT FK_multa_asociado_con FOREIGN KEY (id_asociado_con) REFERENCES asociado_con(id_asociado_con),

)ENGINE=InnoDB;
