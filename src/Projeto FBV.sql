--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

-- Started on 2023-11-28 05:20:37

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 224 (class 1255 OID 16572)
-- Name: autentica_usu(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.autentica_usu() RETURNS trigger
    LANGUAGE plpgsql
    AS $$

    DECLARE

    BEGIN
        INSERT INTO usuario_login
        VALUES (new.nm_usuario, new.pw_senha);

        RETURN NEW;
    END;
$$;


ALTER FUNCTION public.autentica_usu() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 221 (class 1259 OID 16596)
-- Name: jogo_usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jogo_usuario (
    cd_jogo_usuario integer NOT NULL,
    cd_usuario integer NOT NULL,
    cd_jogo integer NOT NULL
);


ALTER TABLE public.jogo_usuario OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16595)
-- Name: jogo_usuario_cd_jogo_usuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.jogo_usuario_cd_jogo_usuario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.jogo_usuario_cd_jogo_usuario_seq OWNER TO postgres;

--
-- TOC entry 4824 (class 0 OID 0)
-- Dependencies: 220
-- Name: jogo_usuario_cd_jogo_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.jogo_usuario_cd_jogo_usuario_seq OWNED BY public.jogo_usuario.cd_jogo_usuario;


--
-- TOC entry 223 (class 1259 OID 16603)
-- Name: jogos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jogos (
    cd_jogo integer NOT NULL,
    nm_jogo character varying(50) NOT NULL,
    ds_foto character varying(100)
);


ALTER TABLE public.jogos OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16602)
-- Name: jogos_cd_jogo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.jogos_cd_jogo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.jogos_cd_jogo_seq OWNER TO postgres;

--
-- TOC entry 4825 (class 0 OID 0)
-- Dependencies: 222
-- Name: jogos_cd_jogo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.jogos_cd_jogo_seq OWNED BY public.jogos.cd_jogo;


--
-- TOC entry 219 (class 1259 OID 16589)
-- Name: regiao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.regiao (
    cd_regiao integer NOT NULL,
    ds_regiao character varying(6) NOT NULL
);


ALTER TABLE public.regiao OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16588)
-- Name: regiao_cd_regiao_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.regiao_cd_regiao_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.regiao_cd_regiao_seq OWNER TO postgres;

--
-- TOC entry 4826 (class 0 OID 0)
-- Dependencies: 218
-- Name: regiao_cd_regiao_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.regiao_cd_regiao_seq OWNED BY public.regiao.cd_regiao;


--
-- TOC entry 216 (class 1259 OID 16563)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    cd_usuario integer NOT NULL,
    nm_usuario character varying(15) NOT NULL,
    pw_senha character varying(15) NOT NULL,
    ds_nome character varying(100),
    ds_email character varying(50) NOT NULL,
    ds_regiao character varying(6),
    ds_ddd character varying(4),
    ds_telefone character varying(10)
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16562)
-- Name: usuario_cd_usuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_cd_usuario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.usuario_cd_usuario_seq OWNER TO postgres;

--
-- TOC entry 4827 (class 0 OID 0)
-- Dependencies: 215
-- Name: usuario_cd_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_cd_usuario_seq OWNED BY public.usuario.cd_usuario;


--
-- TOC entry 217 (class 1259 OID 16569)
-- Name: usuario_login; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario_login (
    nm_usuario character varying(15) NOT NULL,
    pw_senha character varying(15) NOT NULL
);


ALTER TABLE public.usuario_login OWNER TO postgres;

--
-- TOC entry 4656 (class 2604 OID 16599)
-- Name: jogo_usuario cd_jogo_usuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jogo_usuario ALTER COLUMN cd_jogo_usuario SET DEFAULT nextval('public.jogo_usuario_cd_jogo_usuario_seq'::regclass);


--
-- TOC entry 4657 (class 2604 OID 16606)
-- Name: jogos cd_jogo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jogos ALTER COLUMN cd_jogo SET DEFAULT nextval('public.jogos_cd_jogo_seq'::regclass);


--
-- TOC entry 4655 (class 2604 OID 16592)
-- Name: regiao cd_regiao; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.regiao ALTER COLUMN cd_regiao SET DEFAULT nextval('public.regiao_cd_regiao_seq'::regclass);


