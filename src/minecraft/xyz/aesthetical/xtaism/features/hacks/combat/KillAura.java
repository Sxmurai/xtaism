package xyz.aesthetical.xtaism.features.hacks.combat;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;
import xyz.aesthetical.xtaism.features.gui.options.XtaismOptions.KeybindOpt;

@Hack(
	name = "KillAura", 
	description = "Attacks anybody in your aura",
	color = 16073282
)
@Keybind(setting = KeybindOpt.KEYBIND_KILLAURA)
@Category(category = Group.COMBAT)
public class KillAura extends Mod {
	// TODO: make these settings
	private double range = 3;
	private double hits = 3;
	private boolean avoidZombiePigmen = true;
	private boolean waitForSwingCooldown = true;
	
	private int counter = 0;
	private long stoppedAt = 0;
	private double oldHits = 0;
	
	@Override
	public void onUpdate() {
		if (mc.player.isSwingInProgress && waitForSwingCooldown && hits == 1) {
			return;
		}
		
		for (Entity entity : mc.world.loadedEntityList) {
			if (entity == mc.player || (entity instanceof EntityPigZombie && avoidZombiePigmen)) {
				continue;
			}
			
			if (mc.player.getDistanceSq(entity) > Math.pow(range, 2) || !entity.canBeAttackedWithItem()) {
				continue;
			}
						
			float[] r = l(mc.player.getPositionEyes(mc.getRenderPartialTicks()), entity.getPositionEyes(mc.getRenderPartialTicks()));
			
			mc.player.rotationYaw = r[0];
			mc.player.rotationPitch = r[1];
						
			try {
				for (int i = 0; i < hits; ++i) {
					mc.player.swingArm(EnumHand.MAIN_HAND);
					mc.playerController.attackEntity(mc.player, entity);	
					counter++;
				}
				
				if (counter >= ((hits + 1) * 10)) {
					if (stoppedAt == 0 && oldHits == 0) {
						oldHits = hits;	
						stoppedAt = System.currentTimeMillis() + 4000;
						hits = 1;
					}
					
					if (System.currentTimeMillis() > stoppedAt) {
						stoppedAt = 0;
						counter = 0;
						hits = oldHits;
						oldHits = 0;
					}
				}
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
		
		float yaw = (float) Math.toDegrees(Math.atan2(dZ, dX)) + 85;
		float pitch = (float) Math.toDegrees(Math.atan2(dY, dXZ));
		
		return new float[] {yaw, pitch};
	}
}