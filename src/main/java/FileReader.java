import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class FileReader {
    private Scanner scan;
    private final String[] textTypeChecker;

    public FileReader() {
        this.textTypeChecker = new String[]{"Title: ","Subtext: ","Description: ","Rent: ","Color: "};
        try{
            File file = new File(new File("./").getCanonicalPath() + "\\src\\main\\resources\\field_info.txt");
            this.scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String[] ReadFileText(){
        String[] string = new String[5];
        for (int i = 0; i < string.length; i++) {
                scan.skip(textTypeChecker[i]);
                string[i] = scan.nextLine();
        }
        scan.nextLine();
        return string;
    }
}
