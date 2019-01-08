//package xyz.wongs.aspect;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//
//import java.util.Calendar;
//
//public class LoggingAspect {
//
//    @Pointcut("within(@xyz.wongs.annotation.Loggable *)")
//    public void loggable(){
//
//    }
//
//
//    @Before("loggable()")
//    public void beforeMethodStatistics(JoinPoint jp) throws Throwable {
//        Object[] args = jp.getArgs();
//        if (null != args && args.length > 0) {
//            for (Object arg : args) {
//                if (arg instanceof BaseRequest) {
//                    ((BaseRequest) arg).setStartTimeInMillis(Calendar.getInstance().getTimeInMillis());
//                    break;
//                }
//            }
//        }
//    }
//
//    @After("loggable()")
//    public void afterMethodStatistics(JoinPoint jp){
//        Object[] args = jp.getArgs();
//        if (null != args && args.length > 0) {
//            for (Object arg : args) {
//                if (arg instanceof BaseRequest) {
//                    StatisticsUtils.calculateStatistics(jp, arg, StatisticsMapEnum.ASPECT_LOGGER_STATISTICS_MAP);
//                    break;
//                }
//            }
//        }
//    }
//}