--
-- TOC entry 4654 (class 2604 OID 16566)
-- Name: usuario cd_usuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN cd_usuario SET DEFAULT nextval('public.usuario_cd_usuario_seq'::regclass);


--
-- TOC entry 4816 (class 0 OID 16596)
-- Dependencies: 221
-- Data for Name: jogo_usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jogo_usuario (cd_jogo_usuario, cd_usuario, cd_jogo) FROM stdin;
1	1	1
2	1	2
\.


--
-- TOC entry 4818 (class 0 OID 16603)
-- Dependencies: 223
-- Data for Name: jogos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jogos (cd_jogo, nm_jogo, ds_foto) FROM stdin;
1	Valorant	\N
2	Counter-Strike 2	\N
3	League of Legends	\N
4	League of Legends - Wild Rift	\N
5	Call of Duty - Modern Warfare 3	\N
\.


--
-- TOC entry 4814 (class 0 OID 16589)
-- Dependencies: 219
-- Data for Name: regiao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.regiao (cd_regiao, ds_regiao) FROM stdin;
1	USA
2	LATAM
3	EUR
4	KOR
5	ASIA
6	GLOBAL
\.


--
-- TOC entry 4811 (class 0 OID 16563)
-- Dependencies: 216
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (cd_usuario, nm_usuario, pw_senha, ds_nome, ds_email, ds_regiao, ds_ddd, ds_telefone) FROM stdin;
3	paulovictor	12345678	paulovictor	paulovictor@mysquad.com	\N	\N	\N
2	ericaxavier	54321	Kekinha	ericaxavier@mysquad.com	LATAM	\N	\N
4	vitorhenrique	unifbv	Vitor Henrique Silva	vitorhenrique@mysquad.com	LATAM	\N	\N
5	jonesneves	102030	Jones das Novinhas	jonesneves@mysquad.com	LATAM	\N	\N
1	andremelo	12345	André Melo	andremelo@mysquad.com	LATAM	\N	\N
6	faustogominho	12345	Fausto FBV	fausto@mysquad.com	LATAM	\N	\N
\.


--
-- TOC entry 4812 (class 0 OID 16569)
-- Dependencies: 217
-- Data for Name: usuario_login; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario_login (nm_usuario, pw_senha) FROM stdin;
andremelo	12345
ericaxavier	54321
paulovictor	12345678
vitorhenrique	unifbv
jonesneves	102030
faustogominho	12345
\.


--
-- TOC entry 4828 (class 0 OID 0)
-- Dependencies: 220
-- Name: jogo_usuario_cd_jogo_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.jogo_usuario_cd_jogo_usuario_seq', 2, true);


--
-- TOC entry 4829 (class 0 OID 0)
-- Dependencies: 222
-- Name: jogos_cd_jogo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.jogos_cd_jogo_seq', 5, true);


--
-- TOC entry 4830 (class 0 OID 0)
-- Dependencies: 218
-- Name: regiao_cd_regiao_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.regiao_cd_regiao_seq', 6, true);


--
-- TOC entry 4831 (class 0 OID 0)
-- Dependencies: 215
-- Name: usuario_cd_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_cd_usuario_seq', 6, true);


--
-- TOC entry 4663 (class 2606 OID 16601)
-- Name: jogo_usuario jogo_usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jogo_usuario
    ADD CONSTRAINT jogo_usuario_pkey PRIMARY KEY (cd_jogo_usuario);


--
-- TOC entry 4665 (class 2606 OID 16608)
-- Name: jogos jogos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jogos
    ADD CONSTRAINT jogos_pkey PRIMARY KEY (cd_jogo);


--
-- TOC entry 4661 (class 2606 OID 16594)
-- Name: regiao regiao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.regiao
    ADD CONSTRAINT regiao_pkey PRIMARY KEY (cd_regiao);


--
-- TOC entry 4659 (class 2606 OID 16568)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (cd_usuario);


--
-- TOC entry 4666 (class 2620 OID 16573)
-- Name: usuario trg_autentica_usu; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER trg_autentica_usu AFTER INSERT ON public.usuario FOR EACH ROW EXECUTE FUNCTION public.autentica_usu();


-- Completed on 2023-11-28 05:20:38

--
-- PostgreSQL database dump complete
--

