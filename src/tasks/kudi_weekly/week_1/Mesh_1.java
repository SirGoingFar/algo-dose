package tasks.kudi_weekly.week_1;

import java.util.HashSet;
import java.util.Set;

//Todo: Submission by Mesh
public class Mesh_1 {

    public static void main(final String[] args) {
        /*System.out.println("Aab ;c ---> " + isUnique_O_of_N_2("Aab ;c"));
        System.out.println("AB  cD ---> " + isUnique_O_of_N_2("AB  cD"));
        System.out.println(" AAbc ---> " + isUnique_O_of_N_2(" AAbc"));
        System.out.println("; ;B cd ---> " + isUnique_O_of_N_2("; ;B cd"));
        System.out.println("A__abc ---> " + isUnique_O_of_N_2("A__abc"));
        System.out.println("ABc,D ---> " + isUnique_O_of_N_2("ABc,D"));
        System.out.println("AAbc  ---> " + isUnique_O_of_N_2("AAbc "));
        System.out.println("   dBc ---> " + isUnique_O_of_N_2("    dBc"));*/

        String string =
                "Aabkkdjekdskjskskaskakajsajhshasjdkaisajajdhadahjsalksjlkdjalsijdauhduieghdeodjha8iyd8wefeildfjeue9uda;osipaduaioaedasekdjendekdjheijojefojdioejcoidcjdodxiodkkjkdsusdskkssusskdkdkjdjuusjskdkdkdjhskkdkdjdhgydseierldkdudsudkdjsjsoihbhjhnnfjhtrdgjkopjkbhjguguujvgfytfikghhiujuujgjvgjugjugujhtftyhjhgujhvjhvfvhj,glkukyglugjkyugy,yfyuggjyugkuyjgcygfvyhgyugjhjkknbbjhgfftfyjkhjgfyufyhvvfyhjyfghvfykfytfgjkhgmhgkjjgfmhfcgfjdc,jghfdkvfljkisdioewiemsjaAcAabkkdjekdskjskskaskakajsajhshasjdkaisajajdhadahjsalksjlkdjalsijdauhduieghdeodjha8iyd8wefeildfjeue9uda;osipaduaioaedasekdjendekdjheijojefojdioejcoidcjdodxiodkkjkdsusdskkssusskdkdkjdjuusjskdkdkdjhskkdkdjdhgydseierldkdudsudkdjsjsoihbhjhnnfjhtrdgjkopjkbhjguguujvgfytfikghhiujuujgjvgjugjugujhtftyhjhgujhvjhvfvhj,glkukyglugjkyugy,yfyuggjyugkuyjgcygfvyhgyugjhjkknbbjhgfftfyjkhjgfyufyhvvfyhjyfghvfykfytfgjkhgmhgkjjgfmhfcgfjdc,jghfdkvfljkisdioewiemsjaAcAabkkdjekdskjskskaskakajsajhshasjdkaisajajdhadahjsalksjlkdjalsijdauhduieghdeodjha8iyd8wefeildfjeue9uda;osipaduaioaedasekdjendekdjheijojefojdioejcoidcjdodxiodkkjkdsusdskkssusskdkdkjdjuusjskdkdkdjhskkdkdjdhgydseierldkdudsudkdjsjsoihbhjhnnfjhtrdgjkopjkbhjguguujvgfytfikghhiujuujgjvgjugjugujhtftyhjhgujhvjhvfvhj,glkukyglugjkyugy,yfyuggjyugkuyjgcygfvyhgyugjhjkknbbjhgfftfyjkhjgfyufyhvvfyhjyfghvfykfytfgjkhgmhgkjjgfmhfcgfjdc,jghfdkvfljkisdioewiemsjaAc";
        long then = System.currentTimeMillis();
        isUnique_O_of_N_2(string);
        System.out.println(System.currentTimeMillis() - then);
    }

    //MTH 1’s Exec time: 1ms, 1ms, 1ms, 1ms, 1ms | Meta -> [Time Complexity = O(N), Java]
    static boolean isUnique_O_of_N(final String value) { // 0 (N)

        final Set<Character> set = new HashSet<>();

        for (int i = 0; i < value.length(); i++) { //  O (N)
            set.add(value.charAt(i)); // O (N * 1)
        }

        return set.size() == value.length(); // 0 (1)

    }

    //MTH 2’s Exec time: ~0ms, ~0ms, ~0ms, ~0ms, ~0ms | Meta -> [Time Complexity = O(N^2), Java]
    static boolean isUnique_O_of_N_2(final String value) {

        for (int i = 0; i < value.length(); i++) { // O (N)

            for (int j = 0; j < value.length(); j++) { // O (N * N)

                if (i != j & value.charAt(i) == value.charAt(j)) // O (1 * N * N )
                    return false;

            }
        }

        return true;

    }
}
