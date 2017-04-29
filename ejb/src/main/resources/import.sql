---Tipos de Usuario INSERT
INSERT INTO tipousuario VALUES (1,  "Manager" );
INSERT INTO tipousuario VALUES (2, "Anfitrion" );
INSERT INTO tipousuario VALUES (3, "Interesado" );


----Tipo de Show INSERT
INSERT INTO tiposhow VALUES (1,  "Banda Musical","Banda" );
INSERT INTO tiposhow VALUES (2,  "Stand Up","StandUp" );
INSERT INTO tiposhow VALUES (3,  "Conjunto de Baile","Danza" );
INSERT INTO tiposhow VALUES (4,  "Solitas","Solista" );




---Tags

INSERT INTO tag VALUES (1, "Pop" );
INSERT INTO tag VALUES (2, "Rock" );
INSERT INTO tag VALUES (3, "Humor" );
INSERT INTO tag VALUES (4, "Rap" );
INSERT INTO tag VALUES (5, "Tango" );


--FrecuenciaTags INSERT
INSERT INTO frecuenciatag VALUES (1, 1, 1,2 );
INSERT INTO frecuenciatag VALUES (2, 1, 2 ,4 );







---Permisos de Usuario INSERT
INSERT INTO permiso VALUES (1, "fa fa-play", "index", "Ivent");
INSERT INTO permiso VALUES (2, "fa fa-calendar", "dashboard", "Eventos" );
INSERT INTO permiso VALUES (3, "fa fa-music", "artistas.menu", "Artistas" );
INSERT INTO permiso VALUES (4, "fa fa-home", "lugar.menu", "Lugares" );
INSERT INTO permiso VALUES (5, "fa fa-map-marker", "login", "Ubicacion" );
INSERT INTO permiso VALUES (6, "fa fa-newspaper-o", "login", "Noticias" );

      ---Permisos Para Manager Banda
INSERT INTO permiso VALUES (7, "fa fa-cog", "artistas.index", "Administrar Artistas");


      ---Permisos para Anfitrion
INSERT INTO permiso VALUES (8, "fa fa-cog", "lugar.index", "Administrar Locales");
INSERT INTO permiso VALUES (9, "fa fa-cog", "solicitud.index", "Solicitudes");
INSERT INTO permiso VALUES (10, "fa fa-cog", "reserva.index", "Mis Reservas");
INSERT INTO permiso VALUES (11, "fa fa-cog", "reserva.reservasLugar", "Reservas Eventos");



---USUARIO INSERT

INSERT INTO usuario VALUES (1,"man","man",45,1);
INSERT INTO usuario VALUES (2,"anf","anf",45,2);
INSERT INTO usuario VALUES (3,"int","int",45,3);


---USUARIO INSERT

INSERT INTO persona VALUES (1,"urbisaglia",NULL ,NULL ,"da@lala.com", "Fabrizio", "456", 1);

---TipoUsuarioXPermiso INSERT

INSERT INTO tipousuario_permiso VALUES (1, 1 );
INSERT INTO tipousuario_permiso VALUES (1, 2 );
INSERT INTO tipousuario_permiso VALUES (1, 3 );
INSERT INTO tipousuario_permiso VALUES (1, 4 );
INSERT INTO tipousuario_permiso VALUES (1, 5 );
-- INSERT INTO tipousuario_permiso VALUES (1, 6 );
INSERT INTO tipousuario_permiso VALUES (1, 7 );
INSERT INTO tipousuario_permiso VALUES (1, 9 );


INSERT INTO tipousuario_permiso VALUES (2, 1 );
INSERT INTO tipousuario_permiso VALUES (2, 2 );
INSERT INTO tipousuario_permiso VALUES (2, 3 );
INSERT INTO tipousuario_permiso VALUES (2, 4 );
INSERT INTO tipousuario_permiso VALUES (2, 5 );
-- INSERT INTO tipousuario_permiso VALUES (2, 6 );
INSERT INTO tipousuario_permiso VALUES (2, 8 );
INSERT INTO tipousuario_permiso VALUES (2, 9 );
INSERT INTO tipousuario_permiso VALUES (2, 11 );

INSERT INTO tipousuario_permiso VALUES (3, 1 );
INSERT INTO tipousuario_permiso VALUES (3, 2 );
INSERT INTO tipousuario_permiso VALUES (3, 3 );
INSERT INTO tipousuario_permiso VALUES (3, 4 );
INSERT INTO tipousuario_permiso VALUES (3, 5 );
INSERT INTO tipousuario_permiso VALUES (3, 10 );
-- INSERT INTO tipousuario_permiso VALUES (3, 6 );


