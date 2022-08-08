package recusosJogo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * A classe <b>FundoDoMar</b> define um tipo de dado abstrato para criação da
 * estrutura de classes de um jogo.
 *
 * @author Rafael Alves de Lima
 * @author Gabriel Kato Gomes
 *
 * @since ago 2022.
 *
 * @version 1.0
 */
public class FundoDoMar {

    /**
     *
     */
    private final int posicaoEmX = 0;

    /**
     *
     */
    private final int posicaoEmY = 70;

    /**
     * O atributo imagem é utilizado para guardar a imagem do fundo do jogo.
     */
    private BufferedImage imagem;

    /**
     * Construtor default da classe <b>FundoDoMar</b>.<br><br>
     * <b>Uso:</b><br>
     * FundoDoMar fundoDoMar = new FundoDoMar();<br><br>
     */
    public FundoDoMar() {
        try {
            imagem = ImageIO.read(getClass().getResourceAsStream("/res/background/fundo-do-mar.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro: Imagem do fundo do mar não encontrada", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Renderiza a imagem do fundo do jogo
     *
     * @param g2 Desenha a imagem na tela
     */
    public void draw(Graphics2D g2) {
        g2.drawImage(getImagem(), getPosicaoEmX(), getPosicaoEmY(), null);
    }

    /**
     * @return the posicaoEmX
     */
    public int getPosicaoEmX() {
        return posicaoEmX;
    }

    /**
     * @return the posicaoEmY
     */
    public int getPosicaoEmY() {
        return posicaoEmY;
    }

    /**
     * @return the imagem
     */
    public BufferedImage getImagem() {
        return imagem;
    }
}
