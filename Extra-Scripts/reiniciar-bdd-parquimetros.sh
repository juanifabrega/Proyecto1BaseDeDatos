#!/bin/bash

# Bash script para utilizar en Linux
# Diferentes formas de resetear la base de datos de los parquimetros ejecutando solamente éste script.


# Seteamos las variables
USUARIO="root" # usuario de la bdd
CLAVE="<TU_CLAVE>" # clave de la bdd

BDD_BORRAR="/home/<TU_USUARIO>/.../borrarparquimetros.sql" # ruta para borrar la bdd
BDD_ESTRUCTURA="/home/<TU_USUARIO>/.../parquimetros.sql" # ruta para crear la bdd
BDD_DATOS="/home/<TU_USUARIO>/.../datos.sql" # ruta para cargar los datos a la bdd

LOG="/home/<TU_USUARIO>/log-mysql.txt" # ruta para guardar registro de las sentencias SQL's que se ejecuten



#----------------------------------------------------------------------------------------
# Primera forma (la mejor)
# Ventaja: dentro del bloque EOF se pueden ejecutar todas las sentencias SQL's que queramos

mysql -vvv --user=$USUARIO --password=$CLAVE > $LOG <<EOF
	source $BDD_BORRAR
	source $BDD_ESTRUCTURA
	source $BDD_DATOS
EOF



#----------------------------------------------------------------------------------------
# Segunda forma
# Ventaja: todo resuelto en 1 linea

#cat $BDD_BORRAR $BDD_ESTRUCTURA $BDD_DATOS | mysql -vvv --user=$USUARIO --password=$CLAVE > $LOG




#----------------------------------------------------------------------------------------
# Tercera forma
# Desventaja: Es mas ineficiente ya que hace 3 conexiones	

#mysql -vvv --user=$USUARIO --password=$CLAVE < $BDD_BORRAR > output1.txt
#mysql -vvv --user=$USUARIO --password=$CLAVE < $BDD_ESTRUCTURA > output2.txt
#mysql -vvv --user=$USUARIO --password=$CLAVE < $BDD_DATOS > output3.txt
