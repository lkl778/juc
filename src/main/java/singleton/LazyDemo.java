package singleton;

/**
 * @author likelong
 * @date 2022/10/8
 */
public class LazyDemo {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                //System.out.println(Person.getPerson());
                System.out.println(Dog.getInstance());
            }).start();
        }
    }
}

class Dog {
    private static Dog DOG = null;

    private Dog() {
    }

    public synchronized static Dog getInstance() {
        if (null == DOG) {
            DOG = new Dog();
        }
        return DOG;
    }
}
