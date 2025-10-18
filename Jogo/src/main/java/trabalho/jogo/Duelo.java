package trabalho.jogo;

import java.util.Random;
import java.util.Scanner;  
import java.util.Set;

public class Duelo {
    private Tabuleiro tabuleiro;
    private Personagem[] player1;
    private Personagem[] player2;
    private final int primeiroJogador;
    private boolean acabou;

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
        Random random = new Random();
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        
        player1[0] = new Personagem('S', 0, 9);
        this.tabuleiro.setCasa(0, 9, 1);
        
        
        player2[0] = new Personagem('L', 5, 7);
        this.tabuleiro.setCasa(5, 7, 5); 
    }
    
    public void turno(int j) {
        String input;
        
    }
}
