package dev.cre8r.iron_spawners;

import dev.cre8r.iron_spawners.config.ModConfig;
import dev.cre8r.iron_spawners.init.BlockInit;
import dev.cre8r.iron_spawners.init.EntityInit;
import dev.cre8r.iron_spawners.init.ItemGroupInit;
import dev.cre8r.iron_spawners.init.ItemInit;
import dev.cre8r.iron_spawners.util.LootTableModifiers;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IronSpawners implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("iron_spawners");
	public static final String MOD_ID = "iron_spawners";

	@Override
	public void onInitialize() {
		LOGGER.info("Log...");

		MidnightConfig.init(MOD_ID, ModConfig.class);
		LootTableModifiers.modifyLootTables();
		ItemInit.load();
		BlockInit.load();
		ItemGroupInit.load();
		EntityInit.load();
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}