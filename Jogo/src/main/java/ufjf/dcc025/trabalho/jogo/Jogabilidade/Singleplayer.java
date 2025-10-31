package ufjf.dcc025.trabalho.jogo.Jogabilidade;

import ufjf.dcc025.trabalho.jogo.TabuleiroUtils.Tabuleiro;
import ufjf.dcc025.trabalho.jogo.Personagens.Personagem;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Singleplayer extends Duelo {

    private Scanner teclado = new Scanner(System.in);

    public Singleplayer(int primeiroJogador) {
        super(primeiroJogador);
    }

    @Override
    public void inicio() {
        this.tabuleiro = new Tabuleiro();

        for (int i = 0; i < 3; i++) {
            this.tabuleiro.desenhaTabuleiro();
            criaPersonagem(1, i);
            Tabuleiro.limpaTerminal();
        }

        for (int i = 0; i < 3; i++) {
            criaPersonagem(2, i);
        }

        Tabuleiro.limpaTerminal();
        //System.exit(0);
        if (this.primeiroJogador == 1) {
            System.out.println("O" + Tabuleiro.ANSI_CYAN + " jogador" + Tabuleiro.ANSI_RESET + " comeca!\n");
        } else {
            System.out.println("O" + Tabuleiro.ANSI_RED + " computador" + Tabuleiro.ANSI_RESET + " jogou!\n");
        }

        if (this.primeiroJogador == 1) {
            while (!acabou) {
                turnoPlayer();
                turnoBot();
            }
        } else {
            while (!acabou) {
                turnoBot();
                turnoPlayer();
            }
        }
    }

    @Override
    public void criaPersonagem(int jogador, int indice) {
        Random random = new Random();
        if (jogador == 1) {

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
            System.out.print("Nome: ");
            String nome = teclado.nextLine();

            this.player1[indice] = new Personagem(i, j, family, nome);
            this.tabuleiro.setCasa(i, j, jogador, family);
        } else {
            int famInd = random.nextInt(1, 4);
            char family = 'S';
            switch (famInd) {
                case 1 ->
                    family = 'S';
                case 2 ->
                    family = 'L';
                case 3 ->
                    family = 'T';
                default -> {
                }
            }
            int i, j;
            do {
                i = random.nextInt(2) + 8;
                j = random.nextInt(10);
            } while (!this.tabuleiro.ehVazia(i, j));
            String nome = "COM" + indice;

            player2[indice] = new Personagem(i, j, family, nome);
            this.tabuleiro.setCasa(i, j, 2, family);
        }
    }

    public void turnoBot() {
        checaPersonagens(2);
        if (checaTime(1)) {
            acabou = true;
            return;
        }
        if (checaTime(2)) {
            acabou = true;
            return;
        }

        int quemAtacou = 0, quemFoiAtacado = 0;
        int hpAntiga = 0;

        boolean bateu = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (player2[i].searchEnemy(player1[j]) && player2[i].isAlive()) {
                    quemAtacou = i;
                    quemFoiAtacado = j;
                    hpAntiga = player1[j].getHp();
                    player2[i].attack(player1[j]);
                    i = 2;
                    bateu = true;
                    break;
                }
            }
        }

        Random random = new Random();
        int indice = 0;
        do {
            indice = random.nextInt(3);
        } while (!player2[indice].isAlive());

        int[] pos;
        pos = player2[indice].getPosition();

        if (verificaSlot(pos[0] - 1, pos[1])) {
            player2[indice].walk("W");
            this.tabuleiro.limpaCasa(pos[0] + 1, pos[1]);
        } else if (verificaSlot(pos[0] - 1, pos[1] + 1)) {
            player2[indice].walk("WD");
            this.tabuleiro.limpaCasa(pos[0] + 1, pos[1] - 1);
        } else if (verificaSlot(pos[0] - 1, pos[1] - 1)) {
            player2[indice].walk("WA");
            this.tabuleiro.limpaCasa(pos[0] - 1, pos[1] + 1);
        } else if (verificaSlot(pos[0], pos[1] + 1)) {
            player2[indice].walk("D");
            this.tabuleiro.limpaCasa(pos[0], pos[1] - 1);
        } else if (verificaSlot(pos[0], pos[1] - 1)) {
            player2[indice].walk("A");
            this.tabuleiro.limpaCasa(pos[0], pos[1] + 1);
        } else if (verificaSlot(pos[0] + 1, pos[1])) {
            player2[indice].walk("S");
            this.tabuleiro.limpaCasa(pos[0] - 1, pos[1]);
        }

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

        if (!bateu) {
            this.replay.salvaEstadoDoJogo(tabuleiro, 2);
        } else {
            this.replay.salvaEstadoDoJogo(tabuleiro, player2[quemAtacou], player1[quemFoiAtacado], hpAntiga - player1[quemFoiAtacado].getHp());
        }
    }

    public void turnoPlayer() {
        if (acabou) {
            return;
        }
        checaPersonagens(1);
        if (checaTime(1)) {
            acabou = true;
            return;
        }
        if (checaTime(2)) {
            acabou = true;
            return;
        }

        System.out.println("");
        System.out.println(Tabuleiro.ANSI_CYAN + "Jogador" + Tabuleiro.ANSI_RESET + ", selecione um personagem para se movimentar\n");
        int indice = escolhePersonagem(1);

        String input;

        int pos[];
        this.tabuleiro.selecionaCasa(this.player1[indice].getPosition());
        pos = this.player1[indice].getPosition();

        Tabuleiro.limpaTerminal();
        printTeam();
        this.tabuleiro.desenhaTabuleiro();

        imprimeMenuDeMovimentacao(1, 0);
        do {
            input = teclado.nextLine().toUpperCase();
            switch (input) {
                case "W" -> {
                    if (verificaSlot(pos[0] - 1, pos[1])) {

                        player1[indice].walk("W");

                        this.tabuleiro.limpaCasa(pos[0] + 1, pos[1]);
                    } else {
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "S" -> {
                    if (verificaSlot(pos[0] + 1, pos[1])) {

                        player1[indice].walk("S");

                        this.tabuleiro.limpaCasa(pos[0] - 1, pos[1]);
                    } else {
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "A" -> {
                    if (verificaSlot(pos[0], pos[1] - 1)) {

                        player1[indice].walk("A");

                        this.tabuleiro.limpaCasa(pos[0], pos[1] + 1);
                    } else {
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "D" -> {
                    if (verificaSlot(pos[0], pos[1] + 1)) {

                        player1[indice].walk("D");

                        this.tabuleiro.limpaCasa(pos[0], pos[1] - 1);
                    } else {
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "WD", "DW" -> {
                    if (verificaSlot(pos[0] - 1, pos[1] + 1)) {

                        player1[indice].walk("WD");

                        this.tabuleiro.limpaCasa(pos[0] + 1, pos[1] - 1);
                    } else {
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "WA", "AW" -> {
                    if (verificaSlot(pos[0] - 1, pos[1] - 1)) {

                        player1[indice].walk("WA");

                        this.tabuleiro.limpaCasa(pos[0] + 1, pos[1] + 1);
                    } else {
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "SA", "AS" -> {
                    if (verificaSlot(pos[0] + 1, pos[1] - 1)) {
                        player1[indice].walk("SA");
                        this.tabuleiro.limpaCasa(pos[0] - 1, pos[1] + 1);
                    } else {
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "SD", "DS" -> {
                    if (verificaSlot(pos[0] + 1, pos[1] + 1)) {
                        player1[indice].walk("SD");
                        this.tabuleiro.limpaCasa(pos[0] - 1, pos[1] - 1);
                    } else {
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                default ->
                    input = "X";
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
        this.tabuleiro.desenhaTabuleiro();

        boolean temInimigo = (procuraInimigo(player1, player2));

        int selec = 0;

        if (temInimigo) {
            Tabuleiro.limpaTerminal();
            System.out.println("Escolha o personagem que vai atacar: \n");
            do {
                selec = escolhePersonagem(1);

            } while (atacar(player1[selec], player2) == 1);
        } else {
            this.replay.salvaEstadoDoJogo(tabuleiro, 1);
        }
        System.out.println("");
        Tabuleiro.limpaTerminal();
    }
}
