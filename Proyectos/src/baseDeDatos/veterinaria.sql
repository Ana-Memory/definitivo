DROP DATABASE IF EXISTS veterinaria;
CREATE DATABASE veterinaria;
USE veterinaria;

-- CREACIÓN DE TABLAS --
CREATE TABLE veterinario (
    id VARCHAR(9) PRIMARY KEY,
    nombre VARCHAR(40) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    telefono VARCHAR(12) NOT NULL,
    correo VARCHAR(40) NOT NULL,
    usuario VARCHAR(30) NOT NULL
);

CREATE TABLE cliente (
    id VARCHAR(9) PRIMARY KEY,
    nombre VARCHAR(40) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    telefono VARCHAR(12) NOT NULL,
    correo VARCHAR(40) NOT NULL,
    usuario VARCHAR(30) NOT NULL
);

CREATE TABLE mascota (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    especie VARCHAR(30) NOT NULL,
    edad VARCHAR(20) NOT NULL,
    sexo VARCHAR(8) NOT NULL,
    peso DOUBLE NOT NULL,
    vacunas BOOLEAN NOT NULL,
    historial VARCHAR(100),
    id_cliente VARCHAR(9),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);

CREATE TABLE consulta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('Revisión', 'Vacunación', 'Esterilización/Castración', 'Enfermedad', 'Desparasitación', 'Odontología', 'Cirugía', 'Otros') NOT NULL,
    precio DOUBLE NOT NULL,
    id_cliente VARCHAR(9),
    id_mascota INT NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id),
    FOREIGN KEY (id_mascota) REFERENCES mascota(id)
);

CREATE TABLE producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    tipo ENUM('Esencial', 'Medicina', 'Juguete') NOT NULL,
    precio DOUBLE NOT NULL,
    unidades INT NOT NULL
);

CREATE TABLE administrador (
    id VARCHAR(9) PRIMARY KEY,
    nombre VARCHAR(40) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    telefono VARCHAR(12) NOT NULL,
    correo VARCHAR(40) NOT NULL,
    usuario VARCHAR(30) NOT NULL
);

CREATE TABLE reserva (
    id INT auto_increment PRIMARY KEY,
    id_cliente VARCHAR(9) NOT NULL,
    id_consulta INT NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id),
    FOREIGN KEY (id_consulta) REFERENCES consulta(id),
    fecha DATE NOT NULL,
    hora TIME NOT NULL;
);

CREATE TABLE compra (
    id INT auto_increment PRIMARY KEY,
    id_cliente VARCHAR(9) NOT NULL,
    id_producto INT NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id),
    FOREIGN KEY (id_producto) REFERENCES producto(id)
);

CREATE TABLE agrega (
    id INT auto_increment PRIMARY KEY,
    id_admin VARCHAR(9) NOT NULL,
    id_producto INT NOT NULL,
    FOREIGN KEY (id_admin) REFERENCES administrador(id),
    FOREIGN KEY (id_producto) REFERENCES producto(id)
);

CREATE TABLE controla (
    id INT auto_increment PRIMARY KEY,
    id_admin VARCHAR(9) NOT NULL,
    id_mascota INT NOT NULL,
    FOREIGN KEY (id_admin) REFERENCES administrador(id),
    FOREIGN KEY (id_mascota) REFERENCES mascota(id)
);

CREATE TABLE atiende (
    id INT auto_increment PRIMARY KEY,
    id_vet VARCHAR(9) NOT NULL,
    id_mascota INT NOT NULL,
    FOREIGN KEY (id_vet) REFERENCES veterinario(id),
    FOREIGN KEY (id_mascota) REFERENCES mascota(id)
);


-- INSERCIÓN DE DATOS EN MASCOTA --
INSERT INTO mascota (nombre, especie, edad, sexo, peso, vacunas, historial)
VALUES ("Dante", "Perro Labrador", "2 años", "Macho", 40.0, true, "Ojo vago."),
("Pretzel", "Gato Cálico", "3 meses", "Hembra", 1.5, true, ""),
("Sheldon", "Tortuga de caparazón blando", "50 años", "Macho", 0.2, false, "Desparasitación."),
("Blue", "Cacatúa", "5 años", "Hembra", 1.2, false, ""),
("Hamtaro", "Hamster", "1 año", "Macho", 0.02, false, "Revisión.");

-- INSERCIÓN DE DATOS EN ADMINISTRADOR --
INSERT INTO administrador (id, nombre, apellidos, telefono, correo, usuario)
VALUES ("12345678A","Ana Lucía", "Barrios Plana", "123456789", "230200@iessierradeguara.com", "ana"),
("23456789B","Julian David", "Reyes Casas", "987654321", "230140@iessierradeguara.com", "julian");

