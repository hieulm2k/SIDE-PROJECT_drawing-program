/**
 * Copyright (c) 2021 TMA Solutions, All Rights Reserved.
 */
package com.tma.solutions.common;

/**
 * This class used to define all command in the drawing program
 *
 * @author Hieu Le Minh
 * @version 1.0.0
 * @since 1.0.0
 */
public enum Message {
	// Error message
	NOT_EQUAL_LENGTH_CREATE_CANVAS_COMMAND("Length of Create Canvas command must be 3!"),
	NOT_EQUAL_LENGTH_CREATE_LINE_COMMAND("Length of Create Line command must be 5!"),
	NOT_EQUAL_LENGTH_CREATE_RECTANGLE_COMMAND("Length of Create Rectangle command must be 5!"),
	NOT_EQUAL_LENGTH_BUCKET_FILL_COMMAND("Length of Bucket Fill command must be 4!"),
	WIDTH_AND_HEIGHT_MUST_GREATER_THAN_ZERO("Width and height must be a number and greater than 0!"),
	X_AND_Y_COORDINATE_MUST_GREATER_THAN_ZERO("X and Y coordinate must be a number and greater than 0!"),
	COLOUR_MUST_BE_A_CHARACTER("Colour must be a character!"),
	COMMAND_NOT_FOUND("Command not found!"),
	DIMENSION_UNSUPPORTED("This dimension is unsupported"),
	SHAPE_UNSUPPORTED("This shape is unsupported"),
	INVALID_LINE("Invalid Line, only support vertical and horizontal line!"),
	INVALID_RECTANGLE("Invalid Rectangle!"),
	OUT_OF_RANGE("Out of range!"),

	// Info message
	SEE_YOU_AGAIN("See you again!"),
	ENTER_COMMAND("enter a command: ");

	private final String messageString;

	Message(String message) {
		this.messageString = message;
	}

	/**
	 * Retrieves {@code {@link #messageString}}
	 *
	 * @return value of {@link #messageString}
	 */
	public String getMessageString() {
		return messageString;
	}
}
