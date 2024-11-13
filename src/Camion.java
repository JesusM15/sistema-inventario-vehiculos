public class Camion extends Vehiculo{
    public int numeroDePuertas;
    public double capacidadDeCarga;
    public Camion(String marca, String modelo,int fabricacion, String numeroIdentificacion, int numeroDePuertas, double capacidad) {
        super(marca,modelo,fabricacion,numeroIdentificacion);
        this.numeroDePuertas = numeroDePuertas;
        this.capacidadDeCarga = capacidad;
    }
    public int getNumeroDePuertas() {return numeroDePuertas;}
    public double getCapacidadDeCarga() {return capacidadDeCarga;}
    public void setNumeroDePuertas(int numeroDePuertas) {
        this.numeroDePuertas = numeroDePuertas;
    }
    public void setCapacidadDeCarga(double capacidadDeCarga) {
        this.capacidadDeCarga = capacidadDeCarga;
    }
    @Override
    public String getTipo(){
        return "Camion";
    }
    @Override
    public String toString(){
        return (
        super.toString()+" numero de puertas: "+numeroDePuertas+" capacidad: "+capacidadDeCarga+"\n"
        );
    }
}
