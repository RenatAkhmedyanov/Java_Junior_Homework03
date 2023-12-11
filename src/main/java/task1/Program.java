package task1;

import java.io.*;

public class Program {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student ivan = new Student("Ivan", 19, 4.8);

        try (FileOutputStream fileOutputStream = new FileOutputStream("students.bin");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(ivan);
            System.out.println("Объект task1.Student сериализован.");
        }

        try (FileInputStream fileInputStream = new FileInputStream("students.bin");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            ivan = (Student) objectInputStream.readObject();
            System.out.println("Объект task1.Student десериализован.");
        }

        System.out.println("Имя: " + ivan.getName());
        System.out.println("Возраст: " + ivan.getAge());
        System.out.println("GPA: " + ivan.getGPA());
        System.out.println("Значение GPA не было сохранено/восстановлено, т.к. поле помечено ключевым словом transient");
    }
}
