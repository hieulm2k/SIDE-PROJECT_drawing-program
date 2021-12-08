/**
 * Copyright (c) 2021 TMA Solutions, All Rights Reserved.
 */
package com.tma.solutions.shape.factory;

import com.tma.solutions.common.ShapeType;
import com.tma.solutions.shape.Shape;

/**
 * This abstract class used to define the abstract factory of shape
 *
 * @author Hieu Le Minh
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class ShapeAbstractFactory {
	/**
	 * This is the method used to get the shape base on the type
	 *
	 * @param type is the type of shape want to get
	 * @return Shape is the shape want to get
	 */
	public abstract Shape getShape(ShapeType type);
}
