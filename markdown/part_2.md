# Part two: Almost Breakout!
[Part 1](http://tann.space/HelloLibgdx) | Part 2 | [Part 3](http://tann.space/HelloLibgdx/3.html)

This part of the tutorial will not show you all the code you need to write. It will give you problems you'll need to solve yourself. A big part of gamedev and coding is learning to solve problems yourself!

First of all, we should probably change our ball-craziness back to a single ball. Also we should make it a nice size and speed for breakout. I'll leave that up to you.

Next, let's fix up the collision so it doesn't go half way into the sides. I recommend getting a pen and paper and trying to figure it out. 

![](http://tann.space/HelloLibgdx/calc.jpg)

This skill will be useful for the rest of this tutorial and gamedev in general! I won't be providing the algorithm but you should get it working so it looks like this before continuing.

![](http://tann.space/HelloLibgdx/wall.gif)

I hope you enjoyed figuring out that little puzzle because there's more to come!

Now we'll need a new class for the player-controlled paddle thingy at the bottom. You can call it what you like but I've called it Paddle. We can use the ShapeRenderer to draw a paddle for this and set its position each frame to the position of the cursor.
```Java
Gdx.input.getX(); // this returns the x position of the cursor in pixels
```

![](http://tann.space/HelloLibgdx/bar.gif)

Right now it's not colliding with the ball so that's a bit useless. We need a method that can detect a collision between a paddle and a ball now. This is a bit trickier than the previous method but there are some steps we can take to make it simpler.

It will be rubbish to wait for the ball to hit the paddle each time when testing our method, plus it would be tricky to check the sides and bottom of the paddle anyway. A good idea is to make the ball still in the middle of the screen and make it so the paddle follows the y cursor position also! 

![](http://tann.space/HelloLibgdx/invert.gif)

Oh... the first time you try it, this will probably happen. It turns out the cursor position returned by Gdx.input.getY() is counted from the top of the screen while drawing the paddle is from the bottom. That sucks. There are a few gotchas like this in LibGDX and a few more in Java itself so you should probably get used to them! Try to figure this one out yourself!

Back to the collisions! Next step is to make a function that detects if the ball is colliding with the paddle. I'll share the code for this precursor step because doing it in this way makes it easier later on.

```Java
public class Ball {
    int x;
    int y;
    int size;
    int xSpeed;
    int ySpeed;
    Color color = Color.WHITE;
    ....
    public void draw(ShapeRenderer shape){
        shape.setColor(color);
        shape.circle(x, y, size);
    }
    public void checkCollision(Paddle paddle) {
        if(collidesWith(paddle)){
            color = Color.GREEN;
        }
        else{
            color = Color.WHITE;
        }
    }
    private boolean collidesWith(Paddle paddle) {
       return Math.random()>.5;
    }
}
```

Colors! Ok, we can set the color of a shaperenderer that does what you might expect. We've created a checkCollision() method in the ball class which you will need to call from the render() method and pass the paddle into. Then, based on the results on the collidesWith() function, it will change color. Currently it's a random function so you should see the ball flash between white and green.

It's now time to get your pen and paper out again because you need to make that function return true if the ball and paddle are overlapping. Remember that the paddle is drawn from the bottom-left and the ball is drawn from the middle. If you get it right, it should look something like this.

![](http://tann.space/HelloLibgdx/detected.gif)

You may notice that my one does not deal with corners perfectly. That's fine, it's good enough to work with for now! It will be easy to make it better later on if we need to.

Lock the y of the ball back to near the bottom of the screen, get the ball moving and reverse its ySpeed (instead of changing its color) whenever it hits the paddle and, if you squint a bit, we have a game on our hands!

![](http://tann.space/HelloLibgdx/ballbar.gif)

Well done for getting this far! In the next part, we'll be adding breakable blocks! Wow!

[Part 3](http://tann.space/HelloLibgdx/3.html)