package recusosJogo;

import java.awt.image.BufferedImage;

/**
 * A classe abstrata <b>Npc</b> define um tipo de dado abstrato para criação da
 * estrutura de classes de um jogo.
 *
 * @author Rafael Alves de Lima.
 * @author Gabriel Kato Gomes.
 *
 * @since ago 2022.
 *
 * @version 1.0
 */
public abstract class Npc implements Movimentaveis {

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
     * O atributo velocidade é utilizado para referenciar a velocidade na qual o
     * objeto se movimenta.
     */
    private int velocidade;

    /**
     * O atributo contadorDeSprites é utilizado para referenciar as trocas de
     * sprites da imagem do objeto.
     */
    private int contadorDeSprites;

    /**
     * O atributo numeroDaSprite é utilizado para referenciar a sprite atual do
     * objeto.
     */
    private int numeroDaSprite;

    /**
     * O atributo esquerdaMovendo1 é utilizado para guardar a imagem do objeto
     * se movendo para a esquerda, sendo a primeira sprite a esquerda.
     */
    private BufferedImage esquerdaMovendo1;

    /**
     * O atributo esquerdaMovendo2 é utilizado para guardar a imagem do objeto
     * se movendo para a esquerda, sendo a segunda sprite a esquerda.
     */
    private BufferedImage esquerdaMovendo2;

    /**
     * O atributo direitaMovendo1 é utilizado para guardar a imagem do objeto se
     * movendo para a direita, sendo a primeira sprite a direita.
     */
    private BufferedImage direitaMovendo1;

    /**
     * O atributo direitaMovendo2 é utilizado para guardar a imagem do objeto se
     * movendo para a direita, sendo a segunda sprite a direita.
     */
    private BufferedImage direitaMovendo2;

    /**
     * O atributo direita é utilizado para referenciar a direção na qual o
     * objeto se movimenta.
     */
    private boolean direita;

    /**
     * Construtor default da classe <b>Npc</b>.<br><br>
     * <b>Uso:</b><br>
     * extends Npc<br><br>
     */
    public Npc() {
    }

    /**
     * Construtor sobrecarregado da classe <b>Peixes</b>.<br><br>
     * <b>Uso:</b><br>
     * Npc npc = new Npc(250, 4, true);<br><br>
     *
     * @param y Coordenada y onde o npc deve aparecer
     * @param velocidade Velocidade na qual o npc se move
     * @param direita Se a direção que o npc está indo é direita ou esquerda
     */
    public Npc(int y, int velocidade, boolean direita) {
        this.direita = direita;

        if (y >= 190 && y <= 420) {
            this.y = y;
        } else {
            this.y = 180;
        }

        if (direita) {
            this.x = -80;
        } else {
            this.x = 750;
        }

        if (velocidade > 0) {
            this.velocidade = velocidade;
        } else {
            this.velocidade = 4;
        }

        contadorDeSprites = 0;
        numeroDaSprite = 1;

        setImagens();
    }

    /**
     * Carrega as imagens referentes ao npc.
     */
    protected abstract void setImagens();

    /**
     * Retorna a largura da imagem a ser renderizada.
     *
     * @return Valor da largura da imagem.
     */
    @Override
    public abstract int getLargura();

    /**
     * Retorna a altura da imagem a ser renderizada.
     *
     * @return Valor da altura da imagem.
     */
    @Override
    public abstract int getAltura();

    /**
     * Retorna a coordena em x.
     *
     * @return Coordenada em x.
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Retorna a coordena em y.
     *
     * @return Coordenada em y.
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * Retorna a velocidade.
     *
     * @return Retorna a velocidade.
     */
    @Override
    public int getVelocidade() {
        return velocidade;
    }

    /**
     * Retorna o contador atual de sprites.
     *
     * @return Retorna o valor do contador de sprites.
     */
    public int getContadorDeSprites() {
        return contadorDeSprites;
    }

