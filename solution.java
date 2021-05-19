import orbital.algorithm.template.*;
import orbital.logic.functor.Function;
import orbital.logic.functor.MutableFunction;
import orbital.math.*;
import java.util.*;
import java.util.zip.*;
import java.io.*;
import java.text.*;
import java.awt.Color;
import util.Basic;

  public class RubiksCube implements GeneralSearchProblem {
  public static final int MAX_STEPS = 16;
  public static final int RANDOM = 0;
  public static final int COMPLEX = 1;
  public static final int STANDARD = 2;
  public static int SEQUENCE = RANDOM;
  public static final boolean RESTRICT_TO_CANONICAL_ACTIONS = true;
  public static final int SIZE = 2;
  public static void main(String arg[])throws Exception {
  DateFormat df = new SimpleDateFormat("H:mm:ss:S");
  df.setTimeZone(TimeZone.getTimeZone("Greenwich/Meantime"));
  Date loadeta;
  Function h;
  try {
   File databaseFile = new File(RubiksCubeCreatePattern.patternDatabaseFile);
   if(!dataBaseFile.exists()) {
     System.err.println("\n File \" "+ databaseFile + "\" Does Not Exist. Will Create One.");
     RubiksCubeCreatePattern.main(arg);
   }
   System.out.println("Loading");
   long loading = System.currentTimeMillis();
   InputStream fis = new FileInputStream(databaseFile);
   if (RubiksCubeCreatePattern.compressed) {
     fis = new InflaterInputStream(fis);
   }
   
   ObjectInputStream is = new ObjectInputStream(fis);
   final int patternDepth = is.readInt();
   final Map patternDatabase = (Map) is.readObject();
   is.close();
   h = new Function() {
     final Real UNDERESTIMATE = Values.getDefaultInstance().valueOf(patternDepth + 1); 
     public Object apply(Object o) {
       return UNDERESTIMATE;
     }
     
   };
   h = new HeuristicAlgorithm.PatternDatabaseHeuristic(h,patternDatabase);
   loadeta = new Date(System.currentTimeMillis() - loading());
   System.out.println("Completed Loading" + df.format(loadeta));
   if (patternDepth != RubiksCubeCreatePattern.MAX_STEPS) {
     System.out.println("Warning: File\" "+ databaseFile + "\"Does Not Seem Up to Date. Consider calling");
     System.out.println("\t java RubiksCubeCreatePattern");
     return;
   }
   System.out.println("Start");
   long Start = System.currentTimeMillis();
   GeneralSearch s;
   s = new IterativeExpansion(h);
   Cube solution = (Cube) s.solve(new RubiksCube(SIZE));
   Date eta = new Date(System.currentTimeMillis() - Start);
   console.color(Color.white);
   if (solution != null) {
     System.out.println("Found \n" + solution + "\n");
     printCubus(solution.feld, 2, 30);
     console.color(Color.white);
     console.LOCATE(4, 9);
     console.print("Total Accumulated cost of" + NumberFormat.getInstance().format(solution.accumulatedCost) + "steps",false);
     }
   
   else {
	   System.out.println("No Solution");
	   console.LOCATE(4,10);
	   console.print("Duration" + df.format(eta), false);
	   console.LOCATE(4,11);
	   console.print("Load Time" + df.format(loadeta), false);
	   
	   
   }
   
   final int left = 0;
   final int back = 1;
   final int top = 2;
   final int front = 3;
   final int right = 4;
   final int down = 5;
   final String names[] = {"L","B", "T", "F", "R", "D"};
   final int orange = 0;
   final int blue = 1;
   final int yellow = 2;
   final int white = 3;
   final int red = 4;
   final int green = 5;
   final Color colors[] = {
		   Color.orange.darker(),
		   Color.blue,
		   Color.yellow.brighter(),
		   Color.white,
		   Color.red,
		   Color.green};
   protected final int size;
private Object blue;
private Object yellow;
private Object red;
private Object white;
   private final Cube_initialState;
   public RubiksCube(int size) {
	   if (size != 2) {
		   throw new InternalError("Only implemented for size 2");
	   }
	   this.size = size;
	   this._initialState = constructInitialState();
	   }
   private Cube constructInitialState() {
	   Cube c = new Cube(size,0.0);
	   switch(SEQUENCE) {
	   case COMPLEX: 
		   c.feld[16] = blue;
		   c.feld[6] = yellow;
		   c.feld[9] = red;
		   c.feld[10] = red;
		   c.feld[19] = white;
		   c.feld[13] = yellow;
		   break;
	   case STANDARD:
		   c.dreht(2,-1); c.dreht(2,-1); c.dreht(1,1); c.dreht(2,1); c.dreht(2,-1); c.dreht(3,-1); c.dreht(2,-1); c.dreht(3,1); c.dreht(2,-1); 
		   break;
	   case RANDOM:
	   {
		   Random random = new Random();
		   final int MIN_ACTION = RESTRICT_2_CANONICAL_ACTIONS? front:left;
		   for (int i = 0; i < MAX_STEPS;i++) {
			   int side = MIN_ACTION + random.nextInt(down-MIN_ACTION + 1);
			   int direction = -1+2*random.nextInt(2);
			   c.dreht(side,direction);
		   }
		   System.out.println(c + "of Maximum Depth" + MAX_STEPS);
		   
	   }
	   break;
	   
	   default: throw new IllegalStateException(SEQUENCE + "is an illegal mode for sequence"); 
	   
	   }
	   c.clearActionLog();
	   return c;
   }
	   }
   }
   }
   
  
  
   
   
   
   
   
		   
    
  }
  
}
  }
  