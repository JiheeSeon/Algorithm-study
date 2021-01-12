#include <cstdio>

/*focus : scanf() 가 읽어들인 입력값의 개수를 반환한다는 점*/
int main(){
    int a,b;
    while(scanf("%d %d", &a, &b) == 2){
        printf("%d\n", a+b);
    }
}