# Go
A Java implementation of the board-game "Go".
## What
This Project is a implementation of the board-game "Go", written in Java.

It show several concepts of software engineering.

* Layered architecture
* components and interfaces


## Why
The project was built in the context of the lecture "Software Engineering" at the University of Applied Science Konstanz, Germany.

#Documentation
See the current [Javadoc](http://michaelknoch.de/sego/doc/).

#Getting support
Feel free to contact us if you have any questions:

* [Michael Knoch](https://twitter.com/Michael85069009)
* [Timo Weiß](https://twitter.com/Timo_Weiss)

## How
If you run the application, it will initialize a simple Text-UI and Graphical-UI (as you can see below).

If the state of the game changes (eg. setting a stone in the TUI), both views will render again (Observer-Pattern).

The usage is like stealing candy from a baby.



```
	0 1 2 3 4 5 6 7 8
	_ _ _ _ _ _ _ _ _
0  |0 0 0 0 0 0 0 0 0 
1  |0 0 0 0 0 0 0 0 0 
2  |0 0 0 0 0 0 0 0 0 
3  |0 0 0 0 0 0 0 0 0 
4  |0 0 0 0 0 0 0 0 0 
5  |0 0 0 0 0 0 0 0 0 
6  |0 0 0 0 0 0 0 0 0 
7  |0 0 0 0 0 0 0 0 0 
8  |0 0 0 0 0 0 0 0 0  

```

![image](http://timo-weiss.com/htwg.se.go/screens/screenGuiInit.png)

* To set a stone to the field, just type in the choords into the command-line or click in the GUI the desired postition (eg. 55)

```
	0 1 2 3 4 5 6 7 8
	_ _ _ _ _ _ _ _ _
0  |0 0 0 0 0 0 0 0 0 
1  |0 0 0 0 0 0 0 0 0 
2  |0 0 0 0 0 0 0 0 0 
3  |0 0 0 0 0 0 0 0 0 
4  |0 0 0 0 0 1 0 0 0 
5  |0 0 0 0 0 0 0 0 0 
6  |0 0 0 0 0 0 0 0 0 
7  |0 0 0 0 0 0 0 0 0 
8  |0 0 0 0 0 0 0 0 0  

```
![image](http://timo-weiss.com/htwg.se.go/screens/screenGui55.png)


* The following example shows a game-situation, by which the player with the white stone can surround his opponent by setting his stone to 42

```
    0 1 2 3 4 5 6 7 8
    _ _ _ _ _ _ _ _ _
0  |0 0 0 0 0 0 0 0 0 
1  |0 0 0 0 0 0 0 0 0 
2  |0 0 1 1 0 0 0 0 0 
3  |0 0 1 2 2 1 0 0 0 
4  |0 0 1 0 1 1 0 0 0 
5  |0 0 0 1 0 0 0 0 0 
6  |0 2 0 0 0 0 2 2 0 
7  |0 2 2 0 0 2 2 0 0 
8  |0 0 0 0 0 0 0 0 0 
```
![image](http://timo-weiss.com/htwg.se.go/screens/screenGameSituation.png)

* After setting a white stone to the pos 42, the gamefield will look like as follows:

```
    0 1 2 3 4 5 6 7 8
    _ _ _ _ _ _ _ _ _
0  |0 0 0 0 0 0 0 0 0 
1  |0 0 0 0 0 0 0 0 0 
2  |0 0 1 1 1 0 0 0 0 
3  |0 0 1 -2 -2 1 0 0 0 
4  |0 0 1 0 1 1 0 0 0 
5  |0 0 0 1 0 0 0 0 0 
6  |0 2 0 0 0 0 2 2 0 
7  |0 2 2 0 0 2 2 0 0 
8  |0 0 0 0 0 0 0 0 0
```
(yes, we know, the shift of the negativ numbers sucks)
![image](http://timo-weiss.com/htwg.se.go/screens/screenGameSituation2.png)