DROP TABLE IF EXISTS Teams cascade;

create table Teams (
teamID bigint not null auto_increment,
teamName varchar(255), primary key (teamId));
