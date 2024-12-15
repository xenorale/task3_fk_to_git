import java.util.ArrayList;
import java.util.List;
public class Polygon implements Cloneable {
    private List<Vector3f> vertices;
    private ArrayList<Integer> vertexIndices;
    private ArrayList<Integer> textureVertexIndices;
    private ArrayList<Integer> normalIndices;

    public Polygon(List<Vector3f> vertices) {
        this.vertices = vertices;
        this.vertexIndices = new ArrayList<>();
        this.textureVertexIndices = new ArrayList<>();
        this.normalIndices = new ArrayList<>();
    }

    @Override
    public Polygon clone() {
        try {
            Polygon clonedPolygon = (Polygon) super.clone();
            clonedPolygon.vertexIndices = new ArrayList<>(this.vertexIndices);
            clonedPolygon.textureVertexIndices = new ArrayList<>(this.textureVertexIndices);
            clonedPolygon.normalIndices = new ArrayList<>(this.normalIndices);
            clonedPolygon.vertices = new ArrayList<>(this.vertices);
            return clonedPolygon;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void setVertexIndices(ArrayList<Integer> vertexIndices) {
        this.vertexIndices = vertexIndices;
    }
    public void setTextureVertexIndices(ArrayList<Integer> textureVertexIndices) {
        this.textureVertexIndices = textureVertexIndices;
    }
    public void setNormalIndices(ArrayList<Integer> normalIndices) {
        this.normalIndices = normalIndices;
    }
    public ArrayList<Integer> getVertexIndices() {
        return vertexIndices;
    }
    public ArrayList<Integer> getTextureVertexIndices() {
        return textureVertexIndices;
    }
    public ArrayList<Integer> getNormalIndices() {
        return normalIndices;
    }
    public List<Vector3f> getVertices() {
        return vertices;
    }
    public void addVertex(Vector3f vertex) {
        vertices.add(vertex);
    }
}
