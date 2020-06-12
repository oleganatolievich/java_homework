package Lesson_7;

import java.io.*;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class FileMerger {
    private static Scanner cin = new Scanner(System.in);
    public static void main(String[] args) {

        //Нет в ДЗ
        //Получаем количество файлов
        Integer filesAmount = getFilesAmount();
        if (filesAmount == null || filesAmount < 2 || filesAmount > 999) {
            System.out.println("Has introducido una cantidad invalida! Largate de mi programa!");
            System.exit(1);
        }

        //1. Создать 2 текстовых файла, примерно по 50-100 символов в каждом (особого значения не имеет)
        //Создаем текстовые файлы с разнообразным содержимым
        ArrayList<String> fileNames = createRandFiles(filesAmount, true);
        if (fileNames.isEmpty()) {
            System.out.println("No files left to copy! No hay archivos para copiar!");
            System.exit(1);
        }

        //2. Написать программу, «склеивающую» эти файлы, то есть вначале идет текст из первого файла, потом текст из второго в новый файл.
        String destFileName = "destfile.txt";
        mergeFilesIntoOne(destFileName, fileNames);

        //3. Написать программу, которая проверяет присутствует ли указанное пользователем слово в файле (работаем только с латиницей).
        String searchWord = getSearchWord();
        if (searchWord == null) {
            System.out.println("Porque introduces las cosas que no tienen sentido? Не надо так");
            System.exit(1);
        }
        int matchesCount = countMatchesInFile(destFileName, searchWord);
        System.out.printf("Количество попаданий слова \"%s\" в файле \"%s\": %d\n", searchWord, destFileName, matchesCount);

        //4. Написать метод, проверяющий, есть ли указанное слово в файлах в папке
        String searchFolder = "C:\\Work\\IdeaProjects\\";
        //regex101.com - наше все....
        String javaFilesPattern = ".*\\.java\\b";
        boolean wordFound = wordFoundInFilesContent(searchFolder, javaFilesPattern, searchWord);
        if (wordFound) System.out.println("GotCHA!");

        //5. Написать метод, добавляющий, указанное слово в файлы в папке
        //не стал реализовывать, ибо от последнего он отличается только направлением потока - на вывод в файл
        //алгоритм такой - получить дерево файлов методом getFileHierarchy(), открыть каждый файл потоком на чтение,
        //созданным с параметром append = true и дописать содержимое файла нужным словом, обработать исключения IOException
        //при выборе типа потока руководствоваться следующими правилами:
        //если в файлах двоичные данные, то используем потомков абстрактного класса OutputStream, например FileOutputStream
        //если в файлах у нас UTF-8 текст, то используем потомков абстрактного класса Writer, например FileWriter
        //при желании оборачиваем эти классы в BufferedOutputStream или BufferedWriter,
        //и используем буффер, ибо самые медленные операции - это дисковые и сетевые операции I/O
        //работа с потоками в Java похожа на таковую в 1С, разве что имена классов в 1С на русском :) что добавляет "ламповости"
        //осталось закодить этот замечательный алгоритм :)
    }
    private static Integer getFilesAmount() {
        Integer result = null;
        System.out.println("Введите число файлов: ");
        if (cin.hasNextInt()) result = new Integer(cin.nextInt());
        return result;
    }
    private static ArrayList<String> createRandFiles(int filesAmount, boolean generateFiles) {
        ArrayList<String> fileNames = new ArrayList<>();
        DecimalFormat intFormatter = new DecimalFormat("000");
        for (int i = 1; i <= filesAmount; i++)
            fileNames.add("file" + intFormatter.format(i) + ".txt");
        if (!generateFiles) return fileNames;
        for (String fileName : fileNames) {
            String phrase = RandPhraseGenerator.getPhrase();
            boolean errorOccured = false;
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
                bw.write(phrase);
                bw.flush();
            }
            catch (IOException e) {
                errorOccured = true;
                System.out.println("Por Dios y porque de nuevo? Something is definitely wrong");
                e.printStackTrace();
            }
            if (!errorOccured) {
                String fullPath = Paths.get(fileName).toAbsolutePath().toString();
                System.out.printf("%s was created! Amen!\n", fullPath);
            }
        }
        return fileNames;
    }
    private static void mergeFilesIntoOne(String destFile, ArrayList<String> fileNames) {
        try (BufferedOutputStream destStream = new BufferedOutputStream(new FileOutputStream(destFile)))
        {
            for (String fileName: fileNames) {
                System.out.printf("Копирую файл %s\n", fileName);
                boolean errorOccured = false;
                try (BufferedInputStream sourceStream = new BufferedInputStream(new FileInputStream(fileName))) {
                    int curByte;
                    do {
                        curByte = sourceStream.read();
                        if (curByte != -1) destStream.write(curByte);
                    } while (curByte != -1);
                } catch (IOException e) {
                    System.out.printf("Не удалось скопировать файл по причине ошибки: %s", e.getMessage());
                }
                if (!errorOccured) System.out.println("Файл успешно скопирован, el archivo se ha copiado con exito");
            }
            destStream.flush();
        } catch (IOException e) {
            System.out.printf("Oops! You did it again, you played with the HDD. %s\n", e.getMessage());
        }
        System.out.printf("If your eyes are red due to constant manual reading, open tty and type \"cat %s\" to see what was copied\n", destFile);
    }
    private static String getSearchWord() {
        System.out.println("Введите слово для поиска: ");
        String anyLatinWord = "\\b[a-zA-Z]+\\b";
        String searchWord = null;
        boolean searchIsPossible = cin.hasNext(anyLatinWord);
        if (searchIsPossible) {
            searchWord = cin.next(anyLatinWord);
            searchIsPossible = !(searchWord.isEmpty());
        }
        if (!searchIsPossible) searchWord = null;
        return searchWord;
    }
    private static int countMatchesInFile(String destFileName, String searchWord) {
        int bufferSize = 1024;
        byte[] byteBuffer = new byte[bufferSize];
        int matchesCount = 0;

        try (BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(destFileName))) {
            boolean eofReached = false;
            do {
                int bytesRead = inStream.read(byteBuffer);
                eofReached = (bytesRead == -1);
                if (bytesRead > 0) {
                    String tempString = new String(byteBuffer);
                    int margin = Math.min(tempString.length(), bytesRead - 1);
                    String trimString = tempString.substring(0, margin);
                    if (trimString.indexOf(searchWord) >= 0) matchesCount ++;
                }
            } while (!eofReached);
        } catch (IOException | StringIndexOutOfBoundsException e) {
            System.out.printf("Oops! You did it again, you played with the HDD. %s\n", e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        return matchesCount;
    }
    public static ArrayList<String> getFileHierarchy(String folder, String pattern) {
        File folderFile = new File(folder);
        ArrayList<String> fileTree = new ArrayList<>();
        fillFileHierarchy(folderFile, pattern, fileTree);
        return fileTree;
    }
    public static void fillFileHierarchy(File folderFile, String pattern, ArrayList<String> fileTree) {
        File[] folderFiles = folderFile.listFiles();
        for (File curFile : folderFiles) {
            if (curFile.isDirectory()) fillFileHierarchy(curFile, pattern, fileTree);
            if (curFile.isFile()) {
                if (curFile.getName().matches(pattern)) {
                    fileTree.add(curFile.getAbsolutePath());
                }
            }
        }
    }
    public static boolean wordFoundInFilesContent(String folder, String pattern, String searchWord) {
        ArrayList<String> FileHierarchy = getFileHierarchy(folder, pattern);
        for (String absPath : FileHierarchy) {
            int matchesCount = countMatchesInFile(absPath, searchWord);
            if (matchesCount > 0) return true;
        }
        return false;
    }
}