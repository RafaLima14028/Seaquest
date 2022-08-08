package recusosJogo;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import seaquest.Score;

/**
 * A classe <b>InicializaJogo</b> capta o nome do usuário, inicializa o jogo e
 * mostra o score final.
 *
 * @author Rafael Alves de Lima
 * @author Gabriel Kato Gomes
 *
 * @since ago 2022.
 *
 * @version 1.0
 */
public class InicializaJogo extends JFrame {

    /**
     * O atributo nomeAtual é utilizado para referenciar o nome do usuário
     * jogando no momento atual.
     */
    private String nomeAtual;

    /**
     * O atributo jogo é utilizado para referenciar a classe que envolve toda
     * parte de regras de négocio do jogo e seus respectivos gráficos.
     */
    private Jogo jogo;

    /**
     * Construtor default da classe <b>InicializaJogo</b>.<br><br>
     * <b>Uso:</b><br>
     * InicializaJogo inicializaJogo = new InicializaJogo();<br><br>
     */
    public InicializaJogo() {
        setNomeAtual();

        jogo = new Jogo(this);
        add(jogo);

        jogo.comecaJogoThread();

        inicializaJanela();
    }

    /**
     * Fecha a janela do jogo e carrega o score atualizado e sua respectiva
     * janela
     *
     * @param pontuacao Pontuação final do jogador atual
     */
    public void fimDoJogo(int pontuacao) {
        dispose();

        new Score(nomeAtual, pontuacao);
    }

    /**
     * Guarda o nome do jogador atual
     */
    private void setNomeAtual() {
        nomeAtual = "";

        do {
            nomeAtual = JOptionPane.showInputDialog("Digite seu nome: ");
        } while (nomeAtual.equalsIgnoreCase(""));
    }

    /**
     * Carrega os valores inicias do JFrame
     */
    private void inicializaJanela() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(700, 600);
        setTitle("Seaquest - Jogo");
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
