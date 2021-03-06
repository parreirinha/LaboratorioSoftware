--use ls;
use lsTests

if(OBJECT_ID('MovieCollection') IS NOT NULL) DROP TABLE MovieCollection
if(OBJECT_ID('Collections') IS NOT NULL) DROP TABLE Collections
if(OBJECT_ID('Review') IS NOT NULL) DROP TABLE Review
if(OBJECT_ID('Movie') IS NOT NULL) DROP TABLE Movie

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
	Average as convert(decimal(4,3),((OneStar + TwoStar*2 + TreeStar * 3 + FourStar * 4 + FiveStar * 5) / 
                        cast(nullif((OneStar + TwoStar + TreeStar + FourStar + FiveStar),0) AS DECIMAL (4,0)))),
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
--passo 14
--update Movie set TreeStar=1 where MovieID=1
--update Movie set FiveStar=3 where MovieID=1

--passo16
--update Movie set OneStar=1 where MovieID=2
--update Movie set Fourstar=1 where MovieID=2

--select * from Review;
--select * from Movie;
--select * from Collections;
--select * from MovieCollection;

/*
select *
from(
	select MC.CID, MC.MovieID, C.Name, C.Description, M.MovieName, ROW_NUMBER() OVER (ORDER BY MC.MovieID) AS RowNumber
		from MovieCollection as MC 
			inner join Collections as C on MC.CID = C.CollectionID
			inner join Movie as M on M.MovieID = MC.MovieID
		where CID = 2
	) as res	
where RowNumber BETWEEN 1 AND 5 */

/*
select * from(
	select  MC.CID, MC.MovieID, C.Name, C.Description, M.MovieName, ROW_NUMBER() OVER (ORDER BY M.MovieID) AS RowNumber,
	CONVERT(DECIMAL(4,3), ((M.OneStar + M.TwoStar*2 + M.TreeStar * 3 + M.FourStar * 4 + M.FiveStar * 5)/ 
	cast(((M.OneStar + M.TwoStar + M.TreeStar + M.FourStar + M.FiveStar)) AS DECIMAL (4,0)))) as rating
from MovieCollection as MC 
			inner join Collections as C on MC.CID = C.CollectionID
			inner join Movie as M on M.MovieID = MC.MovieID
		where CID = 2
) as res
where RowNumber BETWEEN 1 AND 5
order by rating
*/


--drop table MovieCollection;
--drop table Collections;


/*
-- exemplo da query q esta em falta
select * 
from(
	select top (5) *,ROW_NUMBER() OVER (ORDER BY c desc) as Row_Count
		from ( 
			select R.MovieID, count(R.MovieID)as c 
				from Review as R
				group by R.MovieID) as ct
				inner join Movie as M on M.MovieId = ct.MovieID
				order by c desc
				) as res
where Row_Count BETWEEN 2 AND 3
*/

/*
select * from(
	select top (5) m.*, t.C
		from Movie as M 
		left join(
			select R.MovieID, count(R.MovieID)as c 
			from dbo.Review as R
			group by R.MovieID)as T on M.MovieID = T.MovieID
		order by c ) as z
		order by c OFFSET 0 ROWS FETCH NEXT 8 ROWS ONLY
		*/