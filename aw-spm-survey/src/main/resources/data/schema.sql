

CREATE TABLE public.m_survey_components (
    id bigint NOT NULL,
    description character varying(4096),
    a_key character varying(32),
    sequence_no integer,
    a_text character varying(255),
    survey_id bigint
);


ALTER TABLE public.m_survey_components OWNER TO asims_user;



CREATE SEQUENCE public.m_survey_components_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



CREATE TABLE public.m_survey_lookup_tables (
    id bigint NOT NULL,
    description character varying(4096),
    a_key character varying(32),
    score double precision,
    value_from integer,
    value_to integer,
    survey_id bigint
);




CREATE SEQUENCE public.m_survey_lookup_tables_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



ALTER SEQUENCE public.m_survey_lookup_tables_id_seq OWNED BY public.m_survey_lookup_tables.id;


CREATE TABLE public.m_survey_questions (
    id bigint NOT NULL,
    component_key character varying(32),
    description character varying(4096),
    a_key character varying(32),
    sequence_no integer,
    a_text character varying(255),
    survey_id bigint
);




CREATE SEQUENCE public.m_survey_questions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




ALTER SEQUENCE public.m_survey_questions_id_seq OWNED BY public.m_survey_questions.id;



CREATE TABLE public.m_survey_responses (
    id bigint NOT NULL,
    sequence_no integer,
    a_text character varying(255),
    a_value integer,
    question_id bigint
);



CREATE SEQUENCE public.m_survey_responses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



ALTER SEQUENCE public.m_survey_responses_id_seq OWNED BY public.m_survey_responses.id;



CREATE TABLE public.m_survey_scorecards (
    id bigint NOT NULL,
    created_on timestamp without time zone,
    a_value integer,
    question_id bigint,
    response_id bigint,
    survey_id bigint
);



CREATE SEQUENCE public.m_survey_scorecards_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



ALTER SEQUENCE public.m_survey_scorecards_id_seq OWNED BY public.m_survey_scorecards.id;



CREATE TABLE public.m_surveys (
    id bigint NOT NULL,
    country_code character varying(2),
    description character varying(4096),
    a_key character varying(32),
    a_name character varying(255),
    valid_from timestamp without time zone,
    valid_to timestamp without time zone
);




CREATE SEQUENCE public.m_surveys_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




ALTER SEQUENCE public.m_surveys_id_seq OWNED BY public.m_surveys.id;





ALTER TABLE ONLY public.m_survey_components ALTER COLUMN id SET DEFAULT nextval('public.m_survey_components_id_seq'::regclass);



ALTER TABLE ONLY public.m_survey_lookup_tables ALTER COLUMN id SET DEFAULT nextval('public.m_survey_lookup_tables_id_seq'::regclass);




ALTER TABLE ONLY public.m_survey_questions ALTER COLUMN id SET DEFAULT nextval('public.m_survey_questions_id_seq'::regclass);


ALTER TABLE ONLY public.m_survey_responses ALTER COLUMN id SET DEFAULT nextval('public.m_survey_responses_id_seq'::regclass);



ALTER TABLE ONLY public.m_survey_scorecards ALTER COLUMN id SET DEFAULT nextval('public.m_survey_scorecards_id_seq'::regclass);




ALTER TABLE ONLY public.m_surveys ALTER COLUMN id SET DEFAULT nextval('public.m_surveys_id_seq'::regclass);








ALTER TABLE ONLY public.m_survey_components
    ADD CONSTRAINT m_survey_components_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.m_survey_lookup_tables
    ADD CONSTRAINT m_survey_lookup_tables_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.m_survey_questions
    ADD CONSTRAINT m_survey_questions_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.m_survey_responses
    ADD CONSTRAINT m_survey_responses_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.m_survey_scorecards
    ADD CONSTRAINT m_survey_scorecards_pkey PRIMARY KEY (id);




ALTER TABLE ONLY public.m_surveys
    ADD CONSTRAINT m_surveys_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.sample_entity
    ADD CONSTRAINT sample_entity_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.cors
    ADD CONSTRAINT uk_delmogs573hpobhyemau3dckw UNIQUE (allowed_method);



ALTER TABLE ONLY public.m_survey_components
    ADD CONSTRAINT fk2mr2ysvk92dlhk39uts0559wv FOREIGN KEY (survey_id) REFERENCES public.m_surveys(id);


ALTER TABLE ONLY public.m_survey_lookup_tables
    ADD CONSTRAINT fk9om6iidr2akukxqfcwlano7mr FOREIGN KEY (survey_id) REFERENCES public.m_surveys(id);


ALTER TABLE ONLY public.m_survey_scorecards
    ADD CONSTRAINT fkciapdkpaltupwgrfpudnii8to FOREIGN KEY (response_id) REFERENCES public.m_survey_responses(id);



ALTER TABLE ONLY public.m_survey_scorecards
    ADD CONSTRAINT fkefsk0usa96ldgly1b32mgqrq2 FOREIGN KEY (question_id) REFERENCES public.m_survey_questions(id);



ALTER TABLE ONLY public.m_survey_responses
    ADD CONSTRAINT fkn0vj5589uf8gc9xb9r7bjs2fl FOREIGN KEY (question_id) REFERENCES public.m_survey_questions(id);

ALTER TABLE ONLY public.m_survey_scorecards
    ADD CONSTRAINT fko880ym10thkg1h0edfa8b8nrl FOREIGN KEY (survey_id) REFERENCES public.m_surveys(id);




ALTER TABLE ONLY public.m_survey_questions
    ADD CONSTRAINT fkrv41kkh26bxtlks9lhdl1efpa FOREIGN KEY (survey_id) REFERENCES public.m_surveys(id);



