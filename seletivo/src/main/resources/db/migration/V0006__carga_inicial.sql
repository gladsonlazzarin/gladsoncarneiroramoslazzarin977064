-- 1. Inserir Artistas
INSERT INTO "public"."tb_artista" ("id", "nome_artista", "tipo_artista") VALUES
(1, 'Serj Tankian', 'CANTOR'),
(2, 'Mike Shinoda', 'CANTOR'),
(3, 'Michel Teló', 'CANTOR'),
(4, 'Guns N'' Roses', 'BANDA');

-- 2. Inserir Álbuns
INSERT INTO "public"."tb_album" ("id", "nome_album") VALUES
-- Serj Tankian
(1, 'Harakiri'),
(2, 'Black Blooms'),
(3, 'The Rough Dog'),

-- Mike Shinoda
(4, 'The Rising Tied'),
(5, 'Post Traumatic'),
(6, 'Post Traumatic EP'),
(7, 'Where''d You Go'),

-- Michel Teló
(8, 'Bem Sertanejo'),
(9, 'Bem Sertanejo - O Show (Ao Vivo)'),
(10, 'Bem Sertanejo - (1ª Temporada) - EP'),

-- Guns N' Roses
(11, 'Use Your Illusion I'),
(12, 'Use Your Illusion II'),
(13, 'Greatest Hits');

-- 3. Vincular artistas aos álbuns
INSERT INTO "public"."tb_artista_album" ("artista_id", "album_id") VALUES
-- Serj Tankian
(1, 1),
(1, 2),
(1, 3),

-- Mike Shinoda
(2, 4),
(2, 5),
(2, 6),
(2, 7),

-- Michel Teló
(3, 8),
(3, 9),
(3, 10),

-- Guns N' Roses
(4, 11),
(4, 12),
(4, 13);
