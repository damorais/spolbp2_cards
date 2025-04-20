package br.edu.ifsp.spo.java.cards.nucleo;

import br.edu.ifsp.spo.java.cards.itens.Baralho;
import br.edu.ifsp.spo.java.cards.regras.Pontuador;
import br.edu.ifsp.spo.java.cards.ui.JogoUI;

public class Jogo {

    private Baralho baralho;
    private Jogador jogador1;
    private Jogador jogador2;

    private Pontuador pontuador;

    private JogoUI ui;

    public Jogo(){
        this.ui = new JogoUI();

        this.pontuador = this.ui.escolherPontuador();

        this.baralho = new Baralho();
        this.jogador1 = new Jogador(ui.solicitarNomeJogador(1));
        this.jogador2 = new Jogador(ui.solicitarNomeJogador(2));

        for(int i = 0; i < 2; i++){
            this.jogador1.receberCarta(this.baralho.tirarCarta());
            this.jogador2.receberCarta(this.baralho.tirarCarta());
        }
    }

    @Override
    public String toString() {
        String resultado = "Jogo de Baralho Genérico";

        resultado += "\n Jogadores: ";
        resultado += this.jogador1.toString();
        resultado += "\n A pontuação do jogador 1 é: " + this.pontuador.verificarPontuacao(this.jogador1.getMao());
        resultado += this.jogador2.toString();
        resultado += "\n A pontuação do jogador 2 é: " + this.pontuador.verificarPontuacao(this.jogador2.getMao());

        return resultado;
    }
}
