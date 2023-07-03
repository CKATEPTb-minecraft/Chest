package dev.ckateptb.minecraft.chest.repository;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.SQLException;

public interface Repository<ENTITY, ID> extends AutoCloseable {

    HikariDataSource createDatasource();

    ConnectionSource connect() throws SQLException;

    ConnectionSource getConnection();

    Dao<ENTITY, ID> getDao();
}
