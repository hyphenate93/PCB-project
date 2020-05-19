package project;

import java.awt.Color;


public class ComponentGraph extends Graph
{

	public Node[] getNodePrototypes()
	{
		Node[] components =
			{
			   	new Resistor(new Color(255, 100, 50)),
	            new Capacitor(Color.WHITE),
	            new Potentiometer(Color.BLUE),
	            new Inductor(Color.WHITE),
	            new Wire(Color.WHITE)};  

		return components;
	}
}





