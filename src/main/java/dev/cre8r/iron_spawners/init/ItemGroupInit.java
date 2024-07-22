package dev.cre8r.iron_spawners.init;

import dev.cre8r.iron_spawners.IronSpawners;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

import java.util.Optional;

public class ItemGroupInit {
    public static final Text TITLE = Text.translatable("itemgroup."+IronSpawners.MOD_ID);

    public static final ItemGroup IRON_SPAWNERS_GROUP = register("iron_spawners", FabricItemGroup.builder()
            .displayName(TITLE)
            .icon(BlockInit.IRON_SPAWNER_BLOCK.asItem()::getDefaultStack)
            .entries((displayContext, entries) -> Registries.ITEM.getIds()
                    .stream()
                    .filter(key -> key.getNamespace().equals(IronSpawners.MOD_ID))
                    .map(Registries.ITEM::getOrEmpty)
                    .map(Optional::orElseThrow)
                    .forEach(entries::add))
            .build());

    public static <T extends ItemGroup> T register(String name, T itemGroup) {
        return Registry.register(Registries.ITEM_GROUP, IronSpawners.id(name), itemGroup);
    }

    public static void load() {}
}
