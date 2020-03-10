
SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';

INSERT INTO public.user_tbl(id, active, address, email, name, password, odk_password, username, avatar) VALUES
 (3, true, 'Kabul', 'jalil@nsia.gov.af', 'Jalil', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'secret', 'jalil', ''),
 (4, true, 'Kabul', 'saber@nsia.gov.af', 'Saber', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'secret', 'saber', ''),
 (5, true, 'Kabul', 'zafar@nsia.gov.af', 'Zafar', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'secret', 'zafar', ''),
 (6, true, 'Kabul', 'faize@nsia.gov.af', 'Faize', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'secret', 'faize', '');

SELECT pg_catalog.setval('public.user_tbl_seq', 7, false);

INSERT INTO public.group_tbl(id, name, description, active, env_slug) VALUES
 (3, 'SUPERVISOR_GROUP', 'SUPERVISOR GROUP', true, 'nsia'),
 (4, 'DATA_ENTRY_GROUP', 'DATA ENTRY GROUP', true, 'nsia');

SELECT pg_catalog.setval('public.group_tbl_seq', 5, false);

INSERT INTO public.form(id, xml_form_id, name, description, env_slug, active, has_geometry, show_on_map, workflow_id, xml_content, created_at, updated_at) VALUES
 (1, 'Sample_Form', 'Sample Form', 'This is survey form for school inspection', 'nsia', true, false, true , 1,'<xml></xml>', '2011-01-08 04:05:06', '2012-01-08 04:05:06\'),
 (2, '', 'Canal Inspection', 'This is survey form for canal inspection', 'nsia', true, false, false , null, '<xml></xml>', '2013-01-08 04:05:06', '2016-01-08 04:05:06');

SELECT pg_catalog.setval('public.form_seq', 3, false);

 INSERT INTO public.datasource(id, name, host, port, user_name, db_pswrd, SSL , DBName, env_slug) VALUES
(1, 'MYSQL', 'localhost', 4200, 'msn', 'pass', 'Nothing', 'NSIA_DB', 'nsia'),
(2, 'SQL', 'localhost', 8090, 'msn', 'pass', 'Nothing', 'MOH_DB', 'nsia'),
(3, 'PostgreSQL', 'localhost', 8080, 'msn', 'pass', 'Nothing', 'MOF_DB', 'nsia'),
(4, 'Oracal', 'localhost', 8020, 'msn', 'pass', 'Nothing', 'MOW_DB', 'nsia');

SELECT pg_catalog.setval('public.datasource_seq', 5, false);

INSERT INTO public.role(id, name, description, active, env_slug) VALUES
 (3, 'USER_ROLE', 'It can only view the data', true, 'nsia');

SELECT pg_catalog.setval('public.role_seq', 4, false);

INSERT INTO public.user_group(user_id, group_id) VALUES
(3, 3),
(4, 3),
(5, 3),
(6, 3);

INSERT INTO public.group_role(group_id, role_id) VALUES
(3, 3);

INSERT INTO public.role_permission(role_id, permission_id) VALUES
(3, 1), (3, 2),         (3, 8), (3, 9), (3, 10),
(3, 48), (3, 49), (3, 50), (3, 51), (3, 52);

INSERT INTO public.form_group(form_id, group_id) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 2);

 -- --------------------------------------------------------------------------------------------------------------

 INSERT INTO public.environment(id, slug, name, description, active, created_at, updated_at) VALUES
(3, 'mof', 'MoF', 'This Environment belongs to MoF', true, '2014-01-08 04:05:06', '2016-02-08 04:05:06');

SELECT pg_catalog.setval('public.environment_seq', 4, false);

 INSERT INTO public.environment_user(environment_id, user_id) VALUES
 (1, 3),
 (1, 4),
 (1, 5),
 (1, 6),
 (2, 3),
 (2, 4),
 (3, 1),
 (3, 2);

SELECT pg_catalog.setval('public.environment_seq', 4, false);

INSERT INTO public.instance(id, version, form_id, active, env_slug, instance_folder_name, xml_content, is_first_update, created_at, updated_at) VALUES
 (1, 1, 1, true, 'nsia', 'upload/nsia/instances/1/1/Sample_Form_2019-03-19_00-40-57_1552940055736', '<xml></xml>', true, '2013-01-08 04:05:06', '2016-01-08 04:05:06');

 SELECT pg_catalog.setval('public.instance_seq', 2, false);

 INSERT INTO public.instance_comment(id, comment, user_id, instance_id, created_at, updated_at) VALUES
 (1, 'This is a test comment for instance 1', 1, 1, '2011-01-08 04:05:06', '2012-01-08 04:05:06'),
 (2, 'This is a test comment for instance 2', 2, 1, '2013-01-08 04:05:06', '2016-01-08 04:05:06');

 SELECT pg_catalog.setval('public.instance_comment_seq', 3, false);
