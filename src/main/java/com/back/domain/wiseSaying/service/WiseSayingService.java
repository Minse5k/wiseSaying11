package com.back.domain.wiseSaying.service;

import com.back.WiseSaying;

import java.util.List;
import java.util.Optional;

public class WiseSayingService {
    int lastId = 1;

    public WiseSaying write(List<WiseSaying> wiseList, String author, String content) {
        int id = lastId++;
        WiseSaying item = new WiseSaying(id, author, content);

        wiseList.add(item);

        return item;
    }

    public WiseSaying findById(List<WiseSaying> wiseList,int id) {
        Optional<WiseSaying> opWise = wiseList.stream()
                .filter(e -> e.getId() == id)
                .findFirst();

        return opWise.orElse(null);
    }

    public void edit(WiseSaying item, String author, String content) {
        item.setAuthor(author);
        item.setContent(content);
    }
}
