package com.example.smart_assistant.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RecommendationAlgorithm {

    public String recommendOutfit(double tempC, double feelsLikeC, double windChillC, 
                                    double heatIndexC, double dewPointC, double windKph, 
                                    String condition, double maxTempC, double minTempC, double avgTempC,
                                    double userTempOffset, String season
                                    ) {
        Map<String, Integer> recommendations = new HashMap<>();

        // 사용자 개인화 편차 및 계절별 오프셋 적용
        double seasonalOffset = getSeasonalTempOffset(season);
        // userTempOffset = updateUserTempOffset(userTempOffset, feedback);
        double adjustedTempC = tempC + userTempOffset + seasonalOffset;
        double adjustedFeelsLikeC = feelsLikeC + userTempOffset + seasonalOffset;

        // 기본 기온 조건
        if (adjustedTempC < 0) {
            recommendations.put("Thick Coat", 5); // 두꺼운 코트
            recommendations.put("Scarf", 4); // 목도리
            recommendations.put("Gloves", 4); // 장갑
        } else if (adjustedTempC < 10) {
            recommendations.put("Light Jacket", 3); // 가벼운 코트
            recommendations.put("Warm Clothes", 3); // 따뜻한 옷
        } else if (adjustedTempC < 20) {
            recommendations.put("Long Sleeve Shirt", 2); // 긴팔 옷
            recommendations.put("Light Outerwear", 2); // 가벼운 외투
        } else {
            recommendations.put("T-Shirt", 2); // 반팔 티셔츠
            recommendations.put("Light Clothes", 1); // 가벼운 옷
        }

        // Wind Chill 조건
        if (windChillC < tempC - 5) {
            recommendations.merge("Additional Winter Gear (Hat, Gloves) [wind]", 3, Integer::sum); // 추가 방한 용품 (모자, 장갑)
        }
        if (windChillC < -15) {
            recommendations.merge("Face Protection (Scarf, Mask)", 4, Integer::sum); // 얼굴 보호 용품 (스카프, 마스크)
        }

        // Heat Index 조건
        if (heatIndexC > tempC + 5) {
            recommendations.merge("Breathable Clothes (Linen Shirt, T-Shirt)", 3, Integer::sum); // 통기성이 좋은 옷 (린넨 셔츠, 반팔)
        }
        if (heatIndexC > 35) {
            recommendations.merge("Heatstroke Warning! Stay Hydrated", 5, Integer::sum); /// 열사병 주의! 물 섭취를 늘리세요.
        }

        // Dew Point 조건
        if (dewPointC >= 20) {
            recommendations.merge("Sweat-Absorbing Clothes (Dry Fit)", 3, Integer::sum); // 땀 흡수 잘 되는 옷 (드라이 핏)
        }
        if (dewPointC >= 25) {
            recommendations.merge("Extra Change of Clothes", 2, Integer::sum); // 여분의 옷 준비
        }

        // 바람 속도 조건
        if (windKph > 20) {
            recommendations.merge("Windbreaker", 4, Integer::sum); // 바람막이
        }

        // 날씨 조건에 따른 추가 추천
        if (condition.contains("Rain")) {
            recommendations.merge("Umbrella or Waterproof Clothes", 5, Integer::sum); // 우산 또는 방수 옷
        } else if (condition.contains("Snow")) {
            recommendations.merge("Insulated Boots", 4, Integer::sum); // 방한 부츠
        }

        // 일교차 조건
        double tempRange = maxTempC - minTempC;
        if (tempRange > 10) {
            recommendations.merge("Layered Clothing (Multiple Thin Layers)", 4, Integer::sum); // (일교차) 벗을 수 있는 옷 여러 겹
        } else if (tempRange > 15) {
            recommendations.merge("Prepare for Significant Temperature Swings", 5, Integer::sum); // (일교차) 큰 온도에 변화에 대비 필요
        }

        // 평균 온도 조건
        if (avgTempC < 10) {
            recommendations.merge("\nWarm Clothing for the Day!", 3, Integer::sum); // 하루 종일 따뜻한 옷
        } else if (avgTempC > 25) {
            recommendations.merge("\nLight and Cool Clothing!", 3, Integer::sum); // 가볍고 시원한 옷
        }

        // 가중치 정렬 후 결과 반환
        return recommendations.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(", "));
    }
    public double getSeasonalTempOffset(String season) {
        switch (season.toLowerCase()) {
            case "winter": return -2;  // 추위를 더 심하게 느낀다고 가정
            case "summer": return +1;  // 더위를 더 느낀다고 가정
            default: return 0;         // 기본 편차
        }
    }

    public double updateUserTempOffset(double currentOffset, String feedback) {
        if ("too cold".equals(feedback)) {
            return currentOffset - 1;
        } else if ("too hot".equals(feedback)) {
            return currentOffset + 1;
        }
        return currentOffset;
    }
}
