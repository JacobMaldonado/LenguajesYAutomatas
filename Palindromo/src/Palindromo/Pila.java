/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Palindromo;

import java.util.Vector;

/**
 *
 * @author jacob
 */
public class Pila<E> {
    
    private Vector<E> listaPila;
    private int items;
    
    
    public Pila (){
        listaPila = new Vector();
        items = -1;
    }
    
    public void push(E elemento){
        listaPila.add(elemento);
        items ++;
    }
    
    public E pop() throws Exception {
        //si es inferior a 0, se regresa nulo
        if(items >= 0){
            E elemento = listaPila.get(items);
            items --;
            return elemento;
        }
        //si la pila esta vacia, tira un error
        new Exception("La pila esta vacia");
        return null;
    }
    
    public boolean isEmpty(){
        //regresa la condicion 
        return items < 0;
    }
    
    
}
