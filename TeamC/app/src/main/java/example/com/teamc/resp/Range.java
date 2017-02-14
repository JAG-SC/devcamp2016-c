package example.com.teamc.resp;

/**
 * Created by ayijk on 2017/02/14.
 */

public class Range {
    public ResultSet ResultSet;

    public class ResultSet {
        public Point[] Point;

        public class Point {
            public int Minute;
            public Prefecture Prefecture;

            public class Prefecture {
                public String Name;
                public String code;
            }

            public Station Station;

            public class Station {
                public String Name;
                public String Type;
                public String code;
            }

        }
    }
}
