package xyz.aesthetical.xtaism.hacks.combat;

import org.lwjgl.input.Keyboard;

import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShield;
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
	// TODO: broken, fix
	@Override
	public void onUpdate() {
		if (
			mc.player.inventory.offHandInventory.isEmpty() || 
			mc.player.inventory.offHandInventory.get(0).getItem() != Items.TOTEM_OF_UNDYING
		) {			
			if (mc.player.inventory.hasItemStack(new ItemStack(Items.TOTEM_OF_UNDYING))) {				
				int totemIndex = mc.player.inventory.getSlotFor(new ItemStack(Items.TOTEM_OF_UNDYING));
							
				System.out.println(totemIndex);
				
				if (totemIndex != -1) {
					mc.player.inventory.setInventorySlotContents(
						mc.player.inventory.getSizeInventory() - 1,
						mc.player.inventory.removeStackFromSlot(totemIndex)
					);
				}
			}
		}
	}
}
