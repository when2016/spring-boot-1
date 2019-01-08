package xyz.wongs.service.impl;

import org.springframework.stereotype.Service;
import xyz.wongs.annotation.Loggable;
import xyz.wongs.service.PersistDataService;

import java.util.Random;

@Loggable
@Service
public class PersistDataServiceImpl implements PersistDataService {

    @Override
    public void persist(FetchDataRequest request) {
        try {
            Thread.sleep(new Random().nextInt(2000)+1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
