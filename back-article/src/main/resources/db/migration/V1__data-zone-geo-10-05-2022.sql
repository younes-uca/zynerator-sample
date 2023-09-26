-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 26 déc. 2021 à 16:57
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `generator`
--

-- --------------------------------------------------------

--
-- Structure de la table `engine_config`
--

DROP TABLE IF EXISTS `engine_config`;
CREATE TABLE IF NOT EXISTS `engine_config` (
    `id` bigint(20) NOT NULL,
    `output_folder` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `generator_history`
--

DROP TABLE IF EXISTS `generator_history`;
CREATE TABLE IF NOT EXISTS `generator_history` (
    `id` bigint(20) NOT NULL,
    `date` date DEFAULT NULL,
    `project_template` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK6o0s8pxwvq4xrpyi9593y7joy` (`project_template`)
    ) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `generator_history`
--

INSERT INTO `generator_history` (`id`, `date`, `project_template`) VALUES
                                                                       (19, '2021-05-15', 2),
                                                                       (20, '2021-05-15', 9),
                                                                       (21, '2021-05-15', 2),
                                                                       (22, '2021-05-15', 9),
                                                                       (23, '2021-05-15', 2),
                                                                       (24, '2021-05-15', 9),
                                                                       (25, '2021-05-15', 2),
                                                                       (26, '2021-05-15', 9),
                                                                       (27, '2021-05-15', 2),
                                                                       (28, '2021-05-15', 9),
                                                                       (29, '2021-05-15', 3),
                                                                       (30, '2021-05-15', 9),
                                                                       (31, '2021-05-15', 2),
                                                                       (32, '2021-05-15', 9),
                                                                       (33, '2021-05-15', 2),
                                                                       (34, '2021-05-15', 9),
                                                                       (35, '2021-05-16', 2),
                                                                       (36, '2021-05-16', 9),
                                                                       (37, '2021-05-16', 2),
                                                                       (38, '2021-05-16', 9),
                                                                       (39, '2021-05-16', 3),
                                                                       (40, '2021-05-16', 9),
                                                                       (41, '2021-12-17', 9),
                                                                       (42, '2021-12-17', 9),
                                                                       (43, '2021-12-19', 9),
                                                                       (44, '2021-12-19', 9),
                                                                       (45, '2021-12-19', 9),
                                                                       (46, '2021-12-19', 9),
                                                                       (47, '2021-12-19', 9),
                                                                       (48, '2021-12-19', 9),
                                                                       (49, '2021-12-19', 9),
                                                                       (50, '2021-12-19', 9),
                                                                       (51, '2021-12-19', 9),
                                                                       (52, '2021-12-19', 9),
                                                                       (53, '2021-12-19', 9),
                                                                       (54, '2021-12-19', 9),
                                                                       (55, '2021-12-19', 9),
                                                                       (56, '2021-12-19', 9),
                                                                       (57, '2021-12-19', 9),
                                                                       (58, '2021-12-19', 9),
                                                                       (59, '2021-12-19', 2),
                                                                       (60, '2021-12-19', 9),
                                                                       (61, '2021-12-19', 9),
                                                                       (62, '2021-12-19', 9),
                                                                       (63, '2021-12-19', 9),
                                                                       (64, '2021-12-19', 2),
                                                                       (65, '2021-12-19', 9),
                                                                       (66, '2021-12-19', 2),
                                                                       (67, '2021-12-19', 9),
                                                                       (68, '2021-12-19', 2),
                                                                       (69, '2021-12-19', 9),
                                                                       (70, '2021-12-20', 2),
                                                                       (71, '2021-12-20', 9),
                                                                       (72, '2021-12-20', 2),
                                                                       (73, '2021-12-20', 9),
                                                                       (74, '2021-12-20', 2),
                                                                       (75, '2021-12-20', 9),
                                                                       (76, '2021-12-20', 2),
                                                                       (77, '2021-12-20', 9),
                                                                       (78, '2021-12-22', 2),
                                                                       (79, '2021-12-22', 9),
                                                                       (80, '2021-12-23', 2),
                                                                       (81, '2021-12-23', 9),
                                                                       (82, '2021-12-23', 9),
                                                                       (83, '2021-12-23', 2),
                                                                       (84, '2021-12-23', 9),
                                                                       (85, '2021-12-23', 2),
                                                                       (86, '2021-12-23', 9),
                                                                       (87, '2021-12-23', 2),
                                                                       (88, '2021-12-23', 9),
                                                                       (89, '2021-12-23', 2),
                                                                       (90, '2021-12-23', 9),
                                                                       (91, '2021-12-23', 9),
                                                                       (92, '2021-12-23', 2),
                                                                       (93, '2021-12-23', 9),
                                                                       (94, '2021-12-23', 2),
                                                                       (95, '2021-12-23', 9),
                                                                       (96, '2021-12-23', 2),
                                                                       (97, '2021-12-23', 9),
                                                                       (98, '2021-12-23', 2),
                                                                       (99, '2021-12-23', 9),
                                                                       (100, '2021-12-23', 9),
                                                                       (101, '2021-12-23', 9),
                                                                       (102, '2021-12-24', 9),
                                                                       (103, '2021-12-24', 2),
                                                                       (104, '2021-12-24', 9),
                                                                       (105, '2021-12-24', 2),
                                                                       (106, '2021-12-24', 9),
                                                                       (107, '2021-12-24', 2),
                                                                       (108, '2021-12-24', 9),
                                                                       (109, '2021-12-24', 2),
                                                                       (110, '2021-12-24', 9),
                                                                       (111, '2021-12-24', 2),
                                                                       (112, '2021-12-24', 9),
                                                                       (113, '2021-12-25', 2),
                                                                       (114, '2021-12-25', 9);

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
    `next_val` bigint(20) DEFAULT NULL
    ) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
                                                  (115),
                                                  (115);

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
    `id` bigint(20) NOT NULL,
    `content` varchar(255) DEFAULT NULL,
    `date` date DEFAULT NULL,
    `vu` bit(1) DEFAULT NULL,
    `receiver` bigint(20) DEFAULT NULL,
    `sender` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK1wn5617q01o90dwqjua8yhvux` (`receiver`),
    KEY `FKob83vkf2oo4r68pn9d69kgwf8` (`sender`)
    ) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `pojo_config`
--

DROP TABLE IF EXISTS `pojo_config`;
CREATE TABLE IF NOT EXISTS `pojo_config` (
    `id` bigint(20) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `project_template`
--

DROP TABLE IF EXISTS `project_template`;
CREATE TABLE IF NOT EXISTS `project_template` (
    `id` bigint(20) NOT NULL,
    `description` varchar(255) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `path` varchar(255) DEFAULT NULL,
    `technologie` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK7bae57egevoglnw2ciiao2o6` (`technologie`)
    ) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `project_template`
--

INSERT INTO `project_template` (`id`, `description`, `name`, `path`, `technologie`) VALUES
                                                                                        (2, 'default', 'default', 'src\\main\\resources\\templates\\backend\\spring\\default', 1),
                                                                                        (3, 'spring project without vo and converter ', 'default1', 'src\\main\\resources\\templates\\backend\\spring\\default1', 1),
                                                                                        (9, 'default', 'default', 'src\\main\\resources\\templates\\frontend\\angular\\default', 8),
                                                                                        (17, 'default', 'default', 'src\\main\\resources\\templates\\backend\\ejb\\default', 16),
                                                                                        (20, 'default', 'default', 'src\\main\\resources\\templates\\frontend\\jsf\\default', 21),
                                                                                        (21, 'roma', 'roma', 'src\\main\\resources\\templates\\frontend\\angular\\roma', 8),
                                                                                        (22, 'default', 'default', 'src\\main\\resources\\templates\\frontend\\react\\default', 22),
                                                                                        (23, 'default', 'default', 'src\\main\\resources\\templates\\frontend\\native\\default', 23),
                                                                                        (24, 'default', 'default', 'src\\main\\resources\\templates\\backend\\nest\\default', 24),

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
    `id` bigint(20) NOT NULL,
    `authority` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `technologie`
--

DROP TABLE IF EXISTS `technologie`;
CREATE TABLE IF NOT EXISTS `technologie` (
    `id` bigint(20) NOT NULL,
    `category` int(11) DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `default_template` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKbn1th3m4du5injg9sr9l4uy8x` (`default_template`)
    ) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `technologie`
--

INSERT INTO `technologie` (`id`, `category`, `description`, `name`, `default_template`) VALUES
                                                                                            (1, 0, 'spring framework', 'spring', 2),
                                                                                            (8, 1, 'Angular framework', 'angular', 9),
                                                                                            (16, 0, 'ejb framework o l3ibat', 'ejb', 17),
                                                                                            (22, 1, 'React', 'React', 22),
                                                                                            (23, 0, 'React natif', 'React natif', 23),
                                                                                            (24, 0, 'Nest', 'Nest', 24),
                                                                                            (21, 1, 'jsf framework', 'jsf', 20);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
    `id` bigint(20) NOT NULL,
    `account_non_expired` bit(1) NOT NULL,
    `account_non_locked` bit(1) NOT NULL,
    `credentials_non_expired` bit(1) NOT NULL,
    `enabled` bit(1) NOT NULL,
    `github_url` varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    `username` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `user_authorities`
--

DROP TABLE IF EXISTS `user_authorities`;
CREATE TABLE IF NOT EXISTS `user_authorities` (
    `user_id` bigint(20) NOT NULL,
    `authorities` bigint(20) NOT NULL,
    KEY `FKbale5oulw5a18g5b5xqy1f6c6` (`authorities`),
    KEY `FKmj13d0mnuj4cd8b6htotbf9mm` (`user_id`)
    ) ENGINE=MyISAM DEFAULT CHARSET=latin1;
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
