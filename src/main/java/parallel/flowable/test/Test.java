package parallel.flowable.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;

public class Test {
    public static void main(String[] args) throws InterruptedException {
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

        Deployment deployment3 = repositoryService.createDeployment()
            .addClasspathResource("diagrams/holiday-request.bpmn20.xml")
            .deploy();

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
            .deploymentId(deployment.getId())
            .singleResult();
        System.out.println("Found process definition : " + processDefinition.getKey());

        ProcessDefinition processDefinition2 = repositoryService.createProcessDefinitionQuery()
            .deploymentId(deployment2.getId())
            .singleResult();
        System.out.println("Found process definition : " + processDefinition2.getKey());

        ProcessDefinition processDefinition3 = repositoryService.createProcessDefinitionQuery()
            .deploymentId(deployment3.getId())
            .singleResult();
        System.out.println("Found process definition : " + processDefinition3.getName());

        RuntimeService runtimeService = processEngine.getRuntimeService();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employee", "Tersting");
        variables.put("nrOfHolidays", 2);
        variables.put("description", "Male");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("mainProcess");
        while (!processInstance.isEnded()) {
            List<Execution> processInstanceExecutions = processEngine.getRuntimeService()
                .createExecutionQuery()
                .processInstanceId(processInstance.getProcessInstanceId())
                .list();

            System.out.println("You have: " + processInstanceExecutions.size() + " executions");
            for (Execution execution : processInstanceExecutions) {
                System.out.println(execution.getActivityId());
            }
        }

        Thread.sleep(100000);
    }
}
