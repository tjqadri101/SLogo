#Project Artifact Document

##Introduction

##Overview:

###Frontend Modules: Talal, Viju, and Chad

####FunctionStorage
This module deals with loading and saving the user-defined functions. The functionMenu class is called by the menu-builder, and this just serves to “package” the load and saving capabilities of this module. It hides the other classes in this module when it’s called. 

####WorkspacePanel
This class serves as a container for some of the pieces of the GUI. It contains a ProgrammingPanel and an ActionDisplayPanel. The ProgrammingPanel and the ActionDisplayPanel contain many things for the user, such as the buttons and the visual display– it is in a sense their “workspace”. The ProgrammingPanel interacts through the controller with the backend to pass commands while the ActionDisplayPanel is primarily a front-end component. The panels were divided in this manner to keep the separate functionalities separate, to keep the code more readable.

###Backend Modules: Benson and Tara


##User Interface Design: Talal, Viju, and Chad


##Design Details: Benson and Tara

###Backend

The backend of our design includes three main packages: model, turtle, and nodes. The package parser contains class Parser, NodeFactory, and Model. Model is the manager of all workspaces; one workspace has one model and one controller, and each model keeps track of all of its respective Parser objects. NodeFactory creates nodes using reflection, and its method of creating new nodes is called in class Parser.

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

Despite the nomenclature, passToController() also contains method updateTurtleState() that updates the x-coordinates, y-coordinates, and heading of a List of class TurtleImage (the frontend turtles). This design choice was influenced by the impending deadline.

Class Workspace panel, the “top” module of the frontend, calls method passToEnglishModel() within a property listener method to bridge the connection between the frontend and the controller. Method passToEnglishModel() is a wrapper function of passToModel(). In other words, passToModel() is passed in with ‘English’ as the language of choice within passToEnglishModel(). Any language could have been chosen to be passed into passToModel(). This wrapper function was created because the frontend lacks the ability to choose a language of choice.

The controller was conceptually designed to reduce the amount of direct communication between the model and view. The controller’s interaction with frontend is through the IView interface. Although there are no implemented methods from IView, this interface was planned to ping the controller whenever any changes occurred in the view.

Lastly, class Turtle extends abstract class AbstractModel. AbstractModel was created to improve communication between the controller and ITurtle by notifying the controller whenever a Turtle property changed. Although none of the methods from AbstractModel were implemented, the extension of AbstractModel provides a template for feasible changes in the future.

####Model: (Tara)

####Parser: (Tara)

####Nodes: (Tara)

####Turtle: (Benson)

###Frontend

####MenuComponents
This module is for the components that are present in the menu bar. It contains two classes, ColorMenuComponent and FileMenuComponent. Both of these are enum objects. This module compartmentalizes the busy work for some of the menu creation. To add a new color choice for the background, the user just needs to modify the ColorMenuComponent file in a very intuitive way and to add a new option for the fileMenu, the user just needs to add the name of the button and the method that it calls in the FileMenuComponent. Thus, in the class where the menu is created, it just refers to these two classes instead of listing all the components in there, making for more readable code as well. 

The individual components in the two classes are called and created in different ways – this shows that they can be implemented differently. ColorMenuComponent is created by just calling the different “variables” in each part, but the FileMenuComponent is created through reflection. The FileMenuComponent pieces contain names of functions that are called when different menu buttons are clicked, which is why this class implements reflection.  

This module was created to abstract some of the menu bar creation, making the code more readable and more easily extensible. This adheres to the design principles we learned in class as well. 

####FunctionStorage
This class handles the saving and loading of the user-defined functions to this project. It will ideally have contact with the controller to obtain the functions that it loads and saves. It is essentially just a saving and loading device right now – it will get the data that it needs to load and save from the controller. And this code is easily extensible as well. To add additional functionality, the user just needs to create another class and add it to this module and the functionMenu class. For example, if the user wanted to add the ability to combine files of functions together, the user would just need to create a class called FunctionCombiner (for example) and then add the functionality and then add it to the FunctionMenu Class. This menu could be created in a manner similar to the other menus in this project, but this functionality was not implemented. The names of this module might also be confusing, as they are verbs rather than nouns, but this could also be changed easily. 

The functionality for this class was not completely implemented, but we were planning on having the controller pass the functions to be saved through this class, thus keeping the communication between the front and back ends to a minimum, managed by the controller. 

This module was created to abstract the saving and loading of functions to the project. It could have been named “DataManager” and then the saving and loading preferences could also have been included here – this would reduce the repeated code and adhere to design principles of keeping this abstract and distant from their “implementations”. Unfortunately, we were not able to fully implement the functionality of either of these things, but we our design would have allowed us to easily add them. And even though the naming indicates otherwise, this module deals with interacting with different data, thus it adheres to design principles of keeping things separate, abstract, and extensible.

####WorkspacePanel
A majority of this class’s functions deal with interacting with the panels that it contains. It creates and sets each one, which are all separate methods. It is essentially a container for the other panels – streamlining the communication that the controller makes between the front and the back ends. Thus, a majority of the functionality of this panel is essentially discussed in the ActionDisplayPanel piece and the ProgrammingPanel piece of this design details document.
This follows the principles of increased readability and naming by keeping the pieces of our project that do separate things in separate functions and classes. While there may be a lot of classes, it is easier to see how they fit together and what the individual components do because we separated them.
