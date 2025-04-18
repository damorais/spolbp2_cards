package br.edu.ifsp.spo.java.cards;

import java.util.List;

public class Pontuador {

    public int verificarPontuacao(List<Carta> cartas){

        int pontuacao = 0;

        for(Carta carta : cartas){
            switch(carta.getValor()){
                case AS: pontuacao += 1;
                case DOIS: pontuacao += 2;
                case TRES: pontuacao += 3;
                case QUATRO: pontuacao += 4;
                case CINCO: pontuacao += 5;
                case SEIS: pontuacao += 6;
                case SETE: pontuacao += 7;
                case OITO: pontuacao += 8;
                case NOVE: pontuacao += 9;
                case DEZ, DAMA, VALETE, REI: pontuacao += 10;
            }
        }

        return pontuacao;
    }
}
