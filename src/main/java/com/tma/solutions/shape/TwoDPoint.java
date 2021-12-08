/**
 * Copyright (c) 2021 TMA Solutions, All Rights Reserved.
 */
package com.tma.solutions.shape;

/**
 * This record used to define the 2D Point
 *
 * @author Hieu Le Minh
 * @version 1.0.0
 * @since 1.0.0
 */
public record TwoDPoint(int x, int y) {
	/**
	 * Retrieves {@code {@link #x}}
	 *
	 * @return value of {@link #x}
	 */
	public int getX() {
		return x;
	}

	/**
	 * Retrieves {@code {@link #y}}
	 *
	 * @return value of {@link #y}
	 */
	public int getY() {
		return y;
	}
}
