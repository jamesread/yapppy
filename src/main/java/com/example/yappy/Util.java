package com.example.yappy;

public class Util {
	public static String camelCaseToHypenated(String input) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);

			if (Character.isUpperCase(c) && (i > 0)) {
				sb.append('-');

				c = Character.toLowerCase(c);
			}

			sb.append(c);
		}

		return sb.toString();
	}

	public static String hypenatedClassName(Checker checker) {
		return camelCaseToHypenated(checker.getClass().getSimpleName().toString());
	}
}
