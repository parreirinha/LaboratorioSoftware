	use lsTests;
--use ls

insert into Movie (MovieName, MovieRelease, OneStar, TwoStar, TreeStar, FourStar, FiveStar) 
	select 'Fight Club', 1999, 20, 10, 15, 50, 32 union all 
	select 'Seven', 1995, 5, 20, 40, 35, 22 union all 
	select 'The Matrix', 1999, 33, 14, 70, 15, 1 union all 
	select 'Inception', 2010, 0, 5, 30, 44, 60 union all
	select 'Pulp Fiction', 1994, 30, 8, 34, 13, 20 union all 
	select 'American History X', 1998, 1, 5, 20, 100, 50  union all 
	select 'The Silence of the Lambs', 1991, 2, 30, 11, 40,22;

	insert into Movie (MovieName, MovieRelease) values ('movie 1', 2000)

insert into Review (MovieID,ReviewerName, ReviewSummary,ReviewComplete,ReviewRating)
        select 1, 'Manel', 'Magnificent', 'An insomniac office worker, looking for a way to change his life, crosses paths with a devil-may-care soap maker, forming an underground fight club that evolves into something much, much more...',5 union all
        select 1, 'Bad taste Reviwer', 'Horrible', 'This is the worst movie i have seen in my life',1 union all
        select 6, 'Donald Trump', 'Love it', 'I love skinheads, but I still prefer my toupee', 5 union all
        select 2, 'Jack', 'Morgan Freeman is the best', 'Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his modus operandi', 2 union all 
        select 3, 'IT guy', 'interesting concept', 'In a futuristic world some heroes battles the forces of evil in a cybernetic world',3 union all 
        select 4, 'Manel', 'Film of the year candidate', 'Interesting concept movie about dreams hunters',5;
        


insert into Collections (Name, Description) 
	select 'STARWARS','serie de filmes da saga starwars' union all
	select 'Before 2000','movies before 2000' union all
	select 'movies after 2000', 'movies from this century';

insert into MovieCollection (CID, MovieID)
	select 2, 1 union all
	select 2, 2 union all
	select 2, 3 union all
	select 1, 4 union all
	select 2, 5 union all
	select 2, 6 union all
	select 2, 7 ;

