<p align="center">
<h3 align="center">Chest</h3>

------

<p align="center">
Use SQLite and MySQL inside Spigot in Entity <=> Repository mode
</p>

<p align="center">
<img alt="License" src="https://img.shields.io/github/license/CKATEPTb-minecraft/Chest">
<a href="https://docs.gradle.org/7.5/release-notes.html"><img src="https://img.shields.io/badge/Gradle-7.5-brightgreen.svg?colorB=469C00&logo=gradle"></a>
<a href="https://discord.gg/P7FaqjcATp" target="_blank"><img alt="Discord" src="https://img.shields.io/discord/925686623222505482?label=discord"></a>
</p>

------

# Versioning

We use [Semantic Versioning 2.0.0](https://semver.org/spec/v2.0.0.html) to manage our releases.

# Features

- [X] Easy to use
- [X] MySQL
- [X] SQLite

# Download

Download from our repository or depend via Gradle:

```kotlin
repositories {
    maven("https://repo.animecraft.fun/repository/maven-snapshots/")
}
dependencies {
    implementation("dev.ckateptb.minecraft:Chest:<version>")
}
```

# How To

* Import the dependency [as shown above](#Download)
* Add Chest as a dependency to your `plugin.yml`
```yaml
name: ...
version: ...
main: ...
depend: [ Chest ]
authors: ...
description: ...
```
* Create entity model
```java
@Getter
@Setter
@RequiredArgsConstructor
@DatabaseTable(tableName = "users")
public class User {
    @DatabaseField(id = true, canBeNull = false, unique = true)
    private final UUID uuid;
    @DatabaseField
    private String name;
}
```
* Create repository
```java
@Component
public class UserRepository extends SQLiteRepository<User, UUID> {
    @Override
    public Path getPath() {
        return Path.of("database", "sqlite.db");
    }
}
```
* Use repository for manage
```java
@Component
@RequiredArgsConstructor
public class Test {
    private final UserRepository repository;

    @SneakyThrows
    public void doSomething() {
        User user = this.repository.queryForId(UUID.randomUUID());
        user.setName("Daniel");
        this.repository.update(user);
    }
}
```