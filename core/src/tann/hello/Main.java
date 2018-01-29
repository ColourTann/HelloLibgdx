package tann.hello;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends ApplicationAdapter {
	ShapeRenderer shape;
    List<Ball> balls = new ArrayList<>();

	@Override
	public void create() {
		shape = new ShapeRenderer();
		for(int i=0;i<10;i++){
		    balls.add(new Ball(random(Gdx.graphics.getWidth()), random(Gdx.graphics.getHeight()), random(100), random(15), random(15)));
        }

	}

	private int random(int max){
	    return (int)(Math.random()*max);
    }

	@Override
	public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        for(Ball ball:balls){
            ball.update();
            ball.draw(shape);
        }
	}

}
