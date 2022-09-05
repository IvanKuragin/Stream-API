import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]));
        }

        long countUnder18 = persons.stream()
                .filter(a -> a.getAge() < 18)
                .count();
        System.out.println(countUnder18);

        List<String> militaryAvailable = persons.stream()
                .filter(age -> age.getAge() > 18 && age.getAge() < 27)
                .filter(sex -> sex.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        long countMilitary = militaryAvailable.size();
        System.out.println(militaryAvailable);
        System.out.println(countMilitary);

        List<Person> jobAvailable = persons.stream()
                .filter(education -> education.getEducation() == Education.HIGHER)
                .filter(under18 -> under18.getAge() > 18)
                .filter(jobAv -> {
                    if (jobAv.getSex() == Sex.WOMAN && jobAv.getAge() < 60) {
                        return true;
                    } else if (jobAv.getSex() == Sex.MAN && jobAv.getAge() < 65) {
                        return true;
                    }
                    return false;
                })
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        long countJobAvailable = jobAvailable.size();

        System.out.println(jobAvailable);
        System.out.println(countJobAvailable);
    }
}
