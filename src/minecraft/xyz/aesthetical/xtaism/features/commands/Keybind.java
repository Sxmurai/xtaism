package xyz.aesthetical.xtaism.features.commands;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import xyz.aesthetical.xtaism.Xtaism;
import xyz.aesthetical.xtaism.entities.commands.ArgType;
import xyz.aesthetical.xtaism.entities.commands.Command;
import xyz.aesthetical.xtaism.entities.commands.annotations.Arg;
import xyz.aesthetical.xtaism.entities.commands.annotations.Cmd;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.utils.ChatUtil;
import xyz.aesthetical.xtaism.utils.ChatUtil.ChatColor;

@Cmd(
	name = "keybind",
	aliases = {"key", "k"},
	description = "Sets the keybind for a hack",
	usage = "<set | reset> <hackname> <key name>",
	needsArgs = true
)
public class Keybind extends Command {
	@Override
	@Arg(key = "method", index = 0, type = ArgType.CUSTOM)
	@Arg(key = "hackname", index = 1, type = ArgType.CUSTOM)
	@Arg(key = "key", index = 2, type = ArgType.CUSTOM)
	public void execute(List<String> args) {
		Xtaism xtaism = Minecraft.getMinecraft().xtaism;
		
		switch (args.get(0).toLowerCase()) {
			case "set": {
				Mod hack = xtaism.getHackManager().getHack(args.get(1).toLowerCase());		
				
				hack.getKeybindSetting().setKey(Keyboard.getKeyIndex(args.get(2).toUpperCase()));
				
				try {
					xtaism.settings.saveSettings();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
				send(
					xtaism.getCommandManager().xtaismChat +
					"Set the keybind for " + 
					ChatUtil.text(ChatColor.DARK_GRAY, true, hack.getName()) + 
					"to " + ChatUtil.text(ChatColor.BLUE, false, args.get(2).toUpperCase())
				);
				
				break;
			}
			
			case "reset": {
				Mod hack = xtaism.getHackManager().getHack(args.get(1).toLowerCase());		
				
				hack.getKeybindSetting().setKey(-1);
				
				try {
					xtaism.settings.saveSettings();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
				send(
					xtaism.getCommandManager().xtaismChat +
					"Reset the keybind for " + ChatUtil.text(ChatColor.DARK_GRAY, true, hack.getName())
				);
				break;
			}
		}
	}
	
	@Override
	public String parseCustom(String arg, String key, List<String> args) {
		switch (key.toLowerCase()) {
			case "method": {
				List<String> allowedTypes = Arrays.asList(new String[] {"set", "reset"});
				if (!allowedTypes.contains(arg.toLowerCase())) {
					return "Invalid Type! Accepted types for argument " + 
							ChatUtil.text(ChatColor.BLUE, true, key) + "are: " + 
							String.join(", ", makeColorfulArray(allowedTypes));
				}
				
				break;
			}
			
			case "hackname": {
				Xtaism xtaism = Minecraft.getMinecraft().xtaism;
				Mod hack = xtaism.getHackManager().getHack(arg.toLowerCase());
				
				if (hack == null) {
					return "I couldn't find anything for that. Check your spelling?";
				}
				
				break;
			}
			
			case "key": {
				if (args.get(0).equalsIgnoreCase("reset")) {
					return "";
				}
				
				int k = Keyboard.getKeyIndex(arg.toUpperCase());
				
				if (k == 0) {
					return "Couldn't get key provided.";
				}
								
				break;
			}
		}
		
		return "";
	}
	
	private ArrayList<String> makeColorfulArray(List<String> arr) {
		ArrayList<String> r = new ArrayList();
				
		for (String item : arr) {
			r.add(ChatUtil.text(ChatColor.GRAY, false, item));
		}
		
		return r;
	}
}
