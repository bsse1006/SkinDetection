package package123;

import java.io.File;

public class FolderToFiles
{
    public static File[] getListOfFiles(String folderPath)
    {
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        return listOfFiles;
    }
}
