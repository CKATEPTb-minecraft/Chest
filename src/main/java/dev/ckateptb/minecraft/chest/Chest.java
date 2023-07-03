package dev.ckateptb.minecraft.chest;

import dev.ckateptb.common.tableclothcontainer.event.ComponentRegisterEvent;
import dev.ckateptb.common.tableclothevent.EventBus;
import dev.ckateptb.minecraft.chest.repository.Repository;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class Chest extends JavaPlugin {
    private final Set<Runnable> executeOnDisable = new HashSet<>();
    public Chest() {
        EventBus.GLOBAL.registerEventHandler(ComponentRegisterEvent.class, event -> {
            Object instance = event.getInstance();
            if (instance instanceof Repository<?, ?> repository) {
                executeOnDisable.add(() -> {
                    try {
                        repository.close();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        });
    }

    @Override
    public void onDisable() {
        executeOnDisable.forEach(Runnable::run);
    }
}