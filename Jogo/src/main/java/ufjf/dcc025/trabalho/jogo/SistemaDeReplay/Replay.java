package ufjf.dcc025.trabalho.jogo.SistemaDeReplay;

import ufjf.dcc025.trabalho.jogo.TabuleiroUtils.Tabuleiro;
import ufjf.dcc025.trabalho.jogo.Personagens.Personagem;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Replay {

    private List<Jogada> jogadas = new ArrayList<>();
    private Scanner teclado = new Scanner(System.in);

    public void salvarEstadoDoJogo(Tabuleiro tabuleiro, String mensagem) {
        Jogada jogada = new Jogada(tabuleiro, mensagem);
        jogadas.add(jogada);
    }

    public void salvaEstadoDoJogo(Tabuleiro tabuleiro, int jogador) {
        Jogada jogada = new Jogada(tabuleiro, jogador);
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

            input = teclado.nextLine().toUpperCase();

            while (!input.equals("D") && !input.equals("S")) {
                System.out.println("\nInput invalido, tente novamente\n");
                imprimeMenuDeReplay();
                input = teclado.nextLine().toUpperCase();
            }
            
            if (input.equals("S")) {
                return;
            }

            Tabuleiro.limpaTerminal();
        }
    }

    public static void imprimeMenuDeReplay() {
        System.out.println("Menu de Replay");
        System.out.println("(D) Proxima Jogada");
        System.out.println("(S) Sair");
    }
}
