package br.edu.ifsp.spo.java.cards.regras;

import br.edu.ifsp.spo.java.cards.itens.Carta;
import br.edu.ifsp.spo.java.cards.nucleo.Jogador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PontuadorAsValeOnze implements Pontuador {
    public int verificarPontuacao(List<Carta> cartas){
        int pontuacao = 0;

        for(Carta carta : cartas){
            switch(carta.getValor()){
                case AS -> pontuacao += 11;
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

        return pontuacao;
    }

    @Override
    public Map<Jogador, Integer> calcularPontuacaoRodada(Jogador jogador1, Jogador jogador2) {

        var pontuacaoMaoJogador1 = this.verificarPontuacao(jogador1.getMao());
        var pontuacaoMaoJogador2 = this.verificarPontuacao(jogador2.getMao());

        var pontuacaoRodadaJogador1 = 0;
        var pontuacaoRodadaJogador2 = 0;

        var resultado = new HashMap<Jogador, Integer>();

        if(pontuacaoMaoJogador1 < 21 && pontuacaoMaoJogador2 > 21){
            pontuacaoRodadaJogador1 = pontuacaoMaoJogador1;
            pontuacaoRodadaJogador2 = -5;
        }

        if(pontuacaoMaoJogador1 > 21 && pontuacaoMaoJogador2 < 21) {
            pontuacaoRodadaJogador1 = -5;
            pontuacaoRodadaJogador2 = pontuacaoMaoJogador2;
        }

        if(pontuacaoMaoJogador1 > 21 && pontuacaoMaoJogador2 > 21) {
            pontuacaoRodadaJogador1 = 21 - pontuacaoMaoJogador1;
            pontuacaoRodadaJogador2 = 21 - pontuacaoMaoJogador2;
        }

        if(pontuacaoMaoJogador1 == 21 && pontuacaoMaoJogador2 < 21){
            pontuacaoRodadaJogador1 = 30;
            pontuacaoRodadaJogador2 = 0;
        }

        if(pontuacaoMaoJogador1 < 21 && pontuacaoMaoJogador2 == 21){
            pontuacaoRodadaJogador1 = 0;
            pontuacaoRodadaJogador2 = 30;
        }

        if(pontuacaoMaoJogador1 < 21 && pontuacaoMaoJogador2 < 21 && (pontuacaoMaoJogador1 > pontuacaoMaoJogador2)){
            pontuacaoRodadaJogador1 = pontuacaoMaoJogador1 - pontuacaoMaoJogador2;
            pontuacaoRodadaJogador2 = 0;
        }

        if(pontuacaoMaoJogador1 < 21 && pontuacaoMaoJogador2 < 21 && (pontuacaoMaoJogador1 < pontuacaoMaoJogador2)){
            pontuacaoRodadaJogador1 = 0;
            pontuacaoRodadaJogador2 = pontuacaoMaoJogador2 - pontuacaoMaoJogador1;
        }

        if(pontuacaoMaoJogador1 == 21 && pontuacaoMaoJogador2 == 21){
            pontuacaoRodadaJogador1 = 21;
            pontuacaoRodadaJogador2 = 21;
        } else if(pontuacaoMaoJogador1 == pontuacaoMaoJogador2){
            pontuacaoRodadaJogador1 = 10;
            pontuacaoRodadaJogador2 = 10;
        }

        resultado.put(jogador1, pontuacaoRodadaJogador1);
        resultado.put(jogador2, pontuacaoRodadaJogador2);

        return resultado;
    }
}
