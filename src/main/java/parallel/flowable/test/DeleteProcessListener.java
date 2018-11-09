package parallel.flowable.test;

import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;

public class DeleteProcessListener implements FlowableEventListener {

    @Override
    public void onEvent(FlowableEvent event) {
        System.out.println("Delete process listener called...");
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }

    @Override
    public boolean isFireOnTransactionLifecycleEvent() {
        return false;
    }

    @Override
    public String getOnTransaction() {
        return null;
    }

}
