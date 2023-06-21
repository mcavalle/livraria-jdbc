import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;

public class CadastrarLivroJFrame extends JFrame {
    private JLabel nomeLabel = new JLabel("Nome: ");
    private JTextField nomeTextField = new JTextField(20);
    private JLabel editoraLabel = new JLabel("Editora: ");
    private JTextField editoraTextField = new JTextField(15);
    private JLabel precoLabel = new JLabel("Preço: ");
    private JTextField precoTextField = new JTextField(10);
    private JButton cadastrarButton = new JButton("Cadastrar");
    private JPanel panel = new JPanel();

    public CadastrarLivroJFrame(){
        setTitle("Cadastrar Livros");
        setSize(300, 220);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(5, 2, 5, 5));
        panel.add(nomeLabel);
        panel.add(nomeTextField);
        panel.add(editoraLabel);
        panel.add(editoraTextField);
        panel.add(precoLabel);
        panel.add(precoTextField);
        panel.add(cadastrarButton);

        cadastrarButton.addActionListener(e ->{
            String nome = nomeTextField.getText();
            String editora = editoraTextField.getText();
            String preco = precoTextField.getText();

            ArrayList<String> errors = new ArrayList<>();

            if(nome.isBlank()){
                errors.add("Nome deve ser preenchido");
            }

            if(nome.trim().length() < 3 || nome.trim().length() > 100){
                errors.add("Nome deve ter entre 3 e 100 caracteres");
            }

            if(editora.isBlank()){
                errors.add("Preencha a editora");
            }

            if(preco.isBlank()){
                errors.add("Informe o preço");
            }

            if(!errors.isEmpty()){
                JOptionPane.showMessageDialog(CadastrarLivroJFrame.this, String.join("\n", errors), "Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    LivroDAO livroDAO = new LivroDAO();
                    Livro livroSalvo = livroDAO.create(new Livro(0, nome, editora, preco));
                    System.out.println(livroSalvo.getId());
                    cleanTextFields();
                } catch (Exception ex) {
                    
                }
            }
        });

        getContentPane().add(panel); 
        setResizable(false); 

        setVisible(true); 
    }
    private void cleanTextFields(){
    }
}
