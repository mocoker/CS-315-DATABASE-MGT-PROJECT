# Muyiwa Coker
# Church Member Contact Information and Church Donations

CREATE DATABASE mocoker_final_project_db;

GRANT ALL PRIVILEGES ON mocoker_final_project_db.* TO 'student'@'localhost';

FLUSH PRIVILEGES;

USE mocoker_final_project_db;

-- Creating the users table to allow permission to the application users if the login information is correct
CREATE TABLE Admin_Users	(
	userID_No VARCHAR(20) NOT NULL,
	first_Name VARCHAR(30) NOT NULL,
    last_Name VARCHAR(30) NOT NULL,
    user_Name VARCHAR(30) NOT NULL,
    pass_Word VARCHAR(100) NOT NULL,
    PRIMARY KEY(userID_No, user_Name));


-- Inserting some users to test the Admin_Users table and test the trigger created on the table.
INSERT INTO Admin_Users (userID_No, first_Name, last_Name, user_Name, pass_Word) VALUES
	('CA-001', 'John', 'Doe', 'jdoe', SHA('jdoe')),
    ('CA-002', 'Harry', 'Smith', 'hsmith', SHA('hsmith')),
    ('CA-003', 'Muyiwa', 'Coker', 'mcoker', SHA('mcoker')),
    ('CA-004', 'Joe', 'Cole', 'jcole', SHA('jcole')),
    ('CA-005', 'Kim', 'Church', 'kchurch', SHA('kchurch'));


-- Below is a trigger on App_User table to stop insert when the total number of users in the App_Users table is up to 5. 
delimiter //
CREATE TRIGGER tg_Admin_Users
	BEFORE INSERT ON Admin_Users
    FOR EACH ROW
	BEGIN
		IF 5 = (SELECT COUNT(first_Name) FROM Admin_Users)	THEN
			SIGNAL SQLSTATE '02000' SET MESSAGE_TEXT = 'THE APP USER TABLE ALREADY REACHED ITS USER LIMIT ! ! !';
		END IF;
	END;//
delimiter ;


-- Creating the Church Member Contacts table to record contacts of all church members as well as people making donation to the church *** MASTER LIST ***
CREATE TABLE Church_Member_Contacts	(
	Member_ID VARCHAR(10) NOT NULL,
	Full_Name VARCHAR(60) NOT NULL,
    Date_of_Birth DATE,
    Contact_Address VARCHAR(100),
    Phone_Number VARCHAR(20),
    E_Mail VARCHAR(40),
	Notes VARCHAR(20),
    Admin_Users VARCHAR(60),
    PRIMARY KEY(Member_ID));


-- Creating the Church Donations table to keep record of people donating to church, either cash donation or non-cash donation
CREATE TABLE Church_Donations	(
	Donation_ID VARCHAR(10) NOT NULL,
	Full_Name VARCHAR(60) NOT NULL,
    Cash_Donations DECIMAL(9,2) DEFAULT 0.00,
    Non_Cash_Donation VARCHAR(100) DEFAULT 'NONE',
    Phone_Number VARCHAR(20),
    E_Mail VARCHAR(40),
    Date_of_Donation DATE,
    Member_ID VARCHAR(10) NOT NULL,
    PRIMARY KEY(Donation_ID),
    FOREIGN KEY (Member_ID) REFERENCES Church_Member_Contacts(Member_ID) ON DELETE CASCADE);


-- Below is a trigger to insert the name of a donor into Church Member Contacts, if the name of the donor does not already exist in the Church Member Contact table.
delimiter //
CREATE TRIGGER tg_church_donation
	BEFORE INSERT ON Church_Donations
    FOR EACH ROW
	BEGIN
		IF NOT EXISTS (SELECT Member_ID FROM Church_Member_Contacts WHERE Member_ID = NEW.Member_ID) THEN
			INSERT INTO Church_Member_Contacts (Member_ID, Full_Name, Phone_Number, E_Mail) VALUES (NEW.Member_ID, NEW.Full_Name, NEW.Phone_Number, NEW.E_Mail);
		END IF;
	END;//
delimiter ;



-- The below insert statement is to check if my trigger on the Admin_Users table is functioning properly
insert into Admin_Users (userID_No, first_Name, last_Name, user_Name, pass_Word) values
	('CA-006', 'Jennie', 'Lee', 'jlee', SHA('jlee'));

