/**
 * Copyright (c) 2021 TMA Solutions, All Rights Reserved.
 */
package com.tma.solutions;

import com.tma.solutions.common.DimensionalType;
import com.tma.solutions.common.GraphicType;
import com.tma.solutions.graphic.CommonGraphic;
import com.tma.solutions.graphic.factory.GraphicFactory;
import com.tma.solutions.shape.factory.ShapeAbstractFactory;
import com.tma.solutions.shape.factory.ShapeFactory;

/**
 * This class used to define a client to connect to server
 * Client can choose which dimensional and graphic type
 *
 * @author Hieu Le Minh
 * @version 1.0.0
 * @since 1.0.0
 */
public class Client {
	public static void main(String[] args) {
		ShapeAbstractFactory shapeAbstractFactory = ShapeFactory.getFactory(DimensionalType.TWO_DIMENSIONAL);
		CommonGraphic commonGraphic = GraphicFactory.getGraphic(GraphicType.CONSOLE);

		Server server = new Server();
		server.init(shapeAbstractFactory, commonGraphic);
		server.listenFromUser();
	}
}
