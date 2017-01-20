Ultimate Tic-Tac-Toe
====================

Welcome to Ultimate Tic-Tac-Toe! The rules are as follows:

There is a tic-tac-toe grid, and each space contains another tic-tac-toe grid, as follows:

	 | | # | | # | |
     | | # | | # | |
     | | # | | # | |
    #################
     | | # | | # | |
     | | # | | # | |
     | | # | | # | |
    #################
     | | # | | # | |
     | | # | | # | |
     | | # | | # | |

X starts in the center. Wherever X moves in the center determines in which grid O moves. The first two moves might look like this:

	 | | # | | # | |
     | |O# | | # | |
     | | # | | # | |
    #################
     | | #X| | # | |
     | | # | | # | |
     | | # | | # | |
    #################
     | | # | | # | |
     | | # | | # | |
     | | # | | # | |

The same rule applies the other way around. So as O moved in the middle-right location, X's next move will be in the middle-right grid. When a grid is won, it will be filled with the winning player's character for ease of viewing, so theoretically a game could start like this:

	 | | # | | # | |
     |O| # | | # | |
     | | # | | # | |
    #################
     | | #X|X|X# | |
     | | #X|X|X# | |
     | | #X|X|X# | |
    #################
     | | # | | # | |
     | | # | | # |O|
     | | # | | # | |

 - X moved in the upper left of the center grid
 - O moved in the center of the upper left grid
 - X moved in the lower right of the center grid
 - O moved in the center of the lower right grid
 - X moved in the center of the center grid, winning the grid

The objective is to win three tic-tac-toe games in a row, winning the outer game.

Controls
--------

There are two control modes - Numpad and Logical.

 - Logical: A very logical control method. Numbers correspond to a location as follows:

    1|2|3
    4|5|6
    7|8|9

 - Numpad: A control method designed for numpads. The numpad maps directly to the grid, so it looks like this:

    7|8|9
    4|5|6
    1|2|3

You choose which method to use when you first start the game.
