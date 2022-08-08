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
 * A classe <b>SubmarinoInimigo</b> define um tipo de dado abstrato para criação
 * da estrutura de classes de um jogo.
 *
 * @author Rafael Alves de Lima.
 * @author Gabriel Kato Gomes.
 *
 * @since ago 2022.
 *
 * @version 1.0
 */
public class SubmarinoInimigo extends Npc {

    /**
     * O atributo contadorParaLiberarSubInimigo é utilizado para liberar os
     * submarinos inimigos no momento correto.
     */
    private int contadorParaLiberarSubInimigo;

    /**
     * O atributo contadorParaLierarTiroDoSubInimigo é utilizado para liberar os
     * tiros do submarinos inimigos no momento correto.
     */
    private int contadorParaLiberarTiroDoSubInimigo;

    /**
     * O atributo tempoParaLiberarSubInimigo é utilizado para liberar os
     * submarinos inimigos no momento correto.
     */
    private int tempoParaLiberarSubInimigo;

    /**
     * O atributo tempoParaLiberarTiroDoSubInimigo é utilizado para liberar os
     * tiros do submarinos inimigos no momento correto.
     */
    private int tempoParaLiberarTiroDoSubInimigo;

    /**
     * O atributo velocidadePorNiveis é utilizado para saber a velocidade da
     * fase atual.
     */
    private int velocidadePorNiveis;

    /**
     * O atributo subInimigo é utilizado para referenciar um ArrayList de
     * <b>SubmarinoInimigo</b>.
     */
    private List<SubmarinoInimigo> subInimigo;

    /**
     * O atributo tiro é utilizado para referenciar o tipo de dado
     * <b>Tiro</b>, com o ArrayList de tiros e seus respectivos métodos.
     */
    private Tiro tiro;

    /**
     * O atributo aleatorio, do tipo <b>Random</b> é utilizado para referenciar
     * um valor aleatorio.
     */
    private Random aleatorio;

    /**
     * Construtor default da classe <b>SubmarinoInimigo</b>.<br><br>
     * <b>Uso:</b><br>
     * SubmarinoInimigo submarinoInimigo = new SubmarinoInimigo();<br><br>
     */
    public SubmarinoInimigo() {
        contadorParaLiberarSubInimigo = 0;
        contadorParaLiberarTiroDoSubInimigo = 0;
        tempoParaLiberarSubInimigo = 500;
        tempoParaLiberarTiroDoSubInimigo = 90;
        velocidadePorNiveis = 3;

        subInimigo = new ArrayList<>();
        tiro = new Tiro();
        aleatorio = new Random();
    }

    /**
     * Construtor sobrecarregado da classe <b>SubmarinoInimigo</b>.<br><br>
     * <b>Uso:</b><br>
     * SubmarinoInimigo submarinoInimigo = new SubmarinoInimigo(250, 4,
     * true);<br><br>
     *
     * @param y Coordenada y onde o peixe deve aparecer
     * @param velocidade Velocidade na qual o peixe se move
     * @param direita Se a direção que o peixe está indo é direita ou esquerda
     */
    public SubmarinoInimigo(int y, int velocidade, boolean direita) {
        super(y, velocidade, direita);
    }

