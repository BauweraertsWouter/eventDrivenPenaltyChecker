package be.kdg.se.wbw.examenproject.penaltyChecker.shared.api;

/**
 * Generic mapper interface. Implement this interface when you have to map Type I to Type O
 * @param <IN> Incoming type you want to map into another Type O
 * @param <OUT> Resulting Type of the mapping
 */
public interface TypeMapper<IN, OUT> {
    /**
     * Implement this method to map the incoming object into the required format
     * @param in incoming object of type I
     * @return instance of type O, result of the mapping
     */
    OUT map(IN in);
}
