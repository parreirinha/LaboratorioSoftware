use ls;


--use ls_tests
create table Movie
(
	MovieID integer identity(1, 1),
	MovieName varchar(50),
	MovieRelease integer,
	OneStar integer default 0,
	TwoStar integer default 0,
	TreeStar integer default 0,
	FourStar integer default 0,
	FiveStar integer default 0,
	AddedDate datetime not null default getdate(),
	unique(MovieName, MovieRelease),
	primary key(MovieID)
)
create table Review
(
	ReviewID integer identity(1, 1),
	MovieID integer,
	ReviewerName varchar(50) not null,
	ReviewSummary varchar(100) not null,
	ReviewComplete varchar(500) not null,
	ReviewRating integer not null check (ReviewRating >= 1 AND ReviewRating <= 5),
	foreign key(MovieID) references Movie(MovieID),
	primary key(ReviewID, MovieID)
)

create table Collections
(
	CollectionID integer identity(1, 1) unique,
	Name varchar(50),
	Description varchar(200),
	CreateDate date default getdate(),
	primary key (CollectionID)
)

create table MovieCollection
(
	CID integer,
	MovieID integer,
	AddedDate datetime not null default getdate(),
	primary key(CID, MovieID),
	foreign key (CID) references Collections (CollectionID),
	foreign key (MovieID) references Movie(MovieID)
);
/*
	select MC.CID, MC.MovieID, MC.AddedDate, C.Name, C.Description, C.CreateDate, M.MovieName
	from MovieCollection as MC 
	inner join Collections as C on MC.CID = C.CollectionID
	inner join Movie as M on M.MovieID = MC.MovieID
	where CID = 1
	
	*/

--select * from Review;
--select * from Movie;
--select * from Collections;
--select * from MovieCollection;


--drop table MovieCollection;
--drop table Collections;