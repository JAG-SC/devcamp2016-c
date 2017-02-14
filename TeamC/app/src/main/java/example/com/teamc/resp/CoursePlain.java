package example.com.teamc.resp;

/**
 * Created by ayijk on 2017/02/14.
 */

public class CoursePlain {
    public ResultSet ResultSet;

    public class ResultSet {
        public Course[] Course;

        public class Course {
            public Price[] Price;

            public class Price {
                public String Oneway;
                public String Round;
                public String Type;
                public String kind;
            }
        }
    }
}
