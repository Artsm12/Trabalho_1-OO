package ufjf.dcc025.trabalho.jogo;

import java.util.Scanner;
import java.util.Random;

public class Jogo {
    private char modoDeJogo;

    public void menuDeInicio() {
        System.out.println("Seja bem-vindo a Batalha Tatica das Casas de Westeros!");
        System.out.println("Selecione uma opcao:\n");
        System.out.println("(1) Solo (contra computador)");
        System.out.println("(2) Duelo (2 jogadores)");
        System.out.println("(Outro) Sair");
    }
    
    public void inicio() {
        Scanner teclado = new Scanner(System.in);
        Duelo.limpaTerminal();
        menuDeInicio();
        
        this.modoDeJogo = teclado.next().charAt(0);
        Duelo.limpaTerminal();
        
        if(this.modoDeJogo == '1') {
            Singleplayer solo = new Singleplayer(coinFlip());
            solo.inicio();
        }
        
        else if(this.modoDeJogo == '2') {
            Duelo duelo = new Duelo(coinFlip());
            duelo.inicio();
        }
        else {
            System.out.println("Obrigado por jogar :)");
            System.exit(0);
        }
    }

    public int coinFlip() {
        Random random = new Random();
        int flip = random.nextInt(2);
    
        return flip+1;
    }
}
