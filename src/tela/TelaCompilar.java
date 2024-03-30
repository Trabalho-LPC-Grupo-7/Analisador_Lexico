package tela;

import compiller.AnalisadorLexico;
import compiller.Token;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import static java.awt.Toolkit.getDefaultToolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;

/**
 *
 * @author KITO MAHIQUE
 */
public class TelaCompilar extends JFrame {
   
    
    JMenuBar jmBarra = new JMenuBar();
    JMenu jmFile = new JMenu("File");
    JMenu jmEdit = new JMenu("Edit");
    JMenu jmCompilar = new JMenu("Compilar");
    JMenu jmOpcoes = new JMenu("Opcoes");
    JMenu jmAjuda = new JMenu("Ajuda");
    
    JMenuItem jmiOpenFile= new JMenuItem("Open File");
    
    JMenuItem jmiCopy= new JMenuItem("Copy");
    JMenuItem jmiPaste= new JMenuItem("Paste");
    JMenuItem jmiDelete= new JMenuItem("Delete");
    
    JMenuItem jmiCompilar= new JMenuItem("Compilar");
    JMenuItem jmiOPcoes= new JMenuItem("Opcoes");
    JMenuItem jmiAjuda= new JMenuItem("Ajuda");
    
    // Criando um JPanel com um layout personalizado para conter o JTextArea e os números
    JPanel jpCodigo = new JPanel(new BorderLayout());
    JPanel jpLabel = new JPanel(new BorderLayout());
    JLabel jlSaida = new JLabel("SAIDA DO LEXICO");
    JPanel jpTabela = new JPanel(new BorderLayout());
    DefaultTableModel modelo= new DefaultTableModel();
    JTable jtTabela = new JTable(modelo);
    
    Font f1= new Font("Monospaced", Font.BOLD,20);
    Font f2= new Font("Calibre", Font.BOLD,15);
    Font f3= new Font("Calibre", Font.BOLD,15);
    
    JPanel jpOutPut = new JPanel(new BorderLayout());
    JLabel jlOutPut = new JLabel("");
    //area
    private JTextArea jtaCodigoFonte;
    private JTextArea linesTextArea;
    
    long tempoInicial;
    long tempoFinal;
    int milissegundosDecorridos;
    int erro=0;
    
    public TelaCompilar(){
        setLayout(null);
        
        setSize(1200, 640);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Analisador Lexico");
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.DARK_GRAY);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        
        
        setJMenuBar(jmBarra);
        jmFile.setFont(f3);
        jmEdit.setFont(f3);
        jmCompilar.setFont(f3);
        jmiCompilar.setFont(f3);
        jmOpcoes.setFont(f3);
        jmAjuda.setFont(f3);
        
        jmBarra.add(jmFile);
        jmBarra.add(jmEdit);
        jmBarra.add(jmCompilar);
        jmBarra.add(jmOpcoes);
        jmBarra.add(jmAjuda);
        
        //adiconando os itens aos menus
        URL iconURL = getClass().getResource("../icons/play_icon.png");
        jmiCompilar.setIcon(new ImageIcon(iconURL));
        
        jmFile.add(jmiOpenFile);
        jmEdit.add(jmiCopy);
        jmEdit.add(jmiPaste);
        jmEdit.add(jmiDelete);
        jmCompilar.add(jmiCompilar);
        jmOpcoes.add(jmiOPcoes);
        jmAjuda.add(jmiAjuda);
        
        //pegando tamanho da tela
        Dimension frameSize =getContentPane().getSize();
        
        int jpCodigoH = (int) (frameSize.height * 0.55) ;
        jpCodigo.setBounds(5, 10, frameSize.width-10, jpCodigoH);
        jpCodigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        
        //componentes abaixo
        int jpLabelH = (int) (frameSize.height * 0.05) ;
        jpLabel.setBounds(5, jpCodigoH+10, frameSize.width-10, jpLabelH);
        jpLabel.setBackground(Color.WHITE);
        jpLabel.add(jlSaida);
        
        jlSaida.setSize(1175,30);
        jlSaida.setFont(f3);
        jlSaida.setHorizontalAlignment((int) CENTER_ALIGNMENT);
      
        int jpTabelaH = (int) (frameSize.height * 0.3) ;
        jpTabela.setBounds(5, jpCodigoH + jpLabelH + 10, frameSize.width-10, jpTabelaH);
        
        jpLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        
        this.add(jpLabel);
        
        //tabela
        
            jpTabela.setBorder(BorderFactory.createEtchedBorder());
            // Dados para a tabela

        // Cabeçalhos das colunas
        // Criando a tabela
        modelo.addColumn("LINHA");
        modelo.addColumn("LEXEMA");
        modelo.addColumn("TOKEN");
        
        jtTabela.setFillsViewportHeight(true);

        // Adicionando a tabela a um JScrollPane
        JScrollPane scrollPaneTabela = new JScrollPane(jtTabela);

        // Adicionando o JScrollPane ao painel principal
        jpTabela.add(scrollPaneTabela, BorderLayout.CENTER);

        // Adicionando o painel principal ao frame
        add(jpTabela);
        
