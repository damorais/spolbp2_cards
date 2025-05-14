package br.edu.ifsp.spo.java.cards.ui;

import br.edu.ifsp.spo.java.cards.itens.Carta;
import br.edu.ifsp.spo.java.cards.nucleo.AcaoDoJogador;
import br.edu.ifsp.spo.java.cards.nucleo.Jogador;
import br.edu.ifsp.spo.java.cards.regras.Pontuador;
import br.edu.ifsp.spo.java.cards.regras.PontuadorAsValeOnze;
import br.edu.ifsp.spo.java.cards.regras.PontuadorClassico;

import java.util.List;
import java.util.Scanner;

public class JogoUI {

    public String solicitarNomeJogador(int numeroJogador) {
        System.out.println("Digite o nome do jogador " + numeroJogador);

        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public Pontuador escolherPontuador() {
        System.out.println("Escolha o mecanismo de pontuação:");
        System.out.println("(1) Para pontuação clássica (Padrão)");
        System.out.println("(2) Para pontuação \"Ás vale 11\"");

        Scanner sc = new Scanner(System.in);

        int selecao = sc.nextInt();

        Pontuador pontuador = new PontuadorClassico();

        switch (selecao) {
            case 1 -> pontuador = new PontuadorClassico();
            case 2 -> pontuador = new PontuadorAsValeOnze();
            default -> System.out.println("Utilizando o mecanismo de pontuação padrão.");
        }

        return pontuador;
    }

    public void exibirVencedor(Jogador jogador) {

        System.out.println(jogador.getNome() + " venceu a partida!");

    }

    public void exibirInicioJogo() {
        System.out.println("Iniciando a partida!!!!");
    }

    public void exibirInicioRodada(String nome) {

        System.out.println("Agora é a vez de " + nome);

    }

    public AcaoDoJogador obterAcao() {
        System.out.println("O que você deseja fazer?");

        System.out.println("(1) Comprar uma carta");
        System.out.println("(2) Manter a mão atual");

        var scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();

        return opcao == 1 ? AcaoDoJogador.COMPRAR : AcaoDoJogador.PASSAR;
    }

    public void exibirMao(List<Carta> mao, int pontuacao) {

        System.out.println("Sua mão atual é:");
        for(var carta : mao){
            System.out.println(carta);
        }

        System.out.println("Sua pontuação atual é: " + pontuacao);
    }

    public void exibirFimRodada(String nome) {
        System.out.println("Fim da rodada de " + nome);
    }

    public void exibirEmpate() {
        System.out.println("Rodada empatada! Iniciando uma nova rodada...");
    }
}
