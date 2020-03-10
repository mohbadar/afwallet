SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';

SET default_tablespace = '';

SET default_with_oids = false;




CREATE SCHEMA IF NOT EXISTS log;


CREATE TABLE log.eventlog
(
  log_id bigserial NOT NULL,
  entry_date timestamp without time zone,
  exception text,
  logger text,
  log_level character varying(255),
  message text,
  user_id bigint,
  env_slug character varying(255),
  CONSTRAINT eventlog_pkey PRIMARY KEY (log_id)
);
