package pizzashop;

public class Pizza {
    private final String tipo;
    private final String tamaño;
    private final double precio;

    public Pizza(String tipo, String tamaño, double precio) {
        this.tipo = tipo;
        this.tamaño = tamaño;
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTamaño() {
        return tamaño;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return tipo + " (" + tamaño + ") - " + precio + "€";
    }
}
