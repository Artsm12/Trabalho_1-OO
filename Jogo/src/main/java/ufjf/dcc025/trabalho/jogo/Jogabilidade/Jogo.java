package ufjf.dcc025.trabalho.jogo.Jogabilidade;
import ufjf.dcc025.trabalho.jogo.TabuleiroUtils.Tabuleiro;
import ufjf.dcc025.trabalho.jogo.SistemaDeReplay.Replay;

import java.util.Scanner;
import java.util.Random;

public class Jogo {
    private int modoDeJogo;
    protected Replay replay;

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
        
        do{
            input = teclado.nextLine().toUpperCase();
            
            switch(input) {
                case "1" -> {
                    Tabuleiro.limpaTerminal();
                    Singleplayer solo = new Singleplayer(coinFlip());
                    solo.inicio();
                    break;
                }
                case "2" -> {
                    Tabuleiro.limpaTerminal();
                    Duelo duelo = new Duelo(coinFlip());
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
        }while(!input.equals("1") || !input.equals("2") || !input.equals("S"));
    }

    public int coinFlip() {
        Random random = new Random();
        int flip = random.nextInt(2);
    
        return flip+1;
    }
}
