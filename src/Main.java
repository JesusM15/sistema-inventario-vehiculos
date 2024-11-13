import javax.swing.*;
import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        char opc;
        do{
            opc = JOptionPane.showInputDialog(null,
                    "a) Iniciar nuevo inventario\n" +
                            "b) Cargar Inventario desde un archivo"
            ).charAt(0);

        }while(opc != 'b' && opc != 'a');

        CRUD crud = new CRUD();
        String filename;
        File file;
        if (opc == 'b'){
            filename = JOptionPane.showInputDialog("Escriba el nombre del archivo a cargar");
            file = new File(filename);

            if(file.exists()){
                crud.setFile(file);
                crud.loadVehiclesFromFile();
                crud.iniciarCRUD();
            }
        }else{
            do{
                filename = JOptionPane.showInputDialog("Escriba el nombre del archivo donde se guardara el contenido");
                file = new File(filename);

                if(file.exists()){
                    JOptionPane.showMessageDialog(null, "Este archivo ya existe, escriba otro nombre");
                }
            }while(file.exists());

            crud.setFile(file);
            crud.iniciarCRUD();
        }

    }
}