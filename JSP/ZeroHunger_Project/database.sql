
CREATE DATABASE zerohunger;
USE zerohunger;

CREATE TABLE users(
 user_id INT AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(100),
 email VARCHAR(100),
 password VARCHAR(100),
 role VARCHAR(10)
);

CREATE TABLE food_items(
 food_id INT AUTO_INCREMENT PRIMARY KEY,
 donor_id INT,
 food_name VARCHAR(100),
 quantity VARCHAR(50),
 location VARCHAR(150),
 posted_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 status VARCHAR(20)
);
