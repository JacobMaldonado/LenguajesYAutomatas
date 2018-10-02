/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Palindromo;

/**
 *
 * @author jacob
 */
public class Palindromo {
    
    private String palabra;
    private Pila<Character> pila;

    public Palindromo(String palabra){
        this.palabra = palabra;
        pila = new Pila();
    }

    public boolean esPalindromo(){
        String palabraInicial , palabraInvertida;
        //obtenemos la palabra sin espacios y se inserta a la pila
        palabraInicial = insertarPalabraEnPila();
        
        //obtenemos la palabra invertida
        palabraInvertida = obtenerPalabraAlrevez();
        
        //se compara para saber si es palindromo
        return palabraInicial.equals(palabraInvertida);
        
    }
    
    private String insertarPalabraEnPila(){
        //creamos un String para almacenar la palabra sin espacios
        String palabraSinEspacios = "";
        for(int i = 0; i< palabra.length(); i++){
            if(palabra.charAt(i) != ' '){
                //agregamos cada caracter a la pila
                pila.push(palabra.charAt(i));
                //agregamos el caracter al String nuevo
                palabraSinEspacios += palabra.charAt(i);
            }
        }
        return palabraSinEspacios;
    }
    
    private String obtenerPalabraAlrevez(){
        //creamos un String y lo inicializamos vacio
        String palabraAlrevez = "";
        while(!pila.isEmpty()){
            //agregamos letra por letra a el nuevo String
            try{
                palabraAlrevez += pila.pop().toString();
            }catch(Exception e){
                System.out.println("Exepcion : " + e.getMessage());
            }
        }
        return palabraAlrevez;
    }
    
    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }
    
    public String getPalabra() {return palabra;}
}
