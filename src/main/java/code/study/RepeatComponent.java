package code.study;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class RepeatComponent {
    public static void main(String args[]) throws IOException {
        LocalDate now = LocalDate.now();
        RepeatData repeatData1 = new RepeatData(RepeatType.DAY, 1, null);
        RepeatData repeatData2 = new RepeatData(RepeatType.WEEK, 1, null);
        RepeatData repeatData3 = new RepeatData(RepeatType.MONTH, 1, null);

//        List<LocalDate> generateDates = RepeatType.generateRepeatDates(repeatData1, now, now.plusDays(5));
        List<LocalDate> generateDates = RepeatType.generateRepeatDates(repeatData2, now, now.plusDays(20));
        System.out.println(generateDates.toString());
    }

    public static class RepeatData {
        private RepeatType repeatType;
        private int interval;
        private List<Integer> condition;

        public RepeatData(RepeatType repeatType, int interval, List<Integer> condition) {
            this.repeatType = repeatType;
            this.interval = interval;
            this.condition = condition;
        }
    }

    public enum RepeatType {
        DAY("Day"),
        WEEK("Week"),
        MONTH("Month");

        private final String displayName;

        RepeatType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public static List<LocalDate> generateRepeatDates(RepeatData repeatData, LocalDate start, LocalDate end) {
            List<LocalDate> dates = new ArrayList<>();

            switch (repeatData.repeatType) {
                case DAY:
                    for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(repeatData.interval)) {
                        dates.add(date);
                    }
                    break;
                case WEEK:
                    for (LocalDate date = start; !date.isAfter(end); date = date.plusWeeks(repeatData.interval)) {
                        if (repeatData.condition == null || repeatData.condition.isEmpty()) {
                            dates.add(date);
                        } else {
                            for (int dayOfWeek : repeatData.condition) {
                                LocalDate conditionDate = date.with(DayOfWeek.of(dayOfWeek + 1));
                                if (!conditionDate.isBefore(start) && !conditionDate.isAfter(end)) {
                                    dates.add(conditionDate);
                                }
                            }
                        }
                    }
                    break;
                case MONTH:
                    for (LocalDate date = start; !date.isAfter(end); date = date.plusMonths(repeatData.interval)) {
                        if (repeatData.condition == null || repeatData.condition.isEmpty()) {
                            dates.add(date);
                        } else {
                            for (int dayOfMonth : repeatData.condition) {
                                if (dayOfMonth > 0 && dayOfMonth <= date.lengthOfMonth()) {
                                    LocalDate conditionDate = date.withDayOfMonth(dayOfMonth);
                                    if (!conditionDate.isBefore(start) && !conditionDate.isAfter(end)) {
                                        dates.add(conditionDate);
                                    }
                                }
                            }
                        }
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported RepeatType: " + repeatData.repeatType);
            }

            return dates;
        }
    }
}



