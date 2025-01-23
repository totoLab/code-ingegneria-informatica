package tracce.t20220712.parte1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CatenaService {

    // negozio / data / incasso
    final Map<String, Map<String, Integer>> incassiGiornalieriNegozi = new ConcurrentHashMap<>();

    void incrementaGuadagni(int in0, String in1) {
        Map<String, Integer> incassiData = incassiGiornalieriNegozi.get(in1);
        String today = DateUtils.getCurrentDay();
        int incassoPrecedente;
        if (incassiData == null || !incassiData.containsKey(today)) {
            incassoPrecedente = 0;
            if (incassiData == null) {
                incassiData = new ConcurrentHashMap<>();
            }
        } else {
            incassoPrecedente = incassiData.get(today);
        }
        incassiData.put(today, in0 + incassoPrecedente);
    }

    String maxGuadagno() {
        String max = null;
        int differenza_max = 0;
        String today = DateUtils.getCurrentDay();
        String yesterday = DateUtils.previousDay(today);
        synchronized (incassiGiornalieriNegozi) {
            for (String negozio : incassiGiornalieriNegozi.keySet()) {
                Map<String, Integer> incassiData = incassiGiornalieriNegozi.get(negozio);
                if (incassiData != null && incassiData.containsKey(today) && incassiData.containsKey(yesterday)) {
                    int differenza = incassiData.get(today) - incassiData.get(yesterday);
                    if (differenza > differenza_max) {
                        differenza_max = differenza;
                        max = negozio;
                    }
                }
            }
        }
        return max;
    }

    static class DateUtils {

        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        static String previousDay(String date) {
            return LocalDate.parse(date, formatter)
                    .minusDays(1)
                    .format(formatter);
        }

        static String getCurrentDay() {
            return LocalDate.now().format(formatter);
        }

    }

}
