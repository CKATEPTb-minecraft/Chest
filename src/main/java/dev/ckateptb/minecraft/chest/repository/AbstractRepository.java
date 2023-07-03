package dev.ckateptb.minecraft.chest.repository;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.DataSourceConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.Delegate;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;

@Getter
public abstract class AbstractRepository<ENTITY, ID> implements Repository<ENTITY, ID> {
    @Delegate
    protected final Dao<ENTITY, ID> dao;
    protected final ConnectionSource connection;

    @SneakyThrows
    @SuppressWarnings("all")
    public AbstractRepository() {
        Class<ENTITY> entityClass = (Class<ENTITY>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        this.connection = this.connect();
        TableUtils.createTableIfNotExists(this.connection, entityClass);
        this.dao = DaoManager.createDao(this.connection, entityClass);
    }

    @Override
    public ConnectionSource connect() throws SQLException {
        HikariDataSource datasource = this.createDatasource();
        return new DataSourceConnectionSource(datasource, datasource.getJdbcUrl());
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}
