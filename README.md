#slogo

##SLogo Design Document

###[Demo Video](http://www.youtube.com/watch?v=hXYE7NbJbn0&feature=youtu.be)


###Introduction

We believe that model-view-controller would provide the optimal user interface software pattern for this project; however, at this moment we will just group code into either front end or back end. Our goal is to code an interface that allows the user to make calls - both user-defined and provided by SLogo - in order to control the movements of a turtle on screen. 


###Front End

The frontend is simply the graphical user interface onto which the user will type an input and be displayed an output. The GUI will display multiple components. For instance, there will exist a command line where the user will type. There will also exist a display grid where the turtle will move. Moreover we can implement an error checker, which can display logical and syntactical errors. 

###Back End

The backend is responsible for logic and other necessary components not visible to the user but critical to correct functionality of our IDE. 
One of the most integral components of the backend is the code parser. In order to parse the code we decided to first translate all of the user inputed code into a single string which we iterate through creating nodes where necessary (ie at methods, variables, etc.).  We also opted to use a syntax tree to hold all of these nodes.

###Packages

####View: 5 packages: view, turtle_graphics, preferences,functionStorage, and main. view.menuComponent is a sub-package of the view package
	Talal, Chad, and Viju

#####Package main:

######Class Slogo extends JFrame:
	
	Constructor(s):
	No constructor
	
	Public Methods:
	static void main(String[] args): MAIN METHOD

#####Package turtle_graphics:

######Class TurtleFileChooser:

	Constructor(s):
	No constructor

	Public Methods:
	public static File chooseImageFile(): call this method in class TurtleImage; select and return image for a particular turtle

######Class TurtleImage implements ITurtle:

	Constructor(s):
	public TurtleImage(int x, int y)

	Public Methods:
	public BufferedImage setImage() throws IOException: sets the image file of turtle as a buffered image
	public void paintTurtle(Graphics2D pen)
	public void paintLines(Graphics2D pen, Color penColor, int myPen)
	private void initializeTopLeftCorner(double xCenter, double yCenter): defines top left corner of image from center position co-ordinates since Graphics2D image is drawn with respect to top left corner
	public void reinitialTurtle(); maintains the turtle in its current position again
	public void moveTurtleStandardButtons(double deltaX, double deltaY): changes turtle position by specified deltaX and deltaY
	public void updateTurtleState(double deltaX, double deltaY, double heading) 
	public void rotateTurtleRight90()
	public String getCanvasStateInfo(int panelWidth, int panelHeight): returns the turtle coordinates and angle
	public void setTurtleCenter(int panelWidth, int panelHeight): sets turtle at center of a panel if inputted panelWidth and panelHeight are correct. Clears all the trace lines associated with this TurtleImage
	

#####Package view:

######Class TurtleDisplayPanel extends JPanel:

	Constructor(s):
	public TurtleDisplayPanel()

	Public Methods:
	public void createNewTurtleImage(): create an instance of the TurtleImage class
	protected void paintComponent(Graphics g): paint all the TurtleImage turtles and their associated lines
	public void moveTurtleLeft(): move all TurtleImages horizontally left by 5 pixels from its current location w.r.t the center of panel
	public void moveTurtleRight(): move all TurtleImages horizontally right by 5 pixels from its current location w.r.t the center of panel
	public void moveTurtleDown(): move all TurtleImages vertically downwards by 5 pixels from its current location w.r.t the center of panel
	public void moveTurtleForward(): move all TurtleImages vertically upwards by 5 pixels from its current location w.r.t the center of panel
	public void rotateTurtlesRight(): calls each TurtleImage�s rotateTurtleRight90 method
	public Color getColor(): get line pen color
	public List<TurtleImage> getTurtleList()
	public void setPenToggle(): turn line pen on or off
	public void setColor(Color c) set line pen color
	public void resetTurtle(): sets all the turtles at the panel�s center
	public void setList(List<TurtleImage> list): set the current list of TurtleImages in the panel
	public String getAllPositionInfos(): return position and headings of all the TurtleImages

######Class ActionDisplayPanel extends GridBagPanel:
	
	Constructor(s):
	public ActionDisplayPanel()
	
	Public Methods:
	public void showState(): show headings and coordinates of all the TurtleImages
	public TurtleDisplayPanel getInstance(): return the instance of the TurtleDisplayPanel used in this instance of ActionDisplayPanel
	
