// Classe Cliente
public class Cliente {
    private int id;
    private String nome;
    private String cpf;
    private String celular;
    private String email;

    public Cliente(int id, String nome, String cpf, String celular, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.celular = celular;
        this.email = email;
    }

    // Getters e setters para o ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Outros getters
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCelular() {
        return celular;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", CPF: " + cpf + ", Celular: " + celular + ", Email: " + email;
    }
}