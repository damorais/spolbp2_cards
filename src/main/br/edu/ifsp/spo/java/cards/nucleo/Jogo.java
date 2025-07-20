package br.edu.ifsp.spo.java.cards.nucleo;

import br.edu.ifsp.spo.java.cards.itens.Baralho;
import br.edu.ifsp.spo.java.cards.regras.Pontuador;
import br.edu.ifsp.spo.java.cards.ui.JogoUI;

import java.util.Optional;


record EstadoDoJogo(
        int pontuacaoJogador1,
        int pontuacaoJogador2,
        int rodadaAtual
) {

    public static EstadoDoJogo criarEstadoInicial(){
        return new EstadoDoJogo(0, 0, 1);
    }

    public EstadoDoJogo atualizarEstadoERodada(int pontuacaoRodadaJogador1, int pontuacaoRodadaJogador2){
        return new EstadoDoJogo(
                this.pontuacaoJogador1() + pontuacaoRodadaJogador1,
                this.pontuacaoJogador2() + pontuacaoRodadaJogador2,
                this.rodadaAtual() + 1
        );
    }

}

public class Jogo {

    private Baralho baralho;
    private Jogador jogador1;
    private Jogador jogador2;

    private Pontuador pontuador;

    private JogoUI ui;

    public Jogo(){
        this.baralho = new Baralho();
        this.ui = new JogoUI(this.baralho);

        this.pontuador = this.ui.escolherPontuador();

        this.jogador1 = new Jogador(ui.solicitarNomeJogador(1));
        this.jogador2 = new JogadorIA();
    }

    public void play(){
        var estadoDoJogo = EstadoDoJogo.criarEstadoInicial();
        var numeroMaximoDeRodadas = ui.solicitarNumeroRodadas();

        ui.exibirInicioJogo();

        //Execução das rodadas da partida
        while(estadoDoJogo.rodadaAtual() <= numeroMaximoDeRodadas){
            //TODO: Exibir o inicio da rodada
            ui.exibirInicioRodada(
                    jogador1.getNome(),
                    jogador2.getNome(),
                    estadoDoJogo.pontuacaoJogador1(),
                    estadoDoJogo.pontuacaoJogador2());


            this.distribuirCartas();

            executarRodadaJogador(this.jogador1);
            executarRodadaJogador(this.jogador2);

            //Calcular a pontuacao da rodada e atualizar o estado do jogo
            var pontuacoesRodada = this.pontuador.calcularPontuacaoRodada(this.jogador1, this.jogador2);

            //Atualizar estado do jogo
            estadoDoJogo = estadoDoJogo.atualizarEstadoERodada(
                    pontuacoesRodada.get(this.jogador1),
                    pontuacoesRodada.get(this.jogador2)
            );
        }

        this.resolverVencedorPartida(estadoDoJogo);
    }

    private void resolverVencedorPartida(EstadoDoJogo estadoDoJogo) {
        var diferencaPontuacao = estadoDoJogo.pontuacaoJogador1() - estadoDoJogo.pontuacaoJogador2();

        if(diferencaPontuacao > 0){
            ui.exibirVencedor(this.jogador1);
        }else if(diferencaPontuacao < 0){
            ui.exibirVencedor(this.jogador2);
        }else{
            //Exibir empate!
            ui.exibirEmpate();
        }

        ui.exibirPontuacaoFinal(
                jogador1.getNome(),
                jogador2.getNome(),
                estadoDoJogo.pontuacaoJogador1(),
                estadoDoJogo.pontuacaoJogador2());
    }

    private void executarRodadaJogador(Jogador jogador) {
        ui.exibirInicioRodadaJogador(jogador.getNome());

        AcaoDoJogador acao = AcaoDoJogador.PASSAR;

        do {
            var pontuacao = this.pontuador.verificarPontuacao(jogador.getMao());

            if(jogador instanceof JogadorIA){
                var ia = (JogadorIA) jogador;

                acao = ia.decidir(pontuacao);
            }else{
                ui.exibirMao(jogador.getMao(), pontuacao);

                acao = ui.obterAcao();
            }

            if(acao == AcaoDoJogador.COMPRAR)
                jogador.receberCarta(this.baralho.tirarCarta());

        } while(acao == AcaoDoJogador.COMPRAR);

        ui.exibirFimRodada(jogador.getNome());
    }

    private void distribuirCartas() {
        this.baralho.adicionarDescartes(this.jogador1.descartarMao());
        this.baralho.adicionarDescartes(this.jogador2.descartarMao());

        this.jogador1.receberCarta(this.baralho.tirarCarta());
        this.jogador2.receberCarta(this.baralho.tirarCarta());

        this.jogador1.receberCarta(this.baralho.tirarCarta());
        this.jogador2.receberCarta(this.baralho.tirarCarta());
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
