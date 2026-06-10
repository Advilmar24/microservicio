create database wishlist_db;
use wishlist_db;

CREATE TABLE wishlist (
  id INT AUTO_INCREMENT PRIMARY KEY,
  usuario_id INT NOT NULL,
  producto_id INT NOT NULL,
  fecha_agregado TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  estado VARCHAR(50) DEFAULT 'activo'
);

CREATE TABLE wishlist_historial (
  id INT AUTO_INCREMENT PRIMARY KEY,
  wishlist_id INT NOT NULL,
  producto_id INT NOT NULL,
  accion VARCHAR(50), -- agregado, eliminado
  fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);




