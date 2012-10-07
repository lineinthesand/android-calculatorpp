package org.solovyev.android.calculator;

import jscl.JsclMathEngine;
import org.jetbrains.annotations.NotNull;
import org.mockito.Mockito;
import org.solovyev.android.calculator.history.CalculatorHistory;

/**
 * User: serso
 * Date: 10/7/12
 * Time: 8:40 PM
 */
public class CalculatorTestUtils {

    public static void staticSetUp() throws Exception {
        CalculatorLocatorImpl.getInstance().init(new CalculatorImpl(), newCalculatorEngine(), Mockito.mock(CalculatorClipboard.class), Mockito.mock(CalculatorNotifier.class), Mockito.mock(CalculatorHistory.class));
        CalculatorLocatorImpl.getInstance().getEngine().init();
    }

    @NotNull
    static CalculatorEngineImpl newCalculatorEngine() {
        final MathEntityDao mathEntityDao = Mockito.mock(MathEntityDao.class);

        final JsclMathEngine jsclEngine = JsclMathEngine.getInstance();

        final CalculatorVarsRegistry varsRegistry = new CalculatorVarsRegistry(jsclEngine.getConstantsRegistry(), mathEntityDao);
        final CalculatorFunctionsMathRegistry functionsRegistry = new CalculatorFunctionsMathRegistry(jsclEngine.getFunctionsRegistry(), mathEntityDao);
        final CalculatorOperatorsMathRegistry operatorsRegistry = new CalculatorOperatorsMathRegistry(jsclEngine.getOperatorsRegistry(), mathEntityDao);
        final CalculatorPostfixFunctionsRegistry postfixFunctionsRegistry = new CalculatorPostfixFunctionsRegistry(jsclEngine.getPostfixFunctionsRegistry(), mathEntityDao);

        return new CalculatorEngineImpl(jsclEngine, varsRegistry, functionsRegistry, operatorsRegistry, postfixFunctionsRegistry, null);
    }
}
