package trabalho.jogo;

public class Targaryen {
    public static final char simbolo = 'T';
    private int[] posicao;
    private int vidaMax = 45;
    private static final int ataqueBase = 20;
    private static final int defesaBase  = 10;
    private static final int distanciaDeAtaque = 3;
    
    public static int ataca() {
        return Targaryen.ataqueBase+Targaryen.defesaBase;
    }
    
    public void sofreDano(int d) {
        this.vidaMax -= (d-Targaryen.defesaBase);
    }
}
