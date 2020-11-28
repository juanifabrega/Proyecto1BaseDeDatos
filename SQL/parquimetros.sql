#creo de la Base de Datos
CREATE DATABASE parquimetros;

#selecciono la base de datos sobre la cual voy a hacer modificaciones
USE parquimetros;

#-----------------------------------------------------------------------------------------------
#creación Tablas para las entidades

CREATE TABLE conductores(
	dni INT UNSIGNED NOT NULL,
	nombre VARCHAR(45) NOT NULL,
	apellido VARCHAR(45) NOT NULL,
	direccion VARCHAR(45) NOT NULL,
	telefono VARCHAR(45),
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
	descuento DECIMAL(3,2) UNSIGNED NOT NULL, 

	CONSTRAINT pk_tipos_tarjeta PRIMARY KEY (tipo),

	CONSTRAINT chk_descuento CHECK (descuento<=1 AND descuento>=0)
	
)ENGINE=InnoDB;

CREATE TABLE tarjetas(
	id_tarjeta INT UNSIGNED NOT NULL AUTO_INCREMENT,
	saldo DECIMAL(5,2) NOT NULL,
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
	tarifa DECIMAL(5,2) UNSIGNED NOT NULL,

	CONSTRAINT pk_ubicaciones PRIMARY KEY (calle,altura),

	CONSTRAINT chk_tarifa CHECK (tarifa>0)

)ENGINE=InnoDB;

CREATE TABLE parquimetros(
	id_parq INT UNSIGNED NOT NULL,
	numero INT UNSIGNED NOT NULL, 
	calle VARCHAR(45) NOT NULL,
	altura INT UNSIGNED NOT NULL,

	CONSTRAINT pk_parquimetros PRIMARY KEY (id_parq),
	#a la hora de borrar???
	CONSTRAINT FK_parquimetros_ubicaciones FOREIGN KEY (calle,altura) REFERENCES ubicaciones(calle,altura)

)ENGINE=InnoDB;

CREATE TABLE estacionamientos(
	id_tarjeta INT UNSIGNED NOT NULL,
	id_parq INT UNSIGNED NOT NULL,
	fecha_ent DATE NOT NULL,
	hora_ent TIME NOT NULL,
	fecha_sal DATE ,
	hora_sal TIME ,

	CONSTRAINT pk_estacionamientos PRIMARY KEY(id_parq,fecha_ent,hora_ent),
	#a la hora de borrar???
	CONSTRAINT FK_estacionamientos_tarjeta FOREIGN KEY (id_tarjeta) REFERENCES tarjetas(id_tarjeta)
		ON DELETE RESTRICT ON UPDATE CASCADE,
	#a la hora de borrar???
	CONSTRAINT FK_estacionamientos_parquimetros FOREIGN KEY (id_parq) REFERENCES parquimetros(id_parq)
		ON DELETE RESTRICT ON UPDATE CASCADE
	
)ENGINE=InnoDB;

CREATE TABLE accede(
	legajo INT UNSIGNED NOT NULL,
	id_parq INT UNSIGNED NOT NULL,
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
	id_asociado_con INT UNSIGNED NOT NULL AUTO_INCREMENT,
	legajo INT UNSIGNED NOT NULL,
	calle VARCHAR(45) NOT NULL,
	altura INT UNSIGNED NOT NULL,
	dia ENUM('do', 'lu', 'ma', 'mi', 'ju', 'vi', 'sa') NOT NULL,
	turno ENUM('M' , 'T') NOT NULL,

	CONSTRAINT pk_asociado_con PRIMARY KEY (id_asociado_con),
	#a la hora de borrar???
	CONSTRAINT FK_asociado_con_inspectores FOREIGN KEY (legajo) REFERENCES inspectores(legajo)
		ON DELETE RESTRICT ON UPDATE CASCADE,
	#a la hora de borrar???
	CONSTRAINT FK_asociado_con_ubicaiones FOREIGN KEY (calle,altura) REFERENCES ubicaciones(calle,altura)
		ON DELETE RESTRICT ON UPDATE CASCADE

)ENGINE=InnoDB;

