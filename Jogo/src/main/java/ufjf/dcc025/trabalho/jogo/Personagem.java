<<<<<<< HEAD
package ufjf.dcc025.trabalho.jogo;

public class Personagem {

    private String nome;
    private int hp, def, atk, range;
    private char family;
    private int[] position = new int[2];

    public Personagem(int y, int x, char family, String nome) {
        this.nome = nome;
        this.family = family;
        setPosition(y, x);
        setFamily(this.family);
    }

    public void setPosition(int y, int x) {
        this.position[0] = y;
        this.position[1] = x;
    }

    public int[] getPosition() {
        return this.position;
    }

    public void setFamily(char initial) {
        family = initial;
        switch (family) {
            case 'T' -> {
                setTargaryenAtributes();
                break;
            }
            case 'L' -> {
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

    public String getNome() {
        return this.nome;
    }

    public char getFamily() {
        return this.family;
    }
    public int getHp(){
        if(this.hp < 0)
            return 0;
        return this.hp;
    }
    
    private void setTargaryenAtributes() {    //define os status do personagem
        Targaryen stat = new Targaryen();
        this.hp = stat.TargaryenHp();
        this.def = stat.TargaryenDef();
        this.atk = stat.TargaryenAtk();
        this.range = stat.TargaryenRange();
    }

    private void setLannisterAtributes() {    //define status do personagem
        Lannister stat = new Lannister();
        this.hp = stat.LannisterHp();
        this.def = stat.LannisterDef();
        this.atk = stat.LannisterAtk();
        this.range = stat.LannisterRange();
    }

    private void setStarkAtributes() {    //define status do personagem
        Stark stat = new Stark();
        this.hp = stat.StarkHp();
        this.def = stat.StarkDef();
        this.atk = stat.StarkAtk();
        this.range = stat.StarkRange();
    }

    public boolean isAlive() {
        return (this.hp > 0);
    }

    public boolean searchEnemy(Personagem enemy) {
        return (Math.abs(enemy.position[0] - this.position[0]) <= this.range && Math.abs(enemy.position[1] - this.position[1]) <= this.range); //verifica se o inimigo está ao alcance
    }

    public int attack(Personagem enemy) { //ataca o inimigo selecionado caso ele esteja no alcance do personagem
        if (searchEnemy(enemy)) {
            switch (this.family) {
                case 'S' -> {
                    if (enemy.family == 'S') {
                        enemy.hp = (int) (enemy.hp - (this.atk - enemy.def)*(8f/10));
                    }
                    else
                        enemy.hp = enemy.hp - (this.atk - enemy.def);
                }
                case 'L' -> {
                    if (enemy.family == 'S') {
                        enemy.hp = (int) ((enemy.hp - (this.atk - enemy.def + this.atk * 15f/100)*(8f/10)));
                    }
                    else
                        enemy.hp = (int) (enemy.hp - (this.atk - enemy.def + this.atk * 15f/100));
                }
                case 'T' -> {
                    if (enemy.family == 'S') {
                        enemy.hp = (int) (enemy.hp - (this.atk) * (8f / 10));
                    } else {
                        enemy.hp = enemy.hp - (this.atk);
                    }
                }
                default -> {
                    break;
                }
            }
            return 1;
        } else {
            System.out.println("Movimento inválido!");//charminho
        }
        return 0;
    }

    public void walk(String input) {    //personagem anda na direção escolhida
        switch(input){
                case "W": 
                    this.position[0]--;
                    break;
                case "S": 
                    this.position[0]++;
                    break;
                case "A": 
                    this.position[1]--;
                    break;
                case "D": 
                    this.position[1]++;
                    break;
                case "WD": 
                    this.position[0]--; 
                    this.position[1]++; 
                    break;
                case "WA": 
                    this.position[0]--; 
                    this.position[1]--; 
                    break;
                case "SA": 
                    this.position[0]++; 
                    this.position[1]--; 
                    break;
                case "SD": 
                    this.position[0]++; 
                    this.position[1]++; 
                    break;
            }
    }
}
=======
package ufjf.dcc025.trabalho.jogo;

public class Personagem {

    private String nome;
    private int hp, def, atk, range;
    private char family;
    private int[] position = new int[2];

    public Personagem(int y, int x, char family, String nome) {
        this.nome = nome;
        this.family = family;
        setPosition(y, x);
        setFamily(this.family);
    }

    public void setPosition(int y, int x) {
        this.position[0] = y;
        this.position[1] = x;
    }

    public int[] getPosition() {
        return this.position;
    }

    public void setFamily(char initial) {
        family = initial;
        switch (family) {
            case 'T' -> {
                setTargaryenAtributes();
                break;
            }
            case 'L' -> {
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

    public String getNome() {
        return this.nome;
    }

    public char getFamily() {
        return this.family;
    }
    public int getHp(){
        if(this.hp < 0)
            return 0;
        return this.hp;
    }
    
    private void setTargaryenAtributes() {    //define os status do personagem
        Targaryen stat = new Targaryen();
        this.hp = stat.TargaryenHp();
        this.def = stat.TargaryenDef();
        this.atk = stat.TargaryenAtk();
        this.range = stat.TargaryenRange();
    }

    private void setLannisterAtributes() {    //define status do personagem
        Lannister stat = new Lannister();
        this.hp = stat.LannisterHp();
        this.def = stat.LannisterDef();
        this.atk = stat.LannisterAtk();
        this.range = stat.LannisterRange();
    }

    private void setStarkAtributes() {    //define status do personagem
        Stark stat = new Stark();
        this.hp = stat.StarkHp();
        this.def = stat.StarkDef();
        this.atk = stat.StarkAtk();
        this.range = stat.StarkRange();
    }

    public boolean isAlive() {
        return (this.hp > 0);
    }

    public boolean searchEnemy(Personagem enemy) {
        return (Math.abs(enemy.position[0] - this.position[0]) <= this.range && Math.abs(enemy.position[1] - this.position[1]) <= this.range); //verifica se o inimigo está ao alcance
    }

    public int attack(Personagem enemy) { //ataca o inimigo selecionado caso ele esteja no alcance do personagem
        if (searchEnemy(enemy)) {
            switch (this.family) {
                case 'S' -> {
                    if (enemy.family == 'S') {
                        enemy.hp = (int) (enemy.hp - (this.atk - enemy.def)*(8f/10));
                    }
                    else
                        enemy.hp = enemy.hp - (this.atk - enemy.def);
                }
                case 'L' -> {
                    if (enemy.family == 'S') {
                        enemy.hp = (int) ((enemy.hp - (this.atk - enemy.def + this.atk * 15f/100)*(8f/10)));
                    }
                    else
                        enemy.hp = (int) (enemy.hp - (this.atk - enemy.def + this.atk * 15f/100));
                }
                case 'T' -> {
                    if (enemy.family == 'S') {
                        enemy.hp = (int) (enemy.hp - (this.atk) * (8f / 10));
                    } else {
                        enemy.hp = enemy.hp - (this.atk);
                    }
                }
                default -> {
                    break;
                }
            }
            return 1;
        } else {
            System.out.println("Movimento inválido!");//charminho
        }
        return 0;
    }

    public void walk(String input) {    //personagem anda na direção escolhida
        switch(input){
                case "W": 
                    this.position[0]--;
                    break;
                case "S": 
                    this.position[0]++;
                    break;
                case "A": 
                    this.position[1]--;
                    break;
                case "D": 
                    this.position[1]++;
                    break;
                case "WD": 
                    this.position[0]--; 
                    this.position[1]++; 
                    break;
                case "WA": 
                    this.position[0]--; 
                    this.position[1]--; 
                    break;
                case "SA": 
                    this.position[0]++; 
                    this.position[1]--; 
                    break;
                case "SD": 
                    this.position[0]++; 
                    this.position[1]++; 
                    break;
            }
    }
}
>>>>>>> Arthur
