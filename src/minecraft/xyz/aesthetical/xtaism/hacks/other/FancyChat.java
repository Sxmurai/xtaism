package xyz.aesthetical.xtaism.hacks.other;

import org.lwjgl.input.Keyboard;

import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;

@Hack(
		name = "FancyChat", 
		description = "Makes your messages in chat *fancy*",
		color = 11261429
)
@Keybind(key = Keyboard.KEY_NONE)
@Category(category = Group.OTHER)
public class FancyChat extends Mod {
	@Override
	public boolean prePacketSent(Packet<?> pk) {
		if (!(pk instanceof CPacketChatMessage)) {
			return false;
		}
		
		String message = ((CPacketChatMessage) pk).getMessage();
		
		if (message.startsWith("/")) {
			return false;
		}
		
		String newMessage = "";
		
		for (char c : message.toCharArray()) {
			newMessage += convert(c);
		}
		
		mc.player.connection.getNetworkManager().sendPacket(new CPacketChatMessage(newMessage));
		
		return true;
	}
	
	private String convert(char c) {
		String bl = "(){}[]|";
		
		if (c < 0x21 || c > 0x80) {
			return "" + c;
		}
		
		if (bl.contains(Character.toString(c))) {
			return "" + c;
		}
		
		return new String(Character.toChars(c + 0xfee0));
	}
}