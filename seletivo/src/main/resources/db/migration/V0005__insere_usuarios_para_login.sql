-- 1. Inserir as Roles (Permissões)
INSERT INTO "public"."tb_role" ("nome_role") VALUES ('ROLE_ADMIN');
INSERT INTO "public"."tb_role" ("nome_role") VALUES ('ROLE_USER');

-- 2. Inserir Usuários (A senha abaixo é '123456' em BCrypt)

INSERT INTO "tb_usuario" ("nome", "senha") VALUES
('admin', '$2a$12$asFDiCl5/8S31PSz6GfkkeOjvDDpjj3Z.S/z0sFwhMqYBYVa2.b2i'),
('user', '$2a$12$asFDiCl5/8S31PSz6GfkkeOjvDDpjj3Z.S/z0sFwhMqYBYVa2.b2i');

-- 3. Vincular Usuários às Roles
-- admin recebe ROLE_ADMIN (id 1) e ROLE_USER (id 2)
INSERT INTO "public"."tb_usuario_role" ("usuario_id", "role_id") VALUES (1, 1);
INSERT INTO "public"."tb_usuario_role" ("usuario_id", "role_id") VALUES (1, 2);

-- usuario recebe apenas ROLE_USER (id 2)
INSERT INTO "public"."tb_usuario_role" ("usuario_id", "role_id") VALUES (2, 2);