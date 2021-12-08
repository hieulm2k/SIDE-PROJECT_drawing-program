/**
 * Copyright (c) 2021 TMA Solutions, All Rights Reserved.
 */
package com.tma.solutions.shape;

/**
 * This class used to define the Line shape
 *
 * @author Hieu Le Minh
 * @version 1.0.0
 * @since 1.0.0
 */
public class Line implements Shape {
	private TwoDPoint startPoint;
	private TwoDPoint endPoint;

	/**
	 * Retrieves {@code {@link #startPoint}}
	 *
	 * @return value of {@link #startPoint}
	 */
	public TwoDPoint getStartPoint() {
		return startPoint;
	}

	/**
	 * Sets {@code startPoint}
	 *
	 * @param startPoint the {@code com.tma.solutions.shape.TwoDPoint} field
	 */
	public void setStartPoint(TwoDPoint startPoint) {
		this.startPoint = startPoint;
	}

	/**
	 * Retrieves {@code {@link #endPoint}}
	 *
	 * @return value of {@link #endPoint}
	 */
	public TwoDPoint getEndPoint() {
		return endPoint;
	}

	/**
	 * Sets {@code endPoint}
	 *
	 * @param endPoint the {@code com.tma.solutions.shape.TwoDPoint} field
	 */
	public void setEndPoint(TwoDPoint endPoint) {
		this.endPoint = endPoint;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return true if the shape is valid, and vice versa
	 */
	@Override
	public boolean isValid() {
		return startPoint.getX() == endPoint.getX() || startPoint.getY() == endPoint.getY();
	}

	/**
	 * This method used to swap 2 point if there position is not correct
	 */
	private void swapPoint() {
		TwoDPoint temp = startPoint;
		startPoint = endPoint;
		endPoint = temp;
	}

	/**
	 * This method used to check if position of start point and end point is correct or not
	 * if not -> swap 2 point back to their position
	 */
	public void checkPosition() {
		if (startPoint.getY() == endPoint.getY() && startPoint.getX() > endPoint.getX() ||
				startPoint.getX() == endPoint.getX() && startPoint.getY() > endPoint.getY()) {
			swapPoint();
		}
	}
}
