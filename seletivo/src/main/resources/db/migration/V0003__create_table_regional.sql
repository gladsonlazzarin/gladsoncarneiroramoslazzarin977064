DROP TABLE IF EXISTS "tb_regional";
CREATE TABLE "public"."tb_regional" (
    "id" integer NOT NULL,               -- id vindo do endpoint externo
    "nome" character varying(200) NOT NULL,
    "ativo" boolean NOT NULL DEFAULT true,
    CONSTRAINT "tb_regional_pkey" PRIMARY KEY ("id")
) WITH (oids = false);