----ARTISTA INSERT
INSERT INTO artista VALUES (1, '2014', '3','Tributo beatles','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTGVFpVypX82EKI1WULFIVKQbb5WWIio9HKV-oOPp4VuW7_rc2-wA', 'Cordoba, Cordoba, Argentina', 'Cordoba, Cordoba, Argentina', 'two@gmail.com', 'The Beats' ,'0','4521254' ,1 ,1 );
INSERT INTO artista VALUES (2, '2016', '2','Tributo Oasis','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHZJ0ZmYm2Ru8AUajOywsgbnTIAGeMzwqmFhcKPPj4WJ-t0viA', 'Cordoba, Cordoba, Argentina', 'Cordoba, Cordoba, Argentina', 'two@gmail.com', 'OAS' ,'0','4521254' ,1 ,1 );
INSERT INTO artista_tag VALUES (1, 2 );
INSERT INTO artista_tag VALUES (2, 3 );
INSERT INTO artista_tag VALUES (2, 1 );
INSERT INTO lugar VALUES (1, '2014', 'Lugar Bueno', 'https://media-cdn.tripadvisor.com/media/photo-s/09/0f/01/2b/la-esquinita-de-guemes.jpg', 'esquinita@gmail.com', 'Esquinita','0' ,'4521254' ,'Achaval Rodriguez 70, Cordoba, Cordoba, Argentina',2 );
INSERT INTO lugar VALUES (2, '2010', 'Hermoso Lugar', 'https://media-cdn.tripadvisor.com/media/photo-s/09/77/73/d0/bar-romantic.jpg', 'mariamaria@gmail.com', 'Maria Maria','0' ,'4578745' ,'Velez Sarfield 172, Cordoba, Cordoba, Argentina',2 );
INSERT INTO lugar VALUES (3, '2015', 'Lugar Bueno', 'https://media-cdn.tripadvisor.com/media/photo-s/0c/1e/ec/e6/bar-louie.jpg', 'onzas@gmail.com', 'Onzas','0' ,'4545745' ,'Independencia 350, Cordoba, Cordoba, Argentina',2 );
INSERT INTO lugar VALUES (4, '2016', 'Lugar Oriental', 'https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTpaq5bcBPVbBsFwpIiJaoLu7ky2FO28WNm0cen30HUndiAsT3_Yw', 'oriente@gmail.com', 'Oriente','0' ,'4545745' ,'Buenos Aires 200, Cordoba, Cordoba, Argentina',2 );
INSERT INTO lugar VALUES (5, '2016', 'Lugar Oriental', 'https://media-cdn.tripadvisor.com/media/photo-s/0a/e8/09/00/lobby-bar-at-swissotel.jpg', 'club@gmail.com', 'Night Club','0' ,'4545745' ,'Corrientes 467, Cordoba, Cordoba, Argentina',2 );
INSERT INTO lugar_tag VALUES (1, 2 );
INSERT INTO lugar_tag VALUES (2, 3 );
INSERT INTO lugar_tag VALUES (2, 1 );
INSERT INTO lugar_tag VALUES (3, 1 );
INSERT INTO lugar_tag VALUES (4, 1 );
INSERT INTO lugar_tiposhow VALUES (2, 1 );
INSERT INTO lugar_tiposhow VALUES (1, 2 );
INSERT INTO lugar_tiposhow VALUES (3, 2 );
INSERT INTO lugar_tiposhow VALUES (4, 2 );
INSERT INTO evento VALUES (2, 1, 1,'3','hola', 20, 5, '2016-11-15 05:30:08', '2016-12-16 01:00:08' ,'1970-01-01T04:00:00.000Z',2 ,2 );
INSERT INTO evento VALUES (1, 1, 1,'2','hola', 50, 6, '2016-11-15 05:30:08', '2016-12-10 02:30:08' ,'1970-01-01T04:00:00.000Z',1 ,1 );
INSERT INTO evento VALUES (3, 1, 1,'5','hola', 50, 6, '2016-11-15 05:30:08', '2016-12-18 05:30:08' ,'1970-01-01T04:00:00.000Z',1 ,3 );
INSERT INTO evento VALUES (4, 1, 1,'1','hola', 50, 6, '2016-11-15 05:30:08', '2016-12-17 07:30:08' ,'1970-01-01T04:00:00.000Z',1 ,4 );
INSERT INTO evento VALUES (5, 1, 1,'1','hola', 50, 6, '2016-11-15 05:30:08', '2016-12-20 07:30:08' ,'1970-01-01T04:00:00.000Z',2 ,4 );










