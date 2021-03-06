-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 06 mars 2021 à 15:30
-- Version du serveur :  10.4.13-MariaDB
-- Version de PHP : 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `plateforme`
--

-- --------------------------------------------------------

--
-- Structure de la table `carton`
--

CREATE TABLE `carton` (
  `id_carton` int(11) NOT NULL,
  `type_carton` varchar(2) NOT NULL,
  `libelle_carton` varchar(20) NOT NULL,
  `prix` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `carton`
--

INSERT INTO `carton` (`id_carton`, `type_carton`, `libelle_carton`, `prix`) VALUES
(1, 'P', 'carton petit', 100),
(2, 'M', 'carton moyen', 150),
(3, 'G', 'carton grand', 200);

-- --------------------------------------------------------

--
-- Structure de la table `cartondemande`
--

CREATE TABLE `cartondemande` (
  `id_demande` int(11) NOT NULL,
  `id_carton` int(11) NOT NULL,
  `nbrcartondemande` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `cartondemande`
--

INSERT INTO `cartondemande` (`id_demande`, `id_carton`, `nbrcartondemande`) VALUES
(31, 1, 5),
(31, 2, 0),
(31, 3, 5),
(32, 1, 13),
(32, 2, 14),
(32, 3, 27),
(33, 1, 3),
(33, 2, 4),
(33, 3, 55),
(34, 1, 22),
(34, 2, 23),
(34, 3, 24),
(35, 1, 2),
(35, 2, 0),
(35, 3, 22);

-- --------------------------------------------------------

--
-- Structure de la table `demande`
--

CREATE TABLE `demande` (
  `id` int(11) NOT NULL,
  `date_creation` date NOT NULL,
  `etat` varchar(3) NOT NULL,
  `id_client` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `demande`
--

INSERT INTO `demande` (`id`, `date_creation`, `etat`, `id_client`) VALUES
(31, '2021-03-06', 'F', 26),
(32, '2021-03-06', 'NF', 26),
(33, '2021-03-06', 'NF', 26),
(34, '2021-03-06', 'F', 26),
(35, '2021-03-06', 'F', 26);

-- --------------------------------------------------------

--
-- Structure de la table `offre`
--

CREATE TABLE `offre` (
  `id_offre` int(11) NOT NULL,
  `reduction_offre` float NOT NULL,
  `description` varchar(100) NOT NULL,
  `id` int(11) NOT NULL,
  `carton_moyen` int(11) DEFAULT NULL,
  `carton_petit` int(11) DEFAULT NULL,
  `carton_grand` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `offre`
--

INSERT INTO `offre` (`id_offre`, `reduction_offre`, `description`, `id`, `carton_moyen`, `carton_petit`, `carton_grand`) VALUES
(1, 0.3, 'Meilleure offre du moment', 13, 14, 2, 8),
(8, 0.5, 'Petite offre', 13, 0, 5, 5),
(9, 0.2, 'Pack favori', 13, 2, 2, 0),
(11, 0.7, 'Pack grossiste', 13, 23, 22, 24),
(12, 0.12, 'Offre du lundi', 13, 0, 2, 22),
(14, 0.15, 'Pack mini', 13, 4, 3, 5);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `sexe` varchar(10) NOT NULL,
  `tel` varchar(10) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `nom`, `prenom`, `email`, `sexe`, `tel`, `password`, `role`) VALUES
(1, 'admin', 'has', 'admin@has.com', 'M', '0536373839', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'A'),
(13, 'maftah', 'hajar', 'hajar@maftah.com', 'F', '0612121212', 'ce4c41095dfc2dcc12fea71f27079785eef693744b5149fd74c40bd95f039a0c', 'A'),
(26, 'Ali', 'Alami', 'ali@alami.com', 'M', '0607050505', 'f943b5c744154eed22c45acdfc051ff86e95fe928b403d6132ef01b8ee7f817d', 'C'),
(28, 'Masrar', 'Hicham', 'hicham@masrar.com', 'M', '0617897654', '8370f25ac58e47a9603810cf54b84714b6c0e64af3f4b09160b6d1eb1b9078c4', 'C');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `carton`
--
ALTER TABLE `carton`
  ADD PRIMARY KEY (`id_carton`);

--
-- Index pour la table `cartondemande`
--
ALTER TABLE `cartondemande`
  ADD PRIMARY KEY (`id_demande`,`id_carton`),
  ADD KEY `car-dem-fk` (`id_carton`);

--
-- Index pour la table `demande`
--
ALTER TABLE `demande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_demande_fk` (`id_client`);

--
-- Index pour la table `offre`
--
ALTER TABLE `offre`
  ADD PRIMARY KEY (`id_offre`),
  ADD KEY `user_offre_fk` (`id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `carton`
--
ALTER TABLE `carton`
  MODIFY `id_carton` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `demande`
--
ALTER TABLE `demande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT pour la table `offre`
--
ALTER TABLE `offre`
  MODIFY `id_offre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `cartondemande`
--
ALTER TABLE `cartondemande`
  ADD CONSTRAINT `car-dem-dem` FOREIGN KEY (`id_demande`) REFERENCES `demande` (`id`),
  ADD CONSTRAINT `car-dem-fk` FOREIGN KEY (`id_carton`) REFERENCES `carton` (`id_carton`);

--
-- Contraintes pour la table `demande`
--
ALTER TABLE `demande`
  ADD CONSTRAINT `user_demande_fk` FOREIGN KEY (`id_client`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `offre`
--
ALTER TABLE `offre`
  ADD CONSTRAINT `user_offre_fk` FOREIGN KEY (`id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
