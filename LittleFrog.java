import java.util.*;
public class LittleFrog {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int p = in.nextInt();
        int[][]map = new int[n][m];
        for (int x = 0; x < n; x++)
            for (int y = 0; y < m; y++)
                map[x][y]= in.nextInt();
        Result result = getShortestPath(map,p);
        if(result  == null) {
            System.out.println("Can not escape!");
            return;
        }
        String v = new MyVertex(0,m-1,0).toString();
        Stack<String> s = new Stack<>();
        while(v!=null){
            s.push(v);
            v=result.previous.get(v);
        }
        while(!s.isEmpty())
        {
            System.out.print(s.pop()+",");
        }
    }

    public static Result  getShortestPath(int[][] map,int p) {
        Map<String, Integer> distances = new LinkedHashMap<String, Integer>();
        PriorityQueue<MyVertex> nodes = new PriorityQueue<MyVertex>();
        Map<String, String> previous = new LinkedHashMap<String, String>();
        HashSet<String> set = new HashSet<String>();

        MyVertex start = new MyVertex(0,0,0);
        distances.put(start.toString(), 0);
        nodes.add(start);
        previous.put(start.toString(), null);

        while (!nodes.isEmpty() ) {
            MyVertex smallest = nodes.poll();
            set.add(smallest.toString());
            if (distances.get(smallest.toString())>p)
                break;
            if (smallest.getX() == 0 && smallest.getY() == map[0].length-1)
                break;
            if (distances.get(smallest.toString()) == null) {
                break;
            }

//            if (vertices.get(smallest.toString()) != null)
            ArrayList<MyVertex> neighbors = new ArrayList<>();
            if (smallest.getX() != 0 && map[smallest.getX()-1][smallest.getY()] != 0)
               neighbors.add(new MyVertex(smallest.getX()-1,smallest.getY(),3));
            if (smallest.getX() <map.length-1 && map[smallest.getX()+1][smallest.getY()] != 0)
                neighbors.add(new MyVertex(smallest.getX()+1,smallest.getY(),0));
            if (smallest.getY() != 0 && map[smallest.getX()][smallest.getY()-1] != 0)
                neighbors.add(new MyVertex(smallest.getX(),smallest.getY()-1,1));
            if (smallest.getY() <map[smallest.getX()].length-1 && map[smallest.getX()][smallest.getY()+1] != 0)
                neighbors.add(new MyVertex(smallest.getX(),smallest.getY()+1,1));

                for (MyVertex neighbor :neighbors)
                {
/*					if (set.contains(neighbor.toString()) != null) {
						continue;
					}*/
                    Integer alt = distances.get(smallest.toString()) + neighbor.getDistance();
                    if ((distances.get(neighbor.toString()) == null)||alt < distances.get(neighbor.toString())) {
                        if (distances.get(neighbor.toString()) == null) {
                            neighbor.setDistance(alt);
                            nodes.add(neighbor);
                        } else {
                            forloop:
                            for (MyVertex n : nodes) {
                                if (n.toString() == neighbor.toString()) {
                                    n.setDistance(alt);
                                    break forloop;
                                }
                            }
                        }
                        distances.put(neighbor.toString(), alt);
                        previous.put(neighbor.toString(), smallest.toString());
                    }
                }
        }
        if (set.contains(new MyVertex(0,map[0].length-1,0).toString()))
            return new Result(distances, previous);
        else
            return null;
    }
}
class Result{
    Map<String, Integer> distances ;
    Map<String, String> previous;

    public Map<String, Integer> getDistances() {
        return distances;
    }

    public Map<String, String> getPrevious() {
        return previous;
    }

    public Result(Map<String, Integer> distances, Map<String, String> previous) {
        this.distances = distances;
        this.previous = previous;
    }
}
class MyVertex implements Comparable<MyVertex>{
    int x;
    int y;
    int distance;
    public MyVertex(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getX() {

        return x;
    }

    public int getY() {
        return y;
    }

    public int getDistance() {
        return distance;
    }
    public int compareTo(MyVertex o)
    {
        return this.distance < o.getDistance()? -1:this.distance==o.getDistance()?0:1;
    }
    public String toString(){
        return "["+this.x+","+this.y+"]";
    }
    public int hashCode(){
        final int prime = 7;
        int result =1;
        result = prime * this.x + this.y;
        return result;
    }
}