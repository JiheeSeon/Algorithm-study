/*
[Input]     [Output]
1 1         2
2 3         5
3 4         7
9 8         17
5 2         7
*/
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/* kotlin-like code */
fun main10951-1() = with(BufferedReader(InputStreamReader(System.`in`))){
    var input : String? = null
    while({input = readLine(); input }() != null) {
        val st = StringTokenizer(input, " ")
        println(st.nextToken().toInt() + st.nextToken().toInt())
    }
}