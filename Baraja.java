/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.io.Serializable;

/**
 *
 * @author Cliente
 */
/**
 * Un objeto de tipo Baraja representa una baraja de cartas. La cubierta
 * es un mazo de póquer regular que contiene 52 cartas regulares y que puede
 * también opcionalmente incluye dos Jokers.
 */
public class Baraja implements Serializable{
    /**
     * Una matriz de 52 o 54 cartas. Un mazo de 54 cartas contiene dos Jokers,
     * además de las 52 cartas de un mazo de póker regular.
     */
    
    private Carta[] baraja;
    
    /**
     * Realiza un seguimiento del número de cartas que se han repartido desde
     * el mazo hasta ahora.
     */
    
    private int cartasUtilizadas;
    
    /**
     * Construye una baraja de póker regular de 52 cartas. Inicialmente, las cartas
     * están en un orden ordenado. El método shuffle () se puede llamar a
     * aleatorizar el orden. (Tenga en cuenta que "nuevo Deck ()" es equivalente
     * a "cubierta nueva (falsa)".)
     */
    
    public Baraja(){
        this(false);
    }
    /**
     * Construye una baraja de póker regular de 52 cartas. Inicialmente, las cartas
     * están en un orden ordenado. El método shuffle () se puede llamar a
     * aleatorizar el orden. (Tenga en cuenta que "nuevo Deck ()" es equivalente
     * a "cubierta nueva (falsa)".)
     */
    public Baraja(boolean incluirJokers){
        if(incluirJokers){
            baraja = new Carta[54];
        }else{
            baraja = new Carta[52];
        }
        int contadorCartas = 0; //Cuantas cartas se han creado hasta ahora
        for (int palo = 0; palo <= 3; palo++) {
            for (int valor = 1; valor <= 13; valor++) {
                baraja[contadorCartas] = new Carta(valor, palo);
                contadorCartas++;
            }
        }
        if(incluirJokers){
            baraja[52] = new Carta(1, Carta.JOKER);
            baraja[53] = new Carta(2, Carta.JOKER);
        }
        cartasUtilizadas = 0;
    }
    
    /**
     * Coloca todas las tarjetas usadas de nuevo en el mazo (si las hay), y
     * baraja el mazo en un orden aleatorio.
     */
    
    public void barajar(){
        for (int i = baraja.length - 1; i > 0; i--) {
            int aleatorio = (int)(Math.random() * (i + 1)); //CRea un tipo aleatorio para que las posiciones del array varien
            Carta temporal = baraja[i]; //Crea un objeto de la clase CArta y asigna el objeto que se encuentra dentro de la posición del array
            baraja[i] = baraja[aleatorio];
            baraja[aleatorio] = temporal;
        }
        cartasUtilizadas = 0;
    }
    
    /**
     * A medida que las cartas se reparten desde el mazo, la cantidad de cartas restantes
     * disminuye. Esta función devuelve la cantidad de tarjetas que
     * aún quedan en el mazo. El valor de retorno sería
     * 52 o 54 (dependiendo de si el mazo incluye comodines)
     * cuando se crea la plataforma por primera vez o después de que la plataforma ha sido
     * barajado. Disminuye en 1 cada vez que el método dealCard ()
     * se llama.
     */
    
    public int cartasIzquierda(){
        return baraja.length - cartasUtilizadas;
    }
    
    /**
     * Elimina la siguiente carta del mazo y la devuelve. Es ilegal
     * para llamar a este método si no hay más cartas en el mazo. Usted puede
     * verifique el número de tarjetas restantes llamando a la función cardsLeft ().
     * @return la carta que se retira de la baraja.
     * @throws IllegalStateException si no quedan cartas en el mazo
     */
    
    public Carta repartoCartas(){
        if(cartasUtilizadas == baraja.length){
            throw new IllegalStateException("No quedan cartas en el mazo");
        }
        cartasUtilizadas++;
        return baraja[cartasUtilizadas - 1];
        // Nota de programación: las tarjetas no se eliminan literalmente de la matriz
        // eso representa el mazo. Simplemente hacemos un seguimiento de cuántas 
        // cartas han sido usadas.
    }
    
    /**
     * Comprobar si el mazo contiene comodines.
     * @return true, si este es un mazo de 54 cartas que contiene dos comodines, o falso si
     * esta es una baraja de 52 cartas que no contiene comodines.
     */
    
    public boolean tieneJoker(){
        return (baraja.length == 54);
    }
}
