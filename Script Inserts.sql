USE VIDRIERIA;

-- ===========================
-- CLIENTES
-- ===========================
INSERT INTO cliente (nombre, telefono, direccion, email, RFC)
VALUES
('Constructora Horizonte', '6441234567', 'Av. Obregón #125, Cd. Obregón, Sonora', 'contacto@horizonte.com', 'HORC800512ABC'),
('Vidrios del Norte', '6447654321', 'Blvd. Morelos #540, Hermosillo, Sonora', 'ventas@vidriosnorte.com', 'VIDN850401XYZ'),
('Residencial La Joya', '6449876543', 'Fracc. La Joya, Cd. Obregón', 'admin@lajoya.com', 'REJL920301TTR');

-- ===========================
-- VENDEDORES
-- ===========================
INSERT INTO vendedor (nombre, telefono, email)
VALUES
('Juan Pérez', '6441112233', 'juanperez@vidrieria.com'),
('María López', '6442223344', 'marialopez@vidrieria.com');

-- ===========================
-- PROYECTOS
-- ===========================
INSERT INTO proyecto (estado, fechaInicio, fechaEntregaEstimada, idCliente)
VALUES
('Activo', '2025-10-01', '2025-11-15', 1),
('Activo', '2025-10-10', '2025-11-20', 2);

-- ===========================
-- COTIZACIONES
-- ===========================
INSERT INTO cotizacion (fecha, subtotal, manoObra, iva, descuentoMonto, total, estado, idCliente, idProyecto, idVendedor)
VALUES
('2025-10-05', 8500.00, 1200.00, 1600.00, 0.00, 11300.00, 'Aceptado', 1, 1, 1),
('2025-10-15', 4500.00, 800.00, 850.00, 0.00, 6150.00, 'Pendiente', 2, 2, 2);

-- ===========================
-- CATALOGO DE TRABAJOS
-- ===========================
INSERT INTO catalogotrabajos (codigoInterno, nombre, descripcion, serieBase, precioBase)
VALUES
('VTN001', 'Ventana corrediza 2”', 'Ventana de aluminio corrediza serie 2"', 'Serie 2', 1200.00),
('PBA001', 'Puerta abatible ligera', 'Puerta abatible de aluminio serie ligera', 'Serie 1751', 2200.00),
('CFL001', 'Cancelería fija 3”', 'Panel fijo de cancelería serie 3"', 'Serie 3', 1800.00);

-- ===========================
-- MATERIALES
-- ===========================
INSERT INTO material (descripcion, precio, stockActual, tipo)
VALUES
-- VIDRIO
('Vidrio claro 6mm', 480.00, 60, 'VIDRIO'),
('Vidrio tintex 6mm', 550.00, 45, 'VIDRIO'),
('Vidrio reflecta 6mm bronce', 620.00, 30, 'VIDRIO'),

-- ALUMINIO
('Perfil aluminio serie 2" natural', 340.00, 120, 'ALUMINIO'),
('Perfil aluminio serie 3" blanco', 360.00, 90, 'ALUMINIO'),
('Perfil aluminio anodizado negro', 420.00, 80, 'ALUMINIO'),

-- JUNQUILLO
('Junquillo 3/8" aluminio natural', 75.00, 200, 'JUNQUILLO'),
('Junquillo 1/4" anodizado', 85.00, 180, 'JUNQUILLO'),

-- ZOCOLO
('Zócalo aluminio natural', 110.00, 150, 'ZOCOLO'),
('Zócalo aluminio blanco', 120.00, 140, 'ZOCOLO'),

-- TAPA
('Tapa aluminio blanca', 90.00, 160, 'TAPA'),
('Tapa aluminio negra', 95.00, 130, 'TAPA'),

-- CANALILLO
('Canalillo serie 2"', 135.00, 100, 'CANALILLO'),
('Canalillo serie 3"', 145.00, 80, 'CANALILLO'),

-- DUÉLA
('Duela plástica blanca', 65.00, 200, 'DUELA'),
('Duela plástica gris', 70.00, 180, 'DUELA'),

-- ADAPTADOR
('Adaptador aluminio serie 2"', 85.00, 120, 'ADAPTADOR'),
('Adaptador aluminio serie 3"', 95.00, 100, 'ADAPTADOR'),

-- MOSQUITERO
('Mosquitero corredizo gris', 250.00, 60, 'MOSQUITERO'),
('Malla mosquitera fibra de vidrio', 180.00, 90, 'MOSQUITERO'),

-- PIVOTE
('Pivote metálico reforzado', 85.00, 100, 'PIVOTE'),
('Pivote tipo resorte', 95.00, 80, 'PIVOTE'),

