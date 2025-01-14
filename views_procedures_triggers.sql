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
create procedure add_player(in name varchar(45), in surname varchar(45), in age int(2), in nog int(5), in tm int(6), in nogo int(4), in noas int(4), in nosa int(4), in nocg int(4))
begin
	insert into Player(Name, Surname, Age, NumberOfGames, TotalMinutes, NumberOfGoals, NumberOfAssists, NumberOfSaves, NumberOfConcededGoals) values (name, surname, age, nog, tm, nogo, noas, nosa,nocg);
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

-- After add club, add Season_has_Club
DELIMITER $$

CREATE TRIGGER after_club_insert
AFTER INSERT ON Club
FOR EACH ROW
BEGIN
    INSERT INTO Season_has_Club (SeasonYear, ClubName, NumberOfDefeats, NumberOfVictories, NumberOfScoredGoals, NumberOfConcededGoals, Points)
    VALUES (NEW.SeasonYear, NEW.ClubName, NULL, NULL, NULL, NULL);  -- Postavljamo ostale kolone na NULL
END $$

DELIMITER ;


-- VIEWS
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
