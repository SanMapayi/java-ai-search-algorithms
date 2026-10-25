import java.util.*;

public class Robot {

    static class Node {

        private char node;
        private int heuristic;
        private int stepCost;
        public Node(char node, int heuristic) {
            this.node = node;
            this.heuristic = heuristic;

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node node1)) return false;

            if (node != node1.node) return false;
            return heuristic == node1.heuristic;
        }

        @Override
        public int hashCode() {
            int result = node;
            result = 31 * result + heuristic;
            return result;
        }

        public String toString() {
            return "Node " + node;
        }
    }

    static class State {
        private final Node firstNeighbour;
        private final Node secondNeighbour;
        private final Node stateNode;
        private final int firstCost;
        private final int secondCost;

        public State(Node stateNode, Node firstNeighbour, Node secondNeighbour, int firstCost, int secondCost ) {
            this.stateNode = stateNode;
            this.firstNeighbour = firstNeighbour;
            this.secondNeighbour = secondNeighbour;
            this.firstCost = firstCost;
            this.secondCost = secondCost;
        }

        public Node transition(ArrayList<Node> reached) {
            ArrayList<Node> consistent = new ArrayList<>();
            //h(A) ≤ c(A, B) + h(B);
            int a = 0;
            int b = 0;
            //checking if consistency is satisfied from Node A to Node B.
            if (reached.contains(firstNeighbour)) return secondNeighbour;
            if(reached.contains(secondNeighbour)) return firstNeighbour;
            if (stateNode.heuristic <= firstCost + firstNeighbour.heuristic) {
                a = stateNode.heuristic - (firstCost + firstNeighbour.heuristic);
                consistent.add(firstNeighbour);
            }
            if (stateNode.heuristic <= secondCost + secondNeighbour.heuristic) {
                b = stateNode.heuristic - (secondCost + secondNeighbour.heuristic);
                consistent.add(secondNeighbour);
            }
            if (consistent.size() == 2) {
                if ( a > b) return firstNeighbour;
                else if (b > a) return secondNeighbour;
                else {
                    return consistent.get(new Random().nextInt(0, 2));
                }
            }
           else if (consistent.size() == 1) {
               return  consistent.get(0);
            }
           else {
               if (firstNeighbour.heuristic < secondNeighbour.heuristic) return firstNeighbour;
               else  return secondNeighbour;
            }

        }

        public String toString() {
            return "State" + stateNode;
        }

    }

    public Map<Node, State> dataHousing() {

        Node A = new Node('A', 4);
        Node B = new Node('B', 6);
        Node C = new Node('C', 3);
        Node D = new Node('D', 5);
        Node E = new Node('E', 7);
        Node F = new Node('F', 4);
        Node G = new Node('G', 3);
        Node H = new Node('H', 5);
        Node I = new Node('H', 2);
        Node J = new Node('H', 0);



        State stateA = new State(A, B, C, 3, 5);
        State stateB = new State(B, D, E, 1, 7);
        State stateC = new State(C, D, A, 4, 5);
        State stateD = new State(D, F, B, 4, 1);
        State stateE = new State(E, F, B, 5, 7);
        State stateF = new State(F, G, E, 5, 5);
        State stateG = new State(G, H, F, 5, 5);
        State stateH = new State(H, I, G, 2, 5);
        State stateI = new State(I, J, H, 3, 2);
        State stateJ = new State(J, J, I, 0, 3);

       return  new TreeMap<>(Comparator.comparing(Node::toString)) {{
           put(A, stateA);
           put(B, stateB);
           put(C, stateC);
           put(D, stateD);
           put(E, stateE);
           put(F, stateF);
           put(G, stateG);
           put(H, stateH);
           put(I, stateI);
           put(J, stateJ);
       }};
    }

    public boolean isGoal(char name) {
        return name == 'J';
    }


    public char aStarSearch(Node startNode) {
        if (isGoal(startNode.node)) return startNode.node;

        var mapNodesStates = dataHousing();
        Deque<Node> frontier = new LinkedList<>();
        Deque<Node> reached = new LinkedList<>();
        frontier.add(startNode);

        while (frontier.size() > 0) {
            var node = frontier.pollFirst();
            System.out.println("The node N." + node + " has been removed from the frontier");
            System.out.println("The node N." + node + " has been reached");
            reached.add(node);

            var nextState = mapNodesStates.get(node);
            System.out.println("Next state " + nextState);

            if (isGoal(nextState.stateNode.node)) {
                System.out.println("reached" + reached);
                return nextState.stateNode.node;
            }
            Node transitionedToNode = nextState.transition(new ArrayList<>(reached));
            if (!reached.contains(transitionedToNode)) {
                frontier.add(transitionedToNode);
                System.out.println("This is the next node N. " + transitionedToNode +"  to be transitioned");
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        char destination = new Robot().aStarSearch(new Node('A', 3));

        System.out.println("We have reached the destination " + destination);
    }
}
