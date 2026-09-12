package net.lozangocomz.mcollapseextraconfig;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(MineCollapseExtraConfig.MODID)
public class MineCollapseExtraConfig {
    public static final String MODID = "mcollapseextraconfig";

    public MineCollapseExtraConfig(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.SERVER, Config.SPEC);
    }

}