######Class WorkspacePanel:
	Constructor(s): 
	Controller
	
	Public Methods:
	void PropertyChange(PropertyChangeEvent evt): called by controller. Passes data to controller. Refreshes display.
	Color getWorkspaceColor(): for controller.
	String getCurrentFile(): for controller.
	int getNumTurtles(): for controller.
	void setBackgroundColor(Color c): for controller.
	void modelPropertyChange(PropertyChangeEvent evt): null method

######Subpackage menuComponents:

######Class ColorMenuComponent:

	Constructor(s):
	No constructor
	
	Public Methods:
	getColor(): used when creating the menu. Only called when menu is built.

######Class FileMenuComponent 

	Constructor(s):
	No constructor
	
	Public Methods:
	getLabel(): used when creating the menu. Only called when menu is built.
	getCommand(): same as above


#####Package functionStorage:

######Class FunctionMenu:
	Constructor(s):
	Label: name of menu button
	
	Public Methods:
	None

######Class LoadFunctions:
	Constructor(s):
	JComponent: what the fileChooser dialog it is added to

	Public Methods:
	uploadFunctions: gets a File passed in and reads the functions from this file
	
######Class SaveFunctions:
	Constructor(s):
	JComponent: what the fileChooser dialog it is added to

	Public Methods:
	downloadFunctions: gets a File passed in and puts the functions in this file


#####Package preferences:
	
######Class PreferenceChooser

	Constructor(s):
	No constructor
	
	Public Methods:
	public static File choosePrefFile(): chooses and returns an XML file holding saved workspace
	
	
######Class PreferenceHelper
	
	Constructor(s):
	No constructor
	
	Public Methods:
	public static void write(TurtleDisplayPanel t, String filename) throws Exception: meant to write TurtleDisplayPanel as an XML file. Faulty implementation not corrected before project deadline
	public static TurtleDisplayPanel read() throws Exception: meant to load TurtleDisplayPanel from its preferences saved as an XML file. Not correctly implemented before project deadline																					

####Controller:
	Benson and Talal


####Model: 3 main packages: model, nodes, and turtle; within package nodes, there are sub-packages of different types of nodes

#####Package turtle:

######Class Turtle extends AbstractModel implements ITurtle:

	Constructor(s):
	public Turtle()
	public Turtle(double initialX, double initialY, double angle)
	public Turtle(ITurtle iturtle)
	
	Public Methods:
	public void setPenColor(double colorIndex)
	public void setTurtleShape(double shapeIndex)
	public void setID(String id)
	public String getID()
	public void updatePosition(double changeInAngle, double changeInDistance)
	public void clearScreen()
	public void resetClear()
	public void setPosition(double xCoord, double yCoord)
	public void setHeading(double angle)
	public void setPenUp()
	public void setPenDown()
	public void setInvisible()
	public void setVisible()
	public double getXPos()
	public double getYPos()
	public double getAngle()
	public double getPen() 
	public double getVisibility() 
	public String getLangauge() 
	public double getPenColorIndex()
	public double getMyShapeIndex()
	
	public double getPrevX()
	public double getPrevY() 
	public double getDeltaX() 
	public double getDeltaY() 
	public double getAngle() 
	public double getPenToggle() 
	public double getCurX() 
	public double getCurY() 
	public String getPenColor() 
	
	

######interface ITurtle:
	public double getPrevX()
	public double getPrevY() 
	public double getDeltaX() 
	public double getDeltaY() 
	public double getAngle() 
	public double getPenToggle() 
	public double getCurX() 
	public double getCurY() 
	public String getPenColor() 

######abstract class AbstractModel:

	Constructor(s):
	public AbstractModel()
	
	Public Methods:
	public void addPropertyChangeListener(PropertyChangeListener listener) 
	public void removePropertyChangeListener(PropertyChangeListener listener) 

#####Package model:

######Class Parser: 

	Constructor(s):
	public Parser(List<Turtle> turtles, String commands, String language)
	
	Public Methods:
	public boolean isValid(): return true if the commands are valid syntax
	public double doParse(): call this method in class Model; return value to display in the view
	public AbstractNode createTree(): create an Abstract Syntax Tree; return the root
	public double traverseTree(AbstractNode root): traverse an Abstract Syntax tree; return value to display in the view
	public List<String> getVariables: return a list of variables
	
	
######Class Model:

	Constructor(s):
	public Model()
	
	Public Methods:
	public List<Turtle> getTurtles()
	public List<String> getVariables()
	public double processCommands(List<Turtle> turtles, String commands, String language): process commands and return value to display
	
	
