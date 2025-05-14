package br.edu.ifsp.spo.java.cards.nucleo;

import br.edu.ifsp.spo.java.cards.itens.Baralho;
import br.edu.ifsp.spo.java.cards.regras.Pontuador;
import br.edu.ifsp.spo.java.cards.ui.JogoUI;

import javax.swing.text.html.Option;
import java.util.Optional;

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

    public void play(){
        Optional<Jogador> vencedor = Optional.empty();

        while(vencedor.isEmpty()){
            ui.exibirInicioJogo();

            executarRodada(this.jogador1);
            executarRodada(this.jogador2);


            vencedor = this.verificarVencedor();

            if(vencedor.isPresent()){
                ui.exibirVencedor(vencedor.get());
            }else{
                this.reiniciarRodada();
            }
        }
    }

    private void executarRodada(Jogador jogador) {
        ui.exibirInicioRodada(jogador.getNome());

        AcaoDoJogador acao = AcaoDoJogador.PASSAR;

        do {
            var pontuacao = this.pontuador.verificarPontuacao(jogador.getMao());

            ui.exibirMao(jogador.getMao(), pontuacao);

            acao = ui.obterAcao();

            if(acao == AcaoDoJogador.COMPRAR)
                jogador.receberCarta(this.baralho.tirarCarta());

        } while(acao == AcaoDoJogador.COMPRAR);

        ui.exibirFimRodada(jogador.getNome());
    }

    private Optional<Jogador> verificarVencedor() {
        var pontuacaoJogador1 = this.pontuador.verificarPontuacao(this.jogador1.getMao());
        var pontuacaoJogador2 = this.pontuador.verificarPontuacao(this.jogador2.getMao());

        var empate = (pontuacaoJogador1 > 21 && pontuacaoJogador2 > 21) || (pontuacaoJogador1 == pontuacaoJogador2);

        Optional<Jogador> vencedor = Optional.empty();

        if(!empate){
            if(pontuacaoJogador1 > 21)
                vencedor = Optional.of(this.jogador2);
            else if(pontuacaoJogador2 > 21)
                vencedor = Optional.of(this.jogador1);
            else
                vencedor = Optional.of(pontuacaoJogador1>pontuacaoJogador2? this.jogador1 : this.jogador2);
        }else{
            ui.exibirEmpate();
        }

        return vencedor;
    }

    private void reiniciarRodada() {
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
