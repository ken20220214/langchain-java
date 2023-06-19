package langchain.llms.base;

import com.google.common.collect.Lists;
import langchain.baselanguage.BaseLanguageModel;
import langchain.callbacks.base.BaseCallbackManager;
import langchain.schema.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LLM wrapper should take in a prompt and return a string.
 */
@Data
@SuperBuilder
public abstract class BaseLLM<T extends Generation>  extends BaseLanguageModel<T>  {


    protected abstract <T extends Generation> LLMResult<T> _generate(
            List<String> prompts,
            List<String> stops,
            BaseCallbackManager manager,
            boolean verbose
    );

    private <T extends Generation> LLMResult<T> generate(
            List<String> prompts,
            List<String> stops,
            BaseCallbackManager manager,
            boolean verbose
    ) throws IOException {

        manager.onLlmStart();
        LLMResult<T> ret = null;
        try {
            ret = this._generate(prompts, stops, manager, verbose);
        } catch (Exception e) {
            manager.onLlmError();
            throw new IOException(e);
        }
        manager.onLlmEnd();
        return ret;
    }

    @Override
    public LLMResult<T> generatePrompt(
            List<PromptValue> prompts, List<String> stops,
            BaseCallbackManager manager, boolean verbose) throws IOException{

        List<String> promptStrings = prompts.stream().map(
                x -> {return x.toPromptString();}
        ).collect(Collectors.toList());

        return this.generate(promptStrings, stops, manager, verbose);
    }

    private String __call__(String prompt, List<String> stops,
                            BaseCallbackManager manager, boolean verbose) throws IOException {
        return this.generate(Lists.newArrayList(prompt), stops, manager, verbose)
                .getGenerations().get(0).get(0).getText();
    }

    @Override
    public String predict(
            String text, List<String> stops,
            BaseCallbackManager manager, boolean verbose) throws IOException {
        return this.__call__(text, stops, manager, verbose);
    }

    @Override
    public BaseMessage predictMessages(
            List<BaseMessage> messages, List<String> stops,
            BaseCallbackManager manager, boolean verbose) throws IOException {

        StringBuilder sb = new StringBuilder();
        messages.stream().forEach( x -> {
            sb.append(x.getRole()).append(":").append(x.getContent()).append("\n");
        });
        String msg = this.__call__(sb.toString(), stops, manager, verbose);
        AIMessage message = AIMessage.builder().content(msg).build();
        return message;
    }
}
