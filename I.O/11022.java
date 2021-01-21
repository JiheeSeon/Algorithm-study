import java.io.*;

class Main11022 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder stb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int currIdx = 1;
        String s;
        while(currIdx <= T){
            s = br.readLine();
            stb.append("Case #").append(currIdx++).append(": ").append(s.replace(" "," + ")).append(" = ").append(Integer.parseInt(s.split(" ")[0])+Integer.parseInt(s.split(" ")[1])).append("\n");
        }
        bw.write(stb.toString());
        bw.flush();
        bw.close();
    }
}