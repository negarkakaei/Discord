import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Message implements Serializable {

    private LocalDateTime dateTime;
    // holds sender username, channel and server respectively
    private ArrayList<String> sourceInfo;
    private HashMap<String, Integer> reactions = new HashMap<>();


    public Message(String sender) {
        sourceInfo = new ArrayList<>(List.of(sender));
        dateTime = LocalDateTime.now();
        reactions.put("like", 0);
        reactions.put("dislike", 0);
        reactions.put("laugh", 0);

    }

    public Message(String sender, String channel, String server) {
        sourceInfo = new ArrayList<>(Arrays.asList(sender, channel, server));
        dateTime = LocalDateTime.now();
        reactions.put("like", 0);
        reactions.put("dislike", 0);
        reactions.put("laugh", 0);
    }

    //for database to message
    public Message(String sender,LocalDateTime date) {
        sourceInfo = new ArrayList<>(List.of(sender));
        dateTime = date;
        reactions.put("like", 0);
        reactions.put("dislike", 0);
        reactions.put("laugh", 0);

    }

    public Message(String sender, String channel, String server,LocalDateTime date) {
        sourceInfo = new ArrayList<>(Arrays.asList(sender, channel, server));
        dateTime = date;
        reactions.put("like", 0);
        reactions.put("dislike", 0);
        reactions.put("laugh",0);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }


    public String getSourceInfoAsString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = sourceInfo.size() - 1; i >= 0; i--){
            stringBuilder.append(sourceInfo.get(i)).append("-> ");
        }
        stringBuilder.append(" : ");
        return stringBuilder.toString();
    }

    public ArrayList<String> getSourceInfo() {
        return sourceInfo;
    }
}