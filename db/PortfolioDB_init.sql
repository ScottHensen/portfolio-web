INSERT into portfolio.portfolios
(
	 portfolio_name
	,client_id
	,avatar_id
	,creation_ts
	,creation_user_id
	,revision_ts
	,revision_user_id
)
values
(
	 'Der Foo Bar Astro Portenfolio'
	,1
	,null
	,current_timestamp
	,1
	,null
	,null
);
INSERT into portfolio.portfolios
(
	 portfolio_name
	,client_id
	,avatar_id
	,creation_ts
	,creation_user_id
	,revision_ts
	,revision_user_id
)
values
(
	 'Der Foo Bar Framewurk Portenfolio'
	,1
	,null
	,current_timestamp
	,1
	,null
	,null
);
INSERT into portfolio.portfolio_securities 
(	 portfolio_id
	,security_symbol
    ,security_name
    ,sector_name
	,creation_ts
	,creation_user_id
	,revision_ts
	,revision_user_id
) 
values 
(	 1
	,'SAM'
    ,'Boston Beer Company Inc. (The)'
    ,'Consumer Defensive'
	,current_timestamp
	,1
	,null
	,null
);
INSERT into portfolio.portfolio_securities 
(	 portfolio_id
	,security_symbol
    ,security_name
    ,sector_name
	,creation_ts
	,creation_user_id
	,revision_ts
	,revision_user_id
) 
values 
(	 1
	,'TSLA'
    ,'Tesla Inc.'
    ,'Consumer Cyclical'
	,current_timestamp
	,1
	,null
	,null
);
INSERT into portfolio.portfolio_securities 
(	 portfolio_id
	,security_symbol
    ,security_name
    ,sector_name
	,creation_ts
	,creation_user_id
	,revision_ts
	,revision_user_id
) 
values 
(	 2
	,'SAM'
    ,'Boston Beer Company Inc. (The)'
    ,'Consumer Defensive'
	,current_timestamp
	,1
	,null
	,null
);
INSERT into portfolio.portfolio_securities 
(	 portfolio_id
	,security_symbol
    ,security_name
    ,sector_name
	,creation_ts
	,creation_user_id
	,revision_ts
	,revision_user_id
) 
values 
(	 2
	,'PVTL'
    ,'Pivotal Software Inc. Class A'
    ,'Technology'
	,current_timestamp
	,1
	,null
	,null
);
