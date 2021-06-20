drop table if exists Heroes CASCADE;


create table Heroes (
heroId bigint not null auto_increment, 
element varchar(255), level bigint, 
heroName varchar(255), 
weapon varchar(255),
levels int, 
team_id bigint, primary key (heroID));
