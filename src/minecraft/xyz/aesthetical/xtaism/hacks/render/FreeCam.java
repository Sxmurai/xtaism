package xyz.aesthetical.xtaism.hacks.render;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.management.PlayerInteractionManager;
import net.minecraft.util.math.Vec3d;
import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;

@Hack(name = "Freecam", description = "Allows you to move your camera anywhere", color = 14570477)
@Keybind(key = Keyboard.KEY_0)
@Category(category = Group.RENDER)
public class FreeCam extends Mod {
	protected Vec3d oldPos;
	protected EntityOtherPlayerMP fakePlayer; 
	
	@Override
	public void onEnable() {					
		fakePlayer = new EntityOtherPlayerMP(mc.world, mc.player.getGameProfile());
				
		if (mc.inGameHasFocus) {
			oldPos = mc.player.getPositionVector();
			
			fakePlayer.setEntityId(-1881);
			fakePlayer.copyLocationAndAnglesFrom(mc.player);
			fakePlayer.rotationYawHead = mc.player.rotationYawHead;
			mc.world.addEntityToWorld(fakePlayer.getEntityId(), fakePlayer);
		}
	}
	
	@Override
	public void onUpdate() {
		mc.player.motionY = 0;
		
		if (mc.gameSettings.keyBindJump.isKeyDown()) {
			mc.player.motionY += 0.2;
		}
		
		if (mc.gameSettings.keyBindSneak.isKeyDown()) {
			mc.player.motionY -= 0.2;
		}
	}
	
	@Override
	public void onDisable() {
		if (mc.inGameHasFocus) {
			mc.player.setLocationAndAngles(oldPos.x, oldPos.y, oldPos.z, mc.player.rotationYaw, mc.player.prevRotationPitch);
			mc.world.removeEntityFromWorld(fakePlayer.getEntityId());
		}
	}
}
