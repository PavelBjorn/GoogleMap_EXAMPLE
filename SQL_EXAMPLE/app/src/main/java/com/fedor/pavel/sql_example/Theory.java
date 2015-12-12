package com.fedor.pavel.sql_example;


interface Theory {

    /*SQLight - Structured Query Language (название.db)
    *  ------ Типы данных для ДБ:
    *
    * - INTEGER - цлочисленный тип данных
    * - REAL -  тип с плавающей запятой (аналог double или float)
    * - TEXT -  текст
    * - BLOB -  массив байт (хранить картинки не рекомендуется)
    * - NULL - отсутствие какой либо информации
    *
    *  ----- Пример создания дб:
    *
    * 1 - Создание таблицы (CREATE  TABLE [Название таблицы] (
    *                   _id integer PRIMARY KEY AUTOINCREMENT,
    *                   name text,
    *                   age integer NOT NULL,
    *                   x real ,
    *                   );)
    *
    *      CREATE  TABLE [Название таблицы]()- создает таблицу
    *      [имя] [тип] [команда] - создание колонки
    *      integer - тип данных в колонке
    *      PRIMARY KEY - первичный ключ для модели (В одной таблиу может быть только один)
    *      AUTOINCREMENT - автоматически генерирует значение
    *      NOT NULL - команда указывает что в поле не может быть null
    *
    * 2 - Удаление таблицы:
    *
    *     - DROP TABLE [IF EXIST(рекомендуется указывать чтоб не выкинуло таблицу)] [имя];
    *
    * 3 - Вставка:
    *
    *     - INSERT INTO [имя таблицы] ([имя поля1], [имя поля 2], .... [имя поля n]) VALUES ("Значение поля 1", "Значение поля 2", ... "Значение поля n" );
    *
    * 4 - Выборка:
    *
    *       - SELECT * FROM [имя таблицы];
    *                * - указывает что нам нужно выбрать  данные из все колонок
    *       - SELECT * FROM [имя таблицы] WHERE [указание имя колонки] = [значение] - возвращает поля с данным значением;
    *       - SELECT [имя колонок] FROM [имя таблицы] -  возвращает значение из даной колонки;
    *       - SELECT  * FROM [имя табл] WHERE [колонка] [условие] [значение] OR [колонка] [условие] [значение] - возвращает данные или те или те
    *                                                                        AND - возвращает данные стем и тем значением
    *
    *        - SELECT  * FROM [имя табл] WHERE [колонка] [условие] [значение] IS NULL - проверка на null;
    *
    *        - SELECT [имя колонок] FROM [имя таблицы] WHERE [колонка] LIKE [значние%] - возвращает поля в корых могут начинатся
     *                       % - сколько угодн букв после
     *
     *       - SELECT [имя колонок] FROM [имя таблицы] WHERE [колонка] LIKE [_значние]
     *                        _вместо утсутствующей буквы
    *
    *        - SELECT * FROM [имя табл] WHERE [колонка] [условие] [значение]  AND [колонка] [условие] [значение]
    *        - SELECT * FROM [имя табл] WHERE [колонка] [условие] [значение]  BETWEEN [значение] AND [значение] - выборка диапазона
    *        - SELECT * FROM [имя табл] WHERE [колонка] IN (25, 30 ..... n) - выбор множества
    *        - SELECT MIN/MAX (имя колонки) FROM [имя табл] - минимальное и максимальное значние
    *        - SELECT DISTINCT [имя колонки] FROM [имя табл] - возвращает одиночное имя
    *
    *
    *5 - Обновить:
    *
    *      - UPDATE [имя табл] SET ([колонка] = [значние], [колонка] = [значние]) WHERE [колонка] = [значение];
    *
    *6 - Удаление:
    *
    *      - DELETE FROM [имя таблицы] WHERE [колонка]> = [значение];
    *      - DELETE * FROM [имя таблицы] - удаляет значения из данного списка
    *
    *7 - Редактирование
    *
    *      - ALTER TABLE [имя таблицы] ADD COLUMN [имя колонки] TEXT -добавляет колонку
    *      - ALTER TABLE [имя таблицы] RENAME TO [имя колонки]  - переиминовывает колонку
    *
    *8 - Сортировка
    *      - SELECT [имя колонок] FROM [имя таблицы] WHERE [колонка][условие][значение] ORDER BY [колонка]
    *
    *
    *  ------ Команды:
    *
    *  INSERT - вставка информации
    *  SELECT - оператор выбора или чтения информации
    *  UPDATE - обновление информации
    *  DELETE - удаление информации
    *
    *  ------ Операторы
    *
    *   OR - оператор или
    *   AND - оператор и
    *   NOT - оператор "не";
    *   COUNT - возвращает кол-во записей не считая удаленные
    *
    *  ------ Получение данных и нескольких таблиц
    *            users                              city
    *   id   *   name    *  cty_id               _id   *  name
    *        *                                         *
    *   -----*-----------*----------            -------*-----
    *   -----*-----------*----------            -------*-----
    *   -----*-----------*----------            -------*-----
    *
    *    SELECT user._id, users.name, city.name FROM users INNER JOIN city ON (users.city_id =city._id )
    *
    *     SELECT u.id, u.name, c.name FROM users AS u INNER JOIN city AS c ON (u.cityId = c.id)
    *     SELECT u.id, u.name, c.name FROM users AS u INNER JOIN city AS c ON (u.cityId = c.id) WHERE c.id = 4
    *
    *     SELECT * INTO new_table FROM table - копировать из таблицы в таблицы
    *
    *
    *  сайт с доп информацией http://www.w3schools.com/sql/
    *
    *   в Андроид
    *   db.execSQL("CREATE TABLE DATABASE_TABLE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
         + "NAME TEXT, "
         + "DESCRIPTION TEXT, "
         + "IMAGE_RESOURCE_ID INTEGER);");

               Cursor query (String table,
                            String[] columns,  -  передать  null то же что передать *
                            String selection,
                            String[] selectionArgs,
                            String groupBy,
                            String having,
                            String sortOrder)


                            Cursor cursor = db.query("student",
                            new String[] {"name", "age"},
                            "name = ?",
                             new String[] {"Игорь"},
                             null, null, null);

                    SELECT name, age FROM students WHERE name='Игорь'

                    Cursor cursor = db.query("students",
                    new String[] {"name", "age"},
                    "name = ? AND AGE >= ?",
                    new String[] {"Игорь", "20"},
                    null, null, null);

                    SELECT name, age FROM students WHERE name='Игорь' AND age >=20;

                    long insert (String table, String nullColumnHack, ContentValues values);

                    // Создайте новую строку со значениями для вставки.
                    ContentValues newValues = new ContentValues();
                    // Задайте значения для каждой строки.
                    newValues.put(COLUMN_NAME, newValue);
                    [ ... Повторите для каждого столбца ... ]
                    // Вставьте строку в вашу базу данных.
                    myDatabase.insert(DATABASE_TABLE, null, newValues);

                    ContentValues newValues = new ContentValues();

                    int update (String table, ContentValues values, String whereClause, String[] whereArgs)
                    int delete (String table, String whereClause, String[] whereArgs)



                    newValues.put("name", "Игорь2");
                    String where = "name" + "=" + "Игорь";
                    String where = "name" + "=" + "?";
                    null -> new String[]{"Игорь"}
                    String where = "name=? AND age=? AND city=?";
                    new String[]{"Игорь", "20", "Днепр"}
                    myDatabase.delete(DATABASE_TABLE, "age=" + 20, null)
                    myDatabase.delete(DATABASE_TABLE, "age=?", new String[]{"20"})

    *
    *
    */

}
