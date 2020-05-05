DELETE
FROM user_role
WHERE user_uuid IS NOT NULL;
DELETE
FROM voice
WHERE user_uuid IS NOT NULL;
DELETE
FROM user
WHERE uuid IS NOT NULL;
DELETE
FROM role
WHERE uuid IS NOT NULL;
DELETE
FROM dish
WHERE uuid IS NOT NULL;
DELETE
FROM menu
WHERE uuid IS NOT NULL;
DELETE
FROM restaurant
WHERE uuid IS NOT NULL;

-- region user
INSERT INTO user(uuid, name, email, password)
VALUES ('0151e05d-3185-4304-b9dd-9fc8f97db290', 'Администратор', 'admin@foodvoice.ru', ''),
       ('f16eb5ea-14bc-42b0-809a-333639464730', 'Пользователь', 'user@foodvoice.ru', '');
-- endregion

-- region role
INSERT INTO role(uuid, name)
VALUES ('2a9cf406-7f34-4cc7-a4d0-d4f12056a9d9', 'ADMIN'),
       ('1c99e5d3-0bc3-4607-b867-1cde3ce160ce', 'USER');
-- endregion

-- region user_role
INSERT INTO user_role(user_uuid, role_uuid)
VALUES ('0151e05d-3185-4304-b9dd-9fc8f97db290', '2a9cf406-7f34-4cc7-a4d0-d4f12056a9d9'),
       ('0151e05d-3185-4304-b9dd-9fc8f97db290', '1c99e5d3-0bc3-4607-b867-1cde3ce160ce'),
       ('f16eb5ea-14bc-42b0-809a-333639464730', '1c99e5d3-0bc3-4607-b867-1cde3ce160ce');
-- endregion

-- region restaurant
INSERT INTO restaurant(uuid, name, address, email)
VALUES ('58b3d8b0-7d08-4274-8aa8-68976d0582ee', 'Месье Колбасье', 'Город, Улица, дом', 'monsieur_sausage@foodvoice.ru'),
       ('01f8e5df-ac2e-4898-b72c-ac62123c21ed', 'Звери как звери', 'Город, Улица, дом',
        'animals_like_animals@foodvoice.ru');
-- endregion

-- region menu
INSERT INTO menu(uuid, name, restaurant_uuid, date)
VALUES ('dd0e6ec6-1532-4219-bab4-2e04a31b6a01', 'Русская кухня', '58b3d8b0-7d08-4274-8aa8-68976d0582ee', '2020-05-05'),
       ('62e1e74e-ecd7-4d82-a41a-325f91aeddf1', 'Европейская кухня', '01f8e5df-ac2e-4898-b72c-ac62123c21ed',
        '2020-05-05');
-- endregion

-- region dish
INSERT INTO dish(uuid, name, menu_uuid, price)
VALUES ('a29de34f-82cb-4fc3-a3f4-7fc37398b987', 'Блины', 'dd0e6ec6-1532-4219-bab4-2e04a31b6a01', 10000),
       ('e43f72a2-0ee4-4f1f-9f61-ff3d185438ce', 'Селёдка под шубой', 'dd0e6ec6-1532-4219-bab4-2e04a31b6a01', 10000),
       ('3007713f-223d-4d66-8d77-8ce0b9d97147', 'ЩИ', 'dd0e6ec6-1532-4219-bab4-2e04a31b6a01', 10000),
       ('e8cf21e1-4de1-46a3-8f2c-bae6fc87c579', 'Квас', 'dd0e6ec6-1532-4219-bab4-2e04a31b6a01', 10000),
       ('61c07aa6-3099-4e33-a29c-493e04f969f3', 'Цезарь', '62e1e74e-ecd7-4d82-a41a-325f91aeddf1', 10000),
       ('d15c5f85-cfdf-43d1-a648-d9560031e452', 'Грильята', '62e1e74e-ecd7-4d82-a41a-325f91aeddf1', 10000),
       ('1e471d70-e992-41d2-a98f-490a9dc916b0', 'Пицца', '62e1e74e-ecd7-4d82-a41a-325f91aeddf1', 10000),
       ('46a22c10-d830-456c-b62c-7f8395cfaf3d', 'Вино', '62e1e74e-ecd7-4d82-a41a-325f91aeddf1', 10000);
-- endregion

-- region voice
INSERT INTO voice(user_uuid, menu_uuid)
VALUES ('0151e05d-3185-4304-b9dd-9fc8f97db290', 'dd0e6ec6-1532-4219-bab4-2e04a31b6a01'),
       ('f16eb5ea-14bc-42b0-809a-333639464730', '62e1e74e-ecd7-4d82-a41a-325f91aeddf1');
-- endregion