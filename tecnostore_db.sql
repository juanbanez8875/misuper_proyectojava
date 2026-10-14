-- TecnoStore: creacion de base de datos y tablas
-- Ejecutar este archivo en MySQL Workbench.

CREATE DATABASE IF NOT EXISTS tecnostore_db;
USE tecnostore_db;

CREATE TABLE IF NOT EXISTS celulares (
    id INT PRIMARY KEY,
    marca VARCHAR(60) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    precio DECIMAL(12,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    sistema_operativo VARCHAR(50) NOT NULL,
    gama ENUM('ALTA', 'MEDIA', 'BAJA') NOT NULL,
    CONSTRAINT chk_celulares_precio CHECK (precio > 0),
    CONSTRAINT chk_celulares_stock CHECK (stock >= 0)
);

CREATE TABLE IF NOT EXISTS clientes (
    id INT PRIMARY KEY,
    nombre VARCHAR(120) NOT NULL,
    identificacion VARCHAR(30) NOT NULL UNIQUE,
    correo VARCHAR(120) NOT NULL UNIQUE,
    telefono VARCHAR(25) NOT NULL
);

CREATE TABLE IF NOT EXISTS ventas (
    id INT PRIMARY KEY,
    cliente_id INT NOT NULL,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    subtotal DECIMAL(12,2) NOT NULL,
    iva DECIMAL(12,2) NOT NULL,
    total DECIMAL(12,2) NOT NULL,
    CONSTRAINT fk_ventas_cliente
        FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

CREATE TABLE IF NOT EXISTS detalle_ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    venta_id INT NOT NULL,
    celular_id INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(12,2) NOT NULL,
    subtotal DECIMAL(12,2) NOT NULL,
    CONSTRAINT fk_detalle_venta
        FOREIGN KEY (venta_id) REFERENCES ventas(id),
    CONSTRAINT fk_detalle_celular
        FOREIGN KEY (celular_id) REFERENCES celulares(id),
    CONSTRAINT chk_detalle_cantidad CHECK (cantidad > 0),
    CONSTRAINT chk_detalle_precio CHECK (precio_unitario > 0),
    CONSTRAINT chk_detalle_subtotal CHECK (subtotal >= 0)
);

-- Verificacion final
SHOW TABLES;
DESCRIBE celulares;
DESCRIBE clientes;
DESCRIBE ventas;
DESCRIBE detalle_ventas;
