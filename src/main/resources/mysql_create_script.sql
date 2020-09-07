create schema critter_db; -- Create the schema
create user 'critter_sa'@'localhost' identified by 'critter_sa_password'; -- Create the critter_sa user
grant all on critter_db.* to 'critter_sa'@'localhost'; -- Give all privileges to critter_sa user
