package ma.zs.generator.engine.bean;

import java.util.List;

public class InitialisationBlock {
    private List<InitialisationElement> initialisationElements;


    public List<InitialisationElement> getInitialisationElements() {
        return initialisationElements;
    }

    public void setInitialisationElements(List<InitialisationElement> initialisationElements) {
        this.initialisationElements = initialisationElements;
    }

    @Override
    public String toString() {
        return "[" +
                initialisationElements +
                ']';
    }
}
