package dev.cre8r.iron_spawners.renderer;

import dev.cre8r.iron_spawners.entities.BaseSpawnerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class BaseSpawnerRenderer implements BlockEntityRenderer<BaseSpawnerEntity> {
    private final EntityRenderDispatcher entityRenderDispatcher;


    public BaseSpawnerRenderer(BlockEntityRendererFactory.Context ctx) {
        this.entityRenderDispatcher = ctx.getEntityRenderDispatcher();
    }


    @Override
    public void render(BaseSpawnerEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (entity.getRenderEntity() == null) return;

        Entity e = entity.getRenderEntity();
        double biggestDimension = Math.max(e.getVisibilityBoundingBox().getLengthX(), Math.max(e.getVisibilityBoundingBox().getLengthY(), e.getVisibilityBoundingBox().getLengthZ()));
        float scale = (float)(0.5D/biggestDimension);

        matrices.push();
        matrices.translate(0.5, 0.25, 0.5);
        matrices.scale(scale,scale,scale);
        entityRenderDispatcher.render(e, 0.0, 0.0, 0.0, 0.0F, tickDelta, matrices, vertexConsumers, light);
        matrices.pop();
    }
}
