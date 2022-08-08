package recusosJogo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * A classe <b>Jogo</b> é responsavel por atualizar e desenhar os elementos do
 * jogo.
 *
 * @author Rafael Alves de Lima
 * @author Gabriel Kato Gomes
 *
 * @since ago 2022.
 *
 * @version 1.0
 */
public class Jogo extends JPanel implements Runnable {

    /**
     * O atributo teclado é utilizado para referenciar o tipo de dado
     * <b>Teclado</b>, com as ações possíveis do jogador.
     */
    private Teclado teclado = new Teclado();

    /**
     * O atributo threadJogo é utilizado para referenciar uma
     * <b>Thread</b> para o jogo.
     */
    private Thread threadJogo;

    /**
     * O atributo player é utilizado para referenciar o tipo de dado
     * <b>Player</b> que controla tudo referente ao jogador.
     */
    private Jogador player;

    /**
     * O atributo fundoDoMar é utilizado para referenciar o tipo de dado
     * <b>FundoDoMar</b> que guarda o fundo do jogo.
     */
    private FundoDoMar fundoDoMar;

    /**
     * O atributo peixes é utilizado para referenciar o tipo de dado
     * <b>Peixes</b> que controla tudo referente aos peixes.
     */
    private Peixes peixes;

    /**
     * O atributo mergulhador é utilizado para referenciar o tipo de dado
     * <b>Mergulhador</b> que controla tudo referente aos mergulhadores.
     */
    private Mergulhador mergulhador;

    /**
     * O atributo subInimigo é utilizado para referenciar o tipo de dado
     * <b>SubmarinoInimigo</b> que controla tudo referente aos submarinos
     * inimigos.
     */
    private SubmarinoInimigo subInimigo;

    /**
     * O atributo inicializaJogo é utilizado para referenciar o tipo de dado
     * <b>InicializaJogo</b> que controla tudo referente ao inicio e fim do
     * jogo.
     */
    private InicializaJogo inicializaJogo;

    /**
     * Construtor default da classe <b>Jogo</b>.<br><br>
     * <b>Uso:</b><br>
     * Jogo jogo = new Jogo(inicializaJogo);<br><br>
     *
     * @param inicializaJogo Classe onde a classe <b>Jogo</b> é instanciada.
     */
    public Jogo(InicializaJogo inicializaJogo) {
        setPreferredSize(new Dimension(700, 400));
        setBackground(new Color(8, 47, 170));
        setDoubleBuffered(true);
        addKeyListener(teclado);
        setFocusable(true);
        setIgnoreRepaint(true);

        inicializaVariaveis(inicializaJogo);
    }

    /**
     * Instancia as váriaveis da classe <b>Jogo</b>.
     *
     * @param inicializaJogo Classe onde a classe <b>Jogo</b> é instanciada.
     */
    private void inicializaVariaveis(InicializaJogo inicializaJogo) {
        player = new Jogador(teclado);
        fundoDoMar = new FundoDoMar();
        peixes = new Peixes();
        mergulhador = new Mergulhador();
        subInimigo = new SubmarinoInimigo();

        this.inicializaJogo = inicializaJogo;
    }

    /**
     * Instancia uma <b>Thread</b> e inicia a mesma.
     */
    public void comecaJogoThread() {
        threadJogo = new Thread(this);
        threadJogo.start();
    }

    /**
     * Calcula a velocidade de atualização(FPS), atualiza e desenha os elementos
     * do jogo.
     */
    @Override
    public void run() {
        double intervaloDeDesenho = 1000000000 / 60; // 60 FPS
        double delta = 0;
        long ultimaVez = System.nanoTime();
        long tempoAtual;
        long cronometro = 0;
        int contadorDeDesenho = 0;

        while (threadJogo != null) {
            tempoAtual = System.nanoTime();

            delta += (tempoAtual - ultimaVez) / intervaloDeDesenho;
            cronometro += (tempoAtual - ultimaVez);
            ultimaVez = tempoAtual;

            if (delta >= 1) {
                if (atualiza()) {
                    repaint();
                } else {
                    threadJogo = null;
                    inicializaJogo.fimDoJogo(player.getPontuacao());
                }

                delta--;
                contadorDeDesenho++;
            }
            if (cronometro >= 1000000000) {
                contadorDeDesenho = 0;
                cronometro = 0;
            }
        }
    }

    /**
     * Atualiza todos os elementos do jogo
     *
     * @return True se o player ainda estiver vivo, senão False
     */
    private boolean atualiza() {
        if (player.isVivo()) {
            player.atualiza();
            subInimigo.atualiza(player, player.getTirosDoJogador());
            peixes.atualiza(player, player.getTirosDoJogador());
            mergulhador.atualiza(player);

            return true;
        } else {
            return false;
        }
    }

    /**
     * Desenha todos os elementos do jogo
     *
     * @param g Desenhar os elementos gráficos
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        fundoDoMar.draw(g2);
        player.desenha(g2);
        subInimigo.desenha(g2);
        peixes.desenha(g2);
        mergulhador.desenha(g2);

        g2.dispose();
    }
}
