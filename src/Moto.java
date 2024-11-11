public class Moto extends Vehiculo{
    private String tipoDeMotor;
    private boolean deportiva;

    public Moto(String marca, String modelo, int fabricacion, String numeroIdentificacion, String tipoDeMotor, boolean deportiva) {
        super(marca, modelo, fabricacion, numeroIdentificacion);
        this.tipoDeMotor = tipoDeMotor;
        this.deportiva = deportiva;
    }

    public Moto(String marca, String modelo, int fabricacion, String numeroIdentificacion, String tipoDeMotor) {
        super(marca, modelo, fabricacion, numeroIdentificacion);
        this.tipoDeMotor = tipoDeMotor;
        this.deportiva = false;
    }
    public String getTipoDeMotor() {
        return tipoDeMotor;
    }

    public void setTipoDeMotor(String tipoDeMotor) {
        this.tipoDeMotor = tipoDeMotor;
    }

    public boolean isDeportiva() {
        return deportiva;
    }

    public void setDeportiva(boolean deportiva) {
        this.deportiva = deportiva;
    }

    public void mostrarInformacion(){
        super.mostrarInformacion();
        System.out.println("Tipo de motor: " + tipoDeMotor);
        System.out.println("Es Deportiva: " + (deportiva ? "Si" : "No"));
    }
}
