use ls;




if(OBJECT_ID('MovieCollection') IS NOT NULL) DROP TABLE dbo.MovieCollection
if(OBJECT_ID('Collections') IS NOT NULL) DROP TABLE dbo.Collections
if(OBJECT_ID('Review') IS NOT NULL) DROP TABLE dbo.Review
if(OBJECT_ID('Movie') IS NOT NULL) DROP TABLE dbo.Movie


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
	Rating float default 0.0,
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
	primary key (CollectionID)
)

create table MovieCollection
(
	CID integer,
	MovieID integer,
	primary key(CID, MovieID),
	foreign key (CID) references Collections (CollectionID),
	foreign key (MovieID) references Movie(MovieID)
);

/*
select *
from(
	select MC.CID, MC.MovieID, C.Name, C.Description, M.MovieName, ROW_NUMBER() OVER (ORDER BY MC.MovieID) AS RowNumber
		from MovieCollection as MC 
			inner join Collections as C on MC.CID = C.CollectionID
			inner join Movie as M on M.MovieID = MC.MovieID
		where CID = 2
	) as res	
where RowNumber BETWEEN 3 AND 6 */


select * from(
	select  MC.CID, MC.MovieID, C.Name, C.Description, M.MovieName, ROW_NUMBER() OVER (ORDER BY M.MovieID) AS RowNumber,
	CONVERT(DECIMAL(4,3), ((M.OneStar + M.TwoStar*2 + M.TreeStar * 3 + M.FourStar * 4 + M.FiveStar * 5)/ 
	cast(((M.OneStar + M.TwoStar + M.TreeStar + M.FourStar + M.FiveStar)) AS DECIMAL (4,0)))) as rating
		from MovieCollection as MC 
			inner join Collections as C on MC.CID = C.CollectionID
			inner join Movie as M on M.MovieID = MC.MovieID
		where CID = 2
) as res
where RowNumber BETWEEN 2 AND 5
order by rating



--select * from Review;
--select * from Movie;
select * from Collections;
select * from MovieCollection;


--drop table MovieCollection;
--drop table Collections;