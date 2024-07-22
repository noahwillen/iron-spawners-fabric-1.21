package dev.cre8r.iron_spawners.init;

import dev.cre8r.iron_spawners.IronSpawners;
import dev.cre8r.iron_spawners.blocks.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

public class BlockInit {

    public static final Block IRON_SPAWNER_BLOCK = registerWithItem("iron_spawner", new IronSpawnerBlock(AbstractBlock.Settings.create()
            .nonOpaque()
            .strength(5.0F,1200.0F)
            .sounds(BlockSoundGroup.METAL)
            .requiresTool()));
    public static final Block GOLD_SPAWNER_BLOCK = registerWithItem("gold_spawner", new GoldSpawnerBlock(AbstractBlock.Settings.create()
            .nonOpaque()
            .strength(5.0F,1200.0F)
            .sounds(BlockSoundGroup.METAL)
            .requiresTool()));
    public static final Block EMERALD_SPAWNER_BLOCK = registerWithItem("emerald_spawner", new EmeraldSpawnerBlock(AbstractBlock.Settings.create()
            .nonOpaque()
            .strength(5.0F,1200.0F)
            .sounds(BlockSoundGroup.METAL)
            .requiresTool()));
    public static final Block DIAMOND_SPAWNER_BLOCK = registerWithItem("diamond_spawner", new DiamondSpawnerBlock(AbstractBlock.Settings.create()
            .nonOpaque()
            .strength(5.0F,1200.0F)
            .sounds(BlockSoundGroup.METAL)
            .requiresTool()));
    public static final Block NETHERITE_SPAWNER_BLOCK = registerWithItem("netherite_spawner", new NetheriteSpawnerBlock(AbstractBlock.Settings.create()
            .nonOpaque()
            .strength(5.0F,1200.0F)
            .sounds(BlockSoundGroup.METAL)
            .requiresTool()));



    public static <T extends Block> T register(String name, T block) {
        return Registry.register(Registries.BLOCK, IronSpawners.id(name), block);
    }

    public static <T extends Block> T registerWithItem(String name, T block) {
        T registered = register(name, block);
        ItemInit.register(name, new BlockItem(registered, new Item.Settings()));
        return registered;
    }

    public static <T extends Block> T registerWithItem(String name, T block, Item.Settings settings) {
        T registered = register(name, block);
        ItemInit.register(name, new BlockItem(registered, settings));
        return registered;
    }

    public static void load() {}
}
