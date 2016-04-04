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

select * from Movie;
select * from Review;