    /**
     * Retorna o número atual da sprite.
     *
     * @return Retorna o valor de numero da sprite.
     */
    public int getNumeroDaSprite() {
        return numeroDaSprite;
    }

    /**
     * Retorna a imagem se movendo para esquerda sendo a primeira.
     *
     * @return Retorna a imagem se movendo para a esquerda.
     */
    public BufferedImage getEsquerdaMovendo1() {
        return esquerdaMovendo1;
    }

    /**
     * Retorna a imagem se movendo para esquerda sendo a segunda.
     *
     * @return Retorna a imagem se movendo para a esquerda.
     */
    public BufferedImage getEsquerdaMovendo2() {
        return esquerdaMovendo2;
    }

    /**
     * Retorna a imagem se movendo para direita sendo a primeira.
     *
     * @return Retorna a imagem se movendo para a direita.
     */
    public BufferedImage getDireitaMovendo1() {
        return direitaMovendo1;
    }

    /**
     * Retorna a imagem se movendo para direita sendo a segunda.
     *
     * @return Retorna a imagem se movendo para a direita.
     */
    public BufferedImage getDireitaMovendo2() {
        return direitaMovendo2;
    }

    /**
     * Retorna qual a direção de movimento.
     *
     * @return Direção do movimento.
     */
    @Override
    public boolean isDireita() {
        return direita;
    }

    /**
     * Adiciona um novo valor a coordenada x.
     *
     * @param x Valor para ser adicionado a coordenada x.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Adiciona um novo valor a coordenada y.
     *
     * @param y Valor para ser adicionado a coordenada y.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Adiciona um novo valor a velocidade.
     *
     * @param velocidade Valor para ser adicionado a velocidade.
     */
    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    /**
     * Adiciona um novo valor ao contador de sprites.
     *
     * @param contadorDeSprites Valor para ser adicionado ao contador de
     * sprites.
     */
    public void setContadorDeSprites(int contadorDeSprites) {
        this.contadorDeSprites = contadorDeSprites;
    }

    /**
     * Adiciona um novo valor ao número atual de sprites.
     *
     * @param numeroDaSprite Valor para ser adicionado ao numero atual da
     * sprite.
     */
    public void setNumeroDaSprite(int numeroDaSprite) {
        this.numeroDaSprite = numeroDaSprite;
    }

    /**
     * Adiciona um novo valor a imagem se movendo a esquerda, referente a
     * primeira sprite.
     *
     * @param esquerdaMovendo1 Pasta onde se encontra a nova imagem.
     */
    public void setEsquerdaMovendo1(BufferedImage esquerdaMovendo1) {
        this.esquerdaMovendo1 = esquerdaMovendo1;
    }

    /**
     * Adiciona um novo valor a imagem se movendo a esquerda, referente a
     * segunda sprite.
     *
     * @param esquerdaMovendo2 Pasta onde se encontra a nova imagem.
     */
    public void setEsquerdaMovendo2(BufferedImage esquerdaMovendo2) {
        this.esquerdaMovendo2 = esquerdaMovendo2;
    }

    /**
     * Adiciona um novo valor a imagem se movendo a direita, referente a
     * primeira sprite.
     *
     * @param direitaMovendo1 Pasta onde se encontra a nova imagem.
     */
    public void setDireitaMovendo1(BufferedImage direitaMovendo1) {
        this.direitaMovendo1 = direitaMovendo1;
    }

    /**
     * Adiciona um novo valor a imagem se movendo a esquerda, referente a
     * segunda sprite.
     *
     * @param direitaMovendo2 Pasta onde se encontra a nova imagem.
     */
    public void setDireitaMovendo2(BufferedImage direitaMovendo2) {
        this.direitaMovendo2 = direitaMovendo2;
    }

    /**
     * Adiciona um novo valor a direção.
     *
     * @param direita Valor para ser adicionado a direção.
     */
    public void setDireita(boolean direita) {
        this.direita = direita;
    }
}
