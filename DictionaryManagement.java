import java.util.Scanner;
import java.io.*;

public class DictionaryManagement {

    Dictionary dictionaryList = new Dictionary();

    Dictionary dictionaryFile = new Dictionary();

    public int count;

    Scanner sc = new Scanner(System.in);
    // int n = sc.nextInt();
    int n = 0;
    // String s = sc.nextLine();
    String s = "";

    public void BubbleSort(Dictionary d, int n) {
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++) {
                if (d.dictionary[j].getWordtarget().compareTo(d.dictionary[j+1].getWordtarget()) == 1) {
                    String temp1 = d.dictionary[j].getWordtarget();
                    d.dictionary[j].setWordtarget(d.dictionary[j+1].getWordtarget());
                    d.dictionary[j+1].setWordtarget(temp1);

                    String temp2 = d.dictionary[j].getWordexplain();
                    d.dictionary[j].setWordexplain(d.dictionary[j+1].getWordexplain());
                    d.dictionary[j+1].setWordexplain(temp2);
                }
        }
    }
    
    public void insertFromCommandline() {
        dictionaryList.dictionary = new Word[n];
    
        for(int i=0; i<n; i++) {
            dictionaryList.dictionary[i] = new Word();
            String english = sc.nextLine();
            String vietnamese = sc.nextLine();
            dictionaryList.dictionary[i].setWordtarget(english);
            dictionaryList.dictionary[i].setWordexplain(vietnamese);
        }
        BubbleSort(dictionaryList, n);

        sc.close();
    }

    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);  
    }

    public static String padRightInt(int s, int n) {
        return String.format("%-" + n + "s", s);  
    }

    public void showWords() {
        System.out.println(padRight("No", 10) + padRight("|", 10) + padRight("English", 17) + padRight("|", 10) + "Vietnamese");
        for(int i = 0; i < n; i++){
            System.out.println(padRightInt(i+1, 10) + padRight("|", 10) + padRight(dictionaryList.dictionary[i].getWordtarget(), 10) + padRight("|", 10)  + dictionaryList.dictionary[i].getWordexplain());
        }
    }

    public void insertFromFile() {
        String file = "TuDien1.txt";
        dictionaryFile.dictionary = new Word[100000];
        FileInputStream fileinput = null;
        BufferedReader bufferedReader = null;
        try {
            fileinput = new FileInputStream(file);
            bufferedReader = new BufferedReader(new InputStreamReader(fileinput));
            String line1 = " ";
            String line2 = " ";
            count = 0;
            while((line1 = bufferedReader.readLine()) != null) {
                // String[] wordsArray;
                line2 = bufferedReader.readLine();
                // wordsArray = line.split("\t");

                dictionaryFile.dictionary[count] = new Word();
                dictionaryFile.dictionary[count].setWordtarget(line1);
                dictionaryFile.dictionary[count].setWordexplain(line2);
                // System.out.println(dictionaryFile.dictionary[count].getWordtarget() + " " + dictionaryFile.dictionary[count].getWordexplain());
                count++;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("khong tim thay file");
        } catch (IOException ex) {
            System.out.println("Loi nhap ten file");
        }

        finally {
            try {
                fileinput.close();
                bufferedReader.close();
            } catch (IOException ex) {
                System.out.println("Loi nhap ten file");
            }
        }
    }

    public void showWordsFile() {
        System.out.println(padRight("No", 10) + padRight("|", 10) + padRight("English", 17) + padRight("|", 10) + "Vietnamese");
        for(int i = 0; i < count; i++){
            System.out.println(padRightInt(i+1, 10) + padRight("|", 10) + padRight(dictionaryFile.dictionary[i].getWordtarget(), 60) + padRight("|", 10)  + dictionaryFile.dictionary[i].getWordexplain());
        }
    }

    public void dictionaryLookup() {
        System.out.println("Nhap tu can tim kiem:");
        String wordLookup = sc.nextLine();
        System.out.println("Tu can tim la:");
        for (int i = 0; i < count; i++) {
            if (wordLookup.equals(dictionaryFile.dictionary[i].getWordtarget())) {
                System.out.println(dictionaryFile.dictionary[i].getWordexplain());
            }
        }
    }

    public void dictionarySearcher() {
        System.out.println("Nhap tu can tim kiem:");
        String wordSearch = sc.nextLine();
        System.out.println("Cac tu tra ve la:");
         for (int i = 0; i < count; i++) {
            if(dictionaryFile.dictionary[i].getWordtarget().contains(wordSearch) == true) {
                System.out.println(dictionaryFile.dictionary[i].getWordtarget());
            }
        }
    }

    public void dictionaryAdd() {
        System.out.println("Nhap tu can them:");
        String wordAddtarget = sc.nextLine();
        System.out.println("Nhap nghia can them:");
        String wordAddexplain = sc.nextLine();
        dictionaryFile.dictionary[count] = new Word();
        dictionaryFile.dictionary[count].setWordtarget(wordAddtarget);
        dictionaryFile.dictionary[count].setWordexplain(wordAddexplain);
        count = count + 1;
        BubbleSort(dictionaryFile, count);
    }

    public void dictionaryRemove() {
        System.out.println("Nhap tu can xoa:");
        String wordRemovetarget = sc.nextLine();
        int pos = 0;
        for(int i=0; i<count; i++) {
            if(wordRemovetarget.equals(dictionaryFile.dictionary[i].getWordtarget())){
                pos = i;
            } else {
                System.out.println("Tu can xoa khong ton tai");
                return;
            }
        }
        
        for(int i=pos; i<count; i++){
            dictionaryFile.dictionary[i].setWordtarget(dictionaryFile.dictionary[i+1].getWordtarget());
            dictionaryFile.dictionary[i].setWordexplain(dictionaryFile.dictionary[i+1].getWordexplain());
        }

        dictionaryFile.dictionary[count-1].setWordtarget(null);
        dictionaryFile.dictionary[count-1].setWordexplain(null);
        count = count - 1;
    }

    public void dictionaryExportToFile() {
        try {
            String file = " ";
            OutputStream outputstream = new FileOutputStream(file);
            OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream);
            BufferedWriter bufferedwriter = new BufferedWriter(outputstreamwriter);
            for (int i=0; i < count; i++) {
                bufferedwriter.write(dictionaryFile.dictionary[i].getWordtarget() + "\t" + dictionaryFile.dictionary[i].getWordexplain());
            }
            bufferedwriter.flush();
            bufferedwriter.close();
        } catch (Exception e) {
            System.err.println(e.getStackTrace());
        }
    }
}
