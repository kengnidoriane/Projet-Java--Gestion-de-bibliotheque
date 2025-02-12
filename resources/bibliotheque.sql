PGDMP  '                     }            bibliotheque    17.2    17.2     
           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false                       1262    32804    bibliotheque    DATABASE        CREATE DATABASE bibliotheque WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'French_France.1252';
    DROP DATABASE bibliotheque;
                     postgres    false            �            1259    32828    emprunts    TABLE     �   CREATE TABLE public.emprunts (
    id_emprunt integer NOT NULL,
    membre_id integer,
    livre_id integer,
    date_emprunt date NOT NULL,
    date_retour_prevue date NOT NULL,
    date_retour_effective date
);
    DROP TABLE public.emprunts;
       public         heap r       postgres    false            �            1259    32827    emprunts_id_emprunt_seq    SEQUENCE     �   CREATE SEQUENCE public.emprunts_id_emprunt_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.emprunts_id_emprunt_seq;
       public               postgres    false    222                       0    0    emprunts_id_emprunt_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.emprunts_id_emprunt_seq OWNED BY public.emprunts.id_emprunt;
          public               postgres    false    221            �            1259    32809    livres    TABLE     9  CREATE TABLE public.livres (
    id integer NOT NULL,
    titre character varying(255) NOT NULL,
    auteur character varying(255) NOT NULL,
    categorie character varying(100) NOT NULL,
    nombre_exemplaires integer NOT NULL,
    CONSTRAINT livres_nombre_exemplaires_check CHECK ((nombre_exemplaires >= 0))
);
    DROP TABLE public.livres;
       public         heap r       postgres    false            �            1259    32808    livres_id_seq    SEQUENCE     �   CREATE SEQUENCE public.livres_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.livres_id_seq;
       public               postgres    false    218                       0    0    livres_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.livres_id_seq OWNED BY public.livres.id;
          public               postgres    false    217            �            1259    32819    membres    TABLE     �   CREATE TABLE public.membres (
    id integer NOT NULL,
    nom character varying(100) NOT NULL,
    prenom character varying(100) NOT NULL,
    email character varying(255) NOT NULL,
    adhesion_date date NOT NULL
);
    DROP TABLE public.membres;
       public         heap r       postgres    false            �            1259    32818    membres_id_seq    SEQUENCE     �   CREATE SEQUENCE public.membres_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.membres_id_seq;
       public               postgres    false    220                       0    0    membres_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.membres_id_seq OWNED BY public.membres.id;
          public               postgres    false    219            c           2604    32831    emprunts id_emprunt    DEFAULT     z   ALTER TABLE ONLY public.emprunts ALTER COLUMN id_emprunt SET DEFAULT nextval('public.emprunts_id_emprunt_seq'::regclass);
 B   ALTER TABLE public.emprunts ALTER COLUMN id_emprunt DROP DEFAULT;
       public               postgres    false    221    222    222            a           2604    32812 	   livres id    DEFAULT     f   ALTER TABLE ONLY public.livres ALTER COLUMN id SET DEFAULT nextval('public.livres_id_seq'::regclass);
 8   ALTER TABLE public.livres ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    217    218    218            b           2604    32822 
   membres id    DEFAULT     h   ALTER TABLE ONLY public.membres ALTER COLUMN id SET DEFAULT nextval('public.membres_id_seq'::regclass);
 9   ALTER TABLE public.membres ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    220    219    220                      0    32828    emprunts 
   TABLE DATA           |   COPY public.emprunts (id_emprunt, membre_id, livre_id, date_emprunt, date_retour_prevue, date_retour_effective) FROM stdin;
    public               postgres    false    222   h!                 0    32809    livres 
   TABLE DATA           R   COPY public.livres (id, titre, auteur, categorie, nombre_exemplaires) FROM stdin;
    public               postgres    false    218   �!                 0    32819    membres 
   TABLE DATA           H   COPY public.membres (id, nom, prenom, email, adhesion_date) FROM stdin;
    public               postgres    false    220   $                  0    0    emprunts_id_emprunt_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.emprunts_id_emprunt_seq', 5, true);
          public               postgres    false    221                       0    0    livres_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.livres_id_seq', 21, true);
          public               postgres    false    217                       0    0    membres_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.membres_id_seq', 10, true);
          public               postgres    false    219            l           2606    32833    emprunts emprunts_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.emprunts
    ADD CONSTRAINT emprunts_pkey PRIMARY KEY (id_emprunt);
 @   ALTER TABLE ONLY public.emprunts DROP CONSTRAINT emprunts_pkey;
       public                 postgres    false    222            f           2606    32817    livres livres_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.livres
    ADD CONSTRAINT livres_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.livres DROP CONSTRAINT livres_pkey;
       public                 postgres    false    218            h           2606    32826    membres membres_email_key 
   CONSTRAINT     U   ALTER TABLE ONLY public.membres
    ADD CONSTRAINT membres_email_key UNIQUE (email);
 C   ALTER TABLE ONLY public.membres DROP CONSTRAINT membres_email_key;
       public                 postgres    false    220            j           2606    32824    membres membres_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.membres
    ADD CONSTRAINT membres_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.membres DROP CONSTRAINT membres_pkey;
       public                 postgres    false    220            n           2606    32835    emprunts unique_emprunt 
   CONSTRAINT     o   ALTER TABLE ONLY public.emprunts
    ADD CONSTRAINT unique_emprunt UNIQUE (membre_id, livre_id, date_emprunt);
 A   ALTER TABLE ONLY public.emprunts DROP CONSTRAINT unique_emprunt;
       public                 postgres    false    222    222    222            o           2606    32841    emprunts emprunts_livre_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.emprunts
    ADD CONSTRAINT emprunts_livre_id_fkey FOREIGN KEY (livre_id) REFERENCES public.livres(id) ON DELETE CASCADE;
 I   ALTER TABLE ONLY public.emprunts DROP CONSTRAINT emprunts_livre_id_fkey;
       public               postgres    false    4710    218    222            p           2606    32836     emprunts emprunts_membre_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.emprunts
    ADD CONSTRAINT emprunts_membre_id_fkey FOREIGN KEY (membre_id) REFERENCES public.membres(id) ON DELETE CASCADE;
 J   ALTER TABLE ONLY public.emprunts DROP CONSTRAINT emprunts_membre_id_fkey;
       public               postgres    false    220    4714    222               O   x�M��� ���Ul�Kt���C*,�l��C�h���V�؟	{��UJ���oTW��@��\�3} s9�W�~�� ���         /  x�US]o�@|^���E1�4y$@iHDE�����x�qK��$������������8�$	�A|�0�I�3V�K��<�7;-��L���.�w�}���5�Kxc�`�Ƥ;a�e=�i��H�L9l���^�3�=���n��4����$#41����][�g��Z3NS�	�X��.KM	ǒ��9���{��*~3J$�i��.$T0�`�����M���>�Tֶ'�}.Z�9���|����I8�	|b�Wu�@S�W�?C���_�6F�-���Nw|0 ��a�����Xo��Zi���㦽�+��>О��p����J������9�~�w��gcmM̩q�CeW�E�y�xnn��/�b�M��K�u%���>���k����sXP�&�*��^�!?e7բ�d�.�9��KxL>Ql-���\�8�hQ�#|72�aڬ�S�S^#�����Ưq"�Y�?�@~���@�Z�fߞB��z)�p��agٱJ�I<2�N�-�*����Y9����<�W�ͤ�	r(���eb��_�Ϸ��,�> ���           x�m��n� Eח������U"Em��g�P���]��8���;ܙ#�
s�	/dz|� �Ÿ�_�#�Z�K�U�4&N���.�0��Hs-Y�;���g�:�X��8-^j��l�=}�O$ܧ�$]�����lY��������	_�����-[�H���Vi��٩ k�5�����R��b�����Z*t�ɶx�q��=�����W�n��w���5��2x3b*"��V�W�)��.��<�9G��D���U2}5���?4��     