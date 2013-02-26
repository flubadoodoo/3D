package camera;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;
import helper.Point3D;

public class Camera {
	
	private Point3D position; // the camera's position in 3D space
	private float rotationX; // the camera's x rotation
	private float rotationY; // the camera's y rotation
	private float rotationZ; // the camera's z rotation
	private float fieldOfView; // the camera's field of view
	private float aspectRatio; // the camera's aspect ratio
	private float nearClip; // the camera's near clipping plane distance
	private float farClip; // the camera's far clipping plane distance

	/**
	 * Constructor for a Camera, sets all the parameters and then sets up the camera projection
	 * @param position the starting position of the camera in 3D space
	 * @param fieldOfView the field of view of the camera
	 * @param aspectRatio the aspectRatio of the camera
	 * @param nearClip the distance to the camera's near clipping plane
	 * @param farClip the distance to the camera's far clipping plane
	 * */
	public Camera (Point3D position, float fieldOfView, float aspectRatio, float nearClip, float farClip) {
		// initialize variables
		this.position = position;
		this.fieldOfView = fieldOfView;
		this.aspectRatio = aspectRatio;
		this.nearClip = nearClip;
		this.farClip = farClip;
		// initialize rotation
		rotationX = 0f;
		rotationY = 0f;
		rotationZ = 0f;
		// setup the OpenGL specifics of the camera
		setupCameraProjection();
	}

	/**
	 * Setup the OpenGL specifics of the camera
	 * */
	private void setupCameraProjection() {
		glMatrixMode (GL_PROJECTION); // what
		glLoadIdentity(); // what
		gluPerspective(fieldOfView, aspectRatio, nearClip, farClip); // k
		glMatrixMode(GL_MODELVIEW); // very good
		glEnable(GL_DEPTH_TEST); // Aright
	}
	
	/**
	 * Update the camera's rotation on each axis, as well as move the camera to its position
	 * */
	public void updateCamera() {
		glRotatef(rotationX, 1f, 0f, 0f);
		glRotatef(rotationY, 0f, 1f, 0f);
		glRotatef(rotationZ, 0f, 0f, 1f);
		glTranslatef(position.getX(), position.getY(), position.getZ());
	}
	
	/**
	 * Translate the camera in 3D space on a delta basis
	 * @param displacement the vector by which the camera should be moved
	 * */
	public void move(float distance, int direction) {
		/*
		 * Move the camera in the direction that you want based on the displacement vector. This utilizes the 
		 * properties of the sin and cosine functions. To visualize what is happening, imagine a circle on the
		 * screen, but the y-axis is replaced by a z-axis. The radius of the circle is given by the magnitude of
		 * the displacement parameter. Any point on that circle has the form (magnitude * cosine(Θ), magnitude * sin(Θ)).
		 *  So just get the individual components of that coordinate and add that to the camera's position. The Y
		 *  component however works due to magic.
		 * */
		position.setX(position.getX() + distance * (float) Math.cos(Math.toRadians(rotationY + 90 * direction)));
		position.setZ(position.getZ() + distance * (float) Math.sin(Math.toRadians(rotationY + 90 * direction)));
	}
	
	public void rotateBy(float addedRotationX, float addedRotationY, float addedRotationZ) {
		rotationX += addedRotationX;
		rotationY += addedRotationY;
		rotationZ += addedRotationZ;
	}

	/**
	 * @return the position
	 */
	public Point3D getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Point3D position) {
		this.position = position;
	}
	

	/**
	 * @return the rotationX
	 */
	public float getRotationX() {
		return rotationX;
	}

	/**
	 * @param rotationX the rotationX to set
	 */
	public void setRotationX(float rotationX) {
		this.rotationX = rotationX;
	}

	/**
	 * @return the rotationY
	 */
	public float getRotationY() {
		return rotationY;
	}

	/**
	 * @param rotationY the rotationY to set
	 */
	public void setRotationY(float rotationY) {
		this.rotationY = rotationY;
	}

	/**
	 * @return the rotationZ
	 */
	public float getRotationZ() {
		return rotationZ;
	}

	/**
	 * @param rotationZ the rotationZ to set
	 */
	public void setRotationZ(float rotationZ) {
		this.rotationZ = rotationZ;
	}

	/**
	 * @return the fieldOfView
	 */
	public float getFieldOfView() {
		return fieldOfView;
	}

	/**
	 * @param fieldOfView the fieldOfView to set
	 */
	public void setFieldOfView(float fieldOfView) {
		this.fieldOfView = fieldOfView;
	}

	/**
	 * @return the aspectRatio
	 */
	public float getAspectRatio() {
		return aspectRatio;
	}

	/**
	 * @param aspectRatio the aspectRatio to set
	 */
	public void setAspectRatio(float aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	/**
	 * @return the nearClip
	 */
	public float getNearClip() {
		return nearClip;
	}

	/**
	 * @param nearClip the nearClip to set
	 */
	public void setNearClip(float nearClip) {
		this.nearClip = nearClip;
	}

	/**
	 * @return the farClip
	 */
	public float getFarClip() {
		return farClip;
	}

	/**
	 * @param farClip the farClip to set
	 */
	public void setFarClip(float farClip) {
		this.farClip = farClip;
	}

}
