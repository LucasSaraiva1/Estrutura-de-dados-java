import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaConsultaClientes extends JFrame {
    private JTextArea areaTexto;
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

        areaTexto = new JTextArea(10, 30);
        areaTexto.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(areaTexto);

        consultarClientes(""); // Exibir todos os clientes ao iniciar a tela de consulta

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelFiltro, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void consultarClientes(String filtro) {
        areaTexto.setText(""); // Limpar a área de texto antes de exibir os resultados

        for (Cliente cliente : clientes) {
            // Verificar se o cliente corresponde ao filtro
            if (cliente.getNome().toLowerCase().contains(filtro.toLowerCase()) ||
                cliente.getCpf().contains(filtro)) {
                areaTexto.append(cliente.toString() + "\n");
            }
        }
    }
}
