# Creo de la Base de Datos
CREATE DATABASE parquimetros;

# selecciono la base de datos sobre la cual voy a hacer modificaciones
USE parquimetros;

#-----------------------------------------------------------------------------------------------
# Creación Tablas para las entidades

CREATE TABLE conductores(
	dni INT UNSIGNED NOT NULL,
	nombre VARCHAR(45) NOT NULL,
	apellido VARCHAR(45) NOT NULL,
	direccion VARCHAR(45) NOT NULL,
	telefono VARCHAR(45) NOT NULL,
	registro INT UNSIGNED NOT NULL,

	CONSTRAINT pk_conductores PRIMARY KEY (dni)

)ENGINE=InnoDB;

CREATE TABLE automoviles(
	patente VARCHAR(6) NOT NULL,
	marca VARCHAR(45) NOT NULL,
	modelo VARCHAR(45) NOT NULL,
	color VARCHAR(45) NOT NULL,
	dni INT UNSIGNED NOT NULL,

	CONSTRAINT pk_automoviles PRIMARY KEY (patente),
	#ver a la hora de borrar
	CONSTRAINT FK_automovil_conductores FOREIGN KEY (dni) REFERENCES conductores (dni)
		ON DELETE RESTRICT ON UPDATE CASCADE

)ENGINE=InnoDB;

CREATE TABLE tipos_tarjeta(
	tipo VARCHAR(45) NOT NULL,
	descuento FLOAT(3,2) NOT NULL, 

	CONSTRAINT pk_tipos_tarjeta PRIMARY KEY (tipo),

	CONSTRAINT chk_descuento CHECK (descuento<=1 AND descuento>=0)
	
)ENGINE=InnoDB;

CREATE TABLE tarjeta(
	id_tarjeta INT UNSIGNED NOT NULL,
	saldo FLOAT(5,2) NOT NULL,
	tipo VARCHAR(45) NOT NULL,
	patente VARCHAR(6) NOT NULL,

	CONSTRAINT pk_tipos_tarjeta PRIMARY KEY(id_tarjeta),
	#a la hora de borrar???
	CONSTRAINT FK_tarjeta_tipos_tarjeta FOREIGN KEY (tipo) REFERENCES tipos_tarjeta(tipo),
	#a la hora de borrar???
	CONSTRAINT FK_tarjeta_automoviles FOREIGN KEY (patente) REFERENCES automoviles(patente)
		ON DELETE RESTRICT ON UPDATE CASCADE

)ENGINE=InnoDB;

CREATE TABLE inspectores(
	legajo INT UNSIGNED NOT NULL,
	dni INT UNSIGNED NOT NULL,
	nombre VARCHAR(45) NOT NULL,
	apellido VARCHAR(45) NOT NULL,
	password VARCHAR(32) NOT NULL,

	CONSTRAINT pk_inspectores PRIMARY KEY (legajo)

)ENGINE=InnoDB;

CREATE TABLE ubicaciones(
	calle VARCHAR(45) NOT NULL, 
	altura INT UNSIGNED NOT NULL,
	tarifa FLOAT(5,2) NOT NULL,

	CONSTRAINT pk_ubicaciones PRIMARY KEY (calle,altura),

	CONSTRAINT chk_tarifa CHECK (tarifa>0)

)ENGINE=InnoDB;

CREATE TABLE parquimetros(
	id_parq INT NOT NULL,
	numero INT NOT NULL, 
	calle VARCHAR(45) NOT NULL,
	altura INT UNSIGNED NOT NULL,

	CONSTRAINT pk_parquimetros PRIMARY KEY (id_parq),
	#a la hora de borrar???
	CONSTRAINT FK_parquimetros_ubicaciones FOREIGN KEY (calle,altura) REFERENCES ubicaciones(calle,altura)

)ENGINE=InnoDB;

