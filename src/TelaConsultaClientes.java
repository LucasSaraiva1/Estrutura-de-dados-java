import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaConsultaClientes extends JFrame {
    private JPanel panelClientes;
    private JTextField txtFiltro;
    private JButton btnFiltrar;
    private ArrayList<Cliente> clientes;

    public TelaConsultaClientes(ArrayList<Cliente> clientes) {
        super("Consulta de Clientes");
        this.clientes = clientes;

        JPanel panelFiltro = new JPanel(new FlowLayout());
        panelFiltro.add(new JLabel("Filtrar por:"));
        txtFiltro = new JTextField(20);
        panelFiltro.add(txtFiltro);
        btnFiltrar = new JButton("Filtrar");
        btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filtro = txtFiltro.getText();
                consultarClientes(filtro);
            }
        });
        panelFiltro.add(btnFiltrar);

        panelClientes = new JPanel();
        panelClientes.setLayout(new BoxLayout(panelClientes, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(panelClientes);

        consultarClientes(""); // Exibir todos os clientes ao iniciar a tela de consulta

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelFiltro, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setSize(800, 600); // Ajuste do tamanho da tela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void consultarClientes(String filtro) {
        panelClientes.removeAll(); // Limpar o painel de clientes antes de adicionar novos componentes

        for (Cliente cliente : clientes) {
            // Verificar se o cliente corresponde ao filtro
            if (cliente.getNome().toLowerCase().contains(filtro.toLowerCase()) ||
                    cliente.getCpf().contains(filtro)) {
                JPanel panelCliente = new JPanel(new GridLayout(1, 5));
                panelCliente.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                panelCliente.add(new JLabel("ID: " + cliente.getId()));
                panelCliente.add(new JLabel("Nome: " + cliente.getNome()));
                panelCliente.add(new JLabel("CPF: " + cliente.getCpf()));
                panelCliente.add(new JLabel("Celular: " + cliente.getCelular()));
                panelCliente.add(new JLabel("Email: " + cliente.getEmail()));

                panelClientes.add(panelCliente);
            }
        }

        panelClientes.revalidate(); // Atualizar o layout do painel de clientes
        panelClientes.repaint(); // Repintar o painel de clientes
    }
}
