package parallel.flowable.test;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
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
    public void test2() throws InterruptedException {
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration().setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1")
            .setJdbcUsername("sa")
            .setJdbcPassword("")
            .setJdbcDriver("org.h2.Driver")
            .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        ProcessEngine processEngine = cfg.buildProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
            .addClasspathResource("diagrams/main-process.bpmn")
            .deploy();

        Deployment deployment2 = repositoryService.createDeployment()
            .addClasspathResource("diagrams/sub-process.bpmn")
            .deploy();

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
            .deploymentId(deployment.getId())
            .singleResult();
        System.out.println("Found process definition : " + processDefinition.getName());

        ProcessDefinition processDefinition2 = repositoryService.createProcessDefinitionQuery()
            .deploymentId(deployment2.getId())
            .singleResult();
        System.out.println("Found process definition : " + processDefinition2.getName());

        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("mainProcess");
        System.out.println("Process instance id: " + processInstance.getProcessInstanceId());
        // System.out.println("Process instance running: " processInstance.get);
        Thread.sleep(10000000);
    }

}
