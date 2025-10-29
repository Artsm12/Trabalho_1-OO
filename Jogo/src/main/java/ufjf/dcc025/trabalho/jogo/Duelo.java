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
        this.tabuleiro.desenhaTabuleiro();

        for(int i = 0; i < 3; i++) {
            criaPersonagem(1, i);
            limpaTerminal();
            this.tabuleiro.desenhaTabuleiro();
        }
        
        for(int i = 0; i < 3; i++) {
            criaPersonagem(2, i);
            limpaTerminal();
            this.tabuleiro.desenhaTabuleiro();
        }

        System.exit(0);

        if (this.primeiroJogador == 1) {
            System.out.println("O jogador 1 comeca!\n");
        } else {
            System.out.println("O jogador 2 comeca!\n");
        }

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

    public void criaPersonagem(int jogador, int indice) {
        Random random = new Random();
        char family = selecaoDePersonagem(jogador);

        int i, j;
        do {
            if (jogador == 1) {
                i = random.nextInt(2);
            } else {
                i = random.nextInt(2) + 8;
            }
            j = random.nextInt(10);
        } while (!this.tabuleiro.ehVazia(i, j));

        System.out.println("\nEscolha um nome para seu personagem");
        String nome = teclado.nextLine();

        if (jogador == 1) {
            player1[indice] = new Personagem(i, j, family, nome);
            this.tabuleiro.setCasa(i, j, jogador, family);
        } else {
            player2[indice] = new Personagem(i, j, family, nome);
            this.tabuleiro.setCasa(i, j, jogador, family);
        }
    }

    /*
    public void criaPersonagem(int quemJoga, int indice) {
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
     */
    public void imprimeMenuDeMovimentacao(int jogador, int indice) {
        if(jogador == 1) {
            System.out.println(this.player1[indice].getNome() + " ******* " + this.player1[indice].getFamily());
        }
        else {
            System.out.println(this.player2[indice].getNome() + " ******* " + this.player2[indice].getFamily());
        }
        System.out.println("(W) Ir para cima       | (WA) Diagonal para cima e esquerda");
        System.out.println("(A) Ir para a esquerda | (WD) Diagonal para cima e direita");
        System.out.println("(S) Ir para baixo      | (SA) Diagonal para baixo e esquerda");
        System.out.println("(D) Ir para a direita  | (SD) Diagonal para baixo e direita");
        System.out.println("(C) Proximo personagem | (E) Confirmar escolha");
    }

    public char selecaoDePersonagem(int jogador) {
        imprimeSelecaoDePersonagem(jogador);
        String input;
        input = teclado.nextLine().toUpperCase();

        while (input != "S" || input != "L" || input != "T" || input != "C") {
            switch (input) {
                case "S" -> {
                    return 'S';
                }
                case "L" -> {
                    return 'L';
                }
                case "T" -> {
                    return 'T';
                }
                case "C" -> {
                    return 'C';
                }
                default -> {
                    System.out.println("Opcao invalida, insira um input valido");
                    imprimeSelecaoDePersonagem(jogador);
                    break;
                }
            }

            input = teclado.nextLine().toUpperCase();
        }

        return 'C';
    }

    public void imprimeSelecaoDePersonagem(int jogador) {
        System.out.println("**************************Jogador " + jogador + ", Escolha a familia do seu personagem*************************");
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
    }

    public void turno(int jogador) {
        System.out.println("Jogador " + jogador + ", selecione um personagem para mover");
        String input;

        limpaTerminal();
        if (jogador == 1) {
            this.tabuleiro.selecionaCasa(this.player1[0].getPosition());
        } else {
            this.tabuleiro.selecionaCasa(this.player2[0].getPosition());
        }
        this.tabuleiro.desenhaTabuleiro();
        imprimeMenuDeMovimentacao(jogador, 0);
        
        input = teclado.nextLine().toUpperCase();

    }

    public static void limpaTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
