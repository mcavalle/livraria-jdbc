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

public class ListarClientesJFrame extends JDialog{
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private JPanel panel;
    private ClienteDAO clienteDAO;

    public ListarClientesJFrame(ClienteDAO clienteDAO){
        this.clienteDAO = clienteDAO;
        setTitle("Listar Clientes");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);

        tableModel = new DefaultTableModel(
            new Object[] {"ID", "Nome", "Email"}, 0
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
            List<Cliente> clientes = clienteDAO.findAll();

            for (Cliente cliente : clientes) {
                Object[] row = { 
                    cliente.getId(), 
                    cliente.getNome(), 
                    cliente.getEmail()
                };
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                ListarClientesJFrame.this, 
                "Erro ao acessar o banco de dados",
                "Erro",
                JOptionPane.ERROR_MESSAGE    
            );
        }
    }
}