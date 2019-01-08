package xyz.wongs;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class StatisticUtils {

    private static AppLogger logger = AppLoggerFactory.getLogger(StatisticUtils.class);

    public static void calculateStatistics(JoinPoint jp, Object arg, StatisticsMapEnum  statisticsMapEnum) {
        try {
            long resultTimemMillis = Calendar.getInstance().getTimeInMillis() - ((BaseRequest) arg).getStartTimeInMillis();

            StatisticsDto statisticsDto;
            Map<String, StatisticsDto> statisticsMap = CacheUtils.statisticsMap.get(statisticsMapEnum);
            if (GeneralUtils.isNullOrEmptyMap(statisticsMap))
                statisticsMap = new HashMap<>();

            if (GeneralUtils.isNullObject(statisticsMap.get(jp.getSignature().getName()))) {
                statisticsDto = new StatisticsDto();
                statisticsDto.setMethodName(jp.getSignature().getName());
                statisticsDto.setTotalTimeInMillis(resultTimemMillis);
                statisticsDto.setMethodCallCount(1);
            } else {
                statisticsDto = statisticsMap.get(jp.getSignature().getName());
                long totalTimeInMillis = statisticsDto.getTotalTimeMillis() + resultTimemMillis;
                statisticsDto.setTotalTimeInMillis((totalTimeInMillis));
                statisticsDto.setMethodCallCount(statisticsDto.getMethodCallCount() +1);
            }
            statisticsMap.put(jp.getSignature().getName(), statisticsDto);
            CacheUtils.statisticsMap.put(statisticsMapEnum,statisticsMap);
        } catch (Exception ex) {
            logger.error("Exception Occured while calculating statistics:" + ExceptionUtils.getStackTrace(ex));
        }
    }

    public static void logMethodStatistics(StatisticsMapEnum  statisticsMapEnum, BatchTypeEnum batchTypeEnum) {
        try {
            Map<String, StatisticsDto> statisticsDtoMap = CacheUtils.statisticsMap.get(statisticsMapEnum);
            if (!GeneralUtils.isNullOrEmptyMap(statisticsDtoMap)) {
                logger.info(batchTypeEnum.toString() + " Statistics: MethodName  -  MethodAverageTime (millis)");
                Set<Map.Entry<String, StatisticsDto>> entrySet = statisticsDtoMap.entrySet();
                Iterator<Map.Entry<String, StatisticsDto>> iterator = entrySet.iterator();

                while (iterator.hasNext()) {
                    Map.Entry<String, StatisticsDto> entry = iterator.next();
                    StatisticsDto statisticsDto = entry.getValue();
                    logger.info(statisticsDto.getMethodName() + " - " + Long.valueOf(statisticsDto.getTotalTimeMillis() / statisticsDto.getMethodCallCount()) + " ms");
                }
            }
            statisticsDtoMap = new HashMap<>();
            CacheUtils.statisticsMap.put(statisticsMapEnum,statisticsDtoMap);
        } catch (Exception ex) {
            logger.error("Exception Occured while logging statistics:" + ExceptionUtils.getStackTrace(ex));
         }
    }
}
