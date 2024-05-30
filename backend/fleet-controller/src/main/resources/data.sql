insert into realms ( tenant, name ) values ('master', 'master');
insert into permissions ( name ) values ('admin');
insert into users ( realm_tenant , username , password , active ) values ('master', 'lucas', '$2a$12$iW3eR2hoALdZ9V5womXHMOc3.7jaJ3Ao5W51yHDkSUD687GklNzXm', 1);
insert into user_permissions ( user_id , permission_id ) values (1,1);