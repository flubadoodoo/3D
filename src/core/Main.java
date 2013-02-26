package core;

import camera.Camera;

public class Main {
	
	private static final int WIDTH;
	private static final int HEIGHT;
	
	private Camera camera;
	
	static {
		WIDTH = 1280;
		HEIGHT = 720;
	}
	
	public Main() {
		init();
		mainLoop();
		quitProgram();
	}

	private void init() {
		// init display
		// init gl
		// init vars
	}

	private void mainLoop() {
		// update scene
		// render scene
		// get input
		// update display
		// sync display
	}
	
	private void quitProgram() {
		// destroy display
		// System.exit(0);
	}

}
