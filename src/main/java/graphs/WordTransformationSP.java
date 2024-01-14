package graphs;

import java.util.*;


/**
 * You are asked to implement the WordTransformationSP
 * class which allows to find the shortest path
 * from a string A to another string B
 * (with the certainty that there is a path from A to B).
 * To do this, we define a rotation(x, y) operation that
 * reverses the order of the letters between the x and y positions (not included).
 * For example, with A=``HAMBURGER``, if we do rotation(A, 4, 8), we get HAMBEGRUR.
 * So you can see that the URGE sub-string
 * has been inverted to EGRU and the rest of the string
 * has remained unchanged: HAMB + ECRU + R = HAMBEGRUR.
 * Let's say that a rotation(x, y) has a cost of y-x.
 * For example going from HAMBURGER to HAMBEGRUR costs 8-4 = 4.
 * The question is what is the minimum cost to reach a string B from A?
 * So you need to implement a public static int minimalCost(String A, String B)
 * function that returns the minimum cost to reach String B
 * from A using the rotation operation.
 */
public class WordTransformationSP {

    /**
     * Rotate the substring between start and end of a given string s
     * eg. s = HAMBURGER, rotation(s,4,8) = HAMBEGRUR i.e. HAMB + EGRU + R
     */
    /**
     * Rotate the substring between start and end of a given string s
     * eg. s = HAMBURGER, rotation(s,4,8) = HAMBEGRUR i.e. HAMB + EGRU + R
     *
     * @param s
     * @param start
     * @param end
     * @return rotated string
     */
    public static String rotation(String s, int start, int end) {
        return s.substring(0, start) + new StringBuilder(s.substring(start, end)).reverse().toString() + s.substring(end);
    }

    static class Entry implements Comparable<Entry>{
        String value;
        int dist;
        Entry(String content, int value){
            this.value = content;
            this.dist = value;
        }

        @Override
        public int compareTo(Entry o) {
            return this.dist - o.dist;
        }
    }

    /**
     * Compute the minimal cost from string "from" to string "to" representing the shortest path
     *
     * @param from
     * @param to
     * @return
     */
    public static int minimalCost(String from, String to) {
        // TODO
        HashMap<String,Integer> distTo = new HashMap<>();
        PriorityQueue<Entry> pq = new PriorityQueue<>();
        pq.add(new Entry(from,0));
        distTo.put(from,0);
        while (!pq.isEmpty()){
            Entry n = pq.poll();
            String v = n.value;
            for(int i=0;i<v.length()-1;i++){
                for(int j=i+2; j<=v.length();j++){
                    String w = rotation(v,i,j);
                    if(!distTo.containsKey(w) || distTo.get(w)>distTo.get(v)+(j-i)){
                        distTo.put(w,distTo.get(v)+(j-i));
                        pq.add(new Entry(w,distTo.get(v)+(j-i)));
                    }
                }
            }
        }
        return distTo.get(to);
    }


}
