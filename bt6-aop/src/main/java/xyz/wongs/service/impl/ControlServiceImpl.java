package xyz.wongs.service.impl;


import org.springframework.stereotype.Service;
import xyz.wongs.annotation.Loggable;

import java.util.Random;

@Loggable
@Service
public class ControlServiceImpl {

    public void makeSomeCheck(FetchDataRequest request)
    {
        try {
            Thread.sleep(new Random().nextInt(2000)+1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
