----------- Procedures --------------

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
