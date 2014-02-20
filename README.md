slogo
=====

SLogo Design Document

Introduction

We believe that model-view-controller would provide the optimal user interface software pattern for this project; however, at this moment we will just group code into either front end or back end. Our goal is to code an interface that allows the user to make calls - both user-defined and provided by SLogo - in order to control the movements of a turtle on screen. 


Front End

The frontend is simply the graphical user interface onto which the user will type an input and be displayed an output. The GUI will display multiple components. For instance, there will exist a command line where the user will type. There will also exist a display grid where the turtle will move. Moreover we can implement an error checker, which can display logical and syntactical errors. 

Back End

The backend is responsible for logic and other necessary components not visible to the user but critical to correct functionality of our IDE. 
One of the most integral components of the backend is the code parser. In order to parse the code we decided to first translate all of the user inputed code into a single string which we iterate through creating nodes where necessary (ie at methods, variables, etc.).  We also opted to use a Linked List to hold all of these nodes. Furthermore, we are still looking into implementing a syntax tree as part of our parser. The tree would be created based on nodes contained within our Linked List. 


	


View Package: 
- Class Panel: has a submit button, text field, label to display command history (uses jswing - can use JFrame to update frame), pop-up window to display error, and a canvas
Instance variable: myParser
	- void draw(): get x and y position of the turtle and draw
	
Model Package
- Interface Movable: 
	public method:
	- void updatePosition(double changeInX, double changeInY)
Movable turtle = new Turtle();

- Class Turtle: double myXPos, double myYPos
	public methods:
	- double getXPos() 
	- double getYPos()
	- void updatePosition(double changeInX, double changeInY)
	
- Class Parser implements Token: 
	Constructor:
	- Parser(String text, Turtle turtle)
public methods:
- boolean isValid(String text)
- Token tokenize(String text)
- AbstractNode createTree()
- void compileTree(AbstractNode node): interpret the tree and update the turtle position

- Interface Token: has public instance variables for parsing, e.g. FOR_LOOP for “repeat”
	- PARAMETER
	- FOR_LOOP
	- WHILE_LOOP
	- IF_TREE
	- …
	- Token tokenize(String text): implemented in Parser
 	
- abstract class AbstractNode: 
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

- Class IfNode extends AbstractNode
- Class WhileNode extends AbstractNode
- Class ForNode extends AbstractNode
- Class ActionNode extends AbstractNode
- Class FdNode extends ActionNode
- (more actions extends action node)



Controller Package:

MVC and factory method
