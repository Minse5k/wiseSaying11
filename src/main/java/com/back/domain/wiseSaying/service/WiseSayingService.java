package com.back.domain.wiseSaying.service;

import com.back.domain.wiseSaying.entity.WiseSaying;import com.back.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WiseSayingService {
    WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();

    public WiseSaying write(String author, String content) {
        WiseSaying item = new WiseSaying(author, content);

        wiseSayingRepository.save(item);

        return item;
    }

    public WiseSaying findById(int id) {
        return wiseSayingRepository.findById(id);
    }

    public void edit(WiseSaying item, String author, String content) {
        item.setAuthor(author);
        item.setContent(content);
    }

    public List<WiseSaying> reverseList() {
        return wiseSayingRepository.reverseList();
    }

    public boolean delete(int id) {
        return wiseSayingRepository.delete(id);
    }
}
