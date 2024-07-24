drop database if exists `doctorcare`;
create database if not exists `doctorcare`;
use `doctorcare`;
create table `roles`(
	`id` int(11) primary key auto_increment primary key,
    `name` varchar(255),
    `createdAt` datetime default current_timestamp,
    `updatedAt` datetime default current_timestamp on update current_timestamp,
    `deletedAt` datetime);
create table `specializations`(
	`id` int(11) auto_increment primary key,
	`name` varchar(255),
    `description` text,
    `image` varchar(255),
    `createdAt` datetime default current_timestamp,
    `updatedAt` datetime default current_timestamp on update current_timestamp,
    `deletedAt` datetime);
create table `statuses`(
	`id` int(11) auto_increment primary key,
    `name` varchar(255),
    `createdAt` datetime default current_timestamp,
    `updatedAt` datetime default current_timestamp on update current_timestamp,
    `deletedAt` datetime);
create table `clinics`(
	`id` int(11) primary key auto_increment,
    `name` varchar(255),
    `address` varchar(255),
    `phone` varchar(255),
    `introductionHTML` text,
    `introductionMarkdown` text,
    `description` text,
    `image` varchar(255),
    `createdAt` datetime default current_timestamp,
    `updatedAt` datetime default current_timestamp on update current_timestamp,
    `deletedAt` datetime
    );
create table `places`(
	`id` int(11) primary key auto_increment,
    `name` varchar(255),
    `createdAt` datetime default current_timestamp,
    `updatedAt` datetime default current_timestamp on update current_timestamp,
    `deletedAt` datetime);
create table `session`(
	`id` int(11) auto_increment primary key,
    `expires` datetime,
    `data` text,
    `createdAt` datetime default current_timestamp,
    `updatedAt` datetime default current_timestamp on update current_timestamp);
create table `users`(
	`id` int(11) auto_increment primary key,
    `name` varchar(255),
    `email` varchar(255),
    `password` varchar(255),
    `address` varchar(255),
    `phone` varchar(255),
    `avatar` varchar(255),
    `gender` varchar(255),
    `description` text,
    `roleId` int(11),
    `isActive` tinyInt(1),
    `createdAt` datetime default current_timestamp,
    `updatedAt` datetime default current_timestamp on update current_timestamp,
    `deletedAt` datetime,
    constraint `fk_users_roles` foreign key(`roleId`) references `roles`(id));
create table `schedules`(
	`id` int(11) auto_increment primary key,
    `doctorId` int(11),
    `date` varchar(255),
    `time` varchar(255),
    `maxBooking` varchar(255),
    `sumBooking` varchar(255),
    `createdAt` datetime default current_timestamp,
    `updatedAt` datetime default current_timestamp on update current_timestamp,
    `deletedAt` datetime,
    constraint `fk_schedules_users` foreign key(`doctorId`) references `users`(id)
    );
create table `doctor_users`(
	`id` int(11) auto_increment primary key,
    `doctorId` int(11),
    `clinicId` int(11),
    `specializationId` int(11),
    `createdAt` datetime default current_timestamp,
    `updatedAt` datetime default current_timestamp on update current_timestamp,
    `deletedAt` datetime,
    constraint `fk_du_users` foreign key(`doctorId`) references `users`(id),
    constraint `fk_du_specialization` foreign key(`specializationId`) references `specializations`(id),
    constraint `fk_du_clinics` foreign key(`clinicId`) references `clinics` (id));
create table `patients`(
	`id` int(11) auto_increment primary key,
    `doctorId` int(11),
    `statusId` int(11),
    `name` varchar(255),
    constraint `fk_patients_statuses` foreign key(`statusId`) references `statuses`(id));
create table `extrainfos`(
	`id` int(11) primary key auto_increment,
    `patientId` int(11),
    `historyBreath` text,
    `placeId` int(11),
    `oldForms` text,
    `sendForms` text,
    `moreInfo` text,
    `createdAt` datetime default current_timestamp,
    `updatedAt` datetime default current_timestamp on update current_timestamp,
    `deletedAt` datetime,
    constraint `fk_extrainfos_users` foreign key(`patientId`) references `users`(id),
    constraint `fk_extrainfos_places` foreign key(`placeId`) references `places`(id));
create table `posts`(
	`id` int(11) primary key auto_increment,
    `title` varchar(255),
    `contentMarkdown` text,
    `contentHTML` text,
    `forDoctorId` int(11),
    `forSpecializationId` int(11),
    `forClinicId` int(11),
    `writerId` int(11),
    `confirmByDoctor` tinyint(1),
    `image` varchar(255),
    `createdAt` datetime default current_timestamp,
    `updatedAt` datetime default current_timestamp on update current_timestamp,
    `deletedAt` datetime,
    constraint `fk_posts_usesr_doctor` foreign key(`forDoctorId`) references `users`(id),
    constraint `fk_posts_usesr_writer` foreign key(`writerId`) references `users`(id));
