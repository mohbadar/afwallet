--
-- PostgreSQL database 
--
--
-- TOC entry 11 (class 2615 OID 58019)
-- Name: quartz_db; Type: SCHEMA; Schema: -; Owner: asims_user
--

CREATE SCHEMA quartz_db;


ALTER SCHEMA quartz_db OWNER TO asims_user;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 229 (class 1259 OID 57627)
-- Name: qrtz_blob_triggers; Type: TABLE; Schema: quartz_db; Owner: asims_user
--

CREATE TABLE quartz_db.qrtz_blob_triggers (
    sched_name character varying(120) NOT NULL,
    trigger_name character varying(200) NOT NULL,
    trigger_group character varying(200) NOT NULL,
    blob_data bytea
);


ALTER TABLE quartz_db.qrtz_blob_triggers OWNER TO asims_user;

--
-- TOC entry 230 (class 1259 OID 57640)
-- Name: qrtz_calendars; Type: TABLE; Schema: quartz_db; Owner: asims_user
--

CREATE TABLE quartz_db.qrtz_calendars (
    sched_name character varying(120) NOT NULL,
    calendar_name character varying(200) NOT NULL,
    calendar bytea NOT NULL
);


ALTER TABLE quartz_db.qrtz_calendars OWNER TO asims_user;

--
-- TOC entry 227 (class 1259 OID 57601)
-- Name: qrtz_cron_triggers; Type: TABLE; Schema: quartz_db; Owner: asims_user
--

CREATE TABLE quartz_db.qrtz_cron_triggers (
    sched_name character varying(120) NOT NULL,
    trigger_name character varying(200) NOT NULL,
    trigger_group character varying(200) NOT NULL,
    cron_expression character varying(120) NOT NULL,
    time_zone_id character varying(80)
);


ALTER TABLE quartz_db.qrtz_cron_triggers OWNER TO asims_user;

--
-- TOC entry 232 (class 1259 OID 57653)
-- Name: qrtz_fired_triggers; Type: TABLE; Schema: quartz_db; Owner: asims_user
--

CREATE TABLE quartz_db.qrtz_fired_triggers (
    sched_name character varying(120) NOT NULL,
    entry_id character varying(95) NOT NULL,
    trigger_name character varying(200) NOT NULL,
    trigger_group character varying(200) NOT NULL,
    instance_name character varying(200) NOT NULL,
    fired_time bigint NOT NULL,
    sched_time bigint NOT NULL,
    priority integer NOT NULL,
    state character varying(16) NOT NULL,
    job_name character varying(200),
    job_group character varying(200),
    is_nonconcurrent boolean,
    requests_recovery boolean
);


ALTER TABLE quartz_db.qrtz_fired_triggers OWNER TO asims_user;

--
-- TOC entry 224 (class 1259 OID 57567)
-- Name: qrtz_job_details; Type: TABLE; Schema: quartz_db; Owner: asims_user
--

CREATE TABLE quartz_db.qrtz_job_details (
    sched_name character varying(120) NOT NULL,
    job_name character varying(200) NOT NULL,
    job_group character varying(200) NOT NULL,
    description character varying(250),
    job_class_name character varying(250) NOT NULL,
    is_durable boolean NOT NULL,
    is_nonconcurrent boolean NOT NULL,
    is_update_data boolean NOT NULL,
    requests_recovery boolean NOT NULL,
    job_data bytea
);


ALTER TABLE quartz_db.qrtz_job_details OWNER TO asims_user;

--
-- TOC entry 234 (class 1259 OID 57666)
-- Name: qrtz_locks; Type: TABLE; Schema: quartz_db; Owner: asims_user
--

CREATE TABLE quartz_db.qrtz_locks (
    sched_name character varying(120) NOT NULL,
    lock_name character varying(40) NOT NULL
);


ALTER TABLE quartz_db.qrtz_locks OWNER TO asims_user;

--
-- TOC entry 231 (class 1259 OID 57648)
-- Name: qrtz_paused_trigger_grps; Type: TABLE; Schema: quartz_db; Owner: asims_user
--

CREATE TABLE quartz_db.qrtz_paused_trigger_grps (
    sched_name character varying(120) NOT NULL,
    trigger_group character varying(200) NOT NULL
);


