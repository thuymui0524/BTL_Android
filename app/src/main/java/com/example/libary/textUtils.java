package com.example.libary;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class textUtils {
    public static String removeDiacritics(String text) {
        // Chuẩn hóa chuỗi (normalize) và loại bỏ dấu
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("");
    }
}

