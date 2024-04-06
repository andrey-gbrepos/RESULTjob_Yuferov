CREATE DATABASE friendly_animals; -- Создание БД "Друщья человека" -> friendly_animals
USE friendly_animals;

-- Создание таблицы видов дружелюбности животных к человеку (домашние, вьючные)
CREATE TABLE IF NOT EXISTS humanrelation_view (
id_humanrelation INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
humanrelation_name VARCHAR(50) NOT NULL);

-- Заполняем видами: 'домашние', 'вьючные'
INSERT INTO humanrelation_view (humanrelation_name) VALUES 
('home'),
('pack');

.
-- Создание таблицы видов животных 
CREATE TABLE IF NOT EXISTS animals_view (
id_animalsview INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
animalsview_name VARCHAR(50) NOT NULL);

-- Заполнение видов животных (собака, кошка, кролик, хомяк, лошадь, буйвол, верблюд, осел)
INSERT INTO animals_view (animalsview_name) VALUES 
('dog'),
('cat'),
('hamster'),
('horse'),
('camel'),
('donkey');

-- Создание и заполнение таблицы-реестра животных связанная через id с таблицами видов
-- Например конкретная собака может оказаться и ездовой, а какой нибудь конкретный пони - домашним.
CREATE TABLE IF NOT EXISTS animals (
id_animal INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
id_humanrelate INT NOT NULL,
id_animalview INT NOT NULL,
animal_name VARCHAR(50) NOT NULL,
burth_date DATE NOT NULL);

INSERT INTO animals (id_humanrelate, id_animalview, animal_name, burth_date)  VALUES 
(1,1,'dozor','2015-01-17'),
(1,1,'kent','2023-06-11'),
(1,1,'osta','2024-02-28'),
(1,2,'tim','2014-04-10'),
(1,2,'viskas','2022-11-05'),
(1,2,'bella','2019-08-19'),
(1,3,'hrust','2023-11-15'),
(1,3,'kama','2024-01-31'),
(2,4,'iskra','2022-12-28'),
(2,4,'ogonek','2019-07-22'),
(2,4,'fast','2021-05-08'),
(2,5,'red','2020-11-13'),
(2,5,'hank','2019-10-27'),
(2,6,'grey','2023-02-22'),
(2,6,'kira','2022-09-16');

-- Созданине таблицы команд
CREATE TABLE IF NOT EXISTS commands (
id_commands INT PRIMARY KEY  AUTO_INCREMENT NOT NULL,
command VARCHAR(50));
-- Заполнение таблицы команд: сидеть, стоять, ко мне, нельзя, голос, дай лапу,
-- рядом, ползти, иди, стоять..
INSERT INTO commands (command) VALUES
('sit'),
('stay'),
('come'),
('stop'),
('speak'),
('paw'),
('heel'),
('craw'),
('go'),
('pruu');

-- Создание и заполнение таблицы обученных команд
CREATE TABLE IF NOT EXISTS animal_commands (
id_animal INT NOT NULL,
id_command  INT NOT NULL);

INSERT INTO animal_commands (id_animal, id_command) VALUES
INSERT INTO animal_commands (id_animal, id_command) VALUES
(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),
(1,8),(2,1),(2,2),(2,3),(2,4),(2,5),(3,1),
(3,4),(4,6),(10,9),(10,10),(11,9),(11,10),
(12,9),(12,10),(13,8),(13,9),(13,10),(14,8),
(14,9),(14,10),(15,2),(15,8),(15,9),(15,10);



-- Создание представления "Домашние животные" -> 'home_animals'
CREATE VIEW home_animals AS 
SELECT id, an_view, humanrelation_view.humanrelation_name an_humrel, an_name, an_brthdat FROM 
(SELECT animals.id_animal id, 
animals_view.animalsview_name an_view,
	animals.animal_name an_name, 
    animals.burth_date an_brthdat,
    animals.id_humanrelate id_humrel
FROM animals_view LEFT JOIN animals ON animals_view.id_animalsview = animals.id_animalview) AS view_an
RIGHT JOIN humanrelation_view ON view_an.id_humrel = humanrelation_view.id_humanrelation
WHERE humanrelation_view.humanrelation_name = 'home';

