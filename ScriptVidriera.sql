-- ===========================
-- Base de datos: DMS_VIDRIERIA
-- ===========================

CREATE DATABASE IF NOT EXISTS VIDRIERIA;
USE VIDRIERIA;

-- ===========================
-- Tabla: cliente
-- ===========================
CREATE TABLE cliente (
    idCliente INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255),
    telefono VARCHAR(20),
    direccion TEXT,
    email VARCHAR(255),
    RFC VARCHAR(13)
);

-- ===========================
-- Tabla: vendedor
-- ===========================
CREATE TABLE vendedor (
    idVendedor INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255),
    telefono VARCHAR(20),
    email VARCHAR(255)
);

-- ===========================
-- Tabla: proyecto
-- ===========================
CREATE TABLE proyecto (
    idProyecto INT PRIMARY KEY AUTO_INCREMENT,
    estado ENUM('Activo', 'Entregado', 'Cancelado') NOT NULL DEFAULT 'Activo',
    fechaInicio DATE,
    fechaEntregaEstimada DATE,
    idCliente INT,
    FOREIGN KEY (idCliente) REFERENCES cliente(idCliente)
);

-- ===========================
-- Tabla: cotizacion
-- ===========================
CREATE TABLE cotizacion (
    idCotizacion INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATE,
    subtotal DECIMAL(10,2),
    manoObra DECIMAL(10,2),
    iva DECIMAL(10,2),
    descuentoMonto DECIMAL(10,2),
    total DECIMAL(10,2),
    estado ENUM('Pendiente', 'Aceptado', 'Rechazada') NOT NULL DEFAULT 'Pendiente',
    idCliente INT,
    idProyecto INT,
    idVendedor INT,
    FOREIGN KEY (idCliente) REFERENCES cliente(idCliente),
    FOREIGN KEY (idProyecto) REFERENCES proyecto(idProyecto),
    FOREIGN KEY (idVendedor) REFERENCES vendedor(idVendedor)
);

-- ===========================
-- Tabla: catalogotrabajos
-- ===========================
CREATE TABLE catalogotrabajos (
    idCatalogo INT PRIMARY KEY AUTO_INCREMENT,
    codigoInterno VARCHAR(50),
    nombre VARCHAR(255),
    descripcion TEXT,
    serieBase VARCHAR(100),
    precioBase DECIMAL(10,2)
);

-- ===========================
-- Tabla: ventanadetalle
-- ===========================
CREATE TABLE ventanadetalle (
    idVentanaDetalle INT PRIMARY KEY AUTO_INCREMENT,
    id_tipo_trabajo INT,
    id_cotizacion INT,
    medidaHorizontal DECIMAL(10,2),
    medidaVertical DECIMAL(10,2),
    cantidad INT,
    tipoCristal VARCHAR(100),
    noHojas INT,
    precioSoloUnaUnidadCalculado DECIMAL(10,2),
    subtotalLinea DECIMAL(10,2),
    descripcion TEXT,
    tipoVentana ENUM('1 ½"', '2"', '3 7/8"') NOT NULL, 
    mosquitero BOOLEAN NOT NULL DEFAULT FALSE,
    arco BOOLEAN NOT NULL DEFAULT FALSE,
    tipoArco VARCHAR(100),
    medidaArco DECIMAL(10,2),
    tipoCanalillo VARCHAR(100),
    medidaCanalillo DECIMAL(10,2),
    FOREIGN KEY (id_cotizacion) REFERENCES cotizacion(idCotizacion),
    FOREIGN KEY (id_tipo_trabajo) REFERENCES catalogotrabajos(idCatalogo)
);

-- ===========================
-- Tabla: detalle_puertaabatible
-- ===========================
CREATE TABLE detalle_puertaabatible (
    id_detalle_puerta INT PRIMARY KEY AUTO_INCREMENT,
    id_cotizacion INT,
    id_tipo_trabajo INT,
    medidaHorizontal DECIMAL(10,2),
    medidaVertical DECIMAL(10,2),
    cantidad INT,
    tipoCristal VARCHAR(100),
    noHojas INT,
    precioSoloUnaUnidadCalculado DECIMAL(10,2),
    subtotalLinea DECIMAL(10,2),
    descripcion TEXT,
    tipo_puerta ENUM('SERIE_1751', 'LINEA_PESADA'),
    mosquitero TINYINT(1),
    duela TINYINT(1),
    tipo_duela VARCHAR(50),
    medida_duela DECIMAL(8,2),
    adaptador TINYINT(1),
    tipo_adaptador VARCHAR(50),
    junquillo TINYINT(1),
    tipo_junquillo VARCHAR(50),
    canal TINYINT(1),
    tipo_canal VARCHAR(50),
    pivote BOOLEAN NOT NULL DEFAULT FALSE,
    tipo_pivote VARCHAR(50),
    cantidad_pivote INT,
    jaladera TINYINT(1),
    tipo_jaladera VARCHAR(50),
    cantidad_jaladera INT,
    barra TINYINT(1),
    tipo_barra VARCHAR(50),
    FOREIGN KEY (id_cotizacion) REFERENCES cotizacion(idCotizacion),
    FOREIGN KEY (id_tipo_trabajo) REFERENCES catalogotrabajos(idCatalogo)
);

