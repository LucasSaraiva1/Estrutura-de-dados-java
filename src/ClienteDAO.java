import java.io.*;
import java.util.ArrayList;

public class ClienteDAO {
    private static final String FILENAME = "clientes.csv";
    private static final String DELIMITER = ",";
    private static int nextId = 1;

    public static void salvarClientes(ArrayList<Cliente> clientes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME))) {
            for (Cliente cliente : clientes) {
                writer.println(cliente.getId() + DELIMITER + cliente.getNome() + DELIMITER + cliente.getCpf() + DELIMITER + cliente.getCelular() + DELIMITER + cliente.getEmail());
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
                    nextId = Math.max(nextId, id + 1); // Atualiza o próximo ID baseado nos IDs existentes no arquivo
                    String nome = parts[1];
                    String cpf = parts[2];
                    String celular = parts[3];
                    String email = parts[4];
                    Cliente cliente = new Cliente(id, nome, cpf, celular, email);
                    clientes.add(cliente);
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
}
