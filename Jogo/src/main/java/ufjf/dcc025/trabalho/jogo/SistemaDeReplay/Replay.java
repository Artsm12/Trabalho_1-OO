package ufjf.dcc025.trabalho.jogo.SistemaDeReplay;
import ufjf.dcc025.trabalho.jogo.TabuleiroUtils.Tabuleiro;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Replay {
    private List<Tabuleiro> tabuleiros = new ArrayList<>();
    private List<String> jogadas = new ArrayList<>();
    
    public void adicionaTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiros.add(tabuleiro);
    }
    /*
    public void rodaReplay() {
        String input;
        Scanner teclado = new Scanner(System.in);
        
        for(Tabuleiro tabuleiro : this.tabuleiros) {
            tabuleiro.desenhaTabuleiro();
            imprimeMenuDeReplay();
            input = teclado.nextLine().toUpperCase();
            
            switch(input) {
                case "S" -> {
                    System.out.println("\nMuito obrigado por jogar, esperamos que tenha gostado :)");
                    System.exit(0);
                }
                case "D" -> {
                    break;
                }
                default -> {
                    while(input != "S" || input != "D") {
                        
                    }
                }
            }
        }
    }
    */
    public static void imprimeMenuDeReplay() {
        System.out.println("(D) Proxima jogada");
        System.out.println("(S) Sair");
    }
}
