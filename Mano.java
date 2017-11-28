/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Cliente
 */

/**
 * Un objeto de tipo Mano representa una mano de cartas. los
 * las tarjetas pertenecen a la clase Tarjeta. Una mano está vacía cuando
 * se crea y se puede agregar cualquier cantidad de tarjetas.
 */
public class Mano implements Serializable{
    private ArrayList<Carta> mano;  //Las cartas en la mano
    
    /** 
     * Crea una mano que está inicialmente vacía. 
     */ 
    
    public Mano(ArrayList<Carta> mano){
        this.mano = mano;
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }

    public void setMano(ArrayList<Carta> mano) {
        this.mano = mano;
    }

    public Mano() {
        mano = new ArrayList<>();
    }
    
    /** 
     * Retire todas las cartas de la mano, dejándolas vacías. 
     */ 
    
    public void limpiar(){
        mano.clear();
    }
    
    /**
     * Agrega una carta a la mano. Se agrega al final de la mano actual. 
     * @param c la tarjeta no nula que se agregará. 
     * @throws NullPointerException si el parámetro c es nulo. 
     */ 
    
    public void agregarCarta(Carta c){
        if(c == null){
            throw new NullPointerException("No se puede agregar una carta nula");
        }
        mano.add(c);
    }
    
    /** 
     * Eliminar una carta de la mano, si está presente. 
     * @param c la tarjeta que se eliminará. Si c es nulo o si la carta no está en 
     * la mano, entonces no se hace nada. 
     */ 
    
    public void eliminarCarta(Carta c){
        mano.remove(c);
    }
    
    /**
     * Retire la tarjeta en una posición específica de la mano. 
     * @param coloca la posición de la tarjeta que se va a eliminar, donde 
     * las posiciones comienzan desde cero. 
     * @throws IllegalArgumentException si la posición no existe en 
     * la mano, es decir, si la posición es menor que 0 o mayor que 
     * o igual a la cantidad de cartas en la mano. 
     */ 
    
    public void eliminarCarta(int posicion){
        if(posicion < 0 || posicion >= mano.size()){
            throw new IllegalArgumentException("La posición no existe en la mano: " + posicion);
        }
        mano.remove(posicion);
    }
    
    /**
     * Devuelve la cantidad de cartas en la mano. 
     */ 
    
    public int getContadorCartas(){
        return mano.size();
    }
    
    /** 
     * Obtiene la carta en una posición específica en la mano. (Tenga en cuenta que esta tarjeta 
     * no se quita de la mano!) 
     * @param posición de la posición de la tarjeta que va a ser devuelto 
     * @throws IllegalArgumentException si la posición no existe en la mano 
     */ 
    
    public Carta getCarta(int posicion){
        if(posicion < 0 || posicion >= mano.size()){
            throw new IllegalArgumentException("La posicion no existe en la mano: " + posicion);
        }
        return mano.get(posicion);
    }
    
    /** 
     * Clasifica las cartas en la mano para que las cartas del mismo palo 
     * se agrupen juntas, y dentro de un palo las cartas se ordenan por valor. 
     * Tenga en cuenta que se considera que los ases tienen el valor más bajo, 1. 
     */ 
    
    public void ordenarPorPalo(){
        ArrayList<Carta> nuevaMano = new ArrayList<>();
        while(mano.size() > 0){
            int pos = 0;    //Posición de la carta mínima.
            Carta c = mano.get(0);  //CArta mínima
            for (int i = 1; i < mano.size(); i++) {
                Carta c1 = mano.get(i);
                if(c1.getPalo() < c.getPalo() || c1.getPalo() == c.getPalo() && c1.getValor() < c.getValor() ){
                    pos = i;
                    c = c1;
                }
            }
            mano.remove(pos);
            nuevaMano.add(c);
        }
        mano = nuevaMano;
    }
    
    /** 
     * Ordena las cartas en la mano para que las cartas del mismo valor 
     * se agrupen juntas. Las cartas con el mismo valor se clasifican por palo. 
     * Tenga en cuenta que se considera que los ases tienen el valor más bajo, 1. 
     */ 
    
    public void ordenarPorValor(){
        ArrayList<Carta> nuevaMano = new ArrayList<>();
        while(mano.size() > 0){
            int pos = 0; //Posición de la carta mínima
            Carta c = mano.get(0);  //Carta mínima
            for (int i = 1; i < mano.size(); i++) {
                Carta c1 = mano.get(i);
                if(c1.getValor() < c.getValor() || c1.getValor() == c.getValor() && c1.getPalo() < c.getPalo()){
                    pos = i;
                    c = c1;
                }
            }
            mano.remove(pos);
            nuevaMano.add(c);
        }
        mano = nuevaMano;
    }
    
}
