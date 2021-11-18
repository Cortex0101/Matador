import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class FileReader {
    private Scanner scan;
    private File file;

    public FileReader() {
        try{
            this.file = new File("C:\\Users\\augus\\IdeaProjects\\03_part3\\src\\main\\resources\\field_info.txt");
            this.scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Am errpr occurred.");
            e.printStackTrace();
        }

    }
}
