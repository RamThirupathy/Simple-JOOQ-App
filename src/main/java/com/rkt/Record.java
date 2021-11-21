package com.rkt;

import com.rkt.public_.tables.Users;
import com.rkt.public_.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.rkt.public_.tables.Users.USERS;

public class Record {

    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:/Users/cbit011187/Desktop/Workspace/projects/jooq", "admin", "admin")) {
            DSL.using(connection).transaction(configuration -> {
                DSLContext context = DSL.using(configuration);

                UsersRecord usersRecord = context.newRecord(Users.USERS);
                usersRecord.setEmail("tom@test.com");
                usersRecord.store();

                //UsersRecord tomRecord = context.fetchOne(USERS, USERS.EMAIL.eq("tom@test.com"));
                usersRecord.setEmail("toms@test.com");
                usersRecord.store();

                Result<UsersRecord> usersRecords = context.selectFrom(Users.USERS).fetch();
                for (UsersRecord record : usersRecords) {
                    System.out.println(record.getEmail());
                }
            });
        }
    }
}
