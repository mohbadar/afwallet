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

-- drop schema public cascade;

CREATE SCHEMA IF NOT EXISTS public;

--
-- TOC entry 196 (class 1259 OID 35769)
-- Name: datasource; Type: TABLE; Schema: public; Owner: asims_user
--

CREATE TABLE public.datasource (
    id bigint NOT NULL,
    dbname character varying(255),
    ssl character varying(255),
    db_pswrd character varying(255),
    env_slug character varying(255),
    host character varying(255),
    name character varying(255),
    port character varying(255),
    user_name character varying(255)
);


ALTER TABLE public.datasource OWNER TO asims_user;

--
-- TOC entry 219 (class 1259 OID 35918)
-- Name: datasource_seq; Type: SEQUENCE; Schema: public; Owner: asims_user
--

CREATE SEQUENCE public.datasource_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.datasource_seq OWNER TO asims_user;

--
-- TOC entry 197 (class 1259 OID 35777)
-- Name: environment; Type: TABLE; Schema: public; Owner: asims_user
--

CREATE TABLE public.environment (
    id bigint NOT NULL,
    active boolean,
    created_at timestamp without time zone,
    description character varying(255),
    name character varying(255),
    slug character varying(255),
    updated_at timestamp without time zone
);


ALTER TABLE public.environment OWNER TO asims_user;

--
-- TOC entry 220 (class 1259 OID 35920)
-- Name: environment_seq; Type: SEQUENCE; Schema: public; Owner: asims_user
--

CREATE SEQUENCE public.environment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.environment_seq OWNER TO asims_user;

--
-- TOC entry 198 (class 1259 OID 35785)
-- Name: environment_user; Type: TABLE; Schema: public; Owner: asims_user
--

