package com.evi0s.cse6341.p5.utils;

import com.evi0s.cse6341.p5.datastructures.AbstractState;
import com.evi0s.cse6341.p5.datastructures.IdentMap;
import com.evi0s.cse6341.p5.datastructures.ScopeBlock;
import com.evi0s.cse6341.p5.datastructures.ScopeStack;
import com.evi0s.cse6341.p5.errors.AbstractEvaluationError;
import com.evi0s.cse6341.p5.types.AbstractIdentType;
import com.evi0s.cse6341.p5.types.IdentType;
import com.google.common.collect.HashBasedTable;

public class AbstractEvaluator {
    private static final HashBasedTable<AbstractIdentType, AbstractIdentType, AbstractIdentType> addOpTable = HashBasedTable.create();
    private static final HashBasedTable<AbstractIdentType, AbstractIdentType, AbstractIdentType> subOpTable = HashBasedTable.create();
    private static final HashBasedTable<AbstractIdentType, AbstractIdentType, AbstractIdentType> mulOpTable = HashBasedTable.create();
    private static final HashBasedTable<AbstractIdentType, AbstractIdentType, AbstractIdentType> divIntOpTable = HashBasedTable.create();
    private static final HashBasedTable<AbstractIdentType, AbstractIdentType, AbstractIdentType> divFloatOpTable = HashBasedTable.create();

    private static final HashBasedTable<AbstractIdentType, AbstractIdentType, AbstractIdentType> ltOpTable = HashBasedTable.create();
    private static final HashBasedTable<AbstractIdentType, AbstractIdentType, AbstractIdentType> leOpTable = HashBasedTable.create();
    private static final HashBasedTable<AbstractIdentType, AbstractIdentType, AbstractIdentType> gtOpTable = HashBasedTable.create();
    private static final HashBasedTable<AbstractIdentType, AbstractIdentType, AbstractIdentType> geOpTable = HashBasedTable.create();
    private static final HashBasedTable<AbstractIdentType, AbstractIdentType, AbstractIdentType> eqOpTable = HashBasedTable.create();
    private static final HashBasedTable<AbstractIdentType, AbstractIdentType, AbstractIdentType> neOpTable = HashBasedTable.create();

    private static final HashBasedTable<AbstractIdentType, AbstractIdentType, AbstractIdentType> logicAndOpTable = HashBasedTable.create();
    private static final HashBasedTable<AbstractIdentType, AbstractIdentType, AbstractIdentType> logicOrOpTable = HashBasedTable.create();
    private static final HashBasedTable<AbstractIdentType, AbstractIdentType, AbstractIdentType> logicNotOpTable = HashBasedTable.create();

    private static final HashBasedTable<AbstractIdentType, AbstractIdentType, AbstractIdentType> mergeOpTable = HashBasedTable.create();

