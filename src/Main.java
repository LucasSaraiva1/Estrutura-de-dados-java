import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        exibirMenuPrincipal();
    }

    private static void exibirMenuPrincipal() {
        String[] options = {"Cadastrar Clientes", "Consultar Clientes", "Sair"};
        int choice = JOptionPane.showOptionDialog(null, "Escolha uma opção", "Menu Principal", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                cadastrarClientes();
                break;
            case 1:
                consultarClientes();
                break;
            case 2:
                System.exit(0);
        }
    }

    private static void cadastrarClientes() {
        ArrayList<Cliente> clientes = ClienteDAO.carregarClientes();
        if (clientes == null) {
            clientes = new ArrayList<>();
        }
        new TelaCadastroClientes(clientes);
    }

    private static void consultarClientes() {
        ArrayList<Cliente> clientes = ClienteDAO.carregarClientes();
        if (clientes != null) {
            new TelaConsultaClientes(clientes);
        }
    }
}