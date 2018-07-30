BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `Usuarios` (
	`Id`	INTEGER,
	`Cuenta`	TEXT NOT NULL UNIQUE,
	`Password`	TEXT NOT NULL UNIQUE,
	`Legajo`	INTEGER NOT NULL UNIQUE,
	`Nombre`	TEXT,
	`Apellido`	INTEGER,
	`Mail`	TEXT UNIQUE,
	`Tipo`	INTEGER NOT NULL
);
INSERT INTO `Usuarios` VALUES (1,'ATR','henrykhe',108644,'Sonie','Ruede','sonie@cs.uns.edu.ar','Docente');
CREATE TABLE IF NOT EXISTS `Prestamos` (
	`Id`	INTEGER NOT NULL,
	`Descripcion`	TEXT,
	`Fecha`	TEXT,
	`IdEstado`	INTEGER NOT NULL,
	`IdHorario`	INTEGER,
	`IdEspacio`	INTEGER,
	`IdTitutlar`	INTEGER,
	`FechaDesde`	TEXT,
	`FechaHasta`	TEXT,
	`Tipo`	TEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS `Horarios` (
	`Id`	INTEGER NOT NULL UNIQUE,
	`HoraInicio`	TEXT,
	`HoraFin`	TEXT,
	`IdPrestamo`	INTEGER NOT NULL,
	`DiasSemana`	TEXT
);
CREATE TABLE IF NOT EXISTS `Espacios` (
	`Id`	INTEGER,
	`Nombre`	TEXT NOT NULL,
	`Capacidad`	INTEGER NOT NULL,
	`Piso`	TEXT,
	`Cuerpo`	INTEGER,
	`IdEdificio`	INTEGER NOT NULL,
	`NombreAnterior`	TEXT,
	`Tipo`	TEXT
);
INSERT INTO `Espacios` VALUES (1,'AulaATR',50,'0','C',1,'AulaNoATR','Aula');
CREATE TABLE IF NOT EXISTS `EstadosPrestamos` (
	`Id`	INTEGER NOT NULL UNIQUE,
	`IdPrestamo`	INTEGER NOT NULL,
	`Tipo`	TEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS `EstadosSolicitudes` (
	`Id`	INTEGER NOT NULL UNIQUE,
	`IdSolicitud`	INTEGER NOT NULL,
	`Tipo`	TEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS `Solicitudes` (
	`Id`	INTEGER NOT NULL UNIQUE,
	`IdEstado`	INTEGER NOT NULL,
	`IdAutor`	INTEGER NOT NULL,
	`IdHorario`	INTEGER NOT NULL,
	`Fecha`	TEXT,
	`Descripcion`	TEXT,
	`CapacidadEstamida`	INTEGER,
	`Tipo`	TEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS `Edificios` (
	`Id`	INTEGER NOT NULL UNIQUE,
	`Nombre`	TEXT NOT NULL UNIQUE,
	`Direccion`	TEXT NOT NULL,
	`Telefono`	TEXT NOT NULL,
	`IdEncargado`	INTEGER NOT NULL UNIQUE,
	`Codigo`	INTEGER UNIQUE,
	`Tipo`	TEXT NOT NULL,
	PRIMARY KEY(`Id`)
);
INSERT INTO `Edificios` VALUES (1,'DCIC','Palihue 1','155555555',1,20,'Departamento');
COMMIT;
