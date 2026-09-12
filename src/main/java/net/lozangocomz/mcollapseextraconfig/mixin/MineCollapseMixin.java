package net.lozangocomz.mcollapseextraconfig.mixin;

import net.lozangocomz.mcollapseextraconfig.Config;
import net.minecraft.core.BlockPos;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.zerodind.minecollapse.MineCollapse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MineCollapse.class)
public class MineCollapseMixin {
    //HEIGHT LIMIT

    @Inject(
            method = "onBlockBroken",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onBlockBroken_mixin(final BlockEvent.BreakEvent event, CallbackInfo ci) {

        final BlockPos pos = event.getPos();
        if(Config.COLLAPSE_YMAX_ENABLED.getAsBoolean())
            if (pos.getY() > Config.COLLAPSE_YMAX.getAsInt())
                ci.cancel();
        if(Config.COLLAPSE_YMIN_ENABLED.getAsBoolean())
            if (pos.getY() < Config.COLLAPSE_YMIN.getAsInt())
                ci.cancel();
    }
}