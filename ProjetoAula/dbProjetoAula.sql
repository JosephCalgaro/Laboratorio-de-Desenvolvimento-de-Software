create database bdaula01;

use bdaula01;

CREATE table pessoa(
id int auto_increment primary key,
nome varchar(50) not null,
sexo varchar(1) not null,
idioma varchar(10) not null
);

create table veiculo(
id int auto_increment primary key,
modelo varchar(30),
placa varchar(7),
id_pessoa int not null,
foreign key(id_pessoa) references pessoa(id)
);