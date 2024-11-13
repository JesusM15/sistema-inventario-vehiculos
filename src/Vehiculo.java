public class Vehiculo {
    protected String marca;
    protected String modelo;
    protected int fabricacion;
    protected String numeroIdentificacion;

    public Vehiculo(String marca, String modelo, int fabricacion, String numeroIdentificacion) {
        this.marca = marca;
        this.modelo = modelo;
        this.fabricacion = fabricacion;
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String toCSVFormat() {
        return String.format("%s,%s,%s,%s,%d", getTipo(), marca, modelo, numeroIdentificacion, fabricacion);
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getFabricacion() {
        return fabricacion;
    }

    public void setFabricacion(int fabricacion) {
        this.fabricacion = fabricacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public void mostrarInformacion() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Año de fabricacion: " + fabricacion);
        System.out.println("Numero Identificacion: " + numeroIdentificacion);
    }

    public String toString() {
        return (
                "Tipo: " + getTipo() + "\n" +
                "Marca: " + marca + "\n" +
                "Modelo: " + modelo + "\n" + "Año de fabricacion: " + fabricacion + "\n"+
                        "Numero de Identificacion: " + numeroIdentificacion + "\n"
        );
    }

    public String getTipo(){
        return "Auto";
    }
}
