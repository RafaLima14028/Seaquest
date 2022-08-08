package recusosJogo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * A classe <b>Tiro</b> define um tipo de dado abstrato para criação da
 * estrutura de classes de um jogo.
 *
 * @author Rafael Alves de Lima.
 * @author Gabriel Kato Gomes.
 *
 * @since ago 2022.
 *
 * @version 1.0
 */
public class Tiro {

    /**
     * O atributo x é utilizado para referenciar coordenada do plano cartesino
     * em relação a x.
     */
    private int x;

    /**
     * O atributo y é utilizado para referenciar coordenada do plano cartesino
     * em relação a y.
     */
    private int y;

    /**
     * O atributo velocidade é utilizado para referenciar a velocidade que o
     * tiro se movimentando.
     */
    private int velocidade;

    /**
     * O atributo direita é utilizado para referenciar a direção que o tiro está
     * seguindo.
     */
    private boolean direita;

    /**
     * O atributo imagemTiro é utilizado para guardar a imagem do tiro.
     */
    private BufferedImage imagemTiro;

    /**
     * O atributo tiros é utilizado para referenciar um ArrayList de
     * <b>Tiro</b>.
     */
    private List<Tiro> tiros;

    /**
     * Construtor default da classe <b>Tiro</b>.<br><br>
     * <b>Uso:</b><br>
     * Tiro tiro = new Tiro();<br><br>
     */
    public Tiro() {
        tiros = new ArrayList<>();
    }

    /**
     * Construtor sobrecarregado da classe <b>Tiro</b>.<br><br>
     * <b>Uso:</b><br>
     * Tiro tiro = new Tiro(100, 250, true, 4);<br><br>
     *
     * @param x Coordenada x onde o peixe deve aparecer
     * @param y Coordenada y onde o peixe deve aparecer
     * @param direita Direcao na qual o tiro é disparado
     * @param velocidade Velocidade na qual o tiro é disparado
     */
    public Tiro(int x, int y, boolean direita, int velocidade) {
        this.x = x;
        this.y = y;
        this.velocidade = velocidade;
        this.direita = direita;

        setImagemDoTiro();
    }

    /**
     * Construtor sobrecarregado da classe <b>Tiro</b>.<br><br>
     * <b>Uso:</b><br>
     * Tiro tiro = new Tiro(100, 250, true);<br><br>
     *
     * @param x Coordenada em x
     * @param y Coordenada em y
     * @param direita Direcao na qual o tiro é disparado
     */
    public Tiro(int x, int y, boolean direita) {
        this.x = x;
        this.y = y;
        this.velocidade = 8;
        this.direita = direita;

        setImagemDoTiro();
    }

    /**
     * Carrega a imagem referente ao tiro.
     */
    private void setImagemDoTiro() {
        try {
            imagemTiro = ImageIO.read(getClass().getResourceAsStream("/res/tiro/tiro.png"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro: Imagem do tiro não foi encontrada",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Adiciona um tiro ao ArrayList.
     *
     * @param x Coordenada em x
     * @param y Coordenada em y
     * @param direita Direcao na qual o tiro é disparado
     * @param velocidade Velocidade na qual o tiro é disparado
     */
    public void adicionaTiro(int x, int y, boolean direita, int velocidade) {
        tiros.add(new Tiro(x, y, direita, velocidade));
    }

    /**
     * Adiciona um tiro ao ArrayList.
     *
     * @param x Coordenada em x
     * @param y Coordenada em y
     * @param direita Direção na qual o tiro é disparado
     */
    public void adicionaTiro(int x, int y, boolean direita) {
        tiros.add(new Tiro(x, y, direita));
    }

    /**
     * Atualiza os elementos referentes a classe <b>Tiro</b> e o estado de cada
     * tiro que está na tela.
     */
    public void atualiza() {
        for (int i = 0; i < tiros.size(); i++) {
            Tiro t = tiros.get(i);

            if (t.x >= -80 && t.y <= 750) {
                if (t.direita) {
                    t.x += t.velocidade;
                } else {
                    t.x -= t.velocidade;
                }
            } else {
                tiros.remove(t);
                t = null;
            }
        }
    }

    /**
     * Desenha os tiros que ainda estão no ArrayList na tela.
     *
     * @param g2 Rendiriza os tiros na tela.
     */
    public void desenha(Graphics2D g2) {
        for (int i = 0; i < tiros.size(); i++) {
            Tiro t = tiros.get(i);

            g2.drawImage(t.imagemTiro, t.x, t.y, null);
        }
    }

    /**
     * Retorna o ArrayList de tiros.
     *
     * @return ArrayList de tiros.
     */
    public List<Tiro> getTiros() {
        return tiros;
    }

    /**
     * Retorna um elemento da posição desejada do ArrayList.
     *
     * @param index É a posição do ArrayList desejado
     * @return Um elemento do tipo <b>Tiro</b> da posição desejada
     */
    public Tiro getTirosIndex(int index) {
        return tiros.get(index);
    }

    /**
     * Remove um elemento do ArrayList.
     *
     * @param t Entra um tiro do tipo <b>Tiro</b>
     */
    public void removeObjeto(Tiro t) {
        tiros.remove(t);
    }

    /**
     * Retorna o tamanho do ArrayList de tiros.
     *
     * @return Tamanho do ArrayList
     */
    public int getTamanho() {
        return tiros.size();
    }

    /**
     * Retorna a coordenada x do tiro.
     *
     * @return Coordenada x
     */
    public int getX() {
        return x;
    }

    /**
     * Retorna a coordenada y do tiro.
     *
     * @return Coordenada y
     */
    public int getY() {
        return y;
    }

    /**
     * Retorna a largura da imagem do tiro.
     *
     * @return Largura da imagem do tiro
     */
    public int getLargura() {
        return imagemTiro.getWidth();
    }

    /**
     * Retorna a altura da imagem do tiro.
     *
     * @return Altura da imagem do tiro
     */
    public int getAltura() {
        return imagemTiro.getHeight();
    }
}
