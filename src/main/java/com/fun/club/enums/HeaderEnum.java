package com.fun.club.enums;

public enum HeaderEnum {

	TOTAL_PAGES("TotalPages"), TOTAL_ELEMENTS("TotalElements");

	private final String value;

	HeaderEnum(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
