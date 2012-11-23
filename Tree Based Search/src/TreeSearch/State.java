
import java.util.Arrays;

public class State 
{
    private Tile board[][];
    private int rows,cols;
    
    /**
     * @param rows number of rows
     * @param cols number of columns
     */
    public State(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;
        this.board = new Tile[rows][cols];
    }
    
    public State (State that)
    {
        this.rows = that.rows;
        this.cols = that.cols;
        this.board = new Tile[this.rows][this.cols];
        
        for(int i=0;i<board.length;i++)
        {
            //this.board[i] = that.board[i].clone();
            this.board[i] = Arrays.copyOf(that.board[i], that.board[i].length);   
        }
    }
    
    /**
     * compare two Tile[][]
     * @param aBoard
     * @return return ture if they are same
     */
    
    public boolean isEqual(State that)
    {
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                if(!this.board[i][j].isEqual(that.board[i][j]))
                {
                   return false;
                }
            }
        }
        
        return true;   
    }
    
      public void configStartState(String strStartState)
      {
        int tileNumber,xLocation,yLocation;
        String strTileNumber = " " ,strXLocation= " " ,strYLocation= " " ;
        String strTile;
        String[] tiles = strStartState.split("  ");
        
        for(int i=0;i<tiles.length;i++)
        {
            strTile = tiles[i];
            
            //tile number is between 0 - 9
            if(strTile.length() == 7)
            {
                strTileNumber = strTile.substring(0,1);
                strXLocation = strTile.substring(3,4);
                strYLocation = strTile.substring(5,6); 
            }
            //tile number  is greater and equal than 10
            else if(strTile.length() == 8)
            {
                strTileNumber = strTile.substring(0,2);
                strXLocation = strTile.substring(4,5);
                strYLocation = strTile.substring(6,7);
            }
            
            // convert string to int
            tileNumber = Integer.parseInt(strTileNumber);
            xLocation = Integer.parseInt(strXLocation);
            yLocation = Integer.parseInt(strYLocation);
            
            Tile tempTile = new Tile(tileNumber,yLocation-1,xLocation-1);
            //System.out.println(tempTile.toString());
            
            setTile(tempTile);    
        }
      }
      
      public void configEndState(String strEndState)
      {
        int tileNumber,xLocation,yLocation;
        String strTileNumber = " " ,strXLocation= " " ,strYLocation= " " ;
        String strTile;
        String[] tiles = strEndState.split("  ");
        
        for(int i=0;i<tiles.length;i++)
        {
            strTile = tiles[i];

            //tile number is between 0 - 9
            if(strTile.length() == 7)
            {
                strTileNumber = strTile.substring(0,1);
                strXLocation = strTile.substring(3,4);
                strYLocation = strTile.substring(5,6); 
            }
            //tile number range is greater and equal than 10
            else if(strTile.length() == 8)
            {
                strTileNumber = strTile.substring(0,2);
                strXLocation = strTile.substring(4,5);
                strYLocation = strTile.substring(6,7);
            }

            // convert string to int
            tileNumber = Integer.parseInt(strTileNumber);
            xLocation = Integer.parseInt(strXLocation);
            yLocation = Integer.parseInt(strYLocation);

            Tile tempTile = new Tile(tileNumber,yLocation-1,xLocation-1);
            //System.out.println(tempTile.toString());
            setTile(tempTile);    
        }
      }
    
    /**
    * for debug
    * 
    */
    
    public void print()
    {
        //System.out.println("The empty " + this.findEmptyTile().toString());
        
        for(int i= 0;i<rows;i++)
        {
             for(int j=0;j<cols;j++)
             {
                 if(board[i][j].getNumber()!=0)
                 {
                    System.out.print(board[i][j].getNumber()+ "\t");
                 }
                 else
                 {
                     System.out.print(" \t");
                 }
             }
             System.out.println("\n");
        }
    }
    
    private void setTile(Tile aTile)
    {
        this.board[aTile.getXLocation()][aTile.getYLocation()] = aTile;
    }
    
    public boolean isMoveable(int direction)
    {
        Tile emptyTile = this.findEmptyTile();
        
        int indexX = emptyTile.getXLocation();
        int indexY = emptyTile.getYLocation();
        
        if((indexX < rows-1 && indexX > 0) && (indexY < cols-1 && indexY > 0))
        {//empty tile is inside 
            return true;
        }
        else if((indexX == 0) && (indexY ==0) && (direction == 0 || direction ==3))
        {//left up corner
            return true;
        }
        else if((indexX == 0) && (indexY == cols-1) && (direction == 0 || direction == 1))
        {//right up corner
            return true;
        }
        else if((indexX == rows-1) && (indexY == cols-1) && (direction == 1 || direction == 2))
        {//right down
            return true;
        }
        else if((indexX == rows-1) && (indexY == 0) && (direction == 2 || direction == 3))
        {//left down
            return true;
        }
        else if(indexX == 0 && indexY != 0 && indexY != cols-1 && direction != 2)
        {
            return true;
        }
        else if(indexY == cols-1 && indexX != 0 && indexX != rows-1 && direction != 3)
        {
            return true;
        }
        else if(indexX == rows-1 && indexY != 0 && indexY != cols-1 && direction != 0)
        {
            return true;
        }
        else if(indexY == 0 && indexX != 0 && indexX != rows-1 && direction != 1)
        {
            return true;
        }
        else
        {
            System.out.println("invalide direction");
        }
        return false;
    }
    
    public Tile findEmptyTile()
    {
        Tile returnTile = null;
        
        for(int i= 0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                if(board[i][j].getNumber() == 0)
                {
                    returnTile = new Tile(0,i,j);
                    return returnTile;
                }
            }
        }
        
        return returnTile;  
    }
      
    /**
     *    0
     *  3   1
     *    2 
     */
    
    public State move(int direction)
    {   
        State nextState = null;
        
        switch (direction)
        {
            case 0: nextState = moveUp();
                    System.out.println("up");
                    break;
            case 1: nextState = moveRight();
                    System.out.println("right");
                    break;
            case 2: nextState = moveDown();
                    System.out.println("down");        
                    break;
            case 3: nextState = moveLeft();
                    System.out.println("left");
                    break;
            default:
                    System.out.println("Invalid Move Number");
                    break;
        }
        
        return new State(nextState);   
        
    }
    
    public State moveUp()
    {
        State nextState = this;
        
        int emptyX = 0,emptyY = 0;
        Tile emptyTile = null;
        
        emptyTile = nextState.findEmptyTile();
        emptyX = emptyTile.getXLocation();
        emptyY = emptyTile.getYLocation();
        
        //swap emptytile with left one
        Tile belowTile = nextState.board[emptyX+1][emptyY];
        nextState.board[emptyX][emptyY].setNumber(belowTile.getNumber());
        nextState.board[emptyX+1][emptyY].setNumber(0);
        
        return new State(nextState);   
    }
    
    public State moveRight()
    {
        
        State nextState = this;
        
        int emptyX = 0,emptyY = 0;
        Tile emptyTile = null;
        
        emptyTile = nextState.findEmptyTile();
        emptyX = emptyTile.getXLocation();
        emptyY = emptyTile.getYLocation();
        
        //swap emptytile with left one
        Tile leftTile = nextState.board[emptyX][emptyY-1];
        nextState.board[emptyX][emptyY].setNumber(leftTile.getNumber());
        nextState.board[emptyX][emptyY-1].setNumber(0);
        
        return new State(nextState);   
        
    }
    
    public State moveDown()
    {
        State nextState = this;
        
        int emptyX = 0,emptyY = 0;
        Tile emptyTile = null;
        
        emptyTile = nextState.findEmptyTile();
        emptyX = emptyTile.getXLocation();
        emptyY = emptyTile.getYLocation();
        
        //swap emptytile with above one
        Tile aboveTile = nextState.board[emptyX-1][emptyY];
        nextState.board[emptyX][emptyY].setNumber(aboveTile.getNumber());
        nextState.board[emptyX-1][emptyY].setNumber(0);
        
        return new State(nextState);   
    }
    
    public State moveLeft()
    {
        
        State nextState = this;
        
        int emptyX = 0,emptyY = 0;
        Tile emptyTile = null;
        
        emptyTile = nextState.findEmptyTile();
        emptyX = emptyTile.getXLocation();
        emptyY = emptyTile.getYLocation();
        
        //swap emptytile with right one
        Tile rightTile = nextState.board[emptyX][emptyY+1];
        nextState.board[emptyX][emptyY].setNumber(rightTile.getNumber());
        nextState.board[emptyX][emptyY+1].setNumber(0);
        
        return new State(nextState);   
    }
}