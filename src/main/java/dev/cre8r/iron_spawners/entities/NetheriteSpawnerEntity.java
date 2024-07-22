package dev.cre8r.iron_spawners.entities;

import dev.cre8r.iron_spawners.config.ModConfig;
import dev.cre8r.iron_spawners.init.EntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class NetheriteSpawnerEntity extends BaseSpawnerEntity{
    public NetheriteSpawnerEntity(BlockPos pos, BlockState state) {
        super(EntityInit.NETHERITE_SPAWNER_ENTITY, pos, state);
    }

    @Override
    public int maxDelay() {
        return ModConfig.netheriteMaxDelay;
    }

    @Override
    public int spawnCount() {
        return ModConfig.netheriteSpawnCt;
    }
}
