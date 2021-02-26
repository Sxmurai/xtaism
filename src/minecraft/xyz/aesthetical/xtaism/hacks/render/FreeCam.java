package xyz.aesthetical.xtaism.hacks.render;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.util.math.BlockPos;
import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;

@Hack(name = "Freecam", description = "Allows you to move your camera anywhere", color = 14570477)
@Keybind(key = Keyboard.KEY_0)
@Category(category = Group.RENDER)
public class FreeCam extends Mod {
	protected BlockPos oldPos;
	protected EntityOtherPlayerMP fakePlayer; 
	
	@Override
	public void onEnable() {					
		fakePlayer = new EntityOtherPlayerMP(mc.world, mc.player.getGameProfile());
				
		if (mc.inGameHasFocus) {
			oldPos = mc.player.getPosition();
			
			fakePlayer.setEntityId(-2000);
			fakePlayer.copyLocationAndAnglesFrom(mc.player);
			fakePlayer.rotationYawHead = mc.player.rotationYawHead;
			mc.world.addEntityToWorld(fakePlayer.getEntityId(), fakePlayer);
		}
	}
	
	@Override
	public void onUpdate() {		
		mc.player.setVelocity(0, 0, 0);
		mc.player.capabilities.setFlySpeed(2);
		mc.player.noClip = true;
		
		if (mc.gameSettings.keyBindJump.isKeyDown()) {
			mc.player.motionY += 0.3;
		}
		
		if (mc.gameSettings.keyBindSneak.isKeyDown()) {
			mc.player.motionY -= 0.3;
		}
		
		if (mc.player.isInWater()) {
			mc.player.inWater = false;
		}
	}
	
	@Override
	public void onDisable() {
		if (mc.inGameHasFocus) {
			mc.player.setLocationAndAngles(oldPos.getX(), oldPos.getY(), oldPos.getZ(), mc.player.rotationYaw, mc.player.rotationPitch);
			mc.world.removeEntityFromWorld(fakePlayer.getEntityId());
		}
	}
	
	@Override
	public boolean prePacketSent(Packet<?> pk) {
		if (pk.getClass().getTypeName().contains("CPacketPlayer$Position")) {
			return true;
		} else {
			return false;
		}
	}
}
