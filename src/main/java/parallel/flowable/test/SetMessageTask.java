package parallel.flowable.test;

import java.util.Arrays;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class SetMessageTask implements JavaDelegate {

    @Override
    public void execute(DelegateExecution context) {
        context.setVariable("messages", Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8"));
    }

}
