package xyz.aesthetical.xtaism.hacks.combat;

import org.lwjgl.input.Keyboard;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;

@Hack(
	name = "Auto Totem", 
	description = "Auto equips totemss",
	color = 16073282
)
@Keybind(key = Keyboard.KEY_NONE)
@Category(category = Group.COMBAT)
public class AutoTotem extends Mod {
	private ItemStack totem = new ItemStack(Item.getItemById(449));
	
	@Override
	public void onEnable() {
		if (mc.player.getHeldItemOffhand() != totem) {
			addTotemToOffhand();
		}
	}
	
	@Override
	public void onUpdate() {
		if (mc.player.getHeldItemOffhand() != totem) {
			addTotemToOffhand();
		}
	}
	
	private void addTotemToOffhand() {
		if (mc.player.inventory.hasItemStack(totem)) {
			
		}
	}
}
