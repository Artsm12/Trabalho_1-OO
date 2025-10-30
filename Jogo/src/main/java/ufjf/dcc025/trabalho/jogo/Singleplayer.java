package ufjf.dcc025.trabalho.jogo;

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
        this.tabuleiro.desenhaTabuleiro();
        
        for(int i = 0; i < 3; i++) {
            criaPersonagem(1, i);
            limpaTerminal();
            this.tabuleiro.desenhaTabuleiro();
        }
        
        for(int i = 0; i < 3; i++) {
            criaPersonagemBot(i);
            limpaTerminal();
        }
        this.tabuleiro.desenhaTabuleiro();
        
        //System.exit(0);

        if (this.primeiroJogador == 1) {
            System.out.println("O jogador 1 comeca!\n");
        } else {
            System.out.println("O computador comeca!\n");
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

        this.player1[indice] = new Personagem(i, j, family, nome);
        this.tabuleiro.setCasa(i, j, jogador, family);
    }
    
    public void criaPersonagemBot(int indice) {
        Random random = new Random();
        int famInd = random.nextInt(1, 3);
        char family = 'S';
        switch (famInd){
            case 1 -> family = 'S';
            case 2 -> family = 'L';
            case 3 -> family = 'T';
            default -> {
            }
        }
        int i, j;
        do {
            i = random.nextInt(2) + 8;
            j = random.nextInt(10);
        } while (!this.tabuleiro.ehVazia(i, j));
        String nome = "COM"+indice;

        player2[indice] = new Personagem(i, j, family, nome);
        this.tabuleiro.setCasa(i, j, 2, family);
    }
    
        private void turnoBot(){
        checaPersonagens(2);
        if(checaTime(1)){
            acabou = true;
            return;
        }
        if(checaTime(2)){
            acabou = true;
            return;
        }
        this.tabuleiro.desenhaTabuleiro();
        
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++){
                if(player2[i].searchEnemy(player1[j]) && player2[i].isAlive())
                {
                    player2[i].attack(player1[j]);
                    i=2;
                    break;
                }
            }
        Random random = new Random();
        int indice = 0;
        do{
            indice = random.nextInt(0, 2);
        }while(!player2[indice].isAlive());
        int[] pos;
        pos = player2[indice].getPosition();
        if(verificaSlot(pos[0]-1, pos[1])){
            player2[indice].walk("W");
            this.tabuleiro.limpaCasa(pos[0]+1, pos[1]);
        }
        else if(verificaSlot(pos[0]+1, pos[1])){
            player2[indice].walk("S");
            this.tabuleiro.limpaCasa(pos[0]-1, pos[1]);
        }
        else if(verificaSlot(pos[0], pos[1]+1)){
            player2[indice].walk("D");
            this.tabuleiro.limpaCasa(pos[0], pos[1]-1);
        }
        else if(verificaSlot(pos[0], pos[1]-1)){
            player2[indice].walk("A");
            this.tabuleiro.limpaCasa(pos[0], pos[1]+1);
        }
        
        for(int i=0; i<3; i++){
            pos = player1[i].getPosition();
            if(player1[i].isAlive())
                this.tabuleiro.setCasa(pos[0], pos[1], 1, player1[i].getFamily());
        }
        for(int i=0; i<3; i++){
            pos = player2[i].getPosition();
            if(player2[i].isAlive())
                this.tabuleiro.setCasa(pos[0], pos[1], 2, player2[i].getFamily());
        }
        
    }
        
    public void turnoPlayer() {
        checaPersonagens(1);
        if(checaTime(1)){
            acabou = true;
            return;
        }
        if(checaTime(2)){
            acabou = true;
            return;
        }
        this.tabuleiro.desenhaTabuleiro();
        
        System.out.println("Jogador, selecione um personagem para mover");
        int indice = escolheBoneco(1);
        
        String input;

        limpaTerminal();
        int pos[];
        this.tabuleiro.selecionaCasa(this.player1[indice].getPosition());
        pos = this.player1[indice].getPosition();
        
        this.tabuleiro.desenhaTabuleiro();
        imprimeMenuDeMovimentacao(1, 0);
        do{
            input = teclado.nextLine().toUpperCase();
            switch(input){
                case "W" -> { 
                    if(verificaSlot(pos[0]-1, pos[1])){
                        
                            player1[indice].walk("W");
                        
                        this.tabuleiro.limpaCasa(pos[0]+1, pos[1]);
                    }
                    else{
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "S" -> { 
                    if(verificaSlot(pos[0]+1, pos[1])){
                        
                            player1[indice].walk("S");
                        
                       
                        this.tabuleiro.limpaCasa(pos[0]-1, pos[1]);
                    }
                    else{
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "A" -> {
                    if(verificaSlot(pos[0], pos[1]-1)){
                        
                            player1[indice].walk("A");
                        
                        this.tabuleiro.limpaCasa(pos[0], pos[1]+1);
                    }
                    else{
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "D" -> {
                    if(verificaSlot(pos[0], pos[1]+1)){
                       
                            player1[indice].walk("D");
                        
                        
                        this.tabuleiro.limpaCasa(pos[0], pos[1]-1);
                    }
                    else{
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "WD" -> {
                    if(verificaSlot(pos[0]-1, pos[1]+1)){
                       
                            player1[indice].walk("WD");
                        
                        this.tabuleiro.limpaCasa(pos[0]+1, pos[1]-1);
                    }
                    else{
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "WA" -> {
                    if(verificaSlot(pos[0]-1, pos[1]-1)){
                        
                            player1[indice].walk("WA");
                        
                        
                        this.tabuleiro.limpaCasa(pos[0]+1, pos[1]+1);
                    }
                    else{
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "SA" -> {
                    if(verificaSlot(pos[0]+1, pos[1]-1)){
                        player1[indice].walk("SA");
                        this.tabuleiro.limpaCasa(pos[0]-1, pos[1]+1);
                    }
                    else{
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "SD" -> {
                    if(verificaSlot(pos[0]+1, pos[1]+1)){
                       player1[indice].walk("SD");
                       this.tabuleiro.limpaCasa(pos[0]-1, pos[1]-1);
                    }
                    else{
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "C" -> {}
                default -> input = "X";
                }
            }while(input.equals("X"));
        
        for(int i=0; i<3; i++){
            pos = player1[i].getPosition();
            if(player1[i].isAlive())
                this.tabuleiro.setCasa(pos[0], pos[1], 1, player1[i].getFamily());
        }
        for(int i=0; i<3; i++){
            pos = player2[i].getPosition();
            if(player2[i].isAlive())
                this.tabuleiro.setCasa(pos[0], pos[1], 2, player2[i].getFamily());
        }
        this.tabuleiro.desenhaTabuleiro();
        int selec = 0;
        System.out.println("Escolha o personagem que vai atacar: ");
        selec = escolheBoneco(1);
        atacar(player1[selec], player2);
        
        System.out.println("Equipe 1: ");
        for(int i=0; i<3; i++)
            System.out.print(player1[i].getNome() + ": " +  player1[i].getHp() + " | ");
        System.out.println("");
        System.out.println("Equipe 2: ");
        for(int i=0; i<3; i++)
            System.out.print(player2[i].getNome() + ": " +  player2[i].getHp() + " | ");
        System.out.println("");
    }
}
