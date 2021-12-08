/**
 * Copyright (c) 2021 TMA Solutions, All Rights Reserved.
 */
package com.tma.solutions.graphic.factory;

import static com.tma.solutions.common.Message.DIMENSION_UNSUPPORTED;

import com.tma.solutions.common.GraphicType;
import com.tma.solutions.graphic.CommonGraphic;
import com.tma.solutions.graphic.ConsoleGraphic;

/**
 * This class used to define a Graphic Factory to produce Graphic
 *
 * @author Hieu Le Minh
 * @version 1.0.0
 * @since 1.0.0
 */
public class GraphicFactory {
	/**
	 * This is the factory method to create CommonGraphic based on GraphicType
	 *
	 * @param type is the type of graphic that want to create
	 * @return the CommonGraphic that want to create
	 */
	public static CommonGraphic getGraphic(GraphicType type) {
		switch (type) {
			case CONSOLE:
				return new ConsoleGraphic();
			default:
				throw new UnsupportedOperationException(DIMENSION_UNSUPPORTED.getMessageString());
		}
	}
}
