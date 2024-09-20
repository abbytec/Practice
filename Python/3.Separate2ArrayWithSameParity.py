from io import StringIO
import sys

# Simulated input
inputs = """7
8
1 2 4 3 2 3 5 4
2
4 7
3
3 9 8
2
1 7
5
5 4 3 2 1
4
4 3 4 5
2
50 48"""

# Redirect stdin to the simulated input
sys.stdin = StringIO(inputs)

def se_puede(args: list) -> bool:
    impares = obtener_impares(args)
    if(len(impares)>0 and len(impares)%2 == 0 and len(args) > 1):
        return True
    if(len(impares)==0 and len(args) > 1 and len(args) % 2 == 0):
        return True
    else:
        return False

def obtener_impares(args: list) -> list:
    return list(filter(lambda x: x % 2 == 1, args))

if __name__ == "__main__":
    cantidad_T = int(input())
    resultados = []
    for _ in range(cantidad_T):
        largo_n = int(input())
        a = list(map(int, input().split()))
        if(largo_n != len(a)):
            raise ValueError("La lista no es del mismo tamaño")
        
        resultados.append(se_puede(a))
        
    for resultado in resultados:
        if(resultado):
            print("YES")
        else:
            print("NO")