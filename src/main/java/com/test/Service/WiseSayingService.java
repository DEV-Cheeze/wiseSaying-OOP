package com.test.Service;

import com.test.Repository.WiseSayingRepository;
import com.test.Repository.WiseSayingRepositoryImpl;
import com.test.domain.WiseSaying;

public class WiseSayingService {

    WiseSayingRepository wiseSayingRepository = new WiseSayingRepositoryImpl();

    public void create(String content, String author){
        WiseSaying wiseSaying = new WiseSaying(content, author);
        wiseSayingRepository.memorySave(wiseSaying);
        wiseSayingRepository.save(wiseSaying);
        wiseSayingRepository.setSequence();
    }

}
