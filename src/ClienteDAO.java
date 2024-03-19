import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ClienteDAO {
    private static final String FILENAME = "clientes.csv";
    private static final String DELIMITER = ",";
    private static int nextId = 1;
    private static HashMap<String, Cliente> hashClientes = new HashMap<>();

    public static void salvarClientes(ArrayList<Cliente> clientes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME))) {
            for (Cliente cliente : clientes) {
                writer.println(cliente.getId() + DELIMITER + cliente.getNome() + DELIMITER + cliente.getCpf() + DELIMITER + cliente.getCelular() + DELIMITER + cliente.getEmail());
                hashClientes.put(cliente.getCpf(), cliente); // Adiciona o cliente à tabela hash
            }
            System.out.println("Clientes salvos com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    public static ArrayList<Cliente> carregarClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                if (parts.length == 5) {
                    int id = Integer.parseInt(parts[0]);
                    nextId = Math.max(nextId, id + 1);
                    String nome = parts[1];
                    String cpf = parts[2];
                    String celular = parts[3];
                    String email = parts[4];
                    Cliente cliente = new Cliente(id, nome, cpf, celular, email);
                    clientes.add(cliente);
                    hashClientes.put(cpf, cliente); // Adiciona o cliente à tabela hash
                }
            }
            System.out.println("Clientes carregados com sucesso.");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de clientes não encontrado. Criando novo arquivo...");
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao carregar clientes: " + e.getMessage());
        }
        return clientes;
    }

    public static int getNextId() {
        return nextId++;
    }

    // Método para buscar um cliente pelo CPF na tabela hash
    public static Cliente buscarClientePorCPF(String cpf) {
        return hashClientes.get(cpf);
    }
}
