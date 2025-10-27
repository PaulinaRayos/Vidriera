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
    estado ENUM('Pendiente', 'Aceptado', 'Cancelada') NOT NULL DEFAULT 'Pendiente',
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
    tipo ENUM('Vidrio', 'Aluminio', 'Accesorio') NOT NULL
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
-- Tablas: Detalles a materiales
-- ===========================
CREATE TABLE VentanaDetalle_Material (
    idVentanaDetalle INT NOT NULL,
    idMaterial INT NOT NULL,
    cantidad DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (idVentanaDetalle, idMaterial),
    FOREIGN KEY (idVentanaDetalle) REFERENCES VentanaDetalle(idVentanaDetalle) ON DELETE CASCADE,
    FOREIGN KEY (idMaterial) REFERENCES Material(idMaterial) ON DELETE CASCADE
);

CREATE TABLE CanceleriaFijaDetalle_Material (
    idCanceleriaDetalle INT NOT NULL,
    idMaterial INT NOT NULL,
    cantidad DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (idCanceleriaDetalle, idMaterial),
    FOREIGN KEY (idCanceleriaDetalle) REFERENCES CanceleriaFijaDetalle(idCanceleriaDetalle) ON DELETE CASCADE,
    FOREIGN KEY (idMaterial) REFERENCES Material(idMaterial) ON DELETE CASCADE
);

CREATE TABLE PuertaAbatible_Material (
    id_detalle_puerta INT NOT NULL,
    idMaterial INT NOT NULL,
    cantidad DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id_detalle_puerta, idMaterial),
    FOREIGN KEY (id_detalle_puerta) REFERENCES Detalle_PuertaAbatible(id_detalle_puerta) ON DELETE CASCADE,
    FOREIGN KEY (idMaterial) REFERENCES Material(idMaterial) ON DELETE CASCADE
);



-- =========================================
-- Insertar Clientes
-- =========================================
INSERT INTO cliente (nombre, telefono, direccion, email, RFC) VALUES
('Juan Pérez', '5551234567', 'Calle Falsa 123', 'juanperez@mail.com', 'JUAP750101XXX'),
('María López', '5559876543', 'Av. Siempre Viva 742', 'marialopez@mail.com', 'MALO880202XXX');

-- =========================================
-- Insertar Vendedores
-- =========================================
INSERT INTO vendedor (nombre, telefono, email) VALUES
('Carlos Gómez', '5551112222', 'carlosg@mail.com'),
('Ana Torres', '5553334444', 'anatorres@mail.com');

-- =========================================
-- Insertar Proyectos
-- =========================================
INSERT INTO proyecto (estado, fechaInicio, fechaEntregaEstimada, idCliente) VALUES
('Activo', '2025-10-01', '2025-10-20', 1),
('Entregado', '2025-09-15', '2025-10-05', 2);

-- =========================================
-- Insertar Cotizaciones
-- =========================================
INSERT INTO cotizacion (fecha, subtotal, manoObra, iva, descuentoMonto, total, estado, idCliente, idProyecto, idVendedor) VALUES
('2025-10-01', 5000, 1200, 600, 0, 6800, 'Pendiente', 1, 1, 1),
('2025-09-16', 3000, 800, 480, 100, 4180, 'Aceptado', 2, 2, 2);

-- =========================================
-- Insertar Catálogo de Trabajos
-- =========================================
INSERT INTO catalogotrabajos (codigoInterno, nombre, descripcion, serieBase, precioBase) VALUES
('V001', 'Ventana Corrediza', 'Ventana de vidrio corrediza', 'SERIE A', 1200),
('P001', 'Puerta Abatible', 'Puerta abatible de aluminio', 'SERIE B', 1800),
('C001', 'Cancelaría Fija', 'Cancelaría fija con vidrio templado', 'SERIE C', 1500);

-- =========================================
-- Insertar Materiales
-- =========================================
INSERT INTO material (descripcion, precio, stockActual, tipo) VALUES
('Vidrio templado 6mm', 300, 50, 'Vidrio'),
('Aluminio serie A', 200, 100, 'Aluminio'),
('Jaladera cromada', 50, 30, 'Accesorio');

-- =========================================
-- Insertar Detalles de Ventana
-- =========================================
INSERT INTO ventanadetalle (id_tipo_trabajo, id_cotizacion, medidaHorizontal, medidaVertical, cantidad, tipoCristal, noHojas, precioSoloUnaUnidadCalculado, subtotalLinea, descripcion, tipoVentana, mosquitero, arco, tipoArco, medidaArco, tipoCanalillo, medidaCanalillo) VALUES
(1, 1, 1.2, 1.5, 2, 'Templado', 2, 1400, 2800, 'Ventana principal sala', '2"', 1, 0, NULL, NULL, 'Aluminio', 1.2);

-- =========================================
-- Insertar Detalles de Puerta Abatible
-- =========================================
INSERT INTO detalle_puertaabatible (id_cotizacion, id_tipo_trabajo, medidaHorizontal, medidaVertical, cantidad, tipoCristal, noHojas, precioSoloUnaUnidadCalculado, subtotalLinea, descripcion, tipo_puerta, mosquitero, duela, tipo_duela, medida_duela, adaptador, tipo_adaptador, junquillo, tipo_junquillo, canal, tipo_canal, pivote, tipo_pivote, cantidad_pivote, jaladera, tipo_jaladera, cantidad_jaladera, barra, tipo_barra) VALUES
(1, 2, 0.9, 2.0, 1, 'Templado', 1, 1800, 1800, 'Puerta principal', 'SERIE_1751', 0, 0, NULL, NULL, 0, NULL, 0, NULL, 0, NULL, 1, 'Pivote estándar', 2, 1, 'Cromo', 1, 0, NULL);

-- =========================================
-- Insertar Detalles de Cancelaría Fija
-- =========================================
INSERT INTO canceleriafijadetalle (id_tipo_trabajo, id_cotizacion, medidaHorizontal, medidaVertical, cantidad, tipoCristal, noHojas, precioSoloUnaUnidadCalculado, subtotalLinea, descripcion, tipoCanceleria, bolsa, numFijosVerticales, numFijosHorizontales, tipoTapa, cantidadTapa, zoclo, tipoZoclo, junquillo, tipoJunquillo, arco, tipoArco, medidaArco, canalillo, tipoCanalillo, medidaCanalillo) VALUES
(3, 2, 2.0, 1.5, 1, 'Templado', 1, 1500, 1500, 'Cancelaría ventana cocina', '3"', 0, 1, 2, 'Plástico', 2, 0, NULL, 1, 'PVC', 0, NULL, NULL, 0, NULL, NULL);

-- =========================================
-- Insertar Materiales usados en Ventanas
-- =========================================
INSERT INTO VentanaDetalle_Material (idVentanaDetalle, idMaterial, cantidad) VALUES
(1, 1, 2), -- vidrio
(1, 2, 4); -- aluminio

-- =========================================
-- Insertar Materiales usados en Puertas
-- =========================================
INSERT INTO PuertaAbatible_Material (id_detalle_puerta, idMaterial, cantidad) VALUES
(1, 1, 1), -- vidrio
(1, 3, 1); -- jaladera

-- =========================================
-- Insertar Materiales usados en Cancelaría Fija
-- =========================================
INSERT INTO CanceleriaFijaDetalle_Material (idCanceleriaDetalle, idMaterial, cantidad) VALUES
(1, 1, 1), -- vidrio
(1, 2, 2); -- aluminio