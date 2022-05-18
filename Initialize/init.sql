-- Database: postgres

-- DROP DATABASE IF EXISTS postgres;

CREATE DATABASE postgres
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE postgres
    IS 'default administrative connection database';

-- SCHEMA: public

-- DROP SCHEMA IF EXISTS public ;

CREATE SCHEMA IF NOT EXISTS public
    AUTHORIZATION postgres;

COMMENT ON SCHEMA public
    IS 'standard public schema';

GRANT ALL ON SCHEMA public TO PUBLIC;

GRANT ALL ON SCHEMA public TO postgres;

-- Table: public.items

-- DROP TABLE IF EXISTS public.items;

CREATE TABLE IF NOT EXISTS public.items
(
    item_id integer NOT NULL DEFAULT nextval('items_item_id_seq'::regclass),
    title character varying(30) COLLATE pg_catalog."default",
    description character varying(80) COLLATE pg_catalog."default",
    CONSTRAINT "PK_items$item_id" PRIMARY KEY (item_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.items
    OWNER to postgres;

-- Table: public.lots

-- DROP TABLE IF EXISTS public.lots;

CREATE TABLE IF NOT EXISTS public.lots
(
    lot_id integer NOT NULL DEFAULT nextval('"LOTS_lot_id_seq"'::regclass),
    start_price double precision,
    stop_date date NOT NULL,
    user_id integer NOT NULL,
    bid_id integer,
    item_id integer NOT NULL,
    bid_inc double precision,
    CONSTRAINT "LOTS_pkey" PRIMARY KEY (lot_id),
    CONSTRAINT "FK_lots$items$item_id" FOREIGN KEY (item_id)
        REFERENCES public.items (item_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "FK_lots$users$bid_id" FOREIGN KEY (bid_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "FK_lots$users$user_id" FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.lots
    OWNER to postgres;
-- Index: fki_o

-- DROP INDEX IF EXISTS public.fki_o;

CREATE INDEX IF NOT EXISTS fki_o
    ON public.lots USING btree
    (user_id ASC NULLS LAST)
    TABLESPACE pg_default;

-- Table: public.user_roles

-- DROP TABLE IF EXISTS public.user_roles;

CREATE TABLE IF NOT EXISTS public.user_roles
(
    role_id integer NOT NULL DEFAULT nextval('user_roles_role_id_seq'::regclass),
    role_title character varying(15) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_roles_pk PRIMARY KEY (role_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user_roles
    OWNER to postgres;

-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    user_id integer NOT NULL DEFAULT nextval('users_user_id_seq'::regclass),
    username character varying(30) COLLATE pg_catalog."default" NOT NULL,
    email character varying(50) COLLATE pg_catalog."default" NOT NULL,
    password character varying(100) COLLATE pg_catalog."default" NOT NULL,
    firstname character varying(25) COLLATE pg_catalog."default",
    lastname character varying(35) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (user_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;

-- Table: public.users_user_roles

-- DROP TABLE IF EXISTS public.users_user_roles;

CREATE TABLE IF NOT EXISTS public.users_user_roles
(
    users_user_id integer NOT NULL,
    userroles_role_id integer NOT NULL,
    CONSTRAINT users_user_roles_pkey PRIMARY KEY (users_user_id, userroles_role_id),
    CONSTRAINT fkop9wfhgqfh0ejfxjimdlc3jxc FOREIGN KEY (userroles_role_id)
        REFERENCES public.user_roles (role_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fks79bm3bk3u022txmw1djc5s3h FOREIGN KEY (users_user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users_user_roles
    OWNER to postgres;
	
-- ADD USER_ROLES DATA

INSERT INTO postgres.public.user_roles(
	role_id, role_title)
	VALUES (1, ROLE_GUEST), (2, ROLE_USER);