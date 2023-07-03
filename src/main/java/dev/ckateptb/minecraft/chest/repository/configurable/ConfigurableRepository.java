package dev.ckateptb.minecraft.chest.repository.configurable;

import com.j256.ormlite.dao.Dao;
import dev.ckateptb.minecraft.chest.repository.Repository;
import dev.ckateptb.minecraft.chest.repository.mysql.MySQLRepository;
import dev.ckateptb.minecraft.chest.repository.sqlite.SQLiteRepository;
import lombok.experimental.Delegate;

import java.nio.file.Path;

public abstract class ConfigurableRepository<ENTITY, ID> implements Repository<ENTITY, ID> {
    @Delegate
    private final Repository<ENTITY, ID> repository;
    @Delegate
    private final Dao<ENTITY, ID> dao;

    public ConfigurableRepository(Class<ENTITY> entityClass, String username, String password, String jdbcUrl) {
        this.repository = new MySQLRepository<ENTITY, ID>(entityClass) {
            @Override
            protected String getUsername() {
                return username;
            }

            @Override
            protected String getPassword() {
                return password;
            }

            @Override
            protected String getJDBCUrl() {
                return jdbcUrl;
            }
        };
        this.dao = this.repository.getDao();
    }

    public ConfigurableRepository(Class<ENTITY> entityClass, Path path) {
        this.repository = new SQLiteRepository<ENTITY, ID>(entityClass) {
            @Override
            public Path getPath() {
                return path;
            }
        };
        this.dao = this.repository.getDao();
    }

}
