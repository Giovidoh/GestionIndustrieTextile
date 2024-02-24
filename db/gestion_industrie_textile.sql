-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 24 fév. 2024 à 15:24
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestion_industrie_textile`
--

-- --------------------------------------------------------

--
-- Structure de la table `design`
--

CREATE TABLE `design` (
  `Id` int(11) NOT NULL,
  `NomDesign` varchar(30) NOT NULL,
  `DescriptionDesign` varchar(500) NOT NULL,
  `StatutDesign` varchar(20) NOT NULL,
  `IdDesigner` int(11) NOT NULL,
  `IdProjet` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

CREATE TABLE `employe` (
  `Id` int(11) NOT NULL,
  `NomEmp` varchar(30) NOT NULL,
  `PrenomEmp` varchar(50) NOT NULL,
  `DateNaisEmp` date NOT NULL,
  `GenreEmp` varchar(1) NOT NULL,
  `RespoEmp` varchar(30) NOT NULL,
  `ContactEmp` varchar(8) NOT NULL,
  `EmailEmp` varchar(30) NOT NULL,
  `IdEmp` varchar(30) NOT NULL,
  `MdpEmp` varchar(20) NOT NULL,
  `Responsable` tinyint(1) NOT NULL DEFAULT 0,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `deleted_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `employe`
--

