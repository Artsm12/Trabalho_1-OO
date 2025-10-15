package trabalho.jogo;

public class Targaryen {
    public static final char simbolo = 'T';
    private int vidaMax = 45;
    private static final int ataqueBase = 20;
    private static final int defesaBase  = 10;
    private static final int distanciaDeAtaque = 3;
    
     public int TargaryenAtk(){
        return ataqueBase;
    }
    
    public int TargaryenDef(){
        return defesaBase;
    }
    
    public int TargaryenHp(){
        return vidaMax;
    }
    
    public int TargaryenRange(){
        return distanciaDeAtaque;
    }
    
    
}