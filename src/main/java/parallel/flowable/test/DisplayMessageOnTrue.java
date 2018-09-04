package parallel.flowable.test;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class DisplayMessageOnTrue implements JavaDelegate {

    @Override
    public void execute(DelegateExecution context) {
        System.out.println("Displaying massage: " + (String) context.getVariable("message"));
    }

}
