package com.test.Service;

import com.test.Repository.WiseSayingRepository;
import com.test.Repository.WiseSayingRepositoryImpl;
import com.test.domain.WiseSaying;

public class WiseSayingService {

    WiseSayingRepository wiseSayingRepository = new WiseSayingRepositoryImpl();

    public WiseSaying create(String content, String author){

        isValidateObject(content, author);

        WiseSaying wiseSaying = new WiseSaying(content, author);
        wiseSayingRepository.memorySave(wiseSaying);
        wiseSayingRepository.save(wiseSaying);
        wiseSayingRepository.setSequence();

        return wiseSaying;
    }


    public void isValidateObject(String content, String author){

        String regex = ".*[^ㄱ-ㅎ가-힣a-zA-Z0-9\\s].*";

        boolean contentCheck = content.matches(regex);
        boolean authorCheck = author.matches(regex);
        if(contentCheck || authorCheck) throw new IllegalArgumentException("특수문자는 허용되지 않습니다.");
    }





}