         //OUtput
        int jpOutPutH = (int) (frameSize.height * 0.07) ;
        jpOutPut.setBounds(5, jpCodigoH + jpLabelH + jpTabelaH +10, frameSize.width-10, jpOutPutH);
        jpOutPut.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "OUTPUT"));
        jlOutPut.setHorizontalTextPosition((int) LEFT_ALIGNMENT);
        jlOutPut.setVerticalTextPosition((int) CENTER_ALIGNMENT);
        jlOutPut.setFont(f3);
        jpOutPut.add(jlOutPut);
        this.add(jpOutPut);
        
        //area
        
        jpCodigo.setBorder(new EmptyBorder(5, 5, 5, 5));

        jtaCodigoFonte = new JTextArea();
        jtaCodigoFonte.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateLineNumbers();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateLineNumbers();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateLineNumbers();
            }
        });

        linesTextArea = new JTextArea();
        linesTextArea.setEditable(false);
        linesTextArea.setBackground(Color.LIGHT_GRAY);
        linesTextArea.setFont(f1);
        jtaCodigoFonte.setFont(f1);
        
        //margem
        jtaCodigoFonte.setMargin(new Insets(0, 10, 0, 0));
        linesTextArea.setMargin(new Insets(0, 8, 0, 8));
        linesTextArea.setBackground(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(jtaCodigoFonte);
        scrollPane.setRowHeaderView(linesTextArea);

        jpCodigo.add(scrollPane, BorderLayout.CENTER);

        add(jpCodigo);

        updateLineNumbers();
        
        
        // Força a atualização da tela
        revalidate();
        repaint();

        updateLineNumbers();

        //Area reservada para accoes
        //adicionar accao ao clicar no botao compilar
        jmiCompilar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensagem;
                try {
                    tempoInicial = System.currentTimeMillis();
                    accaoCompilar();
                    
                } catch (BadLocationException ex) {
                    Logger.getLogger(TelaCompilar.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(erro==1){
                        jlOutPut.setForeground(Color.red);
                        mensagem="FAILED";
                    }else{
                        jlOutPut.setForeground(Color.GREEN);
                        mensagem="SUCCESSFUL";
                    }
                tempoFinal = System.currentTimeMillis();
                 milissegundosDecorridos = (int) ((tempoFinal - tempoInicial) % 1000);
                jlOutPut.setText("BUILD "+ mensagem + " (total time: "+ 
                            milissegundosDecorridos+" milissegundos)");
            }
        });
        
        //
        
        
    }
    
    private void updateLineNumbers() {
        String text = jtaCodigoFonte.getText();
        int lineCount = jtaCodigoFonte.getLineCount();
        StringBuilder numbersText = new StringBuilder();
        for (int i = 1; i <= lineCount; i++) {
            numbersText.append(i).append('\n');
        }
        linesTextArea.setText(numbersText.toString());
        linesTextArea.setCaretPosition(0);
    }
    
    public void preencherTabela (ArrayList<Token> dado) throws BadLocationException{
        //criando a tabela
        DefaultTableModel tabela = (DefaultTableModel)jtTabela.getModel();
        tabela.setRowCount(0);
        jtTabela.setFont(f2);
        jtTabela.setRowHeight(25);
        //crindo arrayList para receber os dados passados por parametro
        ArrayList<Token> dados = dado;
        
        //preenchendo a tabela com os simbolos e seus estados (existe(simbolo) ou nao existe(error))
        for(int i=0; i<dados.size(); i++){
            
            Object t[] = {jtaCodigoFonte.getLineOfOffset(dados.get(i).getLinha())+1,
                dados.get(i).getToken(), dados.get(i).getEstado()};
            tabela.addRow(t);
            
            if(dados.get(i).getEstado().equals("ERRO")){
                pintarPalavra(dados.get(i).getToken());
                erro=1;
            }
        }
    }
    
    public void accaoCompilar() throws BadLocationException{
        String codigoFonte="";
        for(int i=0; i<jtaCodigoFonte.getText().length(); i++){
            if (jtaCodigoFonte.getText().charAt(i) == '\n') {
               codigoFonte+=" ";
            }else {
               codigoFonte+=jtaCodigoFonte.getText().charAt(i); 
            }
        }
        
       AnalisadorLexico al = new AnalisadorLexico();
       preencherTabela(al.verificarCaractere(codigoFonte));
    }
    
    
    public void pintarPalavra(String palavra) {
        try {
            Highlighter highlighter = jtaCodigoFonte.getHighlighter();
            highlighter.removeAllHighlights(); // Remove todos os destaques existentes

            String texto = jtaCodigoFonte.getText();
            int inicioPalavra = texto.indexOf(palavra); // Encontra a posição da palavra

            while (inicioPalavra >= 0) {
                int numeroLinha = jtaCodigoFonte.getLineOfOffset(inicioPalavra); // Obtém o número da linha em que a palavra está
                int inicioLinha = jtaCodigoFonte.getLineStartOffset(numeroLinha); // Obtém o início da linha
                int fimLinha = jtaCodigoFonte.getLineEndOffset(numeroLinha); // Obtém o fim da linha

                // Pinta toda a linha de vermelho
                highlighter.addHighlight(inicioLinha, fimLinha,
                        new DefaultHighlighter.DefaultHighlightPainter(Color.RED));

                inicioPalavra = texto.indexOf(palavra, fimLinha); // Procura pela próxima ocorrência da palavra
            }

            // Desativa a funcionalidade de pintura enquanto digitamos
            Document document = jtaCodigoFonte.getDocument();
            document.addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    removerPintura();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    removerPintura();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    removerPintura();
                }

                private void removerPintura() {
                    highlighter.removeAllHighlights();
                }
            });
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}
