package xyz.aesthetical.xtaism.entities.hacks.annotations;

import xyz.aesthetical.xtaism.entities.hacks.Group;

public @interface Category {
	Group category() default Group.OTHER;
}