######Class CommandFinder:

	Constructor(s):
	public CommandFinder()
	
	Public Methods:
	public static String aliasLookup(String language)

######Class CommandReader: 

	Constructor(s):
	public CommandReader()
	
	Public Methods:
	public static Map<String, String> readCommands(String fileName)

#####Package nodes:
 	
######abstract class AbstractNode: 
	
	Constructor(s):
	public AbstractNode(List<Turtle>)
	
	Public Methods:
	public List<Turtle> getTurtles()
	public void setTurtles(List<Turtle> turtles)
	public AbstractNode getLeftNode()
	public AbstractNode getRightNode()
	public AbstractNode getParent()
	public void setLeftNode(AbstractNode node)
	public void setRightNode(AbstractNode node)
	public void setParent(AbstractNode node)
	public void addChild(AbstractNode node)
	public boolean hasChild()
	public List<AbstractNode> getChildren()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChilren()
	public boolean hasOneConditionOneBlock(): return true when one condition node and one block node are current node's child nodes
	public boolean hasTwoBlockNodes(): return true when two block nodes are current node's child nodes
	public boolean isAlreadyDeclared(): (for function nodes and variable nodes) return true if the function or variable has already been declared
	public void setCurrentValue()
	public String getName(): (for function nodes and variable nodes) return the name of the function or variable
	
	public double evaluate(): return the value of subtree


######class BlockNode extends AbstractNode:

	Constructor(s):
	public BlockNode(List<Turtle>)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()



######class FunctionNode extends AbstractNode:

	Constructor(s):
	public FunctionNode(List<Turtle>)
	
	Public Methods:
	public void setName(String name): set function name
	public String getName()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()


######class MakeNode extends AbstractNode:

	Constructor(s):
	public MakeNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	

######class VariableNode extends AbstractNode:

	Constructor(s):
	public VariableNode(List<Turtle> turtles, String variabelName)
	public VariableNode(List<Turtle> turtles, String variabelName, double value)
	public VariableNode(List<Turtle> turtles, String variableName, double startingValue, double endingValue, double increment)
	
	Public Methods:
	public void setCurrentValue(double value)
	public void setIncrement(double value)
	public String getName(): return variable name
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()


######class NodeFactory:

	Constructor(s):
	public NodeFactory(List<Turtle> turtles, String language) 
	
	Public Methods:
	public AbstractNode createNode(String word)
	

#####Package nodes.booleannodes:

######class CompareNode extends AbstractNode:

	Constructor(s):
	public CompareNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()

######class GreaterNode extends CompareNode:

	Constructor(s):
	public GreaterNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	
	
######class OrNode extends CompareNode:

	Constructor(s):
	public OrNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	
	
######class NotNode extends AbstractNode:

	Constructor(s):
	public NotNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allosMoreThanTwoChildren()
	
	

######class NotEqualNode extends CompareNode:

	Constructor(s):
	public NotEqualNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	
	
######class LessNode extends CompareNode:

	Constructor(s):
	public LessNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	
	
######class LessNode extends CompareNode:

	Constructor(s):
	public LessNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	
	
######class AndNode extends CompareNode:

	Constructor(s):
	public AndbenNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()	

#####Package nodes.commandnodes:

######class LeftNode extends AbstractNode:

	Constructor(s):
	public LeftNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()


######class RightNode extends AbstractNode:

	Constructor(s):
	public RightNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	
	
######class SetHeadingNode extends AbstractNode:

	Constructor(s):
	public SetHeadingNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	
	
######class SetXYNode extends AbstractNode:

	Constructor(s):
	public SetXYNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	
	
######class BackNode extends AbstractNode:

	Constructor(s):
	public BackNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	
	
######class ForwardNode extends AbstractNode:

	Constructor(s):
	public ForwardNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	
	
######class ShowTurtleNode extends LeafNode:

	Constructor(s):
	public ShowTurtleNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	
	
######class ClearScreenNode extends LeafNode:

	Constructor(s):
	public ClearScreenNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()	


######class PenUpNode extends LeafNode:

	Constructor(s):
	public PenUpNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	

######class PenDownNode extends LeafNode:

	Constructor(s):
	public PenDownNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	
	
######class HomeNode extends LeafNode:

	Constructor(s):
	public HomeNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()	

#####Package nodes.controlnodes:

######class ConditionNode extends AbstractNode:

	Constructor(s):
	public ConditionNode(List<Turtle> turtles) 
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	
	
######class DoTimesNode extends AbstractNode:

	Constructor(s):
	public DoTimesNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	public boolean hasTwoBlockNodes()
	
	
