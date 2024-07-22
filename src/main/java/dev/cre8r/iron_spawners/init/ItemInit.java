package dev.cre8r.iron_spawners.init;

import dev.cre8r.iron_spawners.IronSpawners;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ItemInit {

    public static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, IronSpawners.id(name), item);
    }

    public static void load() {}
}
