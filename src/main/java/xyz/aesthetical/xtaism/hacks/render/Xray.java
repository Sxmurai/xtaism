package xyz.aesthetical.xtaism.hacks.render;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import xyz.aesthetical.xtaism.hacks.Group;
import xyz.aesthetical.xtaism.hacks.Module;
import xyz.aesthetical.xtaism.utils.XrayUtils;

public class Xray extends Module {
	public static ArrayList<Block> xrayBlocks = new ArrayList(); 
	
	public Xray() {
		super(
			"Xray", 
			Group.RENDER, 
			Keyboard.KEY_X,
			Integer.parseInt("ffc0cb", 16)
		);
		
		XrayUtils.initXRayBlocks();
	}
	
	@Override
	public void onEnable() {
		minecraft.renderGlobal.loadRenderers();
	}
	
	@Override
	public void onDisable() {
		minecraft.renderGlobal.loadRenderers();
	}
}
