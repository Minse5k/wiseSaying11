package com.back.domain.wiseSaying.service;

import com.back.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WiseSayingService {
    List<WiseSaying> wiseList = new ArrayList<>();
    int lastId = 1;

    public WiseSaying write(String author, String content) {
        int id = lastId++;
        WiseSaying item = new WiseSaying(id, author, content);

        wiseList.add(item);

        return item;
    }

    public WiseSaying findById(int id) {
        Optional<WiseSaying> opWise = wiseList.stream()
                .filter(e -> e.getId() == id)
                .findFirst();

        return opWise.orElse(null);
    }

    public void edit(WiseSaying item, String author, String content) {
        item.setAuthor(author);
        item.setContent(content);
    }

    public List<WiseSaying> reverseList() {
        return wiseList.reversed();
    }

    public boolean delete(int id) {
        return wiseList.removeIf(e -> e.getId() == id);
    }
}
