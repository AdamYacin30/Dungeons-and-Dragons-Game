# Dungeon Escape Game

## Overview
This project is an implementation of a dungeon escape game. The objective of the game is to help a warrior find the shortest path from the initial chamber to the exit in a dungeon filled with various types of chambers. The solution involves using a priority queue implemented with a doubly linked list.

## Features
- **Priority Queue**: Implement a priority queue using a doubly linked list.
- **Shortest Path Algorithm**: Find the shortest path from the initial chamber to the exit.
- **Dungeon Navigation**: Navigate through various types of chambers (empty, dragon, wall, exit).
- **Custom Data Structures**: Implement custom data structures and algorithms in Java.

## Provided Files
- **DLinkedNode.java**: Represents the nodes of a doubly linked list used in the priority queue.
- **DLPriorityQueue.java**: Implements the priority queue using a doubly linked list.
- **FindShortestPath.java**: Contains the main method and the algorithm to find the shortest path.
- **Dungeon.java**: Represents the dungeon and reads the dungeon layout from a file.
- **Hexagon.java**: Represents the chambers in the dungeon.
- **HexColors.java**: Manages colors for the graphical representation of the dungeon.
- **HexComponent.java**: Component for displaying hexagonal chambers.
- **HexLayout.java**: Manages the layout of hexagonal chambers.
- **PriorityQueueADT.java**: Interface for the priority queue ADT.

## Installation
1. Ensure you have Java installed on your machine.
2. Place all the provided files in the same directory.
3. Compile the Java files using the following command:
   ```sh
   javac *.java
