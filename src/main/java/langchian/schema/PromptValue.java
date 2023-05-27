package langchian.schema;

import java.util.List;

public interface PromptValue extends StringConverter {

    public List<BaseMessage> convertToMessages();
}
