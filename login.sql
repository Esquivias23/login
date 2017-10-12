drop database if exists login;
create database login;

use login;

create table Usuario(
idUsuario int not null primary key,
nombre nvarchar(120) not null,
pass nvarchar(129) not null
);

drop procedure if exists guardaUsuario;

delimiter **

create procedure guardaUsuario(in neim nvarchar(15),in pass nvarchar(15))
begin
declare msj nvarchar(20);
declare idU int; 
	set idU = (select ifnull(max(idUsuario), 0) + 1 from Usuario);
	insert into Usuario(idUsuario, nombre, pass)
    values (idU, neim, pass);
	set msj = 'Usuario Listo';
    
    select msj as 'Resultado';

end; **
delimiter ;

select * from guardaUsuario;
