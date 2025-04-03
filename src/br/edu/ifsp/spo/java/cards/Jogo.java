package br.edu.ifsp.spo.java.cards;

public class Jogo {

    private Baralho baralho;
    private Jogador jogador1;
    private Jogador jogador2;

    public Jogo(int tamanhoInicialMao){
        this.baralho = new Baralho();
        this.jogador1 = new Jogador("Ada Lovelace");
        this.jogador2 = new Jogador("Grace Hopper");

        for(int i = 0; i < tamanhoInicialMao; i++){
            this.jogador1.receberCarta(this.baralho.tirarCarta());
            this.jogador2.receberCarta(this.baralho.tirarCarta());
        }
    }

    @Override
    public String toString() {
        String resultado = "Jogo de Baralho Genérico";

        resultado += "\n Jogadores: ";
        resultado += this.jogador1.toString();
        resultado += this.jogador2.toString();

        return resultado;
    }
}
