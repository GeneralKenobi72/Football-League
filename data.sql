-- SEASON
INSERT INTO Season (Year) VALUES 
(2024);

-- CLUB
INSERT INTO Club (ClubName, ClubAddress) VALUES 
("FK Sloboda", "Gornji Podgradci"),
("FK Omladinac", "Zadruzna bb"),
("FK Kozara Turjak", "Turjak"),
("FK Obradovac", "Obradovac"),
("FK Biseri", "Catrnja"),
("FK Torpedo", "Cerovljani");

-- PLAYER
INSERT INTO Player (JMBG, Name, Surname, Age, NumberOfGames, TotalMinutes, NumberOfGoals, NumberOfAssists, NumberOfSaves, NumberOfConcededGoals) VALUES 
("2010002101508", "Ivan", "Ilic", 22, 11, 760, 2, 10, 0, 0),
("2010002101509", "Luka", "Zeljko", 22, 14, 1000, 2, 9, 0, 0),
("1923798217391", "Stefan", "Krstanovic", 22, 8, 118, 0, 0 ,0, 0),
("6128736821763", "Nikola", "Nikolic", 34, 45, 2000, 13, 0, 0, 0),
("8721638126388", "Marko", "Petrovic", 25, 22, 990, 0, 0, 30, 14);

-- PlayerStatsInOneMatch
INSERT INTO PlayerStatsInOneMatch (MatchID, PlayerJMBG, NumberOfAssists, NumberOfGoals, YellowCard, RedCard, NumberOfMinutes, StartingEleven) VALUES
(1, '2010002101508', 2, 1, 1, 0, 90, 1),
(1, '2010002101509', 1, 0, 0, 0, 85, 1),
(1, '1923798217391', 0, 0, 1, 0, 70, 1),
(2, '6128736821763', 0, 2, 0, 0, 88, 1),
(2, '8721638126388', 1, 1, 0, 1, 60, 1);

-- PlayerStatsInSeason
INSERT INTO PlayerStatsInSeason (SeasonYear, PlayerJMBG, NumberOfGames, NumberOfMinutes, NumberOfGoals, NumberOfAssists, NumberOfYellowCards, NumberOfRedCards, NumberOfTimesInStartingEleven, NumberOfConcededGoals, NumberOfSaves) VALUES
(2024, "2010002101508", 11, 760, 2, 10, 1, 0, 11, 0, 0),
(2024, "2010002101509", 14, 1000, 2, 9, 0, 0, 14, 0, 0),
(2024, "1923798217391", 8, 118, 0, 0 ,0, 0, 4, 0, 0),
(2024, "6128736821763", 45, 2000, 13, 0, 0, 0, 30, 0, 0),
(2024, '8721638126388', 29, 2600, 10, 4, 3, 0, 20, 0, 0);

-- Round
INSERT INTO `Round` (RoundNumber, SeasonYear) VALUES
(1, 2024),
(2, 2024),
(3, 2024),
(4, 2024),
(5, 2024);

-- Match
INSERT INTO `Match` (MatchID, DateTime, RoundNumber, SeasonYear) VALUES
(1, '2024-08-01 20:00:00', 1, 2024),
(2, '2024-08-03 18:00:00', 1, 2024),
(3, '2024-08-05 17:30:00', 2, 2024),
(4, '2024-08-07 21:00:00', 2, 2024),
(5, '2024-08-10 19:00:00', 3, 2024),
(6, '2024-08-12 16:00:00', 3, 2024),
(7, '2024-08-15 20:30:00', 4, 2024),
(8, '2024-08-17 18:30:00', 4, 2024),
(9, '2024-08-20 19:30:00', 5, 2024),
(10, '2024-08-22 20:00:00', 5, 2024),
(11, '2024-02-01 18:00:00', 1, 2024),
(12, '2024-02-03 20:00:00', 1, 2024),
(13, '2024-02-05 17:45:00', 2, 2024),
(14, '2024-02-07 19:00:00', 2, 2024),
(15, '2024-02-10 16:30:00', 3, 2024);

-- MatchStats
INSERT INTO MatchStats (MatchID, NumberOfGoalsHome, NumberOfGoalsGuests, NumberOfFans, NumberOfYellowCards, NumberOfRedCards, NumberOfCorners) VALUES
(1, 2, 1, 150, 3, 0, 5),
(2, 1, 1, 120, 4, 0, 3),
(3, 3, 0, 200, 2, 1, 8),
(4, 0, 2, 80, 5, 1, 4),
(5, 2, 2, 180, 3, 0, 6),
(6, 1, 3, 160, 4, 0, 7),
(7, 4, 1, 250, 1, 0, 9),
(8, 0, 0, 110, 2, 0, 2),
(9, 3, 3, 210, 6, 0, 5),
(10, 2, 1, 140, 3, 0, 4),
(11, 1, 0, 130, 2, 0, 3),
(12, 2, 3, 220, 5, 0, 10),
(13, 0, 1, 95, 1, 1, 1),
(14, 1, 2, 150, 3, 0, 6),
(15, 2, 1, 190, 4, 0, 8);

-- Referee
INSERT INTO Referee (JMBG, Name, Surname) VALUES
('9876543212345', 'Marko', 'Jovanović'),
('8765432109876', 'Nikola', 'Petrović'),
('7654321098767', 'Stefan', 'Ilić'),
('6543210987658', 'Dušan', 'Nikolić'),
('5432109876549', 'Milan', 'Marković'),
('4321098765430', 'Aleksandar', 'Popović'),
('3210987654321', 'Ivan', 'Tomić'),
('2109876543212', 'Jovan', 'Lazić'),
('1098765432103', 'Vladimir', 'Savić'),
('9987654321094', 'Bojan', 'Stojanović');

-- Coach
INSERT INTO Coach (JMBG, Name, Surname, Age) VALUES
('1112233445566', 'Aleksandar', 'Petrović', 40),
('2233445566777', 'Marko', 'Jovanović', 41),
('3344556677888', 'Nikola', 'Ilić', 55),
('4455667788999', 'Stefan', 'Nikolić', 38),
('5566778899000', 'Milan', 'Jovanović', 53),
('6677889900111', 'Dušan', 'Tomić', 47),
('7788990011222', 'Aleksandar', 'Stojanović', 47),
('8899001122333', 'Vladimir', 'Popović', 52),
('9900112233444', 'Bojan', 'Savić', 41),
('1011122334455', 'Jovan', 'Marković', 44);

-- Stadium
INSERT INTO Stadium (StadiumAdress, Capacity, Name) VALUES
('Zadruzna bb', 300, 'Stadion Omladinca'),
('Turjak', 100, 'Stadion Kozare'),
('Gornji Podgradci', 200, 'Stadion Slobode'),
('Obradovac', 100, 'Stadion Obradovca'),
('Catrnja', 150, 'Stadion Bisera'),
('Cerovljani', 150, 'Stadion Torpeda');
