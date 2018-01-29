package tann.hello;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Main extends ApplicationAdapter {
	ShapeRenderer shape;
	@Override
	public void create () {
		shape = new ShapeRenderer();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.circle(50,50,50);
		shape.end();
	}
}
