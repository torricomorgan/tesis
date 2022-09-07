CREATE TABLE laptop (
nombre_pc VARCHAR(30) PRIMARY KEY,
fecha_registro DATE NOT NULL
);

CREATE TABLE bateria (
id_bateria  VARCHAR(50) PRIMARY KEY,
serial_bateria VARCHAR(15) NOT NULL,
modelo VARCHAR(20) NOT NULL,
manufacturador VARCHAR(15) NOT NULL,
capacidad_carga_fabrica INT NOT NULL,
fecha_registro DATE NOT NULL,
estado INT NOT NULL,
nombre_pc VARCHAR(30) NOT NULL,
FOREIGN KEY (nombre_pc) REFERENCES laptop(nombre_pc)
);

CREATE TABLE historial_capacidad (
id_historial_capacidad INT IDENTITY PRIMARY KEY,
capacidad_carga_actual INT NOT NULL,
desgaste FLOAT NOT NULL,
fecha DATE NOT NULL,
id_bateria VARCHAR(50) NOT NULL,
FOREIGN KEY (id_bateria) REFERENCES bateria(id_bateria)
);