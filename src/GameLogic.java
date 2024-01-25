import java.security.KeyPair;
import java.util.*;

public class GameLogic implements PlayableLogic {
    private static final int BOARD_SIZE = 11;
    private static final int[][] CORNER_POSITIONS = {{0, 0}, {0, 10}, {10, 0}, {10, 10}};
    private ConcretePlayer firstPlayer, secondPlayer, currentPlayer;
    private ConcretePiece[][] board;
    private int[][] stepsBoard;
    private final Stack<Move> moveHistory = new Stack<>();

    public GameLogic() {
        initializeGame();
    }

    private void initializeGame() {
        stepsBoard=new int[11][11];
        firstPlayer = new ConcretePlayer(true);
        secondPlayer = new ConcretePlayer(false);
        currentPlayer = secondPlayer;
        board = new ConcretePiece[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();

    }

    private void initializeBoard() {
        // Places 13 bright parts
        //TODO we need to save the steps each time a piece is placed . 
        this.placePieceAtPosition(new Pawn(firstPlayer),new Position(3,5));    // D1
       // System.out.println("the id of the A1 is : "+((ConcretePiece)getPieceAtPosition(new Position(3,5))).getPositionList());
        this.placePieceAtPosition(new Pawn(firstPlayer), new Position(4, 4));  // D2
        this.placePieceAtPosition(new Pawn(firstPlayer), new Position(4, 5));  // D3
        this.placePieceAtPosition(new Pawn(firstPlayer), new Position(4, 6));  // D4
        this.placePieceAtPosition(new Pawn(firstPlayer), new Position(5, 3));  // D5
        this.placePieceAtPosition(new Pawn(firstPlayer), new Position(5, 4));  // D6
        this.placePieceAtPosition(new King(firstPlayer), new Position(5, 5));  // D7 (King)
        this.placePieceAtPosition(new Pawn(firstPlayer), new Position(5, 6));  // D8
        this.placePieceAtPosition(new Pawn(firstPlayer), new Position(5, 7));  // D9
        this.placePieceAtPosition(new Pawn(firstPlayer), new Position(6, 4));  // D10
        this.placePieceAtPosition(new Pawn(firstPlayer), new Position(6, 5));  // D11
        this.placePieceAtPosition(new Pawn(firstPlayer), new Position(6, 6));  // D12
        this.placePieceAtPosition(new Pawn(firstPlayer), new Position(7, 5));  // D13

        // RESET STATIC ID
        ConcretePiece.resetID();

// Places 24 dark parts
        this.placePieceAtPosition(new Pawn(secondPlayer), new Position(0, 3));   // A1
        this.placePieceAtPosition(new Pawn(secondPlayer), new Position(0, 4));   // A2
        this.placePieceAtPosition(new Pawn(secondPlayer), new Position(0, 5));   // A3
        this.placePieceAtPosition(new Pawn(secondPlayer), new Position(0, 6));   // A4
        this.placePieceAtPosition(new Pawn(secondPlayer), new Position(0, 7));   // A5
        this.placePieceAtPosition(new Pawn(secondPlayer), new Position(1, 5));   // A6
        this.placePieceAtPosition(new Pawn(secondPlayer), new Position(3, 0));   // A7
        this.placePieceAtPosition(new Pawn(secondPlayer), new Position(3, 10));  // A8
        this.placePieceAtPosition(new Pawn(secondPlayer), new Position(4, 0));   // A9
        this.placePieceAtPosition(new Pawn(secondPlayer), new Position(4, 10));  // A10
        this.placePieceAtPosition(new Pawn(secondPlayer), new Position(5, 0));   // A11
        this.placePieceAtPosition(new Pawn(secondPlayer), new Position(5, 1));   // A12
        this.placePieceAtPosition(new Pawn(secondPlayer), new Position(5, 9));   // A13
        this.placePieceAtPosition(new Pawn(secondPlayer), new Position(5, 10));  // A14
        this.placePieceAtPosition(new Pawn(secondPlayer), new Position(6, 0));   // A15
        this.placePieceAtPosition(new Pawn(secondPlayer), new Position(6, 10));  // A16
        this.placePieceAtPosition(new Pawn(secondPlayer), new Position(7, 0));   // A17
        this.placePieceAtPosition(new Pawn(secondPlayer), new Position(7, 10));  // A18
        this.placePieceAtPosition(new Pawn(secondPlayer), new Position(9, 5));   // A19
        this.placePieceAtPosition(new Pawn(secondPlayer), new Position(10, 3));  // A20
        this.placePieceAtPosition(new Pawn(secondPlayer), new Position(10, 4));  // A21
        this.placePieceAtPosition(new Pawn(secondPlayer), new Position(10, 5));  // A22
        this.placePieceAtPosition(new Pawn(secondPlayer), new Position(10, 6));  // A23
        this.placePieceAtPosition(new Pawn(secondPlayer), new Position(10, 7));  // A24

    }
    public void printStepsAsc(Player winner)
    {

        ArrayList<ConcretePiece> listOfPieces= new ArrayList<>();
        ArrayList<ConcretePiece> listOfPiecesPlayerOne= new ArrayList<>();
        ArrayList<ConcretePiece> listOfPiecesPlayerTwo= new ArrayList<>();
        for (int col = 0; col <BOARD_SIZE ; col++) {
            for (int row = 0; row < BOARD_SIZE; row++) {
                if(board[col][row]!=null)
                {
                    ConcretePiece currentPiece=board[col][row];

                    listOfPieces.add(currentPiece);

                }

            }
        }
        Collections.sort(listOfPieces, new Comparator<ConcretePiece>() {
            @Override
            public int compare(ConcretePiece o1, ConcretePiece o2) {
                return Integer.compare(o1.getPositionList().size(),o2.getPositionList().size());
            }
        });
       for (ConcretePiece p: listOfPieces)
       {
        if(p.getOwner().isPlayerOne())
        {
            listOfPiecesPlayerOne.add(p);
        }
        else {
            listOfPiecesPlayerTwo.add(p);
        }

       }
       if(winner.isPlayerOne()){
           for (ConcretePiece p : listOfPiecesPlayerOne)
           {
               System.out.println("D"+p.getId()+" :"+p.getPositionList());
           }
           for (ConcretePiece p : listOfPiecesPlayerTwo)
           {
               System.out.println("A"+p.getId()+" :"+p.getPositionList());
           }

       }
       else {
           for (ConcretePiece p : listOfPiecesPlayerTwo)
           {
               System.out.println("A"+p.getId()+" :"+p.getPositionList());
           }
           for (ConcretePiece p : listOfPiecesPlayerOne)
           {
               System.out.println("D"+p.getId()+" :"+p.getPositionList());
           }

       }
    }

    private void placePieceAtPosition(ConcretePiece piece, Position position) {
        //this places the piece also adds the position to m_positions for each piece
        // also increments stepsBoard[col][row]
        this.board[position.getCol()][position.getRow()] = piece;
        if (piece != null) {
            this.stepsBoard[position.getCol()][position.getRow()]+=1;
            piece.addPosition(position);
        }
    }

    @Override
    public boolean move(Position a, Position b) {
        ConcretePiece piece = board[a.getCol()][a.getRow()];
        if (piece == null || piece.getOwner() != currentPlayer || !isValidMove(a, b))
            return false;
        // System.out.println(a.getRow());
        System.out.println("a.getRow() = " + a.getRow() + " ,a.getCol() = " + a.getCol());
        System.out.println("b.getRow() = " + b.getRow() + " ,b.getCol() = " + b.getCol());
        ///TODO check if vertical or horizontal to loop over the cols/rows and increment the stepBoard[col][row]
        incrementStepsBoard(a,b);
        moveHistory.push(new Move(a, b, piece));
        //board[b.getCol()][b.getRow()] = piece;
        placePieceAtPosition(piece,b);
        placePieceAtPosition(null,a);
        //board[a.getCol()][a.getRow()] = null;

        //TODO scan for possible kill of enemy soldier or king perhaps
        enemyScanAndKill(piece, b);
        currentPlayer = isSecondPlayerTurn() ? firstPlayer : secondPlayer;
        return true;
    }

    private void incrementStepsBoard(Position a, Position b) {
        boolean isVertical= a.getCol() == b.getCol();
        if(isVertical)
        {
            for (int i = Math.min(a.getRow(),b.getRow())+1; i <Math.max(a.getRow(),b.getRow())-1; i++) {
                // TODO dont include a and b themselves assuming when placing the piece there is an increment without calling this function
                stepsBoard[a.getCol()][i]++;

            }
        }
        else{
            for (int i = Math.min(a.getCol(),b.getCol())+1; i <Math.max(a.getCol(),b.getCol())-1; i++) {
                // TODO dont include a and b themselves assuming when placing the piece there is an increment without calling this function
                stepsBoard[i][a.getRow()]++;

            }
        }
    }

    private void enemyScanAndKill(ConcretePiece concretePiece, Position p) {
        if(!(concretePiece instanceof King)) {
            int j = p.getCol();
            int i = p.getRow();
            ///TODO if instance of pawn , since king doesnt have the ability to participate in killing an enemy
            // we begin with checking for nearby pieces and we ask them to identify (lets hope not as a cat)
            Piece up = this.getPieceAtPosition(new Position(j, i - 1));
            Piece down = this.getPieceAtPosition(new Position(j, i + 1));
            Piece left = this.getPieceAtPosition(new Position(j - 1, i));
            Piece right = this.getPieceAtPosition(new Position(j + 1, i));
            //checking each side for an enemy soldier or an enemy king
            boolean b1=check_up(up, j, i);
            boolean b2=check_down(down, j, i);
            boolean b3=check_left(left, j, i);
            boolean b4=check_right(right, j, i);
            if(b1||b2||b3||b4)
            {
                /// king is dead means Attacker wins means second player...
                printStepsAsc(secondPlayer);
            }
            //TODO check the checks for typing errors cause they are a bit complicated .

        }
        // if a king he can't attack whatsoever,
        // but we need to check i he reached a corner or not
        if(is_corner(p.getCol(),p.getRow()))
        {
            board[p.getCol()][p.getRow()]=null;
            printStepsAsc(firstPlayer);
            reset();
        }
    }
    void endGame(Player winner)
    {
        printStepsAsc(winner);
        //TODO MORE PRINTS
        //. .....
        //TODO IMPORTANT INCEREMENT THE WINS OF THE PLAYERS 
        reset();
    }

    private boolean check_right(Piece right, int j, int i) {
        if (right != null && right.getOwner() != this.currentPlayer) {
            /// it's not null and the owner is the other player , means enemy soldier we proceed to check for up-up if it's our soldier
            Piece right_right = getPieceAtPosition(new Position(j+2, i)); // returns null in 2 cases 1.out of bounds 2.no piece
            if (!(right instanceof King)) {
                if ((right_right != null && right_right.getOwner() == this.currentPlayer&&!(right_right instanceof  King)) || (j + 2 == BOARD_SIZE) || (is_corner(j+2, i))) {
                    // Ladies and gentlemen es hora de comer
                    board[j+1][i] = null;
                }

            } else {
                // TODO IS A KING !! 👑
                Piece right_up = getPieceAtPosition(new Position(j + 1, i - 1));
                Piece right_down = getPieceAtPosition(new Position(j + 1, i + 1));
                if ((right_right != null && right_right.getOwner() == this.currentPlayer) && (right_up != null && right_up.getOwner() == this.currentPlayer)
                        && (right_down != null && right_down.getOwner() == this.currentPlayer)) {
                    // if all exist and are friendly soldiers , kill the king bro ...
                    board[j+1][i] = null;
                    return true;
                    // TODO end the game

                } else if ((j + 2 == BOARD_SIZE) && (right_up != null && right_up.getOwner() == this.currentPlayer)
                        && (right_down != null && right_down.getOwner() == this.currentPlayer)) {
                    board[j+1][i] = null;
                    return true;
                    // TODO end the game

                } else if ((right_right != null && right_right.getOwner() == this.currentPlayer) && (right_up != null && right_up.getOwner() == this.currentPlayer)
                        && (i + 1 == BOARD_SIZE)) {
                    board[j+1][i] = null;
                    return true;
                    // TODO end the game

                } else if ((right_right != null && right_right.getOwner() == this.currentPlayer) && (i - 1 == -1)
                        && (right_down != null && right_down.getOwner() == this.currentPlayer)) {

                    board[j+1][i] = null;
                    return true;
                    // TODO end the game
                }
            }
        }
return false;
    }

    private boolean check_left(Piece left, int j, int i) {
        if (left != null && left.getOwner() != this.currentPlayer) {
            /// it's not null and the owner is the other player , means enemy soldier we proceed to check for up-up if it's our soldier
            Piece left_left = getPieceAtPosition(new Position(j-2, i)); // returns null in 2 cases 1.out of bounds 2.no piece
            if (!(left instanceof King)) {
                if ((left_left != null && left_left.getOwner() == this.currentPlayer&&!(left_left instanceof  King)) || (j - 2 == -1) || (is_corner(j-2, i))) {
                    // Ladies and gentlemen es hora de comer
                    board[j-1][i] = null;
                   // return false;
                }


            } else {
                // TODO IS A KING !! 👑
                Piece left_up = getPieceAtPosition(new Position(j - 1, i - 1));
                Piece left_down = getPieceAtPosition(new Position(j - 1, i + 1));
                if ((left_left != null && left_left.getOwner() == this.currentPlayer) && (left_up != null && left_up.getOwner() == this.currentPlayer)
                        && (left_down != null && left_down.getOwner() == this.currentPlayer)) {
                    // if all exist and are friendly soldiers , kill the king bro ...
                    board[j-1][i] = null;
                    return true;
                    // TODO end the game

                } else if ((j - 2 == -1) && (left_up != null && left_up.getOwner() == this.currentPlayer)
                        && (left_down != null && left_down.getOwner() == this.currentPlayer)) {
                    board[j-1][i] = null;
                    return true;
                    // TODO end the game

                } else if ((left_left != null && left_left.getOwner() == this.currentPlayer) && (left_up != null && left_up.getOwner() == this.currentPlayer)
                        && (i + 1 == BOARD_SIZE)) {
                    board[j-1][i] = null;
                    return true;
                    // TODO end the game

                } else if ((left_left != null && left_left.getOwner() == this.currentPlayer) && (i - 1 == -1)
                        && (left_down != null && left_down.getOwner() == this.currentPlayer)) {

                    board[j-1][i] = null;
                    return true;
                    // TODO end the game
                }
            }
        }

        return false;
    }

    private boolean check_down(Piece down, int j, int i) {
        if (down != null && down.getOwner() != this.currentPlayer) {
            /// it's not null and the owner is the other player , means enemy soldier we proceed to check for up-up if it's our soldier
            Piece down_down = getPieceAtPosition(new Position(j, i + 2)); // returns null in 2 cases 1.out of bounds 2.no piece
            if (!(down instanceof King)) {
                if ((down_down != null && down_down.getOwner() == this.currentPlayer&&!(down_down instanceof  King)) || (i + 2 == BOARD_SIZE) || (is_corner(j, i + 2))) {
                    // Ladies and gentlemen es hora de comer
                    board[j][i + 1] = null;
                }

            } else {
                // TODO IS A KING !! 👑
                Piece down_left = getPieceAtPosition(new Position(j - 1, i + 1));
                Piece down_right = getPieceAtPosition(new Position(j + 1, i + 1));
                if ((down_down != null && down_down.getOwner() == this.currentPlayer) && (down_left != null && down_left.getOwner() == this.currentPlayer)
                        && (down_right != null && down_right.getOwner() == this.currentPlayer)) {
                    // if all exist and are friendly soldiers , kill the king bro ...
                    board[j][i + 1] = null;
                    return true;
                    // TODO end the game

                } else if ((i + 2 == BOARD_SIZE) && (down_left != null && down_left.getOwner() == this.currentPlayer)
                        && (down_right != null && down_right.getOwner() == this.currentPlayer)) {
                    board[j][i + 1] = null;
                    return true;
                    // TODO end the game

                } else if ((down_down != null && down_down.getOwner() == this.currentPlayer) && (down_left != null && down_left.getOwner() == this.currentPlayer)
                        && (j + 1 == BOARD_SIZE)) {
                    board[j][i + 1] = null;
                    return true;
                    // TODO end the game

                } else if ((down_down != null && down_down.getOwner() == this.currentPlayer) && (j - 1 == -1)
                        && (down_right != null && down_right.getOwner() == this.currentPlayer)) {

                    board[j][i + 1] = null;
                    return true;
                    // TODO end the game
                }
            }
        }
return false;
    }

    private boolean check_up(Piece up, int j, int i) {
        if (up != null && up.getOwner() != this.currentPlayer) {
            /// it's not null and the owner is the other player , means enemy soldier we proceed to check for up-up if it's our soldier
            Piece up_up = getPieceAtPosition(new Position(j, i - 2)); // returns null in 2 cases 1.out of bounds 2.no piece
            if (!(up instanceof King)) {
                if ((up_up != null && up_up.getOwner() == this.currentPlayer &&!(up_up instanceof  King)) || (i - 2 == -1) || (is_corner(j, i - 2))) {
                    // Ladies and gentlemen es hora de comer
                    board[j][i - 1] = null;
                }

            } else {
                // TODO IS A KING !! 👑
                Piece up_left = getPieceAtPosition(new Position(j - 1, i - 1));
                Piece up_right = getPieceAtPosition(new Position(j + 1, i - 1));
                if ((up_up != null && up_up.getOwner() == this.currentPlayer) && (up_left != null && up_left.getOwner() == this.currentPlayer)
                        && (up_right != null && up_right.getOwner() == this.currentPlayer)) {
                    // if all exist and are friendly soldiers , kill the king bro ...
                    board[j][i - 1] = null;
                    return true;
                    // TODO end the game

                } else if ((i - 2 == -1) && (up_left != null && up_left.getOwner() == this.currentPlayer)
                        && (up_right != null && up_right.getOwner() == this.currentPlayer)) {
                    board[j][i - 1] = null;
                    return true;
                    // TODO end the game

                } else if ((up_up != null && up_up.getOwner() == this.currentPlayer) && (up_left != null && up_left.getOwner() == this.currentPlayer)
                        && (j + 1 == BOARD_SIZE)) {
                    board[j][i - 1] = null;
                    return true;
                    // TODO end the game

                } else if ((up_up != null && up_up.getOwner() == this.currentPlayer) && (j - 1 == -1)
                        && (up_right != null && up_right.getOwner() == this.currentPlayer)) {

                    board[j][i - 1] = null;
                    return true;
                    // TODO end the game
                }
            }
        }

        return false;
    }

    private boolean is_corner(int col, int row) {
        for (int[] cornerPosition : CORNER_POSITIONS) {
            if (cornerPosition[0] == row && cornerPosition[1] == col) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidMove(Position a, Position b) {
        // if it's out of bounds then don't do anything
        if (!isValidPosition(a) || !isValidPosition(b))
            return false;

        Piece piece = getPieceAtPosition(a);
        // if it's not our piece or an empty place then don't move it
        if (piece == null || piece.getOwner() != currentPlayer)
            return false;

        // to prevent diagonal move
        int rowDiff = Math.abs(a.getRow() - b.getRow());
        int colDiff = Math.abs(a.getCol() - b.getCol());
        if (rowDiff != 0 && colDiff != 0)
            return false;

        // Check if the destination position is empty
        if (board[b.getCol()][b.getRow()] != null) {
            return false;
        }

        // Check if there is a piece in the way
        if (rowDiff == 0) { // if in same line means we're about to go horizontal lets check for any pawn in between the col_a and col_b
            int minCol = Math.min(a.getCol(), b.getCol());
            int maxCol = Math.max(a.getCol(), b.getCol());
            for (int col = minCol + 1; col < maxCol; col++) {
                if (board[col][a.getRow()] != null) {
                    return false;
                }
            }
        } else { /// means vertical we check for (min-line+1 to max-line-1 for non-null )
            int minRow = Math.min(a.getRow(), b.getRow());
            int maxRow = Math.max(a.getRow(), b.getRow());
            for (int row = minRow + 1; row < maxRow; row++) {
                if (board[a.getCol()][row] != null) {
                    return false;
                }
            }
        }
        // TODO check it doesnt intervene with the opposite coordinates
        // Check if the destination position is one of the corner positions
        for (int[] corner : CORNER_POSITIONS) {
            if (b.getRow() == corner[0] && b.getCol() == corner[1]) {
                // Only allow the king to go and stand in corner positions
                return piece instanceof King;
            }
        }

        return true;
    }


    private boolean isValidPosition(Position position) {
        int row = position.getRow();
        int col = position.getCol();
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
    }


    @Override
    public Piece getPieceAtPosition(Position position) {
        if (isValidPosition(position)) {
            return board[position.getCol()][position.getRow()];
        }
        return null;
    }

    @Override
    public Player getFirstPlayer() {
        return firstPlayer;
    }

    @Override
    public Player getSecondPlayer() {
        return secondPlayer;
    }

    @Override
    public boolean isGameFinished() {
        // Check if the king has reached one of the corner positions
        for (int[] corner : CORNER_POSITIONS) {
            ConcretePiece piece = board[corner[1]][corner[0]];
            if (piece instanceof King) {
                firstPlayer.incrementWins();
                return true;  // King reached a corner, game over
            }
        }

        return false;  // King not found in corner positions, game continues
    }

    @Override
    public boolean isSecondPlayerTurn() {
        return currentPlayer == secondPlayer;
    }

    @Override
    public void reset() {
        // Clear move history
        moveHistory.clear();

        initializeGame();
    }


    @Override
    public void undoLastMove() {
        if (!moveHistory.isEmpty()) {
            Move lastMove = moveHistory.pop();
            Position from = lastMove.getFrom();
            Position to = lastMove.getTo();
            ConcretePiece piece = lastMove.getPiece();

            // Move the piece back to the original position
            board[from.getRow()][from.getCol()] = piece;
            board[to.getRow()][to.getCol()] = null;

            // Switch back to the previous player
            currentPlayer = (currentPlayer == firstPlayer) ? secondPlayer : firstPlayer;
        }
    }

    @Override
    public int getBoardSize() {
        return BOARD_SIZE;
    }

    // Inner class to represent a move
    private static class Move {
        private final Position from;
        private final Position to;
        private ConcretePiece piece;

        public Move(Position from, Position to, ConcretePiece piece) {
            this.from = from;
            this.to = to;
            this.piece = piece;
        }

        public Position getFrom() {
            return from;
        }

        public Position getTo() {
            return to;
        }

        public ConcretePiece getPiece() {
            return piece;
        }
    }
}