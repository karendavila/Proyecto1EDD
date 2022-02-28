/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import java.util.HashSet;
import javax.swing.JOptionPane;
import usuario.Usuario;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.spriteManager.Sprite;
import org.graphstream.ui.spriteManager.SpriteManager;


/**
 * La clase Grafo se encarga de la matriz de adyacencia,
 * el display del gráfico como tal y la identificación
 * de puentes
 * 
 * @author dario
 */
public class Grafo {
    private Integer[][] matrix; //base de la matriz de adyacencia
    private Lista<Usuario> usuarios; 
    private int[] usuariosArray;
    private int size; 
    private String usersGuide; 
    


    
    /**
     * Grafo crea la matriz de adyacencia 
     * a ser llenada posteriormente
     * 
     * @param size: es el counter del txt 
     * que cuenta las lineas de usuarios, 
     * cuantos usuarios hay
     * 
     * @author Dario Aldana
     */
    public Grafo(int size) {
        matrix = new Integer[size][size];
        usuarios = new Lista<>();
    }
    
    
    
    /**Description: recibe lineUsers, los separa 
     *      usando la coma y toma el primer elemento de 
     *      cada línea (el ID) y lo convierte en int.
     * 
     * 
     * @param lineUsers array de Strings, cada elemento
     *          es una línea del apartado "Usuarios" de 
     *          la base de datos
     * 
     * @author Darío Aldana
     * 
     * @return usuariosArray int[] con sólo los IDs 
     */
    public static int[] createUsersArray(String[] lineUsers){
        int[] usuariosArray = new int[lineUsers.length];
        
        for (int i = 0; i < lineUsers.length; i++) {

            String[] datos = lineUsers[i].split(",");
            usuariosArray[i] = Integer.parseInt(datos[0].trim());
        }
        return usuariosArray;
    }
    
    
    
