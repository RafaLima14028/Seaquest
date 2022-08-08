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
 * A classe <b>Peixes</b> define um tipo de dado abstrato para criação da
 * estrutura de classes de um jogo.
 *
 * @author Rafael Alves Alves de Lima
 * @author Gabriel Kato Gomes
 *
 * @since ago 2022.
 *
 * @version 1.0
 */
public class Peixes extends Npc {

    /**
     * O atributo peixes é utilizado para referenciar um ArrayList de
     * <b>Peixes</b>.
     */
    private List<Peixes> peixes;

    /**
     * O atributo contadorParaLiberarPeixes é utilizado para liberar os peixes
     * no momento correto.
     */
    private int contadorParaLiberarPeixes;

    /**
     * O atributo tempoParaLiberarPeixes é utilizado para saber o momento
     * correto para fazer a liberação do peixe.
     */
    private int tempoParaLiberarPeixes;

    /**
     * O atributo velocidaParaCadaNivel é utilizado para saber a velocidade da
     * fase atual.
     */
    private int velocidaParaCadaNivel;

    /**
     * O atributo aleatorio, do tipo <b>Random</b> é utilizado para referenciar
     * um valor aleatorio.
     */
    private Random aleatorio;

    /**
     * Construtor default da classe <b>Peixes</b>.<br><br>
     * <b>Uso:</b><br>
     * Peixes peixes = new Peixes();<br><br>
     */
    public Peixes() {
        contadorParaLiberarPeixes = 0;
        tempoParaLiberarPeixes = 300;
        velocidaParaCadaNivel = 3;

        peixes = new ArrayList<>();
        aleatorio = new Random();
    }

    /**
     * Construtor sobrecarregado da classe <b>Peixes</b>.<br><br>
     * <b>Uso:</b><br>
     * Peixes peixes = new Peixes(250, 4, true);<br><br>
     *
     * @param y Coordenada y onde o peixe deve aparecer
     * @param velocidade Velocidade na qual o peixe se move
     * @param direita Se a direção que o peixe está indo é direita ou esquerda
     */
    public Peixes(int y, int velocidade, boolean direita) {
        super(y, velocidade, direita);
    }