ALTER TABLE quartz_db.qrtz_paused_trigger_grps OWNER TO asims_user;

--
-- TOC entry 233 (class 1259 OID 57661)
-- Name: qrtz_scheduler_state; Type: TABLE; Schema: quartz_db; Owner: asims_user
--

CREATE TABLE quartz_db.qrtz_scheduler_state (
    sched_name character varying(120) NOT NULL,
    instance_name character varying(200) NOT NULL,
    last_checkin_time bigint NOT NULL,
    checkin_interval bigint NOT NULL
);


ALTER TABLE quartz_db.qrtz_scheduler_state OWNER TO asims_user;

--
-- TOC entry 226 (class 1259 OID 57588)
-- Name: qrtz_simple_triggers; Type: TABLE; Schema: quartz_db; Owner: asims_user
--

CREATE TABLE quartz_db.qrtz_simple_triggers (
    sched_name character varying(120) NOT NULL,
    trigger_name character varying(200) NOT NULL,
    trigger_group character varying(200) NOT NULL,
    repeat_count bigint NOT NULL,
    repeat_interval bigint NOT NULL,
    times_triggered bigint NOT NULL
);


ALTER TABLE quartz_db.qrtz_simple_triggers OWNER TO asims_user;

--
-- TOC entry 228 (class 1259 OID 57614)
-- Name: qrtz_simprop_triggers; Type: TABLE; Schema: quartz_db; Owner: asims_user
--

CREATE TABLE quartz_db.qrtz_simprop_triggers (
    sched_name character varying(120) NOT NULL,
    trigger_name character varying(200) NOT NULL,
    trigger_group character varying(200) NOT NULL,
    str_prop_1 character varying(512),
    str_prop_2 character varying(512),
    str_prop_3 character varying(512),
    int_prop_1 integer,
    int_prop_2 integer,
    long_prop_1 bigint,
    long_prop_2 bigint,
    dec_prop_1 numeric(13,4),
    dec_prop_2 numeric(13,4),
    bool_prop_1 boolean,
    bool_prop_2 boolean
);


ALTER TABLE quartz_db.qrtz_simprop_triggers OWNER TO asims_user;

--
-- TOC entry 225 (class 1259 OID 57575)
-- Name: qrtz_triggers; Type: TABLE; Schema: quartz_db; Owner: asims_user
--

CREATE TABLE quartz_db.qrtz_triggers (
    sched_name character varying(120) NOT NULL,
    trigger_name character varying(200) NOT NULL,
    trigger_group character varying(200) NOT NULL,
    job_name character varying(200) NOT NULL,
    job_group character varying(200) NOT NULL,
    description character varying(250),
    next_fire_time bigint,
    prev_fire_time bigint,
    priority integer,
    trigger_state character varying(16) NOT NULL,
    trigger_type character varying(8) NOT NULL,
    start_time bigint NOT NULL,
    end_time bigint,
    calendar_name character varying(200),
    misfire_instr smallint,
    job_data bytea
);


ALTER TABLE quartz_db.qrtz_triggers OWNER TO asims_user;

--
-- TOC entry 2086 (class 2606 OID 57634)
-- Name: qrtz_blob_triggers qrtz_blob_triggers_pkey; Type: CONSTRAINT; Schema: quartz_db; Owner: asims_user
--

ALTER TABLE ONLY quartz_db.qrtz_blob_triggers
    ADD CONSTRAINT qrtz_blob_triggers_pkey PRIMARY KEY (sched_name, trigger_name, trigger_group);


--
-- TOC entry 2088 (class 2606 OID 57647)
-- Name: qrtz_calendars qrtz_calendars_pkey; Type: CONSTRAINT; Schema: quartz_db; Owner: asims_user
--

ALTER TABLE ONLY quartz_db.qrtz_calendars
    ADD CONSTRAINT qrtz_calendars_pkey PRIMARY KEY (sched_name, calendar_name);


--
-- TOC entry 2082 (class 2606 OID 57608)
-- Name: qrtz_cron_triggers qrtz_cron_triggers_pkey; Type: CONSTRAINT; Schema: quartz_db; Owner: asims_user
--

ALTER TABLE ONLY quartz_db.qrtz_cron_triggers
    ADD CONSTRAINT qrtz_cron_triggers_pkey PRIMARY KEY (sched_name, trigger_name, trigger_group);


