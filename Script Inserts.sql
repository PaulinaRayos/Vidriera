USE VIDRIERIA;

-- =========================================
-- Insertar Clientes
-- =========================================
INSERT INTO cliente (nombre, telefono, direccion, email, RFC) VALUES
('Juan Pérez', '5551234567', 'Calle Falsa 123', 'juanperez@mail.com', 'JUAP750101XXX'),
('María López', '5559876543', 'Av. Siempre Viva 742', 'marialopez@mail.com', 'MALO880202XXX'),
('Luis Martínez', '5552223333', 'Calle 10 #456', 'luismartinez@mail.com', 'LUMA920303XXX'),
('Ana García', '5554445555', 'Av. Reforma 123', 'anagarcia@mail.com', 'ANGA950505XXX'),
('Pedro Sánchez', '5556667777', 'Calle 5 #789', 'pedrosanchez@mail.com', 'PESA880808XXX'),
('Carla Rivera', '5558889999', 'Av. Insurgentes 321', 'carlarivera@mail.com', 'CARI930707XXX');

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
('Entregado', '2025-09-15', '2025-10-05', 2),
('Activo', '2025-10-05', '2025-10-25', 3),
('Activo', '2025-10-07', '2025-10-27', 4),
('Entregado', '2025-09-20', '2025-10-10', 5),
('Activo', '2025-10-10', '2025-10-30', 6);

-- =========================================
-- Insertar Cotizaciones
-- =========================================
INSERT INTO cotizacion (fecha, subtotal, manoObra, iva, descuentoMonto, total, estado, idCliente, idProyecto, idVendedor) VALUES
('2025-10-01', 5000, 1200, 600, 0, 6800, 'Pendiente', 1, 1, 1),
('2025-09-16', 3000, 800, 480, 100, 4180, 'Aceptado', 2, 2, 2),
('2025-10-06', 4500, 1000, 540, 0, 6040, 'Pendiente', 3, 3, 1),
('2025-10-08', 3200, 900, 384, 50, 4434, 'Pendiente', 4, 4, 2),
('2025-09-21', 6000, 1500, 720, 200, 7020, 'Aceptado', 5, 5, 1),
('2025-10-11', 3500, 800, 420, 0, 4720, 'Pendiente', 6, 6, 2);

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
(1, 1, 1.2, 1.5, 2, 'Templado', 2, 1400, 2800, 'Ventana principal sala', '2"', 1, 0, NULL, NULL, 'Aluminio', 1.2),
(1, 2, 1.0, 1.2, 1, 'Templado', 1, 1200, 1200, 'Ventana cocina', '1 ½"', 0, 0, NULL, NULL, 'Aluminio', 1.0),
(1, 3, 1.1, 1.4, 1, 'Templado', 1, 1300, 1300, 'Ventana sala', '2"', 1, 0, NULL, NULL, 'Aluminio', 1.1),
(1, 4, 0.9, 1.3, 2, 'Templado', 2, 1200, 2400, 'Ventana recámara', '1 ½"', 0, 0, NULL, NULL, 'Aluminio', 0.9),
(1, 5, 1.5, 1.5, 1, 'Templado', 1, 1500, 1500, 'Ventana comedor', '3 7/8"', 1, 0, NULL, NULL, 'Aluminio', 1.5),
(1, 6, 1.2, 1.2, 2, 'Templado', 2, 1400, 2800, 'Ventana principal', '2"', 0, 0, NULL, NULL, 'Aluminio', 1.2);

-- =========================================
-- Insertar Detalles de Puerta Abatible
-- =========================================
INSERT INTO detalle_puertaabatible (id_cotizacion, id_tipo_trabajo, medidaHorizontal, medidaVertical, cantidad, tipoCristal, noHojas, precioSoloUnaUnidadCalculado, subtotalLinea, descripcion, tipo_puerta, mosquitero, duela, tipo_duela, medida_duela, adaptador, tipo_adaptador, junquillo, tipo_junquillo, canal, tipo_canal, pivote, tipo_pivote, cantidad_pivote, jaladera, tipo_jaladera, cantidad_jaladera, barra, tipo_barra) VALUES
(1, 2, 0.9, 2.0, 1, 'Templado', 1, 1800, 1800, 'Puerta principal', 'SERIE_1751', 0, 0, NULL, NULL, 0, NULL, 0, NULL, 0, NULL, 1, 'Pivote estándar', 2, 1, 'Cromo', 1, 0, NULL),
(2, 2, 0.8, 2.0, 1, 'Templado', 1, 1700, 1700, 'Puerta cocina', 'SERIE_1751', 0, 0, NULL, NULL, 0, NULL, 0, NULL, 0, NULL, 1, 'Pivote estándar', 2, 1, 'Cromo', 1, 0, NULL),
(3, 2, 1.0, 2.0, 1, 'Templado', 1, 1800, 1800, 'Puerta principal', 'LINEA_PESADA', 0, 0, NULL, NULL, 0, NULL, 0, NULL, 0, NULL, 1, 'Pivote estándar', 2, 1, 'Cromo', 1, 0, NULL),
(4, 2, 0.9, 2.0, 1, 'Templado', 1, 1700, 1700, 'Puerta secundaria', 'SERIE_1751', 0, 0, NULL, NULL, 0, NULL, 0, NULL, 0, NULL, 1, 'Pivote estándar', 2, 1, 'Cromo', 1, 0, NULL),
(5, 2, 1.2, 2.0, 1, 'Templado', 1, 1900, 1900, 'Puerta comedor', 'LINEA_PESADA', 0, 0, NULL, NULL, 0, NULL, 0, NULL, 0, NULL, 1, 'Pivote estándar', 2, 1, 'Cromo', 1, 0, NULL),
(6, 2, 1.0, 2.0, 1, 'Templado', 1, 1800, 1800, 'Puerta principal', 'SERIE_1751', 0, 0, NULL, NULL, 0, NULL, 0, NULL, 0, NULL, 1, 'Pivote estándar', 2, 1, 'Cromo', 1, 0, NULL);

