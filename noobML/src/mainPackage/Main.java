package mainPackage;

import package123.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] foo) {

        double [] [] [] skin = new double [256] [256] [256];
        double [] [] [] nonSkin = new double [256] [256] [256];

        Scanner cin = new Scanner(System.in);

        System.out.println("1. Train\n2. Test");

        int choice;

        choice = cin.nextInt();

        if(choice==1)
        {
            System.out.println("Enter training folder path:");
            String folderPath = cin.nextLine();
            folderPath = cin.nextLine();

            File[] listOfFiles = FolderToFiles.getListOfFiles(folderPath);

            List<File> doneFiles = new ArrayList<>();

            for (File file : listOfFiles)
            {
                if(doneFiles.contains(file))
                    continue;

                File real = null;
                File mask = null;
                if (file.isFile()&&file.getName().contains("bmp"))
                {
                    mask = file;

                    for (File otherFile : listOfFiles)
                    {
                        if(otherFile.isFile()&&otherFile.getName().contains("jpg")&&otherFile.getName().contains(
                                file.getName().substring(0,4)))
                        {
                            real = otherFile;
                        }
                    }
                }
                else if (file.isFile()&&file.getName().contains("jpg"))
                {
                    real = file;

                    for (File otherFile : listOfFiles)
                    {
                        if(otherFile.isFile()&&otherFile.getName().contains("bmp")&&otherFile.getName().contains(
                                file.getName().substring(0,4)))
                        {
                            mask = otherFile;
                        }
                    }
                }

                RGB rgb = new RGB(folderPath+'/'+real.getName(),
                        folderPath+'/'+mask.getName(),skin,nonSkin);

                doneFiles.add(real);
                doneFiles.add(mask);
            }

            ImageProcessor imageProcessor = new ImageProcessor(skin,nonSkin);

            Database database = new Database(imageProcessor.getTrainedData());

            try
            {
                //Saving of object in a file
                FileOutputStream file = new FileOutputStream("Database/database.ser");
                ObjectOutputStream out = new ObjectOutputStream(file);

                // Method for serialization of object
                out.writeObject(database);

                out.close();
                file.close();

                System.out.println("Object has been serialized");

            }
            catch(IOException ex)
            {
                System.out.println("IOException is caught");
            }
        }
        else if(choice==2)
        {
            Database database = null;
            try
            {
                // Reading the object from a file
                FileInputStream file = new FileInputStream("Database/database.ser");
                ObjectInputStream in = new ObjectInputStream(file);

                // Method for deserialization of object
                database = (Database) in.readObject();

                in.close();
                file.close();

                System.out.println("Object has been deserialized ");
            }

            catch(IOException ex)
            {
                System.out.println("IOException is caught");
            }

            catch(ClassNotFoundException ex)
            {
                System.out.println("ClassNotFoundException is caught");
            }

            System.out.println("Enter test image path:");

            String testImagePath = cin.nextLine();

            Result result = new Result(testImagePath, database.getTrainedData());
        }

        cin.close();
    }
}
