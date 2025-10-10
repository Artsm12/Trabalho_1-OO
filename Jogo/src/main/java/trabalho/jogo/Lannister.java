package trabalho.jogo;

public class Lannister {
    public static final char simbolo = 'L';
    private int[] posicao;
    private int vidaMax = 50;
    private static final int ataqueBase = 20;
    private static final int defesaBase  = 10;
    private static final int distanciaDeAtaque = 2;
    
    public static int ataca() {
        return (int)(Lannister.ataqueBase*0.15);
    }
    
    public void sofreDano(int d) {
        this.vidaMax -= (d-Lannister.defesaBase);
    }
}
