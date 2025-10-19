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

        for (int i = 0; i < 3; i++) {
            if (this.primeiroJogador == 1) {
                posicionaPersonagem(1, i);
                posicionaPersonagem(2, i);
            } else {
                posicionaPersonagem(2, i);
                posicionaPersonagem(1, i);
            }
        }

        tabuleiro.desenhaTabuleiro();
        System.exit(0);

        if (this.primeiroJogador == 1) {
            while (!acabou) {
                turno(1);
                turno(2);
            }
        } else {
            while (!acabou) {
                turno(2);
                turno(1);
            }
        }
    }

    public void posicionaPersonagem(int quemJoga, int indice) {
        System.out.println("Jogador " + quemJoga + " selecione onde colocar seu perosnagem:");
        int i = 0;
        int j = 0;
        this.tabuleiro.selecionaCasa(i, j);
        this.tabuleiro.desenhaTabuleiro();

        String input;
        imprimeMenuDeInput();

        input = teclado.nextLine().toUpperCase();

        while (true) {

            switch (input) {
                case "W" -> {
                    if (i - 1 < 0) {
                        System.out.println("Posicao invalida. Por favor, selecione outro movimento");
                        imprimeMenuDeInput();
                        break;
                    }
                    i -= 1;
                    this.tabuleiro.selecionaCasa(i, j);
                    limpaTerminal();
                    this.tabuleiro.desenhaTabuleiro();
                    imprimeMenuDeInput();
                    break;
                }
                case "A" -> {
                    if (j - 1 < 0) {
                        System.out.println("Posicao invalida. Por favor, selecione outro movimento");
                        imprimeMenuDeInput();
                        break;
                    }
                    j -= 1;
                    this.tabuleiro.selecionaCasa(i, j);
                    limpaTerminal();
                    this.tabuleiro.desenhaTabuleiro();
                    imprimeMenuDeInput();
                    break;
                }
                case "S" -> {
                    if (i + 1 >= 10) {
                        System.out.println("Posicao invalida. Por favor, selecione outro movimento");
                        imprimeMenuDeInput();
                        break;
                    }
                    i += 1;
                    this.tabuleiro.selecionaCasa(i, j);
                    limpaTerminal();
                    this.tabuleiro.desenhaTabuleiro();
                    imprimeMenuDeInput();
                    break;
                }
                case "D" -> {
                    if (j + 1 >= 10) {
                        System.out.println("Posicao invalida. Por favor, selecione outro movimento");
                        imprimeMenuDeInput();
                        break;
                    }
                    j += 1;
                    this.tabuleiro.selecionaCasa(i, j);
                    limpaTerminal();
                    this.tabuleiro.desenhaTabuleiro();
                    imprimeMenuDeInput();
                    break;
                }
                case "AW", "WA" -> {
                    if (j - 1 < 0 || i - 1 < 0) {
                        System.out.println("Posicao invalida. Por favor, selecione outro movimento");
                        imprimeMenuDeInput();
                        break;
                    }
                    i -= 1;
                    j -= 1;
                    this.tabuleiro.selecionaCasa(i, j);
                    limpaTerminal();
                    this.tabuleiro.desenhaTabuleiro();
                    imprimeMenuDeInput();
                    break;

                }
                case "DW", "WD" -> {
                    if (j + 1 >= 10 || i - 1 < 0) {
                        System.out.println("Posicao invalida. Por favor, selecione outro movimento");
                        imprimeMenuDeInput();
                        break;
                    }
                    i -= 1;
                    j += 1;
                    this.tabuleiro.selecionaCasa(i, j);
                    limpaTerminal();
                    this.tabuleiro.desenhaTabuleiro();
                    imprimeMenuDeInput();
                    break;
                }
                case "AS", "SA" -> {
                    if (j - 1 < 0 || i + 1 >= 10) {
                        System.out.println("Posicao invalida. Por favor, selecione outro movimento");
                        imprimeMenuDeInput();
                        break;
                    }
                    i += 1;
                    j -= 1;
                    this.tabuleiro.selecionaCasa(i, j);
                    limpaTerminal();
                    this.tabuleiro.desenhaTabuleiro();
                    imprimeMenuDeInput();
                    break;
                }
                case "DS", "SD" -> {
                    if (j + 1 >= 10 || i + 1 >= 10) {
                        System.out.println("Posicao invalida. Por favor, selecione outro movimento");
                        imprimeMenuDeInput();
                        break;
                    }
                    i += 1;
                    j += 1;
                    this.tabuleiro.selecionaCasa(i, j);
                    limpaTerminal();
                    this.tabuleiro.desenhaTabuleiro();
                    imprimeMenuDeInput();
                    break;
                }
                case "E" -> {
                    limpaTerminal();
                    this.tabuleiro.desenhaTabuleiro();
                    char family = selecaoDePersonagem();
                    limpaTerminal();
                    
                    if(family == 'C') {
                        limpaTerminal();
                        break;
                    }

                    if (quemJoga == 1) {
                        this.player1[indice] = new Personagem(i, j, family);
                        this.tabuleiro.setCasa(i, j, quemJoga, family);
                    } 
                    else {
                        this.player2[indice] = new Personagem(i, j, family);
                        this.tabuleiro.setCasa(i, j, quemJoga, family);
                    }
                    limpaTerminal();
                    return;
                }
                default -> {
                    System.out.println("\nPor favor, insira um comando valido\n");
                    imprimeMenuDeInput();
                    break;
                }
            }

            input = teclado.nextLine().toUpperCase();
        }
    }

    public void imprimeMenuDeInput() {
        System.out.println("(W) Ir para cima       | (WA) Diagonal para cima e esquerda");
        System.out.println("(A) Ir para a esquerda | (WD) Diagonal para cima e direita");
        System.out.println("(S) Ir para baixo      | (SA) Diagonal para baixo e esquerda");
        System.out.println("(D) Ir para a direita  | (SD) Diagonal para baixo e direita");
        System.out.println("(E) Confirma escolha   |");
    }

    public char selecaoDePersonagem() {
        imprimeSelecaoDePersonagem();
        String input;
        input = teclado.nextLine().toUpperCase();
        
        while (input != "S" || input != "L" || input != "T" || input != "C") {
            switch (input) {
                case "S", "s" -> {return 'S';}
                case "L", "l" -> {return 'L';}
                case "T", "t" -> {return 'T';}
                case "C", "c" -> {return 'C';}
                default -> {
                    System.out.println("Opcao invalida, insira um input valido");
                    imprimeSelecaoDePersonagem();
                    break;
                }
            }
            
            input = teclado.nextLine().toUpperCase();
        }
        
        return 'C';
    }

    public void imprimeSelecaoDePersonagem() {
        System.out.println("*********************************Escolha a familia do seu personagem*******************************************");
        System.out.println("**************Stark***************|***********Lannister************|*****************Targaryen*****************");
        System.out.println("* Vida maxima: 60                 | Vida maxima: 50                | Vida maxima: 45                          *");
        System.out.println("* Ataque base: 20                 | Ataque base: 20                | Ataque base: 20                          *");
        System.out.println("* Defesa base: 10                 | Defesa base: 10                | Defesa base: 10                          *");
        System.out.println("* Alcance: 1 (corpo-a-corpo)      | Alcance: 2                     | Alcance: 3                               *");
        System.out.println("* Modificador ofensivo: nenhum    | Modificador ofensivo: +15% atk | Modificador ofensivo: ignora defesa base *");
        System.out.println("***************************************************************************************************************");
        System.out.println("(S) Stark");
        System.out.println("(L) Lannister");
        System.out.println("(T) Targaryen");
        System.out.println("(C) Cancelar");
    }

    public void turno(int j) {
        String input;
    }

    public static void limpaTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