-- ===========================
-- Tabla: canceleriafijadetalle
-- ===========================
CREATE TABLE canceleriafijadetalle (
    idCanceleriaDetalle INT PRIMARY KEY AUTO_INCREMENT,
    id_tipo_trabajo INT,
    id_cotizacion INT,
    medidaHorizontal DECIMAL(10,2),
    medidaVertical DECIMAL(10,2),
    cantidad INT,
    tipoCristal VARCHAR(100),
    noHojas INT,
    precioSoloUnaUnidadCalculado DECIMAL(10,2),
    subtotalLinea DECIMAL(10,2),
    descripcion TEXT,
    tipoCanceleria ENUM('2"', '3"'),
    bolsa TINYINT(1),
    numFijosVerticales INT,
    numFijosHorizontales INT,
    tipoTapa VARCHAR(100),
    cantidadTapa INT,
    zoclo TINYINT(1),
    tipoZoclo VARCHAR(100),
    junquillo TINYINT(1),
    tipoJunquillo VARCHAR(100),
    arco TINYINT(1),
    tipoArco VARCHAR(100),
    medidaArco DECIMAL(10,2),
    canalillo TINYINT(1),
    tipoCanalillo VARCHAR(100),
    medidaCanalillo DECIMAL(10,2),
    FOREIGN KEY (id_tipo_trabajo) REFERENCES catalogotrabajos(idCatalogo),
    FOREIGN KEY (id_cotizacion) REFERENCES cotizacion(idCotizacion)
);

-- ===========================
-- Tabla: material
-- ===========================
CREATE TABLE material (
    idMaterial INT PRIMARY KEY AUTO_INCREMENT,
    descripcion TEXT,
    precio DECIMAL(10,2),
    stockActual INT,
    tipo ENUM('Vidrio', 'Aluminio', 'Accesorio') NOT NULL,
    ventanadetalle_idVentanaDetalle INT,
    detalle_puertaabatible_id_detalle_puerta INT,
    canceleriafijadetalle_idCanceleriaDetalle INT,
    FOREIGN KEY (ventanadetalle_idVentanaDetalle) REFERENCES ventanadetalle(idVentanaDetalle),
    FOREIGN KEY (detalle_puertaabatible_id_detalle_puerta) REFERENCES detalle_puertaabatible(id_detalle_puerta),
    FOREIGN KEY (canceleriafijadetalle_idCanceleriaDetalle) REFERENCES canceleriafijadetalle(idCanceleriaDetalle)
);

-- ===========================
-- Tabla: inventariomovimiento
-- ===========================
CREATE TABLE inventariomovimiento (
    idMovimiento INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATE,
    cantidad INT,
    tipoMovimiento ENUM('Entrada', 'Salida') NOT NULL,
    motivo ENUM('Compra', 'Proyecto', 'Desperdicio') NOT NULL,
    idMaterial INT,
    FOREIGN KEY (idMaterial) REFERENCES material(idMaterial)
);

-- ===========================
-- Tabla: ordencompra
-- ===========================
CREATE TABLE ordencompra (
    idOrdenCompra INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATE,
    total DECIMAL(10,2)
);

-- ===========================
-- Tabla: material_ordencompra
-- ===========================
CREATE TABLE material_ordencompra (
    idOrdenCompra INT,
    idMaterial INT,
    cantidad INT,
    precioUnitario DECIMAL(10,2),
    PRIMARY KEY (idOrdenCompra, idMaterial),
    FOREIGN KEY (idOrdenCompra) REFERENCES ordencompra(idOrdenCompra),
    FOREIGN KEY (idMaterial) REFERENCES material(idMaterial)
);

-- ===========================
-- Tabla: proveedor
-- ===========================
CREATE TABLE proveedor (
    idProveedor INT PRIMARY KEY AUTO_INCREMENT,
    nombreEmpresa VARCHAR(255),
    contacto VARCHAR(255),
    telefono VARCHAR(20),
    email VARCHAR(255),
    ordencompra_idOrdenCompra INT,
    FOREIGN KEY (ordencompra_idOrdenCompra) REFERENCES ordencompra(idOrdenCompra)
);

-- ===========================
-- Tabla: ordentrabajo
-- ===========================
CREATE TABLE ordentrabajo (
    idOrdenTrabajo INT PRIMARY KEY AUTO_INCREMENT,
    fechaEntregaTaller DATE,
    medidasFinales TEXT,
    idProyecto INT,
    FOREIGN KEY (idProyecto) REFERENCES proyecto(idProyecto)
);

-- ===========================
-- Tabla: pago
-- ===========================
CREATE TABLE pago (
    idPago INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATE,
    monto DECIMAL(10,2),
    tipo ENUM('Anticipo', 'Final') NOT NULL,
    idProyecto INT,
    FOREIGN KEY (idProyecto) REFERENCES proyecto(idProyecto)
);


-- ===========================
-- Inserts para pruebas
-- ===========================
INSERT INTO cliente (nombre, telefono, direccion, email, RFC)
VALUES ('Juan Pérez', '1234567890', 'Calle 123', 'juan@email.com', 'RFC123');

INSERT INTO vendedor (nombre, telefono, email)
VALUES ('Ana Gómez', '0987654321', 'ana@email.com');

INSERT INTO catalogotrabajos (codigoInterno, nombre, descripcion, serieBase, precioBase)
VALUES
('TRB-001', 'Instalación de ventana', 'Incluye medición, corte y colocación del marco de aluminio.', 'SERIE-A', 500.00),
('TRB-002', 'Reparación de ventana', 'Sustitución de vidrios o componentes dañados.', 'SERIE-B', 350.00),
('TRB-003', 'Mantenimiento de cancelería', 'Limpieza, lubricación y ajuste de cancelería.', 'SERIE-C', 250.00);
