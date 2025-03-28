package itc475.week5;

import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private static final List<Trail> TRAILS = new ArrayList<>();
    
    static {
        TRAILS.add(new Trail(1, "Frisco Highline Trail", "Springfield to Bolivar, MO", 37.2945, -93.4078, 4.5, 500));
        TRAILS.add(new Trail(2, "Springfield Nature Center Trail", "Springfield, MO", 37.128252, -93.239211, 4.6, 600));
        TRAILS.add(new Trail(3, "Busiek Purple Trail", "20 miles SE of Springfield", 36.9731, -93.2372, 4.4, 200));
        TRAILS.add(new Trail(4, "Bennett Spring State Park Campground", "Lebanon, MO", 37.7270, -92.8567, 4.7, 1200));
        TRAILS.add(new Trail(5, "Roaring River State Park Campground", "Cassville, MO", 36.5781, -93.8339, 4.8, 1500));
        TRAILS.add(new Trail(6, "Table Rock State Park Campground", "Branson, MO", 36.5878, -93.3098, 4.6, 900));
        TRAILS.add(new Trail(7, "Wilson's Creek Battlefield Trails", "Republic, MO", 37.1156, -93.4200, 4.7, 800));
        TRAILS.add(new Trail(8, "Pomme de Terre Lake Campground", "Pittsburg, MO", 37.9000, -93.3167, 4.5, 400));
        TRAILS.add(new Trail(9, "Hercules Glades Wilderness Trails", "Bradleyville, MO", 36.6833, -92.9667, 4.4, 150));
        TRAILS.add(new Trail(10, "Silver Dollar City Campground", "Branson, MO", 36.6719, -93.3269, 4.3, 700));
        TRAILS.add(new Trail(11, "Mark Twain National Forest - Cobb Ridge", "Chadwick, MO", 36.9286, -93.0586, 4.3, 100));
        TRAILS.add(new Trail(12, "Sac River Mountain Bike Trails", "Springfield, MO", 37.2590, -93.3570, 4.5, 250));
        TRAILS.add(new Trail(13, "Stockton State Park Campground", "Stockton, MO", 37.6140, -93.7560, 4.6, 350));
        TRAILS.add(new Trail(14, "Lake of the Ozarks State Park Campground", "Kaiser, MO", 38.0986, -92.6167, 4.7, 1800));
        TRAILS.add(new Trail(15, "Piney Creek Wilderness Trails", "Shell Knob, MO", 36.6167, -93.6667, 4.3, 80));
        TRAILS.add(new Trail(16, "Cooper Creek Campground", "Branson, MO", 36.6240, -93.2830, 4.4, 300));
        TRAILS.add(new Trail(17, "James River Greenway Trail", "Springfield, MO", 37.1920, -93.2800, 4.5, 400));
        TRAILS.add(new Trail(18, "Niangua River Oasis Campground", "Bennett Spring Area", 37.7850, -92.9330, 4.4, 200));
        TRAILS.add(new Trail(19, "Mill Creek Campground", "Wheatland, MO", 37.8833, -93.3833, 4.5, 250));
        TRAILS.add(new Trail(20, "Galloway Creek Greenway Trail", "Springfield, MO", 37.173201, -93.241953, 4.6, 350));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        String query = request.getParameter("q");
        if (query == null || query.trim().isEmpty()) {
            out.print("");
            return;
        }

        StringBuilder result = new StringBuilder();
        if (query.equalsIgnoreCase("popular")) {
            for (Trail trail : TRAILS) {
                if (result.length() > 0) result.append("\n");
                result.append(trail.trailId).append("|")
                      .append(trail.name).append("|")
                      .append(trail.location).append("|")
                      .append(trail.latitude).append("|")
                      .append(trail.longitude).append("|")
                      .append(trail.rating).append("|")
                      .append(trail.reviews);
            }
        } else {
            for (Trail trail : TRAILS) {
                if (trail.name.toLowerCase().contains(query.toLowerCase())) {
                    if (result.length() > 0) result.append("\n");
                    result.append(trail.trailId).append("|")
                          .append(trail.name).append("|")
                          .append(trail.location).append("|")
                          .append(trail.latitude).append("|")
                          .append(trail.longitude).append("|")
                          .append(trail.rating).append("|")
                          .append(trail.reviews);
                }
            }
        }
        out.print(result.toString());
    }
}

class Trail implements Serializable {
    private static final long serialVersionUID = 1L;

    int trailId;
    String name;
    String location;
    double latitude;
    double longitude;
    double rating;
    int reviews;

    Trail(int trailId, String name, String location, double latitude, double longitude, double rating, int reviews) {
        this.trailId = trailId;
        this.name = name;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = rating;
        this.reviews = reviews;
    }
}