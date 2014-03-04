package model;
public class Function {

	private String myFunctionName;
	private String myContent;

	public Function(String name, String content) {
		myContent = content;
		myFunctionName = name;
	}

	public String getContent() {
		return myContent;
	}

	public void setContent(String string) {
		myContent = string;
	}

	public String getFunctionName() {
		return myFunctionName;
	}
}
