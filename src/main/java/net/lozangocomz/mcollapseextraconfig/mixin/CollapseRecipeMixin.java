package net.lozangocomz.mcollapseextraconfig.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.lozangocomz.mcollapseextraconfig.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.zerodind.minecollapse.recipes.CollapseRecipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CollapseRecipe.class)
public class CollapseRecipeMixin {
    private static final Logger LOGGER =
            LoggerFactory.getLogger("mcollapseextraconfig");

    //GRADIENT CALC

    @ModifyExpressionValue(
            method = "tryTriggerCollapse",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/neoforged/neoforge/common/ModConfigSpec$DoubleValue;get()Ljava/lang/Object;",
                    ordinal = 0
            )
    )
    private static Object modifyRealCollapseChance(
            Object original,
            Level level,
            BlockPos pos
    ) {
        return modifyChance(pos, ((Number) original).doubleValue());
    }

    @ModifyExpressionValue(
            method = "tryTriggerCollapse",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/neoforged/neoforge/common/ModConfigSpec$DoubleValue;get()Ljava/lang/Object;",
                    ordinal = 1
            )
    )
    private static Object modifyFakeCollapseChance(
            Object original,
            Level level,
            BlockPos pos
    ) {
        return modifyChance(pos, ((Number) original).doubleValue());
    }

    private static double modifyChance(
            BlockPos pos,
            double originalChance
    ) {
        if (!Config.COLLAPSE_CHANCE_GRADIENT_ENABLED.getAsBoolean()) {
            return originalChance;
        }

        int y = pos.getY();

        int peak = Config.COLLAPSE_CHANCE_GRADIENT_START_Y.getAsInt();
        int ymax = Config.COLLAPSE_YMAX.getAsInt();

        if (ymax <= peak) {
            LOGGER.warn(
                    "Invalid collapse chance gradient: ymax={} <= peak={}",
                    ymax,
                    peak
            );

            return originalChance;
        }

        if (y <= peak) {
            return originalChance;
        }

        if (y >= ymax) {
            return 0.0;
        }

        double gradient =
                (double) (ymax - y)
                        / (ymax - peak);

        double result = originalChance * gradient;

        /*LOGGER.debug(
                "Collapse chance: y={}, original={}, gradient={}, result={}",
                y,
                originalChance,
                gradient,
                result
        );*/

        return result;
    }
}