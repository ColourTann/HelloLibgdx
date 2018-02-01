# Part three: Breakout!
[Part 1](http://tann.space/HelloLibgdx) | [Part 2](http://tann.space/HelloLibgdx/2.html) | Part 3

Ok, next up is breakable blocks! First up we need a new class for Blocks! Or Bricks if you like. These just need to be similar to the Paddle class fow now, except we need lots of them on the screen. 
```java
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Block {
    int x,y,width,height;
    public Block(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public void draw(ShapeRenderer shape){
        shape.rect(x, y, width, height);
    }
}
```
Here's my block class to get you started. Now we should populate a list of them in the create() method and put them in a list, similar to the balls.
```java
ArrayList<Block> blocks = new ArrayList<>();
@Override
public void create() {
    ...
    int blockWidth = 63;
    int blockHeight = 20;
    for (int y = Gdx.graphics.getHeight()/2; y < Gdx.graphics.getHeight(); y += blockHeight + 10) {
        for (int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
            blocks.add(new Block(x, y, blockWidth, blockHeight));
        }
    }
}
```
This is a bit of a mouthful but we're just iterating in a grid from half way up the screen upwards and across the screen, placing blocks as we go! I picked 63 as the block width just because it divides into the screen width nicely. Now, you just need to render the list of blocks (like we did with the list of balls) in the render method and we get this:

![](http://tann.space/HelloLibgdx/blocks.gif)

A good start, but some things are still missing. Collision! And destroying blocks! So we need balls to collide with both blocks and paddles now. The smartypants amongst you may be thinking we should genericise the collision logic or move the collision logic to a more central place. You'd be right! But I won't tell anyone if you're lazy and just copy collidesWith(Paddle paddle) method and make a collidesWith(Block block) method ;)

The difference is that we need to destroy the block when the ball hits it. Make a lazy public boolean in the Block class and set it, in addition to reversing ySpeed when the ball hits a block.
```java
public void checkCollision(Block block) {
    if(collidesWith(block)){
        ySpeed = - ySpeed;
        block.destroyed = true;
    }
}
```
Then hook up all the methods in the render() loop. We'll need to pass in each block to the ball.checkCollision method and this can be done just after rendering them. We'll also need to remove the blocks that get destroyed after checking the collisions. This is trickier than you might expect so I'll show you how I've done it.

```java
public void render() {
...
    for (Block b : blocks) {
        b.draw(shape);
        ball.checkCollision(b);
    }
    for (int i = 0; i < blocks.size(); i++) {
        Block b = blocks.get(i);
        if (b.destroyed) {
            blocks.remove(b);
            // we need to decrement i when a ball gets removed, otherwise we skip a ball!
            i--;
        }
    }
}
```

![](http://tann.space/HelloLibgdx/final.gif)

If everything is working you should have something like this! Well done! There is obviously a lot missing from this game but I'm too lazy to explain it all in detail. It's time you spread your wings and probably google a bunch of stuff!

Some pointers though:
- The collision is a bit wonky because we're just inverting the ySpeed when it hits. What about when it hits the side of a block? How can you detect that?
- Give the players more control over the ball by making the bounce direction change based on where it hits the Paddle!
- Add some colour and sounds!
- Add levels and victory/loss conditions! This is can be a bit tricky.
- Juice it up! [Watch this video to find out what I mean!](https://www.youtube.com/watch?v=Fy0aCDmgnxg)

You may also want to rush head first into your own game. Luckily, most gamedev skills are very transferrable so you will be able to use some of the things you learnt here. There's a lot more stuff in LibGDX that's good to learn. You don't need to know it all but here's some good foundational stuff:

- [SpriteBatch](https://github.com/libgdx/libgdx/wiki/Spritebatch%2C-Textureregions%2C-and-Sprites): like a ShapeRenderer but for images!
- [Simple](https://github.com/libgdx/libgdx/wiki/Input-handling) and [more advanced](https://github.com/libgdx/libgdx/wiki/Event-handling) input handline for key presses and clicks.
- Basically just check out the [wiki](https://github.com/libgdx/libgdx/wiki/The-application-framework) haha. It's a bit dense in places but a lot better than most code documentation.

Anyway, thankyou for sitting through this tutorial and I wish you the best of luck! Like, subscribe, leave a rating, [join my network of professional contacts on linkedin](https://twitter.com/colourtann) etc.

