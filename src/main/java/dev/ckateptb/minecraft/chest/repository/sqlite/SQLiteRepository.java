package dev.ckateptb.minecraft.chest.repository.sqlite;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.ckateptb.minecraft.chest.repository.AbstractRepository;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.nio.file.Path;

public abstract class SQLiteRepository<ENTITY, ID> extends AbstractRepository<ENTITY, ID> {
    @Override
    @SneakyThrows
    public HikariDataSource createDatasource() {
        Path path = this.getPath();
        FileUtils.forceMkdirParent(path.toFile());
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.sqlite.JDBC");
        config.setJdbcUrl("jdbc:sqlite:" + path);
        config.setConnectionTimeout(0);
        return new HikariDataSource(config);
    }

    public abstract Path getPath();
}