--
-- TOC entry 2098 (class 2606 OID 57660)
-- Name: qrtz_fired_triggers qrtz_fired_triggers_pkey; Type: CONSTRAINT; Schema: quartz_db; Owner: asims_user
--

ALTER TABLE ONLY quartz_db.qrtz_fired_triggers
    ADD CONSTRAINT qrtz_fired_triggers_pkey PRIMARY KEY (sched_name, entry_id);


--
-- TOC entry 2064 (class 2606 OID 57574)
-- Name: qrtz_job_details qrtz_job_details_pkey; Type: CONSTRAINT; Schema: quartz_db; Owner: asims_user
--

ALTER TABLE ONLY quartz_db.qrtz_job_details
    ADD CONSTRAINT qrtz_job_details_pkey PRIMARY KEY (sched_name, job_name, job_group);


--
-- TOC entry 2102 (class 2606 OID 57670)
-- Name: qrtz_locks qrtz_locks_pkey; Type: CONSTRAINT; Schema: quartz_db; Owner: asims_user
--

ALTER TABLE ONLY quartz_db.qrtz_locks
    ADD CONSTRAINT qrtz_locks_pkey PRIMARY KEY (sched_name, lock_name);


--
-- TOC entry 2090 (class 2606 OID 57652)
-- Name: qrtz_paused_trigger_grps qrtz_paused_trigger_grps_pkey; Type: CONSTRAINT; Schema: quartz_db; Owner: asims_user
--

ALTER TABLE ONLY quartz_db.qrtz_paused_trigger_grps
    ADD CONSTRAINT qrtz_paused_trigger_grps_pkey PRIMARY KEY (sched_name, trigger_group);


--
-- TOC entry 2100 (class 2606 OID 57665)
-- Name: qrtz_scheduler_state qrtz_scheduler_state_pkey; Type: CONSTRAINT; Schema: quartz_db; Owner: asims_user
--

ALTER TABLE ONLY quartz_db.qrtz_scheduler_state
    ADD CONSTRAINT qrtz_scheduler_state_pkey PRIMARY KEY (sched_name, instance_name);


--
-- TOC entry 2080 (class 2606 OID 57595)
-- Name: qrtz_simple_triggers qrtz_simple_triggers_pkey; Type: CONSTRAINT; Schema: quartz_db; Owner: asims_user
--

ALTER TABLE ONLY quartz_db.qrtz_simple_triggers
    ADD CONSTRAINT qrtz_simple_triggers_pkey PRIMARY KEY (sched_name, trigger_name, trigger_group);


--
-- TOC entry 2084 (class 2606 OID 57621)
-- Name: qrtz_simprop_triggers qrtz_simprop_triggers_pkey; Type: CONSTRAINT; Schema: quartz_db; Owner: asims_user
--

ALTER TABLE ONLY quartz_db.qrtz_simprop_triggers
    ADD CONSTRAINT qrtz_simprop_triggers_pkey PRIMARY KEY (sched_name, trigger_name, trigger_group);


--
-- TOC entry 2078 (class 2606 OID 57582)
-- Name: qrtz_triggers qrtz_triggers_pkey; Type: CONSTRAINT; Schema: quartz_db; Owner: asims_user
--

ALTER TABLE ONLY quartz_db.qrtz_triggers
    ADD CONSTRAINT qrtz_triggers_pkey PRIMARY KEY (sched_name, trigger_name, trigger_group);


--
-- TOC entry 2091 (class 1259 OID 57686)
-- Name: idx_qrtz_ft_inst_job_req_rcvry; Type: INDEX; Schema: quartz_db; Owner: asims_user
--

CREATE INDEX idx_qrtz_ft_inst_job_req_rcvry ON quartz_db.qrtz_fired_triggers USING btree (sched_name, instance_name, requests_recovery);


--
-- TOC entry 2092 (class 1259 OID 57687)
-- Name: idx_qrtz_ft_j_g; Type: INDEX; Schema: quartz_db; Owner: asims_user
--

CREATE INDEX idx_qrtz_ft_j_g ON quartz_db.qrtz_fired_triggers USING btree (sched_name, job_name, job_group);


--
-- TOC entry 2093 (class 1259 OID 57688)
-- Name: idx_qrtz_ft_jg; Type: INDEX; Schema: quartz_db; Owner: asims_user
--

