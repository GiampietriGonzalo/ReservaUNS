BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `Solicitudes` (
	`Id`	INTEGER NOT NULL UNIQUE,
	`IdEstado`	INTEGER NOT NULL,
	`IdAutor`	INTEGER NOT NULL,
	`IdHorario`	INTEGER NOT NULL,
	`Fecha`	TEXT,
	`Descripcion`	TEXT,
	`CapacidadEstamida`	INTEGER
);
CREATE TABLE IF NOT EXISTS `Horarios` (
	`Id`	INTEGER NOT NULL UNIQUE,
	`HoraInicio`	TEXT,
	`HoraFin`	TEXT,
	`IdPrestamo`	INTEGER NOT NULL
);
CREATE TABLE IF NOT EXISTS `Prestamos` (
	`Id`	INTEGER NOT NULL,
	`Descripcion`	TEXT,
	`Fecha`	TEXT,
	`IdEstado`	INTEGER NOT NULL,
	`NumReserva`	INTEGER,
	`IdHorario`	INTEGER,
	`IdTitutlar`	INTEGER,
	`NumAsignacion`	INTEGER,
	`FechaDesde`	TEXT,
	`FechaHasta`	TEXT
);
CREATE TABLE IF NOT EXISTS `EstadosPrestamos` (
	`Id`	INTEGER NOT NULL UNIQUE,
	`IdPrestamo`	INTEGER NOT NULL
);
CREATE TABLE IF NOT EXISTS `EstadoSolicitud` (
	`Id`	INTEGER NOT NULL UNIQUE,
	`IdSolicitud`	INTEGER NOT NULL
);
CREATE TABLE IF NOT EXISTS `Usuarios` (
	`Cuenta`	TEXT NOT NULL UNIQUE,
	`Password`	TEXT NOT NULL UNIQUE,
	`NumLegajo`	INTEGER NOT NULL UNIQUE,
	`Nombre`	TEXT,
	`Apellido`	INTEGER,
	`Mail`	TEXT UNIQUE,
	`Tipo`	INTEGER NOT NULL
);
CREATE TABLE IF NOT EXISTS `Espacios` (
	`Nombre`	TEXT NOT NULL,
	`Capacidad`	INTEGER NOT NULL,
	`Piso`	TEXT,
	`Cuerpo`	INTEGER,
	`NombreEdificio`	INTEGER NOT NULL,
	`NombreAnterior`	TEXT,
	`Tipo`	TEXT
);
INSERT INTO `Espacios` VALUES ('pipen',100,NULL,10,'palihue1','',NULL);
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
COMMIT;
