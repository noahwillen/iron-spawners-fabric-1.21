package dev.cre8r.iron_spawners.entities;

import dev.cre8r.iron_spawners.config.ModConfig;
import dev.cre8r.iron_spawners.init.EntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class EmeraldSpawnerEntity extends BaseSpawnerEntity{
    public EmeraldSpawnerEntity(BlockPos pos, BlockState state) {
        super(EntityInit.EMERALD_SPAWNER_ENTITY, pos, state);
    }

    @Override
    public int maxDelay() {
        return ModConfig.emeraldMaxDelay;
    }

    @Override
    public int spawnCount() {
        return ModConfig.emeraldSpawnCt;
    }
}
