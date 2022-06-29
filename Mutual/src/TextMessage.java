import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TextMessage extends Message  implements Serializable {
    private String text;
    private static final long serialVersionUID = 738549465835985L;
    public TextMessage(String sender, String text) {
        super(sender);
        this.text = text;
    }

    //from database to message
    public TextMessage(String sender, String text, LocalDateTime date) throws IOException {
        super(sender,date);
        this.text = text;
    }

    public TextMessage(String sender, String server, String channel, String text, LocalDateTime date) throws IOException {
        super(sender,channel,server,date);
        this.text = text;
    }

    public TextMessage(ArrayList<String> sourceInfo, String text){
        super(sourceInfo);
        this.text = text;
    }


    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        if (super.getSourceInfo().size() > 1)
            return super.getSourceInfoAsString() + text + "\n" + super.getReactions();
        else
            return super.getSourceInfoAsString() + text;
    }

    public String shortFormToString(){
        if (super.getSourceInfo().size() > 1)
            return super.getWriterOnly() + text + "\n" + super.getReactions();
        else
            return super.getWriterOnly() + text;
    }
}
