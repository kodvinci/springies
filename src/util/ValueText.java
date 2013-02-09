package util;

/**
 * This class represents text that is a labeled numeric value.
 * 
 * @author Robert C. Duvall
 */
public class ValueText extends Text {
    private String myLabel;
    private int myValue;
    private int myInitialValue;

    /**
     * Create with its label and an initial value.
     * 
     * @param label label of value text
     * @param initialValue initial value
     */
    public ValueText(String label, int initialValue) {
        super(label + " " + initialValue);
        myValue = initialValue;
        myInitialValue = initialValue;
        myLabel = label;
    }

    /**
     * Returns displayed value.
     */
    public int getValue() {
        return myValue;
    }

    /**
     * Update displayed value.
     * 
     * @param value value to add to current value
     */
    public void updateValue(int value) {
        myValue += value;
        setText(myLabel + " " + myValue);
    }

    /**
     * Reset displayed value to its initial value
     */
    public void resetValue() {
        myValue = myInitialValue;
        setText(myLabel + " " + myValue);
    }
}
