package ua.maxim.bodypartstrader;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.LinkedHashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = BodyPartsTrader.MOD_ID)
public class GameEvents {
    private static final Map<Item, Integer> PRICES = new LinkedHashMap<Item, Integer>();
    static {
        PRICES.put(ModItems.ZOMBIE_FLESH.get(), 1);
        PRICES.put(ModItems.SKELETON_BONE.get(), 2);
        PRICES.put(ModItems.CREEPER_GLAND.get(), 5);
        PRICES.put(ModItems.SPIDER_FANG.get(), 3);
        PRICES.put(ModItems.ENDER_EYE.get(), 8);
    }

    @SubscribeEvent
    public static void onMobDrops(LivingDropsEvent event) {
        EntityType<?> type = event.getEntityLiving().getType();
        Item part = null;

        if (type == EntityType.ZOMBIE || type == EntityType.HUSK || type == EntityType.DROWNED) {
            part = ModItems.ZOMBIE_FLESH.get();
        } else if (type == EntityType.SKELETON || type == EntityType.STRAY || type == EntityType.WITHER_SKELETON) {
            part = ModItems.SKELETON_BONE.get();
        } else if (type == EntityType.CREEPER) {
            part = ModItems.CREEPER_GLAND.get();
        } else if (type == EntityType.SPIDER || type == EntityType.CAVE_SPIDER) {
            part = ModItems.SPIDER_FANG.get();
        } else if (type == EntityType.ENDERMAN) {
            part = ModItems.ENDER_EYE.get();
        }

        if (part != null && event.getEntityLiving().level.random.nextFloat() < 0.65F) {
            event.getDrops().add(new ItemEntity(event.getEntityLiving().level,
                    event.getEntityLiving().getX(), event.getEntityLiving().getY(),
                    event.getEntityLiving().getZ(), new ItemStack(part)));
        }
    }

    @SubscribeEvent
    public static void onVillagerTrade(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getTarget() instanceof VillagerEntity) || event.getWorld().isClientSide) return;
        if (event.getHand() != Hand.MAIN_HAND) return;

        int emeralds = 0;
        for (int slot = 0; slot < event.getPlayer().inventory.getContainerSize(); slot++) {
            ItemStack stack = event.getPlayer().inventory.getItem(slot);
            Integer price = PRICES.get(stack.getItem());
            if (price != null && !stack.isEmpty()) {
                emeralds += stack.getCount() * price;
                stack.setCount(0);
            }
        }

        if (emeralds > 0) {
            ItemStack payment = new ItemStack(Items.EMERALD, emeralds);
            if (!event.getPlayer().inventory.add(payment)) {
                event.getTarget().spawnAtLocation(payment);
            }
            event.getPlayer().displayClientMessage(
                    new net.minecraft.util.text.StringTextComponent("Торговець заплатив " + emeralds + " смарагдів!"),
                    true);
            event.setCanceled(true);
        }
    }
}
