public class Livro {
    private int id;
    private String nome;
    private String editora;
    private String preco;

    public Livro(int id, String nome, String editora, String preco){
        this.id = id;
        this.nome = nome;
        this.editora = editora;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Livro [id=" + id + ", nome=" + nome + ", editora=" + editora + ", preco=" + preco + "]";
    }

    
}
