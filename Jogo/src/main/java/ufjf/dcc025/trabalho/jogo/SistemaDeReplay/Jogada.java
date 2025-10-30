package ufjf.dcc025.trabalho.jogo.SistemaDeReplay;
import ufjf.dcc025.trabalho.jogo.TabuleiroUtils.Tabuleiro;
import ufjf.dcc025.trabalho.jogo.Personagens.Personagem;

public class Jogada {
    
    private Tabuleiro tabuleiro;
    private String ataque;

    public Jogada(Tabuleiro tabuleiro, Personagem p) {
        this.tabuleiro = tabuleiro;
        this.ataque = p.getNome() + " (" + p.getFamily() + ")  pulou sua vez!";
    }

    public Jogada(Tabuleiro tabuleiro, Personagem aliado, Personagem inimigo, int dano) {
        this.tabuleiro = tabuleiro;
        this.ataque = aliado.getNome() + "(" + aliado.getFamily() + ") acertou " + inimigo.getNome() + 
                      " (" + inimigo.getFamily() + ") e causou " + dano + " de dano!"; 
    }
    
    public void imprimeJogada() {
        this.tabuleiro.desenhaTabuleiro();
        System.out.println("\n" + ataque);
    }
}
