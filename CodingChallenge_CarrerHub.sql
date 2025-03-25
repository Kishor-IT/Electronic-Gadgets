drop database if exists CareerHub;
create database CareerHub;
use CareerHub;

create table if not exists Company(
    CompanyID int primary key auto_increment,
    CompanyName varchar(30) not null,
    Location varchar(100)
);

insert into Company (CompanyName,Location) values 
('Hexaware', 'Chennai'),
('Kovai.co', 'Coimbatore'),
('HCL', 'Australia'),
('HashAgile', 'Bangalore'),
('CTS', 'Chennai'),
('TCS', 'Pune'),
('Infosys', 'Chennai'),
('ServiceNow', 'Hyderabad'),
('RetailMart', 'Chennai'),
('CloudVoice', 'Bangalore');

select * from Company;

create table if not exists Jobs(
   JobId int primary key auto_increment,
   CompanyId int not null,
   JobTitle varchar(100) not null,
   JobDescription text,
   JobLocation varchar(50),
   Salary decimal(10,2),
   JobType varchar(20),
   PostedDate datetime,
   foreign key (CompanyId) references Company(CompanyId) on delete cascade
);

insert into Jobs (CompanyID, JobTitle, JobDescription, JobLocation, Salary, JobType, PostedDate) VALUES
(1, 'Software Developer', 'Develop and maintain software applications', 'Chennai', 50000, 'Full-time', NOW()),
(2, 'Data Scientist', 'Analyze complex data sets', 'Coimabtore', 42000, 'Full-time', NOW()),
(3, 'Senior Tester', 'Lead the testing team', 'Australia', 55000, 'Full-time', NOW()),
(4, 'Human Resource', 'Recruit new employees', 'Bangalore', 85000, 'Part-time', NOW()),
(5, 'Data Analyst', 'Analysis the data and requirements', 'Chennai', 75000, 'Full-time', NOW()),
(6, 'Team Lead', 'Advise team to improve efficieny', 'Pune', 70000, 'Full-time', NOW()),
(7, 'Manager', 'Innovate new Projects', 'Chennai', 80000, 'Full_time', NOW()),
(8, 'Senior Developer', 'Helps in coding and performance', 'Hyderabad', 85000, 'Full-time', NOW()),
(9, 'Marketing Executive', 'Distribution of the projects', 'Chennai', 90000, 'Contract', NOW()),
(10, 'Cloud Architect', 'Design and implement cloud solutions.', 'Bangalore', 130000, 'Full-time', NOW());

select * from Jobs;

create table if not exists Applicants(
   ApplicantId int primary key auto_increment,
   FirstName varchar(50) not null,
   LastName varchar(50) not null,
   Email varchar(50) unique,
   Phone varchar(20),
   Resume text
);

insert into Applicants(FirstName, LastName, Email, Phone, Resume) VALUES
('Ram', 'Kumar', 'ramKumar@gmail.com', '9998887771', 'Software Developer'),
('Raj', 'Kumar', 'rajkumar@gmail.com', '9998887772', 'Data Scientist'),
('Sanjay', 'Yadav', 'sanjay12@gmail.com', '9998887773', 'Senior Tester'),
('Virat', 'Kohli', 'evirat18@gmail.com', '9998887774', 'Human Resource'),
('Sai', 'Kishore', 'sai077@gmail.com', '9998887775', 'Data Analyst'),
('Rohit', 'Sharma', 'rohit45@gmail.com', '9998887776', 'Team Lead'),
('Mithali', 'Raj', 'mithali10@gmail.com', '9998887777', 'Manager'),
('Smiriti', 'Mandhana', 'smirithi18@gmail.com', '9998887778', 'Senior Developer'),
('Jemimah', 'Rodrigues', 'jemima@gmailcom', '9998887779', 'Marketing Executive'),
('Rinku', 'Singh', 'rinku55@gmail.com', '9996665842', 'Cloud Associate');

select * from Applicants;

