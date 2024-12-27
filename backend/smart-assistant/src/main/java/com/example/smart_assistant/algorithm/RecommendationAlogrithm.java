package com.example.smart_assistant.algorithm;

public class RecommendationAlogrithm {
    public String recommendOutfit(double tempC, double feelsLikeC, double windChillC, 
                            double heatIndexC, double dewPointC, double windKph, 
                            String condition) {
    StringBuilder outfit = new StringBuilder();

    // 기본 기온 조건
    if (tempC < 0) {
        outfit.append("두꺼운 코트, 목도리, 장갑");
    } else if (tempC < 10) {
        outfit.append("가벼운 코트와 따뜻한 옷");
    } else if (tempC < 20) {
        outfit.append("긴팔 옷과 가벼운 외투");
    } else {
        outfit.append("반팔과 가벼운 옷");
    }

    // Wind Chill 조건
    if (windChillC < tempC - 5) {
        outfit.append(", 추가 방한 용품 (모자, 장갑)");
    }
    if (windChillC < -15) {
        outfit.append(", 얼굴 보호 용품 (스카프, 마스크)");
    }

    // Heat Index 조건
    if (heatIndexC > tempC + 5) {
        outfit.append(", 통기성이 좋은 옷 (린넨 셔츠, 반팔)");
    }
    if (heatIndexC > 35) {
        outfit.append(", 열사병 주의! 물 섭취를 늘리세요.");
    }

    // Dew Point 조건
    if (dewPointC >= 20) {
        outfit.append(", 땀 흡수 잘 되는 옷 (드라이 핏)");
    }
    if (dewPointC >= 25) {
        outfit.append(", 여분의 옷 준비");
    }

    // 바람 속도 조건
    if (windKph > 20) {
        outfit.append(", 바람막이");
    }

    // 날씨 조건에 따른 추가 추천
    if (condition.contains("Rain")) {
        outfit.append(", 우산 또는 방수 옷");
    } else if (condition.contains("Snow")) {
        outfit.append(", 방한 부츠");
    }

    return outfit.toString();
}

    
}
