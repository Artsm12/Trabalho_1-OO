package trabalho.jogo;

import java.util.Scanner;
import java.util.Random;

public class Jogo {
    private Tabuleiro tabuleiro;
    private int modoDeJogo;

    public void menuDeInicio() {
        System.out.println("Seja bem-vindo à Batalha Tática das Casas de Westeros!");
        System.out.println("Selecione uma opção:\n");
        System.out.println("(1) Solo(contra computador)");
        System.out.println("(2) Duelo(2 jogadores)");
        System.out.println("(Outro) Sair");
    }
    
    public void inicio() {
        Scanner teclado = new Scanner(System.in);
        menuDeInicio();
        
        this.modoDeJogo = teclado.nextInt();
            
        if(this.modoDeJogo == 1) 
            iniciaJogoSolo();
        else if(this.modoDeJogo == 2)
            iniciaDuelo();
        else {
            System.out.println("Obrigado por jogar :)");
            System.exit(0);
        }
    }
    
    public void iniciaJogoSolo() {
        Random random = new Random();
        this.tabuleiro = new Tabuleiro();
        
        Computador computador = new Computador();
        Jogador jogador = new Jogador();
        
        this.tabuleiro.desenhaTabuleiro();
        
        
    }
    
    public void iniciaDuelo() {
        this.tabuleiro = new Tabuleiro();
        
        Jogador player_1 = new Jogador();
        Jogador player_2 = new Jogador();
        
        this.tabuleiro.desenhaTabuleiro();
    }
    
    public int coinFlip() {
        Random random = new Random();
        int flip = random.nextInt() % 2;
        
        if(flip == 0) {
            System.out.println("O player 1 começa!");
            return 1;
        }
        else {
            if(this.modoDeJogo == 1)
                System.out.println("O bot começa!");
            else
                System.out.println("O player 2 começa!");
            return 2;
        }
    }
    
    public void posicionaPersonagem() {
        
    }
}
