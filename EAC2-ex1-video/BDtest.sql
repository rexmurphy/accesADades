CREATE TABLE persona
(
nom character varying(30) not NULL,
colorPreferit character varying(40),
  CONSTRAINT clauPrimariaPersona PRIMARY KEY (nom)
)
WITH (
  OIDS=FALSE
);



ALTER TABLE persona
OWNER TO ioc;