create table if not exists Applications(
   ApplicationId int primary key auto_increment,
   JobId int not null,
   ApplicantId int not null,
   ApplicationDate datetime not null,
   CoverLetter text,
   foreign key (JobId) references Jobs(JobId) on delete cascade,
   foreign key (ApplicantId) references Applicants(ApplicantId) on delete cascade
);

insert into Applications (JobID, ApplicantID, ApplicationDate, CoverLetter) VALUES
(1, 1, NOW(), 'Eager to contribute to software development team and company.'),
(2, 2, NOW(), 'Very much interested to analyze and derive insights from data.'),
(3, 3, NOW(), 'Passionate about testing techniques which are to be released newly'),
(4, 4, NOW(), 'Experiance in Recruitment'),
(5, 5, NOW(), 'Expert in analysing data and requirements'),
(6, 6, NOW(), 'Innovative approach to improve quality of the project'),
(7, 7, NOW(), 'Experience in Organizing the work and employees'),
(8, 8, NOW(), 'Skilled in technology and AI '),
(9, 9, NOW(), 'Experienced in marketing field'),
(10, 10, NOW(), 'Expert in cloud computing and architecture.');

select * from Applications;

/*5.	Write an SQL query to count the number of applications received for each job listing in the "Jobs" table. Display the job title and the corresponding application count. Ensure that it lists all jobs, even if they have no applications. */
select J.JobTitle, count(A.ApplicationID) as ApplicationCount
from Jobs J left join Applications A on J.JobID = A.JobID
group by J.JobTitle;

/*6.	Develop an SQL query that retrieves job listings from the "Jobs" table within a specified salary range. Allow parameters for the minimum and maximum salary values. Display the job title, company name, location, and salary for each matching job. */
select J.JobTitle, C.CompanyName, J.JobLocation, J.Salary
from Jobs J join Company C on J.CompanyID = C.CompanyID
where J.Salary between 50000 and 100000;

/*7.	Write an SQL query that retrieves the job application history for a specific applicant. Allow a parameter for the ApplicantID, and return a result set with the job titles, company names, and application dates for all the jobs the applicant has applied to. */
select J.JobTitle, C.CompanyName, A.ApplicationDate
from Applications A join Jobs J on A.JobID = J.JobID
join Company C on J.CompanyID = C.CompanyID
where A.ApplicantID = 7;

/*8.	Create an SQL query that calculates and displays the average salary offered by all companies for job listings in the "Jobs" table. Ensure that the query filters out jobs with a salary of zero. */
select avg(Salary) as AverageSalary from Jobs where Salary > 0;

/*9.	Write an SQL query to identify the company that has posted the most job listings. Display the company name along with the count of job listings they have posted. Handle ties if multiple companies have the same maximum count. */
select CompanyName, count(JobID) as JobCount from Jobs J join
Company C on J.CompanyID = C.CompanyID
group by CompanyName having JobCount = 
    (select max(JobCount) from (select count(JobID) as JobCount from Jobs group by CompanyID)
    as JobCounts);
    
/*10.	Find the applicants who have applied for positions in companies located in 'CityX' and have at least 3 years of experience. */
select A.FirstName, A.LastName, A.Email from Applications X 
join Jobs J on X.JobID = J.JobID
join Company C on J.CompanyID = C.CompanyID
join Applicants A ON X.ApplicantID = A.ApplicantID where C.Location = 'Coimabtore'
and A.Resume like '%more than 3 years%';

/*11.	Retrieve a list of distinct job titles with salaries between $60,000 and $80,000. */
select distinct JobTitle from Jobs where Salary between 60000 and 80000;

/*12.	Find the jobs that have not received any applications. */
select JobTitle from Jobs where JobID not in (select JobID from Applications);

/*13.	Retrieve a list of job applicants along with the companies they have applied to and the positions they have applied for. */
select A.FirstName, A.LastName, C.CompanyName, J.JobTitle from Applications X 
join Applicants A on X.ApplicantID = A.ApplicantID 
join Jobs J on X.JobID = J.JobID 
join Company c on J.CompanyID = C.CompanyID;

