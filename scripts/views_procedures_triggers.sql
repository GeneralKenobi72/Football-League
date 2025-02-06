----------- PROCEDURES --------------

----------- Add Season --------------
delimiter $$
create procedure add_season(in year int(4))
begin
	insert into Season(Year) values (year);
end $$
delimiter ;

----------- Add Player --------------
delimiter $$
create procedure add_player(in name varchar(45), in surname varchar(45), in jmbg char(13), in age int(2), in nog int(5), in tm int(6), in nogo int(4), in noas int(4), in nosa int(4), in nocg int(4))
begin
	insert into Player(Name, Surname, JMBG, Age, NumberOfGames, TotalMinutes, NumberOfGoals, NumberOfAssists, NumberOfSaves, NumberOfConcededGoals) values (name, surname, jmbg, age, nog, tm, nogo, noas, nosa,nocg);
end $$
delimiter ;

----------- Add Club ----------------
delimiter $$
create procedure add_club(in name varchar(45), in address varchar(45))
begin
	insert into Club(ClubName, ClubAddress) values (name, address);
end $$
delimiter ;

----------- Add Round --------------
delimiter $$
create procedure add_round(in round_number int(5), in season int(5))
begin
	insert into `Round`(RoundNumber, SeasonYear) values (round_number, season);
end $$
delimiter ;

----------- Add Match --------------
delimiter $$
create procedure add_match(in matchid int(5), in date_time datetime, in round_number int(5), in season int(5))
begin
	insert into `Match`(MatchID, DateTime, RoundNumber, SeasonYear) values (matchid, date_time, round_number, season);
end $$
delimiter ;

----------- Add MatchStats ---------
delimiter $$
create procedure add_matchstats(in matchid int(5), in nogh int(5), in nogg int(5), in nof int(6), in noyc int(2), in norc int(1), in noc int(3))
begin
	insert into MatchStats(MatchID, NumberOfGoalsHome, NumberOfGoalsGuests, NumberOfFans, NumberOfYellowCards, NumberOfRedCards, NumberOfCorners) values (matchid, nogh, nogg, nof, noyc, norc, noc);
end $$
delimiter ;

----------- Add Club_has_Match ----
delimiter $$
create procedure add_c_has_m(in club varchar(45), in matchid int(5), in role enum('Home', 'Away'))
begin
	insert into Club_has_Match(Club_ClubName, Match_MatchID, Role) values (club, matchid, role);
end $$
delimiter ;

DELIMITER $$

