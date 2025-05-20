-- Insert test data for ventas table
INSERT INTO ventas (cliente_id, fecha, total) VALUES
('CLI001', '2024-05-20 10:00:00', 150.00),
('CLI002', '2024-05-20 11:30:00', 275.50);

-- Insert test data for detalles_venta table
INSERT INTO detalles_venta (venta_id, producto_id, cantidad, precio_unitario, subtotal) VALUES
(1, 1, 2, 50.00, 100.00),
(1, 2, 1, 50.00, 50.00),
(2, 1, 3, 50.00, 150.00),
(2, 2, 2, 62.75, 125.50); 