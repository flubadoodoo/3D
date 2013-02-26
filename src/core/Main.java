package core;

import helper.Error;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector3f;

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
		initDisplay();
		initGL();
		// init vars
	}

	private void initDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(Main.WIDTH, Main.HEIGHT));
			Display.create();
			Display.setFullscreen(false);
		} catch (LWJGLException e) {
			new Error("COULD NOT INITIALIZE DISPLAY", "core", "Main", "initDisplay", e);
		}
	}

	private void initGL() {
		camera = new Camera(new Vector3f(0, 0, -10), 70.0f, (float) Main.WIDTH / Main.HEIGHT, 0.3f, 1000.0f);
		Mouse.setGrabbed(true);
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
