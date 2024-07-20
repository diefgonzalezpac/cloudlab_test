-- Insert data into the 'comics' table
INSERT INTO comics (name) VALUES ('Marvel Comics'),('DC Comics');
-- Insert data into the 'superheros' table
INSERT INTO superheroes (name, civil_name, comic_id, origin, description) VALUES ('Superman', 'Clark Kent', 2, 'Krypton', 'Superman is a superhero with incredible strength, speed, and various other superpowers. He was sent to Earth from the planet Krypton and raised as Clark Kent in Smallville, Kansas. As Superman, he fights for truth, justice, and the American way.'),('Batman', 'Bruce Wayne', 2, 'Gotham City, United States', 'Batman is a superhero who fights crime in the dark and gritty city of Gotham. He is known for his intelligence, martial arts skills, and high-tech gadgets. As Batman, he seeks to protect Gotham from its many dangerous criminals and supervillains.'),('Spider-Man', 'Peter Parker', 1, 'Queens, New York, United States', 'Batman is a superhero who fights crime in the dark and gritty city of Gotham. He is known for his intelligence, martial arts skills, and high-tech gadgets. As Batman, he seeks to protect Gotham from its many dangerous criminals and supervillains.'),('Wonder Woman', 'Diana Prince', 2, 'Themyscira Island', 'Wonder Woman is an Amazonian princess and a warrior with superhuman strength and speed. She uses her powers to fight for justice and equality for all. She hails from Themyscira, an island of Amazon warriors.'),('Captain America', 'Steve Rogers', 1, 'New York City, United States', 'Captain America is a patriotic superhero who fights for American values and freedom. He was originally a scrawny young man named Steve Rogers who was given a serum that transformed him into a super-soldier. He now fights for justice and freedom around the world.'),('Iron Man', 'Tony Stark', 1, 'Long Island, New York, United States', 'Iron Man is a wealthy businessman named Tony Stark who builds a suit of high-tech armor to fight crime and defend the world from danger. He is known for his intelligence, technology, and wit.'),('Black Widow', 'Natasha Romanoff', 1, 'Russia', 'Black Widow is a highly trained spy and assassin who works for S.H.I.E.L.D. and the Avengers. She is skilled in martial arts, espionage, and weapons, and uses her abilities to fight for justice and protect the innocent.'),('Thor', 'Thor Odinson', 1, 'Asgard', 'Thor is a powerful god of thunder from the realm of Asgard. He wields a mighty hammer called Mjolnir and is known for his strength, bravery, and honor. He fights to protect Asgard and the universe from threats both earthly and otherworldly.'),('Hulk', 'Bruce Banner', 1, 'Los Angeles County, California, United States', 'The Hulk is a massive, green-skinned creature with incredible strength and endurance. He is actually Bruce Banner, a scientist who was exposed to gamma radiation and now transforms into the Hulk when he gets angry or stressed.'),('Flash', 'Barry Allen', 2, 'Central City, United States', 'The Flash is a superhero with incredible speed and agility. He is known for his red costume and lightning bolt symbol. The Flash, whose real name is Barry Allen, fights crime in the city of Central and uses his speed to travel through time and dimensions.');
-- Insert data into the 'powers' table
INSERT INTO powers (name) VALUES ('Super strength'),('Super speed'),('Flight'),('Teleportation'),('Telekinesis'),('X-ray vision'),('Invisibility'),('Regeneration'),('Shapeshifting'),('Elasticity'),('Telepathy'),('Intangibility'),('Fire manipulation'),('Ice manipulation'),('Water manipulation'),('Earth manipulation'),('Air manipulation'),('Lightning manipulation'),('Gravity manipulation'),('Time manipulation'),('Reality manipulation');

-- Insert data into the 'superheros_powers' table
-- Superman has super strength, super speed, and flight
INSERT INTO superheroes_powers (superhero_id, power_id) VALUES (1, 1),(1, 2),(1, 3);
-- Batman has super strength, is super fast, and has X-ray vision
INSERT INTO superheroes_powers (superhero_id, power_id) VALUES (2, 1),(2, 2),(2, 6);
-- Spider-Man can swing webs and has super strength
INSERT INTO superheroes_powers (superhero_id, power_id) VALUES (3, 1),(3, 3);
-- Wonder Woman has super strength, flight, and regenerative abilities
INSERT INTO superheroes_powers (superhero_id, power_id) VALUES (4, 1),(4, 3),(4, 8);
-- Captain America has super strength and regenerative abilities
INSERT INTO superheroes_powers (superhero_id, power_id) VALUES (5, 1),(5, 8);
-- Iron Man can fly and has X-ray vision
INSERT INTO superheroes_powers (superhero_id, power_id) VALUES (6, 3),(6, 6);
-- Black Widow has telekinesis and can turn invisible
INSERT INTO superheroes_powers (superhero_id, power_id) VALUES (7, 5),(7, 7);
-- Thor has super strength, flight, and lightning manipulation abilities
INSERT INTO superheroes_powers (superhero_id, power_id) VALUES (8, 1),(8, 3),(8, 18);
-- Hulk has super strength and regeneration abilities
INSERT INTO superheroes_powers (superhero_id, power_id) VALUES (9, 1),(9, 8);
-- Flash has super speed and can manipulate time
INSERT INTO superheroes_powers (superhero_id, power_id) VALUES (10, 2),(10, 20);