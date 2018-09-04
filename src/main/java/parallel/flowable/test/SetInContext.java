package parallel.flowable.test;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class SetInContext implements JavaDelegate {

    @Override
    public void execute(DelegateExecution context) {
        context.setVariable("testVariable", false);
    }

}
