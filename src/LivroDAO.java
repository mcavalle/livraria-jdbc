import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class LivroDAO {
    //Cadastrar livros
    public Livro create(Livro livro) throws SQLException{
        String sql = """
            INSERT INTO livros (nome, editora, preco)
            VALUES (?, ?, ?);
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
                .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            
            statement.setString(1, livro.getNome());
            statement.setString(2, livro.getEditora());
            statement.setString(3, livro.getPreco());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                livro.setId(rs.getInt(1));
            }

            rs.close();

            return livro;
        }
    }

    public List<Livro> findAll() throws SQLException {
        String sql = "SELECT * FROM livros;";
        List<Livro> livros = new ArrayList<>();

        try (
            Connection connection = Conexao.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        ) {
            while(rs.next()) {
                livros.add(resultSetToLivro(rs));
            }

            return livros;
        
        } 
        
    }
     private Livro resultSetToLivro(ResultSet rs) throws SQLException {
        return new Livro(
            rs.getInt("id"),
            rs.getString("nome"),
            rs.getString("editora"),
            rs.getString("preco")
        );
    }

}
