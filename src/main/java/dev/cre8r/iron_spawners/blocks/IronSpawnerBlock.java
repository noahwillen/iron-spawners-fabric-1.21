package dev.cre8r.iron_spawners.blocks;

import com.mojang.serialization.MapCodec;
import dev.cre8r.iron_spawners.entities.IronSpawnerEntity;
import dev.cre8r.iron_spawners.init.EntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class IronSpawnerBlock extends BaseSpawnerBlock {

    public IronSpawnerBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new IronSpawnerEntity(pos, state);
    }

    public static final MapCodec<IronSpawnerBlock> CODEC = createCodec(IronSpawnerBlock::new);

    public MapCodec<IronSpawnerBlock> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return createSpawnerTicker(world, type, EntityInit.IRON_SPAWNER_ENTITY);
    }
}
