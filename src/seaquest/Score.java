package seaquest;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import recursosScore.Pessoa;

/**
 * Apresenta o score
 *
 * @author Rafael Alves de Lima
 * @author Gabriel Kato Gomes
 *
 * @version 1.0
 */
public class Score extends JFrame {

    /**
     * Nome do arquivo binario com os scores salvos
     */
    private final InputStream arquivo = this.getClass().getClassLoader().getResourceAsStream("scores.dat");

    /**
     *
     */
    private final static File file = new File(System.getProperty("src"), "scores.dat");

    /**
     *
     */
    JPanel panel = new JPanel();

    /**
     * Construtor para o fim do jogo
     *
     * @param nome para possivelmente guardar no arquivo
     * @param pontuacao pontuacao final do jogador
     */
    public Score(String nome, int pontuacao) {
        exibeDados(nome, pontuacao);
        iniciaJanela();
    }

    /**
     * Construtor padrao, apenas para visualizar os dados do arquivo
     */
    public Score() {
        iniciaJanela();
        exibeDados();
    }

    /**
     * Le um arquivo binario, para pegar seus nomes e suas pontuacoes
     *
     * @param arquivo onde fica armazenado os scores
     * @return um ArrayList de Conta com os scores presentes
     */
    private static ArrayList<Pessoa> leArquivoBin(InputStream arquivo) {
        ObjectInputStream leitorObj = null;
        FileInputStream leitorArquivo = null;
        ArrayList<Pessoa> ret = new ArrayList<>();

        try {
            try {
                leitorObj = new ObjectInputStream(arquivo);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Arquivo não encontrado",
                        "Arquivo não encontrado", JOptionPane.ERROR_MESSAGE);
            }

            boolean haRegistros = true;

            while (haRegistros) {
                try {
                    Pessoa c = (Pessoa) leitorObj.readObject();
                    ret.add(c);
                } catch (EOFException e) {
                    haRegistros = false;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (leitorArquivo != null) {
                    leitorArquivo.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return ret;
    }

    /**
     * Escreve em um arquivo binario, se os valores forem atualizados
     *
     * @param arquivo onde fica armazenado os scores
     * @param pessoas ArrayList atualizado com as maiores pontuacoes
     */
    private static void escreveArquivoBin(List<Pessoa> pessoas) {
        FileOutputStream escritorArquivo = null;
        ObjectOutputStream escritorObj = null;

        try {
            escritorArquivo = new FileOutputStream(file);
            escritorObj = new ObjectOutputStream(escritorArquivo);
            //escritorObj.writeObject(c); // SALVA O ARRAYLIST INTEIRO
            for (Pessoa ct : pessoas) //SALVA OS ELEMENTOS DO ARRAYLIST SEPARADAMENTE
            {
                escritorObj.writeObject((Pessoa) ct); //Tem que implementar a interface Serializable!
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (escritorArquivo != null) {
                    escritorArquivo.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Exibe os dados e atualiza o arquivo no fim do jogo
     *
     * @param pessoas presentes no arquivo lido
     * @param nome para ser adicionado ao arquivo
     * @param pontuacao para ser adicionado ao arquivo
     */
    private void exibeDados(String nome, int pontuacao) {
        List<Pessoa> pessoas = leArquivoBin(arquivo);

        int i;
        int y = 50;

        for (i = 0; i < 3; i++) {
            Pessoa c = pessoas.get(i);

            if (pontuacao > c.getPontuacao()) {
                for (int j = 2; j < i; j--) {
                    Pessoa p = pessoas.get(j - 1);

                    pessoas.add(j, p);
                }

                pessoas.add(i, new Pessoa(nome, pontuacao));

                escreveArquivoBin(pessoas);
                break;
            }
        }

        JLabel label[] = new JLabel[3];

        for (i = 0; i < 3; i++) {
            label[i] = new JLabel();

            label[i].setText(i + 1 + " - " + pessoas.get(i).getNome() + " com " + pessoas.get(i).getPontuacao() + " pontos");
            label[i].setBounds(60, y, 200, 50);
            y += 20;

            panel.add(label[i]);
        }

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(90, 190, 100, 40);
        voltarButton.addActionListener(e -> {
            dispose(); // Fecha o JFrame atual
        });

        panel.add(voltarButton);
    }

    /**
     * Exibe os dados
     *
     * @param pessoas presentes no arquivo lido
     */
    private void exibeDados() {
        List<Pessoa> pessoas = leArquivoBin(arquivo);

        JLabel label[] = new JLabel[3];

        int i;
        int y = 50;

        for (i = 0; i < 3; i++) {
            label[i] = new JLabel();

            label[i].setText(i + 1 + " - " + pessoas.get(i).getNome() + " com " + pessoas.get(i).getPontuacao() + " pontos");
            label[i].setBounds(60, y, 200, 50);
            y += 20;

            panel.add(label[i]);
        }

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(90, 190, 100, 40);
        voltarButton.addActionListener(e -> {
            dispose(); // Fecha o JFrame atual
        });

        panel.add(voltarButton);
    }

    /**
     * Coloca os valores iniciais no JFrame da classe
     */
    private void iniciaJanela() {
        panel.setLayout(null);
        add(panel);

        setSize(300, 300);
        setTitle("Seaquest - Score");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
