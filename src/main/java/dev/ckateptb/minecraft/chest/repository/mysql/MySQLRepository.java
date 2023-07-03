package dev.ckateptb.minecraft.chest.repository.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.ckateptb.minecraft.chest.repository.AbstractRepository;

public abstract class MySQLRepository<ENTITY, ID> extends AbstractRepository<ENTITY, ID> {
    public MySQLRepository(Class<ENTITY> entityClass) {
        super(entityClass);
    }

    @Override
    public HikariDataSource createDatasource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl(this.getJDBCUrl());
        hikariConfig.setUsername(this.getUsername());
        hikariConfig.setPassword(this.getPassword());
        return new HikariDataSource(hikariConfig);
    }

    protected abstract String getUsername();

    protected abstract String getPassword();

    protected abstract String getJDBCUrl();
}
