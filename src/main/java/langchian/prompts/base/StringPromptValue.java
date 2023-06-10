package langchian.prompts.base;

import com.google.common.collect.Lists;
import langchian.schema.BaseMessage;
import langchian.schema.HumanMessage;
import langchian.schema.PromptValue;
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
