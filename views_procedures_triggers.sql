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

----------- Add Team ----------------
delimiter $$
create procedure add_team(in name varchar(45), in address varchar(45))
begin
	insert into Team(ClubName, ClubAddress) values (name, address);
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
