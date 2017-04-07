# Game Of Life
[Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life), is a cellular automaton developed by John Conway in 1970.  The game, played on a square lattice, has 4 basic rules:

> * Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
> * Any live cell with two or three live neighbours lives on to the next generation.
> * Any live cell with more than three live neighbours dies, as if by overpopulation.
> * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

(Quoted from Wikipedia)

More details about the base simulation can be found at the link above.

# Game Of Life Combat Simulation
First I implemented the Game Of Life complete with a Graphical User Interface (GUI) to get a basis for the underlaying data structures and to have a basis for which to base my modifications on.  An example of the GUI can be seen here:

![Game of Life GUI example](http://i.imgur.com/OT4i5lv.png)
*Black squares denote alive cells and white ones denote dead cells.*

The data structure I use is a Bit Set, part of the Java API, which works like a 1-D boolean array.  This is a generalization but the explanation works well enough for this project.  The alive cells are true and the dead cells are false.  More documentation for Bit Sets can be found [here](https://docs.oracle.com/javase/7/docs/api/java/util/BitSet.html)

The GUI, pictured above, has the functionality of allowing the user to place what are called Oscillators, using the JButtons displayed on the right side.  More information on these can be found on the Game of Life link above.  In addition, the GUI gives the user the ability to start, stop, and reset the simulation using the JButtons located around the outside.

From there I implemented two "AI" players that start on opposite sides of the board and whose goal is to destory the other's core, simulated as an oscillator known as a beacon.  An example of these can be seen in this picture here:

![Game of Life GUI example 2](http://i.imgur.com/fYq1oie.png)
*The beacons are the two furthest shapes, on the farthest outside of the board.*

To accomplish this goal the AI's fire a pattern known as a Spaceship,which can be seen as the two central shapes, which have the ability to move across the board using the 4 rules defined above.  More information on these in the link above.  

The AI will only fire these spaceships if no shape is in contact with their "danger lines".  The danger lines can be seen above as the colour lines of blue or red.  If there are shapes in contact with the danger line, then they will place a 2x2 cube at the point of contact to, hopefully, diffuse the situation.  This simulation runs until one AI has been terminated.

I say "AI" because due to the complexity of the Game of Life, I could not efficiently have an AI predict what will happen when one shape comes into contact with another.  In order to develop a some-what efficient AI I would have needed more time than was given to me.  The AI implemented here are more reactionary than anything and don't "plan ahead".

The architecture of the Java classes has been represented in this flow chart:

![Game of Life Architecture](http://i.imgur.com/oSCyxZj.png)

A full architecture and specification document can be found [here](https://docs.google.com/document/d/1nEJAF2Lxzvt0OO2f8BLdcYJglFEIvV6CWUI_S4EPzoM/edit?usp=sharing).
