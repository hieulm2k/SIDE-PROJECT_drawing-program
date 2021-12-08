/**
 * Copyright (c) 2021 TMA Solutions, All Rights Reserved.
 */
package com.tma.solutions.shape;

/**
 * This class used to define the Rectangle shape
 *
 * @author Hieu Le Minh
 * @version 1.0.0
 * @since 1.0.0
 */
public class Rectangle implements Shape {
	private TwoDPoint topLeftPoint;
	private TwoDPoint bottomRightPoint;

	/**
	 * Retrieves {@code {@link #topLeftPoint}}
	 *
	 * @return value of {@link #topLeftPoint}
	 */
	public TwoDPoint getTopLeftPoint() {
		return topLeftPoint;
	}

	/**
	 * Sets {@code topLeftPoint}
	 *
	 * @param topLeftPoint the {@code com.tma.solutions.shape.TwoDPoint} field
	 */
	public void setTopLeftPoint(TwoDPoint topLeftPoint) {
		this.topLeftPoint = topLeftPoint;
	}

	/**
	 * Retrieves {@code {@link #bottomRightPoint}}
	 *
	 * @return value of {@link #bottomRightPoint}
	 */
	public TwoDPoint getBottomRightPoint() {
		return bottomRightPoint;
	}

	/**
	 * Sets {@code bottomRightPoint}
	 *
	 * @param bottomRightPoint the {@code com.tma.solutions.shape.TwoDPoint} field
	 */
	public void setBottomRightPoint(TwoDPoint bottomRightPoint) {
		this.bottomRightPoint = bottomRightPoint;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return true if the shape is valid, and vice versa
	 */
	@Override
	public boolean isValid() {
		return topLeftPoint.getX() < bottomRightPoint.getX() && topLeftPoint.getY() < bottomRightPoint.getY();
	}
}
