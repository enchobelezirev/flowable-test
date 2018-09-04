package parallel.flowable.test;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class DummyStep4 implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("DummyStep4");
    }

}
