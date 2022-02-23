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
    
    public int buscarPosicion(T value){
        int index = 0;
        
        Nodo<T> aux  = this.first;
        while(aux != null){
            if(aux.equals(value)){
                return index;            
            }
            aux = aux.getNext();
            index++;
        }
        return -1;
    }
    
}
