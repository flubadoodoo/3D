package camera;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private Vector3f position; // the camera's position in 3D space
	private Vector3f rotation; // the camera's rotation in 3D space in degrees
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
	public Camera (Vector3f position, float fieldOfView, float aspectRatio, float nearClip, float farClip) {
		// Initialize variables
		this.position = position;
		this.fieldOfView = fieldOfView;
		this.aspectRatio = aspectRatio;
		this.nearClip = nearClip;
		this.farClip = farClip;
		// Setup the OpenGL specifics of the camera
		setupCameraProjection();
	}

	/**
	 * Setup the OpenGL specifics of the camera
	 * */
	private void setupCameraProjection() {
		glMatrixMode (GL_PROJECTION); // what
		glLoadIdentity(); // what
		gluPerspective(fieldOfView, aspectRatio, nearClip, farClip); // k
	}
	
	/**
	 * Update the camera's rotation on each axis, as well as move the camera to its position
	 * */
	public void updateCamera() {
		glRotatef(rotation.getX(), 1f, 0f, 0f);
		glRotatef(rotation.getY(), 0f, 1f, 0f);
		glRotatef(rotation.getZ(), 0f, 0f, 1f);
		glTranslatef(position.getX(), position.getY(), position.getZ());
	}
	
	/**
	 * Translate the camera in 3D space on a delta basis
	 * @param displacement the vector by which the camera should be moved
	 * */
	public void move(Vector3f displacement) {
		/*
		 * Move the camera in the direction that you want based on the displacement vector. This utilizes the 
		 * properties of the sin and cosine functions. To visualize what is happening, imagine a circle on the
		 * screen, but the y-axis is replaced by a z-axis. The radius of the circle is given by the magnitude of
		 * the displacement parameter. Any point on that circle has the form (magnitude * cosine(Θ), magnitude * sin(Θ)).
		 *  So just get the individual components of that coordinate and add that to the camera's position. The Z
		 *  component however works due to magic.
		 * */
		position.setX(position.getX() + displacement.getX() * (float) Math.cos(Math.toRadians(rotation.getY() + 90)));
		position.setY(position.getY() + displacement.getY() * (float) Math.sin(Math.toRadians(rotation.getY() + 90)));
		position.setZ(position.getZ() + displacement.getZ() * (float) Math.sin(Math.toRadians(rotation.getY() + 90)));
	}
	
	public void rotateBy(Vector3f addedRotation) {
		rotation.set(rotation.getX() + addedRotation.getX(), rotation.getY() + addedRotation.getY(), rotation.getZ() + addedRotation.getZ());
	}

	/**
	 * @return the position
	 */
	public Vector3f getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Vector3f position) {
		this.position = position;
	}

	/**
	 * @return the rotation
	 */
	public Vector3f getRotation() {
		return rotation;
	}

	/**
	 * @param rotation the rotation to set
	 */
	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
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
