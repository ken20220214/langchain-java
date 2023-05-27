package langchian.schema.prompts.chat;

import langchian.schema.BaseMessage;
import langchian.schema.PromptValue;

import java.util.List;

public class ChatPromptValue implements PromptValue {

    private List<BaseMessage> messages = null;

    public ChatPromptValue(List<BaseMessage> messages) {
        this.messages = messages;
    }

    @Override
    public String convertToString() {

        StringBuilder sb = new StringBuilder();
        for (BaseMessage msg : this.messages) {
            sb.append(msg.convertToString());
        }
        return sb.toString();
    }

    @Override
    public List<BaseMessage> convertToMessages() {
        return this.messages;
    }
}
