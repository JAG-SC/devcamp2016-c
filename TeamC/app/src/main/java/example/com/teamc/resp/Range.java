package example.com.teamc.resp;

/**
 * Created by ayijk on 2017/02/14.
 */

public class Range {
    ResultSet ResultSet;
    
    class ResultSet {
        Point[] Point;

        class Point {
            int Minute;
            Prefecture Prefecture;

            class Prefecture {
                String Name;
                String code;
            }

            Station Station;

            class Station {
                String Name;
                String Type;
                String code;
            }

        }
    }
}
