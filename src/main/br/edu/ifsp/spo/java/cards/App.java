package br.edu.ifsp.spo.java.cards;

import br.edu.ifsp.spo.java.cards.itens.Baralho;
import br.edu.ifsp.spo.java.cards.itens.Carta;
import br.edu.ifsp.spo.java.cards.nucleo.Jogo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {
        Jogo vinteUm = new Jogo();
        vinteUm.play();
    }

//    public static final int numeroLinhasCarta = 8;
//    public static final String separador = "   ";
//    public static final int maximoCartasPorLinha = 7;
//    public static final int numeroCartasExemplo = 52;
//
//    public static String[] carregarCarta(Carta carta) {
//        var cartaCarregada = new String[numeroLinhasCarta];
//
//        var caminhoCompleto = String.format("%s/%s/%s.txt", "/cards", carta.getNaipe().toString().toLowerCase(), carta.getValor().toString().toLowerCase());
//
//        try (var bf = new BufferedReader(new InputStreamReader(App.class.getResourceAsStream(caminhoCompleto)))) {
//            String line;
//
//            int numeroLinha = 0;
//
//            while ((line = bf.readLine()) != null) {
//                cartaCarregada[numeroLinha] = line;
//                numeroLinha++;
//            }
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        return cartaCarregada;
//    }

//    public static List<String> construirLinhaDeCartas(List<String[]> cartasCarregadas) throws IllegalArgumentException {
//
//        if(cartasCarregadas == null || cartasCarregadas.size() == 0)
//            throw new IllegalArgumentException("A lista passada é vazia ou não contém itens");
//
//        var linhaDeCartas = new String[numeroLinhasCarta];
//
//        for(var carta : cartasCarregadas){
//            for(var linha = 0; linha < numeroLinhasCarta; linha++)
//                if(linhaDeCartas[linha] == null)
//                    linhaDeCartas[linha] = carta[linha];
//                else
//                    linhaDeCartas[linha] +=  separador + carta[linha];
//        }
//
//        return Arrays.asList(linhaDeCartas);
//    }

//    public static void main(String[] args) throws IOException, InterruptedException {
//
//        var baralho = new Baralho();
//
//        var cartas = new ArrayList<Carta>();
//
//        for (var i = 0; i < numeroCartasExemplo; i++)
//            cartas.add(baralho.tirarCarta());
//
//        System.out.println(cartas);
//
//        var cartasCarregadas = new ArrayList<String[]>();
//
//        for (var carta : cartas) {
//            var cartaCarregada = carregarCarta(carta);
//
//            cartasCarregadas.add(cartaCarregada);
//        }
//
//        var indiceInicial = 0;
//        var indiceFinal = maximoCartasPorLinha;
//
//        var totalLinhasDeCartas = (int) Math.ceil((double)cartasCarregadas.size() / maximoCartasPorLinha);
//
//        for(var linhaCartaAtual = 0; linhaCartaAtual < totalLinhasDeCartas; linhaCartaAtual++){
//            if(indiceFinal > cartasCarregadas.size())
//                indiceFinal = cartasCarregadas.size();
//
//            var recorteCartasCarregadas = cartasCarregadas.subList(indiceInicial, indiceFinal);
//            var cartasParaImpressao = construirLinhaDeCartas(recorteCartasCarregadas);
//
//            for(var linhaImpressao : cartasParaImpressao)
//                System.out.println(linhaImpressao);
//
//            indiceInicial = indiceFinal;
//            indiceFinal = indiceFinal + maximoCartasPorLinha;
//        }
//    }
}