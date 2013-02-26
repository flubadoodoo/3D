package core;

import helper.Clock;
import helper.Error;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.Vector3f;

import camera.Camera;

public class Main {
	
	private static final int WIDTH; // width of the display
	private static final int HEIGHT; // height of the display
	private static final int TARGET_FRAME_RATE; // the target frame rate of the program
	private static final float MOVE_SPEED; // the speed at which the player (really the camera) moves
	
	private long lastFrameSystemTime; // the last frame's time according to the system	
	
	private Camera camera; // a class to encapsulate all camera functionality
	
	/**
	 * Initialize static variables that need to be initialized before the constructor
	 * */
	static {
		// initialize width and height
		WIDTH = 1280;
		HEIGHT = 720;
		TARGET_FRAME_RATE = 60;
		MOVE_SPEED = 0.01f;
	}
	
	/**
	 * Constructor creates display for program to run
	 * */
	public Main() {
		init(); // initialize all variables (including GL related stuff)
		mainLoop(); // main loop of program
		quitProgram(); // when program is about to close, clean up
	}

	/**
	 * Initializes the display, any OpenGL variables as well as program specific variables
	 * */
	private void init() {
		initDisplay();
		initGL();
		initVars();
	}

	/**
	 * Initializes the display, sets its width, height and full screen mode
	 * @exception LWJGLException if display cannot be initialized properly
	 * */
	private void initDisplay() {
		try {
			// Set the DisplayMode according to WIDTH and HEIGHT
			Display.setDisplayMode(new DisplayMode(Main.WIDTH, Main.HEIGHT)); // Set the display's dimensions to WIDTH x HEIGHT
			Display.create(); // Create the display
			Display.setFullscreen(false); // We do not want full screen
		} catch (LWJGLException e) { // Throws LWJGLException
			// Create an Error object and print to the console if display cannot be initialized
			new Error("COULD NOT INITIALIZE DISPLAY", "core", "Main", "initDisplay", e);
		}
	}

	/**
	 * Initialize the camera and grab the mouse
	 * */
	private void initGL() {
		camera = new Camera(new Vector3f(0, 0, -10), 70.0f, (float) Main.WIDTH / (float) Main.HEIGHT, 0.3f, 1000.0f);
		Mouse.setGrabbed(true);
	}
	
	/**
	 * Initialize variables (both GL and program specific)
	 * */
	private void initVars() {
		initGLVars();
	}
	
	/**
	 * Initialize OpenGL variables
	 * */
	private void initGLVars() {
		lastFrameSystemTime = Clock.getTime();
	}

	/**
	 * The main loop of the program
	 * */
	private void mainLoop() {
		while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			pollInput(getDelta());
			// update scene
			renderScene();
			Display.update();
			Display.sync(TARGET_FRAME_RATE);
		}
	}
	
	/**
	 * Poll the input from the keyboard and the mouse, and move the camera accordingly
	 * @param delta the delta value, multiply with every movement
	 * */
	private void pollInput(int delta) {
		float distance = MOVE_SPEED * delta;
		float xDistance = (Keyboard.isKeyDown(Keyboard.KEY_A) != Keyboard.isKeyDown(Keyboard.KEY_D) ? (Keyboard.isKeyDown(Keyboard.KEY_A)) ? -distance : distance : 0f);
		float yDistance = (Keyboard.isKeyDown(Keyboard.KEY_W) != Keyboard.isKeyDown(Keyboard.KEY_S) ? (Keyboard.isKeyDown(Keyboard.KEY_W)) ? -distance : distance : 0f);
		camera.move(new Vector3f(xDistance, 0f, yDistance));
	}
	
	/**
	 * Renders the scene
	 * */
	private void renderScene() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
		camera.updateCamera();
		glBegin (GL_QUADS);
		{
			// FrontFace
			glColor3f (1f, 0f, 0f);
			glVertex3f (-1, -1, 1);
			glVertex3f (1, -1, 1);
			glVertex3f (1, 1, 1);
			glVertex3f (-1, 1, 1);

			// BackFace
			glColor3f (0f, 1f, 0f);
			glVertex3f (-1, -1, -1);
			glVertex3f (-1, 1, -1);
			glVertex3f (1, 1, -1);
			glVertex3f (1, -1, -1);

			// BottomFace
			glColor3f (0f, 0f, 1f);
			glVertex3f (-1, -1, -1);
			glVertex3f (-1, -1, 1);
			glVertex3f (-1, 1, 1);
			glVertex3f (-1, 1, -1);

			// TopFace
			glColor3f (1f, 1f, 0f);
			glVertex3f (1, -1, -1);
			glVertex3f (1, -1, 1);
			glVertex3f (1, 1, 1);
			glVertex3f (1, 1, -1);

			// LeftFace
			glColor3f (0f, 1f, 1f);
			glVertex3f (-1, -1, -1);
			glVertex3f (1, -1, -1);
			glVertex3f (1, -1, 1);
			glVertex3f (-1, -1, 1);

			// Right Face
			glColor3f (1f, 0f, 1f);
			glVertex3f (-1, 1, -1);
			glVertex3f (1, 1, -1);
			glVertex3f (1, 1, 1);
			glVertex3f (-1, 1, 1);
		}
		glEnd ();
	}
	
	/**
	 * Program is about to quit, destroy the display and exit with return value 0
	 * */
	private void quitProgram() {
		Display.destroy();
		System.exit(0);
	}
	
	/**
	 * Get the time that has passed since the last frame
	 * @return the time that has passed since the last frame
	 * */
	private int getDelta() {
		long currentSystemTime = Clock.getTime();
		int deltaTime = (int) (currentSystemTime - lastFrameSystemTime);
		lastFrameSystemTime = currentSystemTime;
		return deltaTime;
	}

}
