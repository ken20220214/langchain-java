package langchian.schema;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Chain {

    private static final Logger LOG = LoggerFactory.getLogger(Chain.class);

    private BaseMemory memory;

    protected boolean verbose = false;

    private String chainType = null;

    private Set<String> inputKeys = null;

    private Set<String> outputKeys = null;

    private boolean validateInputs(Map<String, Object> inputs) {
        Sets.SetView<String> diff = Sets.difference(inputKeys, inputs.keySet());
        return diff.size() == 0;
    }

    private boolean validateOutputs(Map<String, Object> outputs) {
        Sets.SetView<String> diff = Sets.difference(outputKeys, outputs.keySet());
        return diff.size() == 0;
    }

    /**
     * Run the logic of this chain and return the output.
     *
     * @param inputs
     * @return
     */
    protected abstract Map<String, Object> _call(Map<String, Object> inputs);

    public Map<String, Object> call(Map<String, Object> inputs, boolean returnOnlyOutputs) throws IOException {

        Map<String, Object> tmp = this.prepInputs(inputs);
        try {
            Map<String, Object> outputs = this._call(tmp);
            return this.prepOutputs(inputs, outputs, returnOnlyOutputs);
        } catch (Exception e) {
            LOG.warn("error execute chain {}", chainType, e);
            throw new IOException(e);
        }

    }

    private Map<String, Object> prepInputs(Map<String, Object> inputs) throws IOException {

        Map<String, Object> ret = new HashMap<>(inputs);
        if (this.memory != null) {
            Map<String, Object> externalContext = this.memory.loadMemoryVariables(inputs);
            ret.putAll(externalContext);
        }
        if (!this.validateInputs(ret)) {
            StringBuilder sb = new StringBuilder("required input keys ");
            sb.append(JSON.toJSONString(inputKeys)).append(", provided input keys ")
                    .append(JSON.toJSONString(ret.keySet()));
            throw new IOException(sb.toString());
        }
        return ret;
    }

    private Map<String, Object> prepOutputs(Map<String, Object> inputs, Map<String, Object> outputs,
                                            boolean returnOnlyOutputs) throws IOException {

        if (this.validateOutputs(outputs)) {
            StringBuilder sb = new StringBuilder("required output keys ");
            sb.append(JSON.toJSONString(outputKeys)).append(", provided output keys ")
                    .append(JSON.toJSONString(outputs.keySet()));
            throw new IOException();
        }

        if (this.memory != null) {
            this.memory.saveContext(inputs, outputs);
        }

        if (returnOnlyOutputs) {
            return outputs;
        } else {
            Map<String, Object> ret = new HashMap<>(outputs);
            ret.putAll(inputs);
            return ret;
        }
    }

}
