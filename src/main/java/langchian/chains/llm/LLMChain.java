package langchian.chains.llm;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import langchian.baselanguage.BaseLanguageModel;
import langchian.callbacks.base.BaseCallbackManager;
import langchian.chains.base.Chain;
import langchian.prompts.prompt.PromptTemplate;
import langchian.schema.Generation;
import langchian.schema.LLMResult;
import langchian.schema.PromptValue;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Data
@SuperBuilder
public class LLMChain extends Chain {

    private static final Logger LOG = LoggerFactory.getLogger(LLMChain.class);
    private static final String STOP_KEY = "stop";

    private PromptTemplate template;
    private BaseLanguageModel llm;

    @Override
    public List<String> getOutputKeys() {
        return Lists.newArrayList("text");
    }

    @Builder
    public static class PromptInfo {

        private List<PromptValue> prompts;
        private List<String> stops;
    }

    public static <T extends Comparable<T>> boolean compareCollections(
            Collection<T> first, Collection<T> second) {

        assert first != null && second != null;

        if (first.size() != second.size()) {
            return false;
        }
        Iterator<T> fi = first.iterator();
        Iterator<T> si = second.iterator();
        for (int i = 0; i < first.size(); i++) {
            T f = fi.next();
            T s = si.next();
            if (f.compareTo(s) != 0) {
                return false;
            }
        }
        return true;
    }

    private PromptInfo prepPrompts(List<Map<String, Object>> inputLists) throws IOException {

        List<PromptValue> prompts = Lists.newLinkedList();
        List<String> stops = null;

        Map<String, Object> first = inputLists.get(0);
        if (first.containsKey(STOP_KEY)) {
            stops = (List<String>) first.get(STOP_KEY);
        }

        for (Map<String, Object> input : inputLists) {

            PromptValue prompt = template.formatPrompt(input);
            LOG.info("prompt value {}", prompt.toPromptString());
            prompts.add(prompt);

            if (input.containsKey(STOP_KEY)) {

                List<String> tmp = (List<String>) input.get(STOP_KEY);
                if (!compareCollections(tmp, stops)) {
                    LOG.warn("first stop words {}, current stop words {}", stops, tmp);
                    throw new IOException("inconsistent stop words");
                }
            }
        }

        PromptInfo pi = PromptInfo.builder().prompts(prompts).stops(stops).build();
        return pi;
    }


    @Override
    protected Map<String, Object> _call(Map<String, Object> inputs) throws IOException {

        LLMResult<? extends Generation> result = this.generate(Lists.newArrayList(inputs));
        return createOutputs(result).get(0);
    }

    private <T extends Generation> LLMResult<T> generate(List<Map<String, Object>> inputList) throws IOException {

        PromptInfo pi = this.prepPrompts(inputList);
        return llm.generatePrompt(pi.prompts, pi.stops, this.manager, this.verbose);
    }

    private List<Map<String, Object>> createOutputs(LLMResult<? extends Generation> result) {
        List<Map<String, Object>> ret = Lists.newLinkedList();
        for (List<? extends Generation> g : result.getGenerations()) {
            Map<String, Object> map = Maps.newHashMap();
            map.put(getOutputKeys().get(0), g.get(0).getText());
            ret.add(map);
        }

        return ret;
    }

    public String predict(Map<String, Object> inputs) throws IOException {
        return this.__call__(inputs, false).get(this.getOutputKeys().get(0)).toString();
    }

}