/*14.	Retrieve a list of companies along with the count of jobs they have posted, even if they have not received any applications. */
select C.CompanyName, count(J.JobID) as JobCount from Company C 
left join Jobs J on C.CompanyID = J.CompanyID group by C.CompanyName;

/*15.	List all applicants along with the companies and positions they have applied for, including those who have not applied. */
select A.FirstName, A.LastName, C.CompanyName, J.JobTitle from Applicants A 
left join Applications X on A.ApplicantID = X.ApplicantID 
left join Jobs J on X.JobID = J.JobID 
left join Company C on J.CompanyID = C.CompanyID;

/*16.	Find companies that have posted jobs with a salary higher than the average salary of all jobs. */
select distinct C.CompanyName from Company C
join Jobs J on C.CompanyID = J.CompanyID
where J.Salary > (select avg(Salary) from Jobs);

/*17.	Display a list of applicants with their names and a concatenated string of their city and state. */
alter table Applicants add column City varchar(100),add column State varchar(100);

update Applicants set City = 'Chennai', State = 'TamilNadu' where ApplicantID = 1;
update Applicants set City = 'Coimabtore', State = 'TamilNadu' where ApplicantID = 2;
update Applicants set City = 'Melbourne', State = 'Australia' where ApplicantID = 3;
update Applicants set City = 'Bangalore', State = 'Karnataka' where ApplicantID = 4;
update Applicants set City = 'Chennai', State = 'TamilNadu' where ApplicantID = 5;
update Applicants set City = 'Pune', State = 'Mumbai' where ApplicantID = 6;
update Applicants set City = 'Chennai', State = 'TamilNadu' where ApplicantID = 7;
update Applicants set City = 'Hyderabad', State = 'Andhara Pradesh' where ApplicantID = 8;
update Applicants set City = 'Chennai', State = 'TamilNadu' where ApplicantID = 9;
update Applicants set City = 'Bangalore', State = 'Karnataka' where ApplicantID = 10;

select FirstName, LastName, concat(City, ', ', State) as Location from Applicants;

/*18.	Retrieve a list of jobs with titles containing either 'Developer' or 'Engineer'. */
select JobTitle, CompanyID, JobLocation, Salary from Jobs
where JobTitle like '%Software Developer%' or JobTitle like '%Engineer%';

/*19.	Retrieve a list of applicants and the jobs they have applied for, including those who have not applied and jobs without applicants. */
select A.FirstName, A.LastName,J.JobTitle,C.CompanyName
from Applicants A
left join Applications X on A.ApplicantID = X.ApplicantID
left join Jobs J on X.JobID = J.JobID
left join Company C on J.CompanyID = C.CompanyID
union
select A.FirstName,A.LastName,J.JobTitle,C.CompanyName
from Jobs J
left join Applications X on J.JobID = X.JobID
left join Applicants A on X.ApplicantID = A.ApplicantID
left join Company C on J.CompanyID = C.CompanyID;

/*20.	List all combinations of applicants and companies where the company is in a specific city and the applicant has more than 2 years of experience. For example: city=Chennai */
alter table Applicants add column Experience int;

update Applicants set Experience = 3 where ApplicantID=1;
update Applicants set Experience = 4 where ApplicantID=2;
update Applicants set Experience = 5 where ApplicantID=3;
update Applicants set Experience = 4 where ApplicantID=4;
update Applicants set Experience = 2 where ApplicantID=5;
update Applicants set Experience = 7 where ApplicantID=6;
update Applicants set Experience = 1 where ApplicantID=7;
update Applicants set Experience = 4 where ApplicantID=8;
update Applicants set Experience = 2 where ApplicantID=9;
update Applicants set Experience = 3 where ApplicantID=10;

select A.FirstName, A.LastName,C.CompanyName,C.Location
from Applicants A join Company C
where C.Location = 'Chennai'
and A.Experience > 2;







