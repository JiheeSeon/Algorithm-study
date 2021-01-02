T = int(input())
for i in range(T):
    line = list(map(lambda a: int(a), input().split(" ")))
    print(line[0]+line[1])