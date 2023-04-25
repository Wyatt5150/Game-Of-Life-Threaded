class Game implements Runnable
{
    //Recieve Board populated with Cells
    //Does one Cycle and returns state of the board
    private Cell[][] gameBoard;
    private int boardSize;
    public Thread t;
    public int row, col;
    public Cell cell;

    public Game(Cell[][] board, int size, int i, int j)
    {
        gameBoard = board;
        boardSize = size;
        row = i;
        col = j;
        cell = new Cell(board[i][j].GetVal());
    }

    public void start() {
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        LivingStatus(row, col);
        cell.UpdateVal();
    }

    //Finds number of living neighbors relative to cell withought going out of bounds
    private int CheckNeighbors(int i, int j)
    {
        int count = 0;
        if (i > 0)
        {
            if (j < boardSize-1)
                if(gameBoard[i-1][j+1].GetVal())
                    count++;
            if(gameBoard[i-1][j].GetVal())
                count++;
            if (j > 0)
                if(gameBoard[i-1][j-1].GetVal())
                    count++;
        }
        if (j < boardSize-1)
            if(gameBoard[i][j+1].GetVal())
                count++;
        if (j > 0)
            if(gameBoard[i][j-1].GetVal())
                count++;
        if (i < boardSize-1)
        {
            if (j < boardSize-1)
                if(gameBoard[i+1][j+1].GetVal())
                    count++;
            if(gameBoard[i+1][j].GetVal())
                count++;
            if (j > 0)
                if(gameBoard[i+1][j-1].GetVal())
                    count++;
        }
        return count;
    }

    private void LivingStatus(int i, int j)
    {
        int neighbors = CheckNeighbors(i, j);
        if(cell.GetVal())
        {
            if(neighbors < 2)
            {
                cell.QueueNextVal(false);
            }
            if(neighbors > 3)
            {
                cell.QueueNextVal(false);
            }
        } 
        else 
        {
            if(neighbors == 3)
            {
                cell.QueueNextVal(true);
            }
        }
    }
}