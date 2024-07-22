package dev.cre8r.iron_spawners.init;

import dev.cre8r.iron_spawners.IronSpawners;
import dev.cre8r.iron_spawners.entities.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;


public class EntityInit {

    public static final BlockEntityType<IronSpawnerEntity> IRON_SPAWNER_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            IronSpawners.id("iron_spawner_entity"),
            BlockEntityType.Builder.create(IronSpawnerEntity::new,BlockInit.IRON_SPAWNER_BLOCK).build());
    public static final BlockEntityType<GoldSpawnerEntity> GOLD_SPAWNER_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            IronSpawners.id("gold_spawner_entity"),
            BlockEntityType.Builder.create(GoldSpawnerEntity::new,BlockInit.GOLD_SPAWNER_BLOCK).build());
    public static final BlockEntityType<EmeraldSpawnerEntity> EMERALD_SPAWNER_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            IronSpawners.id("emerald_spawner_entity"),
            BlockEntityType.Builder.create(EmeraldSpawnerEntity::new,BlockInit.EMERALD_SPAWNER_BLOCK).build());
    public static final BlockEntityType<DiamondSpawnerEntity> DIAMOND_SPAWNER_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            IronSpawners.id("diamond_spawner_entity"),
            BlockEntityType.Builder.create(DiamondSpawnerEntity::new,BlockInit.DIAMOND_SPAWNER_BLOCK).build());
    public static final BlockEntityType<NetheriteSpawnerEntity> NETHERITE_SPAWNER_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            IronSpawners.id("netherite_spawner_entity"),
            BlockEntityType.Builder.create(NetheriteSpawnerEntity::new,BlockInit.NETHERITE_SPAWNER_BLOCK).build());

    public static void load() {}
}
