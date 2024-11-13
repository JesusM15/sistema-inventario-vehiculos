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

    @Override
    public String toCSVFormat() {
        return super.toCSVFormat() + String.format(",%s,%b", tipoDeMotor, deportiva);
    }

    public void setDeportiva(boolean deportiva) {
        this.deportiva = deportiva;
    }

    public void mostrarInformacion(){
        super.mostrarInformacion();
        System.out.println("Tipo de motor: " + tipoDeMotor);
        System.out.println("Es Deportiva: " + (deportiva ? "Si" : "No"));
    }

    @Override
    public String getTipo() {
        return "Moto";
    }

    @Override
    public String toString(){
        return (
                super.toString() + "Tipo de motor: " + tipoDeMotor + "\n" + "Es deportiva: " + (deportiva ? "Si" : "No")
                );
    }
}
