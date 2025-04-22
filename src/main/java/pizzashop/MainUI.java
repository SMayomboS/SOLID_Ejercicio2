package pizzashop;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class MainUI extends JFrame {
    private final Authenticator authenticator;
    private final OrderManager orderManager;
    private final CartManager cartManager;
    private final CardLayout cardLayout;
    private final JPanel mainPanel;

    private final Map<String, Map<String, Double>> catalogoPizzas;

    private JTextField loginUsernameField;
    private JPasswordField loginPasswordField;
    private JTextField registerUsernameField;
    private JPasswordField registerPasswordField;

    public MainUI(Authenticator authenticator, OrderManager orderManager, CartManager cartManager) {
        this.authenticator = authenticator;
        this.orderManager = orderManager;
        this.cartManager = cartManager;

        catalogoPizzas = crearCatalogo();

        setTitle("Pizzería en Línea");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createWelcomePanel(), "welcome");
        mainPanel.add(createLoginPanel(), "login");
        mainPanel.add(createRegisterPanel(), "register");
        mainPanel.add(createMenuPanel(), "menu");
        mainPanel.add(createPizzaSelectionPanel(), "pizzaSelection");
        mainPanel.add(createCartPanel(), "cart");

        add(mainPanel);
        cardLayout.show(mainPanel, "welcome");
    }

    private Map<String, Map<String, Double>> crearCatalogo() {
        Map<String, Map<String, Double>> catalogo = new HashMap<>();
        catalogo.put("Margarita", Map.of("Pequeña", 5.0, "Mediana", 7.0, "Grande", 9.0));
        catalogo.put("Pepperoni", Map.of("Pequeña", 6.0, "Mediana", 8.5, "Grande", 11.0));
        catalogo.put("4 Quesos", Map.of("Pequeña", 6.5, "Mediana", 9.0, "Grande", 12.0));
        return catalogo;
    }

    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JButton loginButton = new JButton("Iniciar Sesión");
        JButton registerButton = new JButton("Registrarse");

        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(loginButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(registerButton);

        loginButton.addActionListener(e -> cardLayout.show(mainPanel, "login"));
        registerButton.addActionListener(e -> cardLayout.show(mainPanel, "register"));

        return panel;
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        loginUsernameField = new JTextField(15);
        loginPasswordField = new JPasswordField(15);
        JButton loginButton = new JButton("Iniciar Sesión");
        JButton backButton = new JButton("Volver");

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Usuario:"), gbc);
        gbc.gridx = 1; panel.add(loginUsernameField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1; panel.add(loginPasswordField, gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(loginButton, gbc);
        gbc.gridy = 3; panel.add(backButton, gbc);

        loginButton.addActionListener(e -> {
            String username = loginUsernameField.getText().trim();
            String password = new String(loginPasswordField.getPassword()).trim();
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Usuario y contraseña no pueden estar vacíos.");
                return;
            }
            if (authenticator.authenticate(username, password)) {
                JOptionPane.showMessageDialog(null, "¡Login exitoso!");
                cardLayout.show(mainPanel, "menu");
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
            }
        });

        backButton.addActionListener(e -> {
            clearLoginFields();
            cardLayout.show(mainPanel, "welcome");
        });

        return panel;
    }

    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        registerUsernameField = new JTextField(15);
        registerPasswordField = new JPasswordField(15);
        JButton registerButton = new JButton("Registrarse");
        JButton backButton = new JButton("Volver");

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Nuevo Usuario:"), gbc);
        gbc.gridx = 1; panel.add(registerUsernameField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1; panel.add(registerPasswordField, gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(registerButton, gbc);
        gbc.gridy = 3; panel.add(backButton, gbc);

        registerButton.addActionListener(e -> {
            String username = registerUsernameField.getText().trim();
            String password = new String(registerPasswordField.getPassword()).trim();
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Usuario y contraseña no pueden estar vacíos.");
                return;
            }
            if (authenticator.register(username, password)) {
                JOptionPane.showMessageDialog(null, "¡Usuario registrado exitosamente!");
                clearRegisterFields();
                cardLayout.show(mainPanel, "login");
            } else {
                JOptionPane.showMessageDialog(null, "El usuario ya existe.");
            }
        });

        backButton.addActionListener(e -> {
            clearRegisterFields();
            cardLayout.show(mainPanel, "welcome");
        });

        return panel;
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JButton selectPizzaButton = new JButton("Seleccionar Pizzas");
        JButton viewCartButton = new JButton("Ver Carrito y Pagar");
        JButton cancelButton = new JButton("Cancelar Pedido");
        JButton logoutButton = new JButton("Cerrar Sesión");

        selectPizzaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(selectPizzaButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(viewCartButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(cancelButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(logoutButton);

        selectPizzaButton.addActionListener(e -> cardLayout.show(mainPanel, "pizzaSelection"));
        viewCartButton.addActionListener(e -> cardLayout.show(mainPanel, "cart"));

        cancelButton.addActionListener(e -> {
            if (!cartManager.getCarrito().isEmpty()) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres cancelar el pedido?", "Cancelar Pedido", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (orderManager.hasCurrentOrder()) {
                        orderManager.cancelOrder();
                    } else {
                        cartManager.vaciarCarrito();
                    }
                    updateCartArea();
                    JOptionPane.showMessageDialog(null, "Pedido cancelado y carrito vaciado.");
                    cardLayout.show(mainPanel, "menu");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El carrito ya está vacío.");
            }
        });

        logoutButton.addActionListener(e -> {
            cartManager.vaciarCarrito();
            cardLayout.show(mainPanel, "welcome");
        });

        return panel;
    }

    private JPanel createPizzaSelectionPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JComboBox<String> tipoBox = new JComboBox<>(catalogoPizzas.keySet().toArray(new String[0]));
        JComboBox<String> tamañoBox = new JComboBox<>(new String[]{"Pequeña", "Mediana", "Grande"});
        JButton addButton = new JButton("Añadir al Carrito");
        JButton backButton = new JButton("Volver al Menú");

        panel.add(new JLabel("Selecciona tipo de pizza:"));
        panel.add(tipoBox);
        panel.add(new JLabel("Selecciona tamaño:"));
        panel.add(tamañoBox);
        panel.add(addButton);
        panel.add(backButton);

        addButton.addActionListener(e -> {
            String tipo = (String) tipoBox.getSelectedItem();
            String tamaño = (String) tamañoBox.getSelectedItem();
            if (catalogoPizzas.containsKey(tipo) && catalogoPizzas.get(tipo).containsKey(tamaño)) {
                double precio = catalogoPizzas.get(tipo).get(tamaño);
                cartManager.añadirPizza(new Pizza(tipo, tamaño, precio));
                JOptionPane.showMessageDialog(null, "Pizza añadida al carrito.");
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "menu"));

        return panel;
    }

    private JPanel createCartPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea cartArea = new JTextArea();
        JButton payButton = new JButton("Finalizar y Pagar");
        JButton backButton = new JButton("Volver al Menú");

        cartArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(cartArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(payButton);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        payButton.addActionListener(e -> {
            List<Pizza> carrito = new ArrayList<>(cartManager.getCarrito());
            if (carrito.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El carrito está vacío.");
            } else {
                String[] opciones = {"Tarjeta", "PayPal", "Efectivo"};
                String metodo = (String) JOptionPane.showInputDialog(null, "Selecciona método de pago:", "Pago", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                double total = cartManager.calcularTotal();
                if (metodo != null) {
                    Pedido pedido = orderManager.createOrder(carrito, total);
                    JOptionPane.showMessageDialog(null, "Pago realizado con éxito por " + total + "€ mediante " + metodo);
                    cartManager.vaciarCarrito();
                    cardLayout.show(mainPanel, "menu");
                }
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "menu"));

        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                StringBuilder builder = new StringBuilder();
                for (Pizza pizza : cartManager.getCarrito()) {
                    builder.append(pizza).append("\n");
                }
                builder.append("Total: ").append(cartManager.calcularTotal()).append("€");
                cartArea.setText(builder.toString());
            }
        });

        return panel;
    }

    private void clearLoginFields() {
        loginUsernameField.setText("");
        loginPasswordField.setText("");
    }

    private void clearRegisterFields() {
        registerUsernameField.setText("");
        registerPasswordField.setText("");
    }

    private void updateCartArea() {
        JTextArea cartArea = findCartArea(mainPanel);
        if (cartArea != null) {
            StringBuilder builder = new StringBuilder();
            for (Pizza pizza : cartManager.getCarrito()) {
                builder.append(pizza).append("\n");
            }
            builder.append("Total: ").append(cartManager.calcularTotal()).append("€");
            cartArea.setText(builder.toString());
        }
    }

    private JTextArea findCartArea(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JTextArea) {
                return (JTextArea) comp;
            } else if (comp instanceof Container) {
                JTextArea found = findCartArea((Container) comp);
                if (found != null) return found;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        DataBaseManager dbManager = new DataBaseManager();
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        CartManager cartManager = new CartManager();
        Authenticator authenticator = new Authenticator(dbManager);
        OrderManager orderManager = new OrderManager(dbManager, paymentProcessor, cartManager);

        SwingUtilities.invokeLater(() -> {
            MainUI ui = new MainUI(authenticator, orderManager, cartManager);
            ui.setVisible(true);
        });
    }
}
