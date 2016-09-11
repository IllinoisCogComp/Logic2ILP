package edu.illinois.cs.cogcomp.ir.fol.quantifier;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import edu.illinois.cs.cogcomp.ir.IndicatorVariable;
import edu.illinois.cs.cogcomp.ir.fol.FolFormula;

/**
 * Created by haowu on 5/19/16.
 */
public class Forall implements FolFormula {

    private List<FolFormula> formulas;

    public Forall(List<FolFormula> formulas) {
        this.formulas = formulas;
    }

    public Forall(FolFormula... formulas) {
        this.formulas = new ArrayList<>();
        for (FolFormula f : formulas){
            this.formulas.add(f);
        }
    }
    public List<FolFormula> getFormulas() {
        return this.formulas;
    }

    @Override
    public boolean eval(Map<IndicatorVariable, Boolean> assignment) {
        for (FolFormula f : this.formulas){
            if (!f.eval(assignment)){
                return false;
            }
        }
        return true;
    }

    @Override
    public FolFormula toNnf() {
        List<FolFormula> formulas = new ArrayList<>(this.formulas.size());
        this.formulas.forEach(folFormula -> {
            formulas.add(folFormula.toNnf());
        });

        return new Forall(formulas);
    }

    @Override
    public FolFormula negate() {
        throw new RuntimeException("not implemented.");
    }

    @Override
    public String toString() {
        return String.format("(Forall_{ %s )", "(" + StringUtils
            .join(this.formulas.stream().map(Object::toString).collect(Collectors.toList()), " & ")
                                              + ")");    }
}