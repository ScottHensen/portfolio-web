INSERT into enterprise.clients 
(	 user_id
	,first_name
    ,middle_name
    ,last_name
    ,legal_name
    ,avatar_id
	,creation_ts
	,creation_user_id
	,revision_ts
	,revision_user_id
) 
values 
(	 5
    ,'Robert'
    ,'Foo'
	,'Bar'
    ,'Robert F Bar'
    ,null 
	,current_timestamp
	,1
	,null
	,null
);
