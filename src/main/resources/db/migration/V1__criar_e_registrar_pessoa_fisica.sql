CREATE TABLE PERSON_ENTITY(
    ID     BIGINT     	NOT NULL AUTO_INCREMENT,
    NAME   VARCHAR(60)  NOT NULL,
    CPF    VARCHAR(11)  NOT NULL,
    DATE_OF_BIRTH		DATE,
    PRIMARY KEY(ID)
);


CREATE SEQUENCE sequence_person_entity
    INCREMENT BY 1
    START WITH 1
    NO MINVALUE
    NO MAXVALUE
    NO CYCLE
    NO CACHE
;

insert into PERSON_ENTITY values (sequence_person_entity.NEXTVAL, 'João da Silva', '38545517842', '2018-12-28');
insert into PERSON_ENTITY values (sequence_person_entity.NEXTVAL, 'Fernando Cardoso', '12354469875', '2018-12-28');
insert into PERSON_ENTITY values (sequence_person_entity.NEXTVAL, 'Gustavo Santos', '54213325687', '2018-12-28');
insert into PERSON_ENTITY values (sequence_person_entity.NEXTVAL, 'Guilherme Bezerra', '53254463100', '2018-12-28');
insert into PERSON_ENTITY values (sequence_person_entity.NEXTVAL, 'Antonio Silveira', '45233365248', '2018-12-28');
