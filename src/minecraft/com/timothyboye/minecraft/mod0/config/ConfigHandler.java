package com.timothyboye.minecraft.mod0.config;

import java.io.File;

import com.timothyboye.minecraft.mod0.blocks.BlockInfo;
import com.timothyboye.minecraft.mod0.items.ItemInfo;

import net.minecraftforge.common.Configuration;

public class ConfigHandler {

public static void init(File file) {
	Configuration config = new Configuration(file);
	
	config.load();
	
	ItemInfo.WAND_ID = config.get(ItemInfo.CAT_ITEMS, ItemInfo.WAND_KEY, ItemInfo.WAND_DEFAULT).getInt() - 256;
	ItemInfo.CARD_ID = config.get(ItemInfo.CAT_ITEMS, ItemInfo.CARD_KEY, ItemInfo.CARD_DEFAULT).getInt() - 256;
	ItemInfo.MUFFIN_ID = config.get(ItemInfo.CAT_ITEMS, ItemInfo.MUFFIN_KEY, ItemInfo.MUFFIN_DEFAULT).getInt() - 256;
	
	BlockInfo.MACHINE_ID = config.get(BlockInfo.CAT_BLOCKS, BlockInfo.MACHINE_KEY, BlockInfo.MACHINE_DEFAULT).getInt();
	
	config.save();
}
	
}
