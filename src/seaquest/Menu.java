package seaquest;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import recusosJogo.InicializaJogo;

/**
 * A classe <b>Menu</b> define um tipo de dado abstrato para criação da
 * estrutura de classes de um jogo.
 *
 * @author Rafael Alves de Lima.
 * @author Gabriel Kato Gomes.
 *
 * @since ago 2022.
 *
 * @version 1.0
 */
public class Menu extends JFrame {

    /**
     * Construtor default da classe <b>Menu</b>.<br><br>
     * <b>Uso:</b><br>
     * Menu menu = new Menu();<br><br>
     */
    public Menu() {
        iniciaJanela();
        botoes();
    }

    /**
     * Exibe os botoes no JPanel.
     */
    private void botoes() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton jogoButton = new JButton("Iniciar Novo Jogo");
        jogoButton.setBounds(0, 0, 240, 40);
        jogoButton.addActionListener(e -> {
            new InicializaJogo();
        });

        JButton scoreButton = new JButton("Score");
        scoreButton.setBounds(0, 40, 240, 40);
        scoreButton.addActionListener(e -> {
//            new Score(" ", 0);
            new Score();
        });

        JButton comoJogarButton = new JButton("Como Jogar");
        comoJogarButton.setBounds(0, 80, 240, 40);
        comoJogarButton.addActionListener(e -> {
            new ComoJogar();
        });

        JButton creditosButton = new JButton("Creditos");
        creditosButton.setBounds(0, 120, 240, 40);
        creditosButton.addActionListener(e -> {
            new Creditos();
        });

        JButton sairButton = new JButton("Sair");
        sairButton.setBounds(0, 160, 240, 40);
        sairButton.addActionListener(e -> {
            System.exit(0);
        });

        add(jogoButton);
        add(scoreButton);
        add(comoJogarButton);
        add(creditosButton);
        add(sairButton);
        add(panel);
    }

    /**
     * Coloca os valores iniciais no JFrame da classe.
     */
    private void iniciaJanela() {
        setSize(250, 235);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Seaquest - Menu");
        setVisible(true);
    }
}
