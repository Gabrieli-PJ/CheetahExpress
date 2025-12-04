-- Usuário ADMIN
INSERT INTO users (nome, senha, role, enabled)
VALUES (
           'Administrador',
           '$2a$10$6FtuJhIMjRZ8mMWn2nPbQeAL0Zx1V0zG1KMfTOZtJ9qiKT1F0wxnC', -- admin123
           'ADMIN',
           true
       );

-- Usuário CLIENTE (CUSTOMER)
INSERT INTO users (nome, senha, role, enabled)
VALUES (
           'Cliente Padrão',
           '$2a$10$/90ul3r.7b9firgPtBl2O.MuRpgARYL16zpcGo9aYJKCwFAKIbB8W', -- cliente123
           'CLIENTE',
           true
       );

-- Usuário MOTORISTA (DRIVER)
INSERT INTO users (nome, senha, role, enabled)
VALUES (
           'Motorista Padrão',
           '$2a$10$15d/0qhcMHU4zu03Mkik1uDOD3uIlqPGIY8pp2qG8mYVJMcu6Pb36', -- driver123
           'MOTORISTA',
           true
       );