CREATE TABLE estacionamientos( #PREGUNTAR
	id_tarjeta INT UNSIGNED NOT NULL,
	id_parq INT NOT NULL,
	fecha_ent DATE NOT NULL,
	hora_ent TIME NOT NULL,
	fecha_sal DATE ,
	hora_sal TIME ,

	CONSTRAINT pk_estacionamientos PRIMARY KEY(id_parq,fecha_ent,hora_ent),
	#a la hora de borrar???
	CONSTRAINT FK_estacionamientos_tarjeta FOREIGN KEY (id_tarjeta) REFERENCES tarjeta(id_tarjeta)
		ON DELETE RESTRICT ON UPDATE CASCADE,
	#a la hora de borrar???
	CONSTRAINT FK_estacionamientos_parquimetros FOREIGN KEY (id_parq) REFERENCES parquimetros(id_parq)
		ON DELETE RESTRICT ON UPDATE CASCADE
	
)ENGINE=InnoDB;

CREATE TABLE accede(
	legajo INT UNSIGNED NOT NULL,
	id_parq INT NOT NULL, #es un numero natural ??? hago un chek >0?
	fecha DATE NOT NULL,
	hora TIME NOT NULL,

	CONSTRAINT pk_accede PRIMARY KEY(id_parq,fecha,hora),
	#a la hora de borrar???
	CONSTRAINT FK_accede_inspectores FOREIGN KEY (legajo) REFERENCES inspectores(legajo)
		ON DELETE RESTRICT ON UPDATE CASCADE,
	#a la hora de borrar???
	CONSTRAINT FK_accede_parquimetros FOREIGN KEY (id_parq) REFERENCES parquimetros(id_parq)
		ON DELETE RESTRICT ON UPDATE CASCADE

)ENGINE=InnoDB;

CREATE TABLE asociado_con(
	id_asociado_con INT UNSIGNED NOT NULL,
	legajo INT UNSIGNED NOT NULL,
	calle VARCHAR(45) NOT NULL,
	altura INT UNSIGNED NOT NULL,
	dia ENUM('Lu','Ma','Mi', 'Ju','Vi' , 'Sa' , 'Do'),
	turno ENUM('M' , 'T'),

	CONSTRAINT pk_asociado_con PRIMARY KEY (id_asociado_con),
	#a la hora de borrar???
	CONSTRAINT FK_asociado_con_inspectores FOREIGN KEY (legajo) REFERENCES inspectores(legajo)
		ON DELETE RESTRICT ON UPDATE CASCADE,
	#a la hora de borrar???
	CONSTRAINT FK_asociado_con_ubicaiones FOREIGN KEY (calle,altura) REFERENCES ubicaciones(calle,altura)
		ON DELETE RESTRICT ON UPDATE CASCADE

)ENGINE=InnoDB;

CREATE TABLE multa(
	numero INT UNSIGNED NOT NULL,
	fecha DATE NOT NULL,
	hora TIME NOT NULL,
	patente VARCHAR(6) NOT NULL,
	id_asociado_con INT UNSIGNED NOT NULL,

	CONSTRAINT pk_multa PRIMARY KEY (numero),

	CONSTRAINT FK_multa_automoviles FOREIGN KEY (patente) REFERENCES automoviles(patente)
		ON DELETE RESTRICT ON UPDATE CASCADE,

	CONSTRAINT FK_multa_asociado_con FOREIGN KEY (id_asociado_con) REFERENCES asociado_con(id_asociado_con)
		ON DELETE RESTRICT ON UPDATE CASCADE

)ENGINE=InnoDB;

#-----------------------------------------------------------------------------------------------
#Creacion de vistas

#creacion de la vista estacionados
CREATE VIEW estacionados AS
	SELECT calle,altura
	FROM estacionamientos NATURAL JOIN parquimetros NATURAL JOIN tarjeta
	WHERE fecha_sal is NULL AND hora_sal is NULL;    

#-----------------------------------------------------------------------------------------------
#Creacion de usuarios y privilegios

#creacion del usuario admin
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON parquimetros.* TO 'admin'@'localhost' WITH GRANT OPTION;

#creacion del usuario venta
CREATE USER 'venta'@'%' IDENTIFIED BY 'venta';
GRANT INSERT ON parquimetros.tarjeta TO 'venta'@'%';

#creacion del usuario inspector
CREATE USER 'inspector'@'%' IDENTIFIED BY 'inspector';
GRANT SELECT ON parquimetros.estacionados TO 'inspector'@'%';
GRANT INSERT ON parquimetros.multa TO 'inspector'@'%';
GRANT INSERT ON parquimetros.accede TO 'inspector'@'%';
