package com.rkt;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.rkt.public_.tables.Users.USERS;

public class Sql {

    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:/Users/cbit011187/Desktop/Workspace/projects/jooq", "admin", "admin")) {
            DSL.using(connection).transaction(configuration -> {
                DSLContext context = DSL.using(connection);

                //safe point
                context.transaction(config -> {
                    insert(context, "tom@test.com");
                    //insert committed
                });

                //update(context);
                delete(context, "tom@test.com");

                readAllUsers(context);
                //System.out.println("Test "+args[2]);
                //implicit commit or rollback
            });
        }
    }

    private static void readAllUsers(DSLContext context) {
        Result<Record2<String, Integer>> records = context.select(USERS.EMAIL, USERS.ID).from(USERS).fetch();
        for (Record record : records) {
            String email = record.get(USERS.EMAIL);
            Integer id = record.get(USERS.ID);

            System.out.println(email);
        }
    }

    private static void insert(DSLContext context, String email) {
        context.insertInto(USERS, USERS.EMAIL).values(email).execute();
    }

    private static void update(DSLContext context, String fromEmail, String toEmail) {
        context.update(USERS).set(USERS.EMAIL, toEmail)
                .where(USERS.EMAIL.eq(fromEmail)).execute();
    }

    private static void delete(DSLContext context, String email) {
        context.delete(USERS).where(USERS.EMAIL.eq(email)).execute();
    }
}
