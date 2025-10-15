package trabalho.jogo;

public class Tabuleiro {
    private int[][] casas;
    
    //**Cores para identificar os jogadores**
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m"; // Jogador 1
    public static final String ANSI_RED_BACKGROUND  = "\u001B[41m"; // Jogador 2
    public static final String ANSI_RESET = "\u001B[0m";            // Reseta as cores
    //***************************************

    public Tabuleiro() {
        this.casas = new int[10][10];
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                casas[i][j] = 0;
            }
        }
    }
    
    public void imprimeCasa(int i, int j) {
        if(j != 9) {
            if(casas[i][j] == 0)
                System.out.print("  | ");
            else if(casas[i][j] % 3 == 1)
                System.out.print(getCor(i, j) + Stark.simbolo + ANSI_RESET + " | ");
            else if(casas[i][j] % 3 == 2)
                System.out.print(getCor(i, j) + Lannister.simbolo + ANSI_RESET + " | ");
            else
                System.out.print(Targaryen.simbolo + " | ");
        }
        else {
            if(casas[i][j] == 0)
                System.out.println(" ");
            else if(casas[i][j] % 3 == 1)
                System.out.println(getCor(i, j) + Stark.simbolo + Tabuleiro.ANSI_RESET);
            else if(casas[i][j] % 3 == 2)
                System.out.println(getCor(i, j) + Lannister.simbolo + Tabuleiro.ANSI_RESET);
            else
                System.out.println(Targaryen.simbolo + " | ");
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

    public int getCasa(int i, int j) {
        return this.casas[i][j];
    }

    public void setCasa(int i, int j, int valor) {
        this.casas[i][j] = valor;
    }
    
    public String getCor(int i, int j) {
        if(casas[i][j] <= 3)
            return Tabuleiro.ANSI_BLUE_BACKGROUND;
        else
            return Tabuleiro.ANSI_RED_BACKGROUND;
    }
}
