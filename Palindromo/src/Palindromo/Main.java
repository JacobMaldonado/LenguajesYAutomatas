/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Palindromo;

import java.util.Scanner;

/**
 *
 * @author jacob
 */
public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        //obtenemos la palabra del usuario
        String palabra = s.nextLine();
        //creamos el palindromo
        Palindromo palindromo = new Palindromo(palabra);
        //si es palindromo, lo notificamos
        if(palindromo.esPalindromo()){
            System.out.println("La palabra es un palindromo");
        }else{
            System.out.println("La palabra no es un palindromo");
        }
    }
    
}
