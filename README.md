#slogo

##SLogo Design Document

###Introduction

We believe that model-view-controller would provide the optimal user interface software pattern for this project; however, at this moment we will just group code into either front end or back end. Our goal is to code an interface that allows the user to make calls - both user-defined and provided by SLogo - in order to control the movements of a turtle on screen. 


###Front End

The frontend is simply the graphical user interface onto which the user will type an input and be displayed an output. The GUI will display multiple components. For instance, there will exist a command line where the user will type. There will also exist a display grid where the turtle will move. Moreover we can implement an error checker, which can display logical and syntactical errors. 

###Back End

The backend is responsible for logic and other necessary components not visible to the user but critical to correct functionality of our IDE. 
One of the most integral components of the backend is the code parser. In order to parse the code we decided to first translate all of the user inputed code into a single string which we iterate through creating nodes where necessary (ie at methods, variables, etc.).  We also opted to use a syntax tree to hold all of these nodes.

###Packages

####View Package: 
######Class Panel: has a submit button, text field, label to display command history (uses jswing - can use JFrame to update frame), pop-up window to display error, and a canvas

Instance variable: myParser
Instance variable: myTurtlle (initially set by default to lie in the center of the canvas)
- void drawTrailLine(oldXPos, old YPos, newXpos, newYpos): draw a trail line from 
  previous x and y coordinate of the turtle and the new x and y coordinate of the turtle
- void rotateTurtle()
	
####Model: 3 packages: turtle, parse, and nodes
#####Interface Movable: 
	public method:
	- void updatePosition(double changeInX, double changeInY)
	Example of creating a new turtle object in frontend: Movable turtle = new Turtle();

#####Class TurtleFactory: 
	public Turtle createTurtle();

#####Class FunctionFactory:
	public Function createFunction();

#####Class Turtle implements Movable: double myXPos, double myYPos
	public methods:
	- double getXPos() 
	- double getYPos()
	- void updatePosition(double changeInX, changeInY)
	- -void paint(); draws a turtle at the coordinates corresponding to myXPos and myYPos 
	
#####Class Parser implements Token: 
	Constructor:
	- Parser(String text, Turtle turtle)
	public methods:
	- boolean isValid()
	- void createAndStoreFunctions()
	- List<Function> getFunctions() 
	- void parseAllFunctions(List<Function>): calls tokenize, createTree, and compileTree in class Function, and updates turtle position
	
#####class Function:
	Constructor: 
	- Function(String text)
	public methods:
	- String getText()
	- Token tokenize(Function function)
	- AbstractNode createTree()
	- void compileTree(AbstractNode node): interpret the tree and update the turtle position

#####public interface Token: has public instance variables for parsing
	- final String INITIAL_POSITION = "setxy";
	- final String SET_PARAMETER = "set";
	- final String PARAMETER = "parameter";
	- final String FOR_LOOP = "repeat";
	- final String RIGHT = "rt";
	- final String FORWARD = "fd";
	- final String LEFT = "lt";
	- ...(more to add)
	- Token tokenize(String text): implemented in Parser
 	
#####abstract class AbstractNode: 
	
	constructors:
	- AbstractNode(Token token, double value)
	- AbstractNode(Token token)
	
	public methods:
	- AbstractNode makeNode(Token token)
	- AbstractNode getLeftNode()
	- AbstractNode getRightNode()
	- AbstractNode makeSibling(AbstractNode node): make another node a sibling (having the same parent node)
	- void makeLeftChild(AbstractNode node)
	- void makeRightChild(AbstractNode node)
	- double getValue(AbstractNode node)
	- void action(AbstractNode node): update position of the turtle
	
	The following public methods are mainly for for-loops and if-trees, in other types of Nodes where they're irrelevant they will be override with do nothing
	- AbstractNode getStartingNode()
	- AbstractNode getEndingNode()
	- void setStartingNode()
	- void setEndingNode()
	

- Class IfNode extends AbstractNode implements Token
- Class ForNode extends AbstractNode implements Token
- Class ActionNode extends AbstractNode implements Token
- ... (more to add)
- Class FdNode extends ActionNode
- (more actions extends action node)

###Example Code


		When user types ‘fd 50’ (assuming a turtle already exists on the canvas)
		double oldX = myTurtle.getXPos();
		double oldY = myTurtle.getYPos();
		myParser = new Parser(‘fd 50’, myTurtle); 
		parseAllFunctions(myParser.createAndStoreFunctions());
		 drawTrailLine(oldX, oldY, myTurtle.getXPos, myTurtle.getYPos()): 




###Sub-teams


Viju and Talal will work on developing the View and Controller (probably) package which will contain the front-end user interface classes.

Tara, Benson, and Chad will work on developing the back-end user packages, which include turtle, parse, and nodes.
		
###Why?

We chose to not use JGame for front end because there is a lot of code that is suited for a specific implementation of a game. Also, it might be hard to translate among coordinate systems and retain the information.  Debugging would be very difficult because there would also be a lot of internal game engine code with which we are unfamiliar with. We stuck with Swing and AWT mainly because those were the classes discussed in lecture and talked about within a larger contex of graphics design. 

###Screenshot
https://f.cloud.github.com/assets/4603228/2229071/7ba8d964-9ae6-11e3-9816-5d384c2d8a83.png

###UML Design
Located in the root directory of the master branch.

https://github.com/duke-compsci308-spring2014/slogo_team01/blob/master/UML%20for%20Model.pdf
