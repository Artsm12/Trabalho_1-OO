
package trabalho.jogo;

public class Personagem {
    private int hp, def, atk, range;
    private char family;
    public int[] position = new int[2];
    
    public void setFamily(char initial){
        family = initial;
        switch(family){
            case 'T'->{ 
                setTargaryenAtributes();
                break;
            }
            case 'L' ->{
                setLannisterAtributes();
                break;
            }
            case 'S' -> {
                setStarkAtributes();
                break;
            }
            default -> {
                break;
            }
        }
    }
    private void setTargaryenAtributes(){    //define os status do personagem
        Targaryen stat = new Targaryen();
        this.hp = stat.TargaryenHp();
        this.def = stat.TargaryenDef();
        this.atk = stat.TargaryenAtk();
        this.range = stat.TargaryenRange();
    }
    
    private void setLannisterAtributes(){    //define status do personagem
        Lannister stat = new Lannister();
        this.hp = stat.LannisterHp();
        this.def = stat.LannisterDef();
        this.atk = stat.LannisterAtk();
        this.range = stat.LannisterRange();
    }
    
    private void setStarkAtributes(){    //define status do personagem
        Stark stat = new Stark();
        this.hp = stat.StarkHp();
        this.def = stat.StarkDef();
        this.atk = stat.StarkAtk();
        this.range = stat.StarkRange();
    }
    public boolean isAlive(){
        return (this.hp > 0);
    }
    
    public boolean searchEnemy(Personagem enemy){
        return(Math.abs(enemy.position[0] - this.position[0]) <= this.range && Math.abs(enemy.position[1] - this.position[1]) <= this.range); //verifica se o inimigo está ao alcance
    }
    
    public void attack(Personagem enemy){ //ataca o inimigo selecionado caso ele esteja no alcance do personagem
        if(searchEnemy(enemy)){
            switch (this.family) {
                case 'S' -> {
                    if(enemy.family == 'S')
                        enemy.hp = enemy.hp - (this.atk - enemy.def)*(8/10);
                    else
                        enemy.hp = enemy.hp - (this.atk - enemy.def);
                }
                case 'L' -> {
                    if(enemy.family == 'S')
                        enemy.hp = enemy.hp - (this.atk - enemy.def + this.atk * 15/100)*(8/10);
                    else
                        enemy.hp = enemy.hp - (this.atk - enemy.def + this.atk * 15/100);
                }
                case 'T' -> {
                    if(enemy.family == 'S')
                        enemy.hp = enemy.hp - (this.atk)*(8/10);
                    else
                        enemy.hp = enemy.hp - (this.atk);
                }
                default -> {
                    break;
                }
            }
            
        }
        else
            System.out.println("Miss!");    //charminho
    }
    
    public void walk(String choice, int playerNumber){    //personagem anda na direção escolhida
        if(playerNumber == 1){
            switch(choice){
                case "W" -> this.position[1]++;
                case "S" -> this.position[1]--;
                case "A" -> this.position[0]--;
                case "D" -> this.position[0]++;
            }
        }
        switch(choice){
            case "W" -> this.position[1]--;
            case "S" -> this.position[1]++;
            case "A" -> this.position[0]++;
            case "D" -> this.position[0]--;
        }
    }
}
