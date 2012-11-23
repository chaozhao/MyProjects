import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class BFS 
{
    private State startState;
    private State endState;
    private LinkedList<State> fringe;
    private Random generator;
    private ArrayList solution;
    
    public BFS(State _startState, State _endState)
    {
        this.generator = new Random();
        this.startState = _startState;
        this.endState = _endState;
        this.fringe = new LinkedList<State>();
    }
    
    public int getRandomNumber()
    {
        return generator.nextInt(4);
    }
    
    /**
     * Add node to tail of Fringe
     * @param aNode 
     */
    public void addToFringe(State aNode)
    {
        fringe.offer(aNode);
    }
    
    /**
     * remove head node of Fringe
     * and return
     */
    public State removeFromFringe()
    {
        return fringe.poll();
    }
    
    /**
     * return head node of Fringe
     * @return head node of Fringe
     */
    public State examineHeadofFringe()
    {
        return fringe.peek();
    }
    
    /**
     * get size 
     * @return the size of Fringe
     */
    public int getFringeSize()
    {
        return fringe.size();
    }
    
    public LinkedList<Integer> getAvaDirections(State currentState)
    {
        LinkedList <Integer> directions = new LinkedList<Integer>();
        LinkedList <Integer> available_dir = new LinkedList<Integer>();
        
        directions.add(new Integer(0));
        directions.add(new Integer(1));
        directions.add(new Integer(2));
        directions.add(new Integer(3));
        
        Collections.shuffle(directions);
        
        for(int i=0;i<directions.size();i++)
        {            
            if(currentState.isMoveable(directions.get(i).intValue()))
            {
                available_dir.add(directions.get(i));
                System.out.println(directions.get(i).intValue());
            }
        }
        
        return available_dir; 
    }
    
    
    /**
     * Solve the puzzle
     */
    
    public void solve()
    {
        LinkedList <Integer> directions = new LinkedList<Integer>();
        
        this.addToFringe(this.startState);
        State currentState = null;
        State nextState = null;
        
        
        System.out.println("pushed current node to queue");
        
        do
        {
            if(getFringeSize() > 0)
            {
                currentState = removeFromFringe();
                
            }
            currentState.print();
            directions = getAvaDirections(currentState);
            
            for (int i=0; i < directions.size(); i++)
             {
                nextState = currentState.move(directions.get(i).intValue());
                addToFringe(nextState);
                nextState.print();
                
             }
            
            //TODO: get and remove head of queue
            //set to currentState
  
        } while(!nextState.isEqual(endState));
    }
    
    public void makeSolution()
    {
        
    }
    
    public boolean isGoalState(State endState)
    {
        return this.endState == endState;
    }
    
    
    
}
