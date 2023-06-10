package langchian.chains.base;

import com.google.common.collect.Sets;
import com.google.common.collect.Maps;
import langchian.callbacks.base.BaseCallbackManager;
import langchian.schema.BaseMemory;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuperBuilder
@Data
public abstract class Chain {

    private static final Logger LOG = LoggerFactory.getLogger(Chain.class);

    protected BaseMemory memory;
    protected BaseCallbackManager manager;
    protected boolean verbose;
    protected List<String> inputKeys;
    protected List<String> outputKeys;

    public static final void validate(List<String> keys, Map<String, Object> values) throws IOException {

        Set<String> diff = Sets.difference(Sets.newHashSet(keys), values.keySet());
        if (diff.size() != 0) {
            LOG.warn("required keys {}, provided keys {}", keys, values.keySet());
            throw new IOException();
        }

    }

    private Map<String, Object> prepInputs(Map<String, Object> inputs) throws IOException {

        Map<String, Object> ret = Maps.newHashMap();
        ret.putAll(inputs);
        if (memory != null) {
            ret.putAll(memory.loadMemoryVariables(inputs));
        }
        validate(inputKeys, ret);
        return ret;
    }

    private Map<String, Object> prepOutputs(Map<String, Object> inputs, Map<String, Object> result,
                                            boolean returnOnlyOutputs) throws IOException {
        validate(outputKeys, result);
        if (memory != null) {
            memory.saveContext(inputs, result);
        }
        if (returnOnlyOutputs) {
            return result;
        } else {
            Map<String, Object> ret = Maps.newHashMap();
            ret.putAll(inputs);
            ret.putAll(result);
            return ret;
        }
    }

    protected abstract Map<String, Object> _call(Map<String, Object> inputs) throws IOException;

    public Map<String, Object> call(Map<String, Object> inputs, boolean returnOnlyOutputs) throws IOException {

        Map<String, Object> _inputs = prepInputs(inputs);
        if (manager != null) {
            manager.onChainStart();
        }

        Map<String, Object> result = null;

        try {
            result = this._call(_inputs);
        } catch (Exception e) {
            manager.onChainError();
        }
        manager.onChainEnd();

        return prepOutputs(_inputs, result, returnOnlyOutputs);

    }

}
