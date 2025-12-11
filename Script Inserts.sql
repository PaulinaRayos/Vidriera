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
-- COTIZACIONES
-- ===========================
INSERT INTO cotizacion (fecha, subtotal, manoObra, iva, descuentoMonto, total, estado, idCliente, idVendedor)
VALUES
('2025-10-05', 8500.00, 1200.00, 1600.00, 0.00, 11300.00, 'Aceptado', 1, 1),
('2025-10-15', 4500.00, 800.00, 850.00, 0.00, 6150.00, 'Pendiente', 2, 2);

-- ===========================
-- PROYECTOS
-- ===========================
INSERT INTO proyecto (estado, fechaInicio, fechaEntregaEstimada, idCliente,idCotizacion,observaciones)
VALUES
('Activo', '2025-10-01', '2025-11-15', 1,1,'falta material'),
('Activo', '2025-10-10', '2025-11-20', 2,2,'el cliente quiere adelanto en 1 semana');


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
-- MATERIALES (ACTIVO por defecto)
INSERT INTO material (descripcion, precio, stockActual, tipo, unidadMedida, estado)
VALUES
-- VIDRIO
('Claro 6mm (1.83x2.44)', 480.00, 60, 'VIDRIO', 'HOJA', 'ACTIVO'),
('Tintex 6mm (2.13x2.44)', 550.00, 45, 'VIDRIO', 'HOJA', 'ACTIVO'),
('Reflecta 6mm (2.14x3.30)', 620.00, 30, 'VIDRIO', 'HOJA', 'ACTIVO'),
('Reflecta 6mm (2.25x3.21)', 620.00, 30, 'VIDRIO', 'HOJA', 'ACTIVO'),
('Reflecta 6mm (2.44x3.66)', 620.00, 30, 'VIDRIO', 'HOJA', 'ACTIVO'),

-- ALUMINIO
('Perfil aluminio serie 2" natural', 340.00, 120, 'ALUMINIO', 'M', 'ACTIVO'),
('Perfil aluminio serie 3" blanco', 360.00, 90, 'ALUMINIO', 'M', 'ACTIVO'),
('Perfil aluminio anodizado negro', 420.00, 80, 'ALUMINIO', 'M', 'ACTIVO'),

-- JUNQUILLO
('Junquillo 3/8" aluminio natural', 75.00, 200, 'JUNQUILLO', 'M', 'ACTIVO'),
('Junquillo 1/4" anodizado', 85.00, 180, 'JUNQUILLO', 'M', 'ACTIVO'),

-- ZOCOLO
('Zócalo aluminio natural', 110.00, 150, 'ZOCLO', 'M', 'ACTIVO'),
('Zócalo aluminio blanco', 120.00, 140, 'ZOCLO', 'M', 'ACTIVO'),

-- TAPA
('Tapa aluminio blanca', 90.00, 160, 'TAPA', 'M', 'ACTIVO'),
('Tapa aluminio negra', 95.00, 130, 'TAPA', 'M', 'ACTIVO'),

-- CANALILLO
('Canalillo serie 2"', 135.00, 100, 'CANALILLO', 'M', 'ACTIVO'),
('Canalillo serie 3"', 145.00, 80, 'CANALILLO', 'M', 'ACTIVO'),

-- DUÉLA
('Duela plástica blanca', 65.00, 200, 'DUELA', 'M', 'ACTIVO'),
('Duela plástica gris', 70.00, 180, 'DUELA', 'M', 'ACTIVO'),

-- ADAPTADOR
('Adaptador aluminio serie 2"', 85.00, 120, 'ADAPTADOR', 'PZA', 'ACTIVO'),
('Adaptador aluminio serie 3"', 95.00, 100, 'ADAPTADOR', 'PZA', 'ACTIVO'),

-- MOSQUITERO
('Mosquitero corredizo', 250.00, 60, 'MOSQUITERO', 'PZA', 'ACTIVO'),
('Mosquitero fijo', 180.00, 90, 'MOSQUITERO', 'PZA', 'ACTIVO'),

-- PIVOTE
('Pivote metálico reforzado', 85.00, 100, 'PIVOTE', 'PZA', 'ACTIVO'),
('Pivote tipo resorte', 95.00, 80, 'PIVOTE', 'PZA', 'ACTIVO'),

-- JALADERA
('Jaladera tipo concha', 55.00, 140, 'JALADERA', 'PZA', 'ACTIVO'),
('Jaladera tipo barra', 80.00, 100, 'JALADERA', 'PZA', 'ACTIVO'),

