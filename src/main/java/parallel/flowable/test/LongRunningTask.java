package parallel.flowable.test;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class LongRunningTask implements JavaDelegate {

    @Override
    public void execute(DelegateExecution context) {
        System.out.println("This is a long running task... sleeeping for 5 seconds...");
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
        }
    }

}
