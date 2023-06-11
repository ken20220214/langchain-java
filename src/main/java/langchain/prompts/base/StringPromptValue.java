package langchain.prompts.base;

import com.google.common.collect.Lists;
import langchain.schema.BaseMessage;
import langchain.schema.HumanMessage;
import langchain.schema.PromptValue;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class StringPromptValue implements PromptValue {

    private String text;

    @Override
    public String toPromptString() {
        return text;
    }

    @Override
    public List<BaseMessage> toMessages() {
        return Lists.newArrayList(HumanMessage.builder().content(text).build());
    }
}
