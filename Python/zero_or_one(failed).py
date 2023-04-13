# tiene que obtener la moda de una matriz binaria en MENOS de 55 caracteres
zero_or_one=lambda n,s:[round(sum(c)/n)for c in zip(*s)]
zero_or_one=lambda n,s:[max(x,key=x.count)for x in zip(*s)]