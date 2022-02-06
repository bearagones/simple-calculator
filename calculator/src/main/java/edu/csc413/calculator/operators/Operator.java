package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;
import java.util.Map;
import java.util.HashMap;

public abstract class  Operator {
    // The Operator class should contain an instance of a HashMap
    // This map will use keys as the tokens we're interested in,
    // and values will be instances of the Operators.
    // ALL subclasses of operator MUST be in their own file.
    // Example:
    // Where does this declaration go? What should its access level be?
    // Class or instance variable? Is this the right declaration?
    // HashMap operators = new HashMap();
    // operators.put( "+", new AdditionOperator() );
    // operators.put( "-", new SubtractionOperator() );

    private static Map<String, Operator> operatorMap;
    static {
        operatorMap = new HashMap<>();
        operatorMap.put("+", new AddOperator());
        operatorMap.put("-", new SubtractOperator());
        operatorMap.put("*", new MultiplyOperator());
        operatorMap.put("/", new DivideOperator());
        operatorMap.put("^", new PowerOperator());
    }

    /**
     * retrieve the priority of an Operator
     * @return priority of an Operator as an int
     */
    public abstract int priority();

    /**
     * Abstract method to execute an operator given two operands.
     * @param operandOne first operand of operator
     * @param operandTwo second operand of operator
     * @return an operand of the result of the operation.
     */
    public abstract Operand execute(Operand operandOne, Operand operandTwo);

    /**
     * used to retrieve an operator from our HashMap.
     * This will act as out publicly facing function,
     * granting access to the Operator HashMap.
     *
     * @param token key of the operator we want to retrieve
     * @return reference to a Operator instance.
     */
    public static Operator getOperator(String token) {
        return operatorMap.get(token);
    }

    
     /**
     * determines if a given token is a valid operator.
     * please do your best to avoid static checks
     * for example token.equals("+") and so on.
     * Think about what happens if we add more operators.
     */
    public static boolean check(String token) {
        return operatorMap.containsKey(token);
    }
}

class AddOperator extends Operator{
    public int priority() {
        return 1;
    }

    public Operand execute(Operand operandOne, Operand operandTwo) {
        return new Operand(operandOne.getValue() + operandTwo.getValue());
    }
}

class SubtractOperator extends Operator{

    public int priority() {
        return 1;
    }

    public Operand execute(Operand operandOne, Operand operandTwo) {
        return new Operand(operandOne.getValue() - operandTwo.getValue());
    }
}

class MultiplyOperator extends Operator{

    public int priority() {
        return 2;
    }

    public Operand execute(Operand operandOne, Operand operandTwo) {
        return new Operand(operandOne.getValue() * operandTwo.getValue());
    }
}

class DivideOperator extends Operator{

    public int priority() {
        return 2;
    }

    public Operand execute(Operand operandOne, Operand operandTwo) {
        return new Operand(operandOne.getValue() / operandTwo.getValue());
    }
}

class PowerOperator extends Operator{

    public int priority() {
        return 3;
    }

    public Operand execute(Operand operandOne, Operand operandTwo) {
        return new Operand((int) Math.pow(operandOne.getValue(), operandTwo.getValue()));
    }
}
