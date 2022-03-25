public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Boolean> a = new ArrayDeque<Boolean>();
//        a.addFirst(0);
//        a.addFirst(1);
//        a.addLast(2);
//        a.addLast(3);
//        a.addLast(4);
//        a.addLast(5);
//        a.addFirst(6);
//        a.addFirst(7);
//        a.addLast(8);
        a.addFirst(true);
        boolean b = a.removeLast();
        System.out.println(b);
    }
}