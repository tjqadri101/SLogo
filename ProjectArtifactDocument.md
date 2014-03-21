#Project Artifact Document

##Introduction

##Overview:

###Frontend Modules: Talal, Viju, and Chad

###Backend Modules: Benson and Tara


##User Interface Design: Talal, Viju, and Chad


##Design Details: Benson and Tara

###Backend

The backend of our design includes three main packages: model, turtle, and nodes. The package parser contains class Parser, NodeFactory, and Model. Model is the manager of all workspaces; one workspace has one Parser, and Model keeps track of all the Parser objects. NodeFactory creates nodes using reflection, and its method of creating new nodes is called in class Parser.

Class Parser has two main methods: createTree() and traverseTree(). Method createTree() creates an Abstract Syntax Tree for the commands user enters into one workspace. A queue structure is used to store the individual words to be read. Each word is then passed into method createNode(), which is a method within class NodeFactory. 

As stated before, class NodeFactory’s purpose is to create nodes that will then be used in the creation of an Abstract Syntax Tree. Within the method createNode(), each passed in “word” is then filtered: is this String numerical or not? If the “word” is a number, then an instance of class NumberNode is created. 

Given the situation in which the String isn’t a number, reflection is then used. Within the method createNode() a Map is propagated with <String, String>, where each word is the key and each value is the referenced command. The classes CommandFinder() and CommandReader() make it possible to reference these commands. Provided a language and word, CommandFinder() uses reflection to reference the correct language properties file. CommandReader() then parses the file and creates the Map.

Method createNode() then uses the already generated Map to fetch the command. The fetched command is then used to create an instance of each node class via reflection. Let's resurface back to class Parser. 

In Class Parser's createTree(), command nodes, number nodes, and a variety of other nodes are created as we read each word that is popped out from the queue. A BlockNode is created whenever an expression is anticipated. A ConditionNode is created for the left node of control structures ("if" "ifelse", "repeat", "for", etc). When creating the Abstract Syntax Tree, decision of whether a child node will be added is made by the hasTwoChildren() and hasMoreThanTwoChildren() methods in that node. 

A tree structure is chosen because it represents the structure of program code. A tree structure is made of nodes, and each node can be edited and changed. Also, the tree we constructed contains essential information and eliminated brackets and semicolons, which are non-essential. Furthermore, the implementation of each nodes can be easily changed, and new nodes can be easily added. 

A post-order traversal is implemented when traversing the tree after it is created. When a node is reached, the .evaluate() method in the node class is called, which resolves the node and returns its value. For more complex control commands such as "repeat", "ifelse", "dotimes", the .traverseSubtree() method is implemented in their respective node class, which helps keep traversing the tree in post-order.

From Part 2 to Part 3, the methods of creating and traversing the tree are not changed. New control commands "dotimes", "for", and "to" are compared with existing node structures. For example, they are all similar to the "ifelse" command because two block nodes are required for their child nodes. Therefore, they can be easily incorporated into .createTree(). The methods to resolve these nodes are implemented in their respective node classes, and a .traverseSubtree() method is also implemented if required. 

User defined variables and functions are stored in the Parser class. When they are first encountered, they are added to the variable or function map, and when they are encountered after they have been declared, the FunctionNode or VariableNode that are already created are obtained from the map. In cases when the value of a VariableNode changes, such as in the "for" command, the value of the VariableNode is changed in the .traverseAndReplace() method in the ForNode.

Class Model is created in Part 3. We did not anticipate that there would be multiple workspaces and turtles when we implemented Part 2. We created the Model class, which is the "workspace manager" to deal with the change. We also change the constructors of all nodes from one turtle to a list of turtles. Class Model calls the .doParse() method in the Parser class to pass the commands. A list of turtles, commands, and user's choice of language are passed to the Parser class through the Parser constructor. (Tara to Benson: maybe explain more the interaction between the Model class and the controller?)

The turtle package is the central module of SLogo. Within this package, class Turtle contains all the properties that are updated by class Parser and are required by the view. The properties within each turtle instance are updated in real-time as traverseTree() is called. The methodology in which the information is extracted from class Turtle to the view leads us to the next component of our project.

The proceeding discussion will pertain to the interface design between the model and view where information is sent from the view to the model and fetched from the model to the view. The controller package and the turtle package are the two main components that make up this interface.

The controller package contains abstract class AbstractController and class ModelController.  Abstract class AbstractController was created on the premise of extendability and in a time of uncertainty. At the time, an undefined hierarchy in the view made it uncertain how information would be fetched. 

AbstractController was designed in a way that allows us to add registered views and a registered model, given the situation that we wanted to reference multiple workspaces. Methods within AbstractController such as getTurtleList() and getVariables() make it possible to fetch information from the backend.

Moreover, the one and only instance of Class Model is created in AbstractController. This design choice was based on the fact that only one model was needed to fufill the requirements from the view.

Given such time constraints, an all-encompassing controller, class ModelController, was created. This class extends AbstractController and has method passToModel(), which is used to pass information to the backend. This method takes in a list of instances of TurtleImage (Class TurtleImage is displayed in the GUI and is used to represent a frontend replication of the backend turtle), a command, and a language.

Within passToModel(), TurtleImage is converted into an ITurtle, which is an interface implemented by class Turtle. The purpose of ITurtle was to create a level of encapsulation between the model and view; there’s no need for the controller to have access to a complete instantiation of class turtle.

Using all of these parameters, method passToModel() calls method processCommands(), which is a method residing in class Model. Method processCommands() is the highest level method within the backend.

Class Workspace panel, the “top” module of the frontend, calls method passToEnglishModel() within a property listener method to bridge the connection between the frontend and the controller. Method passToEnglishModel() is a wrapper function of passToModel(). In other words, passToModel() is passed in with ‘English’ as the language of choice within passToEnglishModel(). Any language could have been chosen to be passed into passToModel(). This wrapper function was created because the frontend lacks the ability to choose a language of choice.



Tara to Benson: Turtle and Node Factory



###Frontend



###Model: (Tara)

###Parser: (Tara)

###Nodes: (Tara)

###Turtle: (Benson)
