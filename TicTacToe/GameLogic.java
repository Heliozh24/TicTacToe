class GameLogic //Contains all the needed components for the game to work
{
    public boolean isTaken(String grid[][],int choice_pos)  //checks if the pos which any player wants is already taken
    {
        int row;
        int col;
        if(choice_pos <=3)
        {
            row = 0;
            col = (choice_pos)-1;
        }
        else if(choice_pos<=6)
        {
            row = 1;
            col = (choice_pos)-4;
        }
        else
        {
            row = 2;
            col = (choice_pos)-7;
        }
        if (grid[row][col].equals("X") || grid[row][col].equals("O"))
            return true;
        else
            return false;
            
    }
    public void applyChoice(String grid[][],int choice_pos, String player_choice)   //applies choice in the grid
    {
        int row;
        int col;
        if(choice_pos <=3)
        {
            row = 0;
            col = (choice_pos)-1;
        }
        else if(choice_pos<=6)
        {
            row = 1;
            col = (choice_pos)-4;
        }
        else
        {
            row = 2;
            col = (choice_pos)-7;
        }
        grid[row][col] = player_choice; 
    }
    public void drawGrid(String grid[][])   //draws the grid
    {
        for(int i = 0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                if (j == 2)
                    System.out.print(grid[i][j]+" \n");
                else
                    System.out.print(grid[i][j]+" ");
            }
        }
    }
    public void resetGrid(String grid[][])      //resets the grid (when the next round begins)
    {
      String[][] temp_grid = {{"1","2","3"},{"4","5","6"},{"7","8","9"}};
      for(int i = 0; i<3; i++)
      {
        for(int j =0; j<3; j++)
        {
            grid[i][j] = temp_grid[i][j];   
        }
      }
    }
    public boolean foundMatchX(String grid[][]) //checks any trio of X's
    {
        int main_diag = 0;
        int sec_diag = 0;
        for(int i =0; i<3; i++)
        {
            int row = 0;
            int col = 0;
            for(int j =0; j<3; j++)
            {
                if (grid[i][j].equals("X"))
                    row++;
                if (grid[j][i].equals("X"))
                    col++;

            }
            if (row == 3 || col == 3)
                return true;
            if(grid[i][i].equals("X"))
              main_diag++;

            if(grid[i][2-i].equals("X"))
                sec_diag++;
        }     
        return (main_diag == 3 || sec_diag == 3);
    }
    public boolean foundMatchO(String grid[][]) //checks any trio of O's
    {
        int main_diag = 0;
        int sec_diag = 0;
        for(int i =0; i<3; i++)
        {
            int row = 0;
            int col = 0;
            for(int j =0; j<3; j++)
            {
                if (grid[i][j].equals("O"))
                    row++;
                if (grid[j][i].equals("O"))
                    col++;

            }
            if (row == 3 || col == 3)
                return true;
            if(grid[i][i].equals("O"))
              main_diag++;

            if(grid[i][2-i].equals("O"))
              sec_diag++;
        }
        return (main_diag == 3 || sec_diag == 3);
        
    }
    public boolean checkWinPlayer(String grid[][],String player_choice) //checks which player wins
    {
        if (player_choice.equals("X"))
        {
            if (foundMatchX(grid))
                return true;
            else
                return false;
        }
        else
        {
            if (foundMatchO(grid))
                return true;
            else
                return false;
        }
    }
}