CREATE TABLE public.environment_user (
    environment_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.environment_user OWNER TO asims_user;

--
-- TOC entry 199 (class 1259 OID 35788)
-- Name: form; Type: TABLE; Schema: public; Owner: asims_user
--

CREATE TABLE public.form (
    id bigint NOT NULL,
    active boolean,
    created_at timestamp without time zone,
    description character varying(255),
    env_slug character varying(255),
    name character varying(255),
    _schema_etag character varying(255),
    form_type character varying(255),
    form_category integer NOT NULL DEFAULT(0),
    show_on_map boolean,
    has_geometry boolean NOT NULL,
    updated_at timestamp without time zone,
    xml_content text,
    xml_form_id character varying(255),
    workflow_id bigint,
    default_workflow_step character varying(255)
);
CREATE TABLE public.folder (
    id bigint NOT NULL,

    created_at timestamp without time zone,
    env_slug character varying(255),
    name character varying(255),
    type character varying(255),
    path character varying(1000),
    is_directory boolean,
    updated_at timestamp without time zone
);


ALTER TABLE public.form OWNER TO asims_user;
ALTER TABLE public.folder OWNER TO asims_user;

--
-- TOC entry 200 (class 1259 OID 35796)
-- Name: form_form; Type: TABLE; Schema: public; Owner: asims_user
--

CREATE TABLE public.form_form (
    form_id bigint NOT NULL,
    child_form_id bigint NOT NULL
);


ALTER TABLE public.form_form OWNER TO asims_user;

--
-- TOC entry 201 (class 1259 OID 35799)
-- Name: form_group; Type: TABLE; Schema: public; Owner: asims_user
--

CREATE TABLE public.form_group (
    form_id bigint NOT NULL,
    group_id bigint NOT NULL
);


ALTER TABLE public.form_group OWNER TO asims_user;

--
-- TOC entry 221 (class 1259 OID 35922)
-- Name: form_seq; Type: SEQUENCE; Schema: public; Owner: asims_user
--

CREATE SEQUENCE public.form_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.form_seq OWNER TO asims_user;

--
-- TOC entry 202 (class 1259 OID 35802)
-- Name: group_role; Type: TABLE; Schema: public; Owner: asims_user
--

CREATE TABLE public.group_role (
    group_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE public.group_role OWNER TO asims_user;

--
-- TOC entry 203 (class 1259 OID 35805)
-- Name: group_tbl; Type: TABLE; Schema: public; Owner: asims_user
--

CREATE TABLE public.group_tbl (
    id bigint NOT NULL,
    active boolean NOT NULL,
    description character varying(255),
    env_slug character varying(255),
    name character varying(255)
);


ALTER TABLE public.group_tbl OWNER TO asims_user;

--
-- TOC entry 222 (class 1259 OID 35924)
-- Name: group_tbl_seq; Type: SEQUENCE; Schema: public; Owner: asims_user
--

CREATE SEQUENCE public.group_tbl_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.group_tbl_seq OWNER TO asims_user;

--
-- TOC entry 204 (class 1259 OID 35813)
-- Name: instance; Type: TABLE; Schema: public; Owner: asims_user
--

CREATE TABLE public.instance (
    id bigint NOT NULL,
    active boolean,
    is_first_update boolean,
    _row_id character varying(255),
    _row_etag character varying(255),
    created_at timestamp without time zone,
    current_step character varying(255),
    env_slug character varying(255),
    instance_device_id character varying(255),
    instance_end_time timestamp without time zone,
    instance_folder_name character varying(255),
    instance_start_time timestamp without time zone,
    instance_submission character varying(255),
    title character varying(255),
    updated_at timestamp without time zone,
    version integer,
    json_content text,
    xml_content text,
    form_id bigint NOT NULL,
    owner_id bigint,
    parent_instance_id bigint
);


ALTER TABLE public.instance OWNER TO asims_user;

CREATE INDEX instance_form_id_index ON public.instance USING btree (form_id ASC NULLS LAST) TABLESPACE pg_default;

--
-- TOC entry 205 (class 1259 OID 35821)
-- Name: instance_assignee_history; Type: TABLE; Schema: public; Owner: asims_user
--

CREATE TABLE public.instance_assignee_history (
    id bigint NOT NULL,
    env_slug character varying(255)
);


ALTER TABLE public.instance_assignee_history OWNER TO asims_user;

--
-- TOC entry 223 (class 1259 OID 35926)
-- Name: instance_assignee_history_seq; Type: SEQUENCE; Schema: public; Owner: asims_user
--

CREATE SEQUENCE public.instance_assignee_history_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.instance_assignee_history_seq OWNER TO asims_user;

--
-- TOC entry 206 (class 1259 OID 35826)
-- Name: instance_attachment; Type: TABLE; Schema: public; Owner: asims_user
--

CREATE TABLE public.instance_attachment (
    id bigint NOT NULL,
    attachment character varying(255),
    created_at timestamp without time zone,
    env_slug character varying(255),
    updated_at timestamp without time zone,
    instance_id bigint,
    user_id bigint
);


ALTER TABLE public.instance_attachment OWNER TO asims_user;

--
-- TOC entry 224 (class 1259 OID 35928)
-- Name: instance_attachment_seq; Type: SEQUENCE; Schema: public; Owner: asims_user
--

CREATE SEQUENCE public.instance_attachment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.instance_attachment_seq OWNER TO asims_user;

--
-- TOC entry 207 (class 1259 OID 35834)
-- Name: instance_comment; Type: TABLE; Schema: public; Owner: asims_user
--

CREATE TABLE public.instance_comment (
    id bigint NOT NULL,
    comment character varying(255),
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    instance_id bigint,
    user_id bigint
);


ALTER TABLE public.instance_comment OWNER TO asims_user;

--
-- TOC entry 225 (class 1259 OID 35930)
-- Name: instance_comment_seq; Type: SEQUENCE; Schema: public; Owner: asims_user
--

CREATE SEQUENCE public.instance_comment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.instance_comment_seq OWNER TO asims_user;

--
-- TOC entry 208 (class 1259 OID 35839)
-- Name: instance_history; Type: TABLE; Schema: public; Owner: asims_user
--

CREATE TABLE public.instance_history (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    field character varying(255),
    instance_id bigint,
    new_value character varying(255),
    old_value character varying(255),
    user_id bigint
);


ALTER TABLE public.instance_history OWNER TO asims_user;

--
-- TOC entry 226 (class 1259 OID 35932)
-- Name: instance_history_seq; Type: SEQUENCE; Schema: public; Owner: asims_user
--

CREATE SEQUENCE public.instance_history_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.instance_history_seq OWNER TO asims_user;

--
-- TOC entry 227 (class 1259 OID 35934)
-- Name: instance_seq; Type: SEQUENCE; Schema: public; Owner: asims_user
--

CREATE SEQUENCE public.instance_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.instance_seq OWNER TO asims_user;

--
-- TOC entry 209 (class 1259 OID 35847)
-- Name: instance_transition; Type: TABLE; Schema: public; Owner: asims_user
--

CREATE TABLE public.instance_transition (
    id bigint NOT NULL,
    description text,
    next_step character varying(255),
    previous_step character varying(255),
    instance_id bigint,
    user_id bigint
);


ALTER TABLE public.instance_transition OWNER TO asims_user;

--
-- TOC entry 228 (class 1259 OID 35936)
-- Name: instance_transition_seq; Type: SEQUENCE; Schema: public; Owner: asims_user
--

CREATE SEQUENCE public.instance_transition_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.instance_transition_seq OWNER TO asims_user;

--
-- TOC entry 210 (class 1259 OID 35855)
-- Name: instance_watcher; Type: TABLE; Schema: public; Owner: asims_user
--

CREATE TABLE public.instance_watcher (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    watcher_id bigint,
    instance_id bigint
);


ALTER TABLE public.instance_watcher OWNER TO asims_user;

--
-- TOC entry 229 (class 1259 OID 35938)
-- Name: instance_watcher_seq; Type: SEQUENCE; Schema: public; Owner: asims_user
--

CREATE SEQUENCE public.instance_watcher_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.instance_watcher_seq OWNER TO asims_user;

--
-- TOC entry 211 (class 1259 OID 35860)
-- Name: permission; Type: TABLE; Schema: public; Owner: asims_user
--

CREATE TABLE public.permission (
    id bigint NOT NULL,
    active boolean NOT NULL,
    description character varying(255),
    name character varying(255)
);


ALTER TABLE public.permission OWNER TO asims_user;

--
-- TOC entry 230 (class 1259 OID 35940)
-- Name: permission_seq; Type: SEQUENCE; Schema: public; Owner: asims_user
--

CREATE SEQUENCE public.permission_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.permission_seq OWNER TO asims_user;

--
-- TOC entry 212 (class 1259 OID 35868)
-- Name: report; Type: TABLE; Schema: public; Owner: asims_user
--

CREATE TABLE public.report (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    description character varying(255),
    env_slug character varying(255),
    name character varying(255),
    xml_content text,
    form_id bigint
);


ALTER TABLE public.report OWNER TO asims_user;

--
-- TOC entry 231 (class 1259 OID 35942)
-- Name: report_seq; Type: SEQUENCE; Schema: public; Owner: asims_user
--

CREATE SEQUENCE public.report_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.report_seq OWNER TO asims_user;

--
-- TOC entry 213 (class 1259 OID 35876)
-- Name: role; Type: TABLE; Schema: public; Owner: asims_user
--

CREATE TABLE public.role (
    id bigint NOT NULL,
    active boolean NOT NULL,
    description character varying(255),
    env_slug character varying(255),
    name character varying(255)
);


ALTER TABLE public.role OWNER TO asims_user;

--
-- TOC entry 214 (class 1259 OID 35884)
-- Name: role_permission; Type: TABLE; Schema: public; Owner: asims_user
--

CREATE TABLE public.role_permission (
    role_id bigint NOT NULL,
    permission_id bigint NOT NULL
);


ALTER TABLE public.role_permission OWNER TO asims_user;

--
-- TOC entry 232 (class 1259 OID 35944)
-- Name: role_seq; Type: SEQUENCE; Schema: public; Owner: asims_user
--

CREATE SEQUENCE public.role_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_seq OWNER TO asims_user;

--
-- TOC entry 215 (class 1259 OID 35887)
-- Name: system_registry; Type: TABLE; Schema: public; Owner: asims_user
--

CREATE TABLE public.system_registry (
    id bigint NOT NULL,
    active boolean,
    content text,
    created_at timestamp without time zone,
    description character varying(255),
    env_slug character varying(255),
    name character varying(255),
    updated_at timestamp without time zone
);


ALTER TABLE public.system_registry OWNER TO asims_user;

--
-- TOC entry 233 (class 1259 OID 35946)
-- Name: system_registry_seq; Type: SEQUENCE; Schema: public; Owner: asims_user
--

CREATE SEQUENCE public.system_registry_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.system_registry_seq OWNER TO asims_user;

--
-- TOC entry 216 (class 1259 OID 35895)
-- Name: user_group; Type: TABLE; Schema: public; Owner: asims_user
--

CREATE TABLE public.user_group (
    user_id bigint NOT NULL,
    group_id bigint NOT NULL
);


ALTER TABLE public.user_group OWNER TO asims_user;

--
-- TOC entry 217 (class 1259 OID 35898)
-- Name: user_tbl; Type: TABLE; Schema: public; Owner: asims_user
--

CREATE TABLE public.user_tbl (
    id bigint NOT NULL,
    active boolean NOT NULL,
    address character varying(255),
    avatar character varying(255),
    created_at timestamp without time zone,
    email character varying(255),
    preferences text,
    name character varying(255),
    odk_password character varying(100),
    password character varying(100) NOT NULL,
    phone_no character varying(255),
    updated_at timestamp without time zone,
    username character varying(100) NOT NULL
);


ALTER TABLE public.user_tbl OWNER TO asims_user;

--
-- TOC entry 234 (class 1259 OID 35948)
-- Name: user_tbl_seq; Type: SEQUENCE; Schema: public; Owner: asims_user
--

CREATE SEQUENCE public.user_tbl_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_tbl_seq OWNER TO asims_user;

--
-- TOC entry 218 (class 1259 OID 35906)
-- Name: workflow; Type: TABLE; Schema: public; Owner: asims_user
--

CREATE TABLE public.workflow (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    description character varying(255),
    env_slug character varying(255),
    name character varying(255),
    workflow_json text
);


ALTER TABLE public.workflow OWNER TO asims_user;

--
-- TOC entry 235 (class 1259 OID 35950)
-- Name: workflow_seq; Type: SEQUENCE; Schema: public; Owner: asims_user
--

CREATE SEQUENCE public.workflow_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.workflow_seq OWNER TO asims_user;







-- #####################################################################################

-- Set asims_user permissions

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO asims_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO asims_user;
GRANT ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public TO asims_user;
GRANT ALL PRIVILEGES ON SCHEMA public TO asims_user;

-- Necessary Data
INSERT INTO public.user_tbl(id, active, address, email, name, password, odk_password, username, avatar) VALUES
 (1, true, 'Kabul', 'sys_admin@nsia.gov.af', 'Sys Admin', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'secret', 'sys_admin', '2019-02-24_21_22_53_thumbnail.jpg'),
 (2, true, 'Kabul', 'admin@nsia.gov.af', 'Admin User', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'secret', 'admin', '2019-02-24_21_22_53_thumbnail.jpg');

INSERT INTO public.group_tbl(id, name, description, active, env_slug) VALUES
 (1, 'SYS_ADMIN_GROUP', 'SYS ADMIN GROUP', true, 'nsia'),
 (2, 'ADMIN_GROUP', 'ADMIN GROUP', true, 'nsia');

INSERT INTO public.role(id, name, description, active, env_slug) VALUES
 (1, 'SYS_ADMIN_ROLE', 'It can manage the application and envrionments', true, 'nsia'),
 (2, 'ADMIN_ROLE', 'It can manage the application', true, 'nsia');

INSERT INTO public.permission(id, name, description, active) VALUES
(1, 'SYS_ADMIN', 'Can have access to everything', true),
(2, 'ADMIN', 'Can access to admin pages of an environment', true),

(3, 'ENV_CREATE', 'Can only create the new record', true),
(4, 'ENV_VIEW', 'Can only view/read the record', true),
(5, 'ENV_EDIT', 'Can only edit/update the record', true),
(6, 'ENV_DELETE', 'Can only delete the record', true),
(7, 'ENV_LIST', 'Can only see the list/table of records', true),

(8, 'PERMISSION_CREATE', 'Can only create the new record', true),
(9, 'PERMISSION_VIEW', 'Can only view/read the record', true),
(10, 'PERMISSION_EDIT', 'Can only edit/update the record', true),
(11, 'PERMISSION_DELETE', 'Can only delete the record', true),
(12, 'PERMISSION_LIST', 'Can only see the list/table of records', true),

(13, 'USER_CREATE', 'Can only create the new record', true),
(14, 'USER_VIEW', 'Can only view/read the record', true),
(15, 'USER_EDIT', 'Can only edit/update the record', true),
(16, 'USER_DELETE', 'Can only delete the record', true),
(17, 'USER_LIST', 'Can only see the list/table of records', true),

(18, 'GROUP_CREATE', 'Can only create the new record', true),
(19, 'GROUP_VIEW', 'Can only view/read the record', true),
(20, 'GROUP_EDIT', 'Can only edit/update the record', true),
(21, 'GROUP_DELETE', 'Can only delete the record', true),
(22, 'GROUP_LIST', 'Can only see the list/table of records', true),
 
(23, 'ROLE_CREATE', 'Can only create the new record', true),
(24, 'ROLE_VIEW', 'Can only view/read the record', true),
(25, 'ROLE_EDIT', 'Can only edit/update the record', true),
(26, 'ROLE_DELETE', 'Can only delete the record', true),
(27, 'ROLE_LIST', 'Can only see the list/table of records', true),

(28, 'FORM_CREATE', 'Can only create the new record', true),
(29, 'FORM_VIEW', 'Can only view/read the record', true),
(30, 'FORM_EDIT', 'Can only edit/update the record', true),
(31, 'FORM_DELETE', 'Can only delete the record', true),
(32, 'FORM_LIST', 'Can only see the list/table of records', true),

(33, 'SYS_REG_CREATE', 'Can only create the new record', true),
(34, 'SYS_REG_VIEW', 'Can only view/read the record', true),
(35, 'SYS_REG_EDIT', 'Can only edit/update the record', true),
(36, 'SYS_REG_DELETE', 'Can only delete the record', true),
(37, 'SYS_REG_LIST', 'Can only see the list/table of records', true),

(38, 'DATASOURCE_CREATE', 'Can only create the new record', true),
(39, 'DATASOURCE_VIEW', 'Can only view/read the record', true),
(40, 'DATASOURCE_EDIT', 'Can edit/update only his own records', true),
(41, 'DATASOURCE_DELETE', 'Can only delete the record', true),
(42, 'DATASOURCE_LIST', 'Can only see the list/table of records', true),

(43, 'DATASET_CREATE', 'Can only create the new record', true),
(44, 'DATASET_VIEW', 'Can only view/read the record', true),
(45, 'DATASET_EDIT', 'Can only edit/update the record', true),
(46, 'DATASET_DELETE', 'Can only delete the record', true),
(47, 'DATASET_LIST', 'Can only see the list/table of records', true),

(48, 'INSTANCE_CREATE', 'Can only create the new record', true),
(49, 'INSTANCE_VIEW', 'Can only view/read the record', true),
(50, 'INSTANCE_EDIT', 'Can only edit/update the record', true),
(51, 'INSTANCE_DELETE', 'Can only delete the record', true),

(52, 'INSTANCES_VIEW', 'Can see all tabs of instances', true),

(53, 'INSTANCES_LIST', 'Can see the list of all form instances form instances table', true),

(54, 'INSTANCE_LIST', 'Can only see the list/table of records', true),
(55, 'INSTANCE_MAP', 'Can only see the map of records', true),
(56, 'INSTANCE_CHART', 'Can only see the chart of records', true),
(57, 'INSTANCE_GALLARY', 'Can only see the gallary of records', true),

(58, 'MAIN_MAP_VIEW', 'Can only see the main map', true),

(59, 'INSTANCE_COMMENT_CREATE', 'Can only create the new record', true),
(60, 'INSTANCE_COMMENT_VIEW', 'Can only view/read the record', true),
(61, 'INSTANCE_COMMENT_EDIT', 'Can only edit/update HIS/HER record', true),
(62, 'INSTANCE_COMMENT_DELETE', 'Can only delete the record', true),

(63, 'INSTANCE_ATTACHMENT_CREATE', 'Can only create the new record', true),
(64, 'INSTANCE_ATTACHMENT_VIEW', 'Can only view/read the record', true),
(65, 'INSTANCE_ATTACHMENT_EDIT', 'Can only edit/update the record', true),
(66, 'INSTANCE_ATTACHMENT_DELETE', 'Can only delete the record', true),

(67, 'WORKFLOW_CREATE', 'Can only create the new record', true),
(68, 'WORKFLOW_VIEW', 'Can only view/read the record', true),
(69, 'WORKFLOW_EDIT', 'Can only edit/update the record', true),
(70, 'WORKFLOW_DELETE', 'Can only delete the record', true),
(71, 'WORKFLOW_LIST', 'Can only see the list/table of records', true),

(72, 'INSTANCE_HISTORY_VIEW', 'Can only view/read the history of instance', true),

(73, 'INSTANCE_ADD_CHILD', 'Can add a child to instance', true),
(74, 'INSTANCE_DETACH_CHILD', 'Can detach a child from parent', true),

(75, 'INSTANCE_PARENT_ADD', 'Can add a parent to instance', true),

(76, 'INSTANCE_WORKFLOW_TRANSITION', 'Can only delete the record', true),

(77, 'INSTANCE_WATCHER_ADD', 'Can add the new watcher', true),
(78, 'INSTANCE_WATCHER_VIEW', 'Can only view/read watchers', true),
(79, 'INSTANCE_WATCHER_DELETE', 'Can delete a watcher', true),

(80, 'INSTANCE_EDIT_ALL', 'Can see the edit the instance regardless of instance creator/owner.', true),
(81, 'FORM_EXCEL_EXPORT', 'Could export all instances of a form', true),
(82, 'INSTANCE_PRINT_REPORT', 'Can print report of an instance', true),

(83, 'REPORT_CREATE', 'Can only create the new record', true),
(84, 'REPORT_VIEW', 'Can only view/read the record', true),
(85, 'REPORT_EDIT', 'Can only edit/update the record', true),
(86, 'REPORT_DELETE', 'Can only delete the record', true),
(87, 'REPORT_LIST', 'Can only see the list/table of records', true),

(88, 'INSTANCE_WORKFLOW_TRANSITION_HISTORY_VIEW', 'Can only view the transition history', true),
(89, 'INSTANCE_VALIDATE', 'print the invalid fields in console', true),
(90, 'ODK_X_SYNC', 'Can sync the table of category odkx with odk sync endpoint', true),
(91, 'INSTANCE_LIST_ ALL', 'Can see the list of all instances regardless of instance creator/owner.', true),
(92, 'INSTANCE_VIEW_ ALL', 'Can see the view the instance regardless of instance creator/owner.', true),
(93, 'INSTANCE_DELETE_ALL', 'Can see the delete the instance regardless of instance creator/owner.', true),
(94, 'INSTANCE_CLONE', 'Can clone instance', true);

INSERT INTO public.user_group(user_id, group_id) VALUES
(1, 1),
(2, 2);

INSERT INTO public.group_role(group_id, role_id) VALUES
(1, 1),
(2, 2);

INSERT INTO public.role_permission(role_id, permission_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10),
(1, 11), (1, 12), (1, 13), (1, 14), (1, 15), (1, 16), (1, 17), (1, 18), (1, 19), (1, 20),
(1, 21), (1, 22), (1, 23), (1, 24), (1, 25), (1, 26), (1, 27), (1, 28), (1, 29), (1, 30),
(1, 31), (1, 32), (1, 33), (1, 34), (1, 35), (1, 36), (1, 37), (1, 38), (1, 39), (1, 40),
(1, 41), (1, 42), (1, 43), (1, 44), (1, 45), (1, 46), (1, 47), (1, 48), (1, 49), (1, 50),
(1, 51), (1, 52), (1, 53), (1, 54), (1, 55), (1, 56), (1, 57), (1, 58), (1, 59), (1, 60),
(1, 61), (1, 62), (1, 63), (1, 64), (1, 65), (1, 66), (1, 67), (1, 68), (1, 69), (1, 70),
(1, 71), (1, 72), (1, 73), (1, 74), (1, 75), (1, 76), (1, 77), (1, 78), (1, 79), (1, 80),
(1, 81), (1, 82), (1, 83), (1, 84), (1, 85), (1, 86), (1, 87), (1, 88), (1, 89), (1, 90),
(2, 1), (2, 2),         (2, 8), (2, 9), (2, 10),
(2, 11), (2, 12), (2, 13), (2, 14), (2, 15), (2, 16), (2, 17), (2, 18), (2, 19), (2, 20),
(2, 21), (2, 22), (2, 23), (2, 24), (2, 25), (2, 26), (2, 27), (2, 28), (2, 29), (2, 30),
(2, 31), (2, 32), (2, 33), (2, 34), (2, 35), (2, 36), (2, 37), (2, 38), (2, 39), (2, 40),
(2, 41), (2, 42), (2, 43), (2, 44), (2, 45), (2, 46), (2, 47), (2, 48), (2, 49), (2, 50),
(2, 51), (2, 52), (2, 53), (2, 54), (2, 55), (2, 56), (2, 57), (2, 58), (2, 59), (2, 60),
(2, 61), (2, 62), (2, 63), (2, 64), (2, 65), (2, 66), (2, 67), (2, 68), (2, 69), (2, 70),
(2, 71), (2, 72), (2, 73), (2, 74), (2, 75), (2, 76), (2, 77), (2, 78), (2, 79), (2, 80),
(2, 81), (2, 82), (2, 83), (2, 84), (2, 85), (2, 86), (2, 87), (2, 88), (2, 89), (2, 90);


INSERT INTO public.environment(id, slug, name, description, active, created_at, updated_at) VALUES
 (1, 'nsia' , 'NSIA', 'This Environment belongs to NSIA', true, '2011-01-08 04:05:06', '2012-01-08 04:05:06'),
 (2, 'aop' , 'AoP', 'This Environment belongs to AoP', true, '2011-01-08 04:05:06', '2012-01-08 04:05:06');

 INSERT INTO public.environment_user(environment_id, user_id) VALUES
 (1, 1),
 (1, 2),
 (2, 1),
 (2, 2);

INSERT INTO public.workflow(id, description, name, env_slug, workflow_json) VALUES
(1, 'This is default workflow', 'Default Workflow', 'nsia',
'{
   "steps":[
        {"name":"Open", "transitions":[
                {"name":"Reject", "toStep":"Rejected", "CommentRequired": true},
                {"name":"Close","toStep":"Closed","resolutions":["Completed", "Incomplete", "Duplicate"], "CommentRequired": true}
        ],
        "authorizedGroups": ["ADMIN_GROUP"],
        },
        {"name":"Reopened", "transitions":[
                {"name":"Reject", "toStep":"Rejected", "CommentRequired": true},
                {"name":"Close", "toStep":"Closed", "resolutions":["Completed", "Incomplete", "Duplicate"], "CommentRequired": true}
        ],
        "authorizedGroups": [ "ADMIN_GROUP"],
        },
        {"name":"Rejected", "transitions":[
                {"name":"Reopen", "toStep":"Reopened", "CommentRequired": false},
                {"name":"Close", "toStep":"Closed", "resolutions":["Completed", "Incomplete", "Duplicate"], "CommentRequired": true}
        ],
        "authorizedGroups": ["ADMIN_GROUP"],
        },
        {"name":"Closed", "transitions":[ ],
        "authorizedGroups": ["ADMIN_GROUP"],
        }
    ]
}');





-- ###################################################################################
--
-- TOC entry 3363 (class 0 OID 0)
-- Dependencies: 219
-- Name: datasource_seq; Type: SEQUENCE SET; Schema: public; Owner: asims_user
--

SELECT pg_catalog.setval('public.datasource_seq', 1, false);


--
-- TOC entry 3364 (class 0 OID 0)
-- Dependencies: 220
-- Name: environment_seq; Type: SEQUENCE SET; Schema: public; Owner: asims_user
--

SELECT pg_catalog.setval('public.environment_seq', 3, false);


--
-- TOC entry 3365 (class 0 OID 0)
-- Dependencies: 221
-- Name: form_seq; Type: SEQUENCE SET; Schema: public; Owner: asims_user
--

SELECT pg_catalog.setval('public.form_seq', 1, false);


--
-- TOC entry 3366 (class 0 OID 0)
-- Dependencies: 222
-- Name: group_tbl_seq; Type: SEQUENCE SET; Schema: public; Owner: asims_user
--

SELECT pg_catalog.setval('public.group_tbl_seq', 3, false);


--
-- TOC entry 3367 (class 0 OID 0)
-- Dependencies: 223
-- Name: instance_assignee_history_seq; Type: SEQUENCE SET; Schema: public; Owner: asims_user
--

SELECT pg_catalog.setval('public.instance_assignee_history_seq', 1, false);


--
-- TOC entry 3368 (class 0 OID 0)
-- Dependencies: 224
-- Name: instance_attachment_seq; Type: SEQUENCE SET; Schema: public; Owner: asims_user
--

SELECT pg_catalog.setval('public.instance_attachment_seq', 1, false);


--
-- TOC entry 3369 (class 0 OID 0)
-- Dependencies: 225
-- Name: instance_comment_seq; Type: SEQUENCE SET; Schema: public; Owner: asims_user
--

SELECT pg_catalog.setval('public.instance_comment_seq', 1, false);


--
-- TOC entry 3370 (class 0 OID 0)
-- Dependencies: 226
-- Name: instance_history_seq; Type: SEQUENCE SET; Schema: public; Owner: asims_user
--

SELECT pg_catalog.setval('public.instance_history_seq', 1, false);


--
-- TOC entry 3371 (class 0 OID 0)
-- Dependencies: 227
-- Name: instance_seq; Type: SEQUENCE SET; Schema: public; Owner: asims_user
--

SELECT pg_catalog.setval('public.instance_seq', 1, false);


--
-- TOC entry 3372 (class 0 OID 0)
-- Dependencies: 228
-- Name: instance_transition_seq; Type: SEQUENCE SET; Schema: public; Owner: asims_user
--

SELECT pg_catalog.setval('public.instance_transition_seq', 1, false);


--
-- TOC entry 3373 (class 0 OID 0)
-- Dependencies: 229
-- Name: instance_watcher_seq; Type: SEQUENCE SET; Schema: public; Owner: asims_user
--

SELECT pg_catalog.setval('public.instance_watcher_seq', 1, false);


--
-- TOC entry 3374 (class 0 OID 0)
-- Dependencies: 230
-- Name: permission_seq; Type: SEQUENCE SET; Schema: public; Owner: asims_user
--

SELECT pg_catalog.setval('public.permission_seq', 89, false);


--
-- TOC entry 3375 (class 0 OID 0)
-- Dependencies: 231
-- Name: report_seq; Type: SEQUENCE SET; Schema: public; Owner: asims_user
--

SELECT pg_catalog.setval('public.report_seq', 1, false);


--
-- TOC entry 3376 (class 0 OID 0)
-- Dependencies: 232
-- Name: role_seq; Type: SEQUENCE SET; Schema: public; Owner: asims_user
--

SELECT pg_catalog.setval('public.role_seq', 3, false);


--
-- TOC entry 3377 (class 0 OID 0)
-- Dependencies: 233
-- Name: system_registry_seq; Type: SEQUENCE SET; Schema: public; Owner: asims_user
--

SELECT pg_catalog.setval('public.system_registry_seq', 1, false);


--
-- TOC entry 3378 (class 0 OID 0)
-- Dependencies: 234
-- Name: user_tbl_seq; Type: SEQUENCE SET; Schema: public; Owner: asims_user
--

SELECT pg_catalog.setval('public.user_tbl_seq', 3, false);


--
-- TOC entry 3379 (class 0 OID 0)
-- Dependencies: 235
-- Name: workflow_seq; Type: SEQUENCE SET; Schema: public; Owner: asims_user
--

SELECT pg_catalog.setval('public.workflow_seq', 2, false);


--
-- TOC entry 3134 (class 2606 OID 35776)
-- Name: datasource datasource_pkey; Type: CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.datasource
    ADD CONSTRAINT datasource_pkey PRIMARY KEY (id);


--
-- TOC entry 3136 (class 2606 OID 35784)
-- Name: environment environment_pkey; Type: CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.environment
    ADD CONSTRAINT environment_pkey PRIMARY KEY (id);


--
-- TOC entry 3140 (class 2606 OID 35795)
-- Name: form form_pkey; Type: CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.form
    ADD CONSTRAINT form_pkey PRIMARY KEY (id);


--
-- TOC entry 3142 (class 2606 OID 35812)
-- Name: group_tbl group_tbl_pkey; Type: CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.group_tbl
    ADD CONSTRAINT group_tbl_pkey PRIMARY KEY (id);


--
-- TOC entry 3146 (class 2606 OID 35825)
-- Name: instance_assignee_history instance_assignee_history_pkey; Type: CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.instance_assignee_history
    ADD CONSTRAINT instance_assignee_history_pkey PRIMARY KEY (id);


--
-- TOC entry 3148 (class 2606 OID 35833)
-- Name: instance_attachment instance_attachment_pkey; Type: CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.instance_attachment
    ADD CONSTRAINT instance_attachment_pkey PRIMARY KEY (id);


--
-- TOC entry 3150 (class 2606 OID 35838)
-- Name: instance_comment instance_comment_pkey; Type: CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.instance_comment
    ADD CONSTRAINT instance_comment_pkey PRIMARY KEY (id);


--
-- TOC entry 3152 (class 2606 OID 35846)
-- Name: instance_history instance_history_pkey; Type: CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.instance_history
    ADD CONSTRAINT instance_history_pkey PRIMARY KEY (id);


--
-- TOC entry 3144 (class 2606 OID 35820)
-- Name: instance instance_pkey; Type: CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.instance
    ADD CONSTRAINT instance_pkey PRIMARY KEY (id);


--
-- TOC entry 3154 (class 2606 OID 35854)
-- Name: instance_transition instance_transition_pkey; Type: CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.instance_transition
    ADD CONSTRAINT instance_transition_pkey PRIMARY KEY (id);


--
-- TOC entry 3156 (class 2606 OID 35859)
-- Name: instance_watcher instance_watcher_pkey; Type: CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.instance_watcher
    ADD CONSTRAINT instance_watcher_pkey PRIMARY KEY (id);


--
-- TOC entry 3158 (class 2606 OID 35867)
-- Name: permission permission_pkey; Type: CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.permission
    ADD CONSTRAINT permission_pkey PRIMARY KEY (id);


--
-- TOC entry 3160 (class 2606 OID 35875)
-- Name: report report_pkey; Type: CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.report
    ADD CONSTRAINT report_pkey PRIMARY KEY (id);


--
-- TOC entry 3162 (class 2606 OID 35883)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- TOC entry 3164 (class 2606 OID 35894)
-- Name: system_registry system_registry_pkey; Type: CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.system_registry
    ADD CONSTRAINT system_registry_pkey PRIMARY KEY (id);


--
-- TOC entry 3138 (class 2606 OID 35915)
-- Name: environment uk_o857u5svhw18ekqbbxwl4dx3x; Type: CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.environment
    ADD CONSTRAINT uk_o857u5svhw18ekqbbxwl4dx3x UNIQUE (slug);


--
-- TOC entry 3166 (class 2606 OID 35917)
-- Name: user_tbl uk_xkjl2orevvtyrqqshcot355j; Type: CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.user_tbl
    ADD CONSTRAINT uk_xkjl2orevvtyrqqshcot355j UNIQUE (username);


--
-- TOC entry 3168 (class 2606 OID 35905)
-- Name: user_tbl user_tbl_pkey; Type: CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.user_tbl
    ADD CONSTRAINT user_tbl_pkey PRIMARY KEY (id);


--
-- TOC entry 3170 (class 2606 OID 35913)
-- Name: workflow workflow_pkey; Type: CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.workflow
    ADD CONSTRAINT workflow_pkey PRIMARY KEY (id);


--
-- TOC entry 3183 (class 2606 OID 36012)
-- Name: instance_attachment fk5ll8f3yc5o5bawrnsg5aaad9r; Type: FK CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.instance_attachment
    ADD CONSTRAINT fk5ll8f3yc5o5bawrnsg5aaad9r FOREIGN KEY (instance_id) REFERENCES public.instance(id);


--
-- TOC entry 3175 (class 2606 OID 35972)
-- Name: form_form fk7hcdfu1xa9811r6d4e0xj49pp; Type: FK CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.form_form
    ADD CONSTRAINT fk7hcdfu1xa9811r6d4e0xj49pp FOREIGN KEY (form_id) REFERENCES public.form(id);


--
-- TOC entry 3176 (class 2606 OID 35977)
-- Name: form_group fk7jhqk09ew47qgpyyoiogpag1w; Type: FK CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.form_group
    ADD CONSTRAINT fk7jhqk09ew47qgpyyoiogpag1w FOREIGN KEY (group_id) REFERENCES public.group_tbl(id);


--
-- TOC entry 3192 (class 2606 OID 36057)
-- Name: role_permission fka6jx8n8xkesmjmv6jqug6bg68; Type: FK CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.role_permission
    ADD CONSTRAINT fka6jx8n8xkesmjmv6jqug6bg68 FOREIGN KEY (role_id) REFERENCES public.role(id);


--
-- TOC entry 3188 (class 2606 OID 36037)
-- Name: instance_transition fkbndnmtorg77tpsa5jq9tt9ft; Type: FK CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.instance_transition
    ADD CONSTRAINT fkbndnmtorg77tpsa5jq9tt9ft FOREIGN KEY (user_id) REFERENCES public.user_tbl(id);


--
-- TOC entry 3181 (class 2606 OID 36002)
-- Name: instance fkbng0cusxa7dqdmrk6ox8pu2yx; Type: FK CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.instance
    ADD CONSTRAINT fkbng0cusxa7dqdmrk6ox8pu2yx FOREIGN KEY (owner_id) REFERENCES public.user_tbl(id);


--
-- TOC entry 3189 (class 2606 OID 36042)
-- Name: instance_watcher fkcdexfqfo5j0njgamvdgj8ifgx; Type: FK CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.instance_watcher
    ADD CONSTRAINT fkcdexfqfo5j0njgamvdgj8ifgx FOREIGN KEY (instance_id) REFERENCES public.instance(id);


--
-- TOC entry 3172 (class 2606 OID 35957)
-- Name: environment_user fkdv2l19cqeu65d3adauxj6hhr0; Type: FK CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.environment_user
    ADD CONSTRAINT fkdv2l19cqeu65d3adauxj6hhr0 FOREIGN KEY (environment_id) REFERENCES public.environment(id);


--
-- TOC entry 3185 (class 2606 OID 36022)
-- Name: instance_comment fkdw38e8438cuwcd97wgtoqkv8n; Type: FK CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.instance_comment
    ADD CONSTRAINT fkdw38e8438cuwcd97wgtoqkv8n FOREIGN KEY (instance_id) REFERENCES public.instance(id);


--
-- TOC entry 3182 (class 2606 OID 36007)
-- Name: instance fkeoide092tbcr031t9hklar2iu; Type: FK CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.instance
    ADD CONSTRAINT fkeoide092tbcr031t9hklar2iu FOREIGN KEY (parent_instance_id) REFERENCES public.instance(id);


--
-- TOC entry 3191 (class 2606 OID 36052)
-- Name: role_permission fkf8yllw1ecvwqy3ehyxawqa1qp; Type: FK CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.role_permission
    ADD CONSTRAINT fkf8yllw1ecvwqy3ehyxawqa1qp FOREIGN KEY (permission_id) REFERENCES public.permission(id);


--
-- TOC entry 3173 (class 2606 OID 35962)
-- Name: form fkinihlcyul4vi1g9gmerthahb0; Type: FK CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.form
    ADD CONSTRAINT fkinihlcyul4vi1g9gmerthahb0 FOREIGN KEY (workflow_id) REFERENCES public.workflow(id);


--
-- TOC entry 3190 (class 2606 OID 36047)
-- Name: report fkj2t8yv4idxcas5trpytie5j3m; Type: FK CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.report
    ADD CONSTRAINT fkj2t8yv4idxcas5trpytie5j3m FOREIGN KEY (form_id) REFERENCES public.form(id);


--
-- TOC entry 3184 (class 2606 OID 36017)
-- Name: instance_attachment fkjnusucjshjiugx16293ffxodf; Type: FK CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.instance_attachment
    ADD CONSTRAINT fkjnusucjshjiugx16293ffxodf FOREIGN KEY (user_id) REFERENCES public.user_tbl(id);


--
-- TOC entry 3194 (class 2606 OID 36067)
-- Name: user_group fkkexdglfjntm0fuxgpu9xnanu5; Type: FK CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT fkkexdglfjntm0fuxgpu9xnanu5 FOREIGN KEY (user_id) REFERENCES public.user_tbl(id);


--
-- TOC entry 3179 (class 2606 OID 35992)
-- Name: group_role fkloysfsl4uw3sa8f22f3pon8du; Type: FK CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.group_role
    ADD CONSTRAINT fkloysfsl4uw3sa8f22f3pon8du FOREIGN KEY (group_id) REFERENCES public.group_tbl(id);


--
-- TOC entry 3174 (class 2606 OID 35967)
-- Name: form_form fkmnmqb0m967j5li11hhb65rdp5; Type: FK CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.form_form
    ADD CONSTRAINT fkmnmqb0m967j5li11hhb65rdp5 FOREIGN KEY (child_form_id) REFERENCES public.form(id);


--
-- TOC entry 3171 (class 2606 OID 35952)
-- Name: environment_user fknna4souydthqb86ef6rmy17ah; Type: FK CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.environment_user
    ADD CONSTRAINT fknna4souydthqb86ef6rmy17ah FOREIGN KEY (user_id) REFERENCES public.user_tbl(id);


--
-- TOC entry 3177 (class 2606 OID 35982)
-- Name: form_group fko4pal2buqu5ce7wtlt9kj7nuo; Type: FK CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.form_group
    ADD CONSTRAINT fko4pal2buqu5ce7wtlt9kj7nuo FOREIGN KEY (form_id) REFERENCES public.form(id);


--
-- TOC entry 3186 (class 2606 OID 36027)
-- Name: instance_comment fkr194pum3aonk9yw8v1q415o0e; Type: FK CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.instance_comment
    ADD CONSTRAINT fkr194pum3aonk9yw8v1q415o0e FOREIGN KEY (user_id) REFERENCES public.user_tbl(id);


--
-- TOC entry 3178 (class 2606 OID 35987)
-- Name: group_role fkrlbrx4ujb613vbs9rql5uffdi; Type: FK CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.group_role
    ADD CONSTRAINT fkrlbrx4ujb613vbs9rql5uffdi FOREIGN KEY (role_id) REFERENCES public.role(id);


--
-- TOC entry 3187 (class 2606 OID 36032)
-- Name: instance_transition fkshhod4wsfv5tvji4cgdvjf7p7; Type: FK CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.instance_transition
    ADD CONSTRAINT fkshhod4wsfv5tvji4cgdvjf7p7 FOREIGN KEY (instance_id) REFERENCES public.instance(id);


--
-- TOC entry 3193 (class 2606 OID 36062)
-- Name: user_group fkslv34ie1pupalwjrhmsay6tq5; Type: FK CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT fkslv34ie1pupalwjrhmsay6tq5 FOREIGN KEY (group_id) REFERENCES public.group_tbl(id);


--
-- TOC entry 3180 (class 2606 OID 35997)
-- Name: instance fksus5okuyn4bae3vybuht21oda; Type: FK CONSTRAINT; Schema: public; Owner: asims_user
--

ALTER TABLE ONLY public.instance
    ADD CONSTRAINT fksus5okuyn4bae3vybuht21oda FOREIGN KEY (form_id) REFERENCES public.form(id);


--
-- TOC entry 1803 (class 826 OID 16810)
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: -; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT ALL ON TABLES  TO asims_user;