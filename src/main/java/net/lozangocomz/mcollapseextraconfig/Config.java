package net.lozangocomz.mcollapseextraconfig;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue COLLAPSE_YMAX_ENABLED = BUILDER
            .comment("Enables collapseYMax")
            .define("collapseYMax_Enabled", false);
    public static final ModConfigSpec.BooleanValue COLLAPSE_YMIN_ENABLED = BUILDER
            .comment("Enables collapseYMin")
            .define("collapseYMin_Enabled", false);

    public static final ModConfigSpec.IntValue COLLAPSE_YMAX = BUILDER
            .comment("Defines the maximum height that a mine collapse can be triggered. (only works if collapseYMax_Enabled is true)")
            .defineInRange("collapseYMax", 60, Integer.MIN_VALUE, Integer.MAX_VALUE);

    public static final ModConfigSpec.IntValue COLLAPSE_YMIN = BUILDER
            .comment("Defines the minimum height that a mine collapse can be triggered. (only works if collapseYMin_Enabled is true)")
            .defineInRange("collapseYMin", -100, Integer.MIN_VALUE, Integer.MAX_VALUE);

    public static final ModConfigSpec.BooleanValue COLLAPSE_CHANCE_GRADIENT_ENABLED = BUILDER
            .comment("Activates a gradual reduction in the chance of collapse as height increases.")
            .define("collapseChanceGradient_Enabled", false);

    public static final ModConfigSpec.IntValue COLLAPSE_CHANCE_GRADIENT_START_Y = BUILDER
            .comment("Defines the Y-height where the gradual reduction in the chance of collapse begins. Below this height, the chance remains normal. (only works if collapseChanceGradient_Enabled is true)")
            .defineInRange("collapseChanceGradientStartY", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);




    static final ModConfigSpec SPEC = BUILDER.build();
}
