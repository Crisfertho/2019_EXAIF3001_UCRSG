package Examen2019_IF3001;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {

     private Dispensador[] dispensadores;

    public Main() {
        dispensadores = new Dispensador[]{
                new Dispensador("Cristhofer", "1234567"),
                new Dispensador("Jossy", "2345678"),
                new Dispensador("Santi", "3456789")
        };

        setTitle("Estación TAGUITOCAR S.A.");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Insertar Vehículo", crearPanelInsertar());
        tabs.addTab("Buscar Vehículo", crearPanelBuscar());
        tabs.addTab("Eliminar Vehículo", crearPanelEliminar());
        tabs.addTab("Mostrar Vehículos", crearPanelMostrar());
        tabs.addTab("Realizar Corte", crearPanelCorte());

        add(tabs);

    }

    private JPanel crearPanelInsertar() {
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblDispensador = new JLabel("Dispensador:");
        JComboBox<String> comboDispensador = new JComboBox<>(new String[]{"1 - Cristhofer", "2 - Jossy", "3 - Santi"});

        JLabel lblPlaca = new JLabel("Placa:");
        JTextField txtPlaca = new JTextField();

        JLabel lblMarca = new JLabel("Marca:");
        JTextField txtMarca = new JTextField();

        JLabel lblAño = new JLabel("Año:");
        JTextField txtAño = new JTextField();

        JLabel lblCombustible = new JLabel("Tipo de Combustible:");
        JComboBox<String> comboCombustible = new JComboBox<>(new String[]{"Super", "Plus", "Diesel"});

        JLabel lblLitros = new JLabel("Litros:");
        JTextField txtLitros = new JTextField();

        JButton btnInsertar = new JButton("Insertar");
        btnInsertar.addActionListener(e -> {
            try {
                int dispensadorIndex = comboDispensador.getSelectedIndex();
                String placa = txtPlaca.getText();
                String marca = txtMarca.getText();
                int año = Integer.parseInt(txtAño.getText());
                String tipoCombustible = (String) comboCombustible.getSelectedItem();
                double litros = Double.parseDouble(txtLitros.getText());
                double precio = tipoCombustible.equals("Super") ? 596 :
                        tipoCombustible.equals("Plus") ? 580 : 534;

                Vehiculo vehiculo = new Vehiculo(placa, marca, año, tipoCombustible, litros, precio);
                dispensadores[dispensadorIndex].insertarVehiculo(vehiculo);
                JOptionPane.showMessageDialog(this, "Vehículo insertado correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error en los datos ingresados.");
            }
        });

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> {
            txtPlaca.setText("");
            txtMarca.setText("");
            txtAño.setText("");
            txtLitros.setText("");
            comboDispensador.setSelectedIndex(0);
            comboCombustible.setSelectedIndex(0);
        });

        panel.add(lblDispensador);
        panel.add(comboDispensador);
        panel.add(lblPlaca);
        panel.add(txtPlaca);
        panel.add(lblMarca);
        panel.add(txtMarca);
        panel.add(lblAño);
        panel.add(txtAño);
        panel.add(lblCombustible);
        panel.add(comboCombustible);
        panel.add(lblLitros);
        panel.add(txtLitros);
        panel.add(btnInsertar);
        panel.add(btnLimpiar);

        return panel;
    }

    private JPanel crearPanelBuscar() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblPlaca = new JLabel("Placa del Vehículo:");
        JTextField txtPlaca = new JTextField();
        JButton btnBuscar = new JButton("Buscar");
        JTextArea areaResultado = new JTextArea(10, 40);
        areaResultado.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaResultado);

        btnBuscar.addActionListener(e -> {
            String placa = txtPlaca.getText();
            areaResultado.setText("");
            boolean encontrado = false;
            for (Dispensador dispensador : dispensadores) {
                Vehiculo vehiculo = dispensador.buscarVehiculo(placa);
                if (vehiculo != null) {
                    areaResultado.append("Vehículo encontrado en dispensador de " + dispensador.nombrePistero + ":\n");
                    areaResultado.append(vehiculo.toString() + "\n");
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                areaResultado.append("No se encontró un vehículo con la placa proporcionada.\n");
            }
        });

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.add(lblPlaca, BorderLayout.WEST);
        topPanel.add(txtPlaca, BorderLayout.CENTER);
        topPanel.add(btnBuscar, BorderLayout.EAST);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelEliminar() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblPlaca = new JLabel("Placa del Vehículo a Eliminar:");
        JTextField txtPlaca = new JTextField();
        JButton btnEliminar = new JButton("Eliminar");
        JTextArea areaResultado = new JTextArea(10, 40);
        areaResultado.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaResultado);

        btnEliminar.addActionListener(e -> {
            String placa = txtPlaca.getText();
            boolean eliminado = false;
            areaResultado.setText("");
            for (Dispensador dispensador : dispensadores) {
                if (dispensador.eliminarVehiculo(placa)) {
                    areaResultado.append("Vehículo eliminado del dispensador de " + dispensador.nombrePistero + ".\n");
                    eliminado = true;
                    break;
                }
            }
            if (!eliminado) {
                areaResultado.append("No se encontró un vehículo con la placa proporcionada.\n");
            }
        });

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.add(lblPlaca, BorderLayout.WEST);
        topPanel.add(txtPlaca, BorderLayout.CENTER);
        topPanel.add(btnEliminar, BorderLayout.EAST);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelMostrar() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JButton btnMostrar = new JButton("Mostrar Todos los Vehículos");
        JTextArea areaResultado = new JTextArea(15, 50);
        areaResultado.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaResultado);

        btnMostrar.addActionListener(e -> {
            areaResultado.setText("");
            for (Dispensador dispensador : dispensadores) {
                areaResultado.append("Vehículos en el dispensador de " + dispensador.nombrePistero + ":\n");
                dispensador.mostrarVehiculos(areaResultado);
                areaResultado.append("\n");
            }
        });

        panel.add(btnMostrar, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelCorte() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JButton btnCorte = new JButton("Realizar Corte de Caja");
        JTextArea areaResultado = new JTextArea(15, 50);
        areaResultado.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaResultado);

        btnCorte.addActionListener(e -> {
            areaResultado.setText("");
            for (Dispensador dispensador : dispensadores) {
                dispensador.realizarCorte(areaResultado);
            }
        });

        panel.add(btnCorte, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
    }
}

