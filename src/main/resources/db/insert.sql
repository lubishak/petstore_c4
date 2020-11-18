SET FOREIGN_KEY_CHECKS = 0;

truncate table pet;
truncate table store;

INSERT INTO store(`id`, `name`, `location`, `contact_no`)
VALUES (21, 'Shakstore', 'Lagos', '0220146u84,');
INSERT INTO pet(`id`, `name`, `color`, `breed`, `age`, `pet_sex`, `store_id`)
VALUES (31, 'jilo', 'blue', 'parrot', 6, 'MALE', 21),
(32, 'jack', 'black', 'dog', 2, 'MALE', 21),
(33, 'sam', 'gray', 'cat', 3, 'FEMALE', 21),
(34, 'kortor', 'green', 'cow', 4, 'MALE', 21),
(35, 'magor', 'pink', 'human', 5, 'MALE', 21),
(36, 'frinpkon', 'brown', 'human', 6, 'FEMALE', 21),
(37, 'riguz', 'black', 'goat', 7, 'MALE', 21);

SET  FOREIGN_KEY_CHECKS = 1;