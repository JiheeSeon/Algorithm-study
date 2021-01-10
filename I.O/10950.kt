import java.util.Scanner;
fun main(args: Array<String>) = with(Scanner(System.`in`)){
    var T = nextInt() //Testcase number
    for i in (1..T)
        println(nextInt() + nextInt())
}