import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class ListarLivrosJFrame extends JDialog{
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private JPanel panel;
    private LivroDAO livroDAO;

    public ListarLivrosJFrame(LivroDAO livroDAO){
        this.livroDAO = livroDAO;
        setTitle("Listar Livros");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);

        tableModel = new DefaultTableModel(
            new Object[] {"ID", "Nome", "Editora", "Preco"}, 0
        );

        table = new JTable(tableModel);

        scrollPane = new JScrollPane(table);

        panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        loadData();

        getContentPane().add(panel);

        setVisible(true);
    }

    private void loadData() {
        try {
            List<Livro> livros = livroDAO.findAll();

            for (Livro livro : livros) {
                Object[] row = { 
                    livro.getId(), 
                    livro.getNome(), 
                    livro.getEditora(), 
                    livro.getPreco(),
                };
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                ListarLivrosJFrame.this, 
                "Erro ao acessar o banco de dados",
                "Erro",
                JOptionPane.ERROR_MESSAGE    
            );
        }
    }
}