######class ForNode extends AbstractNode:

	Constructor(s):
	public ForNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	public boolean hasTwoBlockNodes()
	
	
######class IfElseNode extends AbstractNode:

	Constructor(s):
	public IfElseNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	
	
######class IfNode extends AbstractNode:

	Constructor(s):
	public IfNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	public boolean hasOneConditionOneBlock()
	
	
######class RepeatNode extends AbstractNode:

	Constructor(s):
	public RepeatNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	public boolean hasOneConditionOneBlock()
	
	
#####Package nodes.displaynodes:

######class PenColorNode extends LeafNode:

	Constructor(s):
	public PenColorNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	
	
######class ShapeNode extends LeafNode:

	Constructor(s):
	public ShapeNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	
	
######class SetShapeNode extends LeafNode:

	Constructor(s):
	public SetShapeNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	
	
######class SetPenColorNode extends AbstractNode:

	Constructor(s):
	public SetPenColorNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()	

#####Package nodes.leafnodes:

######class LeafNode extends AbstractNode:

	Constructor(s):
	public LeafNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	

######class NumberNode extends LeafNode:

	Constructor(s):
	public NumberNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()	

#####Package nodes.mathnodes:


######class RandomNode extends AbstractNode:

	Constructor(s):
	public RandomNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	
	
######class RemainderNode extends AbstractNode:

	Constructor(s):
	public RemainderNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	
	
######class SinNode extends AbstractNode:

	Constructor(s):
	public SinNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()	
	
	
######class SumNode extends AbstractNode:

	Constructor(s):
	public SumNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	
	
######class QuotientNode extends AbstractNode:

	Constructor(s):
	public QuotientNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()	
	
	
######class ProductNode extends AbstractNode:

	Constructor(s):
	public ProuctNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()	
	
######class TanNode extends AbstractNode:

	Constructor(s):
	public TanNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	
######class PowNode extends AbstractNode:

	Constructor(s):
	public PowNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	
######class MinusNode extends AbstractNode:

	Constructor(s):
	public MinusNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()	


######class ATanNode extends AbstractNode:

	Constructor(s):
	public AtanNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	
	
######class CosNode extends AbstractNode:

	Constructor(s):
	public CosNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	

######class DifferenceNode extends AbstractNode:

	Constructor(s):
	public DifferenceNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()


######class LogNode extends AbstractNode:

	Constructor(s):
	public LogNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	
	
#####class MathResults

	Constructor(s):
	public MathResults()
	
	Public Methods:
	public static List<String> getMathResultsList()
	public static void addToMathResultsList(String result)
	
#####Package nodes.querynodes:


######class HeadingNode extends LeafNode:

	Constructor(s):
	public HeadingNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	
	
######class PenDownPNode extends AbstractNode:

	Constructor(s):
	public PenDownPNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	

######class ShowingNode extends AbstractNode:

	Constructor(s):
	public ShowingNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	

######class XCorNode extends AbstractNode:

	Constructor(s):
	public XCorNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	
	
######class YCorNode extends AbstractNode:

	Constructor(s):
	public YCorNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()	

###Example Code


		



###Sub-teams


Viju, Talal, and Chad worked on developing the view package which contains the front-end user interface classes.

Benson and Talal worked on the controller which interfaces the view with the model.

Tara and Benson worked on developing the back-end user packages, which include turtle, parse, and nodes.
		
###Why?

We chose to not use JGame for front end because there is a lot of code that is suited for a specific implementation of a game. Also, it might be hard to translate among coordinate systems and retain the information.  Debugging would be very difficult because there would also be a lot of internal game engine code with which we are unfamiliar with. We stuck with Swing and AWT mainly because those were the classes discussed in lecture and talked about within a larger contex of graphics design. 

###Screenshot
https://f.cloud.github.com/assets/4603228/2229071/7ba8d964-9ae6-11e3-9816-5d384c2d8a83.png

###UML Design
![] (https://raw.githubusercontent.com/duke-compsci308-spring2014/slogo_team01/master/UML.jpg?token=6359679__eyJzY29wZSI6IlJhd0Jsb2I6ZHVrZS1jb21wc2NpMzA4LXNwcmluZzIwMTQvc2xvZ29fdGVhbTAxL21hc3Rlci9VTUwuanBnIiwiZXhwaXJlcyI6MTM5NjEwNjA2MX0%3D--4796447e788ddd88d97cf2eb1f9905939ce7c331)
