package helper;

import org.lwjgl.LWJGLException;

public class Error {
	
	/**
	 * Constructor for Error, log to the console the particular error with formatting
	 * @param errorMessage the error message associated with this Error
	 * @param packageName the package this Error is associated with
	 * @param className the class this Error is associated with
	 * @param methodName the method this Error is associated with
	 * @param exception the LWJGLException object which is used to print the stack trace
	 * */
	public Error(String errorMessage, String packageName, String className, String methodName, LWJGLException e) {
		System.out.println(errorMessage + "\n\n");
		System.out.println("Package: " + packageName + "\nClass: " + className + "\nMethod: " + methodName + "()\n\n");
		System.out.println("*******************STACK TRACE*******************\n\n");
		e.printStackTrace();
	}

}
