import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaCadastroClientes extends JFrame implements ActionListener {
    private JTextField txtNome, txtCpf, txtCelular, txtEmail;
    private JButton btnCadastrar, btnVoltarMenu;
    private ArrayList<Cliente> listaClientes;

    public TelaCadastroClientes(ArrayList<Cliente> clientes) {
        super("Cadastro de Clientes");
        this.listaClientes = clientes;

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        panel.add(txtNome);
        panel.add(new JLabel("CPF:"));
        txtCpf = new JTextField();
        panel.add(txtCpf);
        panel.add(new JLabel("Celular:"));
        txtCelular = new JTextField();
        panel.add(txtCelular);
        panel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panel.add(txtEmail);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(this);
        panel.add(btnCadastrar);

        btnVoltarMenu = new JButton("Voltar ao Menu");
        btnVoltarMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarMenuPrincipal();
            }
        });
        panel.add(btnVoltarMenu);

        add(panel);
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCadastrar) {
            cadastrarCliente();
        }
    }

    private void cadastrarCliente() {
        String nome = txtNome.getText().trim();
        String cpf = txtCpf.getText().trim();
        String celular = txtCelular.getText().trim();
        String email = txtEmail.getText().trim();

        // Verificar se algum campo está vazio
        if (nome.isEmpty() || cpf.isEmpty() || celular.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return; // Sai do método se algum campo estiver vazio
        }

        // Verifica se o CPF já está cadastrado
        if (ClienteDAO.buscarClientePorCPF(cpf) != null) {
            JOptionPane.showMessageDialog(this, "CPF já cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return; // Sai do método se o CPF já estiver cadastrado
        }

        int id = ClienteDAO.getNextId();
        Cliente cliente = new Cliente(id, nome, cpf, celular, email);

        listaClientes.add(cliente);
        ClienteDAO.salvarClientes(listaClientes);

        // Notificação de cadastro realizado com sucesso
        JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        System.out.println("Cliente cadastrado: " + cliente);

        limparCampos();
    }

    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtCelular.setText("");
        txtEmail.setText("");
    }

    private void voltarMenuPrincipal() {
        dispose(); // Fecha a tela de cadastro
        Main.main(null); // Abre o menu principal novamente
    }
}
