package ufjf.dcc025.trabalho.jogo;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Singleplayer{
    private Tabuleiro tabuleiro;
    private Personagem[] player;
    private Personagem[] computador;
    private int primeiroJogador;
    public boolean acabou;
    Scanner teclado = new Scanner(System.in);
    
    public Singleplayer(int primeiroJogador) {
        this.player = new Personagem[3];
        this.computador = new Personagem[3];
        this.primeiroJogador = primeiroJogador;
        this.acabou = false;
    }
    
    public void inicio() {
        this.tabuleiro = new Tabuleiro();
        this.tabuleiro.desenhaTabuleiro();
        
        for(int i = 0; i < 3; i++) {
            criaPersonagem(i);
            limpaTerminal();
            this.tabuleiro.desenhaTabuleiro();
        }
        
        for(int i = 0; i < 3; i++) {
            criaPersonagemBot(i);
            limpaTerminal();
        }
        this.tabuleiro.desenhaTabuleiro();
        
        //System.exit(0);

        if (this.primeiroJogador == 1) {
            System.out.println("O jogador 1 comeca!\n");
        } else {
            System.out.println("O computador comeca!\n");
        }
        
        
        
        if (this.primeiroJogador == 1) {
            while (!acabou) {
                turnoPlayer();
                //turno(2);
            }
        } else {
            while (!acabou) {
                //turno(2);
                turnoPlayer();
            }
        }
    }

    public void criaPersonagem(int indice) {
        Random random = new Random();
        char family = selecaoDePersonagem(1);

        int i, j;
        do {
            i = random.nextInt(2);
            j = random.nextInt(10);
        }while (!this.tabuleiro.ehVazia(i, j));

        System.out.println("\nEscolha um nome para seu personagem");
        String nome = teclado.nextLine();

        player[indice] = new Personagem(i, j, family, nome);
        this.tabuleiro.setCasa(i, j, 1, family);
        
    }
    
    public void criaPersonagemBot(int indice) {
        Random random = new Random();
        int famInd = random.nextInt(1, 3);
        char family = 'S';
        switch (famInd){
            case 1 -> family = 'S';
            case 2 -> family = 'L';
            case 3 -> family = 'T';
            default -> {
            }
        }
        int i, j;
        do {
            i = random.nextInt(2) + 8;
            j = random.nextInt(10);
        } while (!this.tabuleiro.ehVazia(i, j));
        String nome = "COM"+indice;

        computador[indice] = new Personagem(i, j, family, nome);
        this.tabuleiro.setCasa(i, j, 2, family);
    }
    
    public void imprimeMenuDeMovimentacao(int jogador, int indice) {
        if(jogador == 1) {
            System.out.println(this.player[indice].getNome() + " ******* " + this.player[indice].getFamily());
        }
        else {
            System.out.println(this.computador[indice].getNome() + " ******* " + this.computador[indice].getFamily());
        }
        System.out.println("(W) Ir para cima       | (WA) Diagonal para cima e esquerda");
        System.out.println("(A) Ir para a esquerda | (WD) Diagonal para cima e direita");
        System.out.println("(S) Ir para baixo      | (SA) Diagonal para baixo e esquerda");
        System.out.println("(D) Ir para a direita  | (SD) Diagonal para baixo e direita");
    }

    public char selecaoDePersonagem(int jogador) {
        imprimeSelecaoDePersonagem(jogador);
        String input;
        input = teclado.nextLine().toUpperCase();

        while (!input.equals("S") || !input.equals("L") || !input.equals("T")) {
            switch (input) {
                case "S" -> {
                    return 'S';
                }
                case "L" -> {
                    return 'L';
                }
                case "T" -> {
                    return 'T';
                }
                default -> {
                    System.out.println("Opcao invalida, insira um input valido");
                    imprimeSelecaoDePersonagem(jogador);
                    break;
                }
            }

            input = teclado.nextLine().toUpperCase();
        }

        return 'X';
    }

    public void imprimeSelecaoDePersonagem(int jogador) {
        System.out.println("**************************Jogador, Escolha a familia do seu personagem*************************");
        System.out.println("**************Stark***************|***********Lannister************|*****************Targaryen*****************");
        System.out.println("* Vida maxima: 60                 | Vida maxima: 50                | Vida maxima: 45                          *");
        System.out.println("* Ataque base: 20                 | Ataque base: 20                | Ataque base: 20                          *");
        System.out.println("* Defesa base: 10                 | Defesa base: 10                | Defesa base: 10                          *");
        System.out.println("* Alcance: 1 (corpo-a-corpo)      | Alcance: 2                     | Alcance: 3                               *");
        System.out.println("* Modificador ofensivo: nenhum    | Modificador ofensivo: +15% atk | Modificador ofensivo: ignora defesa base *");
        System.out.println("***************************************************************************************************************");
        System.out.println("(S) Stark");
        System.out.println("(L) Lannister");
        System.out.println("(T) Targaryen");
    }
    
    private void checaPersonagens(int jogador){
        int [] pos;
        if(jogador == 1){
            for(int i=0; i<3; i++){
                if(!player[i].isAlive()){
                    pos = player[i].getPosition();
                    this.tabuleiro.limpaCasa(pos[0], pos[1]);
                }
            }
        }
        else{
            for(int i=0; i<3; i++){
                if(!computador[i].isAlive()){
                    pos = computador[i].getPosition();
                    this.tabuleiro.limpaCasa(pos[0], pos[1]);
                }
            }
        }
    }
    
    private boolean checaTime(Personagem[] player1){
        return (!player1[0].isAlive() && !player1[1].isAlive() && !player1[2].isAlive());  
    }
    
    public int escolheBoneco(){
        int indice=0;
        boolean valid = false;
        do{
            System.out.println("[1] " + player[0].getNome() + "\n[2] "+ player[1].getNome()+ "\n[3] "+ player[2].getNome());
           while(true){
                try {
                    // 3. Tenta ler um número inteiro
                    indice = teclado.nextInt();
                    break;
                    // 4. Se conseguiu ler, quebra o loop 
                
                } catch (InputMismatchException e) {
                    // 5. Se deu erro (ex: digitou 'A' ou "olá")
                    System.out.println("Erro: Entrada inválida. Você deve digitar um número inteiro.");
                    System.out.println("Tente novamente.");
                
                    // 6. Limpa o buffer do scanner (IMPORTANTE!)
                    teclado.nextLine(); // Isso consome a entrada inválida que ficou "presa"
                }
            }
            indice--;
            valid = true;
        if(indice < 0 && indice > 2)
                System.out.print("Escolha invalida!");
        else if(!player[indice].isAlive()){
            System.out.println("Personagem incapacitado!");
            valid = false;
        }
        }while(indice < 0 && indice > 2 || valid == false);
        return indice;
    
    }
    
    public void turnoPlayer() {
        checaPersonagens(1);
        if(checaTime(player)){
            acabou = true;
            return;
        }
        if(checaTime(computador)){
            acabou = true;
            return;
        }
        this.tabuleiro.desenhaTabuleiro();
        
        System.out.println("Jogador, selecione um personagem para mover");
        int indice = escolheBoneco();
        
        String input;

        limpaTerminal();
        int pos[];
        this.tabuleiro.selecionaCasa(this.player[indice].getPosition());
        pos = this.player[indice].getPosition();
        
        this.tabuleiro.desenhaTabuleiro();
        imprimeMenuDeMovimentacao(1, 0);
        do{
            input = teclado.nextLine().toUpperCase();
            switch(input){
                case "W" -> { 
                    if(verificaSlot(pos[0]-1, pos[1])){
                        
                            player[indice].walk("W");
                        
                        this.tabuleiro.limpaCasa(pos[0]+1, pos[1]);
                    }
                    else{
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "S" -> { 
                    if(verificaSlot(pos[0]+1, pos[1])){
                        
                            player[indice].walk("S");
                        
                       
                        this.tabuleiro.limpaCasa(pos[0]-1, pos[1]);
                    }
                    else{
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "A" -> {
                    if(verificaSlot(pos[0], pos[1]-1)){
                        
                            player[indice].walk("A");
                        
                        this.tabuleiro.limpaCasa(pos[0], pos[1]+1);
                    }
                    else{
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "D" -> {
                    if(verificaSlot(pos[0], pos[1]+1)){
                       
                            player[indice].walk("D");
                        
                        
                        this.tabuleiro.limpaCasa(pos[0], pos[1]-1);
                    }
                    else{
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "WD" -> {
                    if(verificaSlot(pos[0]-1, pos[1]+1)){
                       
                            player[indice].walk("WD");
                        
                        this.tabuleiro.limpaCasa(pos[0]+1, pos[1]-1);
                    }
                    else{
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "WA" -> {
                    if(verificaSlot(pos[0]-1, pos[1]-1)){
                        
                            player[indice].walk("WA");
                        
                        
                        this.tabuleiro.limpaCasa(pos[0]+1, pos[1]+1);
                    }
                    else{
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "SA" -> {
                    if(verificaSlot(pos[0]+1, pos[1]-1)){
                        player[indice].walk("SA");
                        this.tabuleiro.limpaCasa(pos[0]-1, pos[1]+1);
                    }
                    else{
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "SD" -> {
                    if(verificaSlot(pos[0]+1, pos[1]+1)){
                       player[indice].walk("SD");
                       this.tabuleiro.limpaCasa(pos[0]-1, pos[1]-1);
                    }
                    else{
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                default -> input = "X";
                }
            }while(input.equals("X"));
        
        for(int i=0; i<3; i++){
            pos = player[i].getPosition();
            if(player[i].isAlive())
                this.tabuleiro.setCasa(pos[0], pos[1], 1, player[i].getFamily());
        }
        for(int i=0; i<3; i++){
            pos = computador[i].getPosition();
            if(computador[i].isAlive())
                this.tabuleiro.setCasa(pos[0], pos[1], 2, computador[i].getFamily());
        }
        this.tabuleiro.desenhaTabuleiro();
        int selec = 0;
        System.out.println("Escolha o personagem que vai atacar: ");
        selec = escolheBoneco();
        atacar(player[selec], computador);
        
        System.out.println("Equipe 1: ");
        for(int i=0; i<3; i++)
            System.out.print(player[i].getNome() + ": " +  player[i].getHp() + " | ");
        System.out.println("");
        System.out.println("Equipe 2: ");
        for(int i=0; i<3; i++)
            System.out.print(computador[i].getNome() + ": " +  computador[i].getHp() + " | ");
        System.out.println("");
    }

    private void atacar(Personagem player, Personagem[] enemy){
        System.out.println("Selecione o alvo: ");
        int escolha = 0, j=1;
        System.out.println("[0] Passar a vez");
        for(int i=0; i<3; i++)
            if(player.searchEnemy(enemy[i]) && enemy[i].isAlive()){
                System.out.println("[" + (i+1) + "]" + ' ' +  enemy[i].getNome());
                j++;
            }
        do{
            while(true){
                try {
                    // 3. Tenta ler um número inteiro
                    escolha = teclado.nextInt();
                
                    // 4. Se conseguiu ler, quebra o loop 
                    break;
                } catch (InputMismatchException e) {
                    // 5. Se deu erro (ex: digitou 'A' ou "olá")
                    System.out.println("Erro: Entrada inválida. Você deve digitar um número inteiro.");
                    System.out.println("Tente novamente.");
                
                    // 6. Limpa o buffer do scanner (IMPORTANTE!)
                    teclado.nextLine(); // Isso consome a entrada inválida que ficou "presa"
                }
            }
            if(escolha < 0 && escolha > j)
                System.out.print("Escolha uma opcao valida: ");
        }while(escolha < 0 && escolha > j);
            
        if(escolha > 0)
            player.attack(enemy[escolha-1]);
            
    }
    
    private boolean verificaSlot(int i, int j){
        return (i>=0 && i<10 && j>=0 && j<10 && this.tabuleiro.ehVazia(i, j));
    }
    
    public static void limpaTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    
}
