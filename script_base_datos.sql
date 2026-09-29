CREATE TABLE Carretera (
    id_carretera SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL
);

CREATE TABLE Comuna (
    id_comuna SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE Tramo (
    id_tramo SERIAL PRIMARY KEY,
    id_carretera INT NOT NULL,
    km_inicio DECIMAL(8,2) NOT NULL,
    km_fin DECIMAL(8,2) NOT NULL,
    tipo_conclusion VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_carretera) REFERENCES Carretera(id_carretera)
);

CREATE TABLE Tramo_Comuna (
    id_tramo INT NOT NULL,
    id_comuna INT NOT NULL,
    km_inicio_comuna DECIMAL(8,2) NOT NULL,
    km_fin_comuna DECIMAL(8,2) NOT NULL,
    PRIMARY KEY (id_tramo, id_comuna),
    FOREIGN KEY (id_tramo) REFERENCES Tramo(id_tramo),
    FOREIGN KEY (id_comuna) REFERENCES Comuna(id_comuna)
);

INSERT INTO Carretera (nombre, categoria) VALUES ('CA-1 Occidente', 'Nacional'), ('CA-9 Sur', 'Autovía');
INSERT INTO Comuna (nombre) VALUES ('Mixco'), ('San Lucas Sacatepéquez'), ('Amatitlán');
INSERT INTO Tramo (id_carretera, km_inicio, km_fin, tipo_conclusion) VALUES (1, 10.00, 29.50, 'Otra Carretera'), (2, 12.00, 36.00, 'Física');
INSERT INTO Tramo_Comuna (id_tramo, id_comuna, km_inicio_comuna, km_fin_comuna) VALUES (1, 1, 10.00, 16.50), (1, 2, 16.50, 29.50), (2, 3, 20.00, 36.00);
