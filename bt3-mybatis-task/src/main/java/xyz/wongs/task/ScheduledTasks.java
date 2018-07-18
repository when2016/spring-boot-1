package xyz.wongs.task;

import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xyz.wongs.domain.User;
import xyz.wongs.domain.UserRepository;

import java.util.Date;
import java.util.Random;

/**
 * @author WCNGS@QQ.CO
 * @version V1.0
 * @Title:
 * @Package spring-boot xyz.wongs.task
 * @Description: TODO
 * @date 2018/6/20 16:48
 **/
@Component
public class ScheduledTasks {

    @Autowired
    private UserRepository userRepository;

    @Scheduled(fixedRate = 5000)
    public void scheduMethod(){
        String formatName = DateUtil.format(new Date(),"ddHHmmss");

        User user = new User();
        user.setName(formatName);
        user.setAge(new Random().nextInt(100));
        userRepository.save(user);
    }

}
