package parallel.flowable.test;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class RetrieveFromContext implements JavaDelegate {

    @Override
    public void execute(DelegateExecution context) {
        System.out.println("Getting testVariable from context: " + context.getVariable("testVariable"));
    }

}
