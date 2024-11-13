public class Auto extends Vehiculo {
    protected int numPuertas;
    protected boolean deportivo;
    public Auto(String marca, String modelo, int fabricacion, String numeroIdentificacion,boolean deportiva, int numPuertas) {
        super(marca,modelo,fabricacion,numeroIdentificacion);
        this.numPuertas = numPuertas;
        this.deportivo = deportiva;
    }
    public Auto(String marca, String modelo, int fabricacion, String numeroIdentificacion,int numPuertas) {
        super(marca,modelo,fabricacion,numeroIdentificacion);
        this.numPuertas = numPuertas;
        this.deportivo = false;
    }
    public int getNumPuertas() {return numPuertas;}
    public void setNumPuertas(int numPuertas) {}
    public boolean isDeportivo(){return deportivo;}
    public void setDeportivo(boolean deportivo){this.deportivo = deportivo;}

    @Override
    public String getTipo() {return "Auto";}
    @Override
    public String toString(){
        return (
                super.toString() + "numero de Puertas: " + numPuertas + "\n" + "Es deportiva: " + (deportivo ? "Si" : "No")
        );
    }
}
