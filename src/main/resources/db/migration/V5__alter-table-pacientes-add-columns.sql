alter table pacientes add column calle varchar(100) not null,
                      add column distrito varchar(100) not null,
                      add column complemento varchar(100),
                      add column numero varchar(20),
                      add column ciudad varchar(100) not null;