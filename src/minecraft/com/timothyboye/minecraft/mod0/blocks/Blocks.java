package com.timothyboye.minecraft.mod0.blocks;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {
	
	public static Block machine;
	
	public static void init() {
		machine = new BlockMachine(BlockInfo.MACHINE_ID);
		GameRegistry.registerBlock(machine, BlockInfo.MACHINE_KEY);
	}
	
	public static void addNames() {
		LanguageRegistry.addName(machine, BlockInfo.MACHINE_NAME);
	}
}
