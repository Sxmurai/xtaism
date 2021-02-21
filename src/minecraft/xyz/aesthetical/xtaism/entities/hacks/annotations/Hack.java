package xyz.aesthetical.xtaism.entities.hacks.annotations;

public @interface Hack {
	String name();
	String description() default "No description provided for hack";
	int color();
}
