# Итоговая контрольная работа

## Задание  

### 1. Используя команду cat в терминале операционной системы Linux, создать два файла:
### Домашние животные (заполнив файл собаками, кошками, хомяками) и Вьючные животными заполнив файл Лошадьми, верблюдами и ослы), а затем объединить их. Просмотреть содержимое созданного файла. Переименовать файл, дав ему новое имя (Друзья человека). 

Создан файл home_animals, заполнен записями: dog, cat, rabbit, hamster: cat > home_animals
Создан файл pack_animals, заполнен записями: horse, camel, bool,donkey: cat > pacl_animals
Объединение файлов в файле home_animals: cat pack_animals >> home_animals
Переименование файла: mv home_animals human_friends.

### 2. Создать директорию, переместить файл туда. 

Создана новая директория animals: mkdir animals
Файл human_friends перемещен в новую директорию: mv human_friends animals


### 3. Подключить дополнительный репозиторий MySQL. 
### Установить любой пакет из этого репозитория. 

С помощью команды: sudo nano /etc/apt/sources.list.d/mysql.list
открываем файл mysql.list и прописываем в нем путь к ключу и репозиторию.

Выполняем команду sudo apt update 
Убеждаемся что отсутствует ключ для репозиторя mysql

Скачиваем ключ командой: gpg --keyserver keyserver.ubuntu.com --recv B7B3B788A8D3785C

И устанавливаем его в систему: 
gpg --export B7B3B788A8D3785C | sudo tee /usr/share/keyrings/mysql.gpg > /dev/null
Проверяем установку ключа с помощью команды: apt update:


### 4. Установить и удалить deb-пакет с помощью dpkg

Создаем и входим в конфигурационный файл: sudo nano  /etc/apt/sources.list.d/vbox.list
 

На сайте производителя скачиваем и выполняем команду для загрузки и установки ключа репозитория: wget -O- https://www.virtualbox.org/download/oracle_vbox_2016.asc | sudo gpg --yes --output /usr/share/keyrings/oracle-virtualbox-2016.gpg –dearmor

С помощью команды apt update проверяем установку репозитория:

Используем команду dpkg  для установки пакета: 

C помощью команды apt -f install устанавливаем связи с библиотеками:

Удаление пакета с помощью утилиты dpkg

Удаление зависимостей:

### 5. Выложить историю команд в терминале ubuntu 

Историю команд можно посмотреть с помощью команды history:

### 6. Нарисовать диаграмму, в которой есть класс родительский класс, домашние животные и вьючные животные, в состав которых, в случае домашних животных войдут классы: собаки, кошки, хомяки, а в класс вьючные животные войдут: Лошади, верблюды и ослы. 

В диаграмме в качестве базовой выступает Таблица  animals, которая включает в себя основные поля:id_animal - идентификатор конкретного животного
animal_name - кличка животного
burth_date - дата рождения животного
И вспомогательные поля:
id_humanrelate - id для связи с Таблицей humanrelation_view предназначения животного (домашнее или вьючное)
id_animalview - id для связи с Таблицей animals_view вида животного (кошка, верблюд и т.д.)

Кроме указанных таблиц существует еще две таблицы:
commands - для хранения списка команд которым может быть обучено животное и
animal_commands - для хранения соответствия id животного и id команды которой оно обучено.

Необходимые сочетания набора данных решаются с помощью Представлений.

### 7. В подключенном MySQL репозитории создать базу данных “Друзья человека” 
Создание и подключение к базе данных "Друзья человека" -> friendly_animals:
CREATE DATABASE friendly_animals; 
USE friendly_animals;


### 8. Создать таблицы с иерархией из диаграммы в БД 
-- Создание таблицы видов дружелюбности животных к человеку (домашние, вьючные)
CREATE TABLE IF NOT EXISTS humanrelation_view (
id_humanrelation INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
humanrelation_name VARCHAR(50) NOT NULL);

-- Создание таблицы видов животных 
CREATE TABLE IF NOT EXISTS animals_view (
id_animalsview INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
animalsview_name VARCHAR(50) NOT NULL);

-- Создание таблицы-реестра животных связанная через id с таблицами видов
-- Например конкретная собака может оказаться и ездовой, а какой нибудь конкретный пони - домашним.
CREATE TABLE IF NOT EXISTS animals (
id_animal INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
id_humanrelate INT NOT NULL,
id_animalview INT NOT NULL,
animal_name VARCHAR(50) NOT NULL,
burth_date DATE NOT NULL);
 

-- Создание и заполнение таблицы обученных команд
CREATE TABLE IF NOT EXISTS animal_commands (
id_animal INT NOT NULL,
id_command  INT NOT NULL);

Здесь же созданы Представления: 'camels' - верблюды,'cats' - кошки, 'dogs' - собаки, 'horses' - лошади, 'donkeys' - ослы, 'hamsters' - хомяки, 'home_animals' - домашние животные, 'pack_animals' - вьючные животные -> см. файлы SQL


### 9. Заполнить низкоуровневые таблицы именами(животных), командами которые они выполняют и датами рождения 

-- Заполняем видами: 'домашние', 'вьючные'
INSERT INTO humanrelation_view (humanrelation_name) VALUES 
('home'),
('pack');

-- Заполнение видов животных (собака, кошка, кролик, хомяк, лошадь, буйвол, верблюд, осел)
INSERT INTO animals_view (animalsview_name) VALUES 
('dog'),
('cat'),
('hamster'),
('horse'),
('camel'),
('donkey');

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
('tpruu');

INSERT INTO animal_commands (id_animal, id_command) VALUES
(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),
(1,8),(2,1),(2,2),(2,3),(2,4),(2,5),(3,1),
(3,4),(4,6),(10,9),(10,10),(11,9),(11,10),
(12,9),(12,10),(13,8),(13,9),(13,10),(14,8),
(14,9),(14,10),(15,2),(15,8),(15,9),(15,10);

### 10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу. 

Удаление верблюдов и повторный Запрос списка:

-- удаление верблюдов (camel) из общего реестра
DELETE FROM animals
WHERE id_animalview = 
(SELECT id_animalsview FROM animals_view WHERE animalsview_name = 'camel');

Объединение в одну таблицу Представлений «horses» и «donkeys».

-- Создание общей таблицы horsee  и donkeys
CREATE VIEW donkey_horse AS
SELECT un.id, un.an_view, un.an_humrel, un.an_name, un.an_brthdat FROM
(SELECT id, an_view, an_humrel, an_name, an_brthdat FROM donkeys
UNION
SELECT id, an_view, an_humrel, an_name, an_brthdat FROM horses) AS un;

### 11.Создать новую таблицу “молодые животные” в которую попадут все животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью до месяца подсчитать возраст животных в новой таблице 
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

### 12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую принадлежность к старым таблицам. 
-- объединение всех созданных таблиц
SELECT id, an_view, an_humrel, an_name, an_brthdat, NULL AS month_age FROM dogs UNION
SELECT id, an_view, an_humrel, an_name, an_brthdat, NULL AS month_age FROM cats UNION
SELECT id, an_view, an_humrel, an_name, an_brthdat, NULL AS month_age FROM hamsters UNION
SELECT id, an_view, an_humrel, an_name, an_brthdat, NULL AS month_age FROM donkey_horse UNION
SELECT id, an_view, NULL AS an_humrel, an_name, an_brthdat, month_age FROM yang_animals;



