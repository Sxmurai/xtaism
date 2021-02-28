package xyz.aesthetical.xtaism.features.commands;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.Minecraft;
import xyz.aesthetical.xtaism.Xtaism;
import xyz.aesthetical.xtaism.entities.commands.ArgType;
import xyz.aesthetical.xtaism.entities.commands.Command;
import xyz.aesthetical.xtaism.entities.commands.annotations.Arg;
import xyz.aesthetical.xtaism.entities.commands.annotations.Cmd;
import xyz.aesthetical.xtaism.features.gui.options.XtaismOptions.Opt;
import xyz.aesthetical.xtaism.utils.ChatUtil;
import xyz.aesthetical.xtaism.utils.ChatUtil.ChatColor;

@Cmd(
	name = "settings",
	aliases = {"s", "options"},
	usage = "<set | reset> <setting>",
	needsArgs = true
)
public class Settings extends Command {
	@Override
	@Arg(key = "method", index = 0, type = ArgType.CUSTOM)
	@Arg(key = "setting", index = 1, type = ArgType.CUSTOM)
	@Arg(key = "value", index = 2, type = ArgType.CUSTOM)
	public void execute(List<String> args) {
		Xtaism xtaism = Minecraft.getMinecraft().xtaism;
		
		switch (args.get(1).toLowerCase()) {
			case "fps":
			case "frames": {
				xtaism.settings.set(Opt.SHOW_FPS, args.get(0).equalsIgnoreCase("reset") ? true : args.get(2).equalsIgnoreCase("on"));
				
				try {
					xtaism.settings.saveSettings();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
				send(
					xtaism.getCommandManager().xtaismChat + 
					"Now " + (args.get(2).equalsIgnoreCase("on") ? "showing " : "not showing ") + "the FPS counter"
				);
				break;
			}
			
			case "coords":
			case "coordinates": {
				xtaism.settings.set(Opt.SHOW_COORDS, args.get(0).equalsIgnoreCase("reset") ? true : args.get(2).equalsIgnoreCase("on"));
				
				try {
					xtaism.settings.saveSettings();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
				send(
					xtaism.getCommandManager().xtaismChat + 
					"Now " + (args.get(2).equalsIgnoreCase("on") ? "showing " : "not showing ") + "the coordinates"
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
			
			case "setting": {
				List<String> allowedTypes = Arrays.asList(new String[] {"fps", "frames", "coordinates", "coords"});

				if (!allowedTypes.contains(arg.toLowerCase())) {
					return "Invalid Type! Accepted types for argument " + 
							ChatUtil.text(ChatColor.BLUE, true, key) + "are: " + 
							String.join(", ", makeColorfulArray(allowedTypes));
				}
				
				break;
			}
			
			case "value": {				
				switch (args.get(1).toLowerCase()) {
					case "fps":
					case "frames": 
					case "coords":
					case "coordinates": {
						List<String> allowedTypes = Arrays.asList(new String[] {"on", "off"});

						if (!allowedTypes.contains(arg.toLowerCase())) {
							return "Invalid Type! Accepted types for argument " + 
									ChatUtil.text(ChatColor.BLUE, true, key) + "are: " + 
									String.join(", ", makeColorfulArray(allowedTypes));
						}
						break;
					}
				}
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
