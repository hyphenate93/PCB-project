package project;

/**
 * @author Niklas Andersson
 * @author Andreas Östlin
 * 
 * Specifies what type of components we are using in the Toolbar
 */
@SuppressWarnings("serial")
public class BreadBoardComponent extends BreadBoard
{
	
	/**
	 * @return Node[] add components to toolbar
	 */
	public Node[] getNodePrototypes()
	{
		Node[] components = {
			   	new Resistor(),
	            new Capacitor(),
	            new Potentiometer(),
	            new Inductor(),
	            new Wire()};  
		return components;
	}
}





