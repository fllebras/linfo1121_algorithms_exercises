package searching;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * In this exercise, you must compute the skyline defined by a set of buildings.
 * When viewed from far away, the buildings only appear as if they were on a two-dimensionnal line.
 * Hence, they can be defined by three integers: The start of the building (left side), the height
 * of the building and the end of the building (right side).
 * For example, a building defined by (2, 5, 4) would look like
 *
 *   xxx
 *   xxx
 *   xxx
 *   xxx
 *   xxx
 * ________
 *
 * Obviously in practice buildings are not on a line, so they can overlap. If we add a new, smaller,
 * building in front of the previous one, defined by (3, 3, 6), then the view looks like:
 *
 *   xxx
 *   xxx
 *   xyyyy
 *   xyyyy
 *   xyyyy
 * ________
 *
 * The skyline is then define as the line that follows the highest building at any given points.
 * Visually, for the above example, it gives:
 *
 *   sss
 *      
 *      ss
 *        
 *        
 * ________
 *
 *
 * We ask you to compute, given a set of building, their skyline.
 */
public class Skyline {

    public static class BuildingPoint implements Comparable<BuildingPoint>{
        int x;
        boolean isStart;
        int height;

        public BuildingPoint(int x, boolean isStart, int height){
            this.x = x;
            this.isStart = isStart;
            this.height = height;
        }

        @Override
        public int compareTo(BuildingPoint o) {
            if(this.x!=o.x){
                return this.x-o.x;
            }else{
                return (this.isStart ? -this.height : this.height) - (o.isStart ? -o.height : o.height);
            }
        }
    }


    /**
     *   The buildings are defined with triplets (left, height, right).
     *         int[][] buildings = {{1, 11, 5}, {2, 6, 7}, {3, 13, 9}, {12, 7, 16}, {14, 3, 25}, {19, 18, 22}, {23, 13, 29}, {24, 4, 28}};
     *
     *         [{1,11},{3,13},{9,0},{12,7},{16,3},{19,18},{22,3},{23,13},{29,0}]
     *
     * @param buildings
     * @return  the skyline in the form of a list of "key points [x, height]".
     *          A key point is the left endpoint of a horizontal line segment.
     *          The key points are sorted by their x-coordinate in the list.
     */
    public static List<int[]> getSkyline(int[][] buildings) {
        BuildingPoint[] points = new BuildingPoint[buildings.length*2];
        int i = 0;
        for(int[] building : buildings){
            points[i]=new BuildingPoint(building[0],true,building[1]);
            points[i+1]=new BuildingPoint(building[2],false,building[1]);
            i+=2;
        }
        Arrays.sort(points);
        List<int[]> skyline = new ArrayList<>();
        TreeMap<Integer,Integer> queue = new TreeMap<>();
        queue.put(0,1);
        int prevMax = 0;
        for(BuildingPoint point : points){
            if(point.isStart){
                queue.compute(point.height,(key,value) -> (value!=null) ? value+1 : 1);
            }else{
                queue.compute(point.height,(key,value) -> (value==1) ? null : value-1);
            }
            int currentMax = queue.lastKey();
            if(prevMax != currentMax){
                skyline.add(new int[]{point.x, currentMax});
                prevMax = currentMax;
            }
        }
        return skyline;
    }

}
