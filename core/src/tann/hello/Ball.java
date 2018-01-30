package tann.hello;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    int x;
    int y;
    int size;
    int xSpeed;
    int ySpeed;

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
        if(x<0 || x> Gdx.graphics.getWidth()){
            xSpeed = -xSpeed;
        }
        if(y<0 || y> Gdx.graphics.getHeight()){
            ySpeed = -ySpeed;
        }
    }

    public void draw(ShapeRenderer shape){
        shape.circle(x, y, size);
    }
}
