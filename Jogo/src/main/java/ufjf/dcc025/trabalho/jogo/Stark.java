package ufjf.dcc025.trabalho.jogo;

public class Stark {
    public static final char simbolo = 'S';
    private int vidaMax = 60;
    private static final int ataqueBase = 20;
    private static final int defesaBase  = 10;
    private static final int distanciaDeAtaque = 1;

    public int StarkAtk(){
        return ataqueBase;
    }
    
    public int StarkDef(){
        return defesaBase;
    }
    
    public int StarkHp(){
        return vidaMax;
    }
    
    public int StarkRange(){
        return distanciaDeAtaque;
    }
    
    
}
