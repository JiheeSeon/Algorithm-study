# To-remember
## I.O
### Buffered~~
#### BufferedReader  <--> Scanner
- `role` 사용자 또는 파일 등의 입력을 한줄 단위로 받기, Scanner보다 빠름
- `import` java.io.BufferedReader, java.io.InputStreamReader
- `creation` BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
- `usage`
  - bw.readLine() // 한줄씩 읽어들임 :: \n기준으로 받음

#### BufferedWriter    <--> System.out.println
- `role` 한번에 출력, System.out.println() 보다 빠름
- `import` java.io.BufferedWriter, java.io.OutputStreamWriter
- `creation` BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
- `usage`
  - bw.write(stringToWrite) // 쓰고자 하는 string을 버퍼에 넣어둠
  - bw.flush() // 버퍼에 있던 값들을 한번에 내보냄
  - bw.close() // 출력버퍼를 닫음

### StringBuilder, StringBuffer   <--> str1 + str2
- `role` String formatting, unboxing, boxing 하는 + 연산자보다 빠름
- `import` java.lang.StringBuilder, java.lang.StringBuffer
- `creation` StringBuilder stb = new StringBuilder();
- `usage`
  - stb.append(stringToRead) // stringbuilder에 스트링을 이어붙임
  - stb.replace(old, new) // 해당 문자를 바꿈
  - stb.toString() // system.out.println, BufferedWriter 는 인자로 String을 받으므로 toString()을 해줘야 함

### StringTokenizer <--> split 
- `role` split과 같이 String을 기준이 되는 token 단위로 쪼갬, 정규식 쓰는 split보다 빠름
- `import` java.util.StringTokenizer
- `creation` StringTokenizer st = new StringTokenizer(inputString);
- `usage`
  - st.hasMoreTokens() // 쪼개진 string이(token) 더 있는지 (boolean) 반환
  - st.nextToken() // 잘라진 다음 string(token)을 받아옴