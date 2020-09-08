package lesson4;

/*
    1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС).
        Используйте wait/notify/notifyAll.
    2. На серверной стороне сетевого чата реализовать управление потоками через ExecutorService.
 */
public class Lesson4
{
    private static final int PRINT_TIMES = 5;
    public static final String LETTER_A = "A";
    public static final String LETTER_B = "B";
    public static final String LETTER_C = "C";

    private Object monitor = new Object();

    private volatile String nextLetter = LETTER_A;


    public static void main(String[] args)
    {
        Lesson4 lesson = new Lesson4();

        lesson.task1PrintABC();
    }

    private void task1PrintABC()
    {
        this.createThreadForPrint(LETTER_A, LETTER_B).start();
        this.createThreadForPrint(LETTER_B, LETTER_C).start();
        this.createThreadForPrint(LETTER_C, LETTER_A).start();
    }

    private Thread createThreadForPrint(String printLetter, String nextLetter)
    {
        return new Thread(() -> {
            try
            {
                for (int i = 0; i < PRINT_TIMES; i++)
                {
                    synchronized (this.monitor)
                    {
                        while (!this.nextLetter.equals(printLetter))
                        {
                            this.monitor.wait();
                        }

                        System.out.print(printLetter);

                        this.nextLetter = nextLetter;
                        this.monitor.notifyAll();
                    }
                }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        });
    }
}
