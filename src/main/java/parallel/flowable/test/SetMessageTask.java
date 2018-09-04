package parallel.flowable.test;

import java.util.ArrayList;
import java.util.List;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class SetMessageTask implements JavaDelegate {

    @Override
    public void execute(DelegateExecution context) {
        List<String> messages = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            messages.add(Integer.toString(i));
        }
        context.setVariable("messages", messages);
    }

}
