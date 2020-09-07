package lesson3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws Exception {
//        File file = new File("1/a.txt");
//        System.out.println(file.length());

//        file.createNewFile();

//        file.mkdirs();
//        System.out.println(file.exists());
//        System.out.println(file.getCanonicalPath());

//        System.out.println(file.isFile());
//        System.out.println(file.isDirectory());

//        File file = new File("src/lesson1");

//        System.out.println(Arrays.toString(file.list()));

//        System.out.println(Arrays.toString(
//                file.listFiles(f -> f.getName().startsWith("B"))
//        ));
//        FileFilter filter = new FileFilter() {
//            @Override
//            public boolean accept(File pathname) {
//                return pathname.length() < 600;
//            }
//        };
//
//        System.out.println(Arrays.toString(file.listFiles(filter)));


//        FileInputStream fin = new FileInputStream("1/a.txt");
//        fin.close();

//        try (FileInputStream fin = new FileInputStream("1/a.txt");) {
//            int x;
//            while ((x = fin.read()) != -1) {
//                System.out.print((char) x);
//            }
//        }
//
//        byte[] arr = new byte[8];
//
//        try (FileInputStream fin = new FileInputStream("1/a.txt");) {
//
//            while (fin.read(arr) > 0) {
//                System.out.print(new String(arr));
//            }
//        }


//        try (FileOutputStream fos = new FileOutputStream("1/b.txt",true);) {
////            fos.write(65);
////            fos.write(new byte[]{65,66,67,68});
//            fos.write("Hello".getBytes());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("1/a.txt"))) {
//            int x;
//            while ((x = bis.read()) != -1) {
//                System.out.print((char) x);
//            }
//        }

//        try (InputStreamReader isr = new InputStreamReader(new FileInputStream("1/a.txt"),
//                StandardCharsets.UTF_8)) {
//            int x;
//            while ((x = isr.read()) != -1) {
//                System.out.print((char) x);
//            }
//        }


//        Cat cat = new Cat("Barsik",5);
//        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("cats.txt"));
//        out.writeObject(cat);
//        out.close();


//        ObjectInputStream in = new ObjectInputStream(new FileInputStream("cats.txt"));
//        Cat catFromFile = (Cat) in.readObject();
//
//
//        System.out.println(catFromFile);

        testBufferedReader();
    }

    private static void testBufferedReader()
    {
        BufferedReader bufferedReader = null;
        //Der Pfad zur Textdatei
        String filePath = "1/a.txt";
        File file = new File(filePath);

        try
        {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;

            Object[] lines = bufferedReader.lines().toArray();
            int startIdx = lines.length - 5;

            StringBuilder linesToLoad = new StringBuilder();
            for (int i = startIdx; i < lines.length; i++)
            {
                linesToLoad.append(lines[i].toString()).append("\n");
            }

            System.out.println(linesToLoad.toString());


            //Stream<String> lines = bufferedReader.lines();
            //System.out.println("lines: " + lines.count());
//            Iterator<String> iterator = lines.iterator();
//            iterator.
//            while (iterator.hasNext())
//            {
//                System.out.println(iterator.next());
//            }

//            while ((line = bufferedReader.readLine()) != null)
//            {
//                System.out.println(line);
//                //Hier kann Ihr Code stehen ...
//            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (bufferedReader != null)
            {
                try
                {
                    bufferedReader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
