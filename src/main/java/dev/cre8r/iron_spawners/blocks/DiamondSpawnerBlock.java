package dev.cre8r.iron_spawners.blocks;

import com.mojang.serialization.MapCodec;
import dev.cre8r.iron_spawners.entities.DiamondSpawnerEntity;
import dev.cre8r.iron_spawners.init.EntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DiamondSpawnerBlock extends BaseSpawnerBlock {

    public DiamondSpawnerBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DiamondSpawnerEntity(pos, state);
    }

    public static final MapCodec<DiamondSpawnerBlock> CODEC = createCodec(DiamondSpawnerBlock::new);

    public MapCodec<DiamondSpawnerBlock> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return createSpawnerTicker(world, type, EntityInit.DIAMOND_SPAWNER_ENTITY);
    }
}
