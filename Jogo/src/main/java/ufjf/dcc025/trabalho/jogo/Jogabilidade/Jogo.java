package ufjf.dcc025.trabalho.jogo.Jogabilidade;

import ufjf.dcc025.trabalho.jogo.TabuleiroUtils.Tabuleiro;
import ufjf.dcc025.trabalho.jogo.SistemaDeReplay.Replay;

import java.util.Scanner;
import java.util.Random;

public class Jogo {

    public static int modoDeJogo;
    private Duelo duelo;
    private Singleplayer solo;

    public static void menuDeInicio() {
        System.out.println("Selecione uma opcao:\n");
        System.out.println("*------------------------------*");
        System.out.println("| " + Tabuleiro.ANSI_YELLOW + "(1) Solo (contra computador)" + Tabuleiro.ANSI_RESET + " |");
        System.out.println("| " + Tabuleiro.ANSI_GREEN + "(2) Duelo (2 jogadores)" + Tabuleiro.ANSI_RESET + "      |");
        System.out.println("| " + Tabuleiro.ANSI_RED + "(S) Sair" + Tabuleiro.ANSI_RESET + "                     |");
        System.out.println("*------------------------------*");
    }

    public void inicio() {
        String input;
        Scanner teclado = new Scanner(System.in);
        Tabuleiro.limpaTerminal();
        System.out.println("Seja bem-vindo a Batalha Tatica das Casas de Westeros!");
        menuDeInicio();

        do {
            input = teclado.nextLine().toUpperCase();

            switch (input) {
                case "1" -> {
                    this.modoDeJogo = 1;
                    Tabuleiro.limpaTerminal();
                    solo = new Singleplayer(coinFlip());
                    solo.inicio();
                    break;
                }
                case "2" -> {
                    this.modoDeJogo = 2;
                    Tabuleiro.limpaTerminal();
                    duelo = new Duelo(coinFlip());
                    duelo.inicio();
                    break;
                }
                case "S" -> {
                    System.out.println("Obrigado por jogar :)");
                    System.exit(0);
                }
                default -> {
                    System.out.println("\nInput Invalido, tente novamente\n");
                    menuDeInicio();
                    break;
                }
            }
        } while (!input.equals("1") && !input.equals("2") && !input.equals("S"));
        
        finalizaJogo();
    }

    public void finalizaJogo() {
        System.out.println("FIM DE JOGO");
        if (this.modoDeJogo == 1) {
            if (solo.checaTime(2)) {
                System.out.println("O " + Tabuleiro.ANSI_CYAN + "jogador 1" + Tabuleiro.ANSI_RESET + " venceu!!!");
            } else {
                System.out.println("O " + Tabuleiro.ANSI_RED + "bot" + Tabuleiro.ANSI_RESET + " venceu!!!");
            }
        } else {
            if (duelo.checaTime(2)) {
                System.out.println("O " + Tabuleiro.ANSI_CYAN + "jogador 1" + Tabuleiro.ANSI_RESET + " venceu!!!");
            } else {
                System.out.println("O " + Tabuleiro.ANSI_RED + "jogador 2" + Tabuleiro.ANSI_RESET + " venceu!!!");
            }
        }
        
        imprimeMenuDeFimDeJogo();
        Scanner teclado = new Scanner(System.in);
        
        String input;
        
        while(true) {
            input = teclado.nextLine().toUpperCase();
            if(input.equals("S")) {
                System.out.println("Muito obrigado por jogar, esperamos que tenha gostado :)");
                System.exit(0);
            }
            
            switch(input) {
                case "R" -> {
                    Tabuleiro.limpaTerminal();
                    if(this.modoDeJogo == 1)
                        this.solo.assisteReplay();
                    else
                        this.duelo.assisteReplay();
                    
                    imprimeMenuDeFimDeJogo();
                    break;
                }
                case "D" -> {
                    this.inicio();
                }
                default -> {
                    System.out.println("\nInput invalido, tente novamente");
                    imprimeMenuDeFimDeJogo();
                    break;
                }
            }
        }
    }
    
    public static void imprimeMenuDeFimDeJogo() {
        System.out.println("Selecione uma opcao:");
        System.out.println("(R) Ver replay  |  (D) Jogar novamente");
        System.out.println("(S) Sair");
    }

    public int coinFlip() {
        Random random = new Random();
        int flip = random.nextInt(2);

        return flip + 1;
    }

    public int getModoDeJogo() {
        return modoDeJogo;
    }
}
