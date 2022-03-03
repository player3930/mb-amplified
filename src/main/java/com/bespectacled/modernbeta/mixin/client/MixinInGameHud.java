package com.bespectacled.modernbeta.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.bespectacled.modernbeta.ModernBeta;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public class MixinInGameHud {
    @Shadow private MinecraftClient client;
    
    @Unique private static final String VERSION;
    
    @Inject(method = "render", at = @At("TAIL"))
    private void injectDebugVersion(MatrixStack matrices, float tickDelta, CallbackInfo info) {
        if (ModernBeta.RENDER_CONFIG.configOther.renderGameVersion && !this.client.options.debugEnabled) {
            this.renderDebugVersion(matrices);
        }
    }
    
    @Unique
    private void renderDebugVersion(MatrixStack matrices) {
        this.client.textRenderer.drawWithShadow(matrices, VERSION, 2f, 2f, 0xFFFFFF);
    }
    
    static {
        VERSION = (Math.random() > 0.0001 ? "Minecraft " : "Minceraft ") + SharedConstants.getGameVersion().getName();
    }
}
