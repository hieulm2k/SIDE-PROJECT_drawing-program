/**
 * Copyright (c) 2021 TMA Solutions, All Rights Reserved.
 */
package com.tma.solutions.graphic;

import com.tma.solutions.shape.Canvas;
import com.tma.solutions.shape.Line;
import com.tma.solutions.shape.Rectangle;
import com.tma.solutions.shape.TwoDPoint;

/**
 * This interface used to define all behaviors of the Graphic
 *
 * @author Hieu Le Minh
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CommonGraphic {
	/**
	 * This method used to set a canvas to graphic
	 *
	 * @param canvas is the canvas want to set
	 */
	void setCanvas(Canvas canvas);

	/**
	 * This method used to init the state of graphic
	 */
	void initState();

	/**
	 * This method used to draw a line
	 *
	 * @param line is the line want to draw
	 * @return true if draw successfully, and vice versa
	 */
	boolean drawLine(Line line);

	/**
	 * This method used to draw a rectangle
	 *
	 * @param rectangle is the rectangle want to draw
	 * @return true if draw successfully, and vice versa
	 */
	boolean drawRectangle(Rectangle rectangle);

	/**
	 * This method used to fill the canvas with the colour from startPoint
	 *
	 * @param startPoint is the start point want to fill
	 * @param colour is the colour want to fill
	 * @return true if fill successfully, and vice versa
	 */
	boolean bucketFill(TwoDPoint startPoint, char colour);

	/**
	 * This method used to render the current state of graphic
	 */
	void render();
}
