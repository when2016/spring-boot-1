package xyz.wongs.service.impl;

import org.springframework.stereotype.Service;
import xyz.wongs.annotation.Loggable;
import xyz.wongs.service.FetchDataService;

import java.util.ArrayList;
import java.util.Random;

@Loggable
@Service
public class FetchDataServiceImpl implements FetchDataService {

    @Override
    public void fetchSomeData(FetchDataRequest request) {
        try {
            FetchDataDto fetchDto=new FetchDataDto();
            List<FetchDataDto> fetchDataDtoList=new ArrayList<>();
            fetchDto.setId(1001L);
            fetchDto.setName("Tom");
            fetchDto.setSurName("Walker");
            fetchDto.setAddressInfo("Chicago");
            fetchDataDtoList.add(fetchDto);
            fetchDto.setId(1002L);
            fetchDto.setName("Clark");
            fetchDto.setSurName("Michigan");
            fetchDto.setAddressInfo("New York");
            fetchDataDtoList.add(fetchDto);
            fetchDto.setId(1003L);
            fetchDto.setName("Tom");
            fetchDto.setSurName("Walker");
            fetchDto.setAddressInfo("Chicago");
            fetchDataDtoList.add(fetchDto);
            fetchDto.setId(1004L);
            fetchDto.setName("Clark");
            fetchDto.setSurName("Michigan");
            fetchDto.setAddressInfo("New York");
            fetchDataDtoList.add(fetchDto);
            fetchDto.setId(1005L);
            fetchDto.setName("Tom");
            fetchDto.setSurName("Walker");
            fetchDto.setAddressInfo("Chicago");
            fetchDataDtoList.add(fetchDto);
            fetchDto.setId(1006L);
            fetchDto.setName("Clark");
            fetchDto.setSurName("Michigan");
            fetchDto.setAddressInfo("New York");
            fetchDataDtoList.add(fetchDto);
            request.setFetchDataDtoList(fetchDataDtoList);
            Thread.sleep(new Random().nextInt(2000)+1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
