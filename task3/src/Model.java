import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Model implements Cloneable {
    public ArrayList<Vector3f> vertices = new ArrayList<>();
    public ArrayList<Vector2f> textureVertices = new ArrayList<>();
    public ArrayList<Vector3f> normals = new ArrayList<>();
    public ArrayList<Polygon> polygons = new ArrayList<>();
    private Set<Vector3f> freeVertices;

    public Model() {
        this.polygons = new ArrayList<>();
        this.vertices = new ArrayList<>();
        this.freeVertices = new HashSet<>();
    }

    public void addPolygon(Polygon polygon) {
        this.polygons.add(polygon);
    }

    public void deletePolygon(Polygon polygon) {
        ArrayList<Vector3f> verticesToDelete = new ArrayList<>();
        ArrayList<Integer> indicesToDelete = new ArrayList<>();
        for (Polygon p : polygons) {
            if (p == polygon) {
                List<Vector3f> polygonVertices = p.getVertices();
                for (Vector3f vertex : polygonVertices) {
                    verticesToDelete.add(vertex);
                    indicesToDelete.add(vertices.indexOf(vertex));
                }
            }
        }
        polygons.remove(polygon);

        for (Vector3f vertex : verticesToDelete) {
            if (!isVertexUsed(vertex)) {
                freeVertices.add(vertex);
            }
        }

        updateVertices(indicesToDelete);
        for (Vector3f freeVertex : freeVertices) {
            deleteVertex(freeVertex);
        }
    }

    public void deleteVertex(Vector3f vertex) {
        if (vertices.contains(vertex)) {
            int vertexIndex = vertices.indexOf(vertex);
            vertices.remove(vertexIndex);
        }
    }

    private boolean isVertexUsed(Vector3f vertex) {
        for (Polygon p : polygons) {
            if (p.getVertices().contains(vertex)) {
                return true;
            }
        }
        return false;
    }

    private void updateVertices(ArrayList<Integer> deletedIndices) {
        ArrayList<Vector3f> updatedVertices = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            if (!deletedIndices.contains(i)) {
                updatedVertices.add(vertices.get(i));
            }
        }
        vertices = updatedVertices;
        for (Polygon p : polygons) {
            for (int i = 0; i < p.getVertices().size(); i++) {
                int updatedIndex = vertices.indexOf(p.getVertices().get(i));
                p.getVertexIndices().set(i, updatedIndex);
            }
        }
    }

    public ArrayList<Vector3f> cloneVertices() {
        ArrayList<Vector3f> clonedVertices = new ArrayList<>();
        for (Vector3f vertex : this.vertices) {
            clonedVertices.add(vertex.clone());
        }
        return clonedVertices;
    }

    public ArrayList<Vector2f> cloneTextureVertices() {
        ArrayList<Vector2f> clonedTextureVertices = new ArrayList<>();
        for (Vector2f textureVertex : this.textureVertices) {
            clonedTextureVertices.add(textureVertex.clone());
        }
        return clonedTextureVertices;
    }

    public ArrayList<Vector3f> cloneNormals() {
        ArrayList<Vector3f> clonedNormals = new ArrayList<>();
        for (Vector3f normal : this.normals) {
            clonedNormals.add(normal.clone());
        }
        return clonedNormals;
    }

    public ArrayList<Polygon> clonePolygons() {
        ArrayList<Polygon> clonedPolygons = new ArrayList<>();
        for (Polygon polygon : this.polygons) {
            clonedPolygons.add(polygon.clone());
        }
        return clonedPolygons;
    }

    @Override
    public Model clone() {
        Model clonedModel = new Model();
        clonedModel.vertices = this.cloneVertices();
        clonedModel.textureVertices = this.cloneTextureVertices();
        clonedModel.normals = this.cloneNormals();
        clonedModel.polygons = this.clonePolygons();
        return clonedModel;
    }

    public void exportToOBJ() {
        for (Vector3f vertex : vertices) {
            System.out.printf("v %.6f %.6f %.6f%n", vertex.getX(), vertex.getY(), vertex.getZ());
        }
        for (Vector3f normal : normals) {
            System.out.printf("vn %.6f %.6f %.6f%n", normal.getX(), normal.getY(), normal.getZ());
        }
        for (Vector2f textureVertex : textureVertices) {
            System.out.printf("vt %.6f %.6f%n", textureVertex.getX(), textureVertex.getY());
        }
        for (Polygon polygon : polygons) {
            System.out.print("f");
            for (int i = 0; i < polygon.getVertexIndices().size(); i++) {
                StringBuilder facePart = new StringBuilder();
                int vertexIndex = polygon.getVertexIndices().get(i) + 1;
                facePart.append(vertexIndex);
                if (!polygon.getTextureVertexIndices().isEmpty() && i < polygon.getTextureVertexIndices().size()) {
                    int textureIndex = polygon.getTextureVertexIndices().get(i) + 1;
                    facePart.append("/").append(textureIndex);
                } else {
                    facePart.append("/");
                }
                if (!polygon.getNormalIndices().isEmpty() && i < polygon.getNormalIndices().size()) {
                    int normalIndex = polygon.getNormalIndices().get(i) + 1;
                    facePart.append("/").append(normalIndex);
                }
                System.out.print(" " + facePart);
            }
            System.out.println();
        }
    }
}