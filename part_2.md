# Part two: Breakout!
This part of the tutorial will not show you all the code you need to write. It will give you problems you'll need to solve yourself. A big part of gamedev and coding is learning to solve problems yourself!

First of all, we should probably change our ball-craziness to a single ball. Also we should make it a nice size and speed for breakout, I recommend keeping it as a list of a single ball though so it will be easy to add multiball mode later on.

Next, let's fix up the collision so it doesn't go half way into the sides. I recommend getting a pen and paper and trying to figure it out. 

![](https://i.imgur.com/CVIuwJg.jpg)

This skill will be useful for the rest of this tutorial and gamedev in general! I won't be providing the algorithm but you should get it working so it looks like this before continuing.

![](https://im3.ezgif.com/tmp/ezgif-3-a4c72338d0.gif)

Excellent, I hope you enjoyed figuring out that little puzzle because there's more to come!

Now we'll need a new class for the player-controlled bar thingy at the bottom. You can call it what you like but I've called it Bar. We can use the ShapeRenderer to draw a bar for this and set its position each frame to the position of the cursor.
```Java
Gdx.input.getX(); // this returns the x position of the cursor in pixels
```

(add moving bar image)

Right now it's not colliding with the ball so that's a bit useless. We need a method that can detect a collision between a bar and a ball now. This is a bit trickier than the previous method but there are some steps we can take to make it simpler.

but take your time and try to figure it out.