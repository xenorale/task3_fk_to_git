import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
class ModelTest {
    private Model model;
    private List<Vector3f> vertices;
    @BeforeEach
    void setUp() {
        model = new Model();
        vertices = new ArrayList<>();
        vertices.add(new Vector3f(1.0f, 2.0f, 3.0f));
        vertices.add(new Vector3f(4.0f, 5.0f, 6.0f));
        vertices.add(new Vector3f(7.0f, 8.0f, 9.0f));
    }
    @Test
    void testAddPolygon() {
        Polygon polygon = new Polygon(vertices);
        model.addPolygon(polygon);
        assertEquals(1, model.polygons.size(), "Полигон должен быть добавлен");
    }
    @Test
    void testCloneVertices() {
        model.vertices.add(new Vector3f(1, 2, 3));
        model.vertices.add(new Vector3f(4, 5, 6));
        ArrayList<Vector3f> clonedVertices = model.cloneVertices();
        assertEquals(model.vertices.size(), clonedVertices.size(), "Размеры исходного и клонированного списков вершин должны совпадать");
        assertNotSame(model.vertices.get(0), clonedVertices.get(0), "Клонированные вершины должны быть разными объектами");
    }
    @Test
    void testDeletePolygons() {
        Polygon polygon1 = new Polygon(vertices);
        Polygon polygon2 = new Polygon(vertices);
        model.addPolygon(polygon1);
        model.addPolygon(polygon2);
        model.deletePolygon(polygon1);
        assertEquals(1, model.polygons.size(), "После удаления одного полигона должен остаться только один");
        assertEquals(polygon2, model.polygons.get(0), "Оставшийся полигон должен быть вторым");
    }

    @Test
    void testExportToOBJ() {
        model.vertices.add(new Vector3f(1.0f, 2.0f, 3.0f));
        model.normals.add(new Vector3f(0.0f, 1.0f, 0.0f));
        model.textureVertices.add(new Vector2f(0.5f, 0.5f));
        model.exportToOBJ();
    }
}
