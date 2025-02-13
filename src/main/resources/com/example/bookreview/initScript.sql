

CREATE TABLE Person (
    UserID SERIAL PRIMARY KEY,
    Name varchar(50)  NOT NULL,
    Role varchar(20)  NOT NULL CHECK(Role IN ('User','Author','Admin')),
    DateOfBirth date  NOT NULL,
    Password varchar(255) NOT NULL
);

SELECT * FROM Person;

CREATE TABLE Book (
    BookID SERIAL PRIMARY KEY,
    AuthorID int  NOT NULL,
    Title varchar(100)  NOT NULL,
    FOREIGN KEY (AuthorID) REFERENCES Person(UserID) ON DELETE CASCADE
);


CREATE TABLE Reviews (
    ReviewID SERIAL PRIMARY KEY,
    BookID int  NOT NULL,
    ReviewerID int  NOT NULL,
    Rating smallint CHECK(Rating BETWEEN 1 AND 5)  NOT NULL,
    ReviewText text  DEFAULT NULL,
    FOREIGN KEY (BookID) REFERENCES Book(bookid) ON DELETE CASCADE,
    FOREIGN KEY (ReviewerID) REFERENCES Person(UserID) ON DELETE CASCADE
);

INSERT INTO Person (Name, Password, DateOfBirth, Role) VALUES
('John Doe', 'john', '1985-06-15', 'Author'),
('Jane Smith', 'jane', '1992-08-22', 'User'),
('Alice Johnson', 'alice', '1978-01-10', 'Admin'),
('Robert Brown', 'robert', '1990-11-30', 'User'),
('Emily White', 'emily', '1983-04-25', 'Author');
INSERT INTO Reviews (BookID, ReviewerID, Rating, ReviewText) VALUES
(1, 2, 5, 'This book provides a deep understanding of PostgreSQL internals.'),
(2, 4, 4, 'Great explanations, but some examples could be clearer.'),
(3, 2, 3, 'A decent book but lacking in practical exercises.'),
(4, 2, 5, 'Excellent guide on SQL optimization!'),
(5, 4, 4, 'Very useful for learning database indexing.');
INSERT INTO Book (AuthorID, Title) VALUES
(1, 'Mastering PostgreSQL'),
(5, 'The Art of Java Programming'),
(1, 'Advanced Database Design'),
(5, 'Effective SQL Queries'),
(1, 'Understanding Indexing in Databases');





