package com.rkt;

import com.rkt.public_.tables.daos.UsersDao;
import com.rkt.public_.tables.pojos.Users;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DataSourceConnectionProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.rkt.public_.tables.Users.USERS;

public class Dao {

    public static void main(String[] args) throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:/Users/cbit011187/Desktop/Workspace/projects/jooq");
        config.setUsername("admin");
        config.setPassword("admin");

        HikariDataSource dataSource = new HikariDataSource(config);
        DSL.using(new DataSourceConnectionProvider(dataSource), SQLDialect.H2).transaction(configuration -> {

            UsersDao dao = new UsersDao(configuration);

            Users user = new Users();
            user.setEmail("Jill@test.com");
            dao.insert(user);

            dao.fetchOne(USERS.EMAIL, "Jill@test.com");
        });
    }
}
