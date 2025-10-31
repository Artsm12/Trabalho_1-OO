package ufjf.dcc025.trabalho.jogo.Jogabilidade;

import ufjf.dcc025.trabalho.jogo.TabuleiroUtils.Tabuleiro;
import ufjf.dcc025.trabalho.jogo.Personagens.Personagem;
import ufjf.dcc025.trabalho.jogo.SistemaDeReplay.Replay;

import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Duelo {

    protected Tabuleiro tabuleiro;
    protected Personagem[] player1;
    protected Personagem[] player2;
    protected Replay replay;
    protected int primeiroJogador;
    protected boolean acabou;

    private Scanner teclado = new Scanner(System.in);

    public Duelo(int primeiroJogador) {
        this.player1 = new Personagem[3];
        this.player2 = new Personagem[3];
        this.replay = new Replay();
        this.primeiroJogador = primeiroJogador;
        this.acabou = false;
    }

    public void inicio() {
        this.tabuleiro = new Tabuleiro();
        this.tabuleiro.desenhaTabuleiro();

        for (int i = 0; i < 3; i++) {
            criaPersonagem(1, i);
            Tabuleiro.limpaTerminal();
            this.tabuleiro.desenhaTabuleiro();
        }

        for (int i = 0; i < 3; i++) {
            criaPersonagem(2, i);
            Tabuleiro.limpaTerminal();
            this.tabuleiro.desenhaTabuleiro();
        }

        //System.exit(0);
        Tabuleiro.limpaTerminal();
        if (this.primeiroJogador == 1) {
            System.out.println("O " + Tabuleiro.ANSI_CYAN + "jogador 1 " + Tabuleiro.ANSI_RESET + "comeca!\n");
        } else {
            System.out.println("O " + Tabuleiro.ANSI_RED + "jogador 2 " + Tabuleiro.ANSI_RESET + "comeca!\n");
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

    public void imprimeMenuDeMovimentacao(int jogador, int indice) {
        if (jogador == 1) {
            System.out.print("Nome: " + this.player1[indice].getNome() + "  |  ");

            System.out.print("Familia: ");
            if (player1[indice].getFamily() == 'S') {
                System.out.println("Stark");
            } else if (player1[indice].getFamily() == 'L') {
                System.out.println("Lannister");
            } else {
                System.out.println("Targaryen");
            }
        } else {
            System.out.print(this.player2[indice].getNome() + "  |  ");

            if (player2[indice].getFamily() == 'S') {
                System.out.println("Stark");
            } else if (player2[indice].getFamily() == 'L') {
                System.out.println("Lannister");
            } else {
                System.out.println("Targaryen");
            }
        }

        System.out.println("*--------------------------------------------------------------*");
        System.out.println("| (W) Ir para cima       | (WA) Diagonal para cima e esquerda  |");
        System.out.println("| (A) Ir para a esquerda | (WD) Diagonal para cima e direita   |");
        System.out.println("| (S) Ir para baixo      | (SA) Diagonal para baixo e esquerda |");
        System.out.println("| (D) Ir para a direita  | (SD) Diagonal para baixo e direita  |");
        System.out.println("| (P) Pular turno                                              |");
        System.out.println("*--------------------------------------------------------------*");

    }

    public char selecaoDePersonagem(int jogador) {
        imprimeSelecaoDePersonagem(jogador);
        String input;
        input = teclado.nextLine().toUpperCase();

        while (!input.equals("S") || !input.equals("L") || !input.equals("T")) {
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
                default -> {
                    System.out.println("Opcao invalida, insira um input valido");
                    imprimeSelecaoDePersonagem(jogador);
                    break;
                }
            }

            input = teclado.nextLine().toUpperCase();
        }

        return 'X';
    }

    public void imprimeSelecaoDePersonagem(int jogador) {
        if (jogador == 1) {
            System.out.print(Tabuleiro.ANSI_CYAN);
        } else {
            System.out.print(Tabuleiro.ANSI_RED);
        }

        System.out.println("Jogador " + jogador + Tabuleiro.ANSI_RESET + ", Escolha a familia do seu personagem\n");
        System.out.println("*-------------Stark---------------------------Lannister------------------------------Targaryen----------------*");
        System.out.println("| Vida maxima: 60                 | Vida maxima: 50                | Vida maxima: 45                          |");
        System.out.println("| Ataque base: 20                 | Ataque base: 20                | Ataque base: 20                          |");
        System.out.println("| Defesa base: 10                 | Defesa base: 10                | Defesa base: 10                          |");
        System.out.println("| Alcance: 1 (corpo-a-corpo)      | Alcance: 2                     | Alcance: 3                               |");
        System.out.println("| Modificador ofensivo: nenhum    | Modificador ofensivo: +15% atk | Modificador ofensivo: ignora defesa base |");
        System.out.println("*-------------------------------------------------------------------------------------------------------------*");
        System.out.println("(S) Stark  |  (L) Lannister  |  (T) Targaryen");
    }

    protected void checaPersonagens(int jogador) {
        int[] pos;
        if (jogador == 1) {
            for (int i = 0; i < 3; i++) {
                if (!player1[i].isAlive()) {
                    pos = player1[i].getPosition();
                    this.tabuleiro.limpaCasa(pos[0], pos[1]);
                }
            }
        } else {
            for (int i = 0; i < 3; i++) {
                if (!player2[i].isAlive()) {
                    pos = player2[i].getPosition();
                    this.tabuleiro.limpaCasa(pos[0], pos[1]);
                }
            }
        }
    }

    protected boolean checaTime(int jogador) {
        if (jogador == 1) {
            return (!player1[0].isAlive() && !player1[1].isAlive() && !player1[2].isAlive());
        }
        return (!player2[0].isAlive() && !player2[1].isAlive() && !player2[2].isAlive());

    }

    public int escolhePersonagem(int jogador) {
        int indice;
        if(jogador == 1) 
            for(indice = 0; !player1[indice].isAlive(); indice++);
        else
            for(indice = 0; !player2[indice].isAlive(); indice++);

        if (jogador == 1) {
            this.tabuleiro.selecionaCasa(player1[indice].getPosition());
        } else {
            this.tabuleiro.selecionaCasa(player2[indice].getPosition());
        }

        this.tabuleiro.desenhaTabuleiro();
        if (jogador == 1) {
            player1[indice].imprimeInformacoesDoPersonagem();
        } else {
            player2[indice].imprimeInformacoesDoPersonagem();
        }

        System.out.println("(A) Personagem anterior | (D) Proximo personagem");
        System.out.println("(E) Confirmar escolha");

        String input;
        input = teclado.nextLine().toUpperCase();
        Tabuleiro.limpaTerminal();

        while (true) {
            switch (input) {
                case "A" -> {
                    if (jogador == 1) {
                        do {
                            if (indice - 1 < 0) {
                                indice = 2;
                                while(!player1[indice].isAlive())
                                    indice--;
                            } else {
                                indice -= 1;
                            }
                        } while (!player1[indice].isAlive());

                        this.tabuleiro.selecionaCasa(player1[indice].getPosition());
                        Tabuleiro.limpaTerminal();
                        this.tabuleiro.desenhaTabuleiro();

                        player1[indice].imprimeInformacoesDoPersonagem();
                    } 
                    else {
                        do {
                            if (indice - 1 < 0) {
                                indice = 2;
                                while(!player2[indice].isAlive())
                                    indice--;
                            } else {
                                indice -= 1;
                            }
                        } while (!player2[indice].isAlive());

                        this.tabuleiro.selecionaCasa(player2[indice].getPosition());
                        Tabuleiro.limpaTerminal();
                        this.tabuleiro.desenhaTabuleiro();

                        player2[indice].imprimeInformacoesDoPersonagem();
                    }
                    System.out.println("(A) Personagem anterior | (D) Proximo personagem");
                    System.out.println("(E) Confirmar escolha");
                    input = teclado.nextLine().toUpperCase();
                    Tabuleiro.limpaTerminal();
                    break;
                }
                case "D" -> {
                    if (jogador == 1) {
                        do {
                            if (indice + 1 > 2) {
                                indice = 0;
                            } else {
                                indice += 1;
                            }
                        } while (!player1[indice].isAlive());

                        this.tabuleiro.selecionaCasa(player1[indice].getPosition());
                        Tabuleiro.limpaTerminal();
                        this.tabuleiro.desenhaTabuleiro();
                        player1[indice].imprimeInformacoesDoPersonagem();
                    } 
                    else {
                        do {
                            if (indice + 1 > 2) {
                                indice = 0;
                                while(!player2[indice].isAlive())
                                    indice++;
                            } else {
                                indice += 1;
                            }
                        } while (!player2[indice].isAlive());

                        this.tabuleiro.selecionaCasa(player2[indice].getPosition());
                        Tabuleiro.limpaTerminal();
                        this.tabuleiro.desenhaTabuleiro();
                        player2[indice].imprimeInformacoesDoPersonagem();
                    }
                    System.out.println("(A) Personagem anterior | (D) Proximo personagem");
                    System.out.println("(E) Confirmar escolha");
                    input = teclado.nextLine().toUpperCase();
                    Tabuleiro.limpaTerminal();
                    break;
                }
                case "E" -> {
                    Tabuleiro.limpaTerminal();
                    return indice;
                }
                default -> {
                    Tabuleiro.limpaTerminal();
                    this.tabuleiro.desenhaTabuleiro();
                    System.out.println("Input invalido, tente novamente");
                    if (jogador == 1) {
                        player1[indice].imprimeInformacoesDoPersonagem();
                    } else {
                        player2[indice].imprimeInformacoesDoPersonagem();
                    }
                    System.out.println("(A) Personagem anterior | (D) Proximo personagem");
                    System.out.println("(E) Confirmar escolha");

                    input = teclado.nextLine().toUpperCase();
                    break;
                }
            }
        }
    }

    public void turno(int jogador) {
        checaPersonagens(jogador);
        if (checaTime(1)) {
            acabou = true;
            return;
        }
        if (checaTime(2)) {
            acabou = true;
            return;
        }

        if (jogador == 1) {
            System.out.print(Tabuleiro.ANSI_CYAN);
        } else {
            System.out.print(Tabuleiro.ANSI_RED);
        }

        System.out.println("Jogador " + jogador + Tabuleiro.ANSI_RESET + ", escolha um personagem para movimentar:\n");
        int indice = escolhePersonagem(jogador);

        String input;

        int pos[];
        if (jogador == 1) {
            this.tabuleiro.selecionaCasa(this.player1[indice].getPosition());
            pos = this.player1[indice].getPosition();
        } else {
            this.tabuleiro.selecionaCasa(this.player2[indice].getPosition());
            pos = this.player2[indice].getPosition();
        }

        this.tabuleiro.desenhaTabuleiro();
        imprimeMenuDeMovimentacao(jogador, 0);

        do {
            input = teclado.nextLine().toUpperCase();
            switch (input) {
                case "W" -> {
                    if (verificaSlot(pos[0] - 1, pos[1])) {
                        if (jogador == 1) {
                            player1[indice].walk("W");
                        } else {
                            player2[indice].walk("W");
                        }

                        this.tabuleiro.limpaCasa(pos[0] + 1, pos[1]);
                    } else {
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }

                case "S" -> {
                    if (verificaSlot(pos[0] + 1, pos[1])) {
                        if (jogador == 1) {
                            player1[indice].walk("S");
                        } else {
                            player2[indice].walk("S");
                        }

                        this.tabuleiro.limpaCasa(pos[0] - 1, pos[1]);
                    } else {
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "A" -> {
                    if (verificaSlot(pos[0], pos[1] - 1)) {
                        if (jogador == 1) {
                            player1[indice].walk("A");
                        } else {
                            player2[indice].walk("A");
                        }

                        this.tabuleiro.limpaCasa(pos[0], pos[1] + 1);
                    } else {
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "D" -> {
                    if (verificaSlot(pos[0], pos[1] + 1)) {
                        if (jogador == 1) {
                            player1[indice].walk("D");
                        } else {
                            player2[indice].walk("D");
                        }

                        this.tabuleiro.limpaCasa(pos[0], pos[1] - 1);
                    } else {
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "WD", "DW" -> {
                    if (verificaSlot(pos[0] - 1, pos[1] + 1)) {
                        if (jogador == 1) {
                            player1[indice].walk("WD");
                        } else {
                            player2[indice].walk("WD");
                        }

                        this.tabuleiro.limpaCasa(pos[0] + 1, pos[1] - 1);
                    } else {
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "WA", "AW" -> {
                    if (verificaSlot(pos[0] - 1, pos[1] - 1)) {
                        if (jogador == 1) {
                            player1[indice].walk("WA");
                        } else {
                            player2[indice].walk("WA");
                        }

                        this.tabuleiro.limpaCasa(pos[0] + 1, pos[1] + 1);
                    } else {
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "SA", "AS" -> {
                    if (verificaSlot(pos[0] + 1, pos[1] - 1)) {
                        if (jogador == 1) {
                            player1[indice].walk("SA");
                        } else {
                            player2[indice].walk("SA");
                        }

                        this.tabuleiro.limpaCasa(pos[0] - 1, pos[1] + 1);
                    } else {
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "SD", "DS" -> {
                    if (verificaSlot(pos[0] + 1, pos[1] + 1)) {
                        if (jogador == 1) {
                            player1[indice].walk("SD");
                        } else {
                            player2[indice].walk("SD");
                        }

                        this.tabuleiro.limpaCasa(pos[0] - 1, pos[1] - 1);
                    } else {
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "P" -> {
                    String mensagem = "O " + jogador + " pulou sua vez";
                    this.replay.salvarEstadoDoJogo(this.tabuleiro, mensagem);
                    return;
                }
                default -> {
                    Tabuleiro.limpaTerminal();
                    this.tabuleiro.desenhaTabuleiro();
                    System.out.println("\nInput Invalido, tente novamente\n");
                    imprimeMenuDeMovimentacao(jogador, indice);
                    input = "X";
                }
            }
        } while (input.equals("X"));

        for (int i = 0; i < 3; i++) {
            pos = player1[i].getPosition();
            if (player1[i].isAlive()) {
                this.tabuleiro.setCasa(pos[0], pos[1], 1, player1[i].getFamily());
            }
        }
        for (int i = 0; i < 3; i++) {
            pos = player2[i].getPosition();
            if (player2[i].isAlive()) {
                this.tabuleiro.setCasa(pos[0], pos[1], 2, player2[i].getFamily());
            }
        }

        int selec = 0;

        boolean temInimigo = false;
        if (jogador == 1) {
            temInimigo = procuraInimigo(player1, player2);
        } else {
            temInimigo = procuraInimigo(player2, player1);
        }
        if (temInimigo) {
            Tabuleiro.limpaTerminal();
            System.out.println("Escolha o personagem que vai atacar: \n");
            
            if (jogador == 1) {
                do{
                selec = escolhePersonagem(jogador);
                
                }while(atacar(player1[selec], player2)==1);
            } else {
                do{
                selec = escolhePersonagem(jogador);
                }while(atacar(player2[selec], player1)==1);
            }
        }
        else
            this.replay.salvaEstadoDoJogo(tabuleiro, jogador);

        Tabuleiro.limpaTerminal();
    }

    public void printTeam() {
        System.out.println("Equipe 1: ");
        for (int i = 0; i < 3; i++) {
            System.out.print(this.player1[i].getNome() + ": " + this.player1[i].getHp() + " | ");
        }
        System.out.println("");
        System.out.println("Equipe 2: ");
        for (int i = 0; i < 3; i++) {
            System.out.print(this.player2[i].getNome() + ": " + this.player2[i].getHp() + " | ");
        }
        System.out.println("");
    }

    private boolean procuraInimigoAux(Personagem player, Personagem enemy[]) {
        return ((player.searchEnemy(enemy[0]) && enemy[0].isAlive()) || (player.searchEnemy(enemy[1]) && enemy[1].isAlive()) || (player.searchEnemy(enemy[2]) && enemy[2].isAlive()));
    }

    protected boolean procuraInimigo(Personagem player[], Personagem enemy[]) {
        boolean temInimigo = false;
        for (int i = 0; i < 3; i++) {
            if (procuraInimigoAux(player[i], enemy) && player[i].isAlive()) {
                temInimigo = true;
                break;
            }
        }
        return temInimigo;
    }

    protected int atacar(Personagem player, Personagem[] enemy) {
        if(!procuraInimigoAux(player, enemy)){
            System.out.println("Não haviam inimigos próximos, escolha outro personagem para atacar.");
            return 1;}
        System.out.println("Selecione o alvo: ");
        int escolha = 0, j = 1;
        System.out.println("[0] Passar a vez");
        for (int i = 0; i < 3; i++) {
            if (player.searchEnemy(enemy[i]) && enemy[i].isAlive()) {
                System.out.println("[" + (i + 1) + "]" + ' ' + enemy[i].getNome());
                j++;
            }
        }
        do {
            while (true) {
                try {
                    // 3. Tenta ler um número inteiro
                    escolha = teclado.nextInt();
                    while(escolha > 3) {
                        System.out.println("\nInput invalido, tente novamente\n");
                        escolha = teclado.nextInt();
                    }
                    teclado.nextLine();

                    // 4. Se conseguiu ler, quebra o loop 
                    break;
                } catch (InputMismatchException e) {
                    // 5. Se deu erro (ex: digitou 'A' ou "olá")
                    System.out.println("\nInput invalido, tente novamente\n");

                    // 6. Limpa o buffer do scanner (IMPORTANTE!)
                    teclado.nextLine(); // Isso consome a entrada inválida que ficou "presa"
                }
            }
            if (escolha < 0 && escolha > j) {
                System.out.print("Escolha uma opcao valida: ");
            }
        } while (escolha < 0 && escolha > j);

        if (escolha != 0) {
            int hpAntiga = enemy[escolha - 1].getHp();
            player.attack(enemy[escolha - 1]);
            this.replay.salvaEstadoDoJogo(this.tabuleiro, player, enemy[escolha-1], hpAntiga - enemy[escolha - 1].getHp());
        } 
        return 0;
    }

    protected boolean verificaSlot(int i, int j) {
        return (i >= 0 && i < 10 && j >= 0 && j < 10 && this.tabuleiro.ehVazia(i, j));
    }
    
    public void assisteReplay() {
        this.replay.verReplay();
        return;
    }
}
