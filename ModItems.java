package ua.maxim.bodypartstrader;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BodyPartsTrader.MOD_ID);

    public static final RegistryObject<Item> ZOMBIE_FLESH = ITEMS.register("zombie_flesh",
            () -> new Item(new Item.Properties().tab(net.minecraft.item.ItemGroup.MATERIALS)));
    public static final RegistryObject<Item> SKELETON_BONE = ITEMS.register("skeleton_bone",
            () -> new Item(new Item.Properties().tab(net.minecraft.item.ItemGroup.MATERIALS)));
    public static final RegistryObject<Item> CREEPER_GLAND = ITEMS.register("creeper_gland",
            () -> new Item(new Item.Properties().tab(net.minecraft.item.ItemGroup.MATERIALS)));
    public static final RegistryObject<Item> SPIDER_FANG = ITEMS.register("spider_fang",
            () -> new Item(new Item.Properties().tab(net.minecraft.item.ItemGroup.MATERIALS)));
    public static final RegistryObject<Item> ENDER_EYE = ITEMS.register("ender_eye_fragment",
            () -> new Item(new Item.Properties().tab(net.minecraft.item.ItemGroup.MATERIALS)));
}
