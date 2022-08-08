package seaquest;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A classe <b>ComoJogar</b> define um tipo de dado abstrato para criação da
 * estrutura de classes de um jogo.
 *
 * @author Rafael Alves de Lima.
 * @author Gabriel Kato Gomes.
 *
 * @since ago 2022.
 *
 * @version 1.0
 */
public class ComoJogar extends JFrame {

    /**
     * O atributo movimentacaoString é utilizado para referenciar como o jogador
     * pode se mover no jogo.
     */
    private final String movimentacaoString = "<html>Movimentação: Utilize as setas </br>"
            + " do teclado para se mover, o espaço é utilizado para atirar e a tecla 'E', "
            + "serve para emergir instantaneamente.";

    /**
     * O atributo objetivoString é utilizado para referenciar qual o objetivo do
     * jogador.
     */
    private final String objetivoString = "<html>Objetivo: Não toque nos peixes ou submarinos "
            + "inimigos e nem sege atingido pelos tiros inimigos, "
            + "mas não se esqueça de coletar os mergulhadores.";

    /**
     * Construtor default da classe <b>ComoJogar</b>.<br><br>
     * <b>Uso:</b><br>
     * ComoJogar comoJogar = new ComoJogar();<br><br>
     */
    public ComoJogar() {
        iniciaJanela();
        instrucoes();
    }

    /**
     * Exibe as instruções de como o jogador deve prosseguir no jogo.
     */
    private void instrucoes() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel movimentacaoLabel = new JLabel();
        movimentacaoLabel.setText(movimentacaoString);
        movimentacaoLabel.setBounds(20, 10, 230, 80);

        JLabel objetivoLabel = new JLabel();
        objetivoLabel.setText(objetivoString);
        objetivoLabel.setBounds(20, 90, 230, 80);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(90, 190, 100, 40);
        voltarButton.addActionListener(e -> {
            dispose(); // Fecha o JFrame atual
        });

        panel.add(movimentacaoLabel);
        panel.add(objetivoLabel);
        panel.add(voltarButton);
        add(panel);
    }

    /**
     * Coloca os valores iniciais no JFrame da classe.
     */
    private void iniciaJanela() {
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Seaquest - Como Jogar");
        setVisible(true);
    }
}
