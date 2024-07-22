package dev.cre8r.iron_spawners.blocks;

import com.mojang.serialization.MapCodec;
import dev.cre8r.iron_spawners.entities.NetheriteSpawnerEntity;
import dev.cre8r.iron_spawners.init.EntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NetheriteSpawnerBlock extends BaseSpawnerBlock {

    public NetheriteSpawnerBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new NetheriteSpawnerEntity(pos, state);
    }

    public static final MapCodec<NetheriteSpawnerBlock> CODEC = createCodec(NetheriteSpawnerBlock::new);

    public MapCodec<NetheriteSpawnerBlock> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return createSpawnerTicker(world, type, EntityInit.NETHERITE_SPAWNER_ENTITY);
    }
}
