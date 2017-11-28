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
public class ManoDeBlackjack extends Mano implements Serializable{
    /**
     * Calcula y devuelve el valor de esta mano en el juego
     * de Blackjack.
     */
    
    public int getValordeBlackjack(){
        int val;    //El valor calculado para la mano
        boolean as; //Esto se establecera en verdadero si la manecilla contiene un as
        int cartas; //Número de cartas de la mano
        
        val = 0;
        as = false;
        cartas = getContadorCartas();   //Metodo definido y heredado de la clase Mano
        
        for (int i = 0; i < cartas; i++) {
            //Agrega el valor de la i-esima carta en la mano
            Carta carta;    //La i-esima carta
            int valorCarta; //El valor de blackjack de la i-esima carta
            carta = getCarta(i);
            valorCarta = carta.getValor();  //El valor normal, de 1 a 13
            if(valorCarta > 10){
                valorCarta = 10;    //Para un jack, reina o rey
            }
            if(valorCarta == 1){
                as = true;  //Hay al menos un as.
            }
            val = val + valorCarta;
        }
        // Ahora, val es el valor de la mano, contando cualquier as como 1. 
        // Si hay un as, y si cambiar su valor de 1 a 
        // 11 dejaría el puntaje menor o igual a 21,
        // entonces hazlo sumando los 10 puntos adicionales a val. 
        
        if(as == true && val + 10 <= 21){
            val = val + 10;
        }
        return val;
    }   //Fin getValorBlackjack
}   //fin de clase
