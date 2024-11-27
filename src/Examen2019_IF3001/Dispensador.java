package Examen2019_IF3001;

import javax.swing.JTextArea;

public class Dispensador {
    String nombrePistero;
    String dni;
    Vehiculo cabeza;
    int totalVehiculos;
    double totalSuper, totalPlus, totalDiesel;
    double montoSuper, montoPlus, montoDiesel;

    public Dispensador(String nombrePistero, String dni) {
        this.nombrePistero = nombrePistero;
        this.dni = dni;
        this.cabeza = null;
        this.totalVehiculos = 0;
        this.totalSuper = this.totalPlus = this.totalDiesel = 0;
        this.montoSuper = this.montoPlus = this.montoDiesel = 0;
    }

    public void insertarVehiculo(Vehiculo nuevo) {
        if (cabeza == null) {
            cabeza = nuevo;
            cabeza.siguiente = cabeza;
        } else {
            Vehiculo actual = cabeza;
            while (actual.siguiente != cabeza) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
            nuevo.siguiente = cabeza;
        }
        totalVehiculos++;
        actualizarEstadisticas(nuevo);
    }

    public Vehiculo buscarVehiculo(String placa) {
        if (cabeza == null) return null;
        Vehiculo actual = cabeza;
        do {
            if (actual.placa.equals(placa)) return actual;
            actual = actual.siguiente;
        } while (actual != cabeza);
        return null;
    }

    public boolean eliminarVehiculo(String placa) {
        if (cabeza == null) return false;

        Vehiculo actual = cabeza;
        Vehiculo anterior = null;
        do {
            if (actual.placa.equals(placa)) {
                if (actual == cabeza && cabeza.siguiente == cabeza) {
                    cabeza = null;
                } else {
                    if (actual == cabeza) {
                        Vehiculo temp = cabeza;
                        while (temp.siguiente != cabeza) {
                            temp = temp.siguiente;
                        }
                        cabeza = cabeza.siguiente;
                        temp.siguiente = cabeza;
                    } else {
                        anterior.siguiente = actual.siguiente;
                    }
                }
                totalVehiculos--;
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        } while (actual != cabeza);
        return false;
    }

    public void mostrarVehiculos(JTextArea textArea) {
        if (cabeza == null) {
            textArea.append("No hay vehículos en este dispensador.\n");
            return;
        }
        Vehiculo actual = cabeza;
        do {
            textArea.append(actual.toString() + "\n");
            actual = actual.siguiente;
        } while (actual != cabeza);
    }

    private void actualizarEstadisticas(Vehiculo vehiculo) {
        switch (vehiculo.tipoCombustible) {
            case "Super":
                totalSuper += vehiculo.litros;
                montoSuper += vehiculo.montoPagar;
                break;
            case "Plus":
                totalPlus += vehiculo.litros;
                montoPlus += vehiculo.montoPagar;
                break;
            case "Diesel":
                totalDiesel += vehiculo.litros;
                montoDiesel += vehiculo.montoPagar;
                break;
        }
    }

    public void realizarCorte(JTextArea textArea) {
        textArea.append("Corte para dispensador de " + nombrePistero + " (" + dni + "):\n");
        textArea.append("Total Vehículos Atendidos: " + totalVehiculos + "\n");
        textArea.append("Total Litros - Súper: " + totalSuper + "L, Plus: " + totalPlus + "L, Diesel: " + totalDiesel + "L\n");
        textArea.append("Total Dinero - Súper: " + montoSuper + "₡, Plus: " + montoPlus + "₡, Diesel: " + montoDiesel + "₡\n");
        textArea.append("TOTAL GENERAL: Litros: " + (totalSuper + totalPlus + totalDiesel) +
                "L, Dinero: " + (montoSuper + montoPlus + montoDiesel) + "₡\n\n");

        totalVehiculos = 0;
        totalSuper = totalPlus = totalDiesel = 0;
        montoSuper = montoPlus = montoDiesel = 0;
    }
}
