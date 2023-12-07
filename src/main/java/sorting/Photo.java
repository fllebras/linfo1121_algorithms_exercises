package sorting;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * You're a photographer for a soccer meet.
 * You will be taking pictures of pairs of opposing teams.
 * All teams have the same number of players.
 * A team photo consists of a front row of players and
 * a back row of players.
 * In order to be visible, a player in the back row must be taller (not equal thus)
 * than the player in front of him.
 * All players in a row must be from the same team.
 *
 * You must design an algorithm that takes as input two teams (their heights) and checks if it is
 * possible to arrange them to take a photo given the constraints.
 * If so, your method must return the
 * sum of the difference between the height of the aligned players.
 * For example if each team has three players, and their respective heights are [170, 160, 180] and
 * [192, 175, 178], then your method must return 35.
 * If no arrangement can be made, it returns -1.
 *
 * Feel free to use existing java classes.
 */
public class Photo {

    /**
     * This method checks if there is an arrangement of team A and B such that
     * a photo can be taken. If this is the case, it returns the sum of the
     * (absolute) difference between the players placed on the same spot (one
     * behind the other). Your method must run in O(n log(n)) with n the size
     * of the teams.
     *
     * @param teamA height of the players in team A
     * @param teamB height of the players in team B
     * @return the sum of the difference between players on the same spot. If
     *         no arrangement can be found, returns -1
     */
    public static int canTakePictures(int [] teamA, int [] teamB) {
        Arrays.sort(teamA);
        Arrays.sort(teamB);
        int count;
        if(teamA[0]<teamB[0]){
            count = teamB[0]-teamA[0];
            for(int i = 1; i<teamA.length; i++){
                if (teamA[i]>=teamB[i]){
                    return -1;
                }
                count+=teamB[i]-teamA[i];
            }
        }else if(teamA[0]>teamB[0]){
            count = teamA[0]-teamB[0];
            for(int i = 1; i<teamA.length; i++){
                if (teamA[i]<=teamB[i]){
                    return -1;
                }
                count += teamA[i]-teamB[i];
            }
        }else {
            return -1;
        }
        return count;
    }
}