CREATE PROCEDURE add_full_match(
    IN p_matchid INT(5),
    IN p_date_time DATETIME,
    IN p_round_number INT(5),
    IN p_season INT(5),
    IN p_nogh INT(5),
    IN p_nogg INT(5),
    IN p_nof INT(6),
    IN p_noyc INT(2),
    IN p_norc INT(1),
    IN p_noc INT(3),
    IN p_home_club VARCHAR(45),
    IN p_away_club VARCHAR(45)
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
    END;

    START TRANSACTION;

    INSERT INTO `Match`(MatchID, DateTime, RoundNumber, SeasonYear)
    VALUES (p_matchid, p_date_time, p_round_number, p_season);

    INSERT INTO MatchStats(MatchID, NumberOfGoalsHome, NumberOfGoalsGuests, NumberOfFans, NumberOfYellowCards, NumberOfRedCards, NumberOfCorners)
    VALUES (p_matchid, p_nogh, p_nogg, p_nof, p_noyc, p_norc, p_noc);

    INSERT INTO Club_has_Match(Club_ClubName, Match_MatchID, Role)
    VALUES (p_home_club, p_matchid, 'Home');

    INSERT INTO Club_has_Match(Club_ClubName, Match_MatchID, Role)
    VALUES (p_away_club, p_matchid, 'Away');

    COMMIT;
END$$

DELIMITER ;

-------------- leaderboard --------------
delimiter $$
create procedure leaderboard(in season int(5))
begin
select c.ClubName, count(m.MatchID) as TotalMatches, sum(
														case
															when chm.Role = 'Home' and ms.NumberOfGOalsHome > ms.NumberOfGoalsGuests then 3
															when chm.Role = 'Away' and ms.NumberOfGOalsGuests > ms.NumberOfGoalsHome then 3
															when ms.NumberOfGOalsHome = ms.NumberOfGoalsGuests then 1
															else 0
														end
													) as TotalPoints, sum(
														case
															when chm.Role = 'Home' and ms.NumberOfGoalsHome > ms.NumberOfGoalsGuests then 1
															when chm.Role = 'Away' and ms.NumberOfGoalsGuests > ms.NumberOfGoalsHome then 1
															else 0
														end
													) as TotalWins, sum(
														case
															when chm.Role = 'Home' and ms.NumberOfGoalsHome < ms.NumberOfGoalsGuests then 1
															when chm.Role = 'Away' and ms.NumberOfGoalsGuests < ms.NumberOfGoalsHome then 1
															else 0
														end
													) as TotalLoses, sum(
														case
															when chm.Role = 'Home' and ms.NumberOfGoalsHome = ms.NumberOfGoalsGuests then 1
															when chm.Role = 'Away' and ms.NumberOfGoalsGuests = ms.NumberOfGoalsHome then 1
															else 0
														end
													)
from Club c
join Club_has_Match chm on c.ClubName = chm.Club_ClubName
join `Match` m on chm.Match_MatchID = m.MatchID
join MatchStats ms on m.MatchID = ms.MatchID
join Season s on m.SeasonYear = s.Year
where s.Year = season
group by c.ClubName
order by TotalPoints desc;
end $$
delimiter ;

-- Triggers

-- Before deleting Match, delete Club_has_Match and MatchStats
DELIMITER $$

CREATE TRIGGER before_match_delete
BEFORE DELETE ON `Match`
FOR EACH ROW
BEGIN
    DELETE FROM Club_has_Match WHERE Match_MatchID = OLD.MatchID;

    DELETE FROM MatchStats WHERE MatchID = OLD.MatchID;
END$$

Delimiter ;

-- Before deleting Round, delete Matches from Round

DELIMITER $$

CREATE TRIGGER before_round_delete
BEFORE DELETE ON `Round`
FOR EACH ROW
BEGIN
    DELETE FROM `Match` WHERE RoundNumber = OLD.RoundNumber AND SeasonYear = OLD.SeasonYear;
END$$

Delimiter ;

-- Before deleting Season, delete rounds

DELIMITER $$

CREATE TRIGGER before_season_delete
BEFORE DELETE ON `Season`
FOR EACH ROW
BEGIN
    DELETE FROM Round WHERE SeasonYear = OLD.Year;
END$$

DELIMITER ;

-- VIEWS

-- Clubs View

CREATE VIEW ClubStats AS
SELECT 
    c.ClubName AS Club,
    COUNT(chm.Match_MatchID) AS TotalMatches,
    SUM(
        CASE 
            WHEN chm.Role = 'Home' THEN ms.NumberOfGoalsHome
            WHEN chm.Role = 'Away' THEN ms.NumberOfGoalsGuests
            ELSE 0
        END
    ) AS TotalGoals,
	SUM(
		CASE
			WHEN chm.Role = 'Home' and ms.NumberOfGoalsHome > ms.NumberOfGoalsGuests THEN 1
			WHEN chm.Role = 'Away' and ms.NumberOfGoalsGuests > ms.NumberOfGoalsHome THEN 1
			else 0
		END
	) AS TotalWins,
	SUM(
		CASE 
			WHEN chm.Role = 'Home' and ms.NumberOfGoalsHome < ms.NumberOfGoalsGuests THEN 1
			WHEN chm.Role = 'Away' and ms.NumberOfGoalsGuests < ms.NumberOfGoalsHome THEN 1
			else 0
		END
	) AS TotalLosses,
	SUM(
		CASE 
			WHEN chm.Role = 'Home' and ms.NumberOfGoalsHome = ms.NumberOfGoalsGuests THEN 1
			WHEN chm.Role = 'Away' and ms.NumberOfGoalsGuests = ms.NumberOfGoalsHome THEN 1
			else 0
		END
	) AS TotalDraws
FROM 
    Club c
JOIN 
    Club_has_Match chm ON c.ClubName = chm.Club_ClubName
JOIN 
    MatchStats ms ON chm.Match_MatchID = ms.MatchID
GROUP BY 
    c.ClubName
ORDER BY
	TotalMatches desc;

-- Players View

CREATE VIEW PlayerStats AS
SELECT Name, Surname, Age, NumberOfGames, TotalMinutes, NumberOfGoals from Player
