class Main
{
    public static void main(String[] args)
    {
        GameHandler game = new GameHandler("input.txt");
        game.RunGame();
        game.ShowResults();
    }
}