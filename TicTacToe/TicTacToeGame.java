import java.util.Scanner;
class TicTacToeGame //Main game file which uses all the necessary components from the GameLogic file
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        for(;;)
        {
            System.out.println("----------------"); //UI print
            System.out.println("| Tic Tac Toe  |");
            System.out.println("|  Play (P)    |" );
            System.out.println("|  About (A)   |");
            System.out.println("|  Exit (E)    |");
            System.out.println("----------------");
            String choice = scanner.nextLine();
            if (choice.equals("P"))
            {
                System.out.println("Starting the game...\n");
                System.out.print("Enter the 1st player's name: ");
                String player1_name = scanner.nextLine();
                    
                System.out.print(player1_name+" Select X for X or O for O: ");
                String player1_choice = scanner.nextLine();

                do
                {
                    if(!player1_choice.equals("X") && !player1_choice.equals("O"))
                    {
                        System.out.print("Please enter a valid choice: ");  
                        player1_choice = scanner.nextLine();
                    }
                } while(!player1_choice.equals("X") && !player1_choice.equals("O"));    

                System.out.print("Enter the 2nd player's name: ");
                String player2_name = scanner.nextLine();
                    
                String player2_choice;
                if (player1_choice.equals("O"))
                {
                    System.out.println(player2_name+" You have been assigned the letter X.");
                     player2_choice = "X";
                }
                else 
                {  
                    System.out.println(player2_name+" You have been assigned the letter O.");
                     player2_choice = "O";
                }
                int rounds = 1;
                int player1_score = 0, player2_score = 0;
                int bonus_rounds = 0;                           //initilization for the first loop (do while)
                boolean hasTie = false;
                GameLogic gameHelper = new GameLogic();
                String[][] grid = {{"1","2","3"},{"4","5","6"},{"7","8","9"}};
                do  
                {
                    boolean player1Won = false;
                    boolean player2Won = false; // initilization for the 2nd loop (while)
                    int tries = 0;
                    gameHelper.resetGrid(grid);
                    if (hasTie)
                    {
                        bonus_rounds++;
                        System.out.println("BONUS ROUND "+bonus_rounds);
                    }
                    else
                    System.out.println("ROUND "+rounds);
                    while( !player1Won && !player2Won && tries < 9 )
                    { 
                        gameHelper.drawGrid(grid);
                        System.out.print(player1_name + " Select where you want to place " + player1_choice + ",(1 to 9): ");
                        int choice_pos1 = scanner.nextInt();
                        scanner.nextLine(); 
                        
                        do
                        {
                            if( choice_pos1 < 1 || choice_pos1 > 9 )
                            {
                               System.out.print("Please enter a valid place (1 to 9): ") ;  //ensure valid data from player1
                               choice_pos1 = scanner.nextInt();
                               scanner.nextLine(); 
                               
                            }
                            else if(gameHelper.isTaken(grid,choice_pos1))
                            {
                                System.out.print("Please enter a not taken position: ");
                                choice_pos1 = scanner.nextInt();
                                scanner.nextLine(); 
                            }

                        } while(choice_pos1 < 1 || choice_pos1 > 9 || gameHelper.isTaken(grid,choice_pos1));    
                        tries++;
                        gameHelper.applyChoice(grid, choice_pos1, player1_choice);
                        gameHelper.drawGrid(grid);
                        if(gameHelper.checkWinPlayer(grid,player1_choice))      //checks if the current move of player1 gets them the win
                        {
                            player1Won = true;
                            System.out.println(player1_name+" Won this round!");
                            player1_score += 10;
                            continue;
                        }

                        if(tries < 9)   // at the 9th try no need to ask player2 his choice, the grid is already full
                        {
                            System.out.print(player2_name + " Select where you want to place "+player2_choice+",(1 to 9): ");
                            int choice_pos2 = scanner.nextInt();
                            scanner.nextLine(); 
                            do
                            {
                                if( choice_pos2 < 1 || choice_pos2 > 9 )
                                {
                                   System.out.print("Please enter a valid place (1 to 9): ");
                                   choice_pos2 = scanner.nextInt();
                                   scanner.nextLine();  
                                }
                                else if(gameHelper.isTaken(grid,choice_pos2))
                                {
                                    System.out.print("Please enter a not taken position: ");
                                    choice_pos2 = scanner.nextInt();
                                    scanner.nextLine(); 
                                }
    
                            } while(choice_pos2 < 1 || choice_pos2 > 9 || gameHelper.isTaken(grid,choice_pos2));
    
                            tries++;
                            gameHelper.applyChoice(grid, choice_pos2, player2_choice);
                            if(gameHelper.checkWinPlayer(grid,player2_choice))
                            {
                                player2Won = true;
                                gameHelper.drawGrid(grid);
                                System.out.println(player2_name+" Won this round!");
                                player2_score += 10;
                                continue;
                            }
                        }
                        if( tries == 9 && !player1Won && !player2Won )  //Tie
                            System.out.println("Tie! No one won this round.");
                    }
                    if( rounds == 3 || hasTie)
                    {
                        if (player1_score > player2_score)
                        {
                            System.out.println(player1_name+" Won the game with "+player1_score+" score!");
                            System.out.println("------------------------------------------------");
                            System.out.println("               | Game Analysis |                "); //player1 Win menu
                            System.out.println("Winner: "+player1_name+"\nScore: "+player1_score);
                            System.out.println("Loser: "+player2_name+"\nScore: "+player2_score);
                            System.out.println("Total amount of rounds: " + rounds);
                            System.out.println("Bonus rounds: "+ bonus_rounds);
                            System.out.println("-------------------------------------------------");
                            hasTie = false;
                        }
                        else if (player1_score < player2_score)
                        {
                            System.out.println(player2_name+" Won the game with "+player2_score+" score!");
                            System.out.println("------------------------------------------------");
                            System.out.println("               | Game Analysis |                ");
                            System.out.println("Winner: "+player2_name+"\nScore: "+player2_score);  //player2 Win menu
                            System.out.println("Loser: "+player1_name+"\nScore: "+player1_score);
                            System.out.println("Total amount of rounds: " + rounds);
                            System.out.println("Bonus rounds: "+ bonus_rounds);
                            System.out.println("-------------------------------------------------");
                            hasTie = false;
                        }
                        else
                        {
                            System.out.print("Tie! Both players have " + player1_score + " score. Continue the game or not? (Yes/No): ");
                            hasTie = true;
                            String bonus_choice = scanner.nextLine();
                            do
                            {
                                if(!bonus_choice.equals("Yes") && !bonus_choice.equals("No"))
                                {
                                    System.out.print("Please enter a valid choice: ");
                                    bonus_choice = scanner.nextLine();
                                }
                                if(bonus_choice.equals("No")) 
                                {
                                    hasTie = false;
                                    System.out.println("------------------------------------------------");
                                    System.out.println("               | Game Analysis |                ");
                                    System.out.println("Winner: None"+"\nLoser: None");
                                    System.out.println("Score of both players: "+player1_score);                // Tie menu if both players decline for bonus round
                                    System.out.println("Total amount of rounds: " + rounds);
                                    System.out.println("Bonus rounds: "+ bonus_rounds);
                                    System.out.println("-------------------------------------------------");
                                }
                    
                            } while(!bonus_choice.equals("Yes") && !bonus_choice.equals("No")); //Bonus round begins( 1 extra round )
                        }
                    }
                    rounds++;
                } while(rounds <=3  || hasTie); 
            }
            else if(choice.equals("A"))
            {
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.println("\nThis game was made by Helio Zhuleku on 14/4/2025 with the Java Programming Language.\n"); //Credits
                System.out.println("--------------------------------------------------------------------------------------");

            }
            else if(choice.equals("E"))
            {
                System.out.println("Exiting the game...");  //Exit
                break;
            }
        }

    }
}       