#SOLID_Ejercicio2

##📋 Descripción

Sistema de gestión de pedidos para una pizzería online, desarrollado en Java aplicando principios de diseño SOLID y utilizando Swing para la interfaz gráfica.

El sistema permite:

Registro y autenticación de usuarios.
Creación, visualización y cancelación de pedidos de pizza.
Manejo de carritos de compra.
Procesamiento de pagos simulado.
##🏗️ Tecnologías

Java 17
Swing
Maven
JUnit 5
##🧱 Arquitectura

Separación en capas de UI y lógica de negocio.
Aplicación de principios SOLID (Single Responsibility, Dependency Inversion, etc.).
Uso de patrón Factory para la creación de objetos Pizza.
Inyección de dependencias a través de constructores.
##🧪 Pruebas

Se han desarrollado pruebas unitarias utilizando JUnit 5 para:

Registro y autenticación (AuthenticatorTest).
Manejo de pedidos (OrderManagerTest).
Procesamiento de pagos (PaymentProcessorTest).
Manejo del carrito (CartManagerTest).
Ejecutar tests:

mvn test

##Diagrama UML
📄 Autor

SMayomboS
