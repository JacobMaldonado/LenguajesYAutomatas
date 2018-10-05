/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimizacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jacob
 */
public class MinimizacionAutomatasDeterministas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //datos a pedir de cada automata
        String []estados; 
        String []estadosFinales;
        String []alfabeto;
        Map<String,String> funcionDeTransicion;
        String estadoInicial;
        //cadena auxiliar
        String auxiliar;
        
        //automata finito determinista
        AutomataFinitoDeterminista afd = null;
        
        System.out.println("Equivalencia de automatas:");
        //ingreso de los datos del automata 
        try{
                System.out.println("Automata Finito Determinista");
                System.out.println("Ingrese el conjunto de estados separados por ',' :");
                auxiliar = reader.readLine();
                estados = auxiliar.split(",");
                System.out.println("Ingrese el conjunto de simbolos del alfabeto separados por ',' ");
                auxiliar = reader.readLine();
                alfabeto = auxiliar.split(",");
                System.out.println("Ingrese la funcion de transición para cada uno de los siguientes:");
                funcionDeTransicion = pedirFuncionTransicion(estados, alfabeto);
                System.out.println("Ingrese el conjunto de Estados Finales separados por ',' ");
                auxiliar = reader.readLine();
                estadosFinales = auxiliar.split(",");
                System.out.println("Ingrese el estado inicial:  ");
                estadoInicial = reader.readLine();
                afd = new AutomataFinitoDeterminista(estados, alfabeto, 
                        funcionDeTransicion, estadoInicial, estadosFinales);
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        //si se puede reducir se imprime el nuevo automata
        if(afd.reducirAutomata()){
            imprimir(afd);
        }else{
            System.out.println("El automata no se pudo reducir");
        }
            
    }
    
    /**
     * 
     * @param estados -> estados del AFD
     * @param alfab -> simbolos en el alfabeto del AFD
     * @return -> funcion de trancision en hashMap
     * @throws java.io.IOException
     */
    public static Map pedirFuncionTransicion(String []estados, String []alfab) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map <String,String> mapa = new HashMap();
        String transicion, valor; 
        for (int i = 0; i < (estados.length * alfab.length); i++) {
            transicion = estados[i / alfab.length] + "," 
                    + alfab[i % alfab.length];
            System.out.println("Ingresa el estado de trancision para (" 
                    + transicion + ") : ");
            valor = reader.readLine();
            mapa.put(transicion, valor);
        }
        return mapa;
    }
    
    public static void imprimir(AutomataFinitoDeterminista afd){
        System.out.println("Estados: ");
        System.out.println(Arrays.toString(afd.getEstados()));
        System.out.println("Alfabeto: ");
        System.out.println(Arrays.toString(afd.getAlfabeto()));
        System.out.println("Funcion Trancision");
        System.out.println(afd.getFuncionTransicion().toString());
        System.out.println("Estado inicial: ");
        System.out.println(afd.getEstadoInicial());
        System.out.println("Estados Finales: ");
        System.out.println(Arrays.toString(afd.getEstadosFinales()));
    }
    
}
