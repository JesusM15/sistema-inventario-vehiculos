import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Panel extends JFrame {
    private JTable table;
    private String[] columnNames = {"Número de Identificación", "Marca", "Modelo", "Año de Fabricación", "Tipo"};
    private JButton cargarButton;
    private JButton getButton, deleteButton, addButton, updateButton;

    public Panel(){
        setTitle("Sistema de inventario vehiculos");
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null); // posicion absoluta

        // creando tabla para listar

        Vehiculo[][] data = {};
        table = new JTable(data, columnNames);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 850, 300);
        add(scrollPane);

        int buttonWidth = 150;
        int buttonX = 10;
        // botones
        cargarButton = new JButton("Cargar Vehículos");
        cargarButton.setBounds(buttonX, 320, 150, 30);
        add(cargarButton);
        buttonX += buttonWidth + 10;
        //boton para leer 1 solo vehiculo
        getButton = new JButton("Inspeccionar");
        getButton.setBounds(buttonX, 320, 150, 30);
        add(getButton);
        buttonX += buttonWidth + 10;
        deleteButton = new JButton("Eliminar");
        deleteButton.setBounds(buttonX, 320, 150, 30);
        add(deleteButton);
        buttonX += buttonWidth + 10;

        addButton = new JButton("Agregar vehiculo");
        addButton.setBounds(buttonX, 320, 150, 30);
        add(addButton);
        buttonX += buttonWidth + 10;
        updateButton = new JButton("Actualizar vehiculo");
        updateButton.setBounds(buttonX, 320, 150, 30);
        add(updateButton);

        this.setVisible(true);
    }

    public void addUpdateButton(ActionListener listener){
        updateButton.addActionListener(listener);
    }

    public void addAddButton(ActionListener listener){
        addButton.addActionListener(listener);
    }

    public void addDeleteButtonListener(ActionListener listener){
        deleteButton.addActionListener(listener);
    }

    public void addCargarButtonListener(ActionListener listener) {
        cargarButton.addActionListener(listener);
    }

    public void addGetButtonListener(ActionListener listener) {
        getButton.addActionListener(listener);
    }

    public void actualizarTabla(ArrayList<Vehiculo> vehiculos) {
        Object[][] data = new Object[vehiculos.size()][columnNames.length];

        for (int i = 0; i < vehiculos.size(); i++) {
            Vehiculo v = vehiculos.get(i);
            data[i][0] = v.getNumeroIdentificacion();
            data[i][1] = v.getMarca();
            data[i][2] = v.getModelo();
            data[i][3] = v.getFabricacion();
            data[i][4] = v.getTipo();
        }

        // Actualizar el modelo de la tabla
        table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
}