CREATE TABLE multa(
	numero INT UNSIGNED NOT NULL AUTO_INCREMENT,
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

CREATE TABLE ventas(
	id_tarjeta INT UNSIGNED NOT NULL,
	tipo VARCHAR(45) NOT NULL,
	saldo DECIMAL(5,2) NOT NULL,
	fecha DATE NOT NULL,
	hora TIME NOT NULL,

	CONSTRAINT pk_ventas PRIMARY KEY (id_tarjeta),

	CONSTRAINT FK_ventas_tarjetas FOREIGN KEY (id_tarjeta) REFERENCES tarjetas(id_tarjeta)
		ON DELETE RESTRICT ON UPDATE CASCADE,

	CONSTRAINT FK_ventas_tipo FOREIGN KEY (tipo) REFERENCES tipos_tarjeta(tipo)
		ON DELETE RESTRICT ON UPDATE CASCADE

)ENGINE=InnoDB;

#-----------------------------------------------------------------------------------------------
#creacion de triggers

delimiter !
CREATE TRIGGER venta_tarjetas
AFTER INSERT ON tarjetas
FOR EACH ROW
BEGIN
	INSERT INTO ventas (id_tarjeta,tipo,saldo,fecha,hora)
	VALUES (NEW.id_tarjeta,NEW.tipo,NEW.saldo,curdate(),curtime());
END; !
delimiter ;

#-----------------------------------------------------------------------------------------------
#creacion del procedimiento conectar

delimiter !

CREATE PROCEDURE conectar(IN idtarjeta INTEGER,IN idparq INTEGER)
begin
	declare saldo_nuevo DECIMAL(5,2);
	declare id_estaciono INT;
	declare fecha_estaciono DATE;
	declare hora_estaciono TIME;
	declare saldo_actual DECIMAL(5,2);
	declare fecha_actual DATE;
	declare hora_actual TIME;
	declare tiempo_estacionamiento TIME;
	declare tarjeta_descuento DECIMAL(3,2);
	declare precio_minuto DECIMAL(5,2);

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
    	SELECT 'ERROR: Transaccion reestablecida' AS operacion;
        ROLLBACK;
    END;

	START TRANSACTION;

	#controlo si la tarjeta y el parquimetro existen
		if not(exists(SELECT * FROM tarjetas WHERE  id_tarjeta=idtarjeta) AND exists(SELECT * FROM parquimetros WHERE id_parq=idparq)) then
			SELECT "ERROR: La tarjeta y/o el parquimetros son incorrectos" AS operacion;
		else
			#inicializo las variables que voy a necesitar tanto para una apertura como para un cierre de estacionamiento
			SELECT saldo into saldo_actual FROM tarjetas WHERE id_tarjeta=idtarjeta;
				      	
	      	SELECT descuento into tarjeta_descuento FROM tarjetas NATURAL JOIN tipos_tarjeta WHERE id_tarjeta=idtarjeta;

	      	SET fecha_actual=CURRENT_DATE();
		        
		    SET hora_actual=CURRENT_TIME();

	      	#controlo si la tarjeta tiene un estacionamiento abierto
			if exists(SELECT * FROM estacionamientos WHERE id_tarjeta=idtarjeta AND (fecha_sal is NULL) AND (hora_sal is NULL)) then
				#se trata de un cierre de estacionamiento
				#inicializo y recupero valores que necesito para poder operar

				SELECT id_parq INTO id_estaciono FROM estacionamientos WHERE id_tarjeta=idtarjeta AND (fecha_sal is NULL) AND (hora_sal is NULL);
				
				SELECT tarifa INTO precio_minuto FROM (parquimetros NATURAL JOIN ubicaciones) WHERE id_parq=id_estaciono;

				SELECT fecha_ent into fecha_estaciono FROM estacionamientos WHERE id_tarjeta=idtarjeta AND id_parq=id_estaciono AND (fecha_sal is NULL) AND (hora_sal is NULL);
		        
		        SELECT hora_ent into hora_estaciono FROM estacionamientos WHERE id_tarjeta=idtarjeta AND id_parq=id_estaciono AND (fecha_sal is NULL) AND (hora_sal is NULL);
		        												
		        #cierro el estacionamiento
		        UPDATE estacionamientos SET fecha_sal=fecha_actual,hora_sal=hora_actual WHERE id_parq=id_estaciono AND id_tarjeta=idtarjeta AND fecha_ent=fecha_estaciono AND hora_ent=hora_estaciono;
		        
		        SET tiempo_estacionamiento=TIMEDIFF( TIMESTAMP(fecha_actual,hora_actual),TIMESTAMP(fecha_estaciono,hora_estaciono));
		        
		        SET saldo_nuevo=greatest(-999.99,(saldo_actual-((time_to_sec(tiempo_estacionamiento)/60)*precio_minuto*(1 - tarjeta_descuento)))) ;
		        
		        UPDATE tarjetas SET saldo=saldo_nuevo WHERE id_tarjeta=idtarjeta;
		        
		        #muestro la opecion
		        SELECT "cierre" AS operacion, time_to_sec(tiempo_estacionamiento)/60 AS tiempo_estacionado, saldo_nuevo AS saldo;
	   		else
	   			#se trata de una apertura de un estacionamiento
	   			SELECT tarifa into precio_minuto FROM parquimetros NATURAL JOIN ubicaciones WHERE id_parq=idparq;	

	   			#se controla que el saldo sea positivo
	   			if (saldo_actual)>0 then
	   				INSERT INTO estacionamientos VALUES(idtarjeta,idparq,fecha_actual,hora_actual,null,null);
	   				SELECT "apertura" AS operacion, "exitosa" AS resultado, saldo_actual/(precio_minuto*(1-tarjeta_descuento)) AS tiempo_disponible;
	   			else
	   				SELECT "aperturta" AS operacion, "inconclusa" AS resultado, "saldo insuficiente" AS motivo;
	   			end if;

	   		end if;

		end if;

	commit; 

end; !

delimiter ;

#-----------------------------------------------------------------------------------------------
#creacion de vistas

#creacion de la vista estacionados
CREATE VIEW estacionados AS
	SELECT calle,altura,patente
	FROM estacionamientos NATURAL JOIN parquimetros NATURAL JOIN tarjetas
	WHERE fecha_sal is NULL AND hora_sal is NULL;    

#-----------------------------------------------------------------------------------------------
#creacion de usuarios y privilegios

#creacion del usuario admin
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON parquimetros.* TO 'admin'@'localhost' WITH GRANT OPTION;

#creacion del usuario venta
CREATE USER 'venta'@'%' IDENTIFIED BY 'venta';
GRANT INSERT ON parquimetros.tarjetas TO 'venta'@'%';
GRANT SELECT ON parquimetros.tarjetas TO 'venta'@'%';
GRANT SELECT ON parquimetros.automoviles TO 'venta'@'%';
GRANT SELECT ON parquimetros.tipos_tarjeta TO 'venta'@'%';

#creacion del usuario inspector
CREATE USER 'inspector'@'%' IDENTIFIED BY 'inspector';
GRANT SELECT ON parquimetros.inspectores TO 'inspector'@'%';
GRANT INSERT ON parquimetros.multa TO 'inspector'@'%'; 
GRANT SELECT ON parquimetros.multa TO 'inspector'@'%'; 
GRANT SELECT ON parquimetros.estacionados TO 'inspector'@'%';
GRANT INSERT ON parquimetros.accede TO 'inspector'@'%';
GRANT SELECT ON parquimetros.parquimetros TO 'inspector'@'%';
GRANT SELECT ON parquimetros.asociado_con TO 'inspector'@'%';
GRANT SELECT ON parquimetros.ubicaciones TO 'inspector'@'%';

#creacion del usuario parquimetro
CREATE USER 'parquimetro'@'%' IDENTIFIED BY 'parq';
GRANT SELECT ON parquimetros.ubicaciones TO 'parquimetro'@'%';
GRANT SELECT ON parquimetros.parquimetros TO 'parquimetro'@'%';
GRANT SELECT ON parquimetros.tarjetas TO 'parquimetro'@'%';
GRANT execute on procedure parquimetros.conectar TO 'parquimetro'@'%';
