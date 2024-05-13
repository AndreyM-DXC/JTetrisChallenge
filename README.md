# Java Coding Challenge: Let's play Tetris!

Let's take a break from the routine and play some old classics game.

The only thing is that we will not play ourselves, but our bots will play.
```
[][]   []     [][][] [][] [][][]   [][]
  [][] [][][]   []   [][]     [] [][]  
```

**Task:** Win the bot tournament!

**Rules:**
1. Tournament consists of 100 random rounds.
1. Sequence of Tetris pieces in each round will be the same for all bots.
1. Bot with the most sum of score points in the tournament wins.

**Scoring:**
* No lines cleared - 1 point
* Single line clear - 2 points
* Clearing two lines at once - 4 points
* Clearing three lines at once - 7 points
* Clearing four lines at once - 11 points

**How to play:**

You can find the source code of Tetris game on GitHub:
[https://github.com/AndreyM-DXC/JTetrisChallenge](https://github.com/AndreyM-DXC/JTetrisChallenge)

It contains all the game logics and two sample bots:
* `RandomPlayer` - minimal bot example.

**Goal:**

Implement the `IPlayer` interface so that the bot can play on its own and finish the game in reasonable time.

Feel free to use external libraries, but keep in mind that bot should be simple to assemble. 🤓

Please, share your solution with us via the mail (You can either attach an archive with the source code or share a link to public GitHub repository).

```
    .       .       . 
  .   .   .   .   .  
# . . . . . . . . . .#
#[] . .[] . . . . . .#
#[] . .[] . .[][] .[]#
#[][] .[][][][][][][]#
######################  Score: 1337
```