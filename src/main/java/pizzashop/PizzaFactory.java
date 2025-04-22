package pizzashop;

public class PizzaFactory {

    public static Pizza createPizza(String tipo, String tamaño) {
        double precio;

        switch (tipo) {
            case "Margarita":
                precio = switch (tamaño) {
                    case "Pequeña" -> 5.0;
                    case "Mediana" -> 7.0;
                    case "Grande" -> 9.0;
                    default -> throw new IllegalArgumentException("Tamaño no válido: " + tamaño);
                };
                break;
            case "Pepperoni":
                precio = switch (tamaño) {
                    case "Pequeña" -> 6.0;
                    case "Mediana" -> 8.5;
                    case "Grande" -> 11.0;
                    default -> throw new IllegalArgumentException("Tamaño no válido: " + tamaño);
                };
                break;
            case "4 Quesos":
                precio = switch (tamaño) {
                    case "Pequeña" -> 6.5;
                    case "Mediana" -> 9.0;
                    case "Grande" -> 12.0;
                    default -> throw new IllegalArgumentException("Tamaño no válido: " + tamaño);
                };
                break;
            default:
                throw new IllegalArgumentException("Tipo de pizza no válido: " + tipo);
        }

        return new Pizza(tipo, tamaño, precio);
    }
}