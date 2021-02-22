package xyz.aesthetical.xtaism.helpers;

import java.util.ArrayList;

import net.minecraft.block.Block;

public class XrayHelper {
	protected static ArrayList<Block> xrayBlocks = new ArrayList();
	protected static boolean enabled = false;
	
	public static void init() {
		// Liquids
		addBlock(Block.REGISTRY.getObjectById(10)); // Flowing Lava
		addBlock(Block.REGISTRY.getObjectById(11)); // Lava
		
		// Ores/Ore Blocks
		addBlock(Block.REGISTRY.getObjectById(14));  // Gold Ore
		addBlock(Block.REGISTRY.getObjectById(41));  // Gold Block
		addBlock(Block.REGISTRY.getObjectById(15));  // Iron Ore
		addBlock(Block.REGISTRY.getObjectById(42));  // Iron Block
		addBlock(Block.REGISTRY.getObjectById(16));  // Coal Ore
		addBlock(Block.REGISTRY.getObjectById(173)); // Coal Block
		addBlock(Block.REGISTRY.getObjectById(129)); // Emerald Ore
		addBlock(Block.REGISTRY.getObjectById(133)); // Emerald Block
		addBlock(Block.REGISTRY.getObjectById(73));  // Redstone Ore
		addBlock(Block.REGISTRY.getObjectById(152)); // Redstone Block
		addBlock(Block.REGISTRY.getObjectById(56));  // Diamond Ore
		addBlock(Block.REGISTRY.getObjectById(57));  // Diamond Block		
	}
	
	public static void addBlock(Block block) {
		xrayBlocks.add(block);
	}
	
	public static boolean shouldRenderBlock(Block block) {
		return xrayBlocks.contains(block);
	}
	
	public static boolean shouldRenderBlock(int blockId) {
		return xrayBlocks.contains(Block.REGISTRY.getObjectById(blockId));
	}
	
	public static void toggle() {
		XrayHelper.enabled = !XrayHelper.enabled;
	}
	
	public static boolean isToggled() {
		return XrayHelper.enabled;
	}
}