    /**
     * Carrega as imagens referentes ao peixe.
     */
    @Override
    protected void setImagens() {
        try {
            setEsquerdaMovendo1(ImageIO.read(getClass().getResourceAsStream("/res/fishsYellow/peixe_amarelo_esquerda_1.png")));
            setEsquerdaMovendo2(ImageIO.read(getClass().getResourceAsStream("/res/fishsYellow/peixe_amarelo_esquerda_2.png")));
            setDireitaMovendo1(ImageIO.read(getClass().getResourceAsStream("/res/fishsYellow/peixe_amarelo_direita_1.png")));
            setDireitaMovendo2(ImageIO.read(getClass().getResourceAsStream("/res/fishsYellow/peixe_amarelo_direita_2.png")));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro: Imagens do peixe não encontrada", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Atualiza os elementos referentes a classe <b>Peixe</b> e o estado de cada
     * peixe que está na tela.
     *
     * @param player Verificações do player com relação aos peixes
     * @param tirosPlayer Faz algumas verificacações dos tiros do player com
     * relacao aos peixes
     */
    public void atualiza(Jogador player, List<Tiro> tirosPlayer) {
        if (player.isPassouDeFase()) {
            playerPassouDeNivel();
        }

        for (int i = 0; i < peixes.size(); i++) {
            Peixes p = peixes.get(i);

            if (p.getX() >= -80 && p.getY() <= 750) {
                if (colisao(player, p)) {
                    player.colisaoComPeixeOuSubInimigo();
                    peixes.remove(p);
                    p = null;
                    continue;
                }

                if (!p.isDireita()) {
                    p.setX(p.getX() - p.getVelocidade());
                } else {
                    p.setX(p.getX() + p.getVelocidade());
                }

                p.setContadorDeSprites(p.getContadorDeSprites() + 1);

                if (p.getContadorDeSprites() > 10) {
                    if (p.getNumeroDaSprite() == 1) {
                        p.setNumeroDaSprite(2);
                    } else {
                        p.setNumeroDaSprite(1);
                    }

                    p.setContadorDeSprites(0);
                }

                for (int j = 0; j < tirosPlayer.size(); j++) {
                    Tiro t = tirosPlayer.get(j);

                    if (colisao(t, p)) {
                        tirosPlayer.remove(t);
                        peixes.remove(p);
                        t = null;
                        p = null;
                        player.tiroDoJogadorColidiuComPeixe();
                        break;
                    }
                }
            } else {
                peixes.remove(p);
                p = null;
            }
        }

        contadorParaLiberarPeixes++;

        if (contadorParaLiberarPeixes >= tempoParaLiberarPeixes) {
            int qtddDeFishsAltura = aleatorio.nextInt(1, 4);
            int valorY = aleatorio.nextInt(180, 420);
            boolean velorDireita = aleatorio.nextBoolean();

            for (int i = 0; i < qtddDeFishsAltura; i++) {
                adicionaPeixes(valorY, velorDireita);

                valorY += 50;
            }

            contadorParaLiberarPeixes = 0;
        }
    }

    /**
     * Desenha o peixe que ainda estão no ArrayList na tela.
     *
     * @param g2 Faz os desenhos na tela
     */
    public void desenha(Graphics2D g2) {
        BufferedImage imagem = null;

        for (int i = 0; i < peixes.size(); i++) {
            Peixes p = peixes.get(i);

            if (!p.isDireita()) {
                if (p.getNumeroDaSprite() == 1) {
                    imagem = p.getEsquerdaMovendo1();
                } else {
                    imagem = p.getEsquerdaMovendo2();
                }
            } else {
                if (p.getNumeroDaSprite() == 1) {
                    imagem = p.getDireitaMovendo1();
                } else {
                    imagem = p.getDireitaMovendo2();
                }
            }

            g2.drawImage(imagem, p.getX(), p.getY(), getLargura(), getAltura(), null);
        }
    }

    /**
     * Adiciona peixes ao jogo.
     *
     * @param y Coordenada em y onde o peixe deve aparecer
     * @param velocidade Velocidade na qual o peixe se locomove
     * @param direita se a direcao que o peixe está indo é direita ou esquerda
     */
    private void adicionaPeixes(int y, boolean direita) {
        peixes.add(new Peixes(y, velocidaParaCadaNivel, direita));
    }

    /**
     * Verifica se um tiro e um peixe se colidiu.
     *
     * @param tiroDoJogador É um tiro disparado pelo jogador
     * @param peixe É o peixe que está sendo verificado
     * @return True se ocorrer uma colisao ou False se não ouver colisão
     */
    private boolean colisao(Tiro tiroDoJogador, Peixes peixe) {
        return tiroDoJogador.getX() < peixe.getX() + peixe.getLargura()
                && tiroDoJogador.getX() + tiroDoJogador.getLargura() > peixe.getX()
                && tiroDoJogador.getY() < peixe.getY() + peixe.getAltura()
                && tiroDoJogador.getY() + tiroDoJogador.getAltura() > peixe.getY();
    }

    /**
     * Verifica se um tiro e um peixe se colidiu
     *
     * @param player É o próprio jogador
     * @param peixe É o peixe que está sendo verificado
     * @return True se ouver uma colisao ou False se não ouver colisão
     */
    private boolean colisao(Jogador player, Peixes peixe) {
        return player.getX() < peixe.getX() + peixe.getLargura()
                && player.getX() + player.getLargura() > peixe.getX()
                && player.getY() < peixe.getY() + peixe.getAltura()
                && player.getY() + player.getAltura() > peixe.getY();
    }

    /**
     * Atualiza os valores da velocidade e do tempo para liberar os peixes
     */
    private void playerPassouDeNivel() {
        velocidaParaCadaNivel++;
        tempoParaLiberarPeixes -= 15;
    }

    /**
     * Retorna a largura da imagem a ser renderizada.
     *
     * @return Valor da largura da imagem.
     */
    @Override
    public int getLargura() {
        return 48;
    }

    /**
     * Retorna a altura da imagem a ser renderizada.
     *
     * @return Valor da altura da imagem.
     */
    @Override
    public int getAltura() {
        return 48;
    }
}
