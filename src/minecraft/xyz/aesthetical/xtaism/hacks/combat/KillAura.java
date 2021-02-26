package xyz.aesthetical.xtaism.hacks.combat;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;

@Hack(
	name = "KillAura", 
	description = "Attacks anybody in your aura",
	color = 16073282
)
@Keybind(key = Keyboard.KEY_NONE)
@Category(category = Group.COMBAT)
public class KillAura extends Mod {
	// TODO: make these settings
	private double range = 2;
	private boolean avoidZombiePigmen = true;
	private boolean waitForSwingCooldown = true;
	
	@Override
	public void onUpdate() {
		if (mc.player.isSwingInProgress) {
			return;
		}
		
		for (Entity entity : mc.world.loadedEntityList) {
			if (entity == mc.player) {
				continue;
			}
			
			if (mc.player.getDistanceSq(entity) > Math.pow(range, 2) || !entity.canBeAttackedWithItem()) {
				continue;
			}
						
			float[] r = l(mc.player.getPositionEyes(mc.getRenderPartialTicks()), entity.getPositionEyes(mc.getRenderPartialTicks()));
			
			mc.player.rotationYaw = r[0];
			mc.player.rotationPitch = r[1];
			
			mc.player.swingArm(EnumHand.MAIN_HAND);
			
			try {
				mc.playerController.attackEntity(mc.player, entity);				
			} catch (Exception e) {
				System.out.println(String.format("Couldn't attack %s [%s]", entity.getName(), entity.getUniqueID()));
				continue;
			}
		}
	}
	
	private float[] l(Vec3d v, Vec3d e) {
		double dX = v.x - e.x;
		double dY = v.y - e.y;
		double dZ = v.z - e.z;
		
		double dXZ = Math.sqrt(dX * dX + dZ * dZ);
		
		float yaw = (float) Math.toDegrees(Math.atan2(dZ, dX)) + 90;
		float pitch = (float) Math.toDegrees(Math.atan2(dY, dXZ));
		
		return new float[] {yaw, pitch};
	}
}