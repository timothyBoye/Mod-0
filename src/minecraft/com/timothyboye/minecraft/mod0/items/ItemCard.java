package com.timothyboye.minecraft.mod0.items;

import java.util.List;

import com.timothyboye.minecraft.mod0.blocks.BlockInfo;
import com.timothyboye.minecraft.mod0.blocks.Blocks;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCard extends Item {
	
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	
	public ItemCard(int id) {
		super(id);
		setCreativeTab(CreativeTabs.tabMisc);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return ItemInfo.CARD_UNLOCALIZED_NAME + itemStack.getItemDamage();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		icons = new Icon[ItemInfo.CARD_ICONS.length];
		for (int i = 0; i<icons.length; i++) {
			icons[i] = register.registerIcon(ItemInfo.TEXTURE_LOCATION + ":" + ItemInfo.CARD_ICONS[i]);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int dmg) {
		return icons[dmg];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs tab, List list) {
		for (int i = 0; i < ItemInfo.CARD_NAMES.length; i++) {
			ItemStack stack = new ItemStack(id, 1, i);
			list.add(stack);
		}
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && world.getBlockId(x, y, z) == BlockInfo.MACHINE_ID) {
			int meta = world.getBlockMetadata(x, y, z);
			int disabled = meta % 2;
			int type = stack.getItemDamage() + 1;
			int newMeta = type * 2 + disabled;
			world.setBlockMetadataWithNotify(x, y, z, newMeta, 3);
			stack.stackSize--;
			
			return true;
		} else {
			return false;
		}
	}
	
}
