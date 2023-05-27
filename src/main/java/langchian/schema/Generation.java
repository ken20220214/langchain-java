package langchian.schema;

import java.util.Map;

public class Generation {

    private String txt;
    private Map<String, Object> generationInfo;

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Map<String, Object> getGenerationInfo() {
        return generationInfo;
    }

    public void setGenerationInfo(Map<String, Object> generationInfo) {
        this.generationInfo = generationInfo;
    }
}