-- INSERCIÓN DE DATOS EN VETERINARIO --
INSERT INTO veterinario (id, nombre, apellidos, telefono, correo, usuario)
VALUES ('34567890C', 'Lucía', 'García Pérez', '612345678', 'lucia.garcia@email.com', 'luciagarcia'),
('45678901D', 'Carlos', 'Martínez López', '698765432', 'carlos.martinez@email.com', 'carlosmartinez'),
('56789012E', 'Ana', 'Sánchez Ruiz', '654321987', 'ana.sanchez@email.com', 'anasanchez'),
('67890123F', 'Miguel', 'Fernández Díaz', '623498765', 'miguel.fernandez@email.com', 'miguelfernandez'),
('78901234G', 'Elena', 'Torres Gómez', '687123459', 'elena.torres@email.com', 'elenatorres');

-- INSERCIÓN DE DATOS EN PRODUCTO --
INSERT INTO producto (nombre, descripcion, tipo, precio, unidades)
VALUES ('Arena para Gatos', 'Arena absorbente y control de olores', 'Esencial', 12.50, 50),
('Comida Seca Perro Adulto', 'Pienso balanceado para perros adultos', 'Esencial', 25.00, 40),
('Cama para Mascotas', 'Cama cómoda y lavable para perros y gatos', 'Esencial', 45.99, 30),
('Collar Antipulgas', 'Collar protector contra pulgas y garrapatas', 'Esencial', 15.75, 60),
('Bebedero Automático', 'Bebedero con circulación de agua para mascotas', 'Esencial', 30.00, 25),

('Vitamina para Perros', 'Suplemento vitamínico para perros activos', 'Medicina', 18.50, 40),
('Jarabe Antiparasitario', 'Tratamiento contra parásitos internos en gatos', 'Medicina', 22.00, 35),
('Spray Calmante', 'Spray para reducir ansiedad en mascotas', 'Medicina', 14.25, 50),
('Pastillas Antiinflamatorias', 'Medicamento antiinflamatorio para perros', 'Medicina', 20.00, 45),
('Suero Rehidratante', 'Solución oral para deshidratación en mascotas', 'Medicina', 10.50, 60),

('Pelota de Goma', 'Pelota resistente para morder y lanzar', 'Juguete', 5.99, 100),
('Ratón de Juguete', 'Ratón de felpa para gatos', 'Juguete', 3.50, 80),
('Cuerda para Perro', 'Cuerda para jugar a tirar', 'Juguete', 7.25, 60),
('Hueso de Nylon', 'Hueso duradero para morder', 'Juguete', 6.50, 70),
('Puzzle Alimenticio', 'Juguete interactivo que dispensa comida', 'Juguete', 12.00, 40);

-- CREACIÓN DE USUARIOS ADMINISTRADORES --
CREATE USER 'ana'@'localhost' IDENTIFIED BY '12345678A';
CREATE USER 'julian'@'localhost' IDENTIFIED BY '23456789B';

GRANT ALL PRIVILEGES ON veterinaria TO 'ana'@'localhost';
GRANT ALL PRIVILEGES ON veterinaria TO 'julian'@'localhost';

-- CREACIÓN DE USUARIOS VETERINARIOS --
CREATE USER 'luciagarcia'@'localhost' IDENTIFIED BY '34567890C';
CREATE USER 'carlosmartinez'@'localhost' IDENTIFIED BY '45678901D';
CREATE USER 'anasanchez'@'localhost' IDENTIFIED BY '56789012E';
CREATE USER 'miguelfernandez'@'localhost' IDENTIFIED BY '67890123F';
CREATE USER 'elenatorres'@'localhost' IDENTIFIED BY '78901234G';

GRANT SELECT, UPDATE ON veterinaria.mascota TO 'luciagarcia'@'localhost';
GRANT SELECT, UPDATE ON veterinaria.mascota TO 'carlosmartinez'@'localhost';
GRANT SELECT, UPDATE ON veterinaria.mascota TO 'anasanchez'@'localhost';
GRANT SELECT, UPDATE ON veterinaria.mascota TO 'miguelfernandez'@'localhost';
GRANT SELECT, UPDATE ON veterinaria.mascota TO 'elenatorres'@'localhost';

GRANT SELECT, UPDATE ON veterinaria.consulta TO 'luciagarcia'@'localhost';
GRANT SELECT, UPDATE ON veterinaria.consulta TO 'carlosmartinez'@'localhost';
GRANT SELECT, UPDATE ON veterinaria.consulta TO 'anasanchez'@'localhost';
GRANT SELECT, UPDATE ON veterinaria.consulta TO 'miguelfernandez'@'localhost';
GRANT SELECT, UPDATE ON veterinaria.consulta TO 'elenatorres'@'localhost';