package br.edu.ifsp.spo.java.cards.ui;

import br.edu.ifsp.spo.java.cards.AppArquivos;
import br.edu.ifsp.spo.java.cards.itens.Baralho;
import br.edu.ifsp.spo.java.cards.itens.Carta;
import br.edu.ifsp.spo.java.cards.nucleo.AcaoDoJogador;
import br.edu.ifsp.spo.java.cards.nucleo.Jogador;
import br.edu.ifsp.spo.java.cards.regras.Pontuador;
import br.edu.ifsp.spo.java.cards.regras.PontuadorAsValeOnze;
import br.edu.ifsp.spo.java.cards.regras.PontuadorClassico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class JogoUI {

    public static final int numeroLinhasCarta = 8;
    public static final String separador = "   ";
    public static final int maximoCartasPorLinha = 7;

    private Map<Carta, String[]> imagensCarta = new HashMap<>();

    public JogoUI(Baralho baralho){
        this.carregarRecursos(baralho);
    }

    private void carregarRecursos(Baralho baralho){
        for(var carta : baralho.getCartas())
            this.imagensCarta.put(carta, this.carregarCarta(carta));
    }

    public String[] carregarCarta(Carta carta) {
        var cartaCarregada = new String[numeroLinhasCarta];

        var caminhoCompleto = String.format("%s/%s/%s.txt", "/cards", carta.getNaipe().toString().toLowerCase(), carta.getValor().toString().toLowerCase());

        try (var bf = new BufferedReader(new InputStreamReader(Objects.requireNonNull(AppArquivos.class.getResourceAsStream(caminhoCompleto))))) {
            String line;

            int numeroLinha = 0;

            while ((line = bf.readLine()) != null) {
                cartaCarregada[numeroLinha] = line;
                numeroLinha++;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return cartaCarregada;
    }


    public String solicitarNomeJogador(int numeroJogador) {
        System.out.println();
        System.out.println("Digite o nome do jogador " + numeroJogador);

        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public int solicitarNumeroRodadas() {
        System.out.println();
        System.out.println("Digite o número de rodadas desejadas");

        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public Pontuador escolherPontuador() {
        System.out.println();
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
        System.out.println();
        System.out.println(jogador.getNome() + " venceu a partida!");
    }

    public void exibirInicioJogo() {
        System.out.println();
        System.out.println("Iniciando a partida!!!!");
        System.out.println();
    }

    public void exibirInicioRodadaJogador(String nome) {
        System.out.println();
        System.out.println("Agora é a vez de " + nome);

    }

    public AcaoDoJogador obterAcao() {
        System.out.println();
        System.out.println("O que você deseja fazer?");

        System.out.println("(1) Comprar uma carta");
        System.out.println("(2) Manter a mão atual");

        var scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();

        return opcao == 1 ? AcaoDoJogador.COMPRAR : AcaoDoJogador.PASSAR;
    }

    public void exibirMao(List<Carta> mao, int pontuacao) {
        System.out.println();
        System.out.println("Sua mão atual é:");

        var cartasAExibir = new ArrayList<String[]>();

        for(var carta : mao){
            var imagemCartaAExibir = this.imagensCarta.get(carta);

            cartasAExibir.add(imagemCartaAExibir);
        }

        exibirImagemCartas(cartasAExibir);

        System.out.println("Sua pontuação atual é: " + pontuacao);
    }

    private void exibirImagemCartas(List<String[]> cartasCarregadas){
        var indiceInicial = 0;
        var indiceFinal = maximoCartasPorLinha;

        var totalLinhasDeCartas = (int) Math.ceil((double)cartasCarregadas.size() / maximoCartasPorLinha);

        for(var linhaCartaAtual = 0; linhaCartaAtual < totalLinhasDeCartas; linhaCartaAtual++){
            if(indiceFinal > cartasCarregadas.size())
                indiceFinal = cartasCarregadas.size();

            var recorteCartasCarregadas = cartasCarregadas.subList(indiceInicial, indiceFinal);
            var cartasParaImpressao = this.construirLinhaDeCartas(recorteCartasCarregadas);

            for(var linhaImpressao : cartasParaImpressao)
                System.out.println(linhaImpressao);

            indiceInicial = indiceFinal;
            indiceFinal = indiceFinal + maximoCartasPorLinha;
        }
    }

    public List<String> construirLinhaDeCartas(List<String[]> cartasCarregadas) throws IllegalArgumentException {

        if(cartasCarregadas == null || cartasCarregadas.size() == 0)
            throw new IllegalArgumentException("A lista passada é vazia ou não contém itens");

        var linhaDeCartas = new String[numeroLinhasCarta];

        for(var carta : cartasCarregadas){
            for(var linha = 0; linha < numeroLinhasCarta; linha++)
                if(linhaDeCartas[linha] == null)
                    linhaDeCartas[linha] = carta[linha];
                else
                    linhaDeCartas[linha] +=  separador + carta[linha];
        }

        return Arrays.asList(linhaDeCartas);
    }

    public void exibirFimRodada(String nome) {
        System.out.println("Fim da rodada de " + nome);
    }

    public void exibirEmpate() {
        System.out.println();
        System.out.println("A partida terminou empatada!");
    }

    public void exibirPontuacaoFinal(String nomeJogador1,
                                     String nomeJogador2,
                                     int pontuacaoJogador1,
                                     int pontuacaoJogador2) {

        System.out.println();
        System.out.println("Resultado da partida");
        System.out.println();
        System.out.println(String.format("%s: %d pontos", nomeJogador1, pontuacaoJogador1));
        System.out.println(String.format("%s: %d pontos", nomeJogador2, pontuacaoJogador2));
    }

    public void exibirInicioRodada(String nomeJogador1,
                                   String nomeJogador2,
                                   int pontuacaoJogador1,
                                   int pontuacaoJogador2) {

        System.out.println("Iniciando uma nova rodada");
        System.out.println();
        System.out.println(String.format("%s: %d pontos", nomeJogador1, pontuacaoJogador1));
        System.out.println(String.format("%s: %d pontos", nomeJogador2, pontuacaoJogador2));
    }
}
