Pizzería en Línea - Sistema de Gestión de Pedidos

🌟 Descripción del Proyecto

Este proyecto es un Sistema de Gestión de Pedidos de Pizza en Línea desarrollado en Java utilizando Swing para la interfaz gráfica. El objetivo es permitir a los usuarios:

Registrarse y autenticarse en el sistema.

Seleccionar pizzas de diferentes tipos y tamaños.

Añadir pizzas a un carrito de compras.

Finalizar y pagar pedidos mediante diferentes métodos de pago.

Cancelar pedidos activos antes del pago.

Gestionar usuarios, pedidos y pagos de forma modular.

La aplicación está construida siguiendo principios de inyección de dependencias y principios SOLID.

🔧 Tecnologías utilizadas

Java 17+

Swing para la interfaz gráfica

Maven como sistema de construcción

JUnit 5 para pruebas unitarias

🔎 Estructura del Proyecto

PizzeriaOnline/
├── src/
│   ├── main/java/pizzashop/
│   │   ├── MainUI.java
│   │   ├── Authenticator.java
│   │   ├── DataBaseManager.java
│   │   ├── OrderManager.java
│   │   ├── PaymentProcessor.java
│   │   ├── CartManager.java
│   │   ├── Pizza.java
│   │   ├── Pedido.java
│   ├── test/java/pizzashop/
│       ├── AuthenticatorTest.java
│       ├── DataBaseManagerTest.java
│       ├── OrderManagerTest.java
│       ├── PaymentProcessorTest.java
│       ├── CartManagerTest.java
├── pom.xml
└── README.md

🔬 Pruebas Unitarias

Se han desarrollado pruebas unitarias utilizando JUnit 5 para verificar el correcto funcionamiento de:

Authenticator: Registro y autenticación de usuarios.

DataBaseManager: Guardado de usuarios y pedidos.

OrderManager: Creación y cancelación de pedidos.

PaymentProcessor: Procesamiento de pagos simulados.

CartManager: Gestión del carrito de compras (añadir, calcular total, vaciar).

Ejecución de las Pruebas

Desde la terminal, ubicándote en el directorio del proyecto:

mvn test

Maven ejecutará todas las pruebas automáticamente y mostrará un resumen indicando si todas pasaron correctamente.

✅ Consideraciones Finales

El código sigue principios de modularidad y responsabilidad única.

El patrón de inyección de dependencias se aplica a Authenticator, OrderManager, y PaymentProcessor.

El sistema está preparado para escalar, permitiendo futuras extensiones como historial de pedidos o integración de bases de datos reales.

📚 Autor

Proyecto realizado por [Tu Nombre].
