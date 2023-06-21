import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;

public class CadastrarClienteJFrame extends JFrame {
    private JLabel nomeLabel = new JLabel("Nome: ");
    private JTextField nomeTextField = new JTextField(20);
    private JLabel emailLabel = new JLabel("E-mail: ");
    private JTextField emailTextField = new JTextField(20);
    private JButton cadastrarButton = new JButton("Cadastrar");
    private JPanel panel = new JPanel();

    public CadastrarClienteJFrame(){
        setTitle("Cadastrar Clientes");
        setSize(300, 220);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(5, 2, 5, 5));
        panel.add(nomeLabel);
        panel.add(nomeTextField);
        panel.add(emailLabel);
        panel.add(emailTextField);
        panel.add(cadastrarButton);

        cadastrarButton.addActionListener(e ->{
            String nome = nomeTextField.getText();
            String email = emailTextField.getText();

            ArrayList<String> errors = new ArrayList<>();

            if(nome.isBlank()){
                errors.add("Nome deve ser preenchido");
            }

            if(nome.trim().length() < 3 || nome.trim().length() > 100){
                errors.add("Nome deve ter entre 3 e 100 caracteres");
            }

            if(email.isBlank()){
                errors.add("E-mail deve ser preenchido");
            }

            if(!errors.isEmpty()){
                JOptionPane.showMessageDialog(CadastrarClienteJFrame.this, String.join("\n", errors), "Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    ClienteDAO clienteDAO = new ClienteDAO();
                    Cliente clienteSalvo = clienteDAO.create(new Cliente(0, nome, email));
                    System.out.println(clienteSalvo.getId());
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
