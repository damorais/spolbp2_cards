package br.edu.ifsp.spo.java.cards.ui;

import java.util.Scanner;

public class JogoUI {

    public String solicitarNomeJogador(int numeroJogador) {
        System.out.println("Digite o nome do jogador " + numeroJogador);

        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
