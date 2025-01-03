package com.example.smart_assistant.algorithm;

import java.util.*;
import java.util.stream.Collectors;

public class TextSummarizer {
    public String summarizerText(String text, int sentenceCount) {
        // 문장 분리
        String[] sentences = text.split("(?<=.!?])\\s+");

        // 단어 빈도 계산
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String sentence : sentences) {
            String[] words = sentence.toLowerCase().replaceAll("[^a-zA-Z ]", "").split("\\s+");
            for (String word : words) { 
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }

        // 문장 점수 계산
        Map<String, Integer> sentenceScores = new HashMap<>();
        for (String sentence : sentences) {
            String[] words = sentence.toLowerCase().replaceAll("[^a-zA-Z ]", "").split("\\s+");
            int score = 0;
            for (String word : words) {
                score += wordFrequency.getOrDefault(word, 0);
            }
            sentenceScores.put(sentence, score);
        }

        // 상위 문장 선택
        List<String> sortedSentences = sentenceScores.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(sentenceCount)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        // 텍스트 변환
        return String.join(" ", sortedSentences);
    }


}
