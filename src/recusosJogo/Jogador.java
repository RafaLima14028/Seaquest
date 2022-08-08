package recusosJogo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * A classe <b>Jogador</b> define um tipo de dado abstrato para criação da
 * estrutura de classes de um jogo.
 *
 * @author Rafael Alves de Lima.
 * @author Gabriel Kato Gomes.
 *
 * @since ago 2022.
 *
 * @version 1.0
 */
public class Jogador implements Movimentaveis {

    /**
     * O atributo oxigenioFont é utilizado para referenciar o tipo
     * <b>Font</b>, sendo a fonte usada na palavra oxigênio.
     */
    private final Font oxigenioFont = new Font("Arial", Font.BOLD, 20);

    /**
     * O atributo pontuacaoFont é utilizado para referenciar o tipo
     * <b>Font</b>, sendo a fonte usada na pontuação.
     */
    private final Font pontuacaoFont = new Font("Arial", Font.BOLD, 50);

    /**
     * O atributo corBarraDoOxienio é utilizado para referenciar o tipo
     * <b>Color</b>, sendo a cor usada na barra de oxigênio.
     */
    private final Color corBarraDoOxienio = new Color(185, 189, 184);

    /**
     * O atributo pontuacaoColor é utilizado para referenciar o tipo
     * <b>Color</b>, sendo a cor usada na pontuação.
     */
    private final Color pontuacaoColor = new Color(230, 232, 55);

    /**
     * O atributo deslocamentoEmY é utilizado para fazer o deslocamento na
     * coordenada y.
     */
    private final int deslocamentoEmY = 70;

    /**
     * O atributo tempoParaTiro é utilizado para que não ocorra tiros infinitos.
     */
    private final int tempoParaTiro = 15;

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
     * jogador se movimenta velocidade.
     */
    private int velocidade;

    /**
     * O atributo vidas é utilizado para referenciar a quantidade de vidas do
     * jogador vidas.
     */
    private int vidas;

    /**
     * O atributo pontuacao é utilizado para referenciar a quantidade de pontos
     * do jogador pontuação.
     */
    private int pontuacao;

    /**
     * O atributo numeroDeMergulhadores é utilizado para referenciar o número de
     * mergulhadores.
     */
    private int numeroDeMergulhadores;

    /**
     * O atributo contadorDeSprites é utilizado para referenciar as trocas de
     * sprites da imagem do jogador.
     */
    private int contadorDeSprites;

    /**
     * O atributo numeroDaSprite é utilizado para referenciar a sprite atual do
     * objeto.
     */
    private int numeroDaSprite;

    /**
     * O atributo contTempoTiroValido é utilizado para referenciar se o momento
     * do tiro é válido, para que os tiros não seja infinito.
     */
    private int contTempoTiroValido;

    /**
     * O atributo numeroDeEmergir é utilizado para referenciar o número de vezes
     * que o jogador pode emergir.
     */
    private int numeroDeEmergir;

    /**
     * O atributo oxigenio é utilizado para referenciar a quantidade de oxigênio
     * que o jogador ainda tem.
     */
    private float oxigenio;

    /**
     * O atributo direita é utilizado para referenciar a direção que o usuário
     * está seguindo.
     */
    private boolean direita;

    /**
     * O atributo vivo é utilizado para referenciar se o jogador ainda está
     * vivo.
     */
    private boolean vivo;

    /**
     * O atributo vivo é utilizado para referenciar se o jogador já mergulhou.
     */
    private boolean mergulhou;

    /**
     * O atributo vivo é utilizado para referenciar se o jogador já conseguiu
     * coletar 7(sete) mergulhadores.
     */
    private boolean pegou7Mergulhadores;

    /**
     * O atributo esquerdaMovendo1 é utilizado para guardar a imagem do jogador
     * se movendo para a esquerda, sendo a primeira sprite a esquerda.
     */
    private BufferedImage esquerdaMovendo1;

    /**
     * O atributo esquerdaMovendo1 é utilizado para guardar a imagem do jogador
     * se movendo para a esquerda, sendo a segunda sprite a esquerda.
     */
    private BufferedImage esquerdaMovendo2;

    /**
     * O atributo esquerdaParado é utilizado para guardar a imagem do jogador
     * estando parado.
     */
    private BufferedImage esquerdaParado;

    /**
     * O atributo direitaMovendo1 é utilizado para guardar a imagem do jogador
     * se movendo para a direita, sendo a primeira sprite a direita.
     */
    private BufferedImage direitaMovendo1;

    /**
     * O atributo direitaMovendo2 é utilizado para guardar a imagem do jogador
     * se movendo para a segunda, sendo a segunda sprite a direita.
     */
    private BufferedImage direitaMovendo2;

