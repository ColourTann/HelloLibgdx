package tann.hello;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends ApplicationAdapter {
    ShapeRenderer shape;
    Bar bar = new Bar();
    List<Ball> balls = new ArrayList<>();
    Random r = new Random();
	@Override
	public void create() {
		shape = new ShapeRenderer();
		for(int i=0;i<1;i++){
		    balls.add(new Ball(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 20, 4, 9));
    }

	}

	@Override
	public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeType.Filled);
        bar.update();
        bar.draw(shape);
        for(Ball ball:balls){
            ball.checkCollision(bar);
            ball.update();
            ball.draw(shape);
        }
        shape.end();
	}

}
