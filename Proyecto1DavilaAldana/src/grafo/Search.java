/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import javax.swing.JOptionPane;

/**
 *
 * @author dario
 */
public class Search {
//    private Nodo first; 
//    private Nodo last; 
    private int size; 
    private Nodo n; 
    public static int islandQuant;

    
    public Search(){
        

    }
    
    public void SearchFinal(int[] usuariosArray, 
            Integer[][] matrix, 
            boolean BFS, 
            boolean print){
        
        Cola cola = new Cola(); 
        Pila pila = new Pila(); 
        
        
        int usersQuant; 
        usersQuant = usuariosArray.length; //cantidad de usuarios
        StringBuilder sb = new StringBuilder(); 
        Nodo n = null; 
        
        int index = -1; //index del nodo en la matriz de adyacencia
        int islandCounter = 0; // cuenta las islas
        int nodeCounter = 0; // cuenta los nodos
        boolean separador = false; //define si usamos "--" en el StringBuilder
        
        if (BFS) {
                    size = cola.size();
                } else {
                    size = pila.size();
                }
        
        
        boolean[] checked = new boolean[usersQuant];
        
        for (int i = 0; i < usersQuant; i++) {
            checked[i] = false;
        }

        while (nodeCounter < usersQuant){
            islandCounter++;
            if (size == 0) {
                
                for (int i = 0; i < usersQuant; i++) { 
                    //se selecciona nodo raiz para iniciar la evaluacion
                    
                    if (!checked[i]){
                        
                        checked[i] = true; 
                        
                        n = new Nodo(usuariosArray[i]);
                        

                        if (BFS){
                            cola.queue(n);
                            size++;
                        } else {
                            pila.apilar(n);
                            size++;
                        }
                        
                        break;
                    }
                }
            }
           
            
            while (size != 0){
                
                if (BFS){
                    n = cola.getFirst();
                } else {
                    n = pila.getTop(); 
                }
              
                
                
                if (BFS){
                    cola.dequeue(); 
                } else {
                    pila.pop();
                }
                
                if (separador){
                    sb.append("--" + n.getValue());
                } else {
                    sb.append(n.getValue());
                }
                
                separador = true; 
                
//                if (BFS){
//                    System.out.println("COLA" + n.getValue() + "  ");
//                } else {
//                    System.out.println("PILA" + n.getValue() + "  ");
//                }
                
                int id = (int) n.getValue(); //sacamos el id dentro del nodo del primero de la cola
                for (int i = 0; i < usersQuant; i++) {
                    if (id == usuariosArray[i]){ //sacamos el index de ese id en la matriz
                        index = i;

                        for (int j = 0; j < usersQuant; j++) { //buscamos en la matrix con qué conecta ese nodo

                            if (matrix[index][j] != null){
                                if (!checked[j]){
                                    /**
                                     * si sí hay arista entre ambos nodos y uno 
                                     * de ellos no ha sido evaluado, ese nodo
                                     * se convierte en n y lo ponemos en la cola.
                                        */
                                    n = new Nodo(usuariosArray[j]);
                                    
                                    if (BFS){
                                        cola.queue(n);
                                    } else {
                                        pila.apilar(n);
                                    }

                                    checked[j] = true;

                                }

                            }
                        }   
                    }
                }
                
                if (BFS) {
                    size = cola.size();
                } else {
                    size = pila.size();
                }
                 
                
            }
            
            sb.append("//");
            
            separador = false; 
            //Como agregamos "//", pasamos el 
            //separador a false para no usar "--".
            
            
            nodeCounter = 0;
            
            //Contamos los nodos que hemos tomado en cuenta ya. 
            for (int i = 0; i < checked.length; i++) {
                if (checked[i]){
                    nodeCounter++;
                }
            }

        }
        

        String structure = "";
        
        if (BFS){
            structure = " colas";
        } else {
            structure = " pilas";
        }
        
        if (print){
            JOptionPane.showMessageDialog(null, 
                "El gráfico contiene " + islandCounter + " isla(s). ",
                "Cantidad de Islas",
                JOptionPane.INFORMATION_MESSAGE);
            
            JOptionPane.showMessageDialog(null, 
                    "A continuación se muestran las " + islandCounter + structure,
                    "Cantidad de Islas",
                    JOptionPane.INFORMATION_MESSAGE);

            String[] islands = sb.toString().split("//");
            for (int i = 0; i < islands.length; i++) {
                if (BFS){
                    JOptionPane.showMessageDialog(null, 
                            "Cola N° " + (i+1) + "\n" + islands[i],
                            "Cantidad de Islas",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, 
                            "Pila N° " + (i+1) + "\n" + islands[i],
                            "Cantidad de Islas",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            }
        }
        
        islandSetter(islandCounter); 
        //System.out.println("check" + islandQuant);
        //this.islandCounter = islandCounter; 
        //System.out.println("hoal" + islandQuant);
        //System.out.println("hoal" + this.islandCounter);
        
    }
    
    public static int islandSetter(int islandCounter){
        islandQuant = islandCounter; 
        System.out.println("Probando IQ "+ islandQuant);
        return islandQuant; 
    }
    
    //public int getIslandCounter(){
        //islandCounter = this.islandCounter;
        //System.out.println(islandCounter);
        //return islandCounter;
    //}
    
}
