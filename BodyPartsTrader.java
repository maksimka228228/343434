package ua.maxim.bodypartstrader;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BodyPartsTrader.MOD_ID)
public class BodyPartsTrader {
    public static final String MOD_ID = "bodypartstrader";

    public BodyPartsTrader() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.ITEMS.register(bus);
    }
}
