package graphs;


/**
 * In this exercise, we revisit the GlobalWarming
 * class from the sorting package.
 * You are still given a matrix of altitude in
 * parameter of the constructor, with a water level.
 * All the entries whose altitude is under, or equal to,
 * the water level are submerged while the other constitute small islands.
 *
 * For example let us assume that the water
 * level is 3 and the altitude matrix is the following
 *
 *      | 1 | 3 | 3 | 1 | 3 |
 *      | 4 | 2 | 2 | 4 | 5 |
 *      | 4 | 4 | 1 | 4 | 2 |
 *      | 1 | 4 | 2 | 3 | 6 |
 *      | 1 | 1 | 1 | 6 | 3 |
 *
 * If we replace the submerged entries
 * by _, it gives the following matrix
 *
 *      | _ | _ | _ | _ | _ |
 *      | 4 | _ | _ | 4 | 5 |
 *      | 4 | 4 | _ | 4 | _ |
 *      | _ | 4 | _ | _ | 6 |
 *      | _ | _ | _ | 6 | _ |
 *
 * The goal is to implement two methods that
 * can answer the following questions:
 *      1) Are two entries on the same island?
 *      2) How many islands are there
 *
 * Two entries above the water level are
 * connected if they are next to each other on
 * the same row or the same column. They are
 * **not** connected **in diagonal**.
 * Beware that the methods must run in O(1)
 * time complexity, at the cost of a pre-processing in the constructor.
 * To help you, you'll find a `Point` class
 * in the utils package which identified an entry of the grid.
 * Carefully read the expected time complexity of the different methods.
 */
public class GlobalWarming {

    int nIsland;
    Point[][] id;
    int[][] sz;
    boolean[][] island;

    int[][] ccid;
    int rows, cols;
    int[][] altitude;
    int waterLevel;



    /**
     * Constructor. The run time of this method is expected to be in
     * O(n x log(n)) with n the number of entry in the altitude matrix.
     *
     * @param altitude the matrix of altitude
     * @param waterLevel the water level under which the entries are submerged
     */
    public GlobalWarming(int [][] altitude, int waterLevel) {
        this.altitude=altitude;
        this.waterLevel=waterLevel;
        rows = altitude.length;
        cols = altitude[0].length;
        island=new boolean[rows][cols];
        ccid=new int[rows][cols];
        for(int x = 0; x<rows; x++){
            for(int y = 0; y<cols; y++){
                if(altitude[x][y] > waterLevel){
                    if(!island[x][y]){
                        dfs(x,y);
                        nIsland++;
                    }
                }
            }
        }
        //with union-find
        /*
        int l = altitude[0].length;
        island=new boolean[altitude.length][l];
        id = new Point[altitude.length][l];
        nIsland = altitude.length*l;
        sz = new int[altitude.length][l];
        for(int i = 0; i<altitude.length; i++){
            for(int j = 0; j<l; j++){
                id[i][j]= new Point(i,j);
                sz[i][j]=1;
            }
        }

        for(int i = 0; i<altitude.length-1; i++){
            for(int j = 0; j<l-1; j++){
                if(altitude[i][j]<=waterLevel){
                    nIsland--;
                    island[i][j]=false;
                }else{
                    island[i][j]=true;
                    if((altitude[i][j]>waterLevel && altitude[i][j+1]>waterLevel)&&(!onSameIsland(id[i][j],id[i][j+1]))){
                        union(id[i][j],id[i][j+1]);
                    }
                    if((altitude[i][j]>waterLevel && altitude[i+1][j]>waterLevel)&&(!onSameIsland(id[i][j],id[i+1][j]))){
                        union(id[i][j],id[i+1][j]);
                    }
                }
            }
            if(altitude[i][l-1]<=waterLevel){
                nIsland--;
                island[i][l-1]=false;
            }else{
                island[i][l-1]=true;
                if((altitude[i][l-1]>waterLevel && altitude[i+1][l-1]>waterLevel)&&(!onSameIsland(id[i][l-1],id[i+1][l-1]))){
                    union(id[i][l-1],id[i+1][l-1]);
                }
            }
        }
        for(int j=0;j<l-1; j++){
            if(altitude[altitude.length-1][j]<=waterLevel){
                nIsland--;
                island[altitude.length-1][j]=false;
            }else {
                island[altitude.length-1][j]=true;
                if ((altitude[altitude.length-1][j]>waterLevel && altitude[altitude.length-1][j+1]>waterLevel) && (!onSameIsland(id[altitude.length-1][j], id[altitude.length-1][j+1]))) {
                    union(id[altitude.length-1][j], id[altitude.length-1][j+1]);
                }
            }
        }
        if(altitude[altitude.length-1][l-1]<=waterLevel){
            nIsland--;
            island[altitude.length-1][l-1]=false;
        }else{
            island[altitude.length-1][l-1]=true;
        }

         */
    }

    private void dfs(int x, int y){
        island[x][y] = true;
        ccid[x][y] = nIsland;

        final int[][] pos = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        for (int i = 0; i < 4; i++) {
            int px = pos[i][0];
            int py = pos[i][1];
            int neiX = x+px;
            int neiY = y+py;

            if ((0 <= neiX && neiX < rows) && (0 <= neiY && neiY < cols) && (altitude[neiX][neiY] > waterLevel)) {
                if(!island[neiX][neiY]){
                    dfs(neiX,neiY);
                }
            }
        }
    }

    private Point find(Point p) {
        // Follow links to find a root.
        while (!p.equals(id[p.x][p.y])) p = id[p.x][p.y];
        return p;
    }
    public void union(Point p, Point q) {
        Point i = find(p);
        Point j = find(q);
        if (i.equals(j)) return;
        // Make smaller root point to larger one.
        if (sz[i.x][i.y] < sz[j.x][j.y]) { id[i.x][i.y] = j; sz[j.x][j.y] += sz[i.x][i.y]; }
        else { id[j.x][j.y] = i; sz[i.x][i.y] += sz[j.x][j.y]; }
        nIsland--;
    }



    /**
     * Returns the number of island
     *
     * Expected time complexity O(1)
     */
    public int nbIslands() {
        return nIsland;
    }

    /**
     * Return true if p1 is on the same island as p2, false otherwise
     *
     * Expected time complexity: O(1)
     *
     * @param p1 the first point to compare
     * @param p2 the second point to compare
     */
    public boolean onSameIsland(Point p1, Point p2) {
        if(!island[p1.x][p1.y] || !island[p2.x][p2.y]){
            return false;
        }
        return ccid[p1.x][p1.y]==ccid[p2.x][p2.y];

        //with union-find
        /*
        if(!island[p1.x][p1.y] || !island[p2.x][p2.y]){
            return false;
        }
        return find(p1).equals(find(p2));

         */
    }


    /**
     * This class represent a point in a 2-dimension discrete plane. This is used, for instance, to
     * identified cells of a grid
     */
    static class Point {

        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Point) {
                Point p = (Point) o;
                return p.x == this.x && p.y == this.y;
            }
            return false;
        }
    }
}