-- BARRA
('Barra de empuje inoxidable', 320.00, 50, 'BARRA', 'PZA', 'ACTIVO'),
('Barra tipo pasamanos 1m', 280.00, 60, 'BARRA', 'PZA', 'ACTIVO'),

-- BISAGRA
('Bisagra tipo piano 1.5m', 100.00, 70, 'BISAGRA', 'PZA', 'ACTIVO'),
('Bisagra reforzada de acero', 130.00, 60, 'BISAGRA', 'PZA', 'ACTIVO'),

-- TORNILLERIA
('Tornillos autorroscantes 1”', 180.00, 400, 'TORNILLERIA', 'PZA', 'ACTIVO'),
('Tornillos galvanizados ¾”', 150.00, 500, 'TORNILLERIA', 'PZA', 'ACTIVO'),

-- EMPAQUE
('Empaque negro para vidrio 6mm', 25.00, 300, 'EMPAQUE', 'M', 'ACTIVO'),
('Empaque transparente flexible', 28.00, 250, 'EMPAQUE', 'M', 'ACTIVO'),

-- SELLADOR
('Sellador silicón transparente 300ml', 75.00, 200, 'SELLADOR', 'TUBO', 'ACTIVO'),
('Sellador negro resistente UV', 85.00, 180, 'SELLADOR', 'TUBO', 'ACTIVO'),

-- HERRAJE
('Herraje para puerta abatible', 260.00, 80, 'HERRAJE', 'PZA', 'ACTIVO'),
('Herraje corredizo tipo 3" doble', 320.00, 60, 'HERRAJE', 'PZA', 'ACTIVO'),

-- ACCESORIO
('Escuadra de unión aluminio', 40.00, 150, 'ACCESORIO', 'PZA', 'ACTIVO'),
('Tapa de perfil plástica', 35.00, 180, 'ACCESORIO', 'PZA', 'ACTIVO'),

-- PERFIL
('Aluminio tipo U 1”', 170.00, 100, 'PERFIL', 'M', 'ACTIVO'),
('Aluminio tipo L 3/4”', 160.00, 110, 'PERFIL', 'M', 'ACTIVO'),

-- ARCO
('Arco aluminio serie 2"', 180.00, 50, 'ARCO', 'M', 'ACTIVO'),
('Arco aluminio serie 3"', 200.00, 40, 'ARCO', 'M', 'ACTIVO'),

-- BOLSA
('Bolsa para panel fijo', 50.00, 100, 'BOLSA', 'PZA', 'ACTIVO'),
('Bolsa reforzada transparente', 65.00, 80, 'BOLSA', 'PZA', 'ACTIVO'),

-- OTRO
('Cinta doble cara industrial', 90.00, 70, 'OTRO', 'PZA', 'ACTIVO'),
('Espaciador plástico 3mm', 15.00, 200, 'OTRO', 'PZA', 'ACTIVO'),

-- TELA
('Tela (0.60m de ancho)', 90.00, 70, 'TELA', 'M', 'ACTIVO'),
('Tela (0.90m de ancho)', 150.00, 200, 'TELA', 'M', 'ACTIVO'),
('Tela (1.0m de ancho)', 200.00, 200, 'TELA', 'M', 'ACTIVO'),
('Tela (1.2m de ancho)', 230.00, 200, 'TELA', 'M', 'ACTIVO'),
('Tela (1.5m de ancho)', 260.00, 200, 'TELA', 'M', 'ACTIVO');

-- ===========================
-- DETALLES DE TRABAJOS
-- ===========================
INSERT INTO ventanadetalle (id_tipo_trabajo, id_cotizacion, medidaHorizontal, medidaVertical, cantidad, tipoCristal, noHojas, descripcion, tipoVentana, mosquitero, arco)
VALUES
(1, 1, 1.20, 1.00, 2, 'Vidrio claro 6mm', 2, 'Ventana corrediza con mosquitero', '2"', TRUE, FALSE);

INSERT INTO puertaabatibledetalle (id_cotizacion, id_tipo_trabajo, medidaHorizontal, medidaVertical, cantidad, tipoCristal, noHojas, descripcion, tipo_puerta, duela, adaptador, pivote, jaladera)
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
INSERT INTO PuertaAbatibleDetalle_Material (id_detalle_puerta, idMaterial, cantidad, precioUnitario, precioTotal)
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
