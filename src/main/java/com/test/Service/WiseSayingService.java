package com.test.Service;

import com.test.Repository.WiseSayingRepository;
import com.test.Repository.WiseSayingRepositoryImpl;
import com.test.domain.WiseSaying;

import java.util.List;
import java.util.Optional;

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


    public List<WiseSaying> findAllWiseSayings(){
        return wiseSayingRepository.findAll();
    }

    public int deleteWiseSaying(int id){
        findOne(id).orElseThrow(() -> new IllegalArgumentException(id + "번 명언은 존재하지 않습니다."));
        wiseSayingRepository.delete(id);
        wiseSayingRepository.deleteFromMemory(id);
        return id;
    }

    public Optional<WiseSaying> findOne(int id){
        return wiseSayingRepository.findById(id);
    }


}
