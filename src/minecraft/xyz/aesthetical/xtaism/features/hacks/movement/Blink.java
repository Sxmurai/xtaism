package xyz.aesthetical.xtaism.features.hacks.movement;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.BlockPos;
import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;
import xyz.aesthetical.xtaism.features.gui.options.XtaismOptions.KeybindOpt;

@Hack(name = "Blink", description = "Makes it look like youre lagging while moving", color = 11261429)
@Keybind(setting = KeybindOpt.KEYBIND_BLINK)
@Category(category = Group.MOVEMENT)
public class Blink extends Mod {
	// TODO: make these settings
	private int limit = 5;

	protected EntityOtherPlayerMP fakePlayer;
	protected BlockPos oldPos;
	protected ArrayList<CPacketPlayer> packets = new ArrayList();
	
	@Override
	public void onEnable() {
		createFakePlayerOrUpdate();
	}
	
	@Override
	public void onDisable() {
		removeFakePlayer();
	}
	
	@Override
	public boolean prePacketSent(Packet<?> pk) {
		if (!(pk instanceof CPacketPlayer)) {
			return false;
		}
		
		packets.add((CPacketPlayer) pk);
		if (packets.size() >= limit) {
			System.out.println("Packets have reached the limit, sending suspended packets");
			
			for (CPacketPlayer packet : packets) {
				mc.player.connection.getNetworkManager().sendPacket(packet);
			}
			
			packets.clear();
			
			createFakePlayerOrUpdate();
		}
		
		return true;
	}
	
	private void createFakePlayerOrUpdate() {
		if (fakePlayer == null) {
			fakePlayer = new EntityOtherPlayerMP(mc.world, mc.player.getGameProfile());
			
			if (mc.inGameHasFocus) {
				oldPos = mc.player.getPosition();
				
				fakePlayer.setEntityId(-2000);
				fakePlayer.copyLocationAndAnglesFrom(mc.player);
				fakePlayer.rotationYawHead = mc.player.rotationYawHead;
				mc.world.addEntityToWorld(fakePlayer.getEntityId(), fakePlayer);
			}	
		} else {
			if (packets.isEmpty()) {
				fakePlayer.setPosition(mc.player.posX, mc.player.posY, mc.player.posZ);				
			}
		}
	}
	
	private void removeFakePlayer() {
		if (mc.inGameHasFocus) {
			mc.player.setLocationAndAngles(oldPos.getX(), oldPos.getY(), oldPos.getZ(), mc.player.rotationYaw, mc.player.rotationPitch);
			mc.world.removeEntityFromWorld(fakePlayer.getEntityId());
			
			fakePlayer = null;
		}
	}
}
