package interviews.rocketgate;
public class Rocketgate {
    public static void main(String[] args) {
        System.out.println(byteToHex("name".getBytes()));
    }

    private static String byteToHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();

        for(byte b : bytes){
            hex.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }

        return hex.toString();
    }

    public static class TestTaker {
        public static String password(String[] passwords, String checkString) {

            if (passwords == null || passwords.length == 0) {
                return "";
            }

            if (checkString == null || checkString.isEmpty()) {
                return "";
            }

            char[] checkStringArray = checkString.toCharArray();
            if (checkStringArray.length != 5) {
                return ""; //length of checkString MUST be 5 based on password attributes
            }

            //First Char
            int numberOfUppercaseSymbol = 0;
            if (!Character.isDigit(checkStringArray[0])) {
                return "";
            }
            numberOfUppercaseSymbol = Integer.parseInt(String.valueOf(checkStringArray[0]));

            //Middle Chars
            final String lastThreeChars = String.format("%s%s%s",
                    String.valueOf(checkStringArray[3]),
                    String.valueOf(checkStringArray[2]),
                    String.valueOf(checkStringArray[1]));

            //Last Char
            int sumOfAllDigits = 0;
            if (!Character.isDigit(checkStringArray[4])) {
                return "";
            }
            sumOfAllDigits = Integer.parseInt(String.valueOf(checkStringArray[4]));

            for (String password : passwords) {

                final char[] passwordCharArray = password.toCharArray();

                int digitSum = 0;
                int capitalLetterCount = 0;
                for (char c : passwordCharArray) {
                    if (Character.isDigit(c)) {
                        digitSum += Integer.parseInt(String.valueOf(c));
                    }

                    if (Character.isUpperCase(c)) {
                        capitalLetterCount++;
                    }
                }

                //Check password attributes as defined by "checkString"
                if (capitalLetterCount == numberOfUppercaseSymbol
                        && password.endsWith(lastThreeChars)
                        && digitSum == sumOfAllDigits
                ) {
                    return password;
                }

            }

            return "";
        }
    }
}
