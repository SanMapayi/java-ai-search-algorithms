import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class BreadthFirstSearch {

    private int node;
    private ArrayList<Integer>[] neighbours;

    public BreadthFirstSearch(int node, ArrayList<Integer>[] neighbours) {
        this.node = node;
        this.neighbours = neighbours;
    }


    public boolean isGoal(int node) {
        return node == 7 || node == 8;
    }

    public int breadthFirstSearch() {
        if (isGoal(node)){
            System.out.println("The goal state has been reached for Node N." + node);
        }

        Deque<Integer> frontier = new LinkedList<>();
        Deque<Integer> reached = new LinkedList<>();

        frontier.add(node);
        System.out.println("The Node N." + node + " has been added to the frontier");


        while (frontier.size() > 0) {
          var node = frontier.pollFirst();

            reached.add(node);
            System.out.println("The Node N." + node + " has been reached");

          System.out.println(" Expanding the frontier");
          for (int child : neighbours[node - 1]) {
              if (isGoal(child)) {
                  return child;
              }
              if (!reached.contains(child)) {
                  reached.add(child);
                  frontier.add(child);
              }
          }
        }

        return -1;
    }

}