CREATE INDEX idx_qrtz_ft_jg ON quartz_db.qrtz_fired_triggers USING btree (sched_name, job_group);


--
-- TOC entry 2094 (class 1259 OID 57689)
-- Name: idx_qrtz_ft_t_g; Type: INDEX; Schema: quartz_db; Owner: asims_user
--

CREATE INDEX idx_qrtz_ft_t_g ON quartz_db.qrtz_fired_triggers USING btree (sched_name, trigger_name, trigger_group);


--
-- TOC entry 2095 (class 1259 OID 57690)
-- Name: idx_qrtz_ft_tg; Type: INDEX; Schema: quartz_db; Owner: asims_user
--

CREATE INDEX idx_qrtz_ft_tg ON quartz_db.qrtz_fired_triggers USING btree (sched_name, trigger_group);


--
-- TOC entry 2096 (class 1259 OID 57685)
-- Name: idx_qrtz_ft_trig_inst_name; Type: INDEX; Schema: quartz_db; Owner: asims_user
--

CREATE INDEX idx_qrtz_ft_trig_inst_name ON quartz_db.qrtz_fired_triggers USING btree (sched_name, instance_name);


--
-- TOC entry 2061 (class 1259 OID 57672)
-- Name: idx_qrtz_j_grp; Type: INDEX; Schema: quartz_db; Owner: asims_user
--

CREATE INDEX idx_qrtz_j_grp ON quartz_db.qrtz_job_details USING btree (sched_name, job_group);


--
-- TOC entry 2062 (class 1259 OID 57671)
-- Name: idx_qrtz_j_req_recovery; Type: INDEX; Schema: quartz_db; Owner: asims_user
--

CREATE INDEX idx_qrtz_j_req_recovery ON quartz_db.qrtz_job_details USING btree (sched_name, requests_recovery);


--
-- TOC entry 2065 (class 1259 OID 57675)
-- Name: idx_qrtz_t_c; Type: INDEX; Schema: quartz_db; Owner: asims_user
--

CREATE INDEX idx_qrtz_t_c ON quartz_db.qrtz_triggers USING btree (sched_name, calendar_name);


--
-- TOC entry 2066 (class 1259 OID 57676)
-- Name: idx_qrtz_t_g; Type: INDEX; Schema: quartz_db; Owner: asims_user
--

CREATE INDEX idx_qrtz_t_g ON quartz_db.qrtz_triggers USING btree (sched_name, trigger_group);


--
-- TOC entry 2067 (class 1259 OID 57673)
-- Name: idx_qrtz_t_j; Type: INDEX; Schema: quartz_db; Owner: asims_user
--

CREATE INDEX idx_qrtz_t_j ON quartz_db.qrtz_triggers USING btree (sched_name, job_name, job_group);


--
-- TOC entry 2068 (class 1259 OID 57674)
-- Name: idx_qrtz_t_jg; Type: INDEX; Schema: quartz_db; Owner: asims_user
--

CREATE INDEX idx_qrtz_t_jg ON quartz_db.qrtz_triggers USING btree (sched_name, job_group);


--
-- TOC entry 2069 (class 1259 OID 57679)
-- Name: idx_qrtz_t_n_g_state; Type: INDEX; Schema: quartz_db; Owner: asims_user
--

CREATE INDEX idx_qrtz_t_n_g_state ON quartz_db.qrtz_triggers USING btree (sched_name, trigger_group, trigger_state);


--
-- TOC entry 2070 (class 1259 OID 57678)
-- Name: idx_qrtz_t_n_state; Type: INDEX; Schema: quartz_db; Owner: asims_user
--

CREATE INDEX idx_qrtz_t_n_state ON quartz_db.qrtz_triggers USING btree (sched_name, trigger_name, trigger_group, trigger_state);


--
-- TOC entry 2071 (class 1259 OID 57680)
-- Name: idx_qrtz_t_next_fire_time; Type: INDEX; Schema: quartz_db; Owner: asims_user
--

CREATE INDEX idx_qrtz_t_next_fire_time ON quartz_db.qrtz_triggers USING btree (sched_name, next_fire_time);


--
-- TOC entry 2072 (class 1259 OID 57682)
-- Name: idx_qrtz_t_nft_misfire; Type: INDEX; Schema: quartz_db; Owner: asims_user
--

