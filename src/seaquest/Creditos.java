package seaquest;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A classe <b>Creditos</b> define um tipo de dado abstrato para criação da
 * estrutura de classes de um jogo.
 *
 * @author Rafael Alves de Lima.
 * @author Gabriel Kato Gomes.
 *
 * @since ago 2022.
 *
 * @version 1.0
 */
public class Creditos extends JFrame {

    /**
     * Construtor default da classe <b>Creditos</b>.<br><br>
     * <b>Uso:</b><br>
     * Creditos creditos = new Creditos();<br><br>
     */
    public Creditos() {
        iniciaJanela();
        dados();
    }

    /**
     * Exibe os nomes e o agrdecimento na tela.
     */
    private void dados() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel nome1Label = new JLabel("Rafael Alves de Lima");
        nome1Label.setBounds(60, 40, 240, 40);

        JLabel nome2Label = new JLabel("Gabriel Kato Gomes");
        nome2Label.setBounds(nome1Label.getX(), nome1Label.getY() + 40, 240, 40);

        JLabel agradecimentoLabel = new JLabel("Obrigado por jogar nosso jogo!");
        agradecimentoLabel.setBounds(55, nome2Label.getY() + 70, 220, 40);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(90, 190, 100, 40);
        voltarButton.addActionListener(e -> {
            dispose(); // Fecha o JFrame atual
        });

        panel.add(nome1Label);
        panel.add(nome2Label);
        panel.add(agradecimentoLabel);
        panel.add(voltarButton);
        add(panel);
    }

    /**
     * Coloca os valores iniciais no JFrame da classe.
     */
    private void iniciaJanela() {
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Seaquest - Creditos");
        setVisible(true);
    }
}
