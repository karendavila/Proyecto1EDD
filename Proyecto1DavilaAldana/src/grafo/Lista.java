/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;


public class Lista<T> {
    Nodo<T> first;
    Nodo<T> last;
    int count;

    public Lista() {
        this.count = 0;
        this.first = null;
        this.last = null;
    }
    
    /**
     * insertar permite ir creando la lista, 
     * recibe un valor y enlaza los nodos
     * 
     * @param value: valor a guardar en el nodo, 
     * es un usuario y su ID
     * 
     * @author Karen Davila
     */
    public void insertar(T value){
        Nodo<T> newNodo = new Nodo<>(value);
        if(this.first == null ){
            this.first = newNodo;
            this.last = newNodo;
        }else{
            this.last.setNext(newNodo);
            this.last = newNodo;
        }
        this.count++;
    }
    
    
    /**
     * buscarPosicion devuelve la localización del
     * nodo en la lista, aplica para ambas coordenadas
     * (x y y), devuelve el índice de lo que se 
     * busca para ir creando la matriz 
     * 
     * @param value: valor del nodo
     * 
     * @return index: indice del nodo en el arreglo
     */
    public int buscarPosicion(T value){
        
        int index = 0;
        
        Nodo<T> aux  = this.first;
       
        while(aux != null){
            if(aux.getValue().equals(value)){
                return index;            
            }
            aux = aux.getNext();
            index++;
        }
        return -1;
    }
//        
}
