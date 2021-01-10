/*첫째 줄에 테스트 케이스의 개수 T가 주어진다.
각 테스트 케이스는 한 줄로 이루어져 있으며, 각 줄에 A와 B가 주어진다. (0 < A, B < 10)*/
#include<cstdio>
int main(){
    int T, A, B;
    scanf("%d", &T);
    for (int i = 0; i< T; i++){
        scanf("%d %d", &A, &B);
        printf("%d\n", A+B);
    }
    return 0;
}