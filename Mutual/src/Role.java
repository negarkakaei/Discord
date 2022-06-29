
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Role implements Serializable {
    private static final long serialVersionUID =338245675849926L;
    private String roleName;
    private String values;
    public static final ArrayList<String> abilities = new ArrayList<>(Arrays.asList("create channel", "remove channel", "remove member",
            "restrict member", "ban member", "change server name", "see chat history", "pin message", "delete server"));

    public Role(String values, String name) {
        this.values = values;
        this.roleName = name;
    }


    public ArrayList<String> getAvailableAbilities() {
        ArrayList<String> availableAbilities = new ArrayList<>();
        for (int i = 0; i < 9; i++){
            if (values.charAt(i) == '1'){
                availableAbilities.add(abilities.get(i));
            }
        }

        return availableAbilities;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getValues() {
        return values;
    }
}
