package tann.hello;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;
import java.util.Spliterator;

public class Ball {
    int x;
    int y;
    int size;
    int xSpeed;
    int ySpeed;
    Color color = Color.WHITE;

    public Ball(int x, int y, int size, int xSpeed, int ySpeed){
        this.x=x;
        this.y=y;
        this.size=size;
        this.xSpeed=xSpeed;
        this.ySpeed=ySpeed;
    }

    public void update(){
        x += xSpeed;
        y += ySpeed;
        if(x-size<0 || x+size> Gdx.graphics.getWidth()){
            xSpeed = -xSpeed;
        }
        if(y-size<0 || y+size> Gdx.graphics.getHeight()){
            ySpeed = -ySpeed;
        }
    }

    public void draw(ShapeRenderer shape){
        shape.setColor(color);
        shape.circle(x, y, size);
    }

    public void checkCollision(Bar bar) {
        if(collidesWith(bar)){
            ySpeed = - ySpeed;
        }
    }

    private boolean collidesWith(Bar bar) {
        if(x + size < bar.x || x-size > bar.x+bar.width || y + size < bar.y || y-size > bar.y + bar.height){
            return false;
        }
        return true;
    }
}
