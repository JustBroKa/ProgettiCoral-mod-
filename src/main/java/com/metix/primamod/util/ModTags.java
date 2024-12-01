package com.metix.primamod.util;

import com.metix.primamod.block.ModBlocks;
import com.metix.primamod.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

public class ModTags {
    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(PrimaMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(PrimaMod.MOD_ID, name));
        }
    }

    // The value here should match an entry in the META-INF/mods.toml file
    @Mod(PrimaMod.MOD_ID)
    public static class PrimaMod {
        // Define mod id in a common place for everything to reference
        public static final String MOD_ID = "primamod";
        // Directly reference a slf4j logger
        public static final Logger LOGGER = LogUtils.getLogger();

        public PrimaMod() {
            IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
            modEventBus.addListener(this::commonSetup);
            // Register ourselves for server and other game events we are interested in
            MinecraftForge.EVENT_BUS.register(this);


            ModItems.register(modEventBus);
            ModBlocks.register(modEventBus);

            // Register the item to a creative tab
            modEventBus.addListener(this::addCreative);
            // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        }

        private void commonSetup(final FMLCommonSetupEvent event)  {

        }

        // Add the example block item to the building blocks tab
        private void addCreative(BuildCreativeModeTabContentsEvent event) {
            if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
                event.accept(ModItems.PRIMOITEM);
            }

            if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
                event.accept(ModBlocks.PRIMOBLOCCO);
            }
        }

        // You can use SubscribeEvent and let the Event Bus discover methods to call
        @SubscribeEvent
        public void onServerStarting(ServerStartingEvent event) {

        }

        // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
        @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
        public static class ClientModEvents {
            @SubscribeEvent
            public static void onClientSetup(FMLClientSetupEvent event) {

            }
        }
    }
}
