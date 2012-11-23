public class Tile  
{
    private int number;
    private int xLocation;
    private int yLocation;

    public Tile(int number, int xLocation, int yLocation) 
    {
        this.number = number;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }

    public Tile() 
    {
        this.number = 1;
        this.xLocation = 1;
        this.yLocation = 1;
    }
    
    public Tile(Tile that)
    {
        this.number = that.number;
        this.xLocation = that.xLocation;
        this.yLocation = that.yLocation;
    }
    
    public boolean isEqual(Tile that)
    {
        if(this.number == that.number &&
           this.xLocation == that.xLocation && 
           this.yLocation == that.yLocation )
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Copy
     * @param that another Tile object
     */
    public void copy(Tile that)
    {
        this.number = that.number;
        this.xLocation = that.xLocation;
        this.yLocation = that.yLocation;
    }
    
    @Override
    public String toString()
    {
        return "tile: "+ number + "("+ xLocation +","+ yLocation+")";
    }
    
    public int getNumber()
    {
        return number;
    }
    
    public void setNumber(int number)
    {
        this.number = number;
    }

    public int getXLocation()
    {
            return this.xLocation;
    }

    public int getYLocation()
    {
            return this.yLocation;
    }

    public void setXLocation(int xLocation)
    {
            this.xLocation = xLocation;
    }

    public void setYLocation(int yLocation)
    {
            this.yLocation = yLocation;
    }


}
