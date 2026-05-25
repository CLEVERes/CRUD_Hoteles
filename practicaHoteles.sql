create database practicaHoteles
charset utf8mb4
collate utf8mb4_spanish2_ci;

use practicaHoteles;

CREATE TABLE hoteles (
    idHotel INT AUTO_INCREMENT,
    nombreHotel VARCHAR(45) NOT NULL,
    direccionHotel VARCHAR(100) NOT NULL,
    PRIMARY KEY (idHotel)
);

CREATE TABLE habitaciones (
    idHabitacion INT AUTO_INCREMENT,
    numeroHabitacion INT NOT NULL,
    precioHabitacion DECIMAL(8 , 2 ) NOT NULL,
    idHotelFK INT NOT NULL,
    PRIMARY KEY (idHabitacion),
    FOREIGN KEY (idHotelFK)
        REFERENCES hoteles (idHotel)
);

CREATE TABLE huespedes (
    idHuesped INT AUTO_INCREMENT,
    nombreHuesped VARCHAR(45) not null,
    telefonoHuesped VARCHAR(12) not null,
    PRIMARY KEY (idHuesped)
);

CREATE TABLE ocupar (
    idOcupar INT AUTO_INCREMENT,
    fechaEntrada DATE NOT NULL,
    fechaSalida DATE,
    idHabitacionFK INT NOT NULL,
    idHuespedFK INT NOT NULL,
    PRIMARY KEY (idOcupar),
    FOREIGN KEY (idHabitacionFK)
        REFERENCES habitaciones (idHabitacion),
    FOREIGN KEY (idHuespedFK)
        REFERENCES huespedes (idHuesped)
);

CREATE TABLE usuarios (
    idUsuario INT AUTO_INCREMENT,
    nombreUsuario VARCHAR(45) NOT NULL,
    claveUsuario VARCHAR(256) NOT NULL,
    tipoUsuario INT NOT NULL,
    PRIMARY KEY (idUsuario)
);

describe hoteles;
describe habitaciones;
describe huespedes;
describe ocupar;
describe usuarios;

select * from hoteles;
select * from habitaciones;
select * from huespedes;
select * from ocupar;
select * from usuarios;
-----------------------------------------------------------------------------------------------------------
create user 'userHoteles'@'localhost' identified by '1234';

grant select, insert, update, delete on practicahoteles.* to 'userHoteles'@'localhost';

select host, user from mysql.user;
flush privileges;
show grants for 'userHoteles'@'localhost';