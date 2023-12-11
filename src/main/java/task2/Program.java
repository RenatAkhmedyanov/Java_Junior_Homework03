package task2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;



public class Program {
    public static final String FILE_JSON = "students.json";
    public static final String FILE_XML = "students.xml";

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();

    public static void main(String[] args) {
        Student ivan = new Student("Ivan", 19, 4.8);
        System.out.println("До сериализации: " + ivan);
        saveToFile(FILE_JSON, ivan);
        saveToFile(FILE_XML, ivan);

        ivan = loadFromFile(FILE_JSON);
        System.out.println("После сериализации: " + ivan);

    }

    public static void saveToFile(String fileName, Student s) {
        try {
            if (fileName.endsWith(".json")) {
                objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                objectMapper.writeValue(new File(fileName), s);
            } else if (fileName.endsWith(".xml")) {
                xmlMapper.writeValue(new File(fileName), s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Student loadFromFile(String fileName) {
        Student s = new Student();
        File file = new File(fileName);
        if (file.exists()) {
            try {
                if (fileName.endsWith(".json")) {
                    s = objectMapper.readValue(file, Student.class);
                } else if (fileName.endsWith(".xml")) {
                    s = xmlMapper.readValue(file, Student.class);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return s;
    }
}



