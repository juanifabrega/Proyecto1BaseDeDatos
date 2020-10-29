USE parquimetros;

#-----------------------------------------------------------------------------------------------
#Insercion en conductores

INSERT INTO conductores(dni,nombre,apellido,direccion,telefono,registro)
VALUES (57387404,'Lucas','Pratto','Bernabeu 912','9122018',1);

INSERT INTO conductores(dni,nombre,apellido,direccion,telefono,registro)
VALUES (96385214,'Juan','Quintero','Nuniez 129','2018129',2);

INSERT INTO conductores(dni,nombre,apellido,direccion,telefono,registro)
VALUES (12345678,'Gonzalo','Martinez','Alfaro 9','1292018',3);

INSERT INTO conductores(dni,nombre,apellido,direccion,telefono,registro)
VALUES (14785296,'Diego','Maradona','Mexico 86','4553366',4);

INSERT INTO conductores(dni,nombre,apellido,direccion,telefono,registro)
VALUES (87654321,'Lionel','Messi','Barcelona 2020','4516200',5);

#-----------------------------------------------------------------------------------------------
#Insercion en automoviles

INSERT INTO automoviles(patente,marca,modelo,color,dni)
VALUES ('ABC123','Wolkswagen','Gol','Blanco',57387404);

INSERT INTO automoviles(patente,marca,modelo,color,dni)
VALUES ('TDA987','Chevrolet','Corsa','Rojo',96385214);

INSERT INTO automoviles(patente,marca,modelo,color,dni)
VALUES ('BSD114','Fiat','Palio','Rojo',12345678);

INSERT INTO automoviles(patente,marca,modelo,color,dni)
VALUES ('D10S10','Lamborghinni','Aventador','Naranja',14785296);

INSERT INTO automoviles(patente,marca,modelo,color,dni)
VALUES ('AMB-10','Reanult','Sandero','Gris',87654321);

#-----------------------------------------------------------------------------------------------
#Insercion en tipo_tarjetas

INSERT INTO tipos_tarjeta(tipo,descuento)
VALUES ('Basica','0.01');

INSERT INTO tipos_tarjeta(tipo,descuento)
VALUES ('Premium','0.47');

#-----------------------------------------------------------------------------------------------
#Insercion en tarjetas

INSERT INTO tarjetas(id_tarjeta,saldo,tipo,patente)
VALUES(1,100.50,'Basica','ABC123');

INSERT INTO tarjetas(id_tarjeta,saldo,tipo,patente)
VALUES(2,70.26,'Basica','TDA987');

INSERT INTO tarjetas(id_tarjeta,saldo,tipo,patente)
VALUES(3,95.99,'Basica','BSD114');

INSERT INTO tarjetas(id_tarjeta,saldo,tipo,patente)
VALUES(4,999.99,'Premium','D10S10');

INSERT INTO tarjetas(id_tarjeta,saldo,tipo,patente)
VALUES(5,999.99,'Premium','AMB-10');

#-----------------------------------------------------------------------------------------------
#Insercion en inspectores

INSERT INTO inspectores(legajo,dni,nombre,apellido,password)
VALUES (4444,3571590,'Juan','Perez',md5('pepitoclavounclavito'));

INSERT INTO inspectores(legajo,dni,nombre,apellido,password)
VALUES (333,7539510,'Nahuel','Rodriguez',md5('pepeargento2020'));

#-----------------------------------------------------------------------------------------------
#Insercion en ubicaciones

INSERT INTO ubicaciones(calle,altura,tarifa)
VALUES ('Ramon y Cajal','1200',1.47);

INSERT INTO ubicaciones(calle,altura,tarifa)
VALUES ('Belgrano','300',5.75);

INSERT INTO ubicaciones(calle,altura,tarifa)
VALUES ('Alsina','100',8.38);

INSERT INTO ubicaciones(calle,altura,tarifa)
VALUES ('Rincon','100',80.38);

#-----------------------------------------------------------------------------------------------
#Insercion en parquimetros

INSERT INTO parquimetros(id_parq,numero,calle,altura)
VALUES (1,1,'Ramon y Cajal','1200');

INSERT INTO parquimetros(id_parq,numero,calle,altura)
VALUES (2,2,'Belgrano','300');

INSERT INTO parquimetros(id_parq,numero,calle,altura)
VALUES (3,3,'Alsina','100');

#-----------------------------------------------------------------------------------------------
#Insercion en estacionamientos

INSERT INTO estacionamientos(id_tarjeta,id_parq,fecha_ent,hora_ent,fecha_sal,hora_sal)
VALUES (1,1,CURTIME(),CURDATE(),NULL,NULL);

INSERT INTO estacionamientos(id_tarjeta,id_parq,fecha_ent,hora_ent,fecha_sal,hora_sal)
VALUES (2,2,CURTIME(),CURDATE(),CURTIME(),CURDATE());

INSERT INTO estacionamientos(id_tarjeta,id_parq,fecha_ent,hora_ent,fecha_sal,hora_sal)
VALUES (3,3,CURTIME(),CURDATE(),NULL,NULL);

INSERT INTO estacionamientos(id_tarjeta,id_parq,fecha_ent,hora_ent,fecha_sal,hora_sal)
VALUES (4,3,CURTIME(),'17:00:00',NULL,NULL);

#-----------------------------------------------------------------------------------------------
#Insercion en accede
INSERT INTO accede(legajo,id_parq,fecha,hora)
VALUES (4444,1,CURDATE(),CURTIME());

INSERT INTO accede(legajo,id_parq,fecha,hora)
VALUES (333,2,CURDATE(),CURTIME());

#-----------------------------------------------------------------------------------------------
#Insercion en asociado_con

INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno)
VALUES (1,4444,'Ramon y Cajal','1200','lu','M');

INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno)
VALUES (2,4444,'Belgrano','300','lu','T');

INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno)
VALUES (3,333,'Alsina','100','ma','M');

INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno)
VALUES (4,333,'Rincon','100','ma','T');

#-----------------------------------------------------------------------------------------------
#Insercion en multas

INSERT INTO multa(numero,fecha,hora,patente,id_asociado_con)
VALUES (1,'2018-12-09','17:00:00','D10S10',1);

INSERT INTO multa(numero,fecha,hora,patente,id_asociado_con)
VALUES (2,'2019-10-01','20:30:59','D10S10',2);

INSERT INTO multa(numero,fecha,hora,patente,id_asociado_con)
VALUES (3,'2001-6-19','18:45:00','D10S10',3);