create table `supporterlogs`(
	`id` int(11) primary key auto_increment,
    `patientId` int(11),
    `supporterId` int(11),
    `content` varchar(255),
    `createdAt` datetime default current_timestamp,
    `updatedAt` datetime default current_timestamp on update current_timestamp,
    `deletedAt` datetime,
    constraint `fk_supporterlogs_users_patient` foreign key(`patientId`) references `users`(id),
    constraint `fk_supporterlogs_users_supporter` foreign key(`supporterId`) references `users`(id));
create table `comments`(
	`id` int(11) primary key auto_increment,
    `doctorId` int(11),
    `timeBooking` varchar(255),
    `dateBooking` varchar(255),
    `name` varchar(255),
    `phone` varchar(255),
    `content` text,
    `status` tinyint(1),
    `createdAt` datetime default current_timestamp,
    `updatedAt` datetime default current_timestamp on update current_timestamp,
    `deletedAt` datetime);

-- Procedure lấy chuyên khoa nổi bật được đặt lịch khám nhiều nhất
DELIMITER $$

CREATE PROCEDURE proc_getHighlightedSpecializations(IN limitCount INT)
BEGIN
    SELECT *
    FROM specializations s
    JOIN doctor_users du ON s.id = du.specializationId
    JOIN schedules sch ON du.doctorId = sch.doctorId
    GROUP BY s.id
    ORDER BY COUNT(sch.id) DESC
    LIMIT limitCount;
END $$

DELIMITER ;

-- CALL proc_getHighlightedSpecializations(4);

-- Chèn dữ liệu mẫu vào bảng `roles`
INSERT INTO `roles` (`name`) VALUES
('Doctor'),
('Patient'),
('Supporter');

-- Chèn dữ liệu mẫu vào bảng `specializations`
INSERT INTO `specializations` (`name`, `description`, `image`) VALUES
('Cardiology', 'Heart specialist', 'cardiology.jpg'),
('Neurology', 'Brain and nervous system specialist', 'neurology.jpg'),
('Pediatrics', 'Child specialist', 'pediatrics.jpg'),
('Orthopedics', 'Bone specialist', 'orthopedics.jpg'),
('Dermatology', 'Skin specialist', 'dermatology.jpg');

-- Chèn dữ liệu mẫu vào bảng `statuses`
INSERT INTO `statuses` (`name`) VALUES
('Pending'),
('Confirmed'),
('Cancelled');

-- Chèn dữ liệu mẫu vào bảng `clinics`
INSERT INTO `clinics` (`name`, `address`, `phone`, `introductionHTML`, `introductionMarkdown`, `description`, `image`) VALUES
('Central Clinic', '123 Main St', '123-456-7890', '<p>Welcome to Central Clinic</p>', 'Welcome to Central Clinic', 'Best clinic in town', 'clinic1.jpg'),
('Northside Clinic', '456 North St', '234-567-8901', '<p>Welcome to Northside Clinic</p>', 'Welcome to Northside Clinic', 'Top-rated clinic in the north', 'clinic2.jpg'),
('Southside Clinic', '789 South St', '345-678-9012', '<p>Welcome to Southside Clinic</p>', 'Welcome to Southside Clinic', 'Trusted clinic in the south', 'clinic3.jpg'),
('Eastside Clinic', '101 East St', '456-789-0123', '<p>Welcome to Eastside Clinic</p>', 'Welcome to Eastside Clinic', 'Reliable clinic in the east', 'clinic4.jpg'),
('Westside Clinic', '202 West St', '567-890-1234', '<p>Welcome to Westside Clinic</p>', 'Welcome to Westside Clinic', 'Preferred clinic in the west', 'clinic5.jpg');

-- Chèn dữ liệu mẫu vào bảng `places`
INSERT INTO `places` (`name`) VALUES
('Room 101'),
('Room 102'),
('Room 103'),
('Room 104'),
('Room 105');

-- Chèn dữ liệu mẫu vào bảng `users`
INSERT INTO `users` (`name`, `email`, `password`, `address`, `phone`, `avatar`, `gender`, `description`, `roleId`, `isActive`) VALUES
('John Doe', 'john.doe@example.com', '$2a$12$ymhAWx6huPdzpZujLBsV.ekqZyD1SoPu3jqoDKEdFw7dJ2kT6bdxa', '123 Main St', '123-456-7890', 'john.jpg', 'Male', 'Experienced doctor', 1, 1),
('Alice White', 'alice.white@example.com', '$2a$12$ymhAWx6huPdzpZujLBsV.ekqZyD1SoPu3jqoDKEdFw7dJ2kT6bdxa', '456 Birch St', '234-567-8901', 'alice.jpg', 'Female', 'Pediatric doctor', 1, 1),
('Jane Smith', 'jane.smith@example.com', '$2a$12$ymhAWx6huPdzpZujLBsV.ekqZyD1SoPu3jqoDKEdFw7dJ2kT6bdxa', '456 Elm St', '987-654-3210', 'jane.jpg', 'Female', 'Patient', 2, 1),
('Bob Brown', 'bob.brown@example.com', '$2a$12$ymhAWx6huPdzpZujLBsV.ekqZyD1SoPu3jqoDKEdFw7dJ2kT6bdxa', '101 Pine St', '456-789-0123', 'bob.jpg', 'Male', 'Supporter', 3, 1),
('Charlie Davis', 'charlie.davis@example.com', '$2a$12$ymhAWx6huPdzpZujLBsV.ekqZyD1SoPu3jqoDKEdFw7dJ2kT6bdxa', '202 Cedar St', '567-890-1234', 'charlie.jpg', 'Male', 'Supporter', 3, 1);

