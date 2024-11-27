package Examen2019_IF3001;

public class Vehiculo {

    String placa;
    String marca;
    int año;
    String tipoCombustible; // "Super", "Plus", "Diesel"
    double litros;
    double montoPagar;
    Vehiculo siguiente; // Enlace para lista circular

    public Vehiculo(String placa, String marca, int año, String tipoCombustible, double litros, double precioPorLitro) {
        this.placa = placa;
        this.marca = marca;
        this.año = año;
        this.tipoCombustible = tipoCombustible;
        this.litros = litros;
        this.montoPagar = litros * precioPorLitro;
        this.siguiente = null; // Inicialmente no tiene enlace
    }

    @Override
    public String toString() {
        return "Placa: " + placa + ", Marca: " + marca + ", Año: " + año +
               ", Tipo Combustible: " + tipoCombustible + ", Litros: " + litros +
               ", Monto a Pagar: " + montoPagar;
    }
}
