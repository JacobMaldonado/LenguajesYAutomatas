/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equivalenciaautomatas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author jacob
 */
public class AutomataFinitoDeterminista {
    //Equivalente a Q, conjunto de estados
    private String mEstados[];
    //Equivalente a Sigma mayuscula, que es el alfabeto
    private String mAlfabeto[];
    //Equivalente a delta minuscula, que es la funcion de transición
    private Map<String,String> mFuncionTransicion;
    //Equivalente a q0, que es el estado inicial
    private String mEstadoInicial;
    //Equivalente a F, que es el conjunto de estados finales
    private String mEstadosFinales[];
    
    public AutomataFinitoDeterminista(String[] estados, String[] alfabeto,
            Map<String, String> funcionTransicion, String estadoInicial, String[] estadosFinales) {
        mEstados = estados;
        mAlfabeto = alfabeto;
        mFuncionTransicion = funcionTransicion;
        mEstadoInicial = estadoInicial;
        mEstadosFinales = estadosFinales;
    }

    public String[] getEstados() {
        return mEstados;
    }

    public void setEstados(String[] estados) {
        mEstados = estados;
    }

    public String[] getAlfabeto() {
        return mAlfabeto;
    }

    public void setAlfabeto(String[] alfabeto) {
        mAlfabeto = alfabeto;
    }

    public Map<String, String> getFuncionTransicion() {
        return mFuncionTransicion;
    }

    public void setFuncionTransicion(Map<String, String> funcionTransicion) {
        mFuncionTransicion = funcionTransicion;
    }

    public String getEstadoInicial() {
        return mEstadoInicial;
    }

    public void setEstadoInicial(String estadoInicial) {
        mEstadoInicial = estadoInicial;
    }

    public String[] getEstadosFinales() {
        return mEstadosFinales;
    }

    public void setEstadosFinales(String[] estadosFinales) {
        mEstadosFinales = estadosFinales;
    }
    
    //metodo de equivalencia de Automatas Finitos Deterministas
    //se usa guion bajo para diferenciar los parametros del afd al que se compara
    public boolean equivalenteA(AutomataFinitoDeterminista _afd){
        //variable para saber si faltan estados o ya todos se analizaron
        boolean faltanEstadosPorAnalizar = true;
        //lista para guardar los estados analizados
        List<String> estadosAnalizados = new ArrayList();
        //cadena para estados devueltos al hacer la transicion por los simbolos del alfabeto
        String estados[];
        //pila para guardar estados pendientes
        Stack<String> pila = new Stack();
        
        //obtener estados iniciales
        String estadoActual = mEstadoInicial;
        String _estadoActual = _afd.getEstadoInicial();        
        
        //mientras haya estados no analizados
        while(faltanEstadosPorAnalizar){
            //agregar estados a la lista para evitar repeticion
            estadosAnalizados.add(estadoActual + "," + _estadoActual);
            //obtener la trancision de estados por cada simbolo del alfabeto
            estados = transicion(_afd, estadoActual, _estadoActual);
            //iterar por los estados dados por las transiciones
            for(int i = 0; i < estados.length; i++){
                //si los estados no son compatibles
                if(esEstadoFinal(estados[i].split(",")[0]) 
                        != _afd.esEstadoFinal(estados[i].split(",")[1])){
                    //devolver falso
                    return false;
                }
                //si no
                else {
                    //ingresar a una pila
                    pila.push(estados[i]);
                }
            }
            //hacer mientras el estado se encuentre en la lista de analizados
            String estadoSiguiente = "";
            do{
                if(!pila.isEmpty()){
                    //sacar el siguiente elemento de la pila
                    estadoSiguiente = pila.pop();
                }else{
                    faltanEstadosPorAnalizar = false;
                    break;
                }
            }while(estadosAnalizados.contains(estadoSiguiente));
            //si la cadena no esta vacia
            if(!estadoSiguiente.isEmpty()){
                //dividir la cadena de la pila por "," y asignar a los estados actuales
                estadoActual = estadoSiguiente.split(",")[0];
                _estadoActual = estadoSiguiente.split(",")[1];
            }
        }
        
        return true;
    }
    
    /**Regresa todos los estados de transicion de los simbolos del alfabeto
     * 
     * Se usa guion bajo para diferenciar al automata primo al que se compara
     * @param _afd -> automata finito determinista que se esta comparando
     * @param ea -> estado actual del automata comparado
     * @param _ea -> estado actual del automata al que se compara
     * @return -> conjunto de estados igual al numero de simbolos del alfabeto
     */
    private String[] transicion(AutomataFinitoDeterminista _afd, String ea, String _ea){
        //los estados son iguales al numero de simbolos en el alfabeto
        String []resultado = new String[mAlfabeto.length];
        String nuevoEstado,_nuevoEstado;
        //iteramos por cada simbolo del alfabeto
        for(int i = 0; i < mAlfabeto.length; i++){//TODO: crear funcion para obtener la trancision 
            //obtenemos el estado al que se hace la transicion de dicho simbolo
            nuevoEstado = mFuncionTransicion.get(ea + "," + mAlfabeto[i]);
            _nuevoEstado = _afd.getFuncionTransicion().get(_ea + "," +_afd.getAlfabeto()[i]);
            resultado[i] = nuevoEstado + "," + _nuevoEstado;
        }
        
        return resultado;
    }
    
    public boolean esEstadoFinal(String estado){
        for(int i = 0; i < mEstadosFinales.length; i++ ){
            if(estado.equals(mEstadosFinales[i]))
                return true;
        }
        return false;
    }
            
}
