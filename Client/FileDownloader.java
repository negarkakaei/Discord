
import java.io.*;

/**
 * a seperated thread to fetch file messages from server and saves it to local memory
 */
public class FileDownloader extends Thread {

    private ObjectInputStream fin;

    public FileDownloader(ObjectInputStream fin) {
        this.fin = fin;
    }

    @Override
    public void run() {

        // getting fileBytes object from server
        FileBytes fileBytes = null;
        try {
            fileBytes = (FileBytes) fin.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // choosing a path and saving the file in it
        String filePath = "C:\\discord";
        filePath = filePath + "\\" + fileBytes.getFileName();
        File file = new File(filePath);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(fileBytes.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
