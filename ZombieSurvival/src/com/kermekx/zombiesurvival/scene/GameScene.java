package com.kermekx.zombiesurvival.scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.kermekx.engine.drawable.Drawable;
import com.kermekx.engine.keyboard.Key;
import com.kermekx.engine.scene.Scene;
import com.kermekx.engine.texture.LoadableTexturePack;
import com.kermekx.engine.texture.TextureLoader;
import com.kermekx.engine.texture.TextureManager;
import com.kermekx.zombiesurvival.entity.Bullet;
import com.kermekx.zombiesurvival.entity.Entity;
import com.kermekx.zombiesurvival.entity.Player;
import com.kermekx.zombiesurvival.ia.LookAt;
import com.kermekx.zombiesurvival.terrain.World;
import com.kermekx.zombiesurvival.texture.ItemTextures;
import com.kermekx.zombiesurvival.texture.PlayerTextures;
import com.kermekx.zombiesurvival.texture.TerrainTextures;

public class GameScene extends Scene {

	private Player player;
	private int lastFire;

	private List<Entity> entities = new ArrayList<Entity>();

	public GameScene() {
		TextureLoader.loadTexture(new LoadableTexturePack[] { ItemTextures.GLOCK, TerrainTextures.DIRT,
				PlayerTextures.PLAYER_FEET_IDLE });
		World world = new World(0, 0, 3, 3);
		addDisplayList(world.getDisplayList());

		player = new Player(this, 0, 0, "Kermekx");
		entities.add(player);
		Player p = new Player(this, 500, 0, "MrNuTruT");
		entities.add(p);
		addDrawable(p.getDrawables());
		p.addIA(new LookAt(p, player));
		for (Drawable d : player.getDrawables())
			addDrawable(d);

		try {
			TextureManager.getTexture("assets/misc/bullet/bullet.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Entity> getEntities() {
		return entities;
	}

	@Override
	public void update(int delta) {
		super.update(delta);

		List<Entity> deadEntities = new ArrayList<Entity>();
		for (Entity entity : entities) {
			if (entity.isAlive())
				entity.update(delta);
			else {
				for (Drawable drawable : entity.getDrawables())
					getDrawables().remove(drawable);
				deadEntities.add(entity);
			}
		}
		entities.removeAll(deadEntities);

		if (lastFire > 0) {
			lastFire -= delta;
		}

		if (lastFire <= 0 && keyPressed(Key.KEY_ENTER)) {
			player.fire();
			lastFire = 150;

			Bullet b = new Bullet(this, player.getPosition(), player.getRotation());
			getDrawables().addAll(b.getDrawables());
			entities.add(b);
		}

		if (keyPressed(Key.KEY_Q))
			player.rotate(-delta);
		if (keyPressed(Key.KEY_D))
			player.rotate(delta);

		if (lastFire <= 0) {
			if (keyPressed(Key.KEY_Z))
				player.walk(delta);
			if (keyPressed(Key.KEY_S))
				player.walk(-delta);
		}
	}
}