INSERT INTO `employe` (`Id`, `NomEmp`, `PrenomEmp`, `DateNaisEmp`, `GenreEmp`, `RespoEmp`, `ContactEmp`, `EmailEmp`, `IdEmp`, `MdpEmp`, `Responsable`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'Admin', 'Admin', '2004-05-02', 'M', 'Créateur', '70881015', 'admin@gmail.com', 'admin', 'admin', 0, '2024-02-21 16:05:57', '2024-02-21 16:05:57', NULL),
(2, 'REMADJI', 'Estelle', '2003-06-07', 'F', 'Designer de mode', '98658745', 'remadjiestelle@gmail.com', 'estelle', 'estelle', 1, '2024-02-21 15:47:03', '2024-02-21 17:47:59', NULL),
(3, 'ABALO', 'Kossi', '1990-08-05', 'M', 'Styliste', '98654523', 'abalokossi@gmail.com', 'abalokossi', 'abalokossi', 1, '2024-02-21 17:39:13', '2024-02-21 17:39:13', NULL),
(4, 'DOE', 'John', '2000-12-25', 'M', 'Fabricant', '96563215', 'johndoe@gmail.com', 'johndoe', 'johndoe', 1, '2024-02-21 20:24:42', '2024-02-21 20:24:42', NULL),
(5, 'ATA', 'Afi', '2001-05-16', 'F', 'Styliste', '90456521', 'afiata@gmail.com', 'afiata', 'afiata', 0, '2024-02-21 20:26:24', '2024-02-21 20:26:24', NULL),
(6, 'AFILOTO', 'Jonathan', '2001-10-27', 'M', 'Fabricant', '92365874', 'jonathan@gmail.com', 'jonathan', 'jonathan', 0, '2024-02-22 04:05:13', '2024-02-22 04:05:13', NULL),
(7, 'test', 'test', '2000-01-01', 'M', 'Designer de mode', '98564521', 'test@gmail.com', 'test', 'test', 0, '2024-02-22 04:06:14', '2024-02-22 04:06:20', '2024-02-22 04:06:20'),
(8, 'test', 'test', '2000-01-01', 'F', 'Designer de mode', '91523125', 'test@gmail.com', 'test', 'test', 0, '2024-02-22 04:15:34', '2024-02-22 04:15:34', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `image`
--

CREATE TABLE `image` (
  `Id` int(11) NOT NULL,
  `CheminImage` blob NOT NULL,
  `IdDesign` int(11) DEFAULT NULL,
  `IdStyle` int(11) DEFAULT NULL,
  `IdProduit` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `Id` int(11) NOT NULL,
  `NomProduit` varchar(30) NOT NULL,
  `DescriptionProduit` varchar(500) NOT NULL,
  `StatutProduit` varchar(20) NOT NULL,
  `IdFabricant` int(11) NOT NULL,
  `IdStyle` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `projet`
--

CREATE TABLE `projet` (
  `Id` int(11) NOT NULL,
  `NomProjet` varchar(30) NOT NULL,
  `DescriptionProjet` varchar(500) NOT NULL,
  `StatutProjet` varchar(20) NOT NULL,
  `IdCreateur` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `deleted_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `projet`
--

INSERT INTO `projet` (`Id`, `NomProjet`, `DescriptionProjet`, `StatutProjet`, `IdCreateur`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'fdsqmklj', 'fdqklsjf', 'Pas encore commencé', 1, '2024-02-23 16:13:05', '2024-02-23 16:13:05', '2024-02-23 16:27:42'),
(2, 'qsfdqsffqsdf', 'fdqsf', 'Pas encore commencé', 1, '2024-02-23 16:45:09', '2024-02-23 16:45:09', NULL),
(3, 'ok', 'ok', 'Pas encore commencé', 1, '2024-02-23 16:51:47', '2024-02-23 16:51:47', NULL),
(4, 'good', 'good', 'Pas encore commencé', 1, '2024-02-23 16:52:00', '2024-02-23 16:52:00', NULL),
(12, 'Bien', 'Bien', 'en cours', 1, '2024-02-23 17:57:34', '2024-02-23 17:57:34', NULL),
(13, 'b', 'h', 'en cours', 1, '2024-02-23 17:58:46', '2024-02-23 17:58:46', NULL),
(14, 'h', 'h', 'Pas encore commencé', 1, '2024-02-23 16:59:24', '2024-02-23 16:59:24', NULL),
(15, '\"fdksjhf\"', 'fsdlkfj', 'Pas encore commencé', 1, '2024-02-23 17:07:58', '2024-02-23 17:07:58', NULL),
(16, 'fjdkfldkzlekfjdlkfjdlfldkfjfld', 'fldkqjfjqdklsf', 'Pas encore commencé', 1, '2024-02-23 17:08:49', '2024-02-23 17:08:49', NULL),
(17, 'fjfkdlfmekfjdpriape:akfocirnej', 'fdkjqskljf', 'Pas encore commencé', 1, '2024-02-23 17:09:32', '2024-02-23 17:09:32', NULL),
(18, 'f', 'f', 'Pas encore commencé', 1, '2024-02-23 17:12:20', '2024-02-23 17:12:20', NULL),
(19, 'ff', 'fsdff', 'Pas encore commencé', 1, '2024-02-23 17:12:32', '2024-02-23 17:12:32', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `style`
--

CREATE TABLE `style` (
  `Id` int(11) NOT NULL,
  `NomStyle` varchar(30) NOT NULL,
  `DescriptionStyle` varchar(500) NOT NULL,
  `StatutStyle` varchar(20) NOT NULL,
  `IdStyliste` int(11) NOT NULL,
  `IdDesign` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `design`
--
ALTER TABLE `design`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `IdDesigner` (`IdDesigner`),
  ADD KEY `IdProjet` (`IdProjet`);

--
-- Index pour la table `employe`
--
ALTER TABLE `employe`
  ADD PRIMARY KEY (`Id`);

--
-- Index pour la table `image`
--
ALTER TABLE `image`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `IdDesign` (`IdDesign`),
  ADD KEY `IdStyle` (`IdStyle`),
  ADD KEY `IdProduit` (`IdProduit`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `IdFabricant` (`IdFabricant`),
  ADD KEY `IdStyle` (`IdStyle`);

--
-- Index pour la table `projet`
--
ALTER TABLE `projet`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `IdCreateur` (`IdCreateur`);

--
-- Index pour la table `style`
--
ALTER TABLE `style`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `IdEmploye` (`IdStyliste`),
  ADD KEY `IdDesign` (`IdDesign`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `design`
--
ALTER TABLE `design`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `employe`
--
ALTER TABLE `employe`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `image`
--
ALTER TABLE `image`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `projet`
--
ALTER TABLE `projet`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT pour la table `style`
--
ALTER TABLE `style`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `design`
--
ALTER TABLE `design`
  ADD CONSTRAINT `design_ibfk_1` FOREIGN KEY (`IdDesigner`) REFERENCES `employe` (`Id`),
  ADD CONSTRAINT `design_ibfk_2` FOREIGN KEY (`IdProjet`) REFERENCES `projet` (`Id`);

--
-- Contraintes pour la table `image`
--
ALTER TABLE `image`
  ADD CONSTRAINT `image_ibfk_1` FOREIGN KEY (`IdDesign`) REFERENCES `design` (`Id`),
  ADD CONSTRAINT `image_ibfk_2` FOREIGN KEY (`IdStyle`) REFERENCES `style` (`Id`),
  ADD CONSTRAINT `image_ibfk_3` FOREIGN KEY (`IdProduit`) REFERENCES `produit` (`Id`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `produit_ibfk_1` FOREIGN KEY (`IdFabricant`) REFERENCES `employe` (`Id`),
  ADD CONSTRAINT `produit_ibfk_2` FOREIGN KEY (`IdStyle`) REFERENCES `style` (`Id`);

--
-- Contraintes pour la table `projet`
--
ALTER TABLE `projet`
  ADD CONSTRAINT `projet_ibfk_1` FOREIGN KEY (`IdCreateur`) REFERENCES `employe` (`Id`);

--
-- Contraintes pour la table `style`
--
ALTER TABLE `style`
  ADD CONSTRAINT `style_ibfk_1` FOREIGN KEY (`IdStyliste`) REFERENCES `employe` (`Id`),
  ADD CONSTRAINT `style_ibfk_2` FOREIGN KEY (`IdDesign`) REFERENCES `design` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
