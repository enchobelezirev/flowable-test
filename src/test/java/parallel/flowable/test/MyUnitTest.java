package parallel.flowable.test;

import java.util.List;

import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.test.FlowableRule;
import org.junit.Rule;
import org.junit.Test;

public class MyUnitTest {

    @Rule
    public FlowableRule flowableRule = new FlowableRule();

    @Test
    @org.flowable.engine.test.Deployment(resources = { "diagrams/main-process.bpmn", "diagrams/sub-process.bpmn" })
    public void test() throws InterruptedException {
        List<Deployment> deployment = flowableRule.getRepositoryService()
            .createDeploymentQuery()
            .list();
        for (Deployment deployment2 : deployment) {
            System.err.println(deployment2.getId());
        }
        List<ProcessDefinition> procDef = flowableRule.getRepositoryService()
            .createProcessDefinitionQuery()
            .deploymentId(deployment.get(0)
                .getId())
            .list();
        for (ProcessDefinition processDefinition : procDef) {
            System.out.println(processDefinition.getKey());
        }

        System.out.println("Starting the process with deployment id: " + procDef.get(0)
            .getName());

        ProcessInstance procInst = flowableRule.getRuntimeService()
            .startProcessInstanceByKey("mainProcess");
        ProcessInstance processInstanceRunning = flowableRule.getRuntimeService()
            .createProcessInstanceQuery()
            .processInstanceId(procInst.getProcessInstanceId())
            .singleResult();
        while (!processInstanceRunning.isEnded()) {
            processInstanceRunning = flowableRule.getRuntimeService()
                .createProcessInstanceQuery()
                .processInstanceId(procInst.getProcessInstanceId())
                .singleResult();
            System.out.println(processInstanceRunning.getActivityId());
        }

    }

}
