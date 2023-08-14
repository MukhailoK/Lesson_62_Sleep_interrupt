import model.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> people = List.of(new Person("Jack", 12)
                , new Person("Steave", 50)
                , new Person("Igor", 19)
                , new Person("person", 17));
        writeToFile("persons.txt", people);
        System.out.println("longest string = " + reader("persons.txt"));
        List<Person> personList = getOldest("persons.txt");
        personList.forEach(System.out::println);
    }

    //1
    public static void writeToFile(String fileName, List<Person> people) {
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(fileName));
            for (Person person : people) {
                bf.write(person.getName() + "," + person.getAge() + "\n");
            }
            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //2
    public static String reader(String fileName) {
        String res = "";
        try {
            BufferedReader bf = new BufferedReader(new FileReader(fileName));
            String str;
            while ((str = bf.readLine()) != null) {
                if (res.length() < str.length()) {
                    res = str;
                }
            }
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    //3
    public static List<Person> getOldest(String fileName) {
        List<Person> persons = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String str;

            while ((str = br.readLine()) != null) {
                String[] cells = str.split(",");
                if (Integer.parseInt(cells[1]) >= 18) {
                    persons.add(new Person(cells[0], Integer.parseInt(cells[1])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return persons.stream().sorted(Comparator.comparingInt(Person::getAge)).collect(Collectors.toList());
    }
}