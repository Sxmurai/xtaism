package xyz.aesthetical.xtaism.utils;

public class ChatUtil {
	public static String text(ChatColor color, boolean addSpace, String text) {
		return color.getValue() + text + ChatColor.RESET.getValue() + (addSpace ? " " : "");
	}
	
	public enum ChatColor {		
		BLACK("§0"),
		DARK_BLUE("§1"),
		DARK_GREEN("§2"),
		DARK_AQUA("§3"),
		DARK_RED("§4"),
		DARK_PURPLE("§5"),
		GOLD("§6"),
		GRAY("§7"),
		DARK_GRAY("§8"),
		BLUE("§9"),
		GREEN("§a"),
		AQUA("§b"),
		RED("§c"),
		LIGHT_PURPLE("§d"),
		YELLOW("§e"),
		WHITE("§f"),
		MINECOIN_GOLD("§g"),
		
		RESET("§r");

		private String code;
		
		private ChatColor(String color) {
			this.code = color;
		}
		
		public String getValue() {
			return code;
		}
	}
}
