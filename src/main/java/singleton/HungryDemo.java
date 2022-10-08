package singleton;

/**
 * 饿汉式
 *
 * @author likelong
 * @date 2022/10/8
 */
public class HungryDemo {
    public static void main(String[] args) {
        Person person1 = Person.getPerson();
        Person person2 = Person.getPerson();

        System.out.println(person1);
        System.out.println(person2);
    }
}

class Person {
    private static final Person PERSON = new Person();

    private Person() {
    }

    public static Person getPerson() {
        return PERSON;
    }
}