-- Chèn dữ liệu mẫu vào bảng `schedules`
INSERT INTO `schedules` (`doctorId`, `date`, `time`, `maxBooking`, `sumBooking`) VALUES
(1, '2024-07-21', '09:00', 10, 0),
(1, '2024-07-22', '10:00', 10, 1),
(1, '2024-07-23', '11:00', 10, 2),
(2, '2024-07-24', '12:00', 10, 3),
(2, '2024-07-25', '13:00', 10, 4);

-- Chèn dữ liệu mẫu vào bảng `doctor_users`
INSERT INTO `doctor_users` (`doctorId`, `clinicId`, `specializationId`) VALUES
(1, 1, 1),
(1, 2, 2),
(2, 3, 3),
(2, 4, 4),
(2, 5, 5);

-- Chèn dữ liệu mẫu vào bảng `patients`
INSERT INTO `patients` (`doctorId`, `statusId`, `name`) VALUES
(1, 1, 'Patient A'),
(1, 2, 'Patient B'),
(2, 3, 'Patient C'),
(2, 1, 'Patient D'),
(2, 2, 'Patient E');

-- Chèn dữ liệu mẫu vào bảng `extrainfos`
INSERT INTO `extrainfos` (`patientId`, `historyBreath`, `placeId`, `oldForms`, `sendForms`, `moreInfo`) VALUES
(3, 'No issues', 1, 'None', 'None', 'None'),
(3, 'Asthma', 2, 'Inhaler', 'Regular check-up', 'Follow-up in 6 months'),
(4, 'Bronchitis', 3, 'Antibiotics', 'Regular check-up', 'Avoid smoking'),
(5, 'COPD', 4, 'Oxygen therapy', 'Annual check-up', 'Monitor closely'),
(3, 'Healthy', 5, 'None', 'Regular check-up', 'Keep active');

-- Chèn dữ liệu mẫu vào bảng `posts`
INSERT INTO `posts` (`title`, `contentMarkdown`, `contentHTML`, `forDoctorId`, `forSpecializationId`, `forClinicId`, `writerId`, `confirmByDoctor`, `image`) VALUES
('Health Tips 1', '## Health Tips 1', '<h2>Health Tips 1</h2>', 1, 1, 1, 1, 1, 'tips1.jpg'),
('Health Tips 2', '## Health Tips 2', '<h2>Health Tips 2</h2>', 1, 2, 2, 2, 1, 'tips2.jpg'),
('Health Tips 3', '## Health Tips 3', '<h2>Health Tips 3</h2>', 2, 3, 3, 3, 1, 'tips3.jpg'),
('Health Tips 4', '## Health Tips 4', '<h2>Health Tips 4</h2>', 2, 4, 4, 4, 1, 'tips4.jpg'),
('Health Tips 5', '## Health Tips 5', '<h2>Health Tips 5</h2>', 2, 5, 5, 5, 1, 'tips5.jpg');

-- Chèn dữ liệu mẫu vào bảng `supporterlogs`
INSERT INTO `supporterlogs` (`patientId`, `supporterId`, `content`) VALUES
(3, 4, 'Support log entry 1'),
(3, 4, 'Support log entry 2'),
(4, 5, 'Support log entry 3'),
(5, 5, 'Support log entry 4'),
(3, 4, 'Support log entry 5');

-- Chèn dữ liệu mẫu vào bảng `comments`
INSERT INTO `comments` (`doctorId`, `timeBooking`, `dateBooking`, `name`, `phone`, `content`, `status`) VALUES
(1, '10:00', '2024-07-21', 'Bob Brown', '555-555-5555', 'Great service', 1),
(1, '11:00', '2024-07-22', 'Alice Johnson', '666-666-6666', 'Very satisfied', 1),
(2, '12:00', '2024-07-23', 'Charlie Davis', '777-777-7777', 'Good experience', 1),
(2, '13:00', '2024-07-24', 'John Doe', '888-888-8888', 'Highly recommend', 1),
(2, '14:00', '2024-07-25', 'Jane Smith', '999-999-9999', 'Will come back', 1);

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    