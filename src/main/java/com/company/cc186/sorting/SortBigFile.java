import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class SortBigFile {
    static long memorySize = 5 * 1024 * 1024; //5MB memory
    public static void main(String[] args) {
        File file = new File("bigFile.txt");
        long fileSize = file.length();
        System.out.println(fileSize);
        long singleSize = 0;
        int tempFileSuffix = 0;
        List<String> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("bigFile.txt"));
            String tempLine = br.readLine();
            String nextLine = br.readLine();
            int index = 1;
            while (tempLine != null && nextLine != null) {
                if (tempLine.compareToIgnoreCase(nextLine) > 0) {
                    System.out.println("error index = " + index);
                    break;
                }
                tempLine = nextLine;
                nextLine = br.readLine();
                index++;
            }
            br.close();
            // tempFileSuffix = 24;
            // BufferedReader br1 = new BufferedReader(new FileReader("temp_" + tempFileSuffix--));
            // BufferedReader br2 = new BufferedReader(new FileReader("temp_" + tempFileSuffix--));
            // String line1 = br1.readLine();
            // String line2 = br2.readLine();
            // while (line1 != null && line2 != null) {
            //     if (line1.compareToIgnoreCase(line2) <= 0) {
            //         list.add(line1 + "\n");
            //         singleSize += line1.length() + 1;
            //         line1 = "";
            //     } else {
            //         list.add(line2 + "\n");
            //         singleSize += line2.length() + 1;
            //         line2 = "";
            //     }
            //     if (singleSize > memorySize) {
            //         System.out.println("singleSize = " + singleSize);
            //         outputListToTempFile(list, "bigFileOut.txt", true);
            //         list.clear();
            //         singleSize = 0;
            //     }
            //     if (line1.isEmpty()) {
            //         line1 = br1.readLine();
            //     }
            //     if (line2.isEmpty()) {
            //         line2 = br2.readLine();
            //     }
            // }
            // System.out.println("list size = " + list.size());
            // if (line1 != null) {
            //     System.out.println("file24 not empty");
            //     do {
            //         list.add(line1 + "\n");
            //         singleSize += line1.length() + 1;
            //         if (singleSize > memorySize) {
            //             System.out.println("singleSize1 = " + singleSize);
            //             outputListToTempFile(list, "bigFileOut.txt", true);
            //             list.clear();
            //             singleSize = 0;
            //         }
            //         line1 = br1.readLine();
            //     } while (line1 != null);
            // } else {
            //     System.out.println("file23 out");
            //     do {
            //         list.add(line2 + "\n");
            //         singleSize += line2.length() + 1;
            //         if (singleSize > memorySize) {
            //             System.out.println("singleSize2 = " + singleSize);
            //             outputListToTempFile(list, "bigFileOut.txt", true);
            //             list.clear();
            //             singleSize = 0;
            //         }
            //         line2 = br2.readLine();
            //     } while (line2 != null);
            // }
            // System.out.println("list size = " + list.size());
            // System.out.println("singleSize2 = " + singleSize);
            // outputListToTempFile(list, "bigFileOut.txt", true);
            // list.clear();
            // singleSize = 0;
            // br1.close();
            // br2.close();

            // int temp = 0;
            // while (tempFileSuffix >= 0) {
            //     br1 = new BufferedReader(new FileReader("bigFileOut.txt"));
            //     String fileName = "temp_" + tempFileSuffix--;
            //     System.out.println("processing " + fileName);
            //     br2 = new BufferedReader(new FileReader(fileName));
            //     line1 = br1.readLine();
            //     line2 = br2.readLine();
            //     String tempFileName = "bigFileOut_temp_" + temp++;
            //     while (line1 != null && line2 != null) {
            //         if (line1.compareToIgnoreCase(line2) <= 0) {
            //             list.add(line1 + "\n");
            //             singleSize += line1.length() + 1;
            //             line1 = "";
            //         } else {
            //             list.add(line2 + "\n");
            //             singleSize += line2.length() + 1;
            //             line2 = "";
            //         }
            //         if (singleSize > memorySize) {
            //             System.out.println("singleSize = " + singleSize);
            //             outputListToTempFile(list, tempFileName, true);
            //             list.clear();
            //             singleSize = 0;
            //         }
            //         if (line1.isEmpty()) {
            //             line1 = br1.readLine();
            //         }
            //         if (line2.isEmpty()) {
            //             line2 = br2.readLine();
            //         }
            //     }
            //     if (line1 != null) {
            //         System.out.println("bigFileOutput not empty");
            //         do {
            //             list.add(line1 + "\n");
            //             singleSize += line1.length() + 1;
            //             if (singleSize > memorySize) {
            //                 System.out.println("singleSize1 = " + singleSize);
            //                 outputListToTempFile(list, tempFileName, true);
            //                 list.clear();
            //                 singleSize = 0;
            //             }
            //             line1 = br1.readLine();
            //         } while (line1 != null);
            //     } else {
            //         System.out.println("temp not empty");
            //         do {
            //             list.add(line2 + "\n");
            //             singleSize += line2.length() + 1;
            //             if (singleSize > memorySize) {
            //                 System.out.println("singleSize2 = " + singleSize);
            //                 outputListToTempFile(list, tempFileName, true);
            //                 list.clear();
            //                 singleSize = 0;
            //             }
            //             line2 = br2.readLine();
            //         } while (line2 != null);
            //     }
            //     System.out.println("list size = " + list.size());
            //     System.out.println("singleSize2 = " + singleSize);
            //     outputListToTempFile(list, tempFileName, true);
            //     list.clear();
            //     singleSize = 0;
            //     br1.close();
            //     br2.close();
            //     new File("bigFileOut.txt").delete();
            //     new File(tempFileName).renameTo(new File("bigFileOut.txt"));  
            // }
        } catch (Exception e) {
            System.out.println(singleSize < memorySize);
            e.printStackTrace();
        }
    }

    public static void outputListToTempFile(List<String> list, String fileName, boolean append) throws Exception{
        StringBuilder sb = new StringBuilder();
        String str1 = "";
        for (String str : list) {
            sb.append(str);
            str1 = str;
        }
        FileWriter writer = new FileWriter(fileName, append);
        writer.write(sb.toString());
        writer.flush();
    }
    public static String generateString() {
        String uuid = (UUID.randomUUID().toString() + UUID.randomUUID()).toString().replace("-", "");
        return uuid;
    }
    public static void generateBigFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        int lineNumber = 999999;
        while (lineNumber-- > 0) {
            sb.append(generateString());
            sb.append("\n");
        }
        File file = new File(fileName);
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.append(sb);
            writer.close();
        } catch(IOException e) {
            System.out.println(e);
        }
    }
    public static class SortIgnoreCase implements Comparator<String> {
        public int compare(String s1, String s2) {
            return s1.toLowerCase().compareTo(s2.toLowerCase());
        }
    }
}