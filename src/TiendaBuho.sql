
CREATE DATABASE tiendabuho;
USE tiendabuho;

CREATE TABLE rol(
	tipo_rol VARCHAR(3) PRIMARY KEY NOT NULL,
    desc_rol VARCHAR(15) NOT NULL
);

INSERT INTO rol VALUES ('adm', 'administrador'), ('ven','vendedor');

CREATE TABLE proveedor(
	ident_prov VARCHAR(13) PRIMARY KEY NOT NULL,
    nom_prov VARCHAR(20) NOT NULL,
    tel_prov VARCHAR(10) NULL,
    ema_prov VARCHAR(30) NULL 
);

INSERT INTO proveedor VALUES ('1752774305001', 'PROCAN S.A', '0961731484', 'procan@gmail.com'), 
('1752774306001', 'JURIS', '0961731485', 'juris@gmail.com'),
('1752774307001', 'FRITZS', '0961731486', 'fritzs@hotmail.com');


CREATE TABLE producto(
	cod_Pro VARCHAR(7) PRIMARY KEY NOT NULL,
    det_Pro VARCHAR(50) NOT NULL,
    preUni_Pro DOUBLE NOT NULL,
    preVen_Pro DOUBLE NOT NULL,
    sto_Pro INT NOT NULL,
    desc_Pro DECIMAL(4,2) NOT NULL,
    FKident_Prov VARCHAR(13) NULL,
    FOREIGN KEY (FKident_Prov) REFERENCES proveedor(ident_prov) ON DELETE SET NULL ON UPDATE CASCADE
); 

INSERT INTO producto VALUES('MOR-FAM', 'Mortadela Familiar (12 U)',1.5, 2.5, 12, 0.0, '1752774307001'),
('TOC-JUR', 'Tocino Juris (12 U)',3.0, 3.5, 24, 0.0, '1752774306001'),
('SAL-FAM', 'Salchichas Familiar (10 U)',1.0, 1.5, 12, 0.0, '1752774307001');


CREATE TABLE cliente(
	ident_Cli VARCHAR(13) NOT NULL PRIMARY KEY,
    nom_Cli VARCHAR(10) NOT NULL,
    ape_Cli VARCHAR(15) NOT NULL,
    tel_Cli VARCHAR(10) NULL,
    ema_Cli VARCHAR(30) NULL,
    dir_Cli VARCHAR(60) NULL
);

INSERT INTO cliente VALUES ('1752774305', 'Miguel', 'Carapaz', '1659874106', 'miguel.carapaz@gmail.com', 'La Loma');

INSERT INTO cliente(ident_Cli, nom_Cli, ape_Cli) 
VALUES ('9999999999999', 'CONSUMIDOR', 'FINAL');

CREATE TABLE Usuario(
	ident_Usu VARCHAR(10) PRIMARY KEY NOT NULL,
    nom_Usu VARCHAR(10) NOT NULL,
    ape_Usu VARCHAR(15) NOT NULL,
    ing_Usu DATE NOT NULL,
    tel_Usu VARCHAR(10) NOT NULL,
    ema_Usu VARCHAR(30) NOT NULL,
    FKtipo_rol VARCHAR(3) NULL,
    usuN_Usu VARCHAR(10) NOT NULL,
    pass_Usu VARCHAR(255) NOT NULL,
    FOREIGN KEY (FKtipo_rol) REFERENCES rol(tipo_rol) ON DELETE SET NULL ON UPDATE CASCADE
);

INSERT INTO usuario
VALUES('1752774305', 'Franco', 'Escamilla', '2021-01-02', '0987878987', 'franco@gmail.com', 'adm', 'admin', 'admin'),
('1752664471', 'Juan', 'Carlos', '2022-09-13', '0961731484', 'juan@gmail.com', 'adm', 'carlos', '123');

CREATE TABLE empresa(
	ruc_Emp VARCHAR(13) PRIMARY KEY,
    nom_Emp VARCHAR(30) NOT NULL,
    tel_Emp VARCHAR(10) NOT NULL,
    ema_Emp VARCHAR(30) NOT NULL,
    dir_Emp VARCHAR(60) NOT NULL
);

INSERT INTO empresa VALUES('DEFAULT', 'DEFAULT', 'DEFAULT', 'DEFAULT', 'DEFAULT');

CREATE TABLE CabFactura(
	num_CF INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    FKruc_Emp VARCHAR(13) NULL,
    FKident_Cli VARCHAR(13) NULL,
    cident_Cli VARCHAR(13) NULL,
    FKident_Usu VARCHAR(10) NULL,
    cident_Usu VARCHAR(10) NULL,
    nom_Usu VARCHAR(10) NULL,
    nomCli_CF VARCHAR(10) NULL,
    apeCli_CF VARCHAR(15) NULL,
    telCli_CF VARCHAR(10) NULL,
    dirCli_CF VARCHAR(60) NULL,
    fecha_CF DATE NULL,
    subT_CF DOUBLE NULL DEFAULT 0.0,
    iva_CF DOUBLE NULL,
    valT_CF DOUBLE NULL DEFAULT 0.0, 
    FOREIGN KEY (FKident_Cli) REFERENCES cliente(ident_Cli) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (FKident_Usu) REFERENCES Usuario(ident_Usu) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (FKruc_Emp) REFERENCES empresa(ruc_Emp) ON DELETE SET NULL ON UPDATE CASCADE 
);

CREATE TABLE detalle(
	id_De INT AUTO_INCREMENT PRIMARY KEY,
    FKnum_CF INT NOT NULL,
    FKcod_pro VARCHAR(7) NULL,
    codPro_Det VARCHAR(7) NOT NULL,
    detPro_Det VARCHAR(50) NOT NULL,
    cantPro_Det INT NOT NULL,
    pUPro DOUBLE NOT NULL,
    descPro DOUBLE NOT NULL,
    valto_det DOUBLE NOT NULL,
    FOREIGN KEY (FKnum_CF) REFERENCES CabFactura(num_CF) ON DELETE CASCADE ,
    FOREIGN KEY (FKcod_pro) REFERENCES producto(cod_Pro) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE pathPdf(
	id INT PRIMARY KEY,
    path_save VARCHAR(255) NULL DEFAULT 'Nothing'
);

INSERT INTO pathPdf(id) VALUES (1);

SELECT * FROM pathPdf;

