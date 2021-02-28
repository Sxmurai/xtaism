package xyz.aesthetical.xtaism.features.commands;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import xyz.aesthetical.xtaism.entities.commands.Command;
import xyz.aesthetical.xtaism.entities.commands.annotations.Cmd;
import xyz.aesthetical.xtaism.features.gui.notifications.Notification;

@Cmd(
	name = "notification",
	aliases = {"n", "note"},
	description = "Makes a test notification",
	usage = "<text>",
	needsArgs = true
)
public class TestNotification extends Command {
	@Override
	public void execute(List<String> args) {
		String text = String.join(" ", args);
		
		Notification testNoficiation = new Notification();
		testNoficiation.setText("test notification");
		testNoficiation.setVisible(true);
		
		testNoficiation.draw(new ScaledResolution(Minecraft.getMinecraft()));
	}
}
