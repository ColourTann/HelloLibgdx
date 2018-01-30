package tann.hello;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Bar {
  int x,y,width,height;
  public Bar(){
    this.y = 18;
    this.width = 90;
    this.height = 10;
  }

  public void update(){
      x = Gdx.input.getX() - width/2;
  }


  public void draw(ShapeRenderer shape){
    shape.rect(x, y, width, height);
  }
}
