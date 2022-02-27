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
public class Cola {
    public Nodo first; 
    private Nodo last; 
    private int size; 

    public Cola(){
        this.first = null;
        this.last = null; 
        this.size = 0;     
       
    }
    
    public Nodo getFirst(){
        return first;
    }
    
    public boolean isEmpty(){
        return first == null; 
    }
    
    public void empty(){
        this.first = null; 
        this.last = null; 
        this.size = 0;
    }
    
    public void queue(Nodo nuevo){
        
        if (this.isEmpty()) {
            first = nuevo; 
            last = nuevo; 
        } else {
            last.setNext(nuevo); 
            last = nuevo;
        }
        size++;
    }
    
    public void dequeue(){
        if (this.isEmpty()){
            System.out.println("No hay elementos en la cola");
        } else if(size ==1){
            this.empty();
            size--; 
        } else {
            first = first.getNext();
            size--; 
        }
    }
    
    public String print(){
        if (!this.isEmpty()){
            String printQ = ""; 
            
            for (int i = 0; i < size; i++) {
                Nodo presente = first; 
                dequeue(); 
                printQ += presente.getValue(); 
                queue(presente); 
                
            }
            return printQ;
        }
        
        System.out.println("Cola vacía");
        return null; 
    }
    
    public int size(){
        int counter = 0; 
        Nodo aux = this.first; 
        
        while (aux != null){
            counter++; 
            aux = aux.getNext();

        }
        size = counter; 
        return size;
    }
    
    /**
     * Description: permite la busqueda por BFS usando colas, 
     *      tiene dos ciclos while, el más general para determinar
     *      cuando fueron evaluados todos los nodos y otro para iterar
     *      recorriendo toda la isla seleccionada. Se usa un arreglo
     *      de booleanos para determinar qué IDs (nodos) ya fueron examinados. 
     *      Al final imprime las colas mediante JOptionPane, habiendo creado 
     *      anteriormente un StringBuilder. 
     *      
     * @param usuariosArray array de enteros con el ID de los usuarios
     * 
     * @param matrix es la matriz de adyacencia,
     *      un arreglo en dos dimensiones x y y
     * 
     * 
     * @author Dario Aldana
     */
    public void BFS(int[] usuariosArray, Integer[][] matrix, boolean print){ 
        
        //SearchFinal(usuariosArray, matrix, true);
        Search structure = new Search(); 
        
        structure.SearchFinal(usuariosArray, matrix, true, print);
  
    }

}
    
