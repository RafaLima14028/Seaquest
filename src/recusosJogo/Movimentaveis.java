package recusosJogo;

/**
 *
 * @author Rafael Alves de Lima.
 * @author Gabriel Kato Gomes.
 *
 * @since ago 2022.
 *
 * @version 1.0
 */
public interface Movimentaveis {

    /**
     *
     * @return
     */
    public int getX();

    /**
     *
     * @return
     */
    public int getY();

    /**
     *
     * @return
     */
    public int getVelocidade();

    /**
     *
     * @return
     */
    public boolean isDireita();

    /**
     *
     * @return
     */
    public int getLargura();

    /**
     *
     * @return
     */
    public int getAltura();
}