    /**
     * O atributo direitaParado é utilizado para guardar a imagem do jogador
     * estando parado.
     */
    private BufferedImage direitaParado;

    /**
     * O atributo teclado é utilizado para referenciar o tipo de dado
     * <b>Teclado</b>, com as ações possíveis do jogador.
     */
    private Teclado teclado;

    /**
     * O atributo tiro é utilizado para referenciar o tipo de dado
     * <b>Tiro</b>, com o ArrayList de tiros e seus respectivos métodos.
     */
    private Tiro tiro;

    /**
     * O atributo vidasImg é utilizado para referenciar o tipo de dado
     * <b>VidasImg</b>, com o ArrayList com as imagens e seus respectivos
     * métodos.
     */
    private VidasImg vidasImg;

    /**
     * O atributo vidasImg é utilizado para referenciar o tipo de dado
     * <b>VidasImg</b>, com o ArrayList com as imagens e seus respectivos
     * métodos.
     */
    private MergulhadoresImg mergulhadoresImg;

    /**
     * Construtor default da classe <b>Player</b>.<br><br>
     * <b>Uso:</b><br>
     * Jogador jogador = new Jogador(teclado);<br><br>
     *
     * @param teclado São as possíveis ações do jogador
     */
    public Jogador(Teclado teclado) {
        this.teclado = teclado;

        setValoresDeInicio();
        setImagens();
    }

    /**
     * Instancia as váriaveis da classe <b>Player</b> e suas váriaveis.
     */
    private void setValoresDeInicio() {
        x = 320;
        y = 60 + getDeslocamentoEmY();
        numeroDeMergulhadores = 0;
        numeroDeEmergir = 0;
        contadorDeSprites = 0;
        contTempoTiroValido = 0;
        pontuacao = 0;
        numeroDaSprite = 1;
        velocidade = 4;
        vidas = 7;
        oxigenio = 100;
        direita = vivo = true;
        pegou7Mergulhadores = false;

        tiro = new Tiro();
        vidasImg = new VidasImg();
        mergulhadoresImg = new MergulhadoresImg();
    }

