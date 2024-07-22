package dev.cre8r.iron_spawners.entities;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

import static net.minecraft.state.property.Properties.POWERED;

public abstract class BaseSpawnerEntity extends BlockEntity {

    private int spawnDelay;
    private EntityType entityType;

    public BaseSpawnerEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        spawnDelay = 0;
        entityType = EntityType.PIG;
    }

    @Nullable
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        var nbt = new NbtCompound();
        nbt.putString("entity", entityType.getUntranslatedName());
        return nbt;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.putInt("spawn_delay", spawnDelay);
        nbt.putString("entity", entityType.getUntranslatedName());
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        spawnDelay = nbt.getInt("spawn_delay");
        entityType = EntityType.get(Identifier.tryParse(nbt.getString("entity")).toString()).get();
    }



    public static final class Server {

        public Server() {}

        public static void tick(ServerWorld serverWorld, BlockPos pos, BlockState state, BaseSpawnerEntity blockEntity) {
            if (!state.get(POWERED)) return;

            blockEntity.delay();
            if (blockEntity.spawnDelay>=blockEntity.maxDelay()) {
                int i = 0;
                int spawnAttempts = 0;
                while (i<blockEntity.spawnCount() && spawnAttempts < 200) {
                    Random r = new Random();
                    int dx = r.nextInt(-4, 5);
                    int dy = r.nextInt(-4, 3);
                    int dz = r.nextInt(-4, 5);
                    BlockPos spawnPos = new BlockPos(pos.getX()+dx, pos.getY()+dy, pos.getZ()+dz);
                    if (serverWorld.getBlockState(spawnPos).getBlock().equals(Blocks.AIR)) {
                        blockEntity.entityType.spawn(serverWorld, spawnPos, SpawnReason.SPAWNER);
                        i++;
                    }
                    spawnAttempts++;
                }
                blockEntity.resetDelay();
            }
        }

        public static void setType(BlockState state, ServerWorld serverWorld, BlockPos pos, BaseSpawnerEntity baseSpawnerEntity, EntityType type) {
            baseSpawnerEntity.entityType = type;
            markDirty(serverWorld, pos, state);
            serverWorld.updateListeners(pos, state, serverWorld.getBlockState(pos), Block.NOTIFY_LISTENERS);
        }
    }

    private void delay() {
        spawnDelay++;
    }

    private void resetDelay() {
        spawnDelay=0;
    }

    public int maxDelay() {
        return 999999;
    }

    public int spawnCount() {
        return 0;
    }


    public static final class Client {

        public Client() {}

        public static void tick(World world, BlockPos pos, BlockState state, BaseSpawnerEntity blockEntity) {
            if(state.get(POWERED)) {
               Random r = new Random();
               double d0 = pos.getX() + r.nextDouble();
               double d1 = pos.getY() + r.nextDouble();
               double d2 = pos.getZ() + r.nextDouble();
               world.addParticle(ParticleTypes.SMOKE,d0,d1,d2,0.0D,0.0D,0.0D);
               world.addParticle(ParticleTypes.FLAME,d0,d1,d2,0.0D,0.0D,0.0D);
            }
        }
    }

    public Entity getRenderEntity() {
        Entity e = entityType.create(world);
        e.setHeadYaw(0.0F);
        e.setYaw(0.0F);
        e.setBodyYaw(0.0F);
        if (e instanceof MobEntity m)
            m.setAiDisabled(true);
        return e;
    }
}
