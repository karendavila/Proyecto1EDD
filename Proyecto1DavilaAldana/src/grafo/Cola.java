/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import javax.swing.JOptionPane;

/** Description: Métodos de cola: queue, dequeue, isEmpty()
 *          y demás que fueron útiles en el programa. 
 *          Finalmente, el buscador por BFS que se apoya
 *          en la clase Search. 
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
    
    /**
     * queue: agrega un nodo a la cola. 
     *      si la cola está vacía el nodo inicial y el 
     *      final son los mismos; si la cola tiene elementos
     *      el apuntador del último apuntará al nodo nuevo
     *      y este nodo nuevo se convierte en el último. 
     *      Finalmente se aumenta la size de la cola
     * 
     * @param nuevo nodo que se desea agregar en la cola
     */
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
    
    
    /**dequeue sirve para despachar el primer elemento de la 
     * cola; si está vacía se imprime un mensaje en consola; 
     * si tiene un elemento, se vacía y si tiene más de uno
     * el primer elemento pasa a ser al que éste apunta y se
     * reduce el tamaño en uno. 
     * 
     * @author dario
     */
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
    
    
    /**
     * print, para imprimir la cola, se crea un string que 
     * almacenará los valores de los nodos y se declara un 
     * nodo que será el que en el presente estará de primero.
     *La cola se va desencolando y cada valor se va tomando 
     * encadenando en el String, posteriormente, esos nodos
     * extraídos se ponen al final de la cola. El ciclo for
     * limita cuando se frena la acción y queda la cola en 
     * el estado inicial y se obtiene un string con los valores
     * de los nodos. 
     * @return 
     */
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
    
    /**
     * size es para determinar el tamaño de la cola
     * se tiene un counter que en un ciclo while iterará
     * mientras un nodo auxiliar creado recorrera uno a 
     * uno cada nodo, cuando este llegue al final, se devuelve
     * el counter. 
     * 
     * @return [int] size de la cola
     */
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
     * Description: determina los parámetros necesarios para
     *      llamar a SearchFinal que realizará el recorrido según
     *      los parámetros recibidos. En este caso, el tercer 
     *      parámetro que se le pasa a SearchFinal es true porque
     *      la función es del recorrido por anchura. Y el cuarto
     *      parámetro define si se imprimirá en pantalla el recorrido 
     *      o no, si se imprime es por el botón de determinar la 
     *      cantidad de islas y si no se imprime es porque se están 
     *      identificando puentes.
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
        

        Search structure = new Search(); 
        
        structure.SearchFinal(usuariosArray, matrix, true, print);
  
    }

}
    
