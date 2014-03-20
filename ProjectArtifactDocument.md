#Project Artifact Document

##Introduction

##Overview:

###Frontend Modules: Talal, Viju, and Chad

###Backend Modules: Benson and Tara


##User Interface Design: Talal, Viju, and Chad


##Design Details: Benson and Tara

###Backend

The backend of our design includes three main packages: model, turtle, and nodes. The package parser contains class Parser, NodeFactory, and Model. Model is the manager of all workspaces; one workspace has one Parser, and Model keeps track of all the Parser objects. NodeFactory creates nodes using reflection, and its method of creating new nodes is called in class Parser.

Class Parser has two main methods: createTree() and traverseTree(). Method createTree() creates an Abstract Syntax Tree for the commands user enters into one workspace. A queue structure is used to store the individual words to be read. As we read in each word, (Tara to Benson: NodeFactory, CommandReader, and CommandFinder). 

Command nodes and number nodes are created as we read each word that is poped out from the queue. A BlockNode is created whenever an expression is anticipated. A ConditionNode is created for the left node of control structures (“if”, “ifelse”, “repeat”, “for”, etc). When creating the Abstract Syntax Tree, decision of whether a child node will be added is made by the hasTwoChildren() and hasMoreThanTwoChildren() methods in that node. 

A tree structure is chosen because it represents the structure of program code. A tree structure is made of nodes, and each node can be edited and changed. Also, the tree we constructed contains essential information and eliminated brackets and semicolons, which are non-essential. Furthermore, the implementation of each nodes can be easily changed, and new nodes can be easily added. 

A post-order traversal is implemented when traversing the tree after it is created. When a node is reached, the .evaluate() method in the node class is called, which resolves the node and returns its value. For more complex control commands such as “repeat”, “ifelse”, “dotimes”, the .traverseSubtree() method is implemented in their respective node class, which helps keep traversing the tree in post-order.

From Part 2 to Part 3, the methods of creating and traversing the tree are not changed. New control commands “dotimes”, “for”, and “to” are compared with existing node structures. For example, they are all similar to the “ifelse” command because two block nodes are required for their child nodes. Therefore, they can be easily incorporated into .createTree(). The methods to resolve these nodes are implemented in their respective node classes, and a .traverseSubtree() method is also implemented if required. 

User defined variables and functions are stored in the Parser class. When they are first encountered, they are added to the variable or function map, and when they are encountered after they have been declared, the FunctionNode or VariableNode that are already created are obtained from the map. In cases when the value of a VariableNode changes, such as in the “for” command, the value of the VariableNode is changed in the .traverseAndReplace() method in the ForNode.

Class Model is created in Part 3. We did not anticipate that there would be multiple workspaces and turtles when we implemented Part 2. We created the Model class, which is the “workspace manager” to deal with the change. We also change the constructors of all nodes from one turtle to a list of turtles. Class Model calls the .doParse() method in the Parser class to pass the commands. A list of turtles, commands, and user’s choice of language are passed to the Parser class through the Parser constructor. (Tara to Benson: maybe explain more the interaction between the Model class and the controller?)

Tara to Benson: Turtle and Node Factory



###Frontend



###Model: (Tara)

###Parser: (Tara)

###Nodes: (Tara)

###Turtle: (Benson)
