SELECT 'CREATE DATABASE desafio_conductor' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'desafio_conductor')\gexec;

CREATE TABLE IF NOT EXISTS cliente (
  id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ) NOT NULL,
  nome varchar(100) NOT NULL,
  cpf character varying(11) NOT NULL,
  email character varying(255) NOT NULL,
  PRIMARY KEY (id)
);