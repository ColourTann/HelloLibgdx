# Part three: Breakout!

Ok, next up is breakable blocks! First up we need a new class for Blocks! Or Bricks if you like. These need to basically just be a bigger rectangle. We need lots of them on the screen. 
```java
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
Here's my block class to get you started. Now we to populate a list of them in the create() method and put them in a list, similar to the balls.
```java
@Override
public void create() {
    ...
    int blockWidth = 63;
    int blockHeight = 20;
    for(int y=Gdx.graphics.getHeight()/2;y<Gdx.graphics.getHeight(); y+=blockHeight+10){
        for(int x=0;x<Gdx.graphics.getWidth();x+=blockWidth+10){
            blocks.add(new Block(x, y, blockWidth, blockHeight));
        }
    }
}
```
This is a bit of a mouthful but we're just iterating in a grid from half way up the screen upwards and across the screen, placing blocks as we go! I picked 63 as the block width just because it divides into the screen width nicely. Now, you just need to render the list of blocks in the render method and we get this:

![](http://tann.space/HelloLibgdx/blocks.gif)

A good start, but some things are still missing. Collision! And destroying blocks! So we need Balls to collide with both Blocks and Bars now. The smartypants amongst you may be thinking we should genericise the collision logic or move the collision logic to a more central place. You'd be right! But I won't tell anyone if you're lazy and just copy collidesWith(Bar bar) method and make a collidesWith(Block block) method ;)

The difference is that we need to destroy the block when the ball hits it. Make a lazy public boolean in the Block class and set it, in addition to reversing ySpeed when the ball hits a block.
```java
public void checkCollision(Block block) {
    if(collidesWith(block)){
        ySpeed = - ySpeed;
        block.destoyed;
    }
}
```            for (int x = centralTileX - 1; x <= centralTileX + 1; x++) {
                for (int y = centralTileY - 1; y <= centralTileY + 1; y++) {
                    // Ignore the central tile
                    if (x == centralTileX && y == centralTileY) {
                        continue;
                    }

                    Cell cell = TileLayer.getCell(x, y);

                    if (cell == null) {
                        continue;
                    }

                    switch (cell.getTile().getId) {
                        case MY_NICE_INT_CONSTANT:
                            return MY_OTHER_NICE_INT_CONSTANT;

                        case MY_OTHER_NICE_INT_CONSTANT:
                            return MY_OTHER_OTHER_NICE_INT_CONSTANT;
                    }
                }
            }

Then hook up all the methods in render() and we'll need to remove the blocks that get destroyed. This can be 


