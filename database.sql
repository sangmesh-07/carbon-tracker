CREATE DATABASE carbon_tracker;
USE carbon_tracker;

CREATE TABLE IF NOT EXISTS carbon_history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entry_date DATE NOT NULL,
    electricity DOUBLE,
    fuel DOUBLE,
    travel DOUBLE,
    total DOUBLE
);

-- Example insert (note the correct date format 'YYYY-MM-DD')
INSERT INTO carbon_history (entry_date, electricity, fuel, travel, total)
VALUES ('2004-02-03', 20, 30, 100, 400);
select *from carbon_history;