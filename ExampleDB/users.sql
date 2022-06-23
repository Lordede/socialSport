-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 22. Jun 2022 um 13:09
-- Server-Version: 10.4.22-MariaDB
-- PHP-Version: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `thidb`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `isAdmin` tinyint(1) DEFAULT 0,
  `creationDate` datetime NOT NULL,
  `profilePicture` mediumblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `users`
--

INSERT INTO `users` (`id`, `email`, `username`, `firstname`, `lastname`, `pwd`, `isAdmin`, `creationDate`, `profilePicture`) VALUES
(2, 'admin@admin.de', 'Admin', 'Admin', 'Admin', 'e3afed0047b08059d0fada10f400c1e5', 1, '2022-06-17 00:00:00', NULL),
(3, 'Volker.Stiehl@thi.de', 'VolkerStiehl', 'Volker', 'Stiehl', 'c04a04fe6e0d4c6e2081251f99bd556b', 0, '2022-06-22 00:00:00', NULL),
(4, 'lue0558@thi.de', 'lue0558', 'Lukas', 'Edemüller', '8230c713c0b267372a714b53ff456ba0', 0, '2022-06-22 00:00:00', NULL),
(5, 'ced5742@thi.de', 'ced5742', 'Cem', 'Durmus', '18ba4daa4c356e10f8f4fc81e3f9c5f9', 0, '2022-06-22 00:00:00', NULL),
(6, 'hus4725@thi.de', 'hus4725', 'Hubertus', 'Seitz', '2e04058defff4d8ad3bd32be3665eee4', 0, '2022-06-22 00:00:00', NULL),
(7, 'hugohabicht@thi.de', 'hugohabicht', 'hugo', 'habicht', '5bbe3d9dd18d1f412b097621282fe344', 0, '2022-06-22 00:00:00', NULL),
(8, 'SusiSorglos@thi.de', 'SusiSorglos', 'Susi', 'Sorglos', 'ed997aa42d1edbffca6e12b60a69c7c0', 0, '2022-06-22 00:00:00', NULL),
(9, 'PeterLustig@thi.de', 'PeterLustig', 'Peter', 'Lustig', '19ad37be4d838e7fdc9d2f4dcc7b1dd6', 0, '2022-06-22 00:00:00', NULL),
(10, 'FixiHartmann@thi.de', 'FixiHartmann', 'Fixi', 'Hartmann', '9c956e13afac92b38e77f3308906fb47', 0, '2022-06-22 00:00:00', NULL),
(11, 'OemerDuerustUesluenuen@thi.de', 'ÖmerDüruśtÜslünün', 'Ömer', 'Düruśt Üslünün', '20448800d6c7c600bb649df65f3ca82c', 0, '2022-06-22 00:00:00', NULL);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
