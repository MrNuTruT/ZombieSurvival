package com.kermekx.zombiesurvival.entity;

import java.io.IOException;

import com.kermekx.engine.drawable.Drawable;
import com.kermekx.engine.drawable.SwitchableAnimatedRectangle2D;
import com.kermekx.engine.position.Vector;
import com.kermekx.engine.scene.Scene;
import com.kermekx.engine.texture.TextureManager;

public class Player extends Entity {

	public static int LIFE = 100;

	public Player(Scene context, int x, int y) {
		super(context, new Vector(x, y), new Vector(85, 55), LIFE);
		int[][] texturesPlayer = new int[3][20];
		int[][] texturesPlayerFeet = new int[2][20];
		for (int i = 0; i < 20; i++)
			try {
				texturesPlayerFeet[0][i] = TextureManager
						.getTexture("assets/person/player/feet/idle/survivor-idle_0.png");
				texturesPlayer[0][i] = TextureManager
						.getTexture("assets/person/player/rifle/idle/survivor-idle_rifle_" + i + ".png");
				texturesPlayerFeet[1][i] = TextureManager
						.getTexture("assets/person/player/feet/run/survivor-run_" + i + ".png");
				texturesPlayer[1][i] = TextureManager
						.getTexture("assets/person/player/rifle/move/survivor-move_rifle_" + i + ".png");
				texturesPlayer[2][i] = TextureManager
						.getTexture("assets/person/player/rifle/shoot/survivor-shoot_rifle_" + i % 3 + ".png");
			} catch (IOException e) {
				e.printStackTrace();
			}

		addDrawable(new SwitchableAnimatedRectangle2D(x, y, 102, 62, texturesPlayerFeet));
		addDrawable(new SwitchableAnimatedRectangle2D(x, y, 126, 107, texturesPlayer));
	}

	public void walk(boolean walk) {
		if (walk)
			for (Drawable d : getDrawables()) {
				if (d instanceof SwitchableAnimatedRectangle2D)
					((SwitchableAnimatedRectangle2D) d).setTextureGroupe(1);
			}
		else
			for (Drawable d : getDrawables()) {
				if (d instanceof SwitchableAnimatedRectangle2D)
					((SwitchableAnimatedRectangle2D) d).setTextureGroupe(0);
			}
	}

	public void fire() {
		// .get(0) == hitbox
		((SwitchableAnimatedRectangle2D) getDrawables().get(1)).setTextureGroupe(0);
		((SwitchableAnimatedRectangle2D) getDrawables().get(2)).setTextureGroupe(2);
	}
}
