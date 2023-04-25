import java.util.*;

class GameHandler
{
    private int size, runs;
    private Cell[][] board;
    public GameHandler(String input)
    {
        FileReader read = new FileReader(input);
        size = read.GetFileSize() - 1;
        GenerateBoard(read.GetFileContent());
    }

    //Convert File String Data To size x size Matrix
    private void GenerateBoard(String[] data)
    {
        board = new Cell[size][size];
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                if(data[i].charAt(j) == '.')
                {
                    board[i][j] = new Cell(false);
                } else {
                    board[i][j] = new Cell(true);
                }
            }
        }
        runs = Integer.valueOf(data[size]); //Determines Number Of Runs From End Of File
    }

    //Runs Specified Rounds Of Life
    public void RunGame()
    {
        //Game game = new Game(board, size);
        for(int i = 0; i < runs; i++)
        {
            try
            {
                DoRound();
            }
            catch (InterruptedException e)
            {
                System.out.println("Program has been interupted");
                e.printStackTrace(System.out);
            }
        }
    }

    public void DoRound() throws InterruptedException
    {
        ArrayList<ArrayList<Game>> cellThreads = new ArrayList<>();
        //Creates Threads
        for(int i = 0; i < size; i++)
        {
            ArrayList<Game> threads = new ArrayList<>();
            for(int j = 0; j < size; j++)
            {
                threads.add(new Game(board, size, i, j));
            }
            cellThreads.add(threads);
        }

        //Starts Threads
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                cellThreads.get(i).get(j).start();
            }
        }

        //Join Threads
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                cellThreads.get(i).get(j).t.join();
            }
        }

        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                board[i][j] = cellThreads.get(i).get(j).cell;
            }
        }
    }

    //Prints results, 'x' = living, '.' = dead
    public void ShowResults()
    {
        System.out.println("Ran " + runs + " Times");
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                if(board[i][j].GetVal())
                    System.out.print("x");
                else
                    System.out.print(".");
            }
            System.out.println();
        }
    }
}