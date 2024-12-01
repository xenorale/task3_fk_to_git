import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Path fileName = Path.of("../../3DModels/Faceform/WrapHead.obj");
        String fileContent = Files.readString(fileName);
        System.out.println("Loading model ...");
        Model model = ObjReader.read(fileContent);

        Model model1 = Deleter.polygonDelete(model, List.of(1, 2));

        System.out.println("Vertices: " + model1.vertices.size());
        System.out.println("Texture vertices: " + model1.textureVertices.size());
        System.out.println("Normals: " + model1.normals.size());
        System.out.println("Polygons: " + model1.polygons.size());
    }
}
