create database biblioteca;
create table Usuarios (
	idUsuario varchar(50) not null comment "Profesor: Numero de trabajador / Alumno: Matricula",
    nombres varchar(50) not null, 
    apellidoPaterno varchar(50) not null,
    apellidoMaterno varchar(50) not null,
    calle varchar(100) not null,
    numero varchar(20) not null, 
    colonia varchar(50) not null, 
    municipio varchar(50) not null,
    email varchar(200) not null, 
    telefono varchar(10) not null, 
    tipoUsuario varchar(50) not null,
    primary key (idUsuario);
);

create table Empleados(
    numEmpleados int not null comment "Esta es la llave primaria",
    fechaNacimiento date not null,
    nss varchar(11) not null, 
    curp varchar(20) not null,
    telefono varchar(10) not null,
    contrasenia varchar(50) not null,
    tipoContratacion varchar(50) not null,
    idUsuario varchar(50) not null comment "Columna para llave primaria de Usuarios",
    primary key(numEmpleados),
    foreign key(idUsuario) references Usuarios(idUsuario)
);


create table RecibosDePagosPorDevolucionesTardias(
    idReciboPago int not null comment "id (tal vez podamos auto incrementarla)",
    fechaPago date not null,
    metodoPago varchar(50) not null,
    idUsuario varchar(50) not null comment "llave foranea de Usuarios",
    idPrestamo int not null  comment "llave foranea de Prestamos",
    foreign key(idUsuario) references Usuarios(idUsuario),
    foreign key(idPrestamo) references Prestamo(idPrestamo)
);

create table Libros(
    idLibro int not null auto_increment comment " llave primaria",
    autor varchar(100) not null,
    titulo varchar(150) not null, 
    editorial varchar(50) not null, 
    edicion varchar(50) not null, 
    primary key(idLibro)
);

create table almacen(
    numeroCopias int not null,
    idLibro int not null comment "llave foranea",
    foreign key(idLibro) references Libros(idLibro)
);


create table Prestamo(
    idPrestamo int not null auto_increment comment "llave primaria",
    fehaPrestamo date not null,
    fechaDevolucion date not null,
    idUsuario varchar(50) not null comment "llave foranea",
    idLibro int not null comment "llave foranea",
    primary key(idPrestamo),
    foreign key (idUsuario) references Usuarios(idUsuario),
    foreign key(idLibro) references Libros(idLibro)
);



