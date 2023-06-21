import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.FlowLayout;

public class TelaPrincipal extends JFrame{
    private JButton cadastrarClienteButton;
    private JButton cadastrarLivroButton;
    private JPanel panel; 

    public TelaPrincipal(){
        setTitle("Tela Principal");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menu = new JMenuBar();
        JMenu menuListar = new JMenu("Listar");

        JMenuItem listarClienteMenu = new JMenuItem("Listar Clientes");
        JMenuItem listarLivroMenu = new JMenuItem("Listar Livros");

        listarClienteMenu.addActionListener(e ->{
            new ListarClientesJFrame(new ClienteDAO());
        });

        listarLivroMenu.addActionListener(e -> {
            new ListarLivrosJFrame(new LivroDAO());
        });

        menuListar.add(listarClienteMenu);
        menuListar.add(listarLivroMenu);

        menu.add(menuListar);

        setJMenuBar(menu);

        cadastrarClienteButton = new JButton("Cadastrar Cliente");
        cadastrarLivroButton = new JButton("Cadastrar Livro");

        cadastrarClienteButton.addActionListener(e -> {
            new CadastrarClienteJFrame();
        });

        cadastrarLivroButton.addActionListener(e -> {
            new CadastrarLivroJFrame();
        });

        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(cadastrarClienteButton);
        panel.add(cadastrarLivroButton);

        getContentPane().add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
         SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                new TelaPrincipal();
            }
        });
    }
}
