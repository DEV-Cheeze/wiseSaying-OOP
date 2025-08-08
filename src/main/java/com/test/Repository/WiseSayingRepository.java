package com.test.Repository;

import com.test.domain.WiseSaying;

import java.io.*;
import java.util.List;
import java.util.Optional;

public interface WiseSayingRepository {

    void memoryLoad() throws IOException;
    void deleteFromMemory(int id);
    void delete(int id);
    void memorySave(WiseSaying wiseSaying);
    void setSequence();
    void save(WiseSaying wiseSaying); //저장해서 json 업데이트
    List<WiseSaying> findAll();
    List<WiseSaying> jsonFilesToObjects() throws IOException;
    WiseSaying jsonFileParser(File file) throws IOException;
    Optional<WiseSaying> findById(int id);
    void editFromMemory(WiseSaying wiseSaying);
}
