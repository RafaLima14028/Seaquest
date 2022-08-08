package recursosScore;

import java.io.Serializable;

/**
 * A classe <b>Pessoa</b> define um tipo de dado abstrato para criação da
 * estrutura de classes de um jogo.
 *
 * @author Rafael Alves de Lima
 * @author Gabriel Kato Gomes
 *
 * @since ago 2022
 *
 * @version 1.0
 */
public class Pessoa implements Serializable {

    /**
     * O atributo nome é utilizado para referenciar o nome de
     * <b>Pessoa</b>.
     */
    private String nome;

    /**
     * O atributo pontuacao é utilizado para referenciar a pontuacao de
     * <b>Pessoa</b>.
     */
    private int pontuacao;

    /**
     * Construtor default da classe <b>Pessoa</b>.<br><br>
     * <b>Uso:</b><br>
     * Pessoa pessoa = new Pessoa("Joaozinho", 500);<br><br>
     *
     * @param nome Nome do novo usuario
     * @param pontuacao Pontuacao do novo usuario
     */
    public Pessoa(String nome, int pontuacao) {
        this.nome = nome;
        this.pontuacao = pontuacao;
    }

    /**
     * Retorna o nome do usuario
     *
     * @return O nome do usuario
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a pontuacao do usuario
     *
     * @return A pontuacao do usuario
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * Sobrescreve o método toString para testes
     *
     * @return o nome e a pontuacao do usuario
     */
    @Override
    public String toString() {
        return "Nome: " + nome + " - " + "Pontuacao: " + pontuacao + "\n";
    }
}
