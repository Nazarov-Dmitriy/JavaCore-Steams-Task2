import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        //1.Найти количество несовершеннолетних (т.е. людей младше 18 лет).
        long underage = persons.stream().
                filter(x -> x.getAge() < 18).count();
        System.out.println(underage);

        //2.Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
        List<String> reservist = persons.stream().
                filter(x -> x.getAge() >= 18 && x.getAge() < 27).
                map(x -> x.getFamily()).
                collect(Collectors.toList());
        System.out.println(reservist);

        //3.Получить отсортированный по фамилии список потенциально работоспособных людей с высшим образованием в выборке (т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин).
        List<String> ableBodiedWithHigherEducation = persons.stream().
                filter(x -> (x.getSex() == Sex.WOMAN && x.getAge() >= 18 && x.getAge() < 60)
                        || (x.getSex() == Sex.MAN && x.getAge() >= 18 && x.getAge() < 65)).
                filter(x -> x.getEducation() == Education.HIGHER).
                map(x -> x.getFamily()).
                sorted(Comparator.naturalOrder()).
                collect(Collectors.toList());
        System.out.println(ableBodiedWithHigherEducation);
    }
}

