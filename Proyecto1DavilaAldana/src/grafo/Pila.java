/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

/** Métodos de pila: apilar, pop y demás
 * que fueron útiles para el programa. 
 * Al final está la función para el recorrido 
 * en profundidad (DFS) que se apoya en la clase 
 * Search. 
 *
 * @author dario
 */
public class Pila {
    private Nodo top; 
    private Nodo bottom; 
    private int size; 
    
    
    /**
     * @return the top
     */
    public Nodo getTop() {
        return top;
    }

    /**
     * @return the bottom
     */
    public Nodo getBottom() {
        return bottom;
    }

    
    public Pila(){
        this.top = null; 
        this.bottom = null; 
        this.size = 0; 
        
    }
    
    public boolean isEmpty(){
        return getTop() == null; 
    }
    
    public void empty(){
        this.top = null; 
        this.bottom = null; 
        this.size = 0; 
    }
    
    public int size(){
        return size;
    }
    
    /**
     * Description: Si la pila está vacía, la base y el
     * tope de ella son el mismo nodo nuevo, si no, el nuevo
     * apunta al tope de la fila y se convierte en el primero. 
     * 
     * @param nuevo nodo a agregar
     */
    public void apilar(Nodo nuevo){
        if (this.isEmpty()){
            top = nuevo;
            bottom = nuevo;
        } else {
            nuevo.setNext(getTop());
            top = nuevo; 
        }
        size ++;
        
    }
    
    
    /**Description: Si la pila está vacía, se avisa en 
     *      la terminal; si la pila tiene sólo un elemento, 
     *      se vacía la pila y si tiene más de uno, el tope 
     *      de la pila pasa a ser el segundo. 
     * 
     */
    public void pop(){
        if (this.isEmpty()){
            System.out.println("Pila vacía");
        } else if (size() ==1) {
            this.empty();
        } else{
            top = getTop().getNext(); 
            size--; 
        }
    }
    
    
    
    public String print(String printPila){
        if (!this.isEmpty()){
            Nodo presente = getTop(); 
            pop();
            printPila += presente.getValue(); 
            printPila = print(printPila); 
            apilar(presente);
        }
        return printPila;
    }
    
    /**
     * Description: determina los parámetros necesarios para
     *      llamar a SearchFinal que realizará el recorrido según
     *      los parámtros recibidos. El tercer parámetro es false 
     *      porque ese booleano es si se usa "BFS" y el cuarto es 
     *      verdadero (a diferencia de la función BFS en la clase
     *      Colas) porque la identificación de puentes se realizará
     *      con BFS, nunca con DFS. 
     * 
     * @param usuariosArray array de enteros con el ID de los usuarios
     * 
     * @param matrix es la matriz de adyacencia,
     *      un arreglo en dos dimensiones x y y
     * 
     * 
     * @author Dario Aldana
     */
    public void DFS(int[] usuariosArray, Integer[][] matrix){
        Search structure = new Search();
        structure.SearchFinal(usuariosArray, matrix, false, true);
        
        
    }
    
}