-- Создание представления "Вьючные животные" -> 'pack_animals'
CREATE VIEW pack_animals AS 
SELECT id, an_view, humanrelation_view.humanrelation_name an_humrel, an_name, an_brthdat FROM 
(SELECT animals.id_animal id, 
animals_view.animalsview_name an_view,
	animals.animal_name an_name, 
    animals.burth_date an_brthdat,
    animals.id_humanrelate id_humrel
FROM animals_view LEFT JOIN animals ON animals_view.id_animalsview = animals.id_animalview) AS view_an
RIGHT JOIN humanrelation_view ON view_an.id_humrel = humanrelation_view.id_humanrelation
WHERE humanrelation_view.humanrelation_name = 'pack';
DROP VIEW cats;
-- Создание представления "Собаки"
CREATE VIEW dogs AS 
SELECT id, an_view, humanrelation_view.humanrelation_name an_humrel, an_name, an_brthdat FROM 
(SELECT animals.id_animal id, 
animals_view.animalsview_name an_view,
	animals.animal_name an_name, 
    animals.burth_date an_brthdat,
    animals.id_humanrelate id_humrel
FROM animals_view LEFT JOIN animals ON animals_view.id_animalsview = animals.id_animalview
WHERE animals_view.animalsview_name = 'dog') AS view_an
LEFT JOIN humanrelation_view ON view_an.id_humrel = humanrelation_view.id_humanrelation;

-- Создание представления "Кошки"
CREATE VIEW cats AS 
SELECT id, an_view, humanrelation_view.humanrelation_name an_humrel, an_name, an_brthdat FROM 
(SELECT animals.id_animal id, 
animals_view.animalsview_name an_view,
	animals.animal_name an_name, 
    animals.burth_date an_brthdat,
    animals.id_humanrelate id_humrel
FROM animals_view LEFT JOIN animals ON animals_view.id_animalsview = animals.id_animalview
WHERE animals_view.animalsview_name = 'cat') AS view_an
LEFT JOIN humanrelation_view ON view_an.id_humrel = humanrelation_view.id_humanrelation;

-- Создание представления "Хомяки"
CREATE VIEW hamsters AS 
SELECT id, an_view, humanrelation_view.humanrelation_name an_humrel, an_name, an_brthdat FROM 
(SELECT animals.id_animal id, 
animals_view.animalsview_name an_view,
	animals.animal_name an_name, 
    animals.burth_date an_brthdat,
    animals.id_humanrelate id_humrel
FROM animals_view LEFT JOIN animals ON animals_view.id_animalsview = animals.id_animalview
WHERE animals_view.animalsview_name = 'hamster') AS view_an
LEFT JOIN humanrelation_view ON view_an.id_humrel = humanrelation_view.id_humanrelation;


-- Создание представления "Лошади"
CREATE VIEW horses AS 
SELECT id, an_view, humanrelation_view.humanrelation_name an_humrel, an_name, an_brthdat FROM 
(SELECT animals.id_animal id, 
animals_view.animalsview_name an_view,
	animals.animal_name an_name, 
    animals.burth_date an_brthdat,
    animals.id_humanrelate id_humrel
FROM animals_view LEFT JOIN animals ON animals_view.id_animalsview = animals.id_animalview
WHERE animals_view.animalsview_name = 'horse') AS view_an
LEFT JOIN humanrelation_view ON view_an.id_humrel = humanrelation_view.id_humanrelation;

-- Создание представления "Верблюды"
CREATE VIEW camels AS 
SELECT id, an_view, humanrelation_view.humanrelation_name an_humrel, an_name, an_brthdat FROM 
(SELECT animals.id_animal id, 
animals_view.animalsview_name an_view,
	animals.animal_name an_name, 
    animals.burth_date an_brthdat,
    animals.id_humanrelate id_humrel
FROM animals_view LEFT JOIN animals ON animals_view.id_animalsview = animals.id_animalview
WHERE animals_view.animalsview_name = 'camel') AS view_an
LEFT JOIN humanrelation_view ON view_an.id_humrel = humanrelation_view.id_humanrelation;

