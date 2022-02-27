/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

/**
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
    
    public void DFS(int[] usuariosArray, Integer[][] matrix){
        Search structure = new Search();
        structure.SearchFinal(usuariosArray, matrix, false, true);
        
        
    }
    
}

