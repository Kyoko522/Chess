# Chess Game in Java

A Java-based chess game with a graphical interface, offering **Human vs Human** and **Human vs AI** gameplay modes. The project includes a basic AI opponent and supports interactive player moves on a chessboard.

---

## Table of Contents

- [Features](#features)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Usage](#usage)
- [Game Controls](#game-controls)
- [Technologies Used](#technologies-used)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)
- [License](#license)

---

## Features

- **Game Modes**:
    - **Human vs Human**: Two players can play on the same computer.
    - **Human vs AI**: Play against a basic AI opponent.
- **Interactive Chess Board**: Displays an 8x8 board with draggable pieces in standard starting positions.
- **Pawn Promotion**: Choose to promote a pawn when it reaches the opposite side of the board.
- **Turn-Based System**: Alternates moves between players and AI.

---

## Project Structure

The project is organized into three main packages:

```plaintext
src
├── ai
│   ├── ChessAI.java            # Basic AI for making moves in Human vs AI mode
│   └── ChessUtils.java         # Utility functions for AI decision-making
│
├── main
│   ├── Main.java               # Entry point, initializes frames and menu
│   ├── Board.java              # Manages the chess board and interactions
│   ├── CheckScanner.java       # Placeholder for check/checkmate detection
│   ├── Input.java              # Handles mouse events for selecting and moving pieces
│   └── Move.java               # Encapsulates move data for board operations
│
└── pieces
    ├── Piece.java              # Abstract base class for chess pieces
    ├── King.java               # Implementation for the King piece
    ├── Queen.java              # Implementation for the Queen piece
    ├── Rook.java               # Implementation for the Rook piece
    ├── Bishop.java             # Implementation for the Bishop piece
    ├── Knight.java             # Implementation for the Knight piece
    └── Pawn.java               # Implementation for the Pawn piece

resources
└── pieces.png                  # Sprites for each chess piece
```

## Installation

### Prerequisites

- **Java Development Kit (JDK)**: Java 8 or higher.
- **IDE**: Recommended to use an IDE like IntelliJ IDEA, Eclipse, or NetBeans.

### Steps

1. **Clone the Repository**:
   ```bash
   [git clone https://github.com/yourusername/chess-java-game.git](https://github.com/Kyoko522/Chess.git)
   cd chess-java-game
   ```

2. **Import the Project into Your IDE**:
   - Open your IDE.
   - Import the project as a Java project.

3. **Build the Project**:
   - Ensure your IDE has built the project without any errors.

4. **Run the Project**:
   - Run the `Main.java` file to start the application.

## Usage

1. **Launch the Application**:
   - The application opens with a **menu screen** that offers two options:
     - **Human vs Human**: Starts a game where two players can manually move pieces on the board.
     - **Human vs AI**: Start a game where you play as white against an AI opponent.

2. **Playing the Game**:
   - Select **Human vs Human** to load the chessboard.
   - Pieces can be moved on the board using a drag-and-drop mechanism (implementation in progress).

## Game Controls

### Human vs Human

- **Select a Piece**: Click on any piece to select it.
- **Move the Piece**: Drag the piece to the desired square and release to complete the move.

## Technologies Used

- **Java**: Core programming language.
- **Java Swing**: GUI components, such as frames, buttons, and panels.
- **GridBagLayout**: Layout manager for flexible grid positioning.
- **OOP Principles**: Modular and reusable code structure.

## Future Enhancements

Planned features for future releases:

- **Chess Logic Validation**: Enforce legal moves for each piece.
- **Advanced AI**: Improve AI with Minimax and Alpha-Beta pruning.
- **Check and Checkmate Detection**: Add logic for check and checkmate.
- **Undo and Redo Moves**: Allow players to undo and redo moves.
- **Game Timer**: Implement a chess clock to time each player's moves.
- **Player Scoring**: Track captured pieces or points.
- **Save and Load Game**: Save the game state and reload it later.

## License

This project is licensed under the MIT License.

