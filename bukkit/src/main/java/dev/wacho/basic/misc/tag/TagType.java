package dev.wacho.basic.misc.tag;

public enum TagType {

	NORMAL, SPECIAL, COUNTRY, PARTNER;
	
	public TagType next() {
		switch (this) {
		case NORMAL:
			return SPECIAL;
		case SPECIAL:
			return COUNTRY;
		case COUNTRY:
			return PARTNER;
		default:
			return NORMAL;
		}
	}
}
