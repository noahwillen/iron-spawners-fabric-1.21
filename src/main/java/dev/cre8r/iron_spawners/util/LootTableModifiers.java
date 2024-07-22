package dev.cre8r.iron_spawners.util;

import dev.cre8r.iron_spawners.init.EnchantmentInit;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.entity.EntityType;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceWithEnchantedBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.Optional;


public class LootTableModifiers {

    public static void modifyLootTables() {

        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (key.getValue().getPath().startsWith("entities") && !blacklisted(key)) {
                Optional<RegistryEntry<Enchantment>> extracting = registries.getOptionalWrapper(RegistryKeys.ENCHANTMENT).flatMap(registry -> registry.getOptional(EnchantmentInit.EXTRACTING));

                extracting.ifPresent((extractingEnchantment) -> {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .with(ItemEntry.builder(SpawnEggItem.forEntity(EntityType.get(String.valueOf(Identifier.tryParse(key.getValue().toString().split("/")[1]))).get())))
                            .conditionally(KilledByPlayerLootCondition.builder())
                            .conditionally(() -> {
                                RegistryWrapper.Impl<Enchantment> impl = registries.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
                                return new RandomChanceWithEnchantedBonusLootCondition(0.0F, new EnchantmentLevelBasedValue.Linear(0.035F,0.01F), impl.getOrThrow(EnchantmentInit.EXTRACTING));
                            });

                    tableBuilder.pool(poolBuilder.build());
                });
            }
        });
    }

    public static boolean blacklisted(RegistryKey<LootTable> key) {
        return key == EntityType.ILLUSIONER.getLootTableId()
                || key == EntityType.PLAYER.getLootTableId()
                || key == EntityType.ARMOR_STAND.getLootTableId()
                || key == EntityType.GIANT.getLootTableId()
                || key == EntityType.ENDER_DRAGON.getLootTableId()
                || key == EntityType.ELDER_GUARDIAN.getLootTableId()
                || key == EntityType.IRON_GOLEM.getLootTableId()
                || key == EntityType.WITHER.getLootTableId()
                || key == EntityType.WARDEN.getLootTableId();
    }
}
