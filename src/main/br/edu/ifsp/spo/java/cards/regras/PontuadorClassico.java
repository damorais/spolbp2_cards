package br.edu.ifsp.spo.java.cards.regras;

import br.edu.ifsp.spo.java.cards.itens.Carta;
import br.edu.ifsp.spo.java.cards.nucleo.Jogador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PontuadorClassico implements Pontuador {
    public int verificarPontuacao(List<Carta> cartas){

        int pontuacao = 0;

        if(cartas != null) {
            for (Carta carta : cartas) {
                switch (carta.getValor()) {
                    case AS -> pontuacao += 1;
                    case DOIS -> pontuacao += 2;
                    case TRES -> pontuacao += 3;
                    case QUATRO -> pontuacao += 4;
                    case CINCO -> pontuacao += 5;
                    case SEIS -> pontuacao += 6;
                    case SETE -> pontuacao += 7;
                    case OITO -> pontuacao += 8;
                    case NOVE -> pontuacao += 9;
                    case DEZ, DAMA, VALETE, REI -> pontuacao += 10;
                }
            }
        }

        return pontuacao;
    }

    @Override
    public Map<Jogador, Integer> calcularPontuacaoRodada(Jogador jogador1, Jogador jogador2) {

        var pontuacaoMaoJogador1 = this.verificarPontuacao(jogador1.getMao());
        var pontuacaoMaoJogador2 = this.verificarPontuacao(jogador2.getMao());

        var pontuacaoRodadaJogador1 = 0;
        var pontuacaoRodadaJogador2 = 0;

        if(pontuacaoMaoJogador1 == pontuacaoMaoJogador2){
            pontuacaoRodadaJogador1 = 10;
            pontuacaoRodadaJogador2 = 10;
        }

        return new HashMap<>(Map.of(
                jogador1, pontuacaoRodadaJogador1,
                jogador2, pontuacaoRodadaJogador2
        ));
    }
}
