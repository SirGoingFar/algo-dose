package week_1;

public class Akintunde_1 {

    public static void main(String[] args) {

        /*System.out.println("Aab ;c ---> " + isUniqueCharactersV2("Aab ;c"));
        System.out.println("AB  cD ---> " + isUniqueCharactersV2("AB  cD"));
        System.out.println(" AAbc ---> " + isUniqueCharactersV2(" AAbc"));
        System.out.println("; ;B cd ---> " + isUniqueCharactersV2("; ;B cd"));
        System.out.println("A__abc ---> " + isUniqueCharactersV2("A__abc"));
        System.out.println("ABc,D ---> " + isUniqueCharactersV2("ABc,D"));
        System.out.println("AAbc  ---> " + isUniqueCharactersV2("AAbc "));
        System.out.println("   dBc ---> " + isUniqueCharactersV2("    dBc"));*/

        String string =
                "Aabkkdjekdskjskskaskakajsajhshasjdkaisajajdhadahjsalksjlkdjalsijdauhduieghdeodjha8iyd8wefeildfjeue9uda;osipaduaioaedasekdjendekdjheijojefojdioejcoidcjdodxiodkkjkdsusdskkssusskdkdkjdjuusjskdkdkdjhskkdkdjdhgydseierldkdudsudkdjsjsoihbhjhnnfjhtrdgjkopjkbhjguguujvgfytfikghhiujuujgjvgjugjugujhtftyhjhgujhvjhvfvhj,glkukyglugjkyugy,yfyuggjyugkuyjgcygfvyhgyugjhjkknbbjhgfftfyjkhjgfyufyhvvfyhjyfghvfykfytfgjkhgmhgkjjgfmhfcgfjdc,jghfdkvfljkisdioewiemsjaAcAabkkdjekdskjskskaskakajsajhshasjdkaisajajdhadahjsalksjlkdjalsijdauhduieghdeodjha8iyd8wefeildfjeue9uda;osipaduaioaedasekdjendekdjheijojefojdioejcoidcjdodxiodkkjkdsusdskkssusskdkdkjdjuusjskdkdkdjhskkdkdjdhgydseierldkdudsudkdjsjsoihbhjhnnfjhtrdgjkopjkbhjguguujvgfytfikghhiujuujgjvgjugjugujhtftyhjhgujhvjhvfvhj,glkukyglugjkyugy,yfyuggjyugkuyjgcygfvyhgyugjhjkknbbjhgfftfyjkhjgfyufyhvvfyhjyfghvfykfytfgjkhgmhgkjjgfmhfcgfjdc,jghfdkvfljkisdioewiemsjaAcAabkkdjekdskjskskaskakajsajhshasjdkaisajajdhadahjsalksjlkdjalsijdauhduieghdeodjha8iyd8wefeildfjeue9uda;osipaduaioaedasekdjendekdjheijojefojdioejcoidcjdodxiodkkjkdsusdskkssusskdkdkjdjuusjskdkdkdjhskkdkdjdhgydseierldkdudsudkdjsjsoihbhjhnnfjhtrdgjkopjkbhjguguujvgfytfikghhiujuujgjvgjugjugujhtftyhjhgujhvjhvfvhj,glkukyglugjkyugy,yfyuggjyugkuyjgcygfvyhgyugjhjkknbbjhgfftfyjkhjgfyufyhvvfyhjyfghvfykfytfgjkhgmhgkjjgfmhfcgfjdc,jghfdkvfljkisdioewiemsjaAc";
        long then = System.currentTimeMillis();
        isUniqueCharactersV2(string);
        System.out.println(System.currentTimeMillis() - then);

    }

    //MTH 1’s Exec time: ~0ms, ~0ms, ~0ms, ~0ms, ~0ms | Meta -> [Time Complexity = O(N^2), Java]
    private static boolean isUniqueCharacters(String string) {

        //Input Validation
        if (string == null || string.isEmpty())
            return false;

        int stringLen = string.length();

        //Iterate through the string characters and compare
        for (int outerIndex = 0; outerIndex < stringLen; outerIndex++) {

            for (int innerIndex = 0; innerIndex < stringLen; innerIndex++) {

                char outerIndexChar = string.charAt(outerIndex);
                char innerIndexChar = string.charAt(innerIndex);

                if (outerIndex != innerIndex && outerIndexChar == innerIndexChar)
                    return false;

            }

        }

        return true;
    }


    //MTH 2’s Exec time: ~0ms, ~0ms, ~0ms, ~0ms, ~0ms | Meta -> [Time Complexity = O(N), With Data Structure, Java]
    private static boolean isUniqueCharactersV2(String string) {

        //Input Validation
        if (string == null || string.isEmpty())
            return false;

        //Todo: Know what String it is: ASCII or UniCode
        //ASCII has 128 characters in its character set
        //Extended ASCII has 256 characters in its alphabet (character set)
        boolean[] char_set = new boolean[128];

        for (int index = 0; index < string.length(); index++) {

            int character = string.charAt(index);

            if (char_set[character])
                return false;

            char_set[character] = true;
        }

        return true;
    }
}
