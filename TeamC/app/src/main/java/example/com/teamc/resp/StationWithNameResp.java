package example.com.teamc.resp;

import java.util.List;

/**
 * Created by ayijk on 2017/02/14.
 */

public class StationWithNameResp {
    public ResultSet ResultSet;

    public class ResultSet {
        public List<Point> Point;


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
                //public String Type;
                //public Object Type;
                public String Yomi;
                public String code;
            }
        }
    }
}
