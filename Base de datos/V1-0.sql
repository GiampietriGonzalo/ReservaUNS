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
INSERT INTO `Espacios` VALUES (NULL,'pipen',100,NULL,10,'palihue1','',NULL);
INSERT INTO `Espacios` VALUES (1,'AulaATR',0,'C',100,1,'AulaNoATR','Aula');
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
COMMIT;
