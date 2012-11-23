import java.io.*;

public class Search 
{
    public static void main(String[] args) 
    {
        System.out.println("HIT3002 Assignment 1: n-Puzzle");
        
        String method = args[0];
        String inputFileName = args[1];
        String outputFileName = "";
        
        BFS _bfs = null;
        DFS _dfs = null;
        
        System.out.println(args[0]);
        System.out.println(args[1]);
        
        State startState;
        State endState;
        int rows,cols;

        String strRows = "";
        String strCols = "";
        String strStartState = " ";
        String strEndState = " ";
        
        //Create IO object
        DataOutputStream output;
        DataInputStream input;
        BufferedReader br;
        BufferedWriter bw;  
        
        //read input file 
        try 
        {
            //check is the file exist.
            input = new DataInputStream(new FileInputStream(inputFileName));  
            br = new BufferedReader(new InputStreamReader(input));
            strRows = br.readLine();    //read number of rows
            strCols = br.readLine();    //read number of columns
            strStartState = br.readLine();  
            strEndState = br.readLine();
            //Close the input stream
            input.close();
        }
        catch (Exception error) 
        {
            System.out.println(error);
        }

        rows = Integer.parseInt(strRows);
        cols = Integer.parseInt(strCols);
        
        startState = new State(rows,cols);
        startState.configStartState(strStartState);
        //startState.print();
        
        endState = new State(rows,cols);
        endState.configEndState(strEndState);
        //endState.print();

        /*
        State nextState = new State(startState);
        System.out.println("=======start state===========");
        nextState.print();
        nextState = nextState.move(2);
        System.out.println("==============================");
        startState.print();
        System.out.println("==============================");
        nextState.print();
        */
        
        if(method.equals("BFS"))
        {
            System.out.println("start search with BFS");
            _bfs = new BFS(startState,endState);
            _bfs.solve();
        }
        else if(method.equals("DFS"))
        {
            System.out.println("Start search with DFS");
            _dfs = new DFS(startState,endState);
            _dfs.solve();
        }
    }
}