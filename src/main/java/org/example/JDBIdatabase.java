package org.example;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.StatementException;

import java.util.List;
import java.util.Optional;

public class JDBIdatabase {

    private final String url = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7724614";
    private final String user = "sql7724614";
    private final String password = "hZCHpYfLa2";

    public static void main(String[] args) {
        JDBIdatabase jdbiDatabase = new JDBIdatabase();
        jdbiDatabase.executeDatabaseOperation();
    }

    public void executeDatabaseOperation() {
        Jdbi jdbi = Jdbi.create(url, user, password);
        try (Handle handle = jdbi.open()) {
            handle.execute("CREATE TABLE IF NOT EXISTS usersfinal (id SERIAL PRIMARY KEY, name VARCHAR(100))");
            handle.execute("INSERT INTO usersfinal (name) VALUES (?)", "John Doe");
            handle.execute("INSERT INTO usersfinal (name) VALUES (?)", "Jane Doe");


            // Sorgu sonucunun alınması
            String result = handle.createQuery("SELECT name FROM usersfinal WHERE id = :id")
                    .bind("id", 1)
                    .mapTo(String.class)
                    .one();

            System.out.println("Result Before Update: " + result);

            handle.execute("UPDATE usersfinal SET name = 'Deniz Konuk' WHERE NAME = 'John Doe'");

            String resultafterupdate = handle.createQuery("SELECT name FROM usersfinal WHERE id = :id")
                    .bind("id", 1)
                    .mapTo(String.class)
                    .one();
            System.out.println("Result After Update: " + resultafterupdate);

            String resultid2 = handle.createQuery("SELECT name FROM usersfinal WHERE id = :id")
                    .bind("id", 2)
                    .mapTo(String.class)
                    .one();
            System.out.println(resultid2);

            handle.execute("DELETE FROM usersfinal WHERE name = 'Deniz Konuk'");
            String resultdelete = handle.createQuery("SELECT name FROM usersfinal WHERE id = :id")
                    .bind("id", 2)
                    .mapTo(String.class)
                    .one();
            System.out.println(resultdelete);


        } catch (StatementException e) {
            e.printStackTrace();
        }
    }
}
