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



public class Grafo {
    private Integer[][] matrix;
    private Lista<Usuario> usuarios;
    private int[] usuariosArray;
    private int size; 


    
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
     * @author Karen Davila
     */
    
    
    public static int[] createUsersArray(String[] lineUsers){
        int[] usuariosArray = new int[lineUsers.length];
        
        for (int i = 0; i < lineUsers.length; i++) {

            String[] datos = lineUsers[i].split(",");
            usuariosArray[i] = Integer.parseInt(datos[0].trim());

        }
        
        return usuariosArray;
    }
    
    
    public Integer[][] createGrafo(String usuariosStr, String aristas, int size){

          String[] lineUsers = usuariosStr.split("//");
          String[] lineEdge = aristas.split("//");
          
          usuariosArray = createUsersArray(lineUsers);
          
          
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
          printMatrix(matrix, size);

          return matrix;
    }
    
    public void matrixSymmetry(Integer[][] matrix, int size){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] != null){
                    matrix[j][i] = matrix[i][j];
                }
            }
            
        }
        
    }
    
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
        System.out.println("...\n out ff \n\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] != null){
                    int weight = matrix[i][j];
                    graph.addEdge(Integer.toString(usuariosArray[i])+Integer.toString(usuariosArray[j]), 
                            Integer.toString(usuariosArray[i]), 
                            Integer.toString(usuariosArray[j]));
                    
                    Edge g = graph.getEdge(Integer.toString(usuariosArray[i])+Integer.toString(usuariosArray[j]));
                    g.setAttribute("ui.label", Integer.toString(weight));
                    //g.setAttribute("ui.style", "text-alignment: left"); 
                    
                }
            }
            
        }
        
        Viewer viewer = graph.display(); 
        viewer.enableAutoLayout();
        
        
        
    }
    
    public String[] findBridges(int usersQuant, Integer[][] matrix, int[] usuariosArray){
        
        System.setProperty("org.graphstream.ui", "swing"); 
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        Graph graph = new SingleGraph("Graph"); 
        
        int size = usersQuant;
       
        int initialIslands = Search.islandQuant;

        int finalIslands = Search.islandQuant;
        

        StringBuilder sb = new StringBuilder(); 
        
        System.out.println("ISLAS AL PRINCIPIO " + initialIslands + "\n");
        
        Cola q = new Cola(); 
        
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                
                System.out.println("firstchecking");
                
                if (matrix[i][j] != null){
                    System.out.println("checking");
                    int weight = matrix[i][j];
                    
                    
                    System.out.println(weight);
                    
                    Edge e = graph.getEdge(Integer.toString(usuariosArray[i])+Integer.toString(usuariosArray[j]));
                    
                    graph.removeEdge(e);
                    
                  
                    
//                    Node n = graph.getNode(Integer.toString(usuariosArray[i]));
//                    Node m = graph.getNode(Integer.toString(usuariosArray[j]));
//                    graph.removeEdge(n, m);

//                    graph.removeEdge(Integer.toString(usuariosArray[i]),
//                            Integer.toString(usuariosArray[j]));
                    

                    System.out.println("LESGO");
                    q.BFS(usuariosArray, matrix, false);
                    finalIslands = Search.islandQuant;
                    
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
                    
                    Node n = graph.getNode(Integer.toString(usuariosArray[i]));
                    Node m = graph.getNode(Integer.toString(usuariosArray[j]));
                    graph.addEdge(Integer.toString(usuariosArray[i])+Integer.toString(usuariosArray[j]), n, m);
                    
//                    String id = (Integer.toString(usuariosArray[i])+Integer.toString(usuariosArray[j]));
//                    System.out.println(id);
//                    String n = Integer.toString(usuariosArray[i]);
//                    String m = Integer.toString(usuariosArray[j]);
//
//                    graph.addEdge(id, n, m);
//
//                    graph.addEdge(Integer.toString(usuariosArray[i])+Integer.toString(usuariosArray[j]), 
//                            Integer.toString(usuariosArray[i]), 
//                            Integer.toString(usuariosArray[j]));


//                    graph.addEdge(Integer.toString(usuariosArray[i])+Integer.toString(usuariosArray[j]), 
//                            Integer.toString(usuariosArray[i])), 
//                            Integer.toString(usuariosArray[j]));
                }
            }
            
        }
        
        String[] bridges = sb.toString().split("//");
        return bridges; 
    }
    


}
