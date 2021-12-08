/**
 * Copyright (c) 2021 TMA Solutions, All Rights Reserved.
 */
package com.tma.solutions;

import static com.tma.solutions.common.Constant.ONE_OR_MORE_SPACES;
import static com.tma.solutions.common.Constant.SPACE;
import static com.tma.solutions.common.Message.COLOUR_MUST_BE_A_CHARACTER;
import static com.tma.solutions.common.Message.COMMAND_NOT_FOUND;
import static com.tma.solutions.common.Message.ENTER_COMMAND;
import static com.tma.solutions.common.Message.NOT_EQUAL_LENGTH_BUCKET_FILL_COMMAND;
import static com.tma.solutions.common.Message.NOT_EQUAL_LENGTH_CREATE_CANVAS_COMMAND;
import static com.tma.solutions.common.Message.NOT_EQUAL_LENGTH_CREATE_LINE_COMMAND;
import static com.tma.solutions.common.Message.NOT_EQUAL_LENGTH_CREATE_RECTANGLE_COMMAND;
import static com.tma.solutions.common.Message.SEE_YOU_AGAIN;
import static com.tma.solutions.common.Message.WIDTH_AND_HEIGHT_MUST_GREATER_THAN_ZERO;
import static com.tma.solutions.common.Message.X_AND_Y_COORDINATE_MUST_GREATER_THAN_ZERO;

import java.util.Objects;
import java.util.Scanner;

import com.tma.solutions.common.Command;
import com.tma.solutions.common.ShapeType;
import com.tma.solutions.graphic.CommonGraphic;
import com.tma.solutions.shape.Canvas;
import com.tma.solutions.shape.Line;
import com.tma.solutions.shape.Rectangle;
import com.tma.solutions.shape.TwoDPoint;
import com.tma.solutions.shape.factory.ShapeAbstractFactory;

/**
 * This class used define a server to listen and handle request from client
 *
 * Main features (v1):
 * <pre>
 * <ul>
 *     <li>- C w h: Should create a new canvas with width w and height h</li>
 *     <li>- L x1 y1 x2 y2: Should create a new line from (x1,y1) to (x2,y2): L x1 y2 x2 y2.Currently only horizontal
 *     or vertical lines are supported. Horizontal and vertical lines will be drawn using the 'x' character.
 *     </li>
 *     <li>- R x1 y1 x2 y2: Should create a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2).
 *     Horizontal and vertical lines will be drawn using the 'x' character</li>
 *     <li>- B x y c: Should fill the entire area connected to (x,y) with "colour" c. The behavior of this is the same as that of
 *     the "bucket fill" tool in paint programs</li>
 *     <li>- Q: Should quit the program</li>
 * </ul>
 * </pre>
 *
 * @author Hieu Le Minh
 * @version 1.0.0
 * @since 1.0.0
 */
public class Server {
	private ShapeAbstractFactory shapeAbstractFactory;
	private CommonGraphic commonGraphic;

	/**
	 * This method used to init server by inject shape factory and the graphic
	 *
	 * @param shapeAbstractFactory is the shape factory used to create shape
	 * @param commonGraphic is the graphic used to draw
	 */
	public void init(ShapeAbstractFactory shapeAbstractFactory, CommonGraphic commonGraphic) {
		this.shapeAbstractFactory = shapeAbstractFactory;
		this.commonGraphic = commonGraphic;
	}

	/**
	 * This method used to listen request from user
	 */
	public void listenFromUser() {
		Scanner scanner = new Scanner(System.in);
		String input;

		do {
			System.out.print(ENTER_COMMAND.getMessageString());
			input = scanner.nextLine();
			handleUserInput(input);
		} while (!Objects.equals(input, Command.QUIT.getName()));
	}

