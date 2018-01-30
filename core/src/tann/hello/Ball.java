package tann.hello;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.Random;
import java.util.Spliterator;

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
        if(x-size<0 || x+size> Gdx.graphics.getWidth()){
            xSpeed = -xSpeed;
        }
        if(y-size<0 || y+size> Gdx.graphics.getHeight()){
            ySpeed = -ySpeed;
        }
    }

    public void draw(ShapeRenderer shape){
        shape.circle(x, y, size);
    }

    public boolean collidesWith(Bar bar) {
        if (y - size < bar.y + bar.height && (x - size < bar.x + bar.width && x + size > bar.x)){
            return true;
        }
        return false;
    }

    public void checkCollision(Bar bar) {
        if(collidesWith(bar)){
            // maybe do setting a colour first!
        }
    }
}
