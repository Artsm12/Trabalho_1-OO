package trabalho.jogo;

public class Lannister {
    public static final char simbolo = 'L';
    private int vidaMax = 50;
    private static final int ataqueBase = 20;
    private static final int defesaBase  = 10;
    private static final int distanciaDeAtaque = 2;
    
    public int LannisterAtk(){
        return ataqueBase;
    }
    
    public int LannisterDef(){
        return defesaBase;
    }
    
    public int LannisterHp(){
        return vidaMax;
    }
    
    public int LannisterRange(){
        return distanciaDeAtaque;
    }
    
    
}