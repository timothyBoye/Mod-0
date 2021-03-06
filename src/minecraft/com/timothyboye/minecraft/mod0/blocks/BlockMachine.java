package com.timothyboye.minecraft.mod0.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMachine extends Block {
	
	@SideOnly(Side.CLIENT)
	private Icon topIcon;
	@SideOnly(Side.CLIENT)
	private Icon bottomIcon;
	@SideOnly(Side.CLIENT)
	private Icon[] sideIcons = new Icon[BlockInfo.MACHINE_SIDES.length];
	@SideOnly(Side.CLIENT)
	private Icon disabledIcon;
	
	public BlockMachine(int id) {
		super(id, Material.iron);
		setCreativeTab(CreativeTabs.tabRedstone);
		setHardness(2F);
		setStepSound(Block.soundMetalFootstep);
		setUnlocalizedName(BlockInfo.MACHINE_UNLOCALIZED_NAME);
		}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		topIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.MACHINE_TOP);
		bottomIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.MACHINE_BOTTOM);
		disabledIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.MACHINE_DISABLED);
		for (int i = 0; i < sideIcons.length; i++) {
			sideIcons[i] = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.MACHINE_SIDES[i]);
		}
	}
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		if (side == 0) {
			return bottomIcon;
		} else if (side == 1) {
			return isDisabled(meta) ? disabledIcon : topIcon;
		} else {
			int type = meta / 2;
			return sideIcons[type];
		}
	}
	
	private boolean isDisabled (int meta) {
		return meta % 2 == 1;
	}
	
	private void spawnAnvil(World world, int x, int y, int z) {
		if (world.isAirBlock(x, y, z)) {
			world.setBlock(x, y, z, Block.anvil.blockID);
		}
	}
	
	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
		if (!world.isRemote && !isDisabled(world.getBlockMetadata(x, y, z))) {
			spawnAnvil(world, x, y+20, z);
		}
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int id) {
		int meta = world.getBlockMetadata(x, y, z);
		if (!world.isRemote && world.isBlockIndirectlyGettingPowered(x, y, z) && !isDisabled(meta)) {
			switch (meta/2) {
			case 1:
				for (int i = 0; i < 5; i++) {
					spawnAnvil(world, x, y+20+i, z);
				}
				break;
			case 2:
				for (int i = -1; i <= 1; i++) {
					spawnAnvil(world, x+i, y+20, z-2);
					spawnAnvil(world, x+i, y+20, z+2);
					spawnAnvil(world, x-2, y+20, z+i);
					spawnAnvil(world, x+2, y+20, z+i);
				}
				break;
			case 3:
				for (int i = 1; i <= 3; i++) {
					spawnAnvil(world, x+i, y+20, z);
					spawnAnvil(world, x-i, y+20, z);
					spawnAnvil(world, x, y+20, z+i);
					spawnAnvil(world, x, y+20, z-i);
				}
				break;
			}
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			int meta = world.getBlockMetadata(x, y, z);
			int type = meta / 2;
			int disabled = meta % 2 == 0 ? 1 : 0;
			int newMeta = type * 2 + disabled;
			
			world.setBlockMetadataWithNotify(x, y, z, newMeta, 3);
		}
		return true;
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		for (int i = 0; i < BlockInfo.MACHINE_SIDES.length; i++) {
			list.add(new ItemStack(id, 1, i*2));
		}
	}
}
