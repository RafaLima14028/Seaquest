package recusosJogo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A classe <b>Teclado</b> define um tipo de dado abstrato para criação da
 * estrutura de classes de um jogo.
 *
 * @author Rafael Alves de Lima.
 * @author Gabriel Kato Gomes.
 *
 * @since ago 2022.
 *
 * @version 1.0
 */
public class Teclado implements KeyListener {

    /**
     * O atributo precionaCima é utilizado para referenciar se a direção que o
     * jogador está precionando é para cima.
     */
    private boolean precionaCima;

    /**
     * O atributo precionaBaixo é utilizado para referenciar se a direção que o
     * jogador está precionando é para baixo.
     */
    private boolean precionaBaixo;

    /**
     * O atributo precionaEsquerda é utilizado para referenciar se a direção que
     * o jogador está precionando é para esquerda.
     */
    private boolean precionaEsquerda;

    /**
     * O atributo precionaDireita é utilizado para referenciar se a direção que
     * o jogador está precionando é para direita.
     */
    private boolean precionaDireita;

    /**
     * O atributo precionaEmergir é utilizado para referenciar se a direção que
     * o jogador está precionando é para emergir.
     */
    private boolean precionaEmergir;

    /**
     * O atributo precionaAtirar é utilizado para referenciar se a direção que o
     * jogador está precionando é para atirar.
     */
    private boolean precionaAtirar;

    /**
     * Retorna se o jogador está se movendo para qualquer direção.
     *
     * @return True caso esteja precionando e False caso contrario.
     */
    public boolean movendo() {
        return isPrecionaCima() || isPrecionaBaixo()
                || isPrecionaEsquerda() || isPrecionaDireita();
    }

    /**
     * Realiza alguma ação se uma chave for digitada.
     *
     * @param e Realiza um evento.
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Realiza alguma ação se uma chave for precionada.
     *
     * @param e Realiza um evento.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            precionaCima = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            precionaBaixo = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            precionaEsquerda = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            precionaDireita = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            precionaAtirar = true;
        }
        if (code == KeyEvent.VK_E) {
            precionaEmergir = true;
        }
    }

    /**
     * Realiza alguma ação se uma chave for desprecionada.
     *
     * @param e Realiza um evento.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            precionaCima = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            precionaBaixo = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            precionaEsquerda = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            precionaDireita = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            precionaAtirar = false;
        }
        if (code == KeyEvent.VK_E) {
            precionaEmergir = false;
        }
    }

    /**
     * Retorna se o jogador está precionando a seta para cima.
     *
     * @return True se for verdadeiro e False caso contrario.
     */
    public boolean isPrecionaCima() {
        return precionaCima;
    }

    /**
     * Retorna se o jogador está precionando a seta para baixo.
     *
     * @return True se for verdadeiro e False caso contrario.
     */
    public boolean isPrecionaBaixo() {
        return precionaBaixo;
    }

    /**
     * Retorna se o jogador está precionando a seta para esquerda.
     *
     * @return True se for verdadeiro e False caso contrario.
     */
    public boolean isPrecionaEsquerda() {
        return precionaEsquerda;
    }

    /**
     * Retorna se o jogador está precionando a seta para direita.
     *
     * @return True se for verdadeiro e False caso contrario.
     */
    public boolean isPrecionaDireita() {
        return precionaDireita;
    }

    /**
     * Retorna se o jogador está precionando a seta para emergir.
     *
     * @return True se for verdadeiro e False caso contrario.
     */
    public boolean isPrecionaEmergir() {
        return precionaEmergir;
    }

    /**
     * Retorna se o jogador está precionando a seta para atirar.
     *
     * @return True se for verdadeiro e False caso contrario.
     */
    public boolean isPrecionaAtirar() {
        return precionaAtirar;
    }
}
