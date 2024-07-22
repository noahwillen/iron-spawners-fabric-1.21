package dev.cre8r.iron_spawners.blocks;

import dev.cre8r.iron_spawners.config.ModConfig;
import dev.cre8r.iron_spawners.entities.BaseSpawnerEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import dev.cre8r.iron_spawners.entities.BaseSpawnerEntity.Server;
import dev.cre8r.iron_spawners.entities.BaseSpawnerEntity.Client;

import static net.minecraft.state.property.Properties.POWERED;

public abstract class BaseSpawnerBlock extends BlockWithEntity {

    public BaseSpawnerBlock(Settings settings) {
        super(settings);
        this.setDefaultState(getDefaultState().with(POWERED, false));
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(POWERED, ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos()));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!world.isClient) {
            boolean bl = state.get(POWERED);
            if (bl != world.isReceivingRedstonePower(pos)) {
                if (bl) {
                    world.scheduleBlockTick(pos, this, 4);
                } else {
                    world.setBlockState(pos, state.cycle(POWERED), 2);
                }
            }
        }
    }

    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(POWERED) && !world.isReceivingRedstonePower(pos)) {
            world.setBlockState(pos, state.cycle(POWERED), 2);
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!(world instanceof ServerWorld serverWorld))
            return ItemActionResult.SUCCESS;
        if (stack.isEmpty())
            return ItemActionResult.SUCCESS;
        if (!(stack.getItem() instanceof SpawnEggItem spawnEggItem))
            return ItemActionResult.SUCCESS;
        if (ModConfig.eggBlacklist.contains(stack.getItem().toString()))
            return ItemActionResult.SUCCESS;

        BlockEntity blockEntity = serverWorld.getBlockEntity(pos);
        System.out.println(stack.getItem().toString());
        Server.setType(state, serverWorld, pos, (BaseSpawnerEntity) blockEntity,spawnEggItem.getEntityType(stack));
        world.playSound(null, pos, SoundEvents.BLOCK_DECORATED_POT_INSERT, SoundCategory.BLOCKS, 1.0F, 0.7F + 0.5F);
        return ItemActionResult.SUCCESS;
    }

    @Nullable
    protected static <T extends BlockEntity> BlockEntityTicker<T> createSpawnerTicker(World world, BlockEntityType<T> type, BlockEntityType<? extends BaseSpawnerEntity> _type) {
        if (world instanceof ServerWorld serverWorld) {
            return validateTicker(type, _type, (worldx, pos, statex, blockEntity) -> {
                Server.tick(serverWorld, pos, statex, blockEntity);
            });
        } else {
            return validateTicker(type, _type, (worldx, pos, statex, blockEntity) -> {
                Client.tick(worldx, pos, statex, blockEntity);
            });
        }
    }
}
