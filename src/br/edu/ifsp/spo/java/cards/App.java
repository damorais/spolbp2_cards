package br.edu.ifsp.spo.java.cards;

import java.text.MessageFormat;

public class App {

    public static void main(String[] args) {

        Baralho baralho = new Baralho();

        Carta carta = baralho.tirarCarta();

        System.out.println(carta);

        System.out.println(MessageFormat.format("Cartas restantes no baralho: {0}", baralho.cartasRestantes()));
    }
}