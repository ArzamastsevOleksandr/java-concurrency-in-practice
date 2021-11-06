package s003.kn_nearest_neighbour.data;

import lombok.Data;

@Data
public class Distance implements Comparable<Distance> {

    double distance;
    int index;

    @Override
    public int compareTo(Distance o) {
        if (this.distance < o.distance) {
            return -1;
        } else if (this.distance > o.distance) {
            return 1;
        }
        return 0;
    }

}
