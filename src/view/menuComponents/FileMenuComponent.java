package view.menuComponents;

public enum FileMenuComponent {
	
	NEW("New Workspace", "addNewWorkspace"),
	OPEN("Open", null),
	SAVE("Save Preferences", "savePreferences");
	
	
	private final String myLabel;
	private final String myCommand;
	
	
	FileMenuComponent(String label, String command){
		myLabel= label;
		myCommand = command;
	}
	
	public String getLabel(){
		return myLabel;
	}
	
	public String getCommand(){
		return myCommand;
	}

}
