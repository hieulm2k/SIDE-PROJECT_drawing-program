/**
 * Copyright (c) 2021 TMA Solutions, All Rights Reserved.
 */
package com.tma.solutions.shape;

/**
 * This class used to define the Canvas shape
 *
 * @author Hieu Le Minh
 * @version 1.0.0
 * @since 1.0.0
 */
public class Canvas implements Shape {
	private int width;
	private int height;

	/**
	 * Retrieves {@code {@link #width}}
	 *
	 * @return value of {@link #width}
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets {@code width}
	 *
	 * @param width the {@code int} field
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Retrieves {@code {@link #height}}
	 *
	 * @return value of {@link #height}
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets {@code height}
	 *
	 * @param height the {@code int} field
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return true if the shape is valid, and vice versa
	 */
	@Override
	public boolean isValid() {
		return true;
	}
}
