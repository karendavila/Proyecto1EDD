Proyecto I - Estructuras de Datos
Darío Aldana y Karen Dávila 

			APPmistad

-------------
NOTAS: 
1) La implementación de la representación gráfica de la matriz de adyacencia fue realizada mediante las librerías de Graph Stream. Son cuatro archivos los requeridos para que el programa sea ejecutado idealmente: 
	* gs-algo-2.0
	* gs-ui-javafx-2.0
	* gs-ui-swing-2.0 
	* gs-core-2.0

   Tales archivos deben ser descargados (fueron enviados con el programa) y deben ser activados desde NetBeans para que corra el programa sin inconvenientes.  
   La manera de activarlos es descargar y descomprimir el zip con los cuatro archivos y después desde NetBeans ir al proyecto y acceder a sus propiedades. Después: 
	Librerías >> Classpath >> Add Jar/Folder 
	y seleccionar los cuatro archivos recién descargados. 

2) El archivo inicial, que se usa como base de datos, debe ser descargado y tenerse como txt en la computadora donde se vaya a ejecutar la aplicación, para que se pueda encontrar con el JFileChooser. Fue adjuntado bajo el nombre de "Base de Datos". 
---------------

¿Qué es APPmistad?
r: Aplicación que toma una base de datos con un formato específico con información sobre usuarios y las relaciones entre ellos y posee la facultad de analizar ciertos parámetros sobre ella. 

¿Cómo se maneja?
r: Cuatro botones iniciales:
	* Cargar Archivo
	* Determinar Cantidad de Islas
	* Identificar Puentes
	* Modificar Grafo

Al principio sólo el primero permite explorar, pues, se requiere inicialmente que se cargue el archivo a analizar. Posteriormente a eso, las acciones del segundo botón prelan a las del tercero sólo la primera vez que se ejecuten, pues, los métodos al Determinar Cantidad de Islas son los que permiten que el botón de Identificar Puentes haga su trabajo. 

Cuando se modifique el grafo es necesario que el usuario opte por cargar de nuevo el archivo, para que el próximo análisis sea correcto.

¿Cómo funciona?
r: Se genera una matriz de adyacencia, cuadrada, cuyos parámetros iniciales son los mismos en las filas y en las columnas, pues la matriz es la que determina si existe relación entre esos elementos (nodos = usuarios). Si en la matriz hay un valor de "null" significa que esa columna no tiene relación con esa fila en el grafo; si, por el contrario, hay un número, este indica que sí hay una relación (un arista) y el peso de tal arista es el valor de ese número. 