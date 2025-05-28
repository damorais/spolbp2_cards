package br.edu.ifsp.spo.java.cards.regras;

import br.edu.ifsp.spo.java.cards.itens.Carta;
import br.edu.ifsp.spo.java.cards.itens.Naipe;
import br.edu.ifsp.spo.java.cards.itens.Valor;
import br.edu.ifsp.spo.java.cards.nucleo.Jogador;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PontuadorClassicoTest {
    @Test
    void verificarPontuacaoQuandoListaDeCartasENula(){
        //Classe sob teste
        var pontuador = new PontuadorClassico();

        //1. Configuração do cenário
        List<Carta> cartas = null;
        var pontuacaoEsperada = 0;

        //2. Execução
        var pontuacaoObtida = pontuador.verificarPontuacao(cartas);

        //3. Verificação
        assertEquals(pontuacaoEsperada, pontuacaoObtida);
    }

    @Test
    void verificarPontuacaoQuandoListaDeCartasEVazia(){
        //Classe sob teste
        var pontuador = new PontuadorClassico();

        //1. Configuração do cenário
        List<Carta> cartas = new ArrayList<>();
        var pontuacaoEsperada = 0;

        //2. Execução
        var pontuacaoObtida = pontuador.verificarPontuacao(cartas);

        //3. Verificação
        assertEquals(pontuacaoEsperada, pontuacaoObtida);
    }

    @Test
    void verificarPontuacaoQuandoListaDeCartasContemCartas(){
        //TODO: Este cenário possui um problema. Você consegue identificar?
        // Dica: Altere o código do "verificarPontuacao", trocando o "+=" por "=" em
        // "case DOIS -> pontuacao += 2;" e veja o que acontece

        //Classe sob teste
        var pontuador = new PontuadorClassico();

        //1. Configuração do cenário
        List<Carta> cartas = new ArrayList<>(Arrays.asList(
                new Carta(Naipe.COPAS, Valor.DEZ),
                new Carta(Naipe.ESPADAS, Valor.SETE),
                new Carta(Naipe.OUROS, Valor.AS)
        ));
        var pontuacaoEsperada = 18;

        //2. Execução
        var pontuacaoObtida = pontuador.verificarPontuacao(cartas);

        //3. Verificação
        assertEquals(pontuacaoEsperada, pontuacaoObtida);
    }

    @Test
    void calcularPontuacaoRodadaQuandoEmpate(){
        var pontuador = new PontuadorClassico();

        //1. Configuração
        var conjuntoCartasNaoEstourado1 = new ArrayList<>(Arrays.asList(
                new Carta(Naipe.COPAS, Valor.DEZ),
                new Carta(Naipe.ESPADAS, Valor.SETE),
                new Carta(Naipe.OUROS, Valor.AS)
        ));

        var conjuntoCartasNaoEstourado2 = new ArrayList<>(Arrays.asList(
                new Carta(Naipe.COPAS, Valor.SEIS),
                new Carta(Naipe.ESPADAS, Valor.SEIS),
                new Carta(Naipe.OUROS, Valor.SEIS)
        ));

        var jogador1 = new Jogador("Crash Test Dummy 1");
        conjuntoCartasNaoEstourado1.forEach(jogador1::receberCarta);

        var jogador2 = new Jogador("Crash Test Dummy 2");
        conjuntoCartasNaoEstourado2.forEach(jogador2::receberCarta);

        var resultadoEsperado = new HashMap<>(Map.of(
                jogador1, 10,
                jogador2, 10
        ));

        //2. Execução
        var resultadoObtido = pontuador.calcularPontuacaoRodada(jogador1, jogador2);

        //3. Asserção / Verificação
        assertEquals(resultadoEsperado, resultadoObtido);
    }


}