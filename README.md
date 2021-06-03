# Prácticas Sistemas Distribuidos

### Práctica 1: 
* Ejercicio 1: Programar el servidor central de una aplicación de mensajería para smartphones.
* Ejercicio 2: Programar una cadena de producción con 10 robots manipuladores que estén conectados en anillo.
* Ejercicio 3: Programar una sistema distribuido basado en multiservidor que desencripte mensajes

### Práctica 2: 
El objetivo de la práctica es crear un servidor que devuelva imágenes procesadas con diferentes filtros, para ello se recurrirá a un servidor CORBA que devolverá a un cliente en JAVA un fichero con la imagen inicial filtrada. 

### Trabajo Final:
Debe implementar un servicio de filtros de imagen para una red social. La finalidad del sistema es evitar la sobrecarga del terminal cliente para alargar la vida útil de
las baterías y reducir el consumo.
* SISTEMA A: Utilizará los recursos compartidos de la empresa como si se tratase de una red peer-to-peer. Cualquier nodo podrá procesar cualquier filtro. No obstante, los códigos no estarán almacenados en los nodos de la red peer-to-peer (código móvil).
* SISTEMA B: Utilizará cada parte del sistema de manera dedicada, es decir, cada nodo contendrá el código del filtro a implementar y siempre ejecutará los mismos filtros.
