package ufjf.dcc025.trabalho.jogo.SistemaDeReplay;
import ufjf.dcc025.trabalho.jogo.TabuleiroUtils.Tabuleiro;
import ufjf.dcc025.trabalho.jogo.Personagens.Personagem;
import ufjf.dcc025.trabalho.jogo.Jogabilidade.Jogo;

public class Jogada {
    
    private Tabuleiro tabuleiro;
    private String ataque;

    public Jogada(Tabuleiro t, int jogador) {
        this.tabuleiro = new Tabuleiro();
        this.tabuleiro.copia(t);
        if(Jogo.modoDeJogo == 1 || jogador == 1) {
            this.ataque = "O jogador " + jogador + " nao atacou";
        }
        else {
            this.ataque = "O bot nao atacou";
        }
    }

    public Jogada(Tabuleiro t, Personagem aliado, Personagem inimigo, int dano) {
        this.tabuleiro = new Tabuleiro();
        this.tabuleiro.copia(t);
        this.ataque = aliado.getNome() + "(" + aliado.getFamily() + ") acertou " + inimigo.getNome() + 
                      "(" + inimigo.getFamily() + ") e causou " + dano + " de dano!"; 
    }
    
    public void imprimeJogada() {
        this.tabuleiro.desenhaTabuleiro();
        System.out.println("\n" + ataque);
    }
}
