package dev.cre8r.iron_spawners.entities;

import dev.cre8r.iron_spawners.config.ModConfig;
import dev.cre8r.iron_spawners.init.EntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class IronSpawnerEntity extends BaseSpawnerEntity{

    public IronSpawnerEntity(BlockPos pos, BlockState state) {
        super(EntityInit.IRON_SPAWNER_ENTITY, pos, state);
    }

    @Override
    public int maxDelay() {
        return ModConfig.ironMaxDelay;
    }

    @Override
    public int spawnCount() {
        return ModConfig.ironSpawnCt;
    }
}
