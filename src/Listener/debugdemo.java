package Listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class debugdemo {
    public static void main(String[] args) {
        File file = new File("clickNum.txt");
        System.out.println(file.getAbsolutePath());
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("/src/iListener/clickNum.txt")));
            System.out.println(br.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
