package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.model.Car;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.Student;
import by.clevertec.util.Util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.math.RoundingMode.HALF_UP;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingDouble;
import static java.util.Comparator.comparingInt;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class Main {

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
        task16();
        task17();
        task18();
        task19();
        task20();
        task21();
        task22();
    }

    public static List<Animal> task1() {
        List<Animal> animals = Util.getAnimals();

        AtomicInteger zooIndex = new AtomicInteger(0);
        Map<Integer, List<Animal>> zoo = animals.stream()
                .filter(animal -> animal.getAge() >= 10 && animal.getAge() <= 20)
                .sorted(comparingInt(Animal::getAge))
                .collect(groupingBy(a -> zooIndex.getAndIncrement() / 7));

        zoo.get(2).forEach(System.out::println);

        return zoo.get(2);
    }

    public static void task2() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> "Japanese".equals(animal.getOrigin()))
                .peek(animal -> animal.setBread(animal.getBread().toUpperCase()))
                .filter(animal -> "Female".equals(animal.getGender()))
                .map(Animal::getBread)
                .forEach(System.out::println);
    }

    public static void task3() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() > 30 && animal.getOrigin().startsWith("A"))
                .map(Animal::getOrigin)
                .distinct()
                .forEach(System.out::println);
    }

    public static long task4() {
        List<Animal> animals = Util.getAnimals();
        long countFemaleGender = animals.stream()
                .filter(animal -> "Female".equals(animal.getGender()))
                .count();
        System.out.println(countFemaleGender);
        return countFemaleGender;
    }

    public static void task5() {
        List<Animal> animals = Util.getAnimals();
        boolean isExistFromHungary = animals.stream()
                .filter(animal -> animal.getAge() >= 20 && animal.getAge() <= 30)
                .anyMatch(animal -> "Hungarian".equals(animal.getOrigin()));
        System.out.println(isExistFromHungary);
    }

    public static void task6() {
        List<Animal> animals = Util.getAnimals();
        boolean isAllMaleOrFemale = animals.stream()
                .allMatch(animal -> "Male".equals(animal.getGender()) || "Female".equals(animal.getGender()));
        System.out.println(isAllMaleOrFemale);
    }

    public static void task7() {
        List<Animal> animals = Util.getAnimals();
        boolean isNotExistFromOceania = animals.stream()
                .noneMatch(animal -> "Oceania".equals(animal.getOrigin()));
        System.out.println(isNotExistFromOceania);
    }

    public static void task8() {
        List<Animal> animals = Util.getAnimals();
        int maxAge = animals.stream()
                .sorted(comparing(Animal::getBread))
                .limit(100L)
                .mapToInt(Animal::getAge)
                .max()
                .orElse(0);
        System.out.println(maxAge);
    }

    public static void task9() {
        List<Animal> animals = Util.getAnimals();
        long minLength = animals.stream()
                .map(Animal::getBread)
                .mapToLong(bread -> bread.chars().count())
                .min()
                .orElse(0L);
        System.out.println(minLength);
    }

    public static void task10() {
        List<Animal> animals = Util.getAnimals();
        int sumAge = animals.stream()
                .mapToInt(Animal::getAge)
                .sum();
        System.out.println(sumAge);
    }

    public static void task11() {
        List<Animal> animals = Util.getAnimals();
        double averageAge = animals.stream()
                .filter(animal -> "Indonesian".equals(animal.getOrigin()))
                .mapToInt(Animal::getAge)
                .average()
                .orElse(0.0);
        System.out.println(averageAge);
    }

    public static void task12() {
        List<Person> persons = Util.getPersons();
        persons.stream()
                .filter(person -> "Male".equals(person.getGender()))
                .filter(person -> ChronoUnit.YEARS.between(person.getDateOfBirth(), LocalDate.now()) >= 18)
                .filter(person -> ChronoUnit.YEARS.between(person.getDateOfBirth(), LocalDate.now()) < 27)
                .sorted(comparingInt(Person::getRecruitmentGroup))
                .limit(200L)
                .forEach(System.out::println);
    }

    public static void task13() {
        List<House> houses = Util.getHouses();

        houses.stream()
                .flatMap(house -> house.getPersonList().stream()
                        .map(person -> Map.entry(
                                "Hospital".equals(house.getBuildingType()) ? 1 :
                                ChronoUnit.YEARS.between(person.getDateOfBirth(), LocalDate.now()) < 18 ||
                                ("Male".equals(person.getGender()) &&
                                ChronoUnit.YEARS.between(person.getDateOfBirth(), LocalDate.now()) >= 63) ||
                                ("Female".equals(person.getGender()) &&
                                ChronoUnit.YEARS.between(person.getDateOfBirth(), LocalDate.now()) >= 58) ? 2 :
                                3, person)))
                .collect(groupingBy(Map.Entry::getKey, mapping(Map.Entry::getValue, toList())))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .flatMap(evacuation -> evacuation.getValue().stream())
                .limit(500)
                .forEach(System.out::println);
    }

    public static BigDecimal task14() {
        List<Car> cars = Util.getCars();

        AtomicInteger countryIndex = new AtomicInteger(1);

        double totalRevenue = cars.stream()
                .map(car -> Map.entry(
                        "Jaguar".equals(car.getCarMake()) ||
                        "White".equals(car.getColor()) ? 1 :

                        car.getMass() <= 1500 ||
                        "BMW".equals(car.getCarMake()) ||
                        "Lexus".equals(car.getCarMake()) ||
                        "Chrysler".equals(car.getCarMake()) ||
                        "Toyota".equals(car.getCarMake()) ? 2 :

                        "Black".equals(car.getColor()) &&
                        car.getMass() >= 4000 ||
                        "GMC".equals(car.getCarMake()) ||
                        "Dodge".equals(car.getCarMake()) ? 3 :

                        car.getReleaseYear() < 1982 ||
                        "Civic".equals(car.getCarMake()) ||
                        "Cherokee".equals(car.getCarMake()) ? 4 :

                        !"Yellow".equals(car.getColor()) &&
                        !"Red".equals(car.getColor()) &&
                        !"Green".equals(car.getColor()) &&
                        !"Blue".equals(car.getColor()) ||
                        car.getPrice() > 40000 ? 5 :

                        car.getVin().contains("59") ? 6 :
                        7, car))
                .filter(entry -> entry.getKey() < 7)
                .collect(groupingBy(Map.Entry::getKey, mapping(Map.Entry::getValue, toList())))
                .values()
                .stream()
                .mapToDouble(car -> car.stream().mapToInt(Car::getPrice).sum() -
                                    car.stream().mapToInt(Car::getMass).sum() / 1000.0 * 7.14)
                .peek(fullPrice -> System.out.printf("%s) %.2f$%n", countryIndex.getAndIncrement(), fullPrice))
                .sum();

        System.out.printf("Total Revenue: %.2f$%n", totalRevenue);

        return new BigDecimal(totalRevenue).setScale(2, HALF_UP);
    }

    public static void task15() {
        List<Flower> flowers = Util.getFlowers();

        double sum = flowers.stream()
                .sorted(comparing(Flower::getOrigin).reversed()
                        .thenComparing(comparingInt(Flower::getPrice))
                        .thenComparing(comparingDouble(Flower::getWaterConsumptionPerDay).reversed()))
                .filter(flower -> flower.getCommonName().charAt(0) >= 'C' &&
                        flower.getCommonName().charAt(0) <= 'S')
                .filter(Flower::isShadePreferred)
                .filter(flower -> flower.getFlowerVaseMaterial().contains("Glass") ||
                        flower.getFlowerVaseMaterial().contains("Aluminum") ||
                        flower.getFlowerVaseMaterial().contains("Steel"))
                .mapToDouble(flower -> flower.getPrice() + flower.getWaterConsumptionPerDay() / 1000 * 365 * 5 * 1.39)
                .sum();

        System.out.println(sum);
    }

    public static void task16() {
        List<Student> students = Util.getStudents();
        students.stream()
                .filter(student -> student.getAge() <= 18)
                .sorted(comparing(Student::getSurname))
                .map(student -> student.getSurname() + " " + student.getAge())
                .forEach(System.out::println);
    }

    public static void task17() {
        List<Student> students = Util.getStudents();
        students.stream()
                .collect(groupingBy(Student::getGroup, toSet()))
                .entrySet()
                .forEach(System.out::println);
    }

    public static void task18() {
        List<Student> students = Util.getStudents();
        students.stream()
                .collect(groupingBy(Student::getFaculty, averagingInt(Student::getAge)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(reverseOrder()))
                .forEach(System.out::println);
    }

    public static void task19() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        String givenGroup = "M-1";

        examinations.stream()
                .filter(exam -> exam.getExam3() > 4)
                .flatMap(exam -> students.stream()
                .filter(student -> student.getId() == exam.getStudentId() &&
                                   givenGroup.equals(student.getGroup())))
                .forEach(System.out::println);
    }

    public static void task20() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();

        examinations.stream()
                .collect(groupingBy(
                        exam -> students.stream()
                                .filter(student -> student.getId() == exam.getStudentId())
                                .findFirst()
                                .map(Student::getFaculty)
                                .orElseThrow(RuntimeException::new), averagingInt(Examination::getExam1)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(System.out::println);
    }

    public static void task21() {
        List<Student> students = Util.getStudents();
        students.stream()
                .collect(groupingBy(Student::getGroup, counting()))
                .entrySet()
                .forEach(System.out::println);
    }

    public static void task22() {
        List<Student> students = Util.getStudents();
        students.stream()
                .collect(groupingBy(Student::getFaculty, mapping(Student::getAge, minBy(Integer::compare))))
                .entrySet()
                .forEach(System.out::println);
    }
}