CREATE INDEX idx_qrtz_t_nft_misfire ON quartz_db.qrtz_triggers USING btree (sched_name, misfire_instr, next_fire_time);


--
-- TOC entry 2073 (class 1259 OID 57681)
-- Name: idx_qrtz_t_nft_st; Type: INDEX; Schema: quartz_db; Owner: asims_user
--

CREATE INDEX idx_qrtz_t_nft_st ON quartz_db.qrtz_triggers USING btree (sched_name, trigger_state, next_fire_time);


--
-- TOC entry 2074 (class 1259 OID 57683)
-- Name: idx_qrtz_t_nft_st_misfire; Type: INDEX; Schema: quartz_db; Owner: asims_user
--

CREATE INDEX idx_qrtz_t_nft_st_misfire ON quartz_db.qrtz_triggers USING btree (sched_name, misfire_instr, next_fire_time, trigger_state);


--
-- TOC entry 2075 (class 1259 OID 57684)
-- Name: idx_qrtz_t_nft_st_misfire_grp; Type: INDEX; Schema: quartz_db; Owner: asims_user
--

CREATE INDEX idx_qrtz_t_nft_st_misfire_grp ON quartz_db.qrtz_triggers USING btree (sched_name, misfire_instr, next_fire_time, trigger_group, trigger_state);


--
-- TOC entry 2076 (class 1259 OID 57677)
-- Name: idx_qrtz_t_state; Type: INDEX; Schema: quartz_db; Owner: asims_user
--

CREATE INDEX idx_qrtz_t_state ON quartz_db.qrtz_triggers USING btree (sched_name, trigger_state);


--
-- TOC entry 2107 (class 2606 OID 57635)
-- Name: qrtz_blob_triggers qrtz_blob_triggers_sched_name_fkey; Type: FK CONSTRAINT; Schema: quartz_db; Owner: asims_user
--

ALTER TABLE ONLY quartz_db.qrtz_blob_triggers
    ADD CONSTRAINT qrtz_blob_triggers_sched_name_fkey FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES quartz_db.qrtz_triggers(sched_name, trigger_name, trigger_group);


--
-- TOC entry 2105 (class 2606 OID 57609)
-- Name: qrtz_cron_triggers qrtz_cron_triggers_sched_name_fkey; Type: FK CONSTRAINT; Schema: quartz_db; Owner: asims_user
--

ALTER TABLE ONLY quartz_db.qrtz_cron_triggers
    ADD CONSTRAINT qrtz_cron_triggers_sched_name_fkey FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES quartz_db.qrtz_triggers(sched_name, trigger_name, trigger_group);


--
-- TOC entry 2104 (class 2606 OID 57596)
-- Name: qrtz_simple_triggers qrtz_simple_triggers_sched_name_fkey; Type: FK CONSTRAINT; Schema: quartz_db; Owner: asims_user
--

ALTER TABLE ONLY quartz_db.qrtz_simple_triggers
    ADD CONSTRAINT qrtz_simple_triggers_sched_name_fkey FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES quartz_db.qrtz_triggers(sched_name, trigger_name, trigger_group);


--
-- TOC entry 2106 (class 2606 OID 57622)
-- Name: qrtz_simprop_triggers qrtz_simprop_triggers_sched_name_fkey; Type: FK CONSTRAINT; Schema: quartz_db; Owner: asims_user
--

ALTER TABLE ONLY quartz_db.qrtz_simprop_triggers
    ADD CONSTRAINT qrtz_simprop_triggers_sched_name_fkey FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES quartz_db.qrtz_triggers(sched_name, trigger_name, trigger_group);


--
-- TOC entry 2103 (class 2606 OID 57583)
-- Name: qrtz_triggers qrtz_triggers_sched_name_fkey; Type: FK CONSTRAINT; Schema: quartz_db; Owner: asims_user
--

ALTER TABLE ONLY quartz_db.qrtz_triggers
    ADD CONSTRAINT qrtz_triggers_sched_name_fkey FOREIGN KEY (sched_name, job_name, job_group) REFERENCES quartz_db.qrtz_job_details(sched_name, job_name, job_group);


-- Completed on 2019-12-04 10:58:50 +0430

--
-- PostgreSQL database dump complete
--
