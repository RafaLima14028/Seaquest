package recusosJogo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * A classe <b>Mergulhador</b> define um tipo de dado abstrato para criação da
 * estrutura de classes de um jogo.
 *
 * @author Rafael Alves de Lima.
 * @author Gabriel Kato Gomes.
 *
 * @since ago 2022.
 *
 * @version 1.0
 */
public class Mergulhador extends Npc {

    /**
     * O atributo mergulhadores é utilizado para referenciar um ArrayList de
     * <b>Mergulhador</b>.
     */
    private List<Mergulhador> mergulhadores;

    /**
     * O atributo contadorParaLiberarMergulhadores é utilizado para liberar os
     * mergulhadores no momento correto.
     */
    private int contadorParaLiberarMergulhadores;

    /**
     * O atributo tempoParaLiberarMergulhador é utilizado para saber o momento
     * correto para fazer a liberação do mergulhador.
     */
    private int tempoParaLiberarMergulhador;

    /**
     * O atributo velocidadeParaCadaNivel é utilizado para saber a velocidade da
     * fase atual.
     */
    private int velocidadeParaCadaNivel;

    /**
     * O atributo aleatorio, do tipo <b>Random</b> é utilizado para referenciar
     * um valor aleatorio.
     */
    private Random aleatorio;

    /**
     * Construtor default da classe <b>Mergulhador</b>.<br><br>
     * <b>Uso:</b><br>
     * Mergulhador mergulhador = new Mergulhador();<br><br>
     */
    public Mergulhador() {
        contadorParaLiberarMergulhadores = 0;
        tempoParaLiberarMergulhador = 400;
        velocidadeParaCadaNivel = 3;

        mergulhadores = new ArrayList<>();
        aleatorio = new Random();
    }

    /**
     * Construtor sobrecarregado da classe <b>Mergulhador</b>.<br><br>
     * <b>Uso:</b><br>
     * Mergulhador mergulhador = new Mergulhador(250, 4, true);<br><br>
     *
     * @param y Coordena em y onde o mergulhador deve aparecer
     * @param velocidade Velocidade na qual o mergulhador se move
     * @param direita Se a direção que o mergulhador está indo é direita ou
     * esquerda
     */
    public Mergulhador(int y, int velocidade, boolean direita) {
        super(y, velocidade, direita);
    }

    /**
     * Carrega as imagens referentes ao mergulhador.
     */
    @Override
    protected void setImagens() {
        try {
            setEsquerdaMovendo1(ImageIO.read(getClass().getResourceAsStream("/res/mergulhador/merg_esquerda_1.png")));
            setEsquerdaMovendo2(ImageIO.read(getClass().getResourceAsStream("/res/mergulhador/merg_esquerda_2.png")));
            setDireitaMovendo1(ImageIO.read(getClass().getResourceAsStream("/res/mergulhador/merg_direita_1.png")));
            setDireitaMovendo2(ImageIO.read(getClass().getResourceAsStream("/res/mergulhador/merg_direita_2.png")));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro: Imagens do mergulhador nao encontrada", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Atualiza os elementos referentes a classe <b>Mergulhador</b> e o estado
     * de cada mergulhador que está na tela.
     *
     * @param player Verificações do player com relação aos mergulhadores
     */
    public void atualiza(Jogador player) {
        if (player.isPassouDeFase()) {
            playerPassouDeNivel();
        }

        for (int i = 0; i < mergulhadores.size(); i++) {
            Mergulhador m = mergulhadores.get(i);

            if (m.getX() >= -80 && m.getY() <= 750) {
                if (colisao(player, m)) {
                    player.colisaoComMergulhador();
                    mergulhadores.remove(m);
                    m = null;
                    continue;
                }

                if (!m.isDireita()) {
                    m.setX(m.getX() - m.getVelocidade());
                } else {
                    m.setX(m.getX() + m.getVelocidade());
                }

                m.setContadorDeSprites(m.getContadorDeSprites() + 1);

                if (m.getContadorDeSprites() > 10) {
                    if (m.getNumeroDaSprite() == 1) {
                        m.setNumeroDaSprite(2);
                    } else {
                        m.setNumeroDaSprite(1);
                    }

                    m.setContadorDeSprites(0);
                }
            } else {
                mergulhadores.remove(m);
                m = null;
            }
        }

        contadorParaLiberarMergulhadores++;

        if (contadorParaLiberarMergulhadores >= tempoParaLiberarMergulhador) {
            int valorY = aleatorio.nextInt(180, 420);
            boolean valorDireita = aleatorio.nextBoolean();

            adicionaMergulhador(valorY, valorDireita);

            contadorParaLiberarMergulhadores = 0;
        }
    }

    /**
     * Verifica se o player colidiu com um mergulhador.
     *
     * @param player É o próprio jogador
     * @param mergulhador É o mergulhador que está sendo verificado
     * @return True se ocorrer uma colisao ou False se não ouver colisão
     */
    private boolean colisao(Jogador player, Mergulhador mergulhador) {
        return player.getX() < mergulhador.getX() + mergulhador.getLargura()
                && player.getX() + player.getLargura() > mergulhador.getX()
                && player.getY() < mergulhador.getY() + mergulhador.getAltura()
                && player.getY() + player.getAltura() > mergulhador.getY();
    }

    /**
     * Adiciona um mergulhador ao <b>ArrayList</b> de mergulhadores.
     *
     * @param y Coordenada em y onde o mergulhador deve aparecer
     * @param direita Se a direçãoo que o peixe está indo é direita ou esquerda
     */
    private void adicionaMergulhador(int y, boolean direita) {
        mergulhadores.add(new Mergulhador(y, velocidadeParaCadaNivel, direita));
    }

    /**
     * Desenha os mergulhadores que ainda estão no <b>ArrayList</b> de
     * mergulhadores
     *
     * @param g2 Renderiza os elementos gráficos
     */
    public void desenha(Graphics2D g2) {
        BufferedImage imagem = null;

        for (int i = 0; i < mergulhadores.size(); i++) {
            Mergulhador m = mergulhadores.get(i);

            if (!m.isDireita()) {
                if (m.getNumeroDaSprite() == 1) {
                    imagem = m.getEsquerdaMovendo1();
                } else {
                    imagem = m.getEsquerdaMovendo2();
                }
            } else {
                if (m.getNumeroDaSprite() == 1) {
                    imagem = m.getDireitaMovendo1();
                } else {
                    imagem = m.getDireitaMovendo2();
                }
            }

            g2.drawImage(imagem, m.getX(), m.getY(), getLargura(), getAltura(), null);
        }
    }

    /**
     * Atualiza os valores quando o jogador passar de nível
     */
    private void playerPassouDeNivel() {
        tempoParaLiberarMergulhador -= 20;
        velocidadeParaCadaNivel++;
    }

    /**
     * Retorna a largura da imagem a ser renderizada.
     *
     * @return Valor da largura da imagem.
     */
    @Override
    public int getLargura() {
        return 55;
    }

    /**
     * Retorna a altura da imagem a ser renderizada.
     *
     * @return Valor da altura da imagem.
     */
    @Override
    public int getAltura() {
        return 40;
    }
}
