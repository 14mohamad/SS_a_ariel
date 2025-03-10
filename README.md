# Assignment 1 - Object-Oriented Programming (OOP)

## Overview
This project implements the **Hnefatafl (Viking Chess) game** using object-oriented programming principles. The goal is to apply OOP concepts while developing the game's logic and implementing the necessary game mechanics.

## Features
- **Game Mechanics**:
  - Implement movement rules based on the **Hnefatafl game**.
  - Support for two players: **Attackers vs. Defenders**.
  - The **King** must reach a corner square to win.
  - Attackers win if they **trap the King**.
  - Capturing is done by surrounding a piece from two opposite sides.

- **Object-Oriented Implementation**:
  - Abstract class `ConcretePiece` for general piece behavior.
  - Specific piece classes: `Pawn`, `King`.
  - `ConcretePlayer` class for player implementation.
  - `GameLogic` class for game rules and turn management.
  - `Position` class to track board positions.

- **Game Statistics (Bonus)**:
  - Track and print move history.
  - Sort captured pieces based on capture count.
  - Rank pieces by distance traveled.
  - List board squares used by multiple pieces.

## How to Run
To execute the game:
```bash
javac *.java
java Main


# מטלה 1 - תכנות מונחה עצמים (OOP)

## סקירה כללית
פרויקט זה מממש את **המשחק שחמט ויקינגי (Hnefatafl)** תוך יישום עקרונות תכנות מונחה עצמים (OOP). המטרה היא ליישם את חוקי המשחק ולפתח את הלוגיקה שלו בצורה מבוססת אובייקטים.

## תכונות עיקריות
- **מכניקת המשחק**:
  - מימוש חוקי המשחק של **Hnefatafl**.
  - תמיכה בשני שחקנים: **תוקפים מול מגנים**.
  - **המלך מנצח אם מגיע לפינה**.
  - התוקפים מנצחים אם **לוכדים את המלך**.
  - לכידת כלי מתבצעת כאשר הוא מוקף משני צדדים נגדיים.

- **יישום תכנות מונחה עצמים**:
  - מחלקה אבסטרקטית `ConcretePiece` להתנהגות כללית של כלי.
  - מחלקות לכלים ספציפיים: `Pawn`, `King`.
  - מחלקה `ConcretePlayer` למימוש שחקנים.
  - מחלקה `GameLogic` לניהול חוקי המשחק וסדר התורות.
  - מחלקה `Position` לניהול משבצות הלוח.

- **סטטיסטיקות משחק (בונוס)**:
  - מעקב אחר היסטוריית הצעדים.
  - מיון כלים לפי מספר האכילות שלהם.
  - דירוג כלים לפי המרחק שעברו.
  - הצגת משבצות שבהן עברו מספר כלים.

## כיצד להריץ את המשחק
להרצת המשחק:
```bash
javac *.java
java Main
