package recusosJogo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * A classe <b>MergulhadoresImg</b> define um tipo de dado abstrato para criação
 * da estrutura de classes de um jogo.
 *
 * @author Rafael Alves de Lima.
 * @author Gabriel Kato Gomes.
 *
 * @since ago 2022.
 *
 * @version 1.0
 */
public class MergulhadoresImg {

    /**
     *
     */
    private final int largura = 40;

    /**
     *
     */
    private final int altura = 30;

    /**
     * O atributo mergulhadoresImgs é utilizado para referenciar um ArrayList de
     * <b>MergulhadoresImg</b>.
     */
    private List<MergulhadoresImg> mergulhadoresImgs;

    /**
     * O atributo imageMergulhadores é utilizado para guardar a imagem da
     * quantidade de mergulhadores já coletados pelo jogador.
     */
    private BufferedImage imageMergulhadores;

    /**
     * O atributo numeroDeMergulhadoresAnterior é utilizado para saber se a
     * quantidade de mergulhadores foi alterada.
     */
    private int numeroDeMergulhadoresAnterior;

    /**
     * Construtor default da classe <b>MergulhadoresImg</b>.<br><br>
     * <b>Uso:</b><br>
     * MergulhadoresImg mergulhadoresImg = new MergulhadoresImg();<br><br>
     */
    public MergulhadoresImg() {
        numeroDeMergulhadoresAnterior = 0;
        mergulhadoresImgs = new ArrayList<>();

        try {
            imageMergulhadores = ImageIO.read(getClass().getResource("/res/mergulhador/merg_direita_1.png"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro: Imagem da quantidade de mergulhadores não encontrada", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Atualiza se a quantidade de mergulhadores coletados foi alterado.
     *
     * @param numeroDeMergulhadores Número de megulhadores coletados pelo
     * usuário.
     */
    public void atualiza(int numeroDeMergulhadores) {
        if (numeroDeMergulhadores != numeroDeMergulhadoresAnterior) {
            numeroDeMergulhadoresAnterior = numeroDeMergulhadores;

            for (int i = 0; i < numeroDeMergulhadores; i++) {
                mergulhadoresImgs.add(new MergulhadoresImg());
            }
        }
    }

    /**
     * Desenha os mergulhadores que ainda estão no ArrayList na tela
     *
     * @param g2 Faz os desenhos na tela
     */
    public void desenha(Graphics2D g2) {
        int x = 200;
        int y = 520;

        for (int i = 0; i < numeroDeMergulhadoresAnterior; i++) {
            g2.drawImage(imageMergulhadores, x, y, largura, altura, null);

            x += 45;
        }
    }
}
