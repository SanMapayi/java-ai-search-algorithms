import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;


public class BFS {
    public int state;
    public ArrayList<Integer>[] neighbors;

    public BFS(int initialState, ArrayList<Integer>[] neighbors){
        this.state = initialState;
        this.neighbors = neighbors;
    }

    public boolean isGoal(int node) {
        if (node == 7 || node == 8) {
            return true;
        }
        return false;
    }

    public int breadthSearch() {

        int node = state;
        if (isGoal(node)) {
            return node;
        }



        Deque<Integer> frontier = new LinkedList<>();
        Deque<Integer> reached = new LinkedList<>();
        System.out.println("Initiating search from node N." + node);
        frontier.add(node);

        while (frontier.size() > 0) {
            node = frontier.getLast();
            System.out.println("Node N." + node + " was reached.");
            reached.add(node);
            System.out.println("REACHED  =>   " + reached);
            // add 1 to the reach
            System.out.println("removing node N." + node + " from frontier");
            frontier.pollLast();
            System.out.println("FRONTIER  =>   " + frontier);
            //frontier is now empty after removing one.
            System.out.println("Expanding state N." + node);
            //expanding one
            for(int child: neighbors[node-1]) {
                //1,2,3
                if (isGoal(child)) {
                    System.out.println("checking node neighbor state N." + child);
                    return child;
                }
                if (!reached.contains(child)) {
                    System.out.println("Adding node N." + child + " to reached.");
                    reached.add(child);
                    //add 2
                    //add 3
                    System.out.println("Adding node N." + child + " to frontier.");
                    frontier.add(child);
                    //add 2
                    //add 3
                }
            }

        }
        return -1;
    }
    public static void main(String[] args) {
        int numOfStates = 8;
        ArrayList<Integer>[] neighbors = new ArrayList[numOfStates];

        for(int i =0; i<numOfStates; i++) {
            neighbors[i] = new ArrayList<>();
        }

        neighbors[0].add(1);
        neighbors[0].add(2);
        neighbors[0].add(3);
        neighbors[1].add(1);
        neighbors[1].add(2);
        neighbors[1].add(6);
        neighbors[2].add(3);
        neighbors[2].add(4);
        neighbors[2].add(3);
        neighbors[3].add(3);
        neighbors[3].add(4);
        neighbors[3].add(8);
        neighbors[4].add(5);
        neighbors[4].add(6);
        neighbors[4].add(7);
        neighbors[5].add(5);
        neighbors[5].add(6);
        neighbors[5].add(6);
        neighbors[6].add(7);
        neighbors[6].add(8);
        neighbors[6].add(7);
        neighbors[7].add(7);
        neighbors[7].add(8);
        neighbors[7].add(8);
        long startTime = System.currentTimeMillis();

        BFS bfs = new BFS(1, neighbors);
        int result = bfs.breadthSearch();
        System.out.print("Result = " + result);
        long endTime = System.currentTimeMillis();

        long elapseTime = endTime - startTime;
        System.out.println(elapseTime);
    }
}
