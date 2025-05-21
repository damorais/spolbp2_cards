package br.edu.ifsp.spo.java.cards.regras;

import br.edu.ifsp.spo.java.cards.itens.Carta;
import br.edu.ifsp.spo.java.cards.nucleo.Jogador;

import java.util.List;
import java.util.Map;

public interface Pontuador {
    int verificarPontuacao(List<Carta> cartas);

    Map<Jogador, Integer> calcularPontuacaoRodada(Jogador jogador1, Jogador jogador2);
}
