package br.edu.ifsp.spo.java.cards.regras;

import br.edu.ifsp.spo.java.cards.itens.Carta;
import br.edu.ifsp.spo.java.cards.nucleo.Jogador;

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
        throw new UnsupportedOperationException("Método não implementado");
    }
}
