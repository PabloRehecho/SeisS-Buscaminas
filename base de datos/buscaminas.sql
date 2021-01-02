-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-01-2021 a las 14:02:24
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `buscaminas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `imagenesaudio`
--

CREATE TABLE `imagenesaudio` (
  `tipoRecurso` int(1) NOT NULL,
  `conjunto` int(2) NOT NULL,
  `subtipo` int(1) NOT NULL,
  `direccion` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `premio`
--

CREATE TABLE `premio` (
  `nombre` varchar(20) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `imagen` varchar(40) DEFAULT NULL,
  `requisito` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `premio`
--

INSERT INTO `premio` (`nombre`, `descripcion`, `imagen`, `requisito`) VALUES
('BronceI', 'Ganar en el nivel 1', 'premio bronce 5.png', 5),
('BronceII', 'Ganar en el nivel 1', 'premio bronce 10.png', 10),
('BronceIII', 'Ganar en el nivel 1', 'premio bronce 15.png', 15),
('BronceIV', 'Ganar en el nivel 1', 'premio bronce 20.png', 20),
('BronceV', 'Ganar en el nivel 1', 'premio bronce 25.png', 25),
('OroI', 'Ganar en el nivel 3', 'premio plata 5.png', 5),
('OroII', 'Ganar en el nivel 3', 'premio plata 10.png', 10),
('OroIII', 'Ganar en el nivel 3', 'premio plata 15.png', 15),
('OroIV', 'Ganar en el nivel 3', 'premio plata 20.png', 20),
('OroV', 'Ganar en el nivel 3', 'premio plata 25.png', 25),
('PlataI', 'Ganar en el nivel 2', 'premio plata 5.png', 5),
('PlataII', 'Ganar en el nivel 2', 'premio plata 10.png', 10),
('PlataIII', 'Ganar en el nivel 2', 'premio plata 15.png', 15),
('PlataIV', 'Ganar en el nivel 2', 'premio plata 20.png', 20),
('PlataV', 'Ganar en el nivel 2', 'premio plata 25.png', 25),
('RachaI', 'Gana partidas seguidas hasta', 'Racha1.png', 1),
('RachaII', 'Gana partidas seguidas hasta', 'Racha2.png', 5),
('RachaIII', 'Gana partidas seguidas hasta', 'Racha3.png', 8),
('RachaIV', 'Gana partidas seguidas hasta', 'Racha4.png', 10),
('RachaV', 'Gana partidas seguidas hasta', 'Racha5.png', 15);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ranking`
--

CREATE TABLE `ranking` (
  `fechaHora` timestamp NOT NULL DEFAULT current_timestamp(),
  `emailJugador` varchar(100) NOT NULL,
  `nombre` varchar(100) NOT NULL DEFAULT 'desconocido',
  `puntuacion` int(11) NOT NULL DEFAULT 0,
  `nivel` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `email` varchar(100) NOT NULL,
  `contrasena` varchar(20) NOT NULL,
  `partidasGanadas1` int(11) NOT NULL DEFAULT 0,
  `partidasGanadas2` int(11) NOT NULL DEFAULT 0,
  `partidasGanadas3` int(11) NOT NULL DEFAULT 0,
  `partidasPerdidas` int(11) NOT NULL DEFAULT 0,
  `racha` int(11) NOT NULL DEFAULT 0,
  `nivelInicial` int(11) NOT NULL DEFAULT 1,
  `imagenMinas` int(11) NOT NULL DEFAULT 1,
  `imagenCara` int(11) NOT NULL DEFAULT 1,
  `sonido` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuariopremio`
--

CREATE TABLE `usuariopremio` (
  `emailJugador` varchar(100) NOT NULL,
  `nombrePremio` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valores`
--

CREATE TABLE `valores` (
  `nombre` varchar(20) NOT NULL,
  `valor` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `imagenesaudio`
--
ALTER TABLE `imagenesaudio`
  ADD PRIMARY KEY (`tipoRecurso`,`conjunto`,`subtipo`);

--
-- Indices de la tabla `premio`
--
ALTER TABLE `premio`
  ADD PRIMARY KEY (`nombre`);

--
-- Indices de la tabla `ranking`
--
ALTER TABLE `ranking`
  ADD PRIMARY KEY (`fechaHora`,`emailJugador`),
  ADD KEY `emailJugador` (`emailJugador`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`email`);

--
-- Indices de la tabla `usuariopremio`
--
ALTER TABLE `usuariopremio`
  ADD PRIMARY KEY (`emailJugador`,`nombrePremio`),
  ADD KEY `nombrePremio` (`nombrePremio`);

--
-- Indices de la tabla `valores`
--
ALTER TABLE `valores`
  ADD PRIMARY KEY (`nombre`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ranking`
--
ALTER TABLE `ranking`
  ADD CONSTRAINT `ranking_ibfk_1` FOREIGN KEY (`emailJugador`) REFERENCES `usuario` (`email`) ON DELETE CASCADE;

--
-- Filtros para la tabla `usuariopremio`
--
ALTER TABLE `usuariopremio`
  ADD CONSTRAINT `usuariopremio_ibfk_1` FOREIGN KEY (`emailJugador`) REFERENCES `usuario` (`email`) ON DELETE CASCADE,
  ADD CONSTRAINT `usuariopremio_ibfk_2` FOREIGN KEY (`nombrePremio`) REFERENCES `premio` (`nombre`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
