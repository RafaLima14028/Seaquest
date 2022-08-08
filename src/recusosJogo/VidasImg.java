package recusosJogo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * * A classe <b>VidasImg</b> define um tipo de dado abstrato para criação da
 * estrutura de classes de um jogo.
 *
 * @author Rafael Alves de Lima.
 * @author Gabriel Kato Gomes.
 *
 * @since ago 2022.
 *
 * @version 1.0
 */
public class VidasImg {

    /**
     *
     */
    private final int tamanhoImg = 30;

    /**
     * O atributo vidasImgs é utilizado para referenciar um ArrayList de
     * <b>VidasImg</b>.
     */
    private List<VidasImg> vidasImgs;

    /**
     * O atributo imagemVidas é utilizado para guardar a imagem da quantidade de
     * vidas já coletados pelo jogador.
     */
    private BufferedImage imagemVidas;

    /**
     * O atributo numeroDeVidasAnterior é utilizado para referenciar o número de
     * vidas que o jogador tem.
     */
    private int numeroDeVidasAnterior;

    /**
     * Construtor default da classe <b>VidasImg</b>.<br><br>
     * <b>Uso:</b><br>
     * VidasImg vidasImg = new VidasImg();<br><br>
     */
    public VidasImg() {
        numeroDeVidasAnterior = 0;
        vidasImgs = new ArrayList<>();

        try {
            imagemVidas = ImageIO.read(getClass().getResource("/res/player/submarine_direita_parado.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro: Imagem das vidas não encontradas",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Atualiza a quantidade de vidas que o jogador tem.
     *
     * @param vidas Número de vidas do jogador.
     */
    public void atualiza(int vidas) {
        if (numeroDeVidasAnterior != vidas) {
            numeroDeVidasAnterior = vidas;

            for (int i = 0; i < vidas; i++) {
                vidasImgs.add(new VidasImg());
            }
        }
    }

    /**
     * Renderiza as vidas do jogador.
     *
     * @param g2 Renderiza os elementos gráficos
     */
    public void desenha(Graphics2D g2) {
        int x = 220;
        int y = 80;

        for (int i = 0; i < numeroDeVidasAnterior; i++) {
            g2.drawImage(imagemVidas, x, y, tamanhoImg, tamanhoImg, null);
            x += 35;
        }
    }
}
