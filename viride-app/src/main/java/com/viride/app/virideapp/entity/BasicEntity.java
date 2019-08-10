package com.viride.app.virideapp.entity;


import lombok.Getter;
import lombok.Setter;
import static lombok.AccessLevel.PROTECTED;
import jdk.nashorn.internal.ir.annotations.Ignore;

public class BasicEntity {
	@Getter(PROTECTED)
	@Setter(PROTECTED)
	@Ignore
	protected String _class;
}
