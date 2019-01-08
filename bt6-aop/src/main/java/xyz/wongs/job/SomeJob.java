package xyz.wongs.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;
import xyz.wongs.service.ControlService;
import xyz.wongs.service.FetchDataService;
import xyz.wongs.service.PersistDataService;

@Component
public class SomeJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        FetchDataService fetchDataService = (FetchDataService) ApplicationContextProvider.getApplicationContext().getBean("fetchDataServiceImpl");
        ThreadPoolService threadPoolService = (ThreadPoolService) ApplicationContextProvider.getApplicationContext().getBean("threadPoolServiceImpl");
        PersistDataService persistDataService =(PersistDataService) ApplicationContextProvider.getApplicationContext().getBean("persistDataServiceImpl");
        ControlService controlService =(ControlService) ApplicationContextProvider.getApplicationContext().getBean("controlServiceImpl");
        FetchDataRequest request=new FetchDataRequest() ;
        fetchDataService.fetchSomeData(request);
        controlService.makeSomeCheck(request);
        persistDataService.persist(request);
        StatisticsUtils.logMethodStatistics(BatchTypeEnum.ASPECT_LOGGER_JOB);
    }
}