    /**
     * Carrega as imagens referentes ao jogador.
     */
    private void setImagens() {
        try {
            esquerdaMovendo1 = ImageIO.read(getClass().getResourceAsStream("/res/player/submarine_esquerda_movendo_1.png"));
            esquerdaMovendo2 = ImageIO.read(getClass().getResourceAsStream("/res/player/submarine_esquerda_movendo_2.png"));
            esquerdaParado = ImageIO.read(getClass().getResource("/res/player/submarine_esquerda_parado.png"));
            direitaMovendo1 = ImageIO.read(getClass().getResourceAsStream("/res/player/submarine_direita_movendo_1.png"));
            direitaMovendo2 = ImageIO.read(getClass().getResourceAsStream("/res/player/submarine_direita_movendo_2.png"));
            direitaParado = ImageIO.read(getClass().getResourceAsStream("/res/player/submarine_direita_parado.png"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro: Imagens do jogador nao encontradas", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Trata a colisão entre o jogador e um mergulhador.
     */
    public void colisaoComMergulhador() {
        if (!isPegou7Mergulhadores()) {
            numeroDeMergulhadores++;
        }
        if (getNumeroDeMergulhadores() >= 7) {
            pegou7Mergulhadores = true;
        }
    }

    /**
     * Trata a colisão entre o jogador e um peixe ou submarino inimigo.
     */
    public void colisaoComPeixeOuSubInimigo() {
        if (getNumeroDeMergulhadores() > 0) {
            numeroDeMergulhadores--;
        }
        if (getVidas() > 0) {
            vidas--;
        }
        if (getVidas() <= 0) {
            vivo = false;
        }

        x = 320;
        y = 60 + getDeslocamentoEmY();
        mergulhou = false;
    }

    /**
     * Trata o jogador oxigênio e a regeneração do mesmo.
     */
    private void cuidaOxigenio() {
        if (getY() <= 60 + getDeslocamentoEmY()) {
            if (isPegou7Mergulhadores()) {
                passouDeFase();
            } else if (isMergulhou()) {
                if (getNumeroDeMergulhadores() > 0) {
                    numeroDeMergulhadores--;
                } else if (getVidas() >= 0) {
                    vidas--;
                    mergulhou = false;
                }
            }
            if (getVidas() <= 0) {
                vivo = false;
            }

            oxigenio = 100;
            mergulhou = false;
        } else {
            mergulhou = true;

            if (getOxigenio() > 0) {
                oxigenio -= 0.04;
            } else if (getOxigenio() <= 0 && isMergulhou()) {
                if (getVidas() > 0) {
                    vidas--;
                }
                if (getNumeroDeMergulhadores() > 0) {
                    numeroDeMergulhadores = 0;
                }

                oxigenio = 100;
            }
            if (getVidas() <= 0) {
                vivo = false;
            }
        }
    }

    /**
     * Atualiza os elementos referentes a classe <b>Jogador</b> e o estado do
     * mesmo.
     */
    public void atualiza() {
        if (isVivo()) {
            if (isPassouDeFase()) {
                passouDeFase();
            }

            if (getTeclado().isPrecionaCima() && getY() > 60 + getDeslocamentoEmY()) {
                y -= getVelocidade();
            } else if (getTeclado().isPrecionaBaixo() && getY() <= 352 + getDeslocamentoEmY()) {
                y += getVelocidade();
            }
            if (getTeclado().isPrecionaEsquerda() && getX() > 0) {
                direita = false;
                x -= getVelocidade();
            } else if (getTeclado().isPrecionaDireita() && getX() < 620) {
                direita = true;
                x += getVelocidade();
            }
            if (getContTempoTiroValido() >= getTempoParaTiro() && getTeclado().isPrecionaAtirar()) {
                if (isDireita()) {
                    getTiro().adicionaTiro(getX() + 30, getY() + 30, isDireita());
                } else {
                    getTiro().adicionaTiro(getX() - 30, getY() + 30, isDireita());
                }

                contTempoTiroValido = 0;
            }
            if (getNumeroDeEmergir() > 0 && getTeclado().isPrecionaEmergir()) {
                y = 60 + getDeslocamentoEmY();
                numeroDeEmergir--;
            }

            contTempoTiroValido++;
            contadorDeSprites++;

            if (getContadorDeSprites() > 10) {
                if (getNumeroDaSprite() == 1) {
                    numeroDaSprite = 2;
                } else {
                    numeroDaSprite = 1;
                }

                contadorDeSprites = 0;
            }

            cuidaOxigenio();
            getTiro().atualiza();
            getVidasImg().atualiza(getVidas());
            getMergulhadoresImg().atualiza(getNumeroDeMergulhadores());
        } else {
            vivo = false;
        }
    }

    /**
     * Renderiza o jogador e seus tiros.
     *
     * @param g2 Renderiza os elementos gráficos
     */
    public void desenha(Graphics2D g2) {
        BufferedImage imagem = null;

        if (!isDireita()) {
            if (getTeclado().movendo()) {
                if (getNumeroDaSprite() == 1) {
                    imagem = getEsquerdaMovendo1();
                } else {
                    imagem = getEsquerdaMovendo2();
                }
            } else {
                imagem = getEsquerdaParado();
            }
        } else {
            if (getTeclado().movendo()) {
                if (getNumeroDaSprite() == 1) {
                    imagem = getDireitaMovendo1();
                } else {
                    imagem = getDireitaMovendo2();
                }
            } else {
                imagem = getDireitaParado();
            }
        }

        g2.drawImage(imagem, this.getX(), this.getY(), this.getLargura(), this.getAltura(), null);

        {
            // Texto do oxigenio
            g2.setFont(getOxigenioFont());
            g2.setColor(Color.BLACK);
            g2.drawString("OXIGENIO", 130, 502);

            // Barra de oxigenio
            g2.setColor(getCorBarraDoOxienio());
            g2.fill3DRect(250, 480, (int) getOxigenio() * 2, 30, true);
        }
        {
            // Controla pontuacao
            g2.setFont(getPontuacaoFont());
            g2.setColor(getPontuacaoColor());
            g2.drawString(String.valueOf(getPontuacao()), 290, 70);
        }
        {
            // Controla a qtdd de emergir do usuario

            g2.setFont(getOxigenioFont());
            g2.setColor(getPontuacaoColor());
            g2.drawString("Emergir: ", 590, 30);
            g2.drawString(String.valueOf(getNumeroDeEmergir()), 625, 55);
        }

        getTiro().desenha(g2);
        getVidasImg().desenha(g2);
        getMergulhadoresImg().desenha(g2);
    }

    /**
     * Retorna a pontuação atual do jogador.
     *
     * @return Pontuação do jogador atualmente.
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * Retona a altura para renderização da imagem.
     *
     * @return Altura da imagem do jogador.
     */
    @Override
    public int getAltura() {
        return 48;
    }

    /**
     * Retona a largura para renderização da imagem.
     *
     * @return Largura da imagem do jogador.
     */
    @Override
    public int getLargura() {
        if (getTeclado().movendo()) {
            return 48 + 20;
        } else {
            return 48;
        }
    }

    /**
     * Retorna o ArrayList de tiros do jogador.
     *
     * @return ArrayList de tiros do jogador.
     */
    public List<Tiro> getTirosDoJogador() {
        return getTiro().getTiros();
    }

    /**
     * Adiciona pontuação ao jogador quando houver colisão entre o jogador e o
     * peixe.
     */
    public void tiroDoJogadorColidiuComPeixe() {
        pontuacao += 100;
    }

    /**
     * Retorna o número de mergulhadores coletados pelo jogador.
     *
     * @return Número de mergulhadores coletados.
     */
    public int getNumeroDeMergulhadores() {
        return numeroDeMergulhadores;
    }

    /**
     * Retorna se o jogador ainda está vivo ou não.
     *
     * @return True se estiver vivo ou False caso estiver morto.
     */
    public boolean isVivo() {
        return vivo;
    }

    /**
     * Retorna a coordenada x do jogador.
     *
     * @return Coordenada x.
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Retorna a coordenada y do jogador.
     *
     * @return Coordenada y.
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * Retorna se o jogador passou de fase ou não.
     *
     * @return True se tiver passado ou False caso ainda não.
     */
    public boolean isPassouDeFase() {
        return isVivo() && getY() <= 60 + getDeslocamentoEmY() && isPegou7Mergulhadores();
    }

    /**
     * Se o jogador passar de nível, é atualizado os atributos do jogador.
     */
    private void passouDeFase() {
        pontuacao += 1000;
        vidas++;
        pegou7Mergulhadores = false;
        numeroDeMergulhadores = 0;

        if (getNumeroDeEmergir() <= 3) {
            numeroDeEmergir++;
        }

        if (getOxigenio() > 0) {
            for (int i = 0; i <= (int) getOxigenio(); i++) {
                pontuacao += 10;
                oxigenio--;
            }
        }

        oxigenio = 100;
    }

    /**
     *
     * @return
     */
    @Override
    public int getVelocidade() {
        return velocidade;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isDireita() {
        return direita;
    }

    /**
     * @return the oxigenioFont
     */
    public Font getOxigenioFont() {
        return oxigenioFont;
    }

    /**
     * @return the pontuacaoFont
     */
    public Font getPontuacaoFont() {
        return pontuacaoFont;
    }

    /**
     * @return the corBarraDoOxienio
     */
    public Color getCorBarraDoOxienio() {
        return corBarraDoOxienio;
    }

    /**
     * @return the pontuacaoColor
     */
    public Color getPontuacaoColor() {
        return pontuacaoColor;
    }

    /**
     * @return the deslocamentoEmY
     */
    public int getDeslocamentoEmY() {
        return deslocamentoEmY;
    }

    /**
     * @return the tempoParaTiro
     */
    public int getTempoParaTiro() {
        return tempoParaTiro;
    }

    /**
     * @return the vidas
     */
    public int getVidas() {
        return vidas;
    }

    /**
     * @return the contadorDeSprites
     */
    public int getContadorDeSprites() {
        return contadorDeSprites;
    }

    /**
     * @return the numeroDaSprite
     */
    public int getNumeroDaSprite() {
        return numeroDaSprite;
    }

    /**
     * @return the contTempoTiroValido
     */
    public int getContTempoTiroValido() {
        return contTempoTiroValido;
    }

    /**
     * @return the numeroDeEmergir
     */
    public int getNumeroDeEmergir() {
        return numeroDeEmergir;
    }

    /**
     * @return the oxigenio
     */
    public float getOxigenio() {
        return oxigenio;
    }

    /**
     * @return the mergulhou
     */
    public boolean isMergulhou() {
        return mergulhou;
    }

    /**
     * @return the pegou7Mergulhadores
     */
    public boolean isPegou7Mergulhadores() {
        return pegou7Mergulhadores;
    }

    /**
     * @return the esquerdaMovendo1
     */
    public BufferedImage getEsquerdaMovendo1() {
        return esquerdaMovendo1;
    }

    /**
     * @return the esquerdaMovendo2
     */
    public BufferedImage getEsquerdaMovendo2() {
        return esquerdaMovendo2;
    }

    /**
     * @return the esquerdaParado
     */
    public BufferedImage getEsquerdaParado() {
        return esquerdaParado;
    }

    /**
     * @return the direitaMovendo1
     */
    public BufferedImage getDireitaMovendo1() {
        return direitaMovendo1;
    }

    /**
     * @return the direitaMovendo2
     */
    public BufferedImage getDireitaMovendo2() {
        return direitaMovendo2;
    }

    /**
     * @return the direitaParado
     */
    public BufferedImage getDireitaParado() {
        return direitaParado;
    }

    /**
     * @return the teclado
     */
    public Teclado getTeclado() {
        return teclado;
    }

    /**
     * @return the tiro
     */
    public Tiro getTiro() {
        return tiro;
    }

    /**
     * @return the vidasImg
     */
    public VidasImg getVidasImg() {
        return vidasImg;
    }

    /**
     * @return the mergulhadoresImg
     */
    public MergulhadoresImg getMergulhadoresImg() {
        return mergulhadoresImg;
    }
}
