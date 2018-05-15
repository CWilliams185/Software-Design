# Software-Design
Four projects created for a software design course in Spring 2018. I had a partner for all four assignments,
and none of them could have been completed without him.

1. Mastermind
    A program that replicates the Mastermind board game. The computer chooses 6 colors, and the user has 20
    turns to correctly guess the colors and their order. Implements GUI to create the board (image sample is
    included in the folder "assign1").
 
2. Unscramble
    A simple word game in which the computer scrambles a word, and the user has infinite attemps to guess the
    original word. Utilizes the console, and has a dependency on a website. Dependencies are tested out by
    utilizing mock objects in automated tests.
 
3. Fibonacci
    A program that uses 3 different methods for computing the fibonacci sequence. Exhibits DRY principle by not
    having duplicate code where possible. Proves that the third method is more efficient than the second method
    via automated testing.
  
4. Converters
    A program that possesses many different blocks that convert specified elements of an input String.
    Examples include "capitalize all z's", or "make all letters lowercase". Avoids duplicate code by using base
    classes / interfaces. Allows for third-party blocks to be read and implemented as well (Instructor used
    contents of "test_integration" folder for this purpose).
