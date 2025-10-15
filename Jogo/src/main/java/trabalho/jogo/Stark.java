package trabalho.jogo;

public class Stark {
    public static final char simbolo = 'S';
    private int posicaoX;
    private int posicaoY;
    private int vidaMax = 60;
    private static final int ataqueBase = 20;
    private static final int defesaBase  = 10;
    private static final int distanciaDeAtaque = 1;

    public Stark(int y, int x) {
        this.posicaoX = x;
        this.posicaoY = y;
    }
    
    public static int ataca() {
        return Stark.ataqueBase;
    }
    
    public void sofreDano(int d) {
        this.vidaMax -= ((int)(d*0.8)-Stark.defesaBase);
    }
}