	/**
	 * This method used to handle request from user
	 *
	 * @param input is the input command from user
	 */
	private void handleUserInput(String input) {
		input = formatUserInput(input);

		String[] inputArray = input.split(SPACE);
		String firstChar = inputArray[0].toLowerCase();

		Command command = Command.getCommandByName(firstChar);
		Canvas canvas = (Canvas) shapeAbstractFactory.getShape(ShapeType.CANVAS);

		switch (command) {
			case CREATE_CANVAS -> {
				if (inputArray.length != 3) {
					System.out.println(NOT_EQUAL_LENGTH_CREATE_CANVAS_COMMAND.getMessageString());
					break;
				}

				int width = tryParseInt(inputArray[1]);
				int height = tryParseInt(inputArray[2]);

				if (width <= 0 || height <= 0) {
					System.out.println(WIDTH_AND_HEIGHT_MUST_GREATER_THAN_ZERO.getMessageString());
					break;
				}

				canvas.setHeight(height);
				canvas.setWidth(width);

				commonGraphic.setCanvas(canvas);
				commonGraphic.initState();
				commonGraphic.render();
			}

			case CREATE_LINE -> {
				if (inputArray.length != 5) {
					System.out.println(NOT_EQUAL_LENGTH_CREATE_LINE_COMMAND.getMessageString());
					break;
				}

				int x1 = tryParseInt(inputArray[1]);
				int y1 = tryParseInt(inputArray[2]);
				int x2 = tryParseInt(inputArray[3]);
				int y2 = tryParseInt(inputArray[4]);

				if (x1 <= 0 || y1 <= 0 || x2 <= 0 || y2 <= 0) {
					System.out.println(X_AND_Y_COORDINATE_MUST_GREATER_THAN_ZERO.getMessageString());
					break;
				}

				Line line = (Line) shapeAbstractFactory.getShape(ShapeType.LINE);
				line.setStartPoint(new TwoDPoint(x1, y1));
				line.setEndPoint(new TwoDPoint(x2, y2));

				if (commonGraphic.drawLine(line)) {
					commonGraphic.render();
				}
			}

			case CREATE_RECTANGLE -> {
				if (inputArray.length != 5) {
					System.out.println(NOT_EQUAL_LENGTH_CREATE_RECTANGLE_COMMAND.getMessageString());
					break;
				}

				int x1 = tryParseInt(inputArray[1]);
				int y1 = tryParseInt(inputArray[2]);
				int x2 = tryParseInt(inputArray[3]);
				int y2 = tryParseInt(inputArray[4]);

				if (x1 <= 0 || y1 <= 0 || x2 <= 0 || y2 <= 0) {
					System.out.println(X_AND_Y_COORDINATE_MUST_GREATER_THAN_ZERO.getMessageString());
					break;
				}

				Rectangle rectangle = (Rectangle) shapeAbstractFactory.getShape(ShapeType.RECTANGLE);
				rectangle.setTopLeftPoint(new TwoDPoint(x1, y1));
				rectangle.setBottomRightPoint(new TwoDPoint(x2, y2));

				if (commonGraphic.drawRectangle(rectangle)) {
					commonGraphic.render();
				}
			}

			case BUCKET_FILL -> {
				if (inputArray.length != 4) {
					System.out.println(NOT_EQUAL_LENGTH_BUCKET_FILL_COMMAND.getMessageString());
					break;
				}

				int x = tryParseInt(inputArray[1]);
				int y = tryParseInt(inputArray[2]);

				if (x <= 0 || y <= 0) {
					System.out.println(X_AND_Y_COORDINATE_MUST_GREATER_THAN_ZERO.getMessageString());
					break;
				}

				if (inputArray[3].length() > 1) {
					System.out.println(COLOUR_MUST_BE_A_CHARACTER.getMessageString());
					break;
				}

				char c = inputArray[3].charAt(0);
				if (commonGraphic.bucketFill(new TwoDPoint(x, y), c)) {
					commonGraphic.render();
				}
			}

			case QUIT -> System.out.println(SEE_YOU_AGAIN.getMessageString());

			default -> System.out.println(COMMAND_NOT_FOUND.getMessageString());
		}
	}

	/**
	 * This method used to delete all spaces from beginning, ending string and extra spaces between words
	 *
	 * @param input is the user input to be formatted
	 * @return the formatted user input
	 */
	private String formatUserInput(String input) {
		return input.trim().replaceAll(ONE_OR_MORE_SPACES, SPACE);
	}

	/**
	 * This method used to try parse a string value to int value
	 *
	 * @param value is the string value to be parsed
	 * @return the int value
	 */
	private int tryParseInt(String value) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return -1;
		}
	}
}
