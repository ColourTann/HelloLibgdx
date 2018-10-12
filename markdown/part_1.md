# LibGDX beginner tutorial
# Part 1: Bouncey circles
Part 1 | [Part 2](http://tann.space/HelloLibgdx/2.html) | [Part 3](http://tann.space/HelloLibgdx/3.html)

Hello and welcome to this tutorial! 

First things first: Are you in the right place? You must be interested in game development to be here but...

- **I have never coded before**. Please go to [processing](http://hello.processing.org/) and close this tab! It's a great introduction to coding with a focus on easy visual stuff!
- **I've made a bunch of games before and want a quick look at what LibGDX offers**. I recommend checking out [the official libgdx tutorial](https://github.com/libgdx/libgdx/wiki/A-simple-game). It's a good introduction to LibGDX but it assumes you understand the process of making a game. 
- **Otherwise** you're in the right place! This tutorial will focus on the fundamentals of gamedev with as little config and annoyance as possible. It will assume you're familiar with the very basics of java objects and classes but not much more.

First, you'll need to get a libgdx project set up. This honestly a bit of a pain and many will fall at this first hurdle. I can't really explain it better than https://github.com/libgdx/libgdx/wiki/Project-Setup-Gradle. I recommend unticking most of the boxes in the setup app so it looks like similar to this though (your paths and names will be different and that's fine):

![](http://tann.space/HelloLibgdx/setup.png)

Now, if you have got everything working, you should have this monstrousity on your screen:
![](http://tann.space/HelloLibgdx/awful.png)

Wow, it's weird that they haven't made this nicer looking isn't it? I wouldn't think about it too much if I were you...


Anyway, open up MyGdxGame.java (or whatever you called the class in core/src/something/something/) and delete it all (except the package declaration at the top) and replace it with this:

```Java
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;

public class MyGdxGame extends ApplicationAdapter {
    ShapeRenderer shape;

    @Override
    public void create () {
        shape = new ShapeRenderer();
    }

    @Override
    public void render () {
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.circle(50, 50, 50);
        shape.end();
    }
}
```
![](http://tann.space/HelloLibgdx/circle.png)
Ok! Let's run through this.
- create() runs once when your game starts.
- a ShapeRenderer is something you can use to draw simple shapes.
- render() runs every frame, which is probably about 60 times per second right now!
- shape.circle(x, y, radius) draws a circle at the specified position.
- In Libgdx: 0,0 is the bottom left. 

We're drawing a circle at 50, 50 with a radius of 50. Because it's drawn from the center, it touches the left and bottom edges of the screen.

Right, it's pretty boring at the moment. Let's get this shape moving!

To do this, we need to draw it at location specified by a variable and change that variable over time.
Add some variables to the top of the class:
```Java
int x = 50;
int y = 50;
```
and draw the circle at the x and y variables we create, along with incrementing the x variable.
```Java
@Override
public void render() {
    x += 5;
    shape.begin(ShapeRenderer.ShapeType.Filled);
    shape.circle(x, y, 50);
    shape.end();
}
```


Fantastic! A moving circle! Well... it's leaving a trail. That's because we need to set the screen back to black at the start of our render method.
```Java
Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
```

This just clears the screen.

![alt text](http://tann.space/HelloLibgdx/moving.gif)

Now we have a circle which moves properly. Except it disappears off the edge. A simple way to fix this is to keep track of its position and start subtracting from x once it gets too far.

We need another variable for this.
```Java
int xSpeed = 5;

public void render(){
    ...
    x += xSpeed;
    if (x > Gdx.graphics.getWidth()) {
        xSpeed = -5;
    }
    if (x < 0) {
        xSpeed = 5;
    }
}
```

Gdx.graphics.getWidth() is the width of the screen in pixels. Once it gets past this, we reverse the direction of the circle by changing the xSpeed variable. Simple enough! Plus we need to remember to reverse it again so it doesn't go off the left side of the screen too.

Ok, bouncing ball, pretty simple. Our logic is getting a bit tangled though and I think it's time to stick it in a class.
```Java
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    
    int x;
    int y;
    int size;
    int xSpeed;
    int ySpeed;

    public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
    public void update() {
        x += xSpeed;
        y += ySpeed;
        if (x < 0 || x > Gdx.graphics.getWidth()) {
            xSpeed = -xSpeed;
        }
        if (y < 0 || y > Gdx.graphics.getHeight()) {
            ySpeed = -ySpeed;
        }
    }
    public void draw(ShapeRenderer shape) {
        shape.circle(x, y, size);
    }
}
```
I added vertical bounces and a definable speed. It's not too complicated but you should read it carefully to ensure you understand it. Instead of checking if the ball goes off the left or right side of the screen, we can simplify by just inverting the speed if either happens.

Now we can just replace the code in our main class with this:
```Java
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;

public class MyGdxGame extends ApplicationAdapter {
    ShapeRenderer shape;
    Ball ball;

    @Override
    public void create() {
        shape = new ShapeRenderer();
        ball = new Ball(150, 200, 70, 12, 5);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ball.update();
        shape.begin(ShapeRenderer.ShapeType.Filled);
        ball.draw(shape);
        shape.end();
    }
}
```

Ahh, much tidier. And we can change a bunch of stuff about the ball very simply from here!

Next step is to go ball-crazy!
Delete the reference to a single ball in Main.java and replace it a list, and add a bit of randomness underneath
```Java
import java.util.ArrayList;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
    ShapeRenderer shape;
    ArrayList<Ball> balls = new ArrayList<>();
    Random r = new Random();
    ...
```

Fill this list with balls in create()! And use our random to generate some random numbers between 0 and the argument we pass in. I've used different random bounds for each argument to keep things a bit sane.
```Java
for (int i = 0; i < 10; i++) {
    balls.add(new Ball(r.nextInt(Gdx.graphics.getWidth()),
            r.nextInt(Gdx.graphics.getHeight()),
            r.nextInt(100), r.nextInt(15), r.nextInt(15)));
}
```

Then in render(), update and draw each ball in turn instead of just doing it to the single ball.
```Java
shape.begin(ShapeRenderer.ShapeType.Filled);
for (Ball ball : balls) {
    ball.update();
    ball.draw(shape);
}
shape.end();
```

![](http://tann.space/HelloLibgdx/bouncing.gif)

Excellent! Now we're getting somewhere. We have some clean code and a cool effect with very little effort. Just drawing circles and a tiny bit of maths. 

What can we turn this into? How about the classic Breakout! We already have bouncing balls and balls bouncing off walls so we're half way there! Unfortunately it's probably the easy half but coding problems are the fun!

[Click here for part 2](http://tann.space/HelloLibgdx/2.html)

