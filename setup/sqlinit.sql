create sequence "LOTS_lot_id_seq"
    as integer;

alter sequence "LOTS_lot_id_seq" owner to postgres;

create table if not exists users
(
    user_id   serial
        constraint users_pkey
            primary key,
    username  varchar(30)  not null,
    email     varchar(50)  not null,
    password  varchar(100) not null,
    firstname varchar(25),
    lastname  varchar(35)
);

alter table users
    owner to postgres;

create table if not exists items
(
    item_id     serial
        constraint "PK_items$item_id"
            primary key,
    title       varchar(60),
    description varchar(300)
);

alter table items
    owner to postgres;

create table if not exists lots
(
    lot_id      integer default nextval('"LOTS_lot_id_seq"'::regclass) not null
        constraint "LOTS_pkey"
            primary key,
    start_price double precision,
    stop_date   date                                                   not null,
    user_id     integer                                                not null
        constraint "FK_lots$users$user_id"
            references users,
    bid_id      integer
        constraint "FK_lots$users$bid_id"
            references users,
    item_id     integer                                                not null
        constraint "FK_lots$items$item_id"
            references items,
    bid_inc     double precision,
    isactive    boolean
);

alter table lots
    owner to postgres;

alter sequence "LOTS_lot_id_seq" owned by lots.lot_id;

create index if not exists fki_o
    on lots (user_id);

create table if not exists user_roles
(
    role_id    serial
        constraint user_roles_pk
            primary key,
    role_title varchar(15) not null
);

alter table user_roles
    owner to postgres;

create table if not exists users_user_roles
(
    users_user_id     integer not null
        constraint fks79bm3bk3u022txmw1djc5s3h
            references users,
    userroles_role_id integer not null
        constraint fkop9wfhgqfh0ejfxjimdlc3jxc
            references user_roles,
    constraint users_user_roles_pkey
        primary key (users_user_id, userroles_role_id)
);

alter table users_user_roles
    owner to postgres;
	
INSERT INTO postgres.public.user_roles(
	role_id, role_title)
	VALUES (1, 'ROLE_GUEST'), (2, 'ROLE_USER');
