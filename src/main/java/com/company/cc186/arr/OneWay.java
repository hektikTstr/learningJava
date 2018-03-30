public class OneWay {
    public static boolean check(String origStr, String cmpStr) {
        if (origStr.length() == cmpStr.length()) {
            System.out.println("check replace");
            return checkReplace(origStr, cmpStr);
        } else if (origStr.length() + 1 == cmpStr.length()) {
            System.out.println("check insert");
            return checkInsert(origStr, cmpStr);
        } else if (origStr.length() == cmpStr.length() + 1) {
            System.out.println("check remove");
            return checkInsert(cmpStr, origStr);
        } else {
            return false;
        }
    }

    public static boolean checkReplace(String origStr, String cmpStr) {
        boolean isReplace = false;
        for (int i = 0; i < origStr.length(); i++) {
            if (origStr.charAt(i) != cmpStr.charAt(i)) {
                if (isReplace) {
                    return false;
                }
                isReplace = true;
            }
        }
        return isReplace;
    }

    public static boolean checkInsert(String origStr, String cmpStr) {
        int indexOrig = 0;
        int indexCmp = 0;
        while (indexOrig < origStr.length() && indexCmp < cmpStr.length()) {
            if (origStr.charAt(indexOrig) != cmpStr.charAt(indexCmp)) {
                indexCmp++;
            } else {
                indexOrig++;
                indexCmp++;
            }
        }
        if (indexOrig < origStr.length()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(check("pale", "pale"));
        System.out.println(check("pale", "pele"));
        System.out.println(check("pale", "apale"));
        System.out.println(check("pale", "ale"));
        System.out.println(check("pale", "ple"));
        System.out.println(check("pale", "pal"));
        System.out.println(check("pale", "pe"));
    }
}