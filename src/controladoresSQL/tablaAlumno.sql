CREATE TABLE TB_ALUMNOS (
	id INT NOT NULL AUTO_INCREMENT,
	dni VARCHAR (5),
	nombre VARCHAR (25),
	apellidos VARCHAR (25),
	nota DECIMAL (5,2),
	vip BOOLEAN,
		PRIMARY KEY (id)
);

INSERT INTO TB_ALUMNOS (dni,nombre,apellidos,nota,vip) VALUES (?,?,?,?,?);