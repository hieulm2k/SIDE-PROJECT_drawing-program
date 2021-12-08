/**
 * Copyright (c) 2021 TMA Solutions, All Rights Reserved.
 */
package com.tma.solutions.shape.factory;

import static com.tma.solutions.common.Message.SHAPE_UNSUPPORTED;

import com.tma.solutions.common.ShapeType;
import com.tma.solutions.shape.Canvas;
import com.tma.solutions.shape.Line;
import com.tma.solutions.shape.Rectangle;
import com.tma.solutions.shape.Shape;

/**
 * This class used to define the 2D Factory to produce 2D Shape
 *
 * @author Hieu Le Minh
 * @version 1.0.0
 * @since 1.0.0
 */
public class TwoDFactory extends ShapeAbstractFactory {
	/**
	 * {@inheritDoc}
	 *
	 * @param type is the type of shape want to get
	 * @return Shape is the shape want to get
	 */
	@Override
	public Shape getShape(ShapeType type) {
		switch (type) {
			case LINE -> {
				return new Line();
			}
			case RECTANGLE -> {
				return new Rectangle();
			}
			case CANVAS -> {
				return new Canvas();
			}

			default -> throw new UnsupportedOperationException(SHAPE_UNSUPPORTED.getMessageString());
		}
	}
}
