import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class PolygonTest {
    @Test
    public void testCreatePolygon() {
        List<Vector3f> vertices = Arrays.asList(
                new Vector3f(0, 0, 0),
                new Vector3f(1, 0, 0),
                new Vector3f(0, 1, 0)
        );
        Polygon polygon = new Polygon(vertices);
        assertNotNull(polygon);
        assertEquals(3, polygon.getVertices().size());
    }
    @Test
    public void testAddVertex() {
        Polygon polygon = new Polygon(new ArrayList<>());
        polygon.addVertex(new Vector3f(0, 0, 0));
        assertEquals(1, polygon.getVertices().size());
    }
}