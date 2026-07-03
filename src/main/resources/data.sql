INSERT IGNORE INTO users (id, name, email, cpf, role)
VALUES
    (
        1,
        'Igor Couto',
        'igorcoutinho3100@gmail.com',
        '11122233345',
        'CLIENTE'
    ),
    (
        2,
        'Maria Silva',
        'maria.silva@empresa.com',
        '11122233344',
        'CLIENTE'
    ),
    (
        3,
        'Carlos Tech',
        'carlos.dev@startup.com',
        '55566677788',
        'CLIENTE'
    ),
    (
        4,
        'Roberto Silva',
        'roberto.driver@email.com',
        '99988877766',
        'MOTORISTA'
    ),
    (
        5,
        'Ana Souza',
        'ana.entregas@email.com',
        '55544433322',
        'MOTORISTA'
    );

INSERT IGNORE INTO vehicles (id, model, plate, category, driver_id)
VALUES
    (
        1,
        'Fiat Argo 2022',
        'ABC-1234',
        'CARRO_PASSEIO',
        4
    ),
    (2, 'Honda CG 160', 'XYZ-9876', 'MOTO', 5);