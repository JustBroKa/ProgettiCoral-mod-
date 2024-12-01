package com.metix.primamod.entity;

import com.metix.primamod.PrimaMod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;




public class ModEtity {
    public static final DeferredRegister<?> ENTITY_TYPES =
    DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PrimaMod.MOD_ID);
    }
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
