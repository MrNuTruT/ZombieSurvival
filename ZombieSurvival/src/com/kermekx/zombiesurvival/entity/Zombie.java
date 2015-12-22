package com.kermekx.zombiesurvival.entity;

import java.io.IOException;

import com.kermekx.engine.drawable.SwitchableAnimatedRectangle2D;
import com.kermekx.engine.position.Vector;
import com.kermekx.engine.scene.Scene;
import com.kermekx.engine.texture.TextureManager;

public class Zombie extends Entity {

	
	public static int LIFE = 200;
	public static float MOVEMENT_SPEED = 0.3f;
	public static float MOVEMENT_WALK = 0.1f;

	public Zombie(Scene context, int x, int y) {
		super(context, new Vector(x, y), new Vector(50, 50), LIFE);
		int textureZombie[][] = new int[1][8];
		for (int i = 0; i < 8; i++) {
			try {
				textureZombie[0][i] = TextureManager.getTexture("assets/person/zombie/walk/zombie-walk-" + i + ".png");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		addDrawable(new SwitchableAnimatedRectangle2D(x, y, 64f, 64f, textureZombie));
	}

}