-- Создание представления "Ослы"
CREATE VIEW donkeys AS 
SELECT id, an_view, humanrelation_view.humanrelation_name an_humrel, an_name, an_brthdat FROM 
(SELECT animals.id_animal id, 
animals_view.animalsview_name an_view,
	animals.animal_name an_name, 
    animals.burth_date an_brthdat,
    animals.id_humanrelate id_humrel
FROM animals_view LEFT JOIN animals ON animals_view.id_animalsview = animals.id_animalview
WHERE animals_view.animalsview_name = 'donkey') AS view_an
LEFT JOIN humanrelation_view ON view_an.id_humrel = humanrelation_view.id_humanrelation;

-- Создание представления по поиску комманд доступных конретному животному (по кличке 'dozor' )
CREATE VIEW animal_command AS 
SELECT id_com.id id, commands.command commands FROM  
(SELECT animal_commands.id_command id FROM animal_commands
WHERE animal_commands.id_animal =
 (SELECT animals.id_animal FROM animals WHERE  animal_name = 'dozor')
 ) AS id_com
LEFT JOIN commands ON id_com.id = commands.id_commands;


-- Создание представления по поиску животных исполняющих определенную команду ('sit')
CREATE VIEW command_animal AS 
SELECT id_anim.id id, animals.animal_name an_name FROM  
(SELECT animal_commands.id_animal  id FROM animal_commands
WHERE animal_commands.id_command =
 (SELECT commands.id_commands FROM commands WHERE command = 'sit')
 ) AS id_anim
LEFT JOIN animals ON id_anim.id = animals.id_animal;

-- удаление верблюдов (camel) из общего реестра
DELETE FROM animals
WHERE id_animalview = 
(SELECT id_animalsview FROM animals_view WHERE animalsview_name = 'camel');

-- Создание общей таблицы horsee  и donkeys
CREATE VIEW donkey_horse AS
SELECT un.id, un.an_view, un.an_humrel, un.an_name, un.an_brthdat FROM
(SELECT id, an_view, an_humrel, an_name, an_brthdat FROM donkeys
UNION
SELECT id, an_view, an_humrel, an_name, an_brthdat FROM horses) AS un;


-- создание  таблицы “молодые животные” -> yang_animals по возрасту от 12 до 36 месяцев
-- с дополнительной колонкой возраста в месяцах
CREATE TABLE yang_animals AS 
SELECT dif_age.id id, 
animals_view.animalsview_name an_view,
	dif_age.name an_name, 
    dif_age.brth_dat an_brthdat,
    dif_age.age month_age
FROM animals_view  RIGHT JOIN  
(SELECT tmp.id_animal id, tmp.id_animalview id_view, tmp.animal_name name, tmp.burth_date brth_dat, tmp.month_age age FROM
(SELECT id_animal, id_animalview , animal_name, burth_date, TIMESTAMPDIFF(MONTH,burth_date, CURDATE()) as month_age FROM animals) tmp
WHERE month_age > 12 AND month_age < 36) AS dif_age
ON animals_view.id_animalsview = dif_age.id_view;


-- объединение всех созданных таблиц
SELECT id, an_view, an_humrel, an_name, an_brthdat, NULL AS month_age FROM dogs UNION
SELECT id, an_view, an_humrel, an_name, an_brthdat, NULL AS month_age FROM cats UNION
SELECT id, an_view, an_humrel, an_name, an_brthdat, NULL AS month_age FROM hamsters UNION
SELECT id, an_view, an_humrel, an_name, an_brthdat, NULL AS month_age FROM donkey_horse UNION
SELECT id, an_view, NULL AS an_humrel, an_name, an_brthdat, month_age FROM yang_animals;
