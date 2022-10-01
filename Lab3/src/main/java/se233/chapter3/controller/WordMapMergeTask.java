package se233.chapter3.controller;

import se233.chapter3.model.FileFreq;

import java.io.File;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class WordMapMergeTask implements Callable<LinkedHashMap<String, ArrayList<FileFreq>>> {
    private Map<String, FileFreq>[] wordMap;
    public WordMapMergeTask(Map<String, FileFreq>[] wordMap) {
        this.wordMap = wordMap;
    }


    @Override
        public LinkedHashMap<String, ArrayList<FileFreq>> call() throws Exception {
        LinkedHashMap<String, ArrayList<FileFreq>> uniqueSets;
        List<Map<String, FileFreq>> wordMapList = new ArrayList<>(Arrays.asList(wordMap));
        uniqueSets = wordMapList.stream()
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.groupingBy(
                        e -> e.getKey(),
                        Collector.of(
                                () -> new ArrayList<FileFreq>(),
                                (list, item) -> list.add(item.getValue()),
                                (current_list, new_items) -> {
                                    current_list.addAll(new_items);
                                    return current_list;
                                })
                ))
            .entrySet()
            .stream()
                //Exercise 1
            .sorted(new Comparator<Map.Entry<String, ArrayList<FileFreq>>>() {
                @Override
                public int compare(Map.Entry<String, ArrayList<FileFreq>> o1, Map.Entry<String, ArrayList<FileFreq>> o2) {
                   int countO1 = 0;
                   int countO2 = 0;

                    for(int i=0; i<o1.getValue().size();i++){
                        countO1 += o1.getValue().get(i).getFreq();
                    }
                    for (int i=0; i<o2.getValue().size();i++){
                        countO2 +=o2.getValue().get(i).getFreq();
                    }

                    return countO2-countO1;
                }
            })
            .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(),
                (v1, v2) -> v1, () -> new LinkedHashMap<>()));
            return uniqueSets;
    }
}



