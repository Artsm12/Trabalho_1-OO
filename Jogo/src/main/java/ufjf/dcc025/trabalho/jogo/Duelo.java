package ufjf.dcc025.trabalho.jogo;

import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Duelo {

    protected Tabuleiro tabuleiro;
    protected Personagem[] player1;
    protected Personagem[] player2;
    protected int primeiroJogador;
    protected boolean acabou;

    private Scanner teclado = new Scanner(System.in);

    public Duelo(int primeiroJogador) {
        this.player1 = new Personagem[3];
        this.player2 = new Personagem[3];
        this.primeiroJogador = primeiroJogador;
        this.acabou = false;
    }

    public void inicio() {
        this.tabuleiro = new Tabuleiro();
        this.tabuleiro.desenhaTabuleiro();
        
        for(int i = 0; i < 3; i++) {
            criaPersonagem(1, i);
            limpaTerminal();
            this.tabuleiro.desenhaTabuleiro();
        }
        
        for(int i = 0; i < 3; i++) {
            criaPersonagem(2, i);
            limpaTerminal();
            this.tabuleiro.desenhaTabuleiro();
        }
        
        //System.exit(0);

        limpaTerminal();
        if (this.primeiroJogador == 1) {
            System.out.println("O jogador 1 comeca!\n");
        } else {
            System.out.println("O jogador 2 comeca!\n");
        }
        
        
        
        if (this.primeiroJogador == 1) {
            while (!acabou) {
                turno(1);
                turno(2);
            }
        } else {
            while (!acabou) {
                turno(2);
                turno(1);
            }
        }
    }

    public void criaPersonagem(int jogador, int indice) {
        Random random = new Random();
        char family = selecaoDePersonagem(jogador);

        int i, j;
        do {
            if (jogador == 1) {
                i = random.nextInt(2);
            } else {
                i = random.nextInt(2) + 8;
            }
            j = random.nextInt(10);
        } while (!this.tabuleiro.ehVazia(i, j));

        System.out.println("\nEscolha um nome para seu personagem");
        String nome = teclado.nextLine();

        if (jogador == 1) {
            player1[indice] = new Personagem(i, j, family, nome);
            this.tabuleiro.setCasa(i, j, jogador, family);
        } else {
            player2[indice] = new Personagem(i, j, family, nome);
            this.tabuleiro.setCasa(i, j, jogador, family);
        }
    }

    public void imprimeMenuDeMovimentacao(int jogador, int indice) {
        if(jogador == 1) {
            System.out.println(this.player1[indice].getNome() + " ******* " + this.player1[indice].getFamily());
        }
        else {
            System.out.println(this.player2[indice].getNome() + " ******* " + this.player2[indice].getFamily());
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
        System.out.println("**************************Jogador " + jogador + ", Escolha a familia do seu personagem*************************");
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
    
    protected void checaPersonagens(int jogador){
        int [] pos;
        if(jogador == 1){
            for(int i=0; i<3; i++){
                if(!player1[i].isAlive()){
                    pos = player1[i].getPosition();
                    this.tabuleiro.limpaCasa(pos[0], pos[1]);
                }
            }
        }
        else{
            for(int i=0; i<3; i++){
                if(!player2[i].isAlive()){
                    pos = player2[i].getPosition();
                    this.tabuleiro.limpaCasa(pos[0], pos[1]);
                }
            }
        }
    }
    
    protected boolean checaTime(int jogador){
        if(jogador == 1)
            return (!player1[0].isAlive() && !player1[1].isAlive() && !player1[2].isAlive());
        return (!player2[0].isAlive() && !player2[1].isAlive() && !player2[2].isAlive());
        
    }
    
    public int escolheBoneco(int jogador) {
        int indice = 0;
     
        if(jogador == 1) {
            this.tabuleiro.selecionaCasa(player1[indice].getPosition());
        }
        else {
            this.tabuleiro.selecionaCasa(player2[indice].getPosition());
        }
        
        this.tabuleiro.desenhaTabuleiro();
        imprimeInformacoesDoPersonagem(jogador, indice);
        System.out.println("(A) Personagem anterior | (D) Proximo personagem");
        System.out.println("(E) Confirmar escolha");
        
        String input;
        input = teclado.nextLine().toUpperCase();
        limpaTerminal();
        
        while(true) {
            switch(input) {
                case "A" -> {
                    if(indice - 1 < 0)
                        indice = 2;
                    else
                        indice -= 1;
                    
                    if(jogador == 1) {
                        do {
                            if(indice - 1 < 0)
                                indice = 2;
                            else
                                indice -= 1;
                        }while(!player1[indice].isAlive());
                        
                        this.tabuleiro.selecionaCasa(player1[indice].getPosition());
                        this.tabuleiro.desenhaTabuleiro();
                        imprimeInformacoesDoPersonagem(1, indice);
                    }
                    else {
                        do {
                            if(indice - 1 < 0)
                                indice = 2;
                            else
                                indice -= 1;
                        }while(!player2[indice].isAlive());
                        
                        this.tabuleiro.selecionaCasa(player2[indice].getPosition());
                        this.tabuleiro.desenhaTabuleiro();
                        imprimeInformacoesDoPersonagem(1, indice);
                    }
                    System.out.println("(A) Personagem anterior | (D) Proximo personagem");
                    System.out.println("(E) Confirmar escolha");
                    input = teclado.nextLine().toUpperCase();
                    limpaTerminal();
                    break;
                }
                case "D" -> {
                    if(indice + 1 > 2)
                        indice = 0;
                    else
                        indice += 1;
                    
                    if(jogador == 1) {
                        do {
                            if(indice + 1 > 2)
                                indice = 2;
                            else
                                indice += 1;
                        }while(!player1[indice].isAlive());
                       
                        this.tabuleiro.selecionaCasa(player1[indice].getPosition());
                        this.tabuleiro.desenhaTabuleiro();
                        imprimeInformacoesDoPersonagem(1, indice);
                    }
                    else {
                        do {
                            if(indice + 1 > 2)
                                indice = 2;
                            else
                                indice += 1;
                        }while(!player2[indice].isAlive());
                        
                        this.tabuleiro.selecionaCasa(player2[indice].getPosition());
                        this.tabuleiro.desenhaTabuleiro();
                        imprimeInformacoesDoPersonagem(2, indice);
                    }
                    System.out.println("(A) Personagem anterior | (D) Proximo personagem");
                    System.out.println("(E) Confirmar escolha");
                    input = teclado.nextLine().toUpperCase();
                    limpaTerminal();
                    break;
                }
                case "E" -> {
                    limpaTerminal();
                    return indice;
                }
                default -> {
                    this.tabuleiro.desenhaTabuleiro();
                    System.out.println("Input invalido");
                    System.out.println("(A) Personagem anterior | (D) Proximo personagem");
                    System.out.println("(E) Confirmar escolha");
                    input = teclado.nextLine().toUpperCase();
                    break;
                }
            }
        }
    }
    
    public void imprimeInformacoesDoPersonagem(int jogador, int indice) {
        if(jogador == 1) {
            System.out.println("Nome: " + player1[indice].getNome());
            System.out.println("Indice: " + indice);
            System.out.println("Familia: " + player1[indice].getFamily());
            System.out.println("Hp: " + player1[indice].getHp());
        }
        else {
            System.out.println("Nome: " + player2[indice].getNome());
            System.out.println("Indice: " + indice);
            System.out.println("Familia: " + player2[indice].getFamily());
            System.out.println("Hp: " + player2[indice].getHp());
        }
    }
    
    public void turno(int jogador) {
        checaPersonagens(jogador);
        if(checaTime(1)){
            acabou = true;
            return;
        }
        if(checaTime(2)){
            acabou = true;
            return;
        }
       
        System.out.println("Jogador " + jogador + ", escolha um personagem para movimentar:\n");
        int indice = escolheBoneco(jogador);
        
        String input;

        int pos[];
        if (jogador == 1) {
            this.tabuleiro.selecionaCasa(this.player1[indice].getPosition());
             pos = this.player1[indice].getPosition();
        } else {
            this.tabuleiro.selecionaCasa(this.player2[indice].getPosition());
             pos = this.player2[indice].getPosition();
        }
        this.tabuleiro.desenhaTabuleiro();
        imprimeMenuDeMovimentacao(jogador, 0);
        do{
            input = teclado.nextLine().toUpperCase();
            switch(input){
                case "W" -> { 
                    if(verificaSlot(pos[0]-1, pos[1])){
                        if(jogador == 1){
                            player1[indice].walk("W");
                        }
                        else
                            player2[indice].walk("W");
                        
                        this.tabuleiro.limpaCasa(pos[0]+1, pos[1]);
                    }
                    else{
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                    
                case "S" -> { 
                    if(verificaSlot(pos[0]+1, pos[1])){
                        if(jogador == 1){
                            player1[indice].walk("S");
                        }
                        else
                            player2[indice].walk("S");
                        
                        this.tabuleiro.limpaCasa(pos[0]-1, pos[1]);
                    }
                    else{
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "A" -> {
                    if(verificaSlot(pos[0], pos[1]-1)){
                        if(jogador == 1){
                            player1[indice].walk("A");
                        }
                        else
                            player2[indice].walk("A");
                        
                        this.tabuleiro.limpaCasa(pos[0], pos[1]+1);
                    }
                    else{
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "D" -> {
                    if(verificaSlot(pos[0], pos[1]+1)){
                        if(jogador == 1){
                            player1[indice].walk("D");
                        }
                        else
                            player2[indice].walk("D");
                        
                        this.tabuleiro.limpaCasa(pos[0], pos[1]-1);
                    }
                    else{
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "WD", "DW" -> {
                    if(verificaSlot(pos[0]-1, pos[1]+1)){
                        if(jogador == 1)
                            player1[indice].walk("WD");
                        else
                            player2[indice].walk("WD");
                        
                        this.tabuleiro.limpaCasa(pos[0]+1, pos[1]-1);
                    }
                    else{
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "WA", "AW" -> {
                    if(verificaSlot(pos[0]-1, pos[1]-1)){
                        if(jogador == 1)
                            player1[indice].walk("WA");
                        else
                            player2[indice].walk("WA");
                        
                        this.tabuleiro.limpaCasa(pos[0]+1, pos[1]+1);
                    }
                    else{
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "SA", "AS" -> {
                    if(verificaSlot(pos[0]+1, pos[1]-1)){
                        if(jogador == 1)
                            player1[indice].walk("SA");
                        else
                            player2[indice].walk("SA");
                        
                        this.tabuleiro.limpaCasa(pos[0]-1, pos[1]+1);
                    }
                    else{
                        System.out.println("Movimento invalido!");
                        input = "X";
                    }
                }
                case "SD", "DS" -> {
                    if(verificaSlot(pos[0]+1, pos[1]+1)){
                        if(jogador == 1)
                            player1[indice].walk("SD");
                        else
                            player2[indice].walk("SD");
                        
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
            pos = player1[i].getPosition();
            if(player1[i].isAlive())
                this.tabuleiro.setCasa(pos[0], pos[1], 1, player1[i].getFamily());
        }
        for(int i=0; i<3; i++){
            pos = player2[i].getPosition();
            if(player2[i].isAlive())
                this.tabuleiro.setCasa(pos[0], pos[1], 2, player2[i].getFamily());
        }
        this.tabuleiro.desenhaTabuleiro();
        int selec = 0;
        limpaTerminal();
        System.out.println("Escolha o personagem que vai atacar: ");
        selec = escolheBoneco(jogador);
        if(jogador == 1)
            atacar(player1[selec], player2);
        else 
            atacar(player2[selec], player1);
        
        System.out.println("Equipe 1: ");
        for(int i=0; i<3; i++)
            System.out.print(player1[i].getNome() + ": " +  player1[i].getHp() + " | ");
        System.out.println("");
        System.out.println("Equipe 2: ");
        for(int i=0; i<3; i++)
            System.out.print(player2[i].getNome() + ": " +  player2[i].getHp() + " | ");
        System.out.println("");
    }

    protected void atacar(Personagem player, Personagem[] enemy){
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
                    teclado.nextLine();
                
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
    
    protected boolean verificaSlot(int i, int j){
        return (i>=0 && i<10 && j>=0 && j<10 && this.tabuleiro.ehVazia(i, j));
    }
    
    public static void limpaTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    
}
