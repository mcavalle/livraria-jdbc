import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    //Cadastrar clientes
    public Cliente create(Cliente cliente) throws SQLException{
        String sql = """
            INSERT INTO clientes (nome, email)
            VALUES (?, ?);
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
                .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getEmail());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                cliente.setId(rs.getInt(1));
            }

            rs.close();

            return cliente;
        }
    }

    public List<Cliente> findAll() throws SQLException {
        String sql = "SELECT * FROM clientes;";
        List<Cliente> clientes = new ArrayList<>();

        try (
            Connection connection = Conexao.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        ) {
            while(rs.next()) {
                clientes.add(resultSetToCliente(rs));
            }

            return clientes;
        
        }
        
    }
     private Cliente resultSetToCliente(ResultSet rs) throws SQLException {
        return new Cliente(
            rs.getInt("id"),
            rs.getString("nome"),
            rs.getString("email")
        );
    }
}