-- =========================================
-- Insertar Detalles de Cancelaría Fija
-- =========================================
INSERT INTO canceleriafijadetalle (id_tipo_trabajo, id_cotizacion, medidaHorizontal, medidaVertical, cantidad, tipoCristal, noHojas, precioSoloUnaUnidadCalculado, subtotalLinea, descripcion, tipoCanceleria, bolsa, numFijosVerticales, numFijosHorizontales, tipoTapa, cantidadTapa, zoclo, tipoZoclo, junquillo, tipoJunquillo, arco, tipoArco, medidaArco, canalillo, tipoCanalillo, medidaCanalillo) VALUES
(3, 1, 2.0, 1.5, 1, 'Templado', 1, 1500, 1500, 'Cancelaría ventana sala', '3"', 0, 1, 2, 'Plástico', 2, 0, NULL, 1, 'PVC', 0, NULL, NULL, 0, NULL, NULL),
(3, 2, 1.8, 1.2, 1, 'Templado', 1, 1400, 1400, 'Cancelaría ventana cocina', '2"', 0, 1, 1, 'Plástico', 1, 0, NULL, 1, 'PVC', 0, NULL, NULL, 0, NULL, NULL),
(3, 3, 2.2, 1.6, 1, 'Templado', 1, 1600, 1600, 'Cancelaría sala', '3"', 0, 1, 2, 'Plástico', 2, 0, NULL, 1, 'PVC', 0, NULL, NULL, 0, NULL, NULL),
(3, 4, 1.9, 1.3, 1, 'Templado', 1, 1500, 1500, 'Cancelaría recámara', '2"', 0, 1, 1, 'Plástico', 1, 0, NULL, 1, 'PVC', 0, NULL, NULL, 0, NULL, NULL),
(3, 5, 2.5, 1.7, 1, 'Templado', 1, 1700, 1700, 'Cancelaría comedor', '3"', 0, 1, 2, 'Plástico', 2, 0, NULL, 1, 'PVC', 0, NULL, NULL, 0, NULL, NULL),
(3, 6, 2.0, 1.5, 1, 'Templado', 1, 1500, 1500, 'Cancelaría principal', '2"', 0, 1, 1, 'Plástico', 1, 0, NULL, 1, 'PVC', 0, NULL, NULL, 0, NULL, NULL);

-- =========================================
-- Insertar Materiales usados en Ventanas
-- =========================================
INSERT INTO VentanaDetalle_Material (idVentanaDetalle, idMaterial, cantidad) VALUES
(1, 1, 2), (1, 2, 4),
(2, 1, 1), (2, 2, 2),
(3, 1, 2), (3, 2, 4),
(4, 1, 1), (4, 2, 2),
(5, 1, 2), (5, 2, 4),
(6, 1, 1), (6, 2, 2);

-- =========================================
-- Insertar Materiales usados en Puertas
-- =========================================
INSERT INTO PuertaAbatible_Material (id_detalle_puerta, idMaterial, cantidad) VALUES
(1, 1, 1), (1, 3, 1),
(2, 1, 1), (2, 3, 1),
(3, 1, 1), (3, 3, 1),
(4, 1, 1), (4, 3, 1),
(5, 1, 1), (5, 3, 1),
(6, 1, 1), (6, 3, 1);

-- =========================================
-- Insertar Materiales usados en Cancelaría Fija
-- =========================================
INSERT INTO CanceleriaFijaDetalle_Material (idCanceleriaDetalle, idMaterial, cantidad) VALUES
(1, 1, 1), (1, 2, 2),
(2, 1, 1), (2, 2, 2),
(3, 1, 1), (3, 2, 2),
(4, 1, 1), (4, 2, 2),
(5, 1, 1), (5, 2, 2),
(6, 1, 1), (6, 2, 2);