     /**
     * createGrafo recibe los mega Strings de 
     * usuarios y sus relaciones, los divide en 
     * arreglos de strings y después itera sobre ellos 
     * para ir creando una lista enviándolo a insertar()
     * 
     * después en el otro ciclo for vamos creando la matriz
     * usando coordenadas x y y, buscando el valor de los 
     * nodos mediante buscarPosicion()
     * 
     * @param usuariosStr: es el mega String
     * de usuarios y su ID
     * 
     * @param aristas: es el mega String de las
     * relaciones entre esos usuarios 
     * 
     * @author Darío Aldana
     */
    public Integer[][] createGrafo(String usuariosStr, String aristas, int size){
          
          /**
           * Primero se crean los arrays de Strings,
           * donde cada elemento del array es una línea
           * del txt según corresponda. 
           */
          String[] lineUsers = usuariosStr.split("//");
          String[] lineEdge = aristas.split("//");

          usuariosArray = createUsersArray(lineUsers);
          //usuariosArray es un array pero de enteros,
          //contiene sólo los ID de los usuarios. 

          for (int i = 0; i < lineUsers.length; i++) {

              String[] datos = lineUsers[i].split(",");
              Usuario usuario = new Usuario(Integer.parseInt(datos[0].trim()), datos[1].trim());              
              this.usuarios.insertar(usuario);

          }

          for (int i = 0; i < lineEdge.length; i++) {

            String[] datos= lineEdge[i].split(",");

            Usuario userX = new Usuario(Integer.parseInt(datos[0].trim()),"");
            Usuario userY = new Usuario(Integer.parseInt(datos[1].trim()),"");


            //Busca la posición para determinar
            //si existe arista o no
            int posX = this.usuarios.buscarPosicion(userX);
            int posY = this.usuarios.buscarPosicion(userY);



            this.matrix[posX][posY]= Integer.parseInt(datos[2].trim());
        }
          displayGraph(matrix, size, usuariosArray);
          matrixSymmetry(matrix, size); 
          //printMatrix(matrix, size);
          //si se desea comprobar la matriz, 
          //descomente la función printMatrix
          
          return matrix;
    }
    
    
    /**Description: matrixSymmetry toma la matriz y la
     * hace simétrica, pues, como las relaciones no tienen
     * dirección, se hace más cómodo para futuros procedimientos
     * que la matriz sea simétrica. Se toma que si existe un 
     * arista entre i-j en la matriz (siendo i las filas y j las
     * columnas), pues, ese arista también debe aparecer en (j, i)
     * 
     * @param matrix arreglo de ints en dos dimensiones, 
     *          matriz de adyacencia
     * 
     * @param size int, tamaño de la matriz
     */
    public void matrixSymmetry(Integer[][] matrix, int size){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] != null){
                    matrix[j][i] = matrix[i][j];
                }
            }
            
        }
        
    }
    
    
    /**
     * Description: printMatrix en primera instancia, no se
     * está ejecutando por motivos de presentación, pero si 
     * se desea comprobar la matriz de adyacencia, basta con 
     * descomentarla en createGrafo(). Imprime la matriz de adyacencia. 
     * 
     * @param matrix arreglo de ints en dos dimensiones, 
     *          matriz de adyacencia
     * 
     * @param size int, tamaño de la matriz
     */
    public void printMatrix(Integer[][] matrix, int size) {
        
        StringBuilder sb = new StringBuilder(); 
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(matrix[i][j] + "  ");
            }
            sb.append("\n");
        }
        
        JOptionPane.showMessageDialog(null, sb);
  
    }
    
    
    /**
     * Description: displayGraph es la función que genera
     * la representación gráfica de la matriz de adyacencia 
     * mediante las librerías de Graph Stream. Primero, se 
     * itera sobre el array de IDs y se va creando los nodos, 
     * después se busca los valores distintos a null en la 
     * matriz de adyacencia y se grafican como aristas. 
     *
     * @param matrix arreglo de ints en dos dimensiones, 
     *          matriz de adyacencia
     * 
     * @param size int, tamaño de la matriz
     * 
     * @param usuariosArray array de enteros con los IDs de los users
     */
    public void displayGraph(Integer[][] matrix, int size, int[] usuariosArray) {
        System.setProperty("org.graphstream.ui", "swing"); 
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        Graph graph = new SingleGraph("Graph"); 
        

        for (int i = 0; i < usuariosArray.length; i++) {
            graph.addNode(Integer.toString(usuariosArray[i]));  
            Node n = graph.getNode(Integer.toString(usuariosArray[i]));
            n.setAttribute("ui.style", "shape:circle;fill-color: rgb(154, 220, 255);size: 35px; text-alignment: center;");
            n.setAttribute("ui.label", Integer.toString(usuariosArray[i]));          
   
        }
        
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] != null){
                    int weight = matrix[i][j];
                    graph.addEdge(Integer.toString(usuariosArray[i])+Integer.toString(usuariosArray[j]), 
                            Integer.toString(usuariosArray[i]), 
                            Integer.toString(usuariosArray[j]));
                    
                    Edge g = graph.getEdge(Integer.toString(usuariosArray[i])+Integer.toString(usuariosArray[j]));
                    g.setAttribute("ui.label", Integer.toString(weight));  
                }
            }
        }
        
        Viewer viewer = graph.display(); 
        viewer.enableAutoLayout(); 
    }
    
    
    /**Description: el concepto de findBridges es ir eliminando
     * algún arista, hacer el recorrido y contar si hubo cambio 
     * en la cantidad de islas. Si lo hubo, ese arista eliminado 
     * es un puente, si no, no. 
     * 
     * @param usersQuant cantidad de usuarios, es un entero
     * @param matrix matriz de adyacencia
     * @param usuariosArray array de enteros con sólo
     *              los ID de los usuarios. 
     * 
     * @dario
     * 
     * @return bridges: arreglo de strings, donde cada elemento representa
     *      un puente. 
     */
    public String[] findBridges(int usersQuant, Integer[][] matrix, int[] usuariosArray){

        int size = usersQuant;
       
        /**
         * initialIslands permanecerá igual siempre,
         * finalIslands tomará un valor después de cada
         * recorrido y eliminación de arista para determinar
         * si existió algún cambio y saber si hay puente.
         */
        int initialIslands = Search.islandQuant;
        int finalIslands = Search.islandQuant;
        

        StringBuilder sb = new StringBuilder(); 

        Cola q = new Cola(); 

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if (matrix[i][j] != null){
                    /**si algún elemento de la matriz difiere
                     * de null, es porque tiene un peso, es 
                     * porque tiene un arista. 
                     */
                    
                    finalIslands = initialIslands; 
                    
                    int weight = matrix[i][j]; //peso del arista
                    
                    matrix[i][j] = null; //se borra el arista
                    matrix[j][i] = null; //se borra el simétrico
                    
                    q.BFS(usuariosArray, matrix, false);
                    
                    finalIslands = Search.islandQuant;
                 
                    //se chequea si hay cambio en la cantidad de islas
                    if (finalIslands != initialIslands){
                        
                        if (sb.toString().equals("")){
                            sb.append(Integer.toString(usuariosArray[i]) + "-");
                            sb.append(Integer.toString(usuariosArray[j]));
                        } else {
                            sb.append("//");
                            sb.append(Integer.toString(usuariosArray[i]) + "-");
                            sb.append(Integer.toString(usuariosArray[j]));
                        }
                    }
                    
                    // se vuelve a agregar el arista a la matriz. 
                    matrix[i][j] = weight; 
                    matrix[j][i] = weight; 

                }
            }
        }
        
        String[] bridges = sb.toString().split("//");
        return bridges; 
    }
    
}

