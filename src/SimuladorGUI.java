import java.awt.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class SimuladorGUI extends JFrame {
    private JTextField txtCadeia;
    private JSpinner spinFrames;

    // Paleta SDPM (Baseada no site enviado)
    private final Color SDPM_BG = new Color(10, 10, 10);      // Fundo preto total
    private final Color SDPM_CARD = new Color(20, 20, 20);    // Fundo do card central
    private final Color SDPM_GREEN = new Color(0, 230, 118);  // Verde neon dos detalhes
    private final Color SDPM_TEXT = new Color(220, 220, 220); // Texto cinza claro
    private final Color SDPM_INPUT_BG = new Color(30, 30, 30);// Fundo dos inputs

    public SimuladorGUI() {
        setTitle("SDPM - Page Replacement Simulator");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(SDPM_BG);
        setLayout(new GridBagLayout());

        // --- CARD CENTRAL (FORNECER DADOS) ---
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(SDPM_CARD);
        card.setPreferredSize(new Dimension(600, 400));
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(40, 40, 40), 1, true),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 5, 15, 5);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        // Título do Card com ícone de raio (como no site)
        JLabel lblTituloCard = new JLabel("⚡ Fornecer dados");
        lblTituloCard.setFont(new Font("SansSerif", Font.BOLD, 30));
        lblTituloCard.setForeground(SDPM_GREEN);
        gbc.insets = new Insets(10, 10, 30, 10);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        card.add(lblTituloCard, gbc);

        // Labels e Inputs Estilizados
        txtCadeia = criarInput("");
        spinFrames = new JSpinner(new SpinnerNumberModel(4, 1, 10, 1));
        estilizarSpinner(spinFrames);

        gbc.gridwidth = 1; gbc.gridy = 1;
        card.add(criarLabel("Cadeia de páginas:"), gbc);
        gbc.gridx = 1;
        card.add(criarLabel("7,0,1,2,0,3,0,4"), gbc);
        card.add(txtCadeia, gbc);
        

        gbc.gridx = 0; gbc.gridy = 2;
        card.add(criarLabel("Quadros (Frames):"), gbc);
        gbc.gridx = 1;
        card.add(spinFrames, gbc);

        // Botão Simular (Verde Neon)
        JButton btnSimular = new JButton("Simular");
        btnSimular.setBackground(SDPM_GREEN);
        btnSimular.setForeground(Color.BLACK);
        btnSimular.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnSimular.setFocusPainted(false);
        btnSimular.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        card.add(btnSimular, gbc);

        add(card); // Adiciona o card central à tela principal

        btnSimular.addActionListener(e -> abrirResultados());
    }

    private JLabel criarLabel(String texto) {
        JLabel l = new JLabel(texto);
        l.setForeground(SDPM_TEXT);
        l.setFont(new Font("SansSerif", Font.PLAIN, 13));
        return l;
    }

    private JTextField criarInput(String padrao) {
        JTextField t = new JTextField(padrao, 15);
        t.setBackground(SDPM_INPUT_BG);
        t.setForeground(Color.WHITE);
        t.setCaretColor(SDPM_GREEN);
        t.setBorder(new LineBorder(new Color(60, 60, 60)));
        return t;
    }

    private void estilizarSpinner(JSpinner s) {
        s.setPreferredSize(new Dimension(150, 30));
        JComponent ed = s.getEditor();
        JFormattedTextField tf = ((JSpinner.DefaultEditor) ed).getTextField();
        tf.setBackground(SDPM_INPUT_BG);
        tf.setForeground(Color.WHITE);
        s.setBorder(new LineBorder(new Color(60, 60, 60)));
    }

    private void abrirResultados() {
        try {
            String[] partes = txtCadeia.getText().split(",");
            int[] paginas = Arrays.stream(partes).map(String::trim).mapToInt(Integer::parseInt).toArray();
            int frames = (int) spinFrames.getValue();

            // Chamada direta para seus algoritmos existentes
            int f1 = algoritmoFIFO.executar(paginas, frames);
            int f2 = algoritmoLRU.executar(paginas, frames);
            int f3 = algoritmoOtimo.executar(paginas, frames);
            int f4 = algoritmoClock.executar(paginas, frames);

            new TelaResultados(f1, f2, f3, f4, paginas.length).setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: Verifique a sequência de páginas.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimuladorGUI().setVisible(true));
    }
}

// --- TELA DE RESULTADOS ESTILIZADA ---
class TelaResultados extends JFrame {
    public TelaResultados(int f1, int f2, int f3, int f4, int total) {
        setTitle("SDPM - Resultado da Simulação");
        setSize(850, 650);
        getContentPane().setBackground(new Color(10, 10, 10));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(20, 20));

        PainelGrafico grafico = new PainelGrafico();
        grafico.setBackground(new Color(10, 10, 10));
        grafico.setDados(f1, f2, f3, f4);
        
        // Tabela com estilo Dark
        String[] col = {"Algoritmo", "Faltas", "Porcentagem de acerto %"};
        DefaultTableModel model = new DefaultTableModel(col, 0);
        adicionarLinha(model, "FIFO", f1, total);
        adicionarLinha(model, "LRU", f2, total);
        adicionarLinha(model, "Ótimo", f3, total);
        adicionarLinha(model, "Clock", f4, total);

        JTable table = new JTable(model);
        table.setBackground(new Color(25, 25, 25));
        table.setForeground(Color.WHITE);
        table.setRowHeight(35);
        table.setGridColor(new Color(50, 50, 50));
        
        JScrollPane sp = new JScrollPane(table);
        sp.getViewport().setBackground(new Color(15, 15, 15));
        sp.setPreferredSize(new Dimension(850, 180));

        add(grafico, BorderLayout.CENTER);
        add(sp, BorderLayout.SOUTH);
    }

    private void adicionarLinha(DefaultTableModel m, String n, int f, int t) {
        m.addRow(new Object[]{n, f, String.format("%.1f%%", ((double)(t-f)/t)*100)});
    }
}

// --- PAINEL DO GRÁFICO (REAPROVEITADO COM CORES SDPM) ---
class PainelGrafico extends JPanel {
    private int[] faltas = {0, 0, 0, 0};
    private final String[] nomes = {"FIFO", "LRU", "Ótimo", "Clock"};

    public void setDados(int f1, int f2, int f3, int f4) { this.faltas = new int[]{f1, f2, f3, f4}; repaint(); }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (Arrays.stream(faltas).sum() == 0) return;

        int base = getHeight() - 80;
        int max = Arrays.stream(faltas).max().getAsInt();
        int min = Arrays.stream(faltas).filter(f -> f > 0).min().orElse(0);

        for (int i = 0; i < 4; i++) {
            int h = (faltas[i] * 220) / (max == 0 ? 1 : max);
            int x = 120 + i * 160;

            // Destaque Neon no vencedor
            g2.setColor(faltas[i] == min ? new Color(0, 230, 118) : new Color(50, 50, 50));
            g2.fillRoundRect(x, base - h, 80, h, 15, 15);
            
            g2.setColor(Color.WHITE);
            g2.drawString(nomes[i], x + 20, base + 30);
            g2.drawString(String.valueOf(faltas[i]), x + 35, base - h - 10);
        }
    }
}