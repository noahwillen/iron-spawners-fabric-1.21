package dev.cre8r.iron_spawners;

import dev.cre8r.iron_spawners.init.BlockInit;
import dev.cre8r.iron_spawners.init.EntityInit;
import dev.cre8r.iron_spawners.renderer.BaseSpawnerRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class IronSpawnersClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.IRON_SPAWNER_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.GOLD_SPAWNER_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.EMERALD_SPAWNER_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.DIAMOND_SPAWNER_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.NETHERITE_SPAWNER_BLOCK, RenderLayer.getCutout());

		BlockEntityRendererFactories.register(EntityInit.IRON_SPAWNER_ENTITY, BaseSpawnerRenderer::new);
		BlockEntityRendererFactories.register(EntityInit.GOLD_SPAWNER_ENTITY, BaseSpawnerRenderer::new);
		BlockEntityRendererFactories.register(EntityInit.EMERALD_SPAWNER_ENTITY, BaseSpawnerRenderer::new);
		BlockEntityRendererFactories.register(EntityInit.DIAMOND_SPAWNER_ENTITY, BaseSpawnerRenderer::new);
		BlockEntityRendererFactories.register(EntityInit.NETHERITE_SPAWNER_ENTITY, BaseSpawnerRenderer::new);
	}
}