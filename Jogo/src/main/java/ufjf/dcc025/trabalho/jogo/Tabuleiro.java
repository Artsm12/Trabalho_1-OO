package ufjf.dcc025.trabalho.jogo;

public class Tabuleiro {
    
    private Casa[][] casas;
    private int[] selecionada;
    
    //**Cores para identificar os jogadores**
    public static final String ANSI_CYAN = "\u001B[36m";                // Jogador 1
    public static final String ANSI_RED  = "\u001B[31m";                // Jogador 2
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";   // Indica que a casa foi selecionada
    public static final String ANSI_RESET = "\u001B[0m";                // Reseta as cores
    //***************************************

    public Tabuleiro() {
        this.casas = new Casa[10][10];
        this.selecionada = new int[2];
        this.selecionada[0] = -1;
        this.selecionada[1] = -1;
        
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                casas[i][j] = new Casa();
            }
        }
    }
    
    public void imprimeCasa(int i, int j) {
        if(j != 9) {
            if(casas[i][j].estaVazia()) {
                if(this.casas[i][j].selecionada())
                    System.out.print(ANSI_YELLOW_BACKGROUND + this.casas[i][j].getFamily() + ANSI_RESET + " | ");
                else
                    System.out.print(this.casas[i][j].getFamily() + " | ");
            }
            
            else if(this.casas[i][j].getValor() == 1) {
                if(this.casas[i][j].selecionada())
                    System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_CYAN + this.casas[i][j].getFamily() + ANSI_RESET + " | ");
                else
                    System.out.print(ANSI_CYAN + this.casas[i][j].getFamily() + ANSI_RESET + " | ");
            }
            
            else if(this.casas[i][j].getValor() == 2) {
                if(this.casas[i][j].selecionada())
                    System.out.print(ANSI_YELLOW_BACKGROUND +  ANSI_RED + this.casas[i][j].getFamily() + ANSI_RESET + " | ");
                else
                    System.out.print(ANSI_RED + this.casas[i][j].getFamily() + ANSI_RESET + " | ");
            }
        }
        else {
            if(casas[i][j].estaVazia()) {
                if(this.casas[i][j].selecionada())
                    System.out.print(ANSI_YELLOW_BACKGROUND + casas[i][j].getFamily() + ANSI_RESET);
                else
                    System.out.println(this.casas[i][j].getFamily());
            }
            
            else if(this.casas[i][j].getValor() == 1) {
                if(this.casas[i][j].selecionada())
                    System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_CYAN + this.casas[i][j].getFamily() + ANSI_RESET);
                else
                    System.out.println(ANSI_CYAN + this.casas[i][j].getFamily() + ANSI_RESET);
            }
            
            else if(this.casas[i][j].getValor() == 2) {
                if(this.casas[i][j].selecionada())
                    System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_RED + this.casas[i][j].getFamily() + ANSI_RESET);
                else
                    System.out.println(ANSI_RED + this.casas[i][j].getFamily() + ANSI_RESET);
            }
        } 
    }
    
    public void desenhaTabuleiro() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                imprimeCasa(i, j);
            }
            if(i != 9)
                System.out.println("-------------------------------------");
        }
    }
    
    public void setCasa(int i, int j, int jogador, char family) {
        this.casas[i][j].setFamily(family);
        this.casas[i][j].setValor(jogador);
    }
    
    public void selecionaCasa(int i, int j) {
        if(selecionada[0] != -1 && selecionada[1] != -1)
            this.casas[selecionada[0]][selecionada[1]].removeSelecao();
        
        this.selecionada[0] = i;
        this.selecionada[1] = j;
        this.casas[i][j].seleciona();
    }       
}
