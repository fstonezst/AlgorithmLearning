public class Dijkatra{
    public static void main(String[] args){

    }

    public DijResult getShortestPath(Short start,Map<Short,Boolean> V ){
        Map<Short, Integer> distances = new LinkedHashMap<Short, Integer>();//保存目前已知的start到各点的最短距离
        PriorityQueue<MyVertex> nodes = new PriorityQueue<MyVertex>();//排序目前已知的start到各点的最短距离
        Map<Short, MyVertex> previous = new LinkedHashMap<Short, MyVertex>();//保存目前已知的最短路径(后续有可能会变)中每个点的前驱节点
        Map<Short,Boolean> set = new HashMap<Short,Boolean>(); //保存已求出的最短路径中的点，
        /*初始化：distances、nodes、previous
                若与起点有边相连则该点的distances的值为该边的权值，若没有边相连则该点的distance的值为无穷大
                将起点加入nodes中，起点的前驱节点为null加入previous中*/
        for(Short vertex : vertices.keySet()) {
            if (vertex .equals(start)) {
                distances.put(vertex, 0);
                nodes.add(new MyVertex(vertex, 0));
                previous.put(vertex, null); // 1 ++
            } else {
                distances.put(vertex, Integer.MAX_VALUE);
            }
        }

		/*连通路径和上的点已遍历完，或是所有必经节点都已找到连接的最短路径*/
        int count = V.size();//要求终点的个数
        while (!nodes.isEmpty() && count>0) {
            /*因为权值没有负数，所以当前已知的起点到各点的最短路径中权值最小的路径必为起点到达该点的最短路径，
            * 因为如果不是该路径最短，那么就是通过其它比该路径更长的路径到达该点，明显不可能*/

            //从已知路径中选出最短的那条路径，既已求出起点到该点的最短路径，将该点并入最短路径点集中
            MyVertex smallest = nodes.poll();
			/*将该点并入最短路径中*/
            set.put(smallest.getId(),true);
            if(V.get(smallest.getId()) !=null){
				/*找到一个要求的节点的最短路径*/
                --count;
            }

            if (distances.get(smallest.getId()) == Integer.MAX_VALUE) {
                break;
            }

            //其它点的最短路径有可能通过新加入最短路径的点再到达该点会更短
            if(vertices.get(smallest.getId())!=null)
                for (MyVertex neighbor : vertices.get(smallest.getId())) {
/*					if (set.get(neighbor.getId()) != null) {
						 该点已被并入最短路径中
						continue;
					}*/
                    Integer alt = distances.get(smallest.getId()) + neighbor.getDistance();
                    if(distances.get(neighbor.getId())==null)
                        System.out.println();
                    if (alt < distances.get(neighbor.getId())) {
                        distances.put(neighbor.getId(), alt);
                        previous.put(neighbor.getId(), smallest);
                        if(distances.get(neighbor.getId()) == Integer.MAX_VALUE) {
                            nodes.add(new MyVertex(neighbor.getId(), alt));
                        }else {
                            forloop:
                            for (MyVertex n : nodes) {
                                if (n.getId() == neighbor.getId()) {
                                    n.setDistance(alt);
                                    break forloop;
                                }
                            }
                        }
                    }
                }
        }

        return new DijResult(distances, previous);
    }

}
