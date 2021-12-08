/**
 * Copyright (c) 2021 TMA Solutions, All Rights Reserved.
 */
package com.tma.solutions.graphic;

import static com.tma.solutions.common.Constant.BAR;
import static com.tma.solutions.common.Constant.DASH;
import static com.tma.solutions.common.Constant.DRAW_CHAR;
import static com.tma.solutions.common.Constant.EXTRA_SPACE;
import static com.tma.solutions.common.Constant.LINE_BREAK;
import static com.tma.solutions.common.Constant.MIN_X_COORDINATE;
import static com.tma.solutions.common.Constant.MIN_Y_COORDINATE;
import static com.tma.solutions.common.Constant.SPACE;
import static com.tma.solutions.common.Message.INVALID_LINE;
import static com.tma.solutions.common.Message.INVALID_RECTANGLE;
import static com.tma.solutions.common.Message.OUT_OF_RANGE;

import java.util.LinkedList;
import java.util.Queue;

import com.tma.solutions.shape.Canvas;
import com.tma.solutions.shape.Line;
import com.tma.solutions.shape.Rectangle;
import com.tma.solutions.shape.TwoDPoint;

/**
 * This class used to implement all behaviors of the Console graphic
 *
 * @author Hieu Le Minh
 * @version 1.0.0
 * @since 1.0.0
 */
public class ConsoleGraphic implements CommonGraphic {
	private StringBuilder currentState;
	private Canvas canvas;

