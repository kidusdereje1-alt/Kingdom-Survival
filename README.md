Kingdom Survival Game

Project Description
Kingdom Survival is a simple console-based Java game that demonstrates core Object-Oriented Programming concepts including:
- Classes and Objects
- Encapsulation
- Inheritance
- Polymorphism

The player manages heroes defending a kingdom from enemies.

How to Compile
Open terminal in the project folder and run:
javac .java

How to Run
java Main

OOP Concepts Demonstrated

1. Classes and Objects
Files:
- Character.java
- Warrior.java
- Archer.java
- Enemy.java
- Kingdom.java

Example:
Objects are created in Main.java

2. Encapsulation
File:
- Character.java

Examples:
- private variables:
  - name
  - health
- getters and setters used for controlled access

3. Inheritance
Files:
- Warrior.java
- Archer.java
- Enemy.java

These classes inherit from Character.java

4. Polymorphism

Method Overriding
Files:
- Warrior.java
- Archer.java
- Enemy.java

Each class overrides the attack() method.

Method Overloading
File:
- Character.java

Methods:
- attack()
- attack(String weapon)

Superclass References
File:
- Kingdom.java

Example:
ArrayList<Character> heroes

This allows different subclass objects to be handled dynamically.
