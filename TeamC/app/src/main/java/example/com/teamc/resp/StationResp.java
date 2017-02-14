package example.com.teamc.resp;

/**
 * Created by ayijk on 2017/02/14.
 */

public class StationResp {
    public ResultSet ResultSet;

    public class ResultSet {
        public Point Point;

        public class Point {
            public GeoPoint GeoPoint;

            public class GeoPoint {
                public String gcs;
                public String lati;
                public String lati_d;
                public String longi;
                public String longi_d;
            }

            public Prefecture Prefecture;

            public class Prefecture {
                public String Name;
                public String code;
            }

            public Station Station;

            public class Station {
                public String Name;
                public String Type;
                public String Yomi;
                public String code;
            }
        }
    }
}
