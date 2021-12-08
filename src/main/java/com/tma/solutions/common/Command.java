/**
 * Copyright (c) 2021 TMA Solutions, All Rights Reserved.
 */
package com.tma.solutions.common;

import java.util.Objects;

/**
 * This class used to define all commands in the program
 *
 * @author Hieu Le Minh
 * @version 1.0.0
 * @since 1.0.0
 */
public enum Command {
	CREATE_CANVAS("c"),
	CREATE_LINE("l"),
	CREATE_RECTANGLE("r"),
	BUCKET_FILL("b"),
	QUIT("q"),
	NONE("");

	private final String name;

	/**
	 * This method used to get the command by nam
	 *
	 * @param name the name of command want to get
	 * @return Command is the command want to get
	 */
	public static Command getCommandByName(String name) {
		for (Command command : Command.values()) {
			if (Objects.equals(command.name, name)) {
				return command;
			}
		}
		// If not found anything -> return none value
		return NONE;
	}

	Command(String name) {
		this.name = name;
	}

	/**
	 * Retrieves {@code {@link #name}}
	 *
	 * @return value of {@link #name}
	 */
	public String getName() {
		return name;
	}
}
