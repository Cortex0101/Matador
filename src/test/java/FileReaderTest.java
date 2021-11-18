import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {
    FileReader fileReader;
    String[] testString1;
    String[] testString2;

    @BeforeEach
    void setUp() {
        this.fileReader = new FileReader();

    }

    @Test
    void TextIsHandledProperly() {
        testString1 = new String[]{"GO","","Get 2$ when you pass or land on GO","2","WHITE"};
        testString2 = new String[]{"GO","","Get 2$ when you pass or land on GO","2","WHITE"};
        assertArrayEquals(testString1, fileReader.ReadFileText());
        assertArrayEquals(testString2, fileReader.ReadFileText());

    }

}