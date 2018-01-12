package com.example.examplemod;

import com.example.examplemod.core.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = RangeOverlayMod.MODID, version = RangeOverlayMod.VERSION)
public class RangeOverlayMod {
    public static final String MODID = "range_overlay";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "com.example.examplemod.core.ClientProxy", serverSide = "com.example.examplemod.core.CommonProxy")
    protected static CommonProxy proxy;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }
}

