package trabalho.jogo;

public class Tabuleiro {
    private static char[][] casas;

    public Tabuleiro() {
        this.casas = new char[10][10];
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                casas[i][j] = ' ';
            }
        }
    }
    
    public static void desenhaTabuleiro() {
        for(int i = 0; i < 10; i++) {
            System.out.println(casas[i][0] + " | " + casas[i][1] + " | " + casas[i][2] + " | "
                               + casas[i][3] + " | " + casas[i][4] + " | "  + casas[i][5] + " | "
                               + casas[i][6] + " | " + casas[i][8] + " | "  + casas[i][9]);
            if(i != 9)
                System.out.println("---------------------------------");
        }
    }

    public static char getCasa(int i, int j) {
        return Tabuleiro.casas[i][j];
    }

    public static void setCasa(int i, int j, char valor) {
        Tabuleiro.casas[i][j] = valor;
    }
}
