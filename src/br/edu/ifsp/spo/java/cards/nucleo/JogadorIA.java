package br.edu.ifsp.spo.java.cards.nucleo;

public class JogadorIA extends Jogador {

    public JogadorIA(){
        super("HAL-9000");
    }

    public AcaoDoJogador decidir(int pontuacao){
        return pontuacao < 18 ? AcaoDoJogador.COMPRAR : AcaoDoJogador.PASSAR;
    }
}
