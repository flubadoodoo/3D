package camera;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private Vector3f position;
	private Vector3f rotation;
	private float fieldOfView;
	private float aspectRatio;
	private float nearClip;
	private float farClip;

	public Camera (Vector3f position, float fieldOfView, float aspectRatio, float nearClip, float farClip) {
		this.position = position;
		this.fieldOfView = fieldOfView;
		this.aspectRatio = aspectRatio;
		this.nearClip = nearClip;
		this.farClip = farClip;
		setupCameraProjection();
	}

	private void setupCameraProjection() {
		glMatrixMode (GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(fieldOfView, aspectRatio, nearClip, farClip);
	}
	
	public void updateCamera() {
		glRotatef(rotation.getX(), 1f, 0f, 0f);
		glRotatef(rotation.getY(), 0f, 1f, 0f);
		glRotatef(rotation.getZ(), 0f, 0f, 1f);
		glTranslatef(position.getX(), position.getY(), position.getZ());
	}
	
	// direction of 0 = move in X
	// direction of 1 = move in Z
	public void move(float distance, float direction) {
		position.setX(position.getX() + distance * (float) Math.cos(Math.toRadians(rotation.getY() + 90 * direction)));
		position.setZ(position.getZ() + distance * (float) Math.sin(Math.toRadians(rotation.getY() + 90 * direction)));
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}
	
	public void rotateBy(Vector3f addedRotation) {
		rotation.set(rotation.getX() + addedRotation.getX(), rotation.getY() + addedRotation.getY(), rotation.getZ() + addedRotation.getZ());
	}

	public float getFieldOfView() {
		return fieldOfView;
	}

	public void setFieldOfView(float fieldOfView) {
		this.fieldOfView = fieldOfView;
	}

	public float getAspectRatio() {
		return aspectRatio;
	}

	public void setAspectRatio(float aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	public float getNearClip() {
		return nearClip;
	}

	public void setNearClip(float nearClip) {
		this.nearClip = nearClip;
	}

	public float getFarClip() {
		return farClip;
	}

	public void setFarClip(float farClip) {
		this.farClip = farClip;
	}

}
