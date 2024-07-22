package dev.cre8r.iron_spawners.blocks;

import com.mojang.serialization.MapCodec;
import dev.cre8r.iron_spawners.entities.EmeraldSpawnerEntity;
import dev.cre8r.iron_spawners.init.EntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class EmeraldSpawnerBlock extends BaseSpawnerBlock {

    public EmeraldSpawnerBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new EmeraldSpawnerEntity(pos, state);
    }

    public static final MapCodec<EmeraldSpawnerBlock> CODEC = createCodec(EmeraldSpawnerBlock::new);

    public MapCodec<EmeraldSpawnerBlock> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return createSpawnerTicker(world, type, EntityInit.EMERALD_SPAWNER_ENTITY);
    }
}
