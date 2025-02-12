package com.gildedgames.aether.client.renderer.entity;

import com.gildedgames.aether.common.entity.block.TNTPresentEntity;
import com.gildedgames.aether.common.registry.AetherBlocks;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.TNTMinecartRenderer;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TNTPresentRenderer extends EntityRenderer<TNTPresentEntity>
{
    public TNTPresentRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
        this.shadowRadius = 0.5F;
    }

    @Override
    public void render(TNTPresentEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        matrixStackIn.translate(0.0D, 0.5D, 0.0D);
        if ((float)entityIn.getFuse() - partialTicks + 1.0F < 10.0F) {
            float f = 1.0F - ((float)entityIn.getFuse() - partialTicks + 1.0F) / 10.0F;
            f = MathHelper.clamp(f, 0.0F, 1.0F);
            f = f * f;
            f = f * f;
            float f1 = 1.0F + f * 0.3F;
            matrixStackIn.scale(f1, f1, f1);
        }

        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
        matrixStackIn.translate(-0.5D, -0.5D, 0.5D);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90.0F));
        TNTMinecartRenderer.renderWhiteSolidBlock(AetherBlocks.PRESENT.get().defaultBlockState(), matrixStackIn, bufferIn, packedLightIn, entityIn.getFuse() / 5 % 2 == 0);
        matrixStackIn.popPose();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(TNTPresentEntity entity) {
        return AtlasTexture.LOCATION_BLOCKS;
    }
}
