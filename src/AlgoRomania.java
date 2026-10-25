import java.util.*;

public class AlgoRomania {

    private String node;
    private Map<String, Map<String, Integer>> neighbours;
    private Set<String> reachedRoute = new LinkedHashSet<>();
    public AlgoRomania(String node, Map<String, Map<String, Integer>> neighbours) {
        this.node = node;
        this.neighbours = neighbours;
    }
    public boolean isGoal(String node) {
        return node.equalsIgnoreCase("Bucharest");
    }

    public String bestFirstSearch()   {
         if (isGoal(node)) return node;

         Deque<String> frontier = new LinkedList<>();
         Set<String> reached = new LinkedHashSet<>();
         ArrayList<String> discarded = new ArrayList<>();

         frontier.add(node);


         while (frontier.size() > 0) {

             var reachedNode = frontier.pollFirst();
             reached.add(reachedNode);
             System.out.println("The city of Node N." + reachedNode + " has been reached");
             System.out.println("The city of Node N." + reachedNode + " has been removed from the frontier");




             var nodesTobeExpanded = neighbours.get(reachedNode);


             for (var child : nodesTobeExpanded.entrySet()) {
                 Collection<Integer> childHeuristicSet = nodesTobeExpanded.values();
                 var heuristicValue = child.getValue();
                 var node = child.getKey();
                 if (isGoal(node) && Collections.max(childHeuristicSet) == heuristicValue) {
                     reached.add(node);
                     System.out.println(reached);
                     reachedRoute = new LinkedHashSet<>(reached);
                        return child.getKey();

                 }
                 if (!reached.contains(node) && Collections.max(childHeuristicSet) == heuristicValue) {
                     frontier.add(node);
                     reached.add(node);
                 }
                 else if  (!reached.contains(node) && Collections.max(childHeuristicSet) > heuristicValue){
                    discarded.add(node);
                    System.out.println(discarded);
                 }
             }
         }

        return null;
    }

    public void routeOutline() {

        ArrayList<String> reachedRouteList = new ArrayList<>(reachedRoute);
        Iterator<String> iterator = reachedRouteList.listIterator(1);

        var town = reachedRouteList.get(0);
        while (iterator.hasNext()){
            var nextTown = iterator.next();
            System.out.println(town + " => " + nextTown);
            town = nextTown;
        }

    }



    public static void main(String[] args) {

        Map<String, Map<String, Integer>> neighbours = new LinkedHashMap<>();


        Map<String, Integer> aradMap = new TreeMap<>();
        neighbours.put("Arad",
                new TreeMap<>() {{
                    put("Zerind", 2);
                    put("Timisoara", 3);
                    put("Sibiu", 7);
                }}
        );

        neighbours.put("Zerind",
                new TreeMap<>() {{
                    put("Arad", 2);
                    put("Lemmy", 3);
                }});

        neighbours.put("Lemmy",
                new TreeMap<>() {{
                    put("Sibiu", 6);
                    put("Zerind", 3);
                }});

        neighbours.put("Sibiu",
                new TreeMap<>() {{
                    put("Rimnicu", 4);
                    put("Fagaras", 5);
                }});

        neighbours.put("Rimicu",
                new TreeMap<>() {{
                    put("Pitesti", 4);
                    put("Gallow", 2);
                }});

        neighbours.put("Pitesti",
                new TreeMap<>() {{
                    put("Bucharest", 9);
                }});

        neighbours.put("Fagaras",
                new TreeMap<>() {{
                    put("Bucharest", 11);
                }});

        AlgoRomania algoRomania = new AlgoRomania("Arad", neighbours);

       String destination = algoRomania.bestFirstSearch();
       System.out.println("Here is the best route to the destination " + destination);
       algoRomania.routeOutline();

    }
}
