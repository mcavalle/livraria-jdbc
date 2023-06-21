import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CriarTabelaLivro {
    public static void main(String[] args) throws SQLException {
        //1. Criar a conexão
        String url = "jdbc:sqlite:banco.sqlite";
        Connection connection = DriverManager.getConnection(url);


        //2. Executa a consulta e usa os resultados
        String sql = """
            CREATE TABLE livros(
                id INTEGER PRIMARY KEY,
                nome TEXT,
                editora TEXT,
                preco FLOAT
            );
        """;

        Statement statement = connection.createStatement();
        statement.execute(sql);


        //3. Fecha a conexão
        statement.close();
        connection.close();
    }
}