    private static void initializeAddOpTable() {
        // first op: neg
        addOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_NEG);
        addOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_NEG);
        // addOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_INT); // need fix
        addOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT);
        addOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT);
        // first op: zero
        addOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_NEG);
        addOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ZERO);
        addOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_POS);
        addOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT);
        addOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT);
        // first op: pos
        // addOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_INT); // need fix
        addOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_POS);
        addOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_POS);
        addOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT);
        addOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT);
        // first op: any int
        addOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_INT);
        addOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_INT);
        addOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_INT);
        addOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT);
        // first op: any float
        addOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_FLOAT);
        addOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_FLOAT);
        addOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_FLOAT);
        addOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT);
    }

    private static void initializeMinOpTable() {
        // first op: neg
        // subOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_INT); // need fix
        subOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_NEG);
        subOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_NEG);
        subOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT);
        subOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT);
        // first op: zero
        subOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_POS);
        subOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ZERO);
        subOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_NEG);
        subOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT);
        subOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT);
        // first op: pos
        subOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_POS);
        subOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_POS);
        // subOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_INT); // need fix
        subOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT);
        subOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT);
        // first op: any int
        subOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_INT);
        subOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_INT);
        subOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_INT);
        subOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT);
        // first op: any float
        subOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_FLOAT);
        subOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_FLOAT);
        subOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_FLOAT);
        subOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT);
    }

    private static void initializeMulOpTable() {
        // first op: neg
        mulOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_POS);
        mulOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ZERO);
        mulOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_NEG);
        mulOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT);
        mulOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT);
        // first op: zero
        mulOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ZERO);
        mulOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ZERO);
        mulOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ZERO);
        mulOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ZERO);
        mulOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ZERO);
        // first op: pos
        mulOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_NEG);
        mulOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ZERO);
        mulOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_POS);
        mulOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT);
        mulOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT);
        // first op: any int
        mulOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_INT);
        mulOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ZERO);
        mulOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_INT);
        mulOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT);
        // first op: any float
        mulOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_FLOAT);
        mulOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ZERO);
        mulOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_FLOAT);
        mulOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT);
    }

    private static void initializeDivOpTable() {
        // type: int
        // first op: neg
        divIntOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_INT);
        // divOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ZERO, EE); <-- not possible
        divIntOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_INT);
        // divOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_INT, EE); <-- not possible
        // first op: zero
        divIntOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ZERO);
        // divOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ZERO, EE); <-- not possible
        divIntOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ZERO);
        // divOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_INT, EE); <-- not possible
        // first op: pos
        divIntOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_INT);
        // divOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ZERO, EE); <-- not possible
        divIntOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_INT);
        // divOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_INT, EE); <-- not possible
        // first op: any int
        divIntOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_INT);
        // divOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ZERO, EE); <-- not possible
        divIntOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_INT);
        // divIntOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT, EE); <-- not possible

        // type: float
        // first op: neg
        divFloatOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_POS);
        // divOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ZERO, EE); <-- not possible
        divFloatOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_NEG);
        // divOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_INT, EE); <-- not possible
        // first op: zero
        divFloatOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ZERO);
        // divOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ZERO, EE); <-- not possible
        divFloatOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ZERO);
        // divOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_INT, EE); <-- not possible
        // first op: pos
        divFloatOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_NEG);
        // divOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ZERO, EE); <-- not possible
        divFloatOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_POS);
        // divOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_INT, EE); <-- not possible
        // first op: any float
        divFloatOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_FLOAT);
        // divOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ZERO, EE); <-- not possible
        divFloatOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_FLOAT);
        // divFloatOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT, EE); <-- not possible
    }

    private static void initializeLtOpTable() {
        // first op: neg
        ltOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_BOOL);
        ltOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_TRUE);
        ltOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_TRUE);
        ltOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_BOOL);
        ltOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_BOOL);
        // first op: zero
        ltOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_FALSE);
        ltOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_FALSE);
        ltOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_TRUE);
        ltOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_BOOL);
        ltOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_BOOL);
        // first op: pos
        ltOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_FALSE);
        ltOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_FALSE);
        ltOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_BOOL);
        ltOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_BOOL);
        ltOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_BOOL);
        // first op: any int
        ltOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_BOOL);
        ltOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_BOOL);
        ltOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_BOOL);
        ltOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_BOOL);
        // first op: any float
        ltOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_BOOL);
        ltOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_BOOL);
        ltOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_BOOL);
        ltOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_BOOL);
    }

    private static void initializeLeOpTable() {
        // first op: neg
        leOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_BOOL);
        leOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_TRUE);
        leOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_TRUE);
        leOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_BOOL);
        leOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_BOOL);
        // first op: zero
        leOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_FALSE);
        leOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_TRUE);
        leOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_TRUE);
        leOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_BOOL);
        leOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_BOOL);
        // first op: pos
        leOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_FALSE);
        leOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_FALSE);
        leOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_BOOL);
        leOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_BOOL);
        leOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_BOOL);
        // first op: any int
        leOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_BOOL);
        leOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_BOOL);
        leOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_BOOL);
        leOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_BOOL);
        // first op: any float
        leOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_BOOL);
        leOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_BOOL);
        leOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_BOOL);
        leOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_BOOL);
    }

    private static void initializeGtOpTable() {
        // first op: neg
        gtOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_BOOL);
        gtOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_FALSE);
        gtOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_FALSE);
        gtOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_BOOL);
        gtOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_BOOL);
        // first op: zero
        gtOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_TRUE);
        gtOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_FALSE);
        gtOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_FALSE);
        gtOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_BOOL);
        gtOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_BOOL);
        // first op: pos
        gtOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_TRUE);
        gtOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_TRUE);
        gtOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_BOOL);
        gtOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_BOOL);
        gtOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_BOOL);
        // first op: any int
        gtOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_BOOL);
        gtOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_BOOL);
        gtOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_BOOL);
        gtOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_BOOL);
        // first op: any float
        gtOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_BOOL);
        gtOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_BOOL);
        gtOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_BOOL);
        gtOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_BOOL);
    }

    private static void initializeGeOpTable() {
        // first op: neg
        geOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_BOOL);
        geOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_FALSE);
        geOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_FALSE);
        geOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_BOOL);
        geOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_BOOL);
        // first op: zero
        geOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_TRUE);
        geOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_TRUE);
        geOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_FALSE);
        geOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_BOOL);
        geOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_BOOL);
        // first op: pos
        geOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_TRUE);
        geOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_TRUE);
        geOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_BOOL);
        geOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_BOOL);
        geOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_BOOL);
        // first op: any int
        geOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_BOOL);
        geOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_BOOL);
        geOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_BOOL);
        geOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_BOOL);
        // first op: any float
        geOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_BOOL);
        geOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_BOOL);
        geOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_BOOL);
        geOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_BOOL);
    }

    private static void initializeEqOpTable() {
        // first op: neg
        eqOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_BOOL);
        eqOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_FALSE);
        eqOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_FALSE);
        eqOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_BOOL);
        eqOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_BOOL);
        // first op: zero
        eqOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_FALSE);
        eqOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_TRUE);
        eqOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_FALSE);
        eqOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_BOOL);
        eqOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_BOOL);
        // first op: pos
        eqOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_FALSE);
        eqOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_FALSE);
        eqOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_BOOL);
        eqOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_BOOL);
        eqOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_BOOL);
        // first op: any int
        eqOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_BOOL);
        eqOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_BOOL);
        eqOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_BOOL);
        eqOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_BOOL);
        // first op: any float
        eqOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_BOOL);
        eqOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_BOOL);
        eqOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_BOOL);
        eqOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_BOOL);
    }

    private static void initializeNeOpTable() {
        // first op: neg
        neOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_BOOL);
        neOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_TRUE);
        neOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_TRUE);
        neOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_BOOL);
        neOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_BOOL);
        // first op: zero
        neOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_TRUE);
        neOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_FALSE);
        neOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_TRUE);
        neOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_BOOL);
        neOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_BOOL);
        // first op: pos
        neOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_TRUE);
        neOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_TRUE);
        neOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_BOOL);
        neOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_BOOL);
        neOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_BOOL);
        // first op: any int
        neOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_BOOL);
        neOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_BOOL);
        neOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_BOOL);
        neOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_BOOL);
        // first op: any float
        neOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_BOOL);
        neOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_BOOL);
        neOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_BOOL);
        neOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_BOOL);
    }

    private static void initializeLogicAndOpTable() {
        logicAndOpTable.put(AbstractIdentType.TYPE_TRUE, AbstractIdentType.TYPE_TRUE, AbstractIdentType.TYPE_TRUE);
        logicAndOpTable.put(AbstractIdentType.TYPE_TRUE, AbstractIdentType.TYPE_FALSE, AbstractIdentType.TYPE_FALSE);
        logicAndOpTable.put(AbstractIdentType.TYPE_TRUE, AbstractIdentType.TYPE_ANY_BOOL, AbstractIdentType.TYPE_ANY_BOOL);
        logicAndOpTable.put(AbstractIdentType.TYPE_FALSE, AbstractIdentType.TYPE_TRUE, AbstractIdentType.TYPE_FALSE);
        logicAndOpTable.put(AbstractIdentType.TYPE_FALSE, AbstractIdentType.TYPE_FALSE, AbstractIdentType.TYPE_FALSE);
        logicAndOpTable.put(AbstractIdentType.TYPE_FALSE, AbstractIdentType.TYPE_ANY_BOOL, AbstractIdentType.TYPE_FALSE);
        logicAndOpTable.put(AbstractIdentType.TYPE_ANY_BOOL, AbstractIdentType.TYPE_TRUE, AbstractIdentType.TYPE_ANY_BOOL);
        logicAndOpTable.put(AbstractIdentType.TYPE_ANY_BOOL, AbstractIdentType.TYPE_FALSE, AbstractIdentType.TYPE_FALSE);
        logicAndOpTable.put(AbstractIdentType.TYPE_ANY_BOOL, AbstractIdentType.TYPE_ANY_BOOL, AbstractIdentType.TYPE_ANY_BOOL);
    }

    private static void initializeLogicOrOpTable() {
        logicOrOpTable.put(AbstractIdentType.TYPE_TRUE, AbstractIdentType.TYPE_TRUE, AbstractIdentType.TYPE_TRUE);
        logicOrOpTable.put(AbstractIdentType.TYPE_TRUE, AbstractIdentType.TYPE_FALSE, AbstractIdentType.TYPE_TRUE);
        logicOrOpTable.put(AbstractIdentType.TYPE_TRUE, AbstractIdentType.TYPE_ANY_BOOL, AbstractIdentType.TYPE_TRUE);
        logicOrOpTable.put(AbstractIdentType.TYPE_FALSE, AbstractIdentType.TYPE_TRUE, AbstractIdentType.TYPE_TRUE);
        logicOrOpTable.put(AbstractIdentType.TYPE_FALSE, AbstractIdentType.TYPE_FALSE, AbstractIdentType.TYPE_FALSE);
        logicOrOpTable.put(AbstractIdentType.TYPE_FALSE, AbstractIdentType.TYPE_ANY_BOOL, AbstractIdentType.TYPE_ANY_BOOL);
        logicOrOpTable.put(AbstractIdentType.TYPE_ANY_BOOL, AbstractIdentType.TYPE_TRUE, AbstractIdentType.TYPE_TRUE);
        logicOrOpTable.put(AbstractIdentType.TYPE_ANY_BOOL, AbstractIdentType.TYPE_FALSE, AbstractIdentType.TYPE_ANY_BOOL);
        logicOrOpTable.put(AbstractIdentType.TYPE_ANY_BOOL, AbstractIdentType.TYPE_ANY_BOOL, AbstractIdentType.TYPE_ANY_BOOL);
    }

    private static void initializeLogicNotOpTable() {
        // logic op: not, since there is no second operand, the second operand is always TYPE_ANY_BOOL
        logicNotOpTable.put(AbstractIdentType.TYPE_TRUE, AbstractIdentType.TYPE_ANY_BOOL, AbstractIdentType.TYPE_FALSE);
        logicNotOpTable.put(AbstractIdentType.TYPE_FALSE, AbstractIdentType.TYPE_ANY_BOOL, AbstractIdentType.TYPE_TRUE);
        logicNotOpTable.put(AbstractIdentType.TYPE_ANY_BOOL, AbstractIdentType.TYPE_ANY_BOOL, AbstractIdentType.TYPE_ANY_BOOL);
    }

    private static void initializeMergeOpTable() {
        // first op: neg
        mergeOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_NEG);
        mergeOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_INT);
        mergeOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_INT);
        mergeOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT);
        mergeOpTable.put(AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT);
        // first op: zero
        mergeOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_INT);
        mergeOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ZERO);
        mergeOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_INT);
        mergeOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT);
        mergeOpTable.put(AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT);
        // first op: pos
        mergeOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_INT);
        mergeOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_INT);
        mergeOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_POS);
        mergeOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT);
        mergeOpTable.put(AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT);
        // first op: any int
        mergeOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_INT);
        mergeOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_INT);
        mergeOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_INT);
        mergeOpTable.put(AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT, AbstractIdentType.TYPE_ANY_INT);
        // first op: any float
        mergeOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_NEG, AbstractIdentType.TYPE_ANY_FLOAT);
        mergeOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ZERO, AbstractIdentType.TYPE_ANY_FLOAT);
        mergeOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_POS, AbstractIdentType.TYPE_ANY_FLOAT);
        mergeOpTable.put(AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT, AbstractIdentType.TYPE_ANY_FLOAT);
    }

    public static void initialize () {
        initializeAddOpTable();
        initializeMinOpTable();
        initializeMulOpTable();
        initializeDivOpTable();

        initializeLtOpTable();
        initializeLeOpTable();
        initializeGtOpTable();
        initializeGeOpTable();
        initializeEqOpTable();
        initializeNeOpTable();

        initializeLogicAndOpTable();
        initializeLogicOrOpTable();
        initializeLogicNotOpTable();

        initializeMergeOpTable();
    }

    public static AbstractIdentType getAddOpType(IdentType type, AbstractIdentType op1, AbstractIdentType op2) {
        AbstractIdentType abstractIdentType = IdentType.TYPE_FLOAT.equals(type) ? AbstractIdentType.TYPE_ANY_FLOAT : AbstractIdentType.TYPE_ANY_INT;
        if (AbstractIdentType.TYPE_NEG.equals(op1) && AbstractIdentType.TYPE_POS.equals(op2)) {
            return abstractIdentType;
        }
        if (AbstractIdentType.TYPE_POS.equals(op1) && AbstractIdentType.TYPE_NEG.equals(op2)) {
            return abstractIdentType;
        }
        return addOpTable.get(op1, op2);
    }

    public static AbstractIdentType getSubOpType(IdentType type, AbstractIdentType op1, AbstractIdentType op2) {
        AbstractIdentType abstractIdentType = IdentType.TYPE_FLOAT.equals(type) ? AbstractIdentType.TYPE_ANY_FLOAT : AbstractIdentType.TYPE_ANY_INT;
        if (AbstractIdentType.TYPE_NEG.equals(op1) && AbstractIdentType.TYPE_NEG.equals(op2)) {
            return abstractIdentType;
        }
        if (AbstractIdentType.TYPE_POS.equals(op1) && AbstractIdentType.TYPE_POS.equals(op2)) {
            return abstractIdentType;
        }
        return subOpTable.get(op1, op2);
    }

    public static AbstractIdentType getMulOpType(AbstractIdentType op1, AbstractIdentType op2) {
        return mulOpTable.get(op1, op2);
    }

    public static AbstractIdentType getDivOpType(IdentType type, AbstractIdentType op1, AbstractIdentType op2) {
        if (AbstractIdentType.TYPE_ZERO.equals(op2) ||
                AbstractIdentType.TYPE_ANY_FLOAT.equals(op2) ||
                AbstractIdentType.TYPE_ANY_INT.equals(op2)) {
            throw new AbstractEvaluationError(AbstractEvaluationError.ErrorType.DIV_BY_ZERO);
        }

        return IdentType.TYPE_FLOAT.equals(type) ? divFloatOpTable.get(op1, op2) : divIntOpTable.get(op1, op2);
    }

    public static AbstractIdentType getLtOpType(AbstractIdentType op1, AbstractIdentType op2) {
        return ltOpTable.get(op1, op2);
    }

    public static AbstractIdentType getLeOpType(AbstractIdentType op1, AbstractIdentType op2) {
        return leOpTable.get(op1, op2);
    }

    public static AbstractIdentType getGtOpType(AbstractIdentType op1, AbstractIdentType op2) {
        return gtOpTable.get(op1, op2);
    }

    public static AbstractIdentType getGeOpType(AbstractIdentType op1, AbstractIdentType op2) {
        return geOpTable.get(op1, op2);
    }

    public static AbstractIdentType getEqOpType(AbstractIdentType op1, AbstractIdentType op2) {
        return eqOpTable.get(op1, op2);
    }

    public static AbstractIdentType getNeOpType(AbstractIdentType op1, AbstractIdentType op2) {
        return neOpTable.get(op1, op2);
    }

    public static AbstractIdentType getLogicAndOpType(AbstractIdentType op1, AbstractIdentType op2) {
        return logicAndOpTable.get(op1, op2);
    }

    public static AbstractIdentType getLogicOrOpType(AbstractIdentType op1, AbstractIdentType op2) {
        return logicOrOpTable.get(op1, op2);
    }

    public static AbstractIdentType getLogicNotOpType(AbstractIdentType op) {
        return logicNotOpTable.get(op, AbstractIdentType.TYPE_ANY_BOOL);
    }

    public static AbstractIdentType mergeOpType(AbstractIdentType op1, AbstractIdentType op2) {
        if (AbstractIdentType.TYPE_UNDEFINED.equals(op1) || AbstractIdentType.TYPE_UNDEFINED.equals(op2)) {
            return AbstractIdentType.TYPE_UNDEFINED;
        }

        return mergeOpTable.get(op1, op2);
    }

    public static ScopeStack mergeScopeStack(ScopeStack s1, ScopeStack s2) {
        // merge two scope stacks will drop the last block

        // two scope stack should have the same depth
        if (s1.getScopeStack().size() != s2.getScopeStack().size()) {
            throw new IllegalStateException("error in merging scope stacks: scope stacks have different sizes");
        }

        ScopeStack mergedStack = new ScopeStack();

        ScopeBlock[] array1 = s1.getScopeStack().toArray(new ScopeBlock[0]);
        ScopeBlock[] array2 = s2.getScopeStack().toArray(new ScopeBlock[0]);

        // don't handle the last block as they will be dropped later
        for (int i = s1.getScopeStack().size() - 1; i >= 0; i --) {
            // new scope block
            ScopeBlock newBlock = new ScopeBlock(array1[i].scopeTag);

            ScopeBlock block1 = array1[i];
            ScopeBlock block2 = array2[i];

            // handle ident map, whichever is okay as they are the same
            newBlock.setIdentMap(IdentMap.cloneFromExisting(block1.identMap));

            // handle abstract state
            AbstractState newState = new AbstractState();
            for (String key : block1.abstractState.keySet()) {
                newState.put(key, AbstractEvaluator.mergeOpType(block1.abstractState.get(key), block2.abstractState.get(key)));
            }

            newBlock.setAbstractState(newState);

            mergedStack.push(newBlock);
        }

        return mergedStack;
    }

    public static boolean isSameStackScope(ScopeStack s1, ScopeStack s2) {
        if (s1.getScopeStack().size() != s2.getScopeStack().size()) {
            throw new IllegalStateException("error in comparing scope stacks: scope stacks have different sizes");
        }

        ScopeBlock[] array1 = s1.getScopeStack().toArray(new ScopeBlock[0]);
        ScopeBlock[] array2 = s2.getScopeStack().toArray(new ScopeBlock[0]);

        // don't handle the last block as they will be dropped later
        for (int i = 0; i < s1.getScopeStack().size() - 1; i ++) {
            for (String key : array1[i].getAbstractState().keySet()) {
                if (!array1[i].getAbstractState().get(key).equals(array2[i].getAbstractState().get(key))) {
                    return false;
                }
            }
        }

        return true;
    }

}
