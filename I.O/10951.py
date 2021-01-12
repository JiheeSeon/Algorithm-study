from functools import reduce 
while(True):
    try:
        line = input()
        if not bool(line):
            break
        print(reduce(lambda a,b : int(a)+int(b), line.split(" ")))
    except:
        break