    /**
     * Carrega as imagens referentes ao submarino inimigo.
     */
    @Override
    protected void setImagens() {
        try {
            setEsquerdaMovendo1(ImageIO.read(getClass().getResourceAsStream("/res/subInimigo/submarine_esquerda.png")));
            setEsquerdaMovendo2(ImageIO.read(getClass().getResourceAsStream("/res/subInimigo/submarine_esquerda.png")));
            setDireitaMovendo1(ImageIO.read(getClass().getResourceAsStream("/res/subInimigo/submarine_direita.png")));
            setDireitaMovendo2(ImageIO.read(getClass().getResourceAsStream("/res/subInimigo/submarine_direita.png")));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro: Imagens dos submarino inimigo não encontradas",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Atualiza os elementos referentes a classe <b>SubmarinoInimigo</b> e o
     * estado de cada submarino inimigo que está na tela.
     *
     * @param jogador Verificações do jogador com relação aos submarinos
     * inimigos.
     * @param tirosJogador Faz algumas verificacações dos tiros do jogador com
     * relacao aos submarinos inimigos.
     */
    public void atualiza(Jogador jogador, List<Tiro> tirosJogador) {
        if (jogador.isPassouDeFase()) {
            playerPassouDeNivel();
        }

        for (int i = 0; i < subInimigo.size(); i++) {
            SubmarinoInimigo si = subInimigo.get(i);

            if (si.getX() >= -80 && si.getY() <= 750) {
                // Jogador colidiu com o submarino inimigo
                if (colisao(jogador, si)) {
                    jogador.colisaoComPeixeOuSubInimigo();
                    subInimigo.remove(si);
                    si = null;
                    continue;
                }

                if (!si.isDireita()) {
                    si.setX(si.getX() - si.getVelocidade());
                } else {
                    si.setX(si.getX() + si.getVelocidade());
                }

                si.contadorParaLiberarTiroDoSubInimigo++;

                if (si.contadorParaLiberarTiroDoSubInimigo >= tempoParaLiberarTiroDoSubInimigo) {
                    if (si.isDireita()) {
                        tiro.adicionaTiro(si.getX() + 20, si.getY() + 7, si.isDireita(), velocidadePorNiveis);
                    } else {
                        tiro.adicionaTiro(si.getX() - 20, si.getY() + 7, si.isDireita(), velocidadePorNiveis);
                    }

                    si.contadorParaLiberarTiroDoSubInimigo = 0;
                }

                si.setContadorDeSprites(si.getContadorDeSprites() + 1);

                if (si.getContadorDeSprites() > 10) {
                    if (si.getNumeroDaSprite() == 1) {
                        si.setContadorDeSprites(2);
                    } else {
                        si.setContadorDeSprites(1);
                    }

                    si.setContadorDeSprites(0);
                }

                // Tiro do submarino inimigo acertou o jogador
                for (int j = 0; j < this.tiro.getTamanho(); j++) {
                    Tiro t = this.tiro.getTirosIndex(j);

                    if (colisao(jogador, t)) {
                        this.tiro.removeObjeto(t);
                        jogador.colisaoComPeixeOuSubInimigo();
                        t = null;
                        break;
                    }
                }

                // Tiro do player acertou o subInimigo
                for (int j = 0; j < tirosJogador.size(); j++) {
                    Tiro t = tirosJogador.get(j);

                    if (colisao(t, si)) {
                        tirosJogador.remove(t);
                        subInimigo.remove(si);
                        t = null;
                        si = null;
                        jogador.tiroDoJogadorColidiuComPeixe();
                        break;
                    }
                }

                this.tiro.atualiza();
            } else {
                subInimigo.remove(si);
                si = null;
            }
        }

        contadorParaLiberarSubInimigo++;

        if (contadorParaLiberarSubInimigo >= tempoParaLiberarSubInimigo) {
            int qttdDeSubInimigoAltura = aleatorio.nextInt(1, 4);
            int valorY = aleatorio.nextInt(180, 420);
            boolean valorIsDireita = aleatorio.nextBoolean();

            for (int i = 0; i < qttdDeSubInimigoAltura; i++) {
                adicionaSubmarinoInimigo(valorY, valorIsDireita);
                valorY += 90;
            }

            contadorParaLiberarSubInimigo = 0;
        }
    }

    /**
     * Verifica se um tiro do submarino inimigo e o jogador colidiram.
     *
     * @param jogador É o próprio jogador.
     * @param tiroInimigo É o peixe que está sendo verificado.
     * @return True se ocorrer uma colisao ou False se não ouver colisão.
     */
    private boolean colisao(Jogador jogador, Tiro tiroInimigo) {
        return jogador.getX() < tiroInimigo.getX() + tiroInimigo.getLargura()
                && jogador.getX() + jogador.getLargura() > tiroInimigo.getX()
                && jogador.getY() < tiroInimigo.getY() + tiroInimigo.getAltura()
                && jogador.getY() + jogador.getAltura() > tiroInimigo.getY();
    }

    /**
     * Verifica se um tiro do jogador e um submarino inimigo colidiram.
     *
     * @param tiroDoJogador É um tiro disparado pelo jogador
     * @param subInimigo É um submarino inimigo que está sendo verificado.
     * @return True se ocorrer uma colisao ou False se não ouver colisão.
     */
    private boolean colisao(Tiro tiroDoJogador, SubmarinoInimigo subInimigo) {
        return tiroDoJogador.getX() < subInimigo.getX() + subInimigo.getLargura()
                && tiroDoJogador.getX() + tiroDoJogador.getLargura() > subInimigo.getX()
                && tiroDoJogador.getY() < subInimigo.getY() + subInimigo.getAltura()
                && tiroDoJogador.getY() + tiroDoJogador.getAltura() > subInimigo.getY();
    }

    /**
     * Verifica se o jogador e um submarino inimigo colidiram.
     *
     * @param jogador É o próprio jogador.
     * @param subInimigo É um submarino inimigo que está sendo verificado.
     * @return True se ocorrer uma colisao ou False se não ouver colisão.
     */
    private boolean colisao(Jogador jogador, SubmarinoInimigo subInimigo) {
        return jogador.getX() < subInimigo.getX() + subInimigo.getLargura()
                && jogador.getX() + jogador.getLargura() > subInimigo.getX()
                && jogador.getY() < subInimigo.getY() + subInimigo.getAltura()
                && jogador.getY() + jogador.getAltura() > subInimigo.getY();
    }

    /**
     *
     * @param y
     * @param direita
     */
    private void adicionaSubmarinoInimigo(int y, boolean direita) {
        subInimigo.add(new SubmarinoInimigo(y, velocidadePorNiveis, direita));
    }

    /**
     * Renderiza os submarinos inimigos ainda vivos.
     *
     * @param g2 Renderiza os elementos na tela.
     */
    public void desenha(Graphics2D g2) {
        BufferedImage imagem = null;

        for (int i = 0; i < subInimigo.size(); i++) {
            SubmarinoInimigo si = subInimigo.get(i);

            if (!si.isDireita()) {
                if (si.getNumeroDaSprite() == 1) {
                    imagem = si.getEsquerdaMovendo1();
                } else {
                    imagem = si.getEsquerdaMovendo2();
                }
            } else {
                if (si.getNumeroDaSprite() == 1) {
                    imagem = si.getDireitaMovendo1();
                } else {
                    imagem = si.getDireitaMovendo2();
                }
            }

            g2.drawImage(imagem, si.getX(), si.getY(), getLargura(), getAltura(), null);

            this.tiro.desenha(g2);
        }
    }

    /**
     * Atualiza os valores da velocidade e do tempo para liberar os submarino
     * inimigo e seu tiro.
     */
    private void playerPassouDeNivel() {
        velocidadePorNiveis++;
        tempoParaLiberarSubInimigo -= 10;
        tempoParaLiberarTiroDoSubInimigo -= 5;
    }

    /**
     * Retorna a largura da imagem a ser renderizada.
     *
     * @return Valor da largura da imagem.
     */
    @Override
    public int getLargura() {
        return 50;
    }

    /**
     * Retorna a altura da imagem a ser renderizada.
     *
     * @return Valor da altura da imagem.
     */
    @Override
    public int getAltura() {
        return 17;
    }
}
