package langchian.schema;

public class ChatGeneration extends Generation {

    private BaseMessage message;

    public BaseMessage getMessage() {
        return message;
    }

    public void setMessage(BaseMessage message) {
        this.message = message;
    }
}
