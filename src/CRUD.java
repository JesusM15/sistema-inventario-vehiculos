import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class CRUD {
    private ArrayList<Vehiculo> vehiculos;
    private Panel panel;
    private File file;

    public CRUD() {
        this.panel = new Panel();
        this.vehiculos = new ArrayList<>();

        panel.addCargarButtonListener(e -> cargarVehiculosEnTabla());
        panel.addGetButtonListener(this::cargarInfoVehiculo);
        panel.addDeleteButtonListener(this::deleteVehicle);
        panel.addAddButton(this::addVehicle);
        panel.addUpdateButton(this::updateVehicle);
    }

    public void setFile(File file) {
        this.file = file;
    }

    private void writeNewVehicle(Vehiculo vehiculo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName(), true))) { // "true" para agregar sin sobrescribir
            writer.write(vehiculo.toCSVFormat());
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el vehículo en el archivo.");
        }
    }

    public void saveAllVehicles() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) { // Sobrescribir archivo completo
            for (Vehiculo vehiculo : vehiculos) {
                writer.write(vehiculo.toCSVFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar los vehículos en el archivo.");
        }
    }

    public void iniciarCRUD(){
        panel.activarPanel();
    }

    public void updateVehicle(ActionEvent event){
        Vehiculo vehiculo = getVehicleById();
        if(vehiculo != null){
            JOptionPane.showMessageDialog(null, "Deje el campo en blanco si no lo modificara");
            String marca = JOptionPane.showInputDialog("Ingrese la marca");
            if (!marca.isEmpty()) {
                vehiculo.setMarca(marca);
            }

            String modelo = JOptionPane.showInputDialog("Ingrese el modelo");
            if (!modelo.isEmpty()) {
                vehiculo.setModelo(modelo);
            }

            String anoFabricacion = JOptionPane.showInputDialog("Ingrese el año de fabricación");
            if (!anoFabricacion.isEmpty()) {
                try {
                    int anoFabricacionInt = Integer.parseInt(anoFabricacion);
                    vehiculo.setFabricacion(anoFabricacionInt);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el año.");
                }
            }

            String numeroIdentificacion;
            do {
                numeroIdentificacion = JOptionPane.showInputDialog("Ingrese el número de identificación");

                if (numeroIdentificacion.isEmpty()) {
                    break;
                }

                if (idExist(numeroIdentificacion)) {
                    JOptionPane.showMessageDialog(null, "El número de identificación ya existe. Ingrese uno diferente o deje en blanco para no modificar.");
                } else {
                    vehiculo.setNumeroIdentificacion(numeroIdentificacion);
                    break;
                }
            } while (true);


            if(vehiculo.getTipo().equals("Moto")){
                Moto moto = (Moto) vehiculo;
                String tipoDeMotor = JOptionPane.showInputDialog("Ingrese el tipo de motor");
                if(!tipoDeMotor.isEmpty()){
                    moto.setTipoDeMotor(tipoDeMotor);
                }
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Es deportiva?", "Tipo de Vehículo", JOptionPane.YES_NO_OPTION);
                moto.setDeportiva(respuesta == JOptionPane.YES_OPTION);
            }else if(vehiculo.getTipo().equals("Auto")){
                Auto auto = (Auto) vehiculo;
                String numPuertas = JOptionPane.showInputDialog("Ingrese el numero de puertas: ");
                if(!numPuertas.isEmpty()){
                    try {
                        int numPuertasAuto = Integer.parseInt(numPuertas);
                        auto.setNumPuertas(numPuertasAuto);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el año.");
                    }
                }
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Es deportiva?", "Tipo de Vehículo", JOptionPane.YES_NO_OPTION);
                auto.setDeportivo(respuesta == JOptionPane.YES_OPTION);
            }else if(vehiculo.getTipo().equals("Camion")){
                Camion camion = (Camion) vehiculo;
                String numPuertas = JOptionPane.showInputDialog("Ingrese el numero de puertas: ");
                String capacidad = JOptionPane.showInputDialog("Ingrese la capacidad: ");
                if(!numPuertas.isEmpty()){
                    try {
                        int numPuertasCamion = Integer.parseInt(numPuertas);
                        camion.setNumeroDePuertas(numPuertasCamion);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el año.");
                    }
                }
                if(!capacidad.isEmpty()){
                    try{
                        int capacidadAuto = Integer.parseInt(capacidad);
                        camion.setCapacidadDeCarga( capacidadAuto);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el año.");
                    }
                }
            }

            panel.actualizarTabla(vehiculos);
        }
    }

    private boolean idExist(String id){
        return vehiculos.stream().anyMatch(v -> v.getNumeroIdentificacion().equals(id));
    }

    public void addVehicle(ActionEvent actionEvent) {
        char tipo;
        do{
            tipo = (JOptionPane.showInputDialog("Ingrese un tipo de vehiculo\na) Moto\nb) Auto\nc) Camion")).charAt(0);
            if(tipo != 'a' && tipo != 'b' && tipo != 'c'){
                JOptionPane.showMessageDialog(null, "El tipo de vehiculo no es valido");
            }
        }while(tipo != 'a' && tipo != 'b' && tipo != 'c');
        String marca = JOptionPane.showInputDialog("Ingrese la marca");
        String modelo = JOptionPane.showInputDialog("Ingrese el modelo");
        String anoFabricacion = "0";
        int anoFabricacionInt = 0;

        try {
            anoFabricacion = JOptionPane.showInputDialog("Ingrese el año de fabricación");
            anoFabricacionInt = Integer.parseInt(anoFabricacion);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el año.");
        }

        // validacion para ver que no se repita...
        String numeroIdentificacion;
        do{
            numeroIdentificacion = JOptionPane.showInputDialog("Ingrese el numero de identificacion: ");
            if(idExist(numeroIdentificacion)){
                JOptionPane.showMessageDialog(null, "El numero de identificacion ya existe");
            }
        }while(idExist(numeroIdentificacion));

        switch (tipo){
            case 'a':
                String tipoDeMotor = JOptionPane.showInputDialog("Ingrese el tipo de motor");
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Es deportiva?", "Tipo de Vehículo", JOptionPane.YES_NO_OPTION);
                boolean esDeportiva = (respuesta == JOptionPane.YES_OPTION);

                Moto moto = new Moto(marca, modelo, anoFabricacionInt, numeroIdentificacion, tipoDeMotor, esDeportiva);
                vehiculos.add(moto);
                writeNewVehicle(moto);
                break;
            case 'b':
                int numPuertasAuto = 0;
                String numPuertas = JOptionPane.showInputDialog("Ingrese el numero de puertas");
                if(!numPuertas.isEmpty()){
                    try {
                        numPuertasAuto= Integer.parseInt(numPuertas);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el año.");
                    }
                }
                int respuestaAuto = JOptionPane.showConfirmDialog(null, "¿Es deportiva?", "Tipo de Vehículo", JOptionPane.YES_NO_OPTION);
                boolean esDeportivaAuto = (respuestaAuto == JOptionPane.YES_OPTION);
                Auto auto = new Auto(marca,modelo,anoFabricacionInt,numeroIdentificacion,esDeportivaAuto,numPuertasAuto);
                vehiculos.add(auto);
                writeNewVehicle(auto);
                break;
            case 'c':
                int numPuertasCamion = 0;
                int capacidad = 0;
                String numPuertasCam = JOptionPane.showInputDialog("Ingrese el numero de puertas: ");
                String capacidadCam = JOptionPane.showInputDialog("Ingrese la capacidad: ");
                if(!numPuertasCam.isEmpty()){
                    try {
                        numPuertasCamion= Integer.parseInt(numPuertasCam);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el año.");
                    }
                }
                if(!capacidadCam.isEmpty()){
                    try {
                        capacidad= Integer.parseInt(capacidadCam);
                    }catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null,"por favor, ingrese un numero valido para el año.");
                    }
                }
                Camion camion = new Camion(marca,modelo,anoFabricacionInt,numeroIdentificacion,numPuertasCamion,capacidad);
                vehiculos.add(camion);
                writeNewVehicle(camion);
                break;
        }
        saveAllVehicles();
        panel.actualizarTabla(vehiculos);
    }
    public void loadVehiclesFromFile() {
        if (file == null || !file.exists()) {
            JOptionPane.showMessageDialog(null, "Archivo no configurado o no existe.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file.getName()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Vehiculo vehiculo = parseVehicle(line);
                if (vehiculo != null) {
                    vehiculos.add(vehiculo);
                }
            }
            panel.actualizarTabla(vehiculos); // Actualizar la tabla con los datos cargados
            JOptionPane.showMessageDialog(null, "Vehículos cargados desde el archivo.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo.");
        }
    }

    private Vehiculo parseVehicle(String line) {
        String[] data = line.split(","); // Suponiendo que usaste "," como delimitador en toString

        String tipo = data[0];
        String marca = data[1];
        String modelo = data[2];
        String numeroIdentificacion = data[3];
        int fabricacion = Integer.parseInt(data[4]);

        switch (tipo) {
            case "Moto":
                String tipoDeMotor = data[5];
                boolean esDeportiva = Boolean.parseBoolean(data[6]);
                return new Moto(marca, modelo, fabricacion, numeroIdentificacion, tipoDeMotor, esDeportiva);
            case "Auto":
                int numPuertas = Integer.parseInt(data[5]);
                boolean deportivo = Boolean.parseBoolean(data[6]);
                return new Auto(marca, modelo, fabricacion, numeroIdentificacion, deportivo, numPuertas);
            case "Camion":
                int numeroDePuertas = Integer.parseInt(data[5]);
                double capacidadDeCarga = Double.parseDouble(data[6]);
                return new Camion(marca, modelo, fabricacion, numeroIdentificacion, numeroDePuertas, capacidadDeCarga);
            default:
                return null; // Si no es un tipo válido, retorna null
        }
    }

    public void deleteVehicle(ActionEvent actionEvent) {
        String id = JOptionPane.showInputDialog("Numero de Identificacion de el vehiculo: ");
        if(!idExist(id)){
            JOptionPane.showMessageDialog(null, "El vehiculo no existe");
            return;
        }
        for(Vehiculo vehiculo : vehiculos) {
            if(vehiculo.getNumeroIdentificacion().equals(id)) {
                vehiculos.remove(vehiculo);
                break;
            }
        }
        saveAllVehicles();
        panel.actualizarTabla(vehiculos);
    }

    public Vehiculo getVehicleById() {
        String id = JOptionPane.showInputDialog("Numero de Identificacion de el vehiculo: ");

        if(!idExist(id)){
            JOptionPane.showMessageDialog(null, "El vehiculo no existe");
            return null;
        }
        Vehiculo vehiculo;

        for (Vehiculo v : vehiculos) {
            if (v.getNumeroIdentificacion().equals(id)) {
                vehiculo = v;
                return vehiculo;
            }
        }
        return null;
    }

    public void cargarInfoVehiculo(ActionEvent e) {
        Vehiculo vehiculo = getVehicleById();
        if(vehiculo != null){
            JOptionPane.showMessageDialog(null, vehiculo);
        }
    }

    public void cargarVehiculosEnTabla(){
        panel.actualizarTabla(vehiculos);
    }

    public void generateVehiculos() {
            Moto moto = new Moto(
                    "Toyota",
                    "2020RDS",
                    2020,
                    "1H34",
                    "Motor 1"
            );
            vehiculos.add(moto);
            Moto moto1 = new Moto(
                    "Toyota",
                    "2020RDS",
                    2020,
                    "1234",
                    "Motor 1"
            );
            vehiculos.add(moto1);
    }
}