-- JALADERA
('Jaladera tipo concha', 55.00, 140, 'JALADERA'),
('Jaladera tipo barra', 80.00, 100, 'JALADERA'),

-- BARRA
('Barra de empuje inoxidable', 320.00, 50, 'BARRA'),
('Barra tipo pasamanos 1m', 280.00, 60, 'BARRA'),

-- BISAGRA
('Bisagra tipo piano 1.5m', 100.00, 70, 'BISAGRA'),
('Bisagra reforzada de acero', 130.00, 60, 'BISAGRA'),

-- TORNILLERIA
('Tornillos autorroscantes 1”', 180.00, 400, 'TORNILLERIA'),
('Tornillos galvanizados ¾”', 150.00, 500, 'TORNILLERIA'),

-- EMPAQUE
('Empaque negro para vidrio 6mm', 25.00, 300, 'EMPAQUE'),
('Empaque transparente flexible', 28.00, 250, 'EMPAQUE'),

-- SELLADOR
('Sellador silicón transparente 300ml', 75.00, 200, 'SELLADOR'),
('Sellador negro resistente UV', 85.00, 180, 'SELLADOR'),

-- HERRAJE
('Herraje para puerta abatible', 260.00, 80, 'HERRAJE'),
('Herraje corredizo tipo 3" doble', 320.00, 60, 'HERRAJE'),

-- ACCESORIO
('Escuadra de unión aluminio', 40.00, 150, 'ACCESORIO'),
('Tapa de perfil plástica', 35.00, 180, 'ACCESORIO'),

-- PERFIL
('Perfil de aluminio tipo U 1”', 170.00, 100, 'PERFIL'),
('Perfil de aluminio tipo L 3/4”', 160.00, 110, 'PERFIL'),

-- OTRO
('Cinta doble cara industrial', 90.00, 70, 'OTRO'),
('Espaciador plástico 3mm', 15.00, 200, 'OTRO');

-- ===========================
-- DETALLES DE TRABAJOS
-- ===========================
INSERT INTO ventanadetalle (id_tipo_trabajo, id_cotizacion, medidaHorizontal, medidaVertical, cantidad, tipoCristal, noHojas, descripcion, tipoVentana, mosquitero, arco)
VALUES
(1, 1, 1.20, 1.00, 2, 'Vidrio claro 6mm', 2, 'Ventana corrediza con mosquitero', '2"', TRUE, FALSE);

INSERT INTO detalle_puertaabatible (id_cotizacion, id_tipo_trabajo, medidaHorizontal, medidaVertical, cantidad, tipoCristal, noHojas, descripcion, tipo_puerta, duela, adaptador, pivote, jaladera)
VALUES
(1, 2, 0.90, 2.10, 1, 'Vidrio tintex 6mm', 1, 'Puerta abatible con pivote y jaladera', 'SERIE_1751', TRUE, TRUE, TRUE, TRUE);

INSERT INTO canceleriafijadetalle (id_tipo_trabajo, id_cotizacion, medidaHorizontal, medidaVertical, cantidad, tipoCristal, noHojas, descripcion, tipoCanceleria, numFijosVerticales, numFijosHorizontales)
VALUES
(3, 2, 1.50, 2.00, 1, 'Vidrio claro 6mm', 1, 'Panel fijo de cancelería 3” con junquillo', '3"', 2, 1);

-- ===========================
-- RELACIÓN DETALLES - MATERIALES
-- ===========================
-- Ventanas
INSERT INTO VentanaDetalle_Material (idVentanaDetalle, idMaterial, cantidad, precioUnitario, precioTotal)
VALUES
(1, 1, 2.00, 500.00, 1000.00), -- Vidrio
(1, 3, 4.00, 350.00, 1400.00), -- Aluminio
(1, 9, 2.00, 250.00, 500.00);  -- Mosquitero

-- Puertas
INSERT INTO PuertaAbatible_Material (id_detalle_puerta, idMaterial, cantidad, precioUnitario, precioTotal)
VALUES
(1, 2, 1.00, 600.00, 600.00),  -- Vidrio tintex
(1, 10, 2.00, 85.00, 170.00),  -- Pivote
(1, 11, 1.00, 60.00, 60.00);   -- Jaladera

-- Cancelería fija
INSERT INTO CanceleriaFijaDetalle_Material (idCanceleriaDetalle, idMaterial, cantidad, precioUnitario, precioTotal)
VALUES
(1, 1, 1.00, 500.00, 500.00),
(1, 7, 2.00, 140.00, 280.00),
(1, 4, 2.00, 80.00, 160.00);
