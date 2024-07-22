package dev.cre8r.iron_spawners.init;

import dev.cre8r.iron_spawners.IronSpawners;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class EnchantmentInit {
    public static final RegistryKey<Enchantment> EXTRACTING = of("extracting");

    private static RegistryKey<Enchantment> of(String name) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, IronSpawners.id(name));
    }
}
