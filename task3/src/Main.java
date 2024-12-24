import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название модели (без расширения .obj): ");
        String modelName = scanner.nextLine();

        try {
            Path fileName = Path.of("D:\\ВГУ\\KG\\" + modelName + ".obj");
            String fileContent = Files.readString(fileName);

            System.out.println("\nЗагрузка модели ...");
            Model model = ObjReader.read(fileContent);

            System.out.println("Количество полигонов в модели: " + model.polygons.size());

            System.out.print("\nВведите количество полигонов для удаления: ");
            int numberOfPolygons = scanner.nextInt();
            List<Integer> polygonIndices = new ArrayList<>();
            System.out.println("Введите индексы полигонов для удаления:");
            for (int i = 0; i < numberOfPolygons; i++) {
                System.out.print("Полигон " + (i + 1) + " индекс: ");
                int index = scanner.nextInt();
                polygonIndices.add(index);
            }

            Model modelWithDeletedPolygons = Deleter.polygonDelete(model, polygonIndices);
            System.out.println("\nВершины: " + modelWithDeletedPolygons.vertices.size());
            System.out.println("Текстурные вершины: " + modelWithDeletedPolygons.textureVertices.size());
            System.out.println("Полигоны: " + modelWithDeletedPolygons.polygons.size());

            ObjWriter objWriter = new ObjWriter();
            objWriter.write(modelWithDeletedPolygons, "D:\\ВГУ\\KG\\" + modelName + "_modified.obj");
            System.out.println("Модель сохранена как " + modelName + "_modified.obj");

        } catch (IOException e) {
            System.err.println("Произошла ошибка при обработке модели: " + e.getMessage());
            e.printStackTrace();
        }
        scanner.close();
    }
}