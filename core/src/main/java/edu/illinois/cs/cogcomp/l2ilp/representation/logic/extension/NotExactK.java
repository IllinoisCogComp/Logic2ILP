package edu.illinois.cs.cogcomp.l2ilp.representation.logic.extension;

import edu.illinois.cs.cogcomp.l2ilp.representation.logic.LogicFormula;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class NotExactK implements LogicFormula {

    private int k;
    private List<LogicFormula> formulas;

    public NotExactK(int k, List<LogicFormula> formulas) {
        if (k < 0) {
            throw new RuntimeException("K has to be non-negative.");
        }

        if (formulas == null) {
            throw new RuntimeException("List of formulas has to be non-null.");
        }

        this.k = k;
        this.formulas = formulas;
    }

    public NotExactK(int k, LogicFormula... formulas) {
        if (k < 0) {
            throw new RuntimeException("K has to be non-negative.");
        }

        this.k = k;
        this.formulas = new ArrayList<>(formulas.length);

        for (LogicFormula logicFormula : formulas) {
            this.formulas.add(logicFormula);
        }
    }

    public int getK() {
        return k;
    }

    public List<LogicFormula> getFormulas() {
        return this.formulas;
    }

    @Override
    public LogicFormula negate() {
        return new ExactK(this.k, this.formulas);
    }

    @Override
    public LogicFormula toNnf() {
        return new NotExactK(this.k, this.formulas.stream().map(LogicFormula::toNnf).collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        return ("NotExact_" + this.k + "_of_" + this.formulas.size() + "("
                + StringUtils.join(this.formulas.stream().map(Object::toString).collect(Collectors.toList()), ", ")
                + ")");
    }
}