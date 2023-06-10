package langchian.schema;

import java.util.List;

public interface PromptValue {

    public String toPromptString();

    public List<BaseMessage> toMessages();

}
