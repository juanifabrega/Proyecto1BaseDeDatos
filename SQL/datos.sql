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
VALUES ('abc123','Wolkswagen','Gol','Blanco',57387404);

INSERT INTO automoviles(patente,marca,modelo,color,dni)
VALUES ('tda987','Chevrolet','Corsa','Rojo',96385214);

INSERT INTO automoviles(patente,marca,modelo,color,dni)
VALUES ('bsd114','Fiat','Palio','Rojo',12345678);

INSERT INTO automoviles(patente,marca,modelo,color,dni)
VALUES ('dio101','Lamborghinni','Aventador','Naranja',14785296);

INSERT INTO automoviles(patente,marca,modelo,color,dni)
VALUES ('amb100','Reanult','Sandero','Gris',87654321);

#-----------------------------------------------------------------------------------------------
#Insercion en tipo_tarjetas

INSERT INTO tipos_tarjeta(tipo,descuento)
VALUES ('Basica','0.10');

INSERT INTO tipos_tarjeta(tipo,descuento)
VALUES ('Premium','0.50');

#-----------------------------------------------------------------------------------------------
#Insercion en tarjetas

INSERT INTO tarjetas(id_tarjeta,saldo,tipo,patente)
VALUES(1,999.99,'Basica','abc123');

INSERT INTO tarjetas(id_tarjeta,saldo,tipo,patente)
VALUES(2,999.99,'Basica','tda987');

INSERT INTO tarjetas(id_tarjeta,saldo,tipo,patente)
VALUES(3,999.99,'Basica','bsd114');

INSERT INTO tarjetas(id_tarjeta,saldo,tipo,patente)
VALUES(4,999.99,'Premium','dio101');

INSERT INTO tarjetas(id_tarjeta,saldo,tipo,patente)
VALUES(5,900.00,'Premium','amb100');

#-----------------------------------------------------------------------------------------------
#Insercion en inspectores

INSERT INTO inspectores(legajo,dni,nombre,apellido,password)
VALUES (4444,3571590,'Juan','Perez',md5('pepitoclavounclavito'));

INSERT INTO inspectores(legajo,dni,nombre,apellido,password)
VALUES (333,7539510,'Nahuel','Rodriguez',md5('pepeargento2020'));

#-----------------------------------------------------------------------------------------------
#Insercion en ubicaciones

INSERT INTO ubicaciones(calle,altura,tarifa)
VALUES ('Estomba','1200',10.00);

INSERT INTO ubicaciones(calle,altura,tarifa)
VALUES ('Estomba','1300',10.00);

INSERT INTO ubicaciones(calle,altura,tarifa)
VALUES ('Belgrano','300',10.00);

INSERT INTO ubicaciones(calle,altura,tarifa)
VALUES ('Alsina','100',10.00);

INSERT INTO ubicaciones(calle,altura,tarifa)
VALUES ('Rincon','100',10.00);

#-----------------------------------------------------------------------------------------------
#Insercion en parquimetros

INSERT INTO parquimetros(id_parq,numero,calle,altura)
VALUES (1,1,'Estomba','1200');

INSERT INTO parquimetros(id_parq,numero,calle,altura)
VALUES (2,2,'Belgrano','300');

INSERT INTO parquimetros(id_parq,numero,calle,altura)
VALUES (3,3,'Alsina','100');

INSERT INTO parquimetros(id_parq,numero,calle,altura)
VALUES (4,4,'Estomba','1200');

INSERT INTO parquimetros(id_parq,numero,calle,altura)
VALUES (5,5,'Rincon','100');

INSERT INTO parquimetros(id_parq,numero,calle,altura)
VALUES (6,6,'Rincon','100');

INSERT INTO parquimetros(id_parq,numero,calle,altura)
VALUES (7,7,'Estomba','1300');

#-----------------------------------------------------------------------------------------------
#Insercion en estacionamientos

INSERT INTO estacionamientos(id_tarjeta,id_parq,fecha_ent,hora_ent,fecha_sal,hora_sal)
VALUES (1,1,CURDATE(),CURTIME(),NULL,NULL);

INSERT INTO estacionamientos(id_tarjeta,id_parq,fecha_ent,hora_ent,fecha_sal,hora_sal)
VALUES (2,2,CURDATE(),CURTIME(),NULL,NULL);

#-----------------------------------------------------------------------------------------------
#Insercion en accede
INSERT INTO accede(legajo,id_parq,fecha,hora)
VALUES (4444,1,CURDATE(),CURTIME());

INSERT INTO accede(legajo,id_parq,fecha,hora)
VALUES (333,2,CURDATE(),CURTIME());

#-----------------------------------------------------------------------------------------------
#Insercion en asociado_con

# Legajo 4444
INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno)
VALUES (1,4444,'Belgrano','300','lu','M');

INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno)
VALUES (2,4444,'Belgrano','300','ma','M');

INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno)
VALUES (3,4444,'Belgrano','300','mi','M');

INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno)
VALUES (4,4444,'Belgrano','300','ju','M');

INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno)
VALUES (5,4444,'Belgrano','300','vi','M');

INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno)
VALUES (6,4444,'Belgrano','300','sa','M');

INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno)
VALUES (7,4444,'Belgrano','300','do','M');

# Legajo 333
INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno)
VALUES (8,333,'Estomba','1200','lu','T');

INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno)
VALUES (9,333,'Estomba','1200','ma','T');

INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno)
VALUES (10,333,'Estomba','1200','mi','T');

INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno)
VALUES (11,333,'Estomba','1200','ju','T');

INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno)
VALUES (12,333,'Estomba','1200','vi','T');

INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno)
VALUES (13,333,'Estomba','1200','sa','T');

INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno)
VALUES (14,333,'Estomba','1200','do','T');


INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno)
VALUES (15,333,'Alsina','100','lu','M');

INSERT INTO asociado_con(id_asociado_con,legajo,calle,altura,dia,turno)
VALUES (16,333,'Rincon','100','ma','M');

#-----------------------------------------------------------------------------------------------
#Insercion en multas

INSERT INTO multa(numero,fecha,hora,patente,id_asociado_con)
VALUES (1,'2018-12-09','17:00:00','dio101',1);

INSERT INTO multa(numero,fecha,hora,patente,id_asociado_con)
VALUES (2,'2019-10-01','20:30:59','dio101',2);

INSERT INTO multa(numero,fecha,hora,patente,id_asociado_con)
VALUES (3,'2001-6-19','18:45:00','dio101',3);
