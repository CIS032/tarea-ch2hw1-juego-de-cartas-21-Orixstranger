/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.ArrayList;

/**
 *
 * @author Cliente
 */
public class Blackjack {

    public static boolean jugarBlackjack() {
        Baraja baraja;  //Una baraja de cartas. Una nueva baraja para cada juego.
        ManoDeBlackjack manoCrupier;    //Mano de la persona que trabaja en el casino
        ManoDeBlackjack manoUsuario;    //Mano del usuario

        baraja = new Baraja();
        manoCrupier = new ManoDeBlackjack();
        manoUsuario = new ManoDeBlackjack();
        
        

        /*Baraja el mazo, reparte dos cartas a cada jugador. */
        baraja.barajar();
        manoCrupier.agregarCarta(baraja.repartoCartas());
        manoCrupier.agregarCarta(baraja.repartoCartas());

        manoUsuario.agregarCarta(baraja.repartoCartas());
        manoUsuario.agregarCarta(baraja.repartoCartas());

        System.out.println();
        System.out.println();

        /* Verifica si uno de los jugadores tiene Blackjack (dos cartas que suman un total de 21).
         El jugador con Blackjack gana el juego. El distribuidor gana lazos.
         */
        if (manoCrupier.getValordeBlackjack() == 21) {
            System.out.println("El crupier tiene la " + manoCrupier.getCarta(0) + " y " + manoCrupier.getCarta(1) + ".");
            System.out.println("El usuario tiene la " + manoUsuario.getCarta(0) + " y " + manoUsuario.getCarta(1) + ".");
            System.out.println();
            System.out.println("Crupier tiene Blackjack. Crupier gana.");
            return false;
        }
        
        if(manoUsuario.getValordeBlackjack() == 21){
            System.out.println("El crupier tiene la " + manoCrupier.getCarta(0) + " y " + manoCrupier.getCarta(1) + '.');
            System.out.println("El ususario tiene la " + manoUsuario.getCarta(0) + " y " + manoUsuario.getCarta(1) + '.');
            System.out.println();
            System.out.println("Tu tienes Blackjack. Ganaste");
        }
        /* Si ninguno de los jugadores tiene Blackjack, juega el juego. Primero el usuario
          tiene la oportunidad de robar cartas (es decir, de "golpear"). El bucle while termina
          cuando el usuario elige "Stand". Si el usuario supera los 21,
          el usuario pierde inmediatamente.
         */
        while (true) {
            /* Mostrar tarjetas de usuario, y dejar que el usuario decida continuar o pararse. */
            System.out.println();
            System.out.println();
            System.out.println("Tus cartas son: ");
            for (int i = 0; i < manoCrupier.getContadorCartas(); i++) {
                System.out.println("" + manoUsuario.getCarta(i));
            }
            System.out.println("Su total es " + manoUsuario.getValordeBlackjack());
            System.out.println();
            System.out.println("El concecionario muestra la " + manoCrupier.getCarta(0));
            System.out.println();
            System.out.println("Continuar (C) o Parar (P)");
            char accionUsuario; //Respuesta del usuario, 'C' o 'P'.
            do {
                accionUsuario = Character.toUpperCase(TextIO.getlnChar());
                if (accionUsuario != 'C' && accionUsuario != 'P') {
                    System.out.print("Responda C o P");
                }
            } while (accionUsuario != 'C' && accionUsuario != 'P');

            /* Si el usuario tiene éxito, el usuario obtiene una tarjeta. Si el usuario está parado,
              el bucle termina (y es el turno del crupier de robar cartas).
             */
            if (accionUsuario == 'P') {
                // Loop ends; el usuario ha terminado de tomar cartas.
                break;
            } else {
                // userAction es 'H'. Dale una tarjeta al usuario.  
                // Si el usuario supera los 21, el usuario pierde.
                Carta nuevaCarta = baraja.repartoCartas();
                manoUsuario.agregarCarta(nuevaCarta);
                System.out.println();
                System.out.println("El usuario acierta");
                System.out.println("Su carta es la " + nuevaCarta);
                System.out.println("Su total es ahora " + manoUsuario.getValordeBlackjack());
                if (manoUsuario.getValordeBlackjack() > 21) {
                    System.out.println();
                    System.out.println("Has fallado al pasar de 21. Pierdes");
                    System.out.println("La otra carta del concecionario era la " + manoCrupier.getCarta(1));
                    return false;
                }
            }
        }

        /* Si llegamos a este punto, el usuario tiene Stood con 21 o menos. Ahora, es 
         la oportunidad del distribuidor para dibujar. El distribuidor roba las cartas hasta que el 
         total del repartidor sea> 16. Si el repartidor supera los 21, el repartidor pierde. 
         */
        System.out.println();
        System.out.println("Usuario parado");
        System.out.println("Las cartas del crupier son ");
        System.out.println("    " + manoCrupier.getCarta(0));
        System.out.println("    " + manoCrupier.getCarta(1));

        while (manoCrupier.getValordeBlackjack() <= 16) {
            Carta nuevaCarta = baraja.repartoCartas();
            System.out.println("Crupier continua y obtiene la " + nuevaCarta);
            manoCrupier.agregarCarta(nuevaCarta);
            if (manoCrupier.getValordeBlackjack() > 21) {
                System.out.println();
                System.out.println("Crupier detenido al pasar de 21. Usted gana");
                return true;
            }
        }
        System.out.println("El total del concecionario es " + manoCrupier.getValordeBlackjack());
        /* Si llegamos a este punto, ambos jugadores tienen 21 o menos. Nosotros
         puede determinar el ganador comparando los valores de sus manos. */

        System.out.println();
        if (manoCrupier.getValordeBlackjack() == manoUsuario.getValordeBlackjack()) {
            System.out.println("El crupier gana en un empate. Pierdes");
            return false;
        } else if (manoCrupier.getValordeBlackjack() > manoUsuario.getValordeBlackjack()) {
            System.out.println("Crupier gana, " + manoCrupier.getValordeBlackjack() + " puntos a " + manoUsuario.getValordeBlackjack() + ".");
            return false;

        }else{
            System.out.println("Usted gana, " + manoUsuario.getValordeBlackjack() + " puntos a " + manoCrupier.getValordeBlackjack() + ".");
            return true;
        }
    }

    public Blackjack() {
    }
    
    public static void main(String[] args) {

        int dinero; //CAntidad de dinero que tiene el usuario
        int apuesta;    //CAntidad de apuestas del usuario en un juego.
        boolean usuarioGanador; //¿El usuario ganó el juego?
        
        System.out.println("Bienvenido al juego Blackjack");
        System.out.println();
        
        dinero = 100;   //El usuario comienza con $ 100
        while(true){
            System.out.println("Tiene " + dinero + " dólares");
            do{
                System.out.println("¿Cuantos dólares quiere apostar? (Ingresa 0 para finalizar)");
                System.out.println("?");
                
                apuesta = TextIO.getlnInt();
                if(apuesta < 0 || apuesta > dinero){
                    System.out.println("Su respuesta debe estar entre 0 y " + dinero + '.');
                }
            }while(apuesta < 0 || apuesta > dinero);
            if(apuesta == 0){
                break;
            }
            usuarioGanador = jugarBlackjack();
            if(usuarioGanador){
                dinero = dinero + apuesta; 
            }else{
                dinero = dinero - apuesta;
            }
            System.out.println();
            if(dinero == 0){
                System.out.println("Parece que te has quedado sin dinero");
                break;
            }
        }
        System.out.println();
        System.out.println("Sales con $ " + dinero + '.');
        
        
    }

}
