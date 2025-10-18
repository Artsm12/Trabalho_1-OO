package ufjf.dcc025.trabalho.jogo;

import java.util.Random;
import java.util.Scanner;  
import java.util.Set;

public class Duelo {
    private Tabuleiro tabuleiro;
    private Personagem[] player1;
    private Personagem[] player2;
    private final int primeiroJogador;
    private boolean acabou;
    
    private Scanner teclado = new Scanner(System.in);

    public Duelo(int primeiroJogador) {
        this.player1 = new Personagem[3];
        this.player2 = new Personagem[3];
        this.primeiroJogador = primeiroJogador;
        this.acabou = false;
    }
        
    public void inicio() {
        this.tabuleiro = new Tabuleiro();
          
        posicionaPersonagens();
      
        tabuleiro.desenhaTabuleiro();
        System.exit(0);
        
        if(this.primeiroJogador == 1) {
            while(!acabou) {
                turno(1);
                turno(2);
            }
        }
        else {
            while(!acabou) {
                turno(2);
                turno(1);
            }
        }
    }
    
    public void posicionaPersonagens() {
        System.out.println("Jogador " + this.primeiroJogador + " selecione onde colocar seu perosnagem:");
        int i = 0;
        int j = 0;
        this.tabuleiro.selecionaCasa(i, j);
        this.tabuleiro.desenhaTabuleiro();
        
        String input;
        imprimeMenuDeInput();
        
        input = teclado.nextLine().toUpperCase();
        
        while(input != "E") {
            switch(input) {
                case "W" -> {
                    i -= 1;
                    this.tabuleiro.selecionaCasa(i, j);
                    limpaTerminal();
                    break;
                }
                case "A" -> {
                    j -= 1;
                    this.tabuleiro.selecionaCasa(i, j);
                    limpaTerminal();

                    break;
                }
                case "S" -> {
                    i += 1;
                    this.tabuleiro.selecionaCasa(i, j);
                    limpaTerminal();

                    break;
                }
                case "D" -> {
                    j += 1;
                    this.tabuleiro.selecionaCasa(i, j);
                    limpaTerminal();
                    break;
                }
                case "E" -> {
                    break;
                }
                default -> {
                    System.out.println("Por favor, insira um comando valido");
                    imprimeMenuDeInput();
                    break;
                }
            }
            
            limpaTerminal();
            this.tabuleiro.desenhaTabuleiro();
            imprimeMenuDeInput();
            input = teclado.nextLine().toUpperCase();
        }
    }
    
    public void imprimeMenuDeInput() {
        System.out.println("(W) Ir para cima");
        System.out.println("(A) Ir para a esquerda");
        System.out.println("(S) Ir para baixo");
        System.out.println("(D) Ir para a direita");
        System.out.println("(E) Confirma escolha");
    }
    
    public void turno(int j) {
        String input;
        
    }
    
    public static void limpaTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
