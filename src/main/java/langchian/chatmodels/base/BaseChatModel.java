package langchian.chatmodels.base;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import langchian.baselanguage.BaseLanguageModel;
import langchian.callbacks.base.BaseCallbackManager;
import langchian.schema.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.IOException;
import java.util.List;

@Data
@SuperBuilder
public abstract class BaseChatModel extends BaseLanguageModel<ChatGeneration> {

    protected abstract ChatResult _generate(List<BaseMessage> messages, List<String> stops,
                                   BaseCallbackManager manager, boolean verbose) throws IOException;

    private BaseMessage __call__(List<BaseMessage> messages, List<String> stops,
                                 BaseCallbackManager manager, boolean verbose) throws IOException {
        List<List<BaseMessage>> msgs = Lists.newLinkedList();
        msgs.add(messages);
        LLMResult<ChatGeneration> result = this.generate(msgs, stops, manager, verbose);
        return result.getGenerations().get(0).get(0).getMessage();
    }

    private LLMResult<ChatGeneration> generate(
            List<List<BaseMessage>> messages, List<String> stops,
            BaseCallbackManager manager, boolean verbose) throws IOException {

        LLMResult<ChatGeneration> ret = LLMResult.<ChatGeneration>builder()
                .llmOutput(Maps.newHashMap()).generations(Lists.newLinkedList()).build();
        manager.onLlmStart();
        try {
            for (List<BaseMessage> msgs : messages) {
                ChatResult result = this._generate(msgs, stops, manager, verbose);
                ret.getGenerations().add(result.getGenerations());
                ret.getLlmOutput().putAll(result.getLlmOutput());
            }
        } catch (Exception e) {
            manager.onLlmError();
            throw new IOException(e);
        }


        manager.onLlmEnd();
        return ret;
    }

    @Override
    public LLMResult<ChatGeneration> generatePrompt
            (List<PromptValue> prompts, List<String> stops,
             BaseCallbackManager manager, boolean verbose) throws IOException {

        List<List<BaseMessage>> messages = Lists.newLinkedList();
        prompts.stream().forEach(
                x -> {
                 messages.add(x.toMessages());
                }
        );

        return generate(messages, stops, manager, verbose);
    }

    @Override
    public String predict(String text, List<String> stops, BaseCallbackManager manager, boolean verbose) throws IOException {

        HumanMessage msg = HumanMessage.builder().content(text).build();
        BaseMessage result = __call__(Lists.newArrayList(msg), stops, manager, verbose);
        return result.getContent();
    }

    @Override
    public BaseMessage predictMessages(List<BaseMessage> messages, List<String> stops, BaseCallbackManager manager, boolean verbose) throws IOException {

        BaseMessage result = __call__(messages, stops, manager, verbose);
        return result;
    }
}
