/**
 * Copyright (c) 2021 TMA Solutions, All Rights Reserved.
 */
package com.tma.solutions.shape.factory;

import static com.tma.solutions.common.Message.DIMENSION_UNSUPPORTED;

import com.tma.solutions.common.DimensionalType;

/**
 * This class used to define shape factory to produce concrete shape factory
 *
 * @author Hieu Le Minh
 * @version 1.0.0
 * @since 1.0.0
 */
public class ShapeFactory {
	/**
	 * Returns a concrete factory object that is an instance of the
	 * concrete factory class appropriate for the given architecture.
	 *
	 * @param type is the dimensional type of shape factory want to get
	 * @return ShapeAbstractFactory is the abstract factory of shape
	 */
	public static ShapeAbstractFactory getFactory(DimensionalType type) {
		switch (type) {
			case TWO_DIMENSIONAL:
				return new TwoDFactory();
			default:
				throw new UnsupportedOperationException(DIMENSION_UNSUPPORTED.getMessageString());
		}
	}
}
