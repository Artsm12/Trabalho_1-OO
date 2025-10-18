package ufjf.dcc025.trabalho.jogo;

public class Casa {
    
    private int valor;              // 1 = player 1, 2 = player 2
    private char family;            // S = stark, L = lannister, T = targaryen
    private boolean selecionada;    // indica se a casa esta selecionada ou nao
    
    public Casa() {
        this.valor = 0;
        this.family = ' ';
        this.selecionada = false;
    }

    public boolean estaVazia() {
        if(this.valor == 0)
            return true;
        return false;
    }
    
    public int getValor() {
        return valor;
    }
    
    public void setValor(int val) {
        this.valor = val;
    }
    
    public char getFamily() {
        return family;
    }
    
    public void setFamily(char family) {
        this.family = family;
    }

    public boolean selecionada() {
        return selecionada;
    }
    
    public void seleciona() {
        this.selecionada = true;
    }
    
    public void removeSelecao() {
        this.selecionada = false;
    }
}
