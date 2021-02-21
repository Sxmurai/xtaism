package xyz.aesthetical.xtaism.entities.hacks.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import xyz.aesthetical.xtaism.entities.hacks.Group;

@Retention(RetentionPolicy.RUNTIME)
public @interface Category {
	Group category() default Group.OTHER;
}