	/**
	 * {@inheritDoc}
	 *
	 * @param canvas is the canvas want to set
	 */
	@Override
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initState() {
		String dashes = DASH.repeat(Math.max(0, canvas.getWidth() + 2));

		currentState = new StringBuilder();
		currentState.append(dashes);
		currentState.append(LINE_BREAK);

		for (int i = 0; i < canvas.getHeight(); ++i) {
			currentState.append(BAR);
			currentState.append(SPACE.repeat(Math.max(0, canvas.getWidth())));
			currentState.append(BAR);
			currentState.append(LINE_BREAK);
		}

		currentState.append(dashes);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param line is the line want to draw
	 * @return true if draw successfully, and vice versa
	 */
	@Override
	public boolean drawLine(Line line) {
		if (!line.isValid()) {
			System.out.println(INVALID_LINE.getMessageString());
			return false;
		}

		// Check if the position of start point and end point
		line.checkPosition();

		TwoDPoint startPoint = line.getStartPoint();
		TwoDPoint endPoint = line.getEndPoint();

		if (isOutOfRange(startPoint) || isOutOfRange(endPoint)) {
			System.out.println(OUT_OF_RANGE.getMessageString());
			return false;
		}

		if (startPoint.getY() == endPoint.getY()) {
			int length = endPoint.getX() - startPoint.getX() + 1;
			drawLineHorizontally(startPoint, length);
		} else if (startPoint.getX() == endPoint.getX()) {
			int length = endPoint.getY() - startPoint.getY() + 1;
			drawLineVertically(startPoint, length);
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param rectangle is the rectangle want to draw
	 * @return true if draw successfully, and vice versa
	 */
	@Override
	public boolean drawRectangle(Rectangle rectangle) {
		if (!rectangle.isValid()) {
			System.out.println(INVALID_RECTANGLE.getMessageString());
			return false;
		}

		TwoDPoint topLeftPoint = rectangle.getTopLeftPoint();
		TwoDPoint bottomRightPoint = rectangle.getBottomRightPoint();

		if (isOutOfRange(topLeftPoint) || isOutOfRange(bottomRightPoint)) {
			System.out.println(OUT_OF_RANGE.getMessageString());
			return false;
		}

		int width = bottomRightPoint.getX() - topLeftPoint.getX() + 1;
		int height = bottomRightPoint.getY() - topLeftPoint.getY() + 1;

		// Draw top edge
		drawLineHorizontally(topLeftPoint, width);
		// Draw bottom edge
		drawLineHorizontally(new TwoDPoint(topLeftPoint.getX(), bottomRightPoint.getY()), width);
		// Draw left edge
		drawLineVertically(topLeftPoint, height);
		// Draw right edge
		drawLineVertically(new TwoDPoint(bottomRightPoint.getX(), topLeftPoint.getY()), height);

		return true;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param startPoint is the start point want to fill
	 * @param colour is the colour want to fill
	 * @return true if fill successfully, and vice versa
	 */
	@Override
	public boolean bucketFill(TwoDPoint startPoint, char colour) {
		if (isOutOfRange(startPoint)) {
			System.out.println(OUT_OF_RANGE.getMessageString());
			return false;
		}

		bfs(startPoint, colour);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render() {
		System.out.println(currentState.toString());
	}

	/**
	 * This method used to implement breadth-first search to fill the canvas with a colour
	 *
	 * @param startPoint is the start point want to fill
	 * @param colour is the colour want to fill
	 */
	public void bfs(TwoDPoint startPoint, char colour) {
		// The array to mark a point is visited or not
		boolean[][] visited = new boolean[canvas.getWidth() + 1][canvas.getHeight() + 1];

		// Init visited array with false value
		for (int i = 0; i <= canvas.getWidth(); i++) {
			for (int j = 0; j <= canvas.getHeight(); j++) {
				visited[i][j] = false;
			}
		}

		// Create the queue to store the point and set start point as visited
		Queue<TwoDPoint> queue = new LinkedList<>();
		queue.add(startPoint);
		visited[startPoint.getX()][startPoint.getY()] = true;

		while (!queue.isEmpty()) {
			TwoDPoint frontPoint = queue.peek();
			int x = frontPoint.getX();
			int y = frontPoint.getY();
			char preColor = currentState.charAt(getIndex(frontPoint));
			currentState.setCharAt(getIndex(frontPoint), colour);
			queue.remove();

			// Upside
			if (!isOutOfRange(new TwoDPoint(x, y - 1)) && !visited[x][y - 1] && currentState.charAt(getIndex(new TwoDPoint(x, y - 1))) == preColor) {
				TwoDPoint p = new TwoDPoint(x, y - 1);
				queue.add(p);
				visited[x][y - 1] = true;
			}

			// Downside
			if (!isOutOfRange(new TwoDPoint(x, y + 1)) && !visited[x][y + 1] && currentState.charAt(getIndex(new TwoDPoint(x, y + 1))) == preColor) {
				TwoDPoint p = new TwoDPoint(x, y + 1);
				queue.add(p);
				visited[x][y + 1] = true;
			}

			// Right side
			if (!isOutOfRange(new TwoDPoint(x + 1, y)) && !visited[x + 1][y] && currentState.charAt(getIndex(new TwoDPoint(x + 1, y))) == preColor) {
				TwoDPoint p = new TwoDPoint(x + 1, y);
				queue.add(p);
				visited[x + 1][y] = true;
			}

			// Left side
			if (!isOutOfRange(new TwoDPoint(x - 1, y)) && !visited[x - 1][y] && currentState.charAt(getIndex(new TwoDPoint(x - 1, y))) == preColor) {
				TwoDPoint p = new TwoDPoint(x - 1, y);
				queue.add(p);
				visited[x - 1][y] = true;
			}
		}
	}

	/**
	 * This method used to draw a line horizontally
	 *
	 * @param startPoint is the start point of line
	 * @param length is the length of line
	 */
	private void drawLineHorizontally(TwoDPoint startPoint, int length) {
		int startIndex = getIndex(startPoint);

		for (int i = startIndex; i < startIndex + length; ++i) {
			currentState.setCharAt(i, DRAW_CHAR);
		}
	}

	/**
	 * This method used to draw a line vertically
	 *
	 * @param startPoint is the start point of line
	 * @param length is the length of line
	 */
	private void drawLineVertically(TwoDPoint startPoint, int length) {
		int startIndex = getIndex(startPoint);
		// extra space = 3: 2 border chars and 1 line break char
		int nextColumnIndex = (canvas.getWidth() + EXTRA_SPACE);
		for (int i = startIndex; i < startIndex + length * nextColumnIndex; i += nextColumnIndex) {
			currentState.setCharAt(i, DRAW_CHAR);
		}
	}

	/**
	 * This method used to get the index of point in canvas
	 *
	 * @param point is the point want to get index
	 * @return int value is the index of point
	 */
	private int getIndex(TwoDPoint point) {
		// extra space = 3: 2 border chars and 1 line break char
		return point.getY() * (canvas.getWidth() + EXTRA_SPACE) + point.getX();
	}

	/**
	 * This method used to check if a point is out of range or not
	 *
	 * @param point is the point want to check
	 * @return true if point is out of range, and vice versa
	 */
	private boolean isOutOfRange(TwoDPoint point) {
		return point.getX() < MIN_X_COORDINATE || point.getY() < MIN_Y_COORDINATE || point.getX() > canvas.getWidth() || point.getY() > canvas.getHeight();
	}
}
