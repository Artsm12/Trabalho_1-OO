package ufjf.dcc025.trabalho.jogo.SistemaDeReplay;

import ufjf.dcc025.trabalho.jogo.TabuleiroUtils.Tabuleiro;
import ufjf.dcc025.trabalho.jogo.Personagens.Personagem;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Replay {

    private List<Jogada> jogadas = new ArrayList<>();
    private Scanner teclado = new Scanner(System.in);

    public void salvaEstadoDoJogo(Tabuleiro tabuleiro, Personagem p) {
        Jogada jogada = new Jogada(tabuleiro, p);
        jogadas.add(jogada);
    }

    public void salvaEstadoDoJogo(Tabuleiro tabuleiro, Personagem aliado, Personagem inimigo, int dano) {
        Jogada jogada = new Jogada(tabuleiro, aliado, inimigo, dano);
        jogadas.add(jogada);
    }

    public void verReplay() {
        String input;

        for (Jogada jogada : jogadas) {
            jogada.imprimeJogada();

            imprimeMenuDeReplay();

            do {
                input = teclado.nextLine().toUpperCase();

                switch (input) {
                    case "D" -> {
                        continue;
                    }
                    case "S" -> {
                        System.out.println("Muito obrigado por jogar, esperamos que tenha gostado :)");
                        System.exit(0);
                    }
                    default -> {
                        System.out.println("\nInput invalido!");
                        System.out.println("");
                        imprimeMenuDeReplay();
                        break;
                    }
                }
            } while (!input.equals("D") || !input.equals("S"));
            
            Tabuleiro.limpaTerminal();
        }
    }

    public static void imprimeMenuDeReplay() {
        System.out.println("Menu de Replay");
        System.out.println("(D) Proxima Jogada");
        System.out.println("(S) Sair");
    }
}
