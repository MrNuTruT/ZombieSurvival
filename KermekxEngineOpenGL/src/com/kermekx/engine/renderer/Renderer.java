package com.kermekx.engine.renderer;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glCallList;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glFlush;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.awt.Rectangle;

import com.kermekx.engine.camera.Camera;
import com.kermekx.engine.drawable.Drawable;
import com.kermekx.engine.drawable.list.DisplayList;
import com.kermekx.engine.position.Vector;
import com.kermekx.engine.scene.Scene;

public class Renderer {

	/**
	 * Scene � afficher
	 */
	private Scene scene;

	/**
	 * rendue de la scene
	 */
	public void render() {
		if (scene == null)
			return;
		
		Rectangle bounds = scene.getCamera().getBounds();
		scene.getCamera().setViewModel();
		glClear(GL_COLOR_BUFFER_BIT);
		
		for(DisplayList dl : scene.getDisplayLists())
			if(dl.should(bounds))
				glCallList(dl.getListID());

		for (Drawable drawable : scene.getDrawables()) {
			if (drawable.shouldRender(bounds)) {
				float[] color = drawable.getColor();
				glColor3f(color[0], color[1], color[2]);

				int texture = drawable.getTextureId();
				if (texture != -1);
					glBindTexture(GL_TEXTURE_2D, texture);
				int i = 0;
				float angle = drawable.getRotation();
				if (angle != 0) {
					glPushMatrix();
					Vector position = drawable.getPosition();
					glTranslatef(position.getX(), position.getY(), 0);
					glRotatef( angle, 0, 0, 1 );
					glTranslatef(-position.getX(), -position.getY(), 0);
				}
				
				glBegin(GL_TRIANGLES);
				for (Vector vertex : drawable.getVertex()) {
					if (texture != -1 && i % 2 == 0)
						glTexCoord2f(vertex.getX(), vertex.getY());
					else
						glVertex2f(vertex.getX(), vertex.getY());
					i++;
				}
				glEnd();
				if (texture != -1)
					glBindTexture(GL_TEXTURE_2D, 0);
				if (angle != 0)
					glPopMatrix();
			}
		}
		glFlush();
	}

	/**
	 * modifie la scene � afficher (null pour rien afficher)
	 * @param scene scene � afficher
	 */
	public void setScene(Scene scene) {
		if (scene.getCamera() == null)
			scene.setCamera(new Camera());
		this.scene = scene;
	}

	/**
	 * Mise � jour de la scene (�l�ments de la scene)
	 * @param delta temps depuis la derni�re mise � jour
	 */
	public void update(int delta) {
		if(scene != null)
			scene.update(delta);
	}

}
