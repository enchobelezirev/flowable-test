package parallel.flowable.test;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class DisplayFalseMessage implements JavaDelegate {

    @Override
    public void execute(DelegateExecution context) {
        System.out.println("Dipslaying false message");
    }

}
