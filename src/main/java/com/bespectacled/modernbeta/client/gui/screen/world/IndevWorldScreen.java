package com.bespectacled.modernbeta.client.gui.screen.world;

import java.util.function.Consumer;

import com.bespectacled.modernbeta.api.client.gui.wrapper.CyclingOptionWrapper;
import com.bespectacled.modernbeta.api.client.gui.wrapper.DoubleOptionWrapper;
import com.bespectacled.modernbeta.client.gui.Settings;
import com.bespectacled.modernbeta.client.gui.WorldSettings.WorldSetting;
import com.bespectacled.modernbeta.client.gui.screen.WorldScreen;
import com.bespectacled.modernbeta.util.NbtTags;
import com.bespectacled.modernbeta.util.NbtUtil;
import com.bespectacled.modernbeta.world.gen.provider.indev.IndevTheme;
import com.bespectacled.modernbeta.world.gen.provider.indev.IndevType;

import net.minecraft.nbt.NbtFloat;
import net.minecraft.nbt.NbtInt;
import net.minecraft.nbt.NbtString;
import net.minecraft.text.TranslatableText;

public class IndevWorldScreen extends InfWorldScreen {
    private static final String LEVEL_THEME_DISPLAY_STRING = "createWorld.customize.indev.levelTheme";
    private static final String LEVEL_TYPE_DISPLAY_STRING = "createWorld.customize.indev.levelType";
    private static final String LEVEL_WIDTH_DISPLAY_STRING = "createWorld.customize.indev.levelWidth";
    private static final String LEVEL_LENGTH_DISPLAY_STRING = "createWorld.customize.indev.levelLength";
    private static final String LEVEL_HEIGHT_DISPLAY_STRING = "createWorld.customize.indev.levelHeight";
    private static final String CAVE_RADIUS_DISPLAY_STRING = "createWorld.customize.indev.caveRadius";
    
    private static final String LEVEL_HEIGHT_TOOLTIP = "createWorld.customize.indev.levelHeight.tooltip";
    private static final String CAVE_RADIUS_TOOLTIP = "createWorld.customize.indev.caveRadius.tooltip";

    protected IndevWorldScreen(WorldScreen parent, WorldSetting worldSetting, Consumer<Settings> consumer, Settings settings) {
        super(parent, worldSetting, consumer, settings);
        
        // Set default single biome per level theme
        /*
        String levelTheme = NbtUtil.toStringOrThrow(this.getSetting(NbtTags.LEVEL_THEME));
        this.setDefaultSingleBiome(IndevTheme.fromName(levelTheme).getDefaultBiome().toString());   
        */
    }

    public static IndevWorldScreen create(WorldScreen worldScreen, WorldSetting worldSetting) {
        return new IndevWorldScreen(
            worldScreen,
            worldSetting,
            settings -> worldScreen.getWorldSettings().putCompound(worldSetting, settings.getNbt()),
            new Settings(worldScreen.getWorldSettings().getNbt(worldSetting))
        );
    }
    
    @Override
    protected void init() {
        super.init();
        
        String theme = NbtUtil.toStringOrThrow(this.getSetting("levelTheme"));
        String type = NbtUtil.toStringOrThrow(this.getSetting("levelType"));
        
        CyclingOptionWrapper<IndevTheme> levelTheme = new CyclingOptionWrapper<>(
            LEVEL_THEME_DISPLAY_STRING,
            IndevTheme.values(),
            () -> IndevTheme.fromName(NbtUtil.toStringOrThrow(this.getSetting(NbtTags.LEVEL_THEME))),
            value -> this.putSetting(NbtTags.LEVEL_THEME, NbtString.of(value.getName())),
            value -> value.getColor(),
            value -> this.client.textRenderer.wrapLines(new TranslatableText(value.getDescription()).formatted(value.getColor()), 250),
            IndevTheme.fromName(theme)
        );
        
        CyclingOptionWrapper<IndevType> levelType = new CyclingOptionWrapper<>(
            LEVEL_TYPE_DISPLAY_STRING, 
            IndevType.values(), 
            () -> IndevType.fromName(NbtUtil.toStringOrThrow(this.getSetting(NbtTags.LEVEL_TYPE))), 
            value -> this.putSetting(NbtTags.LEVEL_TYPE, NbtString.of(value.getName())),
            IndevType.fromName(type)
        );
        
        DoubleOptionWrapper<Integer> levelWidth = new DoubleOptionWrapper<>(
            LEVEL_WIDTH_DISPLAY_STRING,
            "blocks",
            128D, 1024D, 128f,
            () -> NbtUtil.toIntOrThrow(this.getSetting(NbtTags.LEVEL_WIDTH)),
            value -> this.putSetting(NbtTags.LEVEL_WIDTH, NbtInt.of(value.intValue()))
        );
        
        DoubleOptionWrapper<Integer> levelLength = new DoubleOptionWrapper<>(
            LEVEL_LENGTH_DISPLAY_STRING,
            "blocks",
            128D, 1024D, 128f,
            () -> NbtUtil.toIntOrThrow(this.getSetting(NbtTags.LEVEL_LENGTH)),
            value -> this.putSetting(NbtTags.LEVEL_LENGTH, NbtInt.of(value.intValue()))
        );
        
        DoubleOptionWrapper<Integer> levelHeight = new DoubleOptionWrapper<>(
            LEVEL_HEIGHT_DISPLAY_STRING, 
            "blocks",
            64D, 256D, 64F,
            () -> NbtUtil.toIntOrThrow(this.getSetting(NbtTags.LEVEL_HEIGHT)),
            value -> this.putSetting(NbtTags.LEVEL_HEIGHT, NbtInt.of(value.intValue())),
            this.client.textRenderer.wrapLines(new TranslatableText(LEVEL_HEIGHT_TOOLTIP), 200)
        );
        
        DoubleOptionWrapper<Float> caveRadius = new DoubleOptionWrapper<>(
            CAVE_RADIUS_DISPLAY_STRING,
            "",
            1D, 3D, 0.1f,
            () -> NbtUtil.toFloatOrThrow(this.getSetting(NbtTags.LEVEL_CAVE_RADIUS)),
            value -> this.putSetting(NbtTags.LEVEL_CAVE_RADIUS, NbtFloat.of(value.floatValue())),
            this.client.textRenderer.wrapLines(new TranslatableText(CAVE_RADIUS_TOOLTIP), 200)
        );
        
        this.addOption(levelTheme);
        this.addOption(levelType);
        this.addOption(levelWidth);
        this.addOption(levelLength);
        this.addOption(levelHeight);
        this.addOption(caveRadius